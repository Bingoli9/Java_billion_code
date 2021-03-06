// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Student.proto

package com.yanqun.grpc.proto;

/**
 * <pre>
 *数据结构,定义请求的Request对象
 * </pre>
 *
 * Protobuf type {@code com.yanqun.grpc.proto.MyRequestCourseName}
 */
public  final class MyRequestCourseName extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.yanqun.grpc.proto.MyRequestCourseName)
    MyRequestCourseNameOrBuilder {
private static final long serialVersionUID = 0L;
  // Use MyRequestCourseName.newBuilder() to construct.
  private MyRequestCourseName(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MyRequestCourseName() {
    courseName_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MyRequestCourseName(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            courseName_ = s;
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.yanqun.grpc.proto.StudentData.internal_static_com_yanqun_grpc_proto_MyRequestCourseName_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.yanqun.grpc.proto.StudentData.internal_static_com_yanqun_grpc_proto_MyRequestCourseName_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.yanqun.grpc.proto.MyRequestCourseName.class, com.yanqun.grpc.proto.MyRequestCourseName.Builder.class);
  }

  public static final int COURSENAME_FIELD_NUMBER = 1;
  private volatile java.lang.Object courseName_;
  /**
   * <code>string courseName = 1;</code>
   */
  public java.lang.String getCourseName() {
    java.lang.Object ref = courseName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      courseName_ = s;
      return s;
    }
  }
  /**
   * <code>string courseName = 1;</code>
   */
  public com.google.protobuf.ByteString
      getCourseNameBytes() {
    java.lang.Object ref = courseName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      courseName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getCourseNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, courseName_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getCourseNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, courseName_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.yanqun.grpc.proto.MyRequestCourseName)) {
      return super.equals(obj);
    }
    com.yanqun.grpc.proto.MyRequestCourseName other = (com.yanqun.grpc.proto.MyRequestCourseName) obj;

    boolean result = true;
    result = result && getCourseName()
        .equals(other.getCourseName());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + COURSENAME_FIELD_NUMBER;
    hash = (53 * hash) + getCourseName().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.yanqun.grpc.proto.MyRequestCourseName parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.yanqun.grpc.proto.MyRequestCourseName parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.yanqun.grpc.proto.MyRequestCourseName parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.yanqun.grpc.proto.MyRequestCourseName parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.yanqun.grpc.proto.MyRequestCourseName parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.yanqun.grpc.proto.MyRequestCourseName parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.yanqun.grpc.proto.MyRequestCourseName parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.yanqun.grpc.proto.MyRequestCourseName parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.yanqun.grpc.proto.MyRequestCourseName parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.yanqun.grpc.proto.MyRequestCourseName parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.yanqun.grpc.proto.MyRequestCourseName parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.yanqun.grpc.proto.MyRequestCourseName parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.yanqun.grpc.proto.MyRequestCourseName prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   *数据结构,定义请求的Request对象
   * </pre>
   *
   * Protobuf type {@code com.yanqun.grpc.proto.MyRequestCourseName}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.yanqun.grpc.proto.MyRequestCourseName)
      com.yanqun.grpc.proto.MyRequestCourseNameOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.yanqun.grpc.proto.StudentData.internal_static_com_yanqun_grpc_proto_MyRequestCourseName_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.yanqun.grpc.proto.StudentData.internal_static_com_yanqun_grpc_proto_MyRequestCourseName_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.yanqun.grpc.proto.MyRequestCourseName.class, com.yanqun.grpc.proto.MyRequestCourseName.Builder.class);
    }

    // Construct using com.yanqun.grpc.proto.MyRequestCourseName.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      courseName_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.yanqun.grpc.proto.StudentData.internal_static_com_yanqun_grpc_proto_MyRequestCourseName_descriptor;
    }

    public com.yanqun.grpc.proto.MyRequestCourseName getDefaultInstanceForType() {
      return com.yanqun.grpc.proto.MyRequestCourseName.getDefaultInstance();
    }

    public com.yanqun.grpc.proto.MyRequestCourseName build() {
      com.yanqun.grpc.proto.MyRequestCourseName result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.yanqun.grpc.proto.MyRequestCourseName buildPartial() {
      com.yanqun.grpc.proto.MyRequestCourseName result = new com.yanqun.grpc.proto.MyRequestCourseName(this);
      result.courseName_ = courseName_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.yanqun.grpc.proto.MyRequestCourseName) {
        return mergeFrom((com.yanqun.grpc.proto.MyRequestCourseName)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.yanqun.grpc.proto.MyRequestCourseName other) {
      if (other == com.yanqun.grpc.proto.MyRequestCourseName.getDefaultInstance()) return this;
      if (!other.getCourseName().isEmpty()) {
        courseName_ = other.courseName_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.yanqun.grpc.proto.MyRequestCourseName parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.yanqun.grpc.proto.MyRequestCourseName) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object courseName_ = "";
    /**
     * <code>string courseName = 1;</code>
     */
    public java.lang.String getCourseName() {
      java.lang.Object ref = courseName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        courseName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string courseName = 1;</code>
     */
    public com.google.protobuf.ByteString
        getCourseNameBytes() {
      java.lang.Object ref = courseName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        courseName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string courseName = 1;</code>
     */
    public Builder setCourseName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      courseName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string courseName = 1;</code>
     */
    public Builder clearCourseName() {
      
      courseName_ = getDefaultInstance().getCourseName();
      onChanged();
      return this;
    }
    /**
     * <code>string courseName = 1;</code>
     */
    public Builder setCourseNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      courseName_ = value;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.yanqun.grpc.proto.MyRequestCourseName)
  }

  // @@protoc_insertion_point(class_scope:com.yanqun.grpc.proto.MyRequestCourseName)
  private static final com.yanqun.grpc.proto.MyRequestCourseName DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.yanqun.grpc.proto.MyRequestCourseName();
  }

  public static com.yanqun.grpc.proto.MyRequestCourseName getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<MyRequestCourseName>
      PARSER = new com.google.protobuf.AbstractParser<MyRequestCourseName>() {
    public MyRequestCourseName parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new MyRequestCourseName(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MyRequestCourseName> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MyRequestCourseName> getParserForType() {
    return PARSER;
  }

  public com.yanqun.grpc.proto.MyRequestCourseName getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

