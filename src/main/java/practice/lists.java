package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class lists {
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
        List<Integer> myList2 = new ArrayList<>(Arrays.asList(new Integer[] { 3, 5, 7 }));
        nums.addAll(myList2);
        Collections.sort(nums);
        for (int i = 0; i < nums.size(); i++) {
            System.out.println(nums.get(i));
        }
    }
}
