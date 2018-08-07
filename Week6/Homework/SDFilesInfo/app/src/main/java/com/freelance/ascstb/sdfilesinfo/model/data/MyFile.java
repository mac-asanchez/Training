package com.freelance.ascstb.sdfilesinfo.model.data;

public class MyFile {
    String name;
    long size;
    String extension;

    public MyFile(String name, long size, String extension) {
        this.name = name;
        this.size = size;
        this.extension = extension;
    }

    @Override
    public String toString() {
        return "MyFile{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", extension='" + extension + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
