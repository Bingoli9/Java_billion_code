package com.yanqun.netty.file;

import java.io.File;
import java.io.Serializable;

public class MySendFile implements Serializable {
    private static final long serialVersionUID = 1L;
    private File file;
    private String fileName;
    private int start;
    private int end;
    private byte[] bytes;
    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public byte[] getBytes() {
        return bytes;
    }
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
