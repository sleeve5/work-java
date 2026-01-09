package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class hashmap {
    class Pair {
        public int key;
        public String val;

        public Pair(int key, String val) {
            this.key = key;
            this.val = val;
        }
    }

    class ArrayHashMap {
        private List<Pair> buckets;

        public ArrayHashMap() {
            buckets = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                buckets.add(null);
            }
        }

        private int hashFunc(int key) {
            int index = key % 100;
            return index;
        }

        public String get(int key) {
            int index = hashFunc(key);
            Pair pair = buckets.get(index);
            if (pair == null) {
                return null;
            }
            return pair.val;
        }

        public void put(int key, String val) {
            int index = hashFunc(key);
            Pair pair = new Pair(key, val);
            buckets.set(index, pair);
        }

        public void remove(int key) {
            int index = hashFunc(key);
            buckets.set(index, null);
        }

        public List<Pair> pairSet() {
            List<Pair> pairSet = new ArrayList<>();
            for (Pair pair : buckets) {
                if (pair != null) {
                    pairSet.add(pair);
                }
            }
            return pairSet;
        }

        public List<Integer> keySet() {
            List<Integer> keySet = new ArrayList<>();
            for (Pair pair : buckets) {
                if (pair != null) {
                    keySet.add(pair.key);
                }
            }
            return keySet;
        }

        public List<String> valueSet() {
            List<String> valueSet = new ArrayList<>();
            for (Pair pair : buckets) {
                if (pair != null) {
                    valueSet.add(pair.val);
                }
            }
            return valueSet;
        }

        public void print() {
            for (Pair pair : buckets) {
                System.out.println(pair.key + " -> " + pair.val);
            }
        }
    }

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();

        map.put(1001, "Alice");
        map.put(1002, "Bob");
        map.put(1003, "Cindy");

        String name = map.get(1002);
        map.remove(1001);

        for (Map.Entry<Integer, String> kv : map.entrySet()) {
            System.out.println(kv.getKey() + " -> " + kv.getValue());
            System.out.println(kv);
        }
        for (int key : map.keySet()) {
            System.out.println(key);
        }
        for (String val : map.values()) {
            System.out.println(val);
        }
    }
}
