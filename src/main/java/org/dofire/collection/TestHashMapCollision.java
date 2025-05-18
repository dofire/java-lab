package org.dofire.collection;

import java.util.HashMap;
import java.util.Map;

class CustomObject {
    private int value;

    public CustomObject(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CustomObject that = (CustomObject) obj;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        // Intentionally return the same hash code for all instances
        return 42;
    }

    @Override
    public String toString() {
        return "CustomObject{value=" + value + "}";
    }
}

public class TestHashMapCollision {
    public static void main(String[] args) {
        Map<CustomObject, String> map = new HashMap<>();

        CustomObject obj1 = new CustomObject(10);
        CustomObject obj2 = new CustomObject(20);
        CustomObject obj3 = new CustomObject(30);

        map.put(obj1, "Value 1");
        map.put(obj2, "Value 2");
        map.put(obj3, "Value 3");

        System.out.println("Map size: " + map.size());
        System.out.println("Map contents:");
        for (Map.Entry<CustomObject, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // Demonstrate that all objects ended up in the same bucket (conceptually)
        // We can't directly access buckets, but the behavior shows they are handled
        // within the HashMap's internal structure. When retrieving, 'equals()' is used
        // to differentiate.
        System.out.println("\nRetrieving objects:");
        System.out.println("Get obj1: " + map.get(new CustomObject(10)));
        System.out.println("Get obj2: " + map.get(new CustomObject(20)));
        System.out.println("Get obj3: " + map.get(new CustomObject(30)));
    }
}
