package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class list {
    class MyList {
        private int[] arr;
        private int capacity = 10;
        private int size = 0;
        private int extendRatio = 2;

        public MyList() {
            arr = new int[capacity];
        }

        public int size() {
            return size;
        }

        public int capacity() {
            return capacity;
        }

        public void extend() {
            arr = Arrays.copyOf(arr, capacity() * extendRatio);
            capacity = arr.length;
        }

        public void add(int num) {
            if (size == capacity()) {
                extend();
            }
            arr[size] = num;
            size++;
        }

        public int remove(int index) {
            if (index < 0 || index >= size()) {
                throw new IndexOutOfBoundsException("Out Of Bound!");
            }
            int num = arr[index];
            for (int i = index; i < size - 1; i++) {
                arr[i] = arr[i + 1];
            }
            size--;
            return num;
        }

        public void modify(int index, int num) {
            if (index < 0 || index >= size()) {
                throw new IndexOutOfBoundsException("Out Of Bound!");
            }
            arr[index] = num;
        }

        public int get(int index) {
            if (index < 0 || index >= size()) {
                throw new IndexOutOfBoundsException("Out Of Bound!");
            }
            return arr[index];
        }

        public void insert(int index, int num) {
            if (index < 0 || index >= size()) {
                throw new IndexOutOfBoundsException("Out Of Bound!");
            }
            if (size == capacity()) {
                extend();
            }
            for (int i = size; i > index; i--) {
                arr[i] = arr[i - 1];
            }
            arr[index] = num;
        }

        public int[] toArray() {
            int size = size();
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = get(i);
            }
            return arr;
        }
    }

    public static void main(String[] args) {
        // List<Integer> myList = new ArrayList<>();
        Integer[] myArray = new Integer[] { 1, 2, 3, 4, 5 };
        List<Integer> nums = new ArrayList<>(Arrays.asList(myArray));

        // int num = nums.get(2);
        nums.set(3, 5);
        nums.add(3);
        nums.add(4, 6);
        nums.remove(3);
        for (int i = 0; i < nums.size(); i++) {
            System.out.println(nums.get(i));
        }
        System.out.println('\n');
        List<Integer> myList2 = new ArrayList<>(Arrays.asList(new Integer[] { 3, 5, 7
        }));
        nums.addAll(myList2);
        Collections.sort(nums);
        for (int i = 0; i < nums.size(); i++) {
            System.out.println(nums.get(i));
        }

        // 创建测试实例
        list outer = new list();
        MyList myList = outer.new MyList();

        System.out.println("=== MyList测试开始 ===");

        // 测试1: 添加元素
        System.out.println("1. 测试add方法:");
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        System.out.println("   添加元素后: " + Arrays.toString(myList.toArray()));
        System.out.println("   大小: " + myList.size() + ", 容量: " + myList.capacity());

        // 测试2: 获取元素
        System.out.println("\n2. 测试get方法:");
        System.out.println("   索引2的元素: " + myList.get(2));
        System.out.println("   索引4的元素: " + myList.get(4));

        // 测试3: 修改元素
        System.out.println("\n3. 测试modify方法:");
        myList.modify(2, 10);
        System.out.println("   修改索引2为10后: " + Arrays.toString(myList.toArray()));

        // 测试4: 插入元素
        System.out.println("\n4. 测试insert方法:");
        myList.insert(1, 20);
        System.out.println("   在索引1插入20后: " + Arrays.toString(myList.toArray()));
        myList.insert(3, 30);
        System.out.println("   在索引3插入30后: " + Arrays.toString(myList.toArray()));

        // 测试5: 删除元素
        System.out.println("\n5. 测试remove方法:");
        int removed = myList.remove(1);
        System.out.println("   删除索引1的元素: " + removed);
        System.out.println("   删除后: " + Arrays.toString(myList.toArray()));
        System.out.println("   大小: " + myList.size() + ", 容量: " + myList.capacity());

        // 测试6: 测试扩容
        System.out.println("\n6. 测试扩容功能:");
        System.out.println("   当前容量: " + myList.capacity());
        for (int i = 0; i < 7; i++) {
            myList.add(i + 100);
        }
        System.out.println("   添加7个元素后: " + Arrays.toString(myList.toArray()));
        System.out.println("   大小: " + myList.size() + ", 容量: " + myList.capacity());

        // 测试7: 测试边界条件
        System.out.println("\n7. 测试边界条件:");
        try {
            myList.get(100);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("   正确捕获越界异常: " + e.getMessage());
        }

        System.out.println("\n=== MyList测试结束 ===");
    }
}
