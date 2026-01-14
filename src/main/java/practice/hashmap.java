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

    class HashMapChaining {
        int size;
        int capacity;
        double loadthres;
        int extendRatio;
        List<List<Pair>> buckets;

        public HashMapChaining() {
            this.size = 0;
            this.capacity = 4;
            this.loadthres = 2.0 / 3.0;
            this.extendRatio = 2;
            buckets = new ArrayList<>(capacity);
            for (int i = 0; i < capacity; i++) {
                buckets.add(new ArrayList<>());
            }
        }

        int hashFunc(int key) {
            return key % capacity;
        }

        double loadFactor() {
            return (double) size / capacity;
        }

        void extend() {
            List<List<Pair>> bucketsTmp = buckets;
            capacity *= extendRatio;
            buckets = new ArrayList<>(capacity);
            for (int i = 0; i < capacity; i++) {
                buckets.add(new ArrayList<>());
            }
            size = 0;
            for (List<Pair> bucket : bucketsTmp) {
                for (Pair pair : bucket) {
                    put(pair.key, pair.val);
                }
            }
        }

        String get(int key) {
            int index = hashFunc(key);
            List<Pair> bucket = buckets.get(index);
            for (Pair pair : bucket) {
                if (pair.key == key) {
                    return pair.val;
                }
            }
            return null;
        }

        void put(int key, String val) {
            if (loadFactor() > loadthres) {
                extend();
            }
            int index = hashFunc(key);
            List<Pair> bucket = buckets.get(index);
            for (Pair pair : bucket) {
                if (pair.key == key) {
                    pair.val = val;
                    return;
                }
            }
            Pair pair = new Pair(key, val);
            bucket.add(pair);
            size++;
        }

        void remove(int key) {
            int index = hashFunc(key);
            List<Pair> bucket = buckets.get(index);
            for (Pair pair : bucket) {
                if (pair.key == key) {
                    bucket.remove(pair);
                    size--;
                    break;
                }
            }
        }

        void print() {
            for (List<Pair> bucket : buckets) {
                for (Pair pair : bucket) {
                    System.out.println(pair.key + " -> " + pair.val);
                }
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
