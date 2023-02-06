package com.andyron.bcdlj.c03.file;

import java.util.Date;

public class MyFolder {
    private String name;

    private Date creatTime;

    private MyFolder parent;

    private MyFile[] files;

    private MyFolder[] folders;

    public int totalSize() {
        int totalSize = 0;
        if (files != null) {
            for (MyFile file : files) {
                totalSize += file.getSize();
            }
        }
        if (folders != null) {
            for (MyFolder folder : folders) {
                totalSize += folder.totalSize();
            }
        }
        return totalSize;
    }
}
