package org.dofire;

import java.util.ArrayList;
import java.util.List;

public class StaticFieldsMemoryLeakUnitTest {
    public static List<Double> list = new ArrayList<>();

    public void populateList() {
        for (int i = 0; i < 10000000; i++) {
            list.add(Math.random());
        }
        System.out.print("Debug Point 2");
    }

    public static void main(String[] args) {
        System.out.print("Debug Point 1");
        new StaticFieldsMemoryLeakUnitTest().populateList();
        System.out.print("Debug Point 3");
    }
}