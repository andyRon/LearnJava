package com.andyron.bcdlj.c03.file;

import java.util.Date;

public class MyFile {
    private String name;

    private Date createTime;

    private int size;

    private MyFolder parent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public MyFolder getParent() {
        return parent;
    }

    public void setParent(MyFolder parent) {
        this.parent = parent;
    }
}
