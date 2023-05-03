package com.andyron.bcdlj.c15.c152;

import java.util.Collections;
import java.util.Map;

/**
 * 同步容器
 * @author andyron
 **/
public class EnhancedMap <K, V> {
    Map<K, V> map;
    public EnhancedMap(Map<K, V> map) {
        this.map = Collections.synchronizedMap(map);
    }
    public V putIfAbsent(K key, V value) {
        synchronized (map) {
            V old = map.get(key);
            if (old != null) {
                return old;
            }
            return map.put(key, value);
        }
    }
    public V put(K key, V value) {
        return map.put(key, value);
    }
    // ...
}
