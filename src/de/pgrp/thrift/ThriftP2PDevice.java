/**
 * Autogenerated by Thrift Compiler (0.9.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package de.pgrp.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThriftP2PDevice implements org.apache.thrift.TBase<ThriftP2PDevice, ThriftP2PDevice._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ThriftP2PDevice");

  private static final org.apache.thrift.protocol.TField JID_FIELD_DESC = new org.apache.thrift.protocol.TField("jid", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField REMOTE_IP_FIELD_DESC = new org.apache.thrift.protocol.TField("remoteIP", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField LOCAL_IP_FIELD_DESC = new org.apache.thrift.protocol.TField("localIP", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField PORT_FIELD_DESC = new org.apache.thrift.protocol.TField("port", org.apache.thrift.protocol.TType.I32, (short)4);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ThriftP2PDeviceStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ThriftP2PDeviceTupleSchemeFactory());
  }

  public String jid; // required
  public String remoteIP; // required
  public String localIP; // required
  public int port; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    JID((short)1, "jid"),
    REMOTE_IP((short)2, "remoteIP"),
    LOCAL_IP((short)3, "localIP"),
    PORT((short)4, "port");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // JID
          return JID;
        case 2: // REMOTE_IP
          return REMOTE_IP;
        case 3: // LOCAL_IP
          return LOCAL_IP;
        case 4: // PORT
          return PORT;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __PORT_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.JID, new org.apache.thrift.meta_data.FieldMetaData("jid", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.REMOTE_IP, new org.apache.thrift.meta_data.FieldMetaData("remoteIP", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.LOCAL_IP, new org.apache.thrift.meta_data.FieldMetaData("localIP", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PORT, new org.apache.thrift.meta_data.FieldMetaData("port", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ThriftP2PDevice.class, metaDataMap);
  }

  public ThriftP2PDevice() {
  }

  public ThriftP2PDevice(
    String jid,
    String remoteIP,
    String localIP,
    int port)
  {
    this();
    this.jid = jid;
    this.remoteIP = remoteIP;
    this.localIP = localIP;
    this.port = port;
    setPortIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ThriftP2PDevice(ThriftP2PDevice other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetJid()) {
      this.jid = other.jid;
    }
    if (other.isSetRemoteIP()) {
      this.remoteIP = other.remoteIP;
    }
    if (other.isSetLocalIP()) {
      this.localIP = other.localIP;
    }
    this.port = other.port;
  }

  public ThriftP2PDevice deepCopy() {
    return new ThriftP2PDevice(this);
  }

  @Override
  public void clear() {
    this.jid = null;
    this.remoteIP = null;
    this.localIP = null;
    setPortIsSet(false);
    this.port = 0;
  }

  public String getJid() {
    return this.jid;
  }

  public ThriftP2PDevice setJid(String jid) {
    this.jid = jid;
    return this;
  }

  public void unsetJid() {
    this.jid = null;
  }

  /** Returns true if field jid is set (has been assigned a value) and false otherwise */
  public boolean isSetJid() {
    return this.jid != null;
  }

  public void setJidIsSet(boolean value) {
    if (!value) {
      this.jid = null;
    }
  }

  public String getRemoteIP() {
    return this.remoteIP;
  }

  public ThriftP2PDevice setRemoteIP(String remoteIP) {
    this.remoteIP = remoteIP;
    return this;
  }

  public void unsetRemoteIP() {
    this.remoteIP = null;
  }

  /** Returns true if field remoteIP is set (has been assigned a value) and false otherwise */
  public boolean isSetRemoteIP() {
    return this.remoteIP != null;
  }

  public void setRemoteIPIsSet(boolean value) {
    if (!value) {
      this.remoteIP = null;
    }
  }

  public String getLocalIP() {
    return this.localIP;
  }

  public ThriftP2PDevice setLocalIP(String localIP) {
    this.localIP = localIP;
    return this;
  }

  public void unsetLocalIP() {
    this.localIP = null;
  }

  /** Returns true if field localIP is set (has been assigned a value) and false otherwise */
  public boolean isSetLocalIP() {
    return this.localIP != null;
  }

  public void setLocalIPIsSet(boolean value) {
    if (!value) {
      this.localIP = null;
    }
  }

  public int getPort() {
    return this.port;
  }

  public ThriftP2PDevice setPort(int port) {
    this.port = port;
    setPortIsSet(true);
    return this;
  }

  public void unsetPort() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __PORT_ISSET_ID);
  }

  /** Returns true if field port is set (has been assigned a value) and false otherwise */
  public boolean isSetPort() {
    return EncodingUtils.testBit(__isset_bitfield, __PORT_ISSET_ID);
  }

  public void setPortIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __PORT_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case JID:
      if (value == null) {
        unsetJid();
      } else {
        setJid((String)value);
      }
      break;

    case REMOTE_IP:
      if (value == null) {
        unsetRemoteIP();
      } else {
        setRemoteIP((String)value);
      }
      break;

    case LOCAL_IP:
      if (value == null) {
        unsetLocalIP();
      } else {
        setLocalIP((String)value);
      }
      break;

    case PORT:
      if (value == null) {
        unsetPort();
      } else {
        setPort((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case JID:
      return getJid();

    case REMOTE_IP:
      return getRemoteIP();

    case LOCAL_IP:
      return getLocalIP();

    case PORT:
      return Integer.valueOf(getPort());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case JID:
      return isSetJid();
    case REMOTE_IP:
      return isSetRemoteIP();
    case LOCAL_IP:
      return isSetLocalIP();
    case PORT:
      return isSetPort();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ThriftP2PDevice)
      return this.equals((ThriftP2PDevice)that);
    return false;
  }

  public boolean equals(ThriftP2PDevice that) {
    if (that == null)
      return false;

    boolean this_present_jid = true && this.isSetJid();
    boolean that_present_jid = true && that.isSetJid();
    if (this_present_jid || that_present_jid) {
      if (!(this_present_jid && that_present_jid))
        return false;
      if (!this.jid.equals(that.jid))
        return false;
    }

    boolean this_present_remoteIP = true && this.isSetRemoteIP();
    boolean that_present_remoteIP = true && that.isSetRemoteIP();
    if (this_present_remoteIP || that_present_remoteIP) {
      if (!(this_present_remoteIP && that_present_remoteIP))
        return false;
      if (!this.remoteIP.equals(that.remoteIP))
        return false;
    }

    boolean this_present_localIP = true && this.isSetLocalIP();
    boolean that_present_localIP = true && that.isSetLocalIP();
    if (this_present_localIP || that_present_localIP) {
      if (!(this_present_localIP && that_present_localIP))
        return false;
      if (!this.localIP.equals(that.localIP))
        return false;
    }

    boolean this_present_port = true;
    boolean that_present_port = true;
    if (this_present_port || that_present_port) {
      if (!(this_present_port && that_present_port))
        return false;
      if (this.port != that.port)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(ThriftP2PDevice other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    ThriftP2PDevice typedOther = (ThriftP2PDevice)other;

    lastComparison = Boolean.valueOf(isSetJid()).compareTo(typedOther.isSetJid());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetJid()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.jid, typedOther.jid);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRemoteIP()).compareTo(typedOther.isSetRemoteIP());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRemoteIP()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.remoteIP, typedOther.remoteIP);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLocalIP()).compareTo(typedOther.isSetLocalIP());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLocalIP()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.localIP, typedOther.localIP);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPort()).compareTo(typedOther.isSetPort());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPort()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.port, typedOther.port);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("ThriftP2PDevice(");
    boolean first = true;

    sb.append("jid:");
    if (this.jid == null) {
      sb.append("null");
    } else {
      sb.append(this.jid);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("remoteIP:");
    if (this.remoteIP == null) {
      sb.append("null");
    } else {
      sb.append(this.remoteIP);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("localIP:");
    if (this.localIP == null) {
      sb.append("null");
    } else {
      sb.append(this.localIP);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("port:");
    sb.append(this.port);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ThriftP2PDeviceStandardSchemeFactory implements SchemeFactory {
    public ThriftP2PDeviceStandardScheme getScheme() {
      return new ThriftP2PDeviceStandardScheme();
    }
  }

  private static class ThriftP2PDeviceStandardScheme extends StandardScheme<ThriftP2PDevice> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ThriftP2PDevice struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // JID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.jid = iprot.readString();
              struct.setJidIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // REMOTE_IP
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.remoteIP = iprot.readString();
              struct.setRemoteIPIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // LOCAL_IP
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.localIP = iprot.readString();
              struct.setLocalIPIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // PORT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.port = iprot.readI32();
              struct.setPortIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, ThriftP2PDevice struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.jid != null) {
        oprot.writeFieldBegin(JID_FIELD_DESC);
        oprot.writeString(struct.jid);
        oprot.writeFieldEnd();
      }
      if (struct.remoteIP != null) {
        oprot.writeFieldBegin(REMOTE_IP_FIELD_DESC);
        oprot.writeString(struct.remoteIP);
        oprot.writeFieldEnd();
      }
      if (struct.localIP != null) {
        oprot.writeFieldBegin(LOCAL_IP_FIELD_DESC);
        oprot.writeString(struct.localIP);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(PORT_FIELD_DESC);
      oprot.writeI32(struct.port);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ThriftP2PDeviceTupleSchemeFactory implements SchemeFactory {
    public ThriftP2PDeviceTupleScheme getScheme() {
      return new ThriftP2PDeviceTupleScheme();
    }
  }

  private static class ThriftP2PDeviceTupleScheme extends TupleScheme<ThriftP2PDevice> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ThriftP2PDevice struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetJid()) {
        optionals.set(0);
      }
      if (struct.isSetRemoteIP()) {
        optionals.set(1);
      }
      if (struct.isSetLocalIP()) {
        optionals.set(2);
      }
      if (struct.isSetPort()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetJid()) {
        oprot.writeString(struct.jid);
      }
      if (struct.isSetRemoteIP()) {
        oprot.writeString(struct.remoteIP);
      }
      if (struct.isSetLocalIP()) {
        oprot.writeString(struct.localIP);
      }
      if (struct.isSetPort()) {
        oprot.writeI32(struct.port);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ThriftP2PDevice struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.jid = iprot.readString();
        struct.setJidIsSet(true);
      }
      if (incoming.get(1)) {
        struct.remoteIP = iprot.readString();
        struct.setRemoteIPIsSet(true);
      }
      if (incoming.get(2)) {
        struct.localIP = iprot.readString();
        struct.setLocalIPIsSet(true);
      }
      if (incoming.get(3)) {
        struct.port = iprot.readI32();
        struct.setPortIsSet(true);
      }
    }
  }

}

