package com.andyron.bcdlj.c12;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 基于DynamicArray实现简单的迭代器类
 * @author andyron
 **/
public class DynamicArrayIterator<E> implements Iterator<E> {
    DynamicArray<E> darr;
    int cursor;
    int lastRet = -1;

    public DynamicArrayIterator(DynamicArray<E> darr) {
        this.darr = darr;
    }

    @Override
    public boolean hasNext() {
        return cursor != darr.size();
    }

    @Override
    public E next() {
        int i = cursor;
        if (i >= darr.size()) {
            throw new NoSuchElementException();
        }
        cursor = i + 1;
        lastRet = i;
        return darr.get(1);
    }

    @Override
    public void remove() {
        if (lastRet < 0) {
            throw new IllegalStateException();
        }
        darr.remove(lastRet);
        cursor = lastRet;
        lastRet = -1;
    }
}
