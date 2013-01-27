/*
 * Peergroup - StorageWorker.java
 * 
 * This file is part of Peergroup.
 *
 * Peergroup is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Peergroup is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * Author : Nicolas Inden
 * Contact: nicolas.inden@rwth-aachen.de
 *
 * Copyright (c) 2013 Nicolas Inden
 */

package de.pgrp.core;

import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.nio.file.*;

/**
 * This thread is listening for file system activities and enqueues events in
 * the global queue.
 * 
 * @author Nicolas Inden
 */
public class StorageWorker extends Thread {

	private WatchService watcher;
	private Map<WatchKey, Path> keys;

	/**
	 * Creates a StorageWorker.
	 */
	public StorageWorker() {
		try {
			this.watcher = FileSystems.getDefault().newWatchService();
			this.keys = new HashMap<WatchKey, Path>();
		} catch (IOException ioe) {
			Constants.log
			.addMsg("Cannot create file-system watcher: " + ioe, 1);
		}

	}

	public void stopStorageWorker() {
		try {
			this.watcher.close();
			this.interrupt();
		} catch (IOException ioe) {
			this.interrupt();
			Constants.log.addMsg("Error: " + ioe, 4);
		}
	}

	/**
	 * The run() method uses the WatchService to monitor our share directory for
	 * changes. Any kind of changes (create/delete/modify) are packed into a
	 * request and are enqueued to be processed by the main thread.
	 */
	public void run() {
		this.setName("Storage Thread");
		Constants.log.addMsg("Storage thread started...");

		// Init WatchService
		registerNewPath(Constants.rootDirectory);

		while (!isInterrupted()) {
			WatchKey signaledKey;
			try {
				// here we are waiting for fs activities
				signaledKey = this.watcher.take();
			} catch (InterruptedException ix) {
				interrupt();
				break;
			} catch (ClosedWatchServiceException cwse) {
				interrupt();
				break;
			}

			// get list of events from key
			List<WatchEvent<?>> list = signaledKey.pollEvents();

			Path dir = keys.get(signaledKey);
			if (dir == null) {
				System.out.println("WatchKey not recognized!!");
				continue;
			}

			// VERY IMPORTANT! call reset() AFTER pollEvents() to allow the
			// key to be reported again by the watch service
			signaledKey.reset();

			for (WatchEvent e : list) {
				if (e.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
					// Entry created
					Path context = (Path) e.context();
					// Ignore hidden files and directories
					if (context.toString().charAt(0) == '.') {
						continue;
					}
					File newEntry = new File(dir.toString() + "/"
							+ context.toString());
					// System.out.print("New: " + newEntry.getPath());
					// For internal handling we use paths relative to the
					// root-share folder
					// Example ./share/file1 -> /file1
					String pathWithoutRoot = getPurePath(dir.toString() + "/"
							+ context.toString());
					if (newEntry.isFile()) {
						// System.out.println(" -- is a file!");
						// System.out.println(pathWithoutRoot);
						insertElement(Constants.delayQueue, new FileEvent(
								Constants.LOCAL_FILE_CREATE, pathWithoutRoot));
					} else if (newEntry.isDirectory()) {
						if (registeredFolder(newEntry.getPath())) {
							continue;
						}
						// System.out.println(" -- is a directory!");
						registerThisAndSubs(newEntry.getPath());
						insertElement(Constants.delayQueue, new FileEvent(
								Constants.LOCAL_DIR_CREATE, pathWithoutRoot));
					}
				} else if (e.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
					// Entry deleted
					Path context = (Path) e.context();
					if (context.toString().charAt(0) == '.') {
						continue;
					}
					File delEntry = new File(dir.toString() + "/"
							+ context.toString());
					// System.out.println("Deleted: " + delEntry.getPath());
					String pathWithoutRoot = getPurePath(dir.toString() + "/"
							+ context.toString());
					if (!registeredFolder(delEntry.getPath())) {
						// System.out.println("File: " + pathWithoutRoot);
						Constants.requestQueue.offer(new FSRequest(
								Constants.LOCAL_FILE_DELETE, pathWithoutRoot));
					} else {
						// System.out.println("Folder: " + pathWithoutRoot);
						deleteThisAndSubs(delEntry.getPath());
						Constants.requestQueue.offer(new FSRequest(
								Constants.LOCAL_DIR_DELETE, pathWithoutRoot));
					}
				} else if (e.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
					// Entry modified
					Path context = (Path) e.context();
					if (context.toString().charAt(0) == '.') {
						continue;
					}
					File modEntry = new File(dir.toString() + "/"
							+ context.toString());
					// System.out.println("Modified: " + modEntry.getPath());
					if (modEntry.isFile()) {
						String pathWithoutRoot = getPurePath(dir.toString()
								+ "/" + context.toString());
						insertElement(Constants.delayQueue, new FileEvent(
								pathWithoutRoot));
					}
				} else if (e.kind() == StandardWatchEventKinds.OVERFLOW) {
					Constants.log
					.addMsg("OVERFLOW: more changes happened than we could retrieve",
							4);
				}
			}
		}
		Constants.log.addMsg("Storage thread interrupted. Closing...", 4);
	}

	/**
	 * Gets the path of a file including the shared folder, and returns the path
	 * without the shared folder.
	 * 
	 * E.g. ./share/log/myLogFile123.log -> log/myLogFile123.log
	 * 
	 * @param entry
	 *            The path to a file in the shared folder
	 * @return The path to this file relative to the shared folder
	 */
	public static String getPurePath(String entry) {
		int rootLength = Constants.rootDirectory.length();
		return entry.substring(rootLength, entry.length());
	}

	private void insertElement(ConcurrentLinkedQueue<FileEvent> list,
			FileEvent me) {
		for (FileEvent e : list) {
			if (e.getName().equals(me.getName())) {
				e.setTime(me.getTime());
				return;
			}
		}
		list.add(me);
	}

	/**
	 * Registers this directory and all sub directories to the WatchService.
	 * Also all files below this new directories are added as new files.
	 * 
	 * @param newDir
	 *            the newly discovered directory in our share folder (absolute
	 *            path)
	 */
	public void registerThisAndSubs(String newDir) {
		File dir = new File(newDir);
		File contents[] = dir.listFiles();

		// System.out.println("Includes: " + contents.length + " elements");

		registerNewPath(newDir);

		for (File sub : contents) {
			if (sub.isDirectory()) {
				registerThisAndSubs(sub.getPath());
				insertElement(Constants.delayQueue, new FileEvent(
						Constants.LOCAL_DIR_CREATE, getPurePath(sub.getPath())));
			} else if (sub.isFile()) {
				if (sub.getName().charAt(0) == '.') {
					continue;
				}
				insertElement(Constants.delayQueue,
						new FileEvent(Constants.LOCAL_FILE_CREATE,
								getPurePath(sub.getPath())));
			}
		}
	}

	/**
	 * Deletes this directory and all sub directories to the WatchService. Also
	 * all files below this new directories are forwarded to the system as
	 * deleted.
	 * 
	 * @param newDir
	 *            the deleted directory in our share folder (absolute path)
	 */
	private void deleteThisAndSubs(String delDir) {
		LinkedList<String> toBeDeleted = new LinkedList<String>();

		for (String folder : Constants.folders) {
			if (folder.startsWith(delDir))
				toBeDeleted.add(folder);
		}

		for (String del : toBeDeleted) {
			Constants.folders.remove(del);
		}
	}

	/**
	 * Registers a new (additional) path at the WatchService (Not in use yet)
	 * 
	 * @param newPath
	 *            the new path relative to the share directory to be registered
	 *            for watching changes
	 */
	public void registerNewPath(String newPath) {
		Path path = Paths.get(newPath);
		Constants.folders.add(newPath);

		WatchKey key = null;
		try {
			key = path.register(this.watcher,
					StandardWatchEventKinds.ENTRY_CREATE,
					StandardWatchEventKinds.ENTRY_DELETE,
					StandardWatchEventKinds.ENTRY_MODIFY);
			Path prev = keys.get(key);
			if (prev == null) {
				// System.out.format("register: %s\n", path);
			} else {
				if (!path.equals(prev)) {
					// System.out.format("update: %s -> %s\n", prev, path);
				}
			}
			keys.put(key, path);
		} catch (UnsupportedOperationException uox) {
			System.err.println("file watching not supported!");
			// handle this error here
		} catch (IOException iox) {
			System.err.println("I/O errors");
			// handle this error here
		}
	}

	private boolean registeredFolder(String name) {
		return Constants.folders.contains(name);
	}
}