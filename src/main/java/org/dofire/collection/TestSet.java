package org.dofire.collection;

import org.dofire.models.User;

import java.util.*;

public class TestSet {
    public static void main(String[] args) {
        var hashSet = new HashSet<String>();
        var linkedHashSet = new LinkedHashSet<String>();
        var treeSet = new TreeSet<String>();

        var user1 = User.fake();
        var user2 = User.fake();

        var user3 = user1.deepClone();
        var d = new Object();

        hashSet.add("1");
        hashSet.add("2");
        hashSet.add("3");
        linkedHashSet.add("1");
        linkedHashSet.add("3");
        linkedHashSet.add("2");
//        treeSet.add(null);

        System.out.println("HashSet: " + hashSet);
        System.out.println("LinkedHashSet: " + linkedHashSet);
        System.out.println("TreeSet: " + treeSet);
    }

}