package org.dofire.collection;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class TestMap {

    public static void main(String[] args) {

        var hashMap = new HashMap<Object, String>();
        var linkedHashMap = new LinkedHashMap<String, String>();
        var treeMap = new TreeMap<String, String>();

        hashMap.put("3", "3");
        hashMap.put('3', "3");
        hashMap.put("2", "2");


        linkedHashMap.put("1", "1");
        linkedHashMap.put("3", "3");
        linkedHashMap.put("2", "2");

        treeMap.put("1", "1");
        treeMap.put("3", "3");
        treeMap.put("2", "2");

        System.out.println("HashMap: " + hashMap);
        System.out.println("LinkedHashMap: " + linkedHashMap);
        System.out.println("TreeMap: " + treeMap);

    }

}
