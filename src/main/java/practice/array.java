package practice;

import java.util.concurrent.ThreadLocalRandom;

public class array {
    int[] arr1 = new int[5];
    int[] arr2 = { 1, 2, 3, 4, 5 };

    int randomAccess(int[] nums) {
        int randomIndex = ThreadLocalRandom.current().nextInt(0, nums.length);
        return nums[randomIndex];
    }

    int[] extend(int[] nums, int size) {
        if (size <= nums.length)
            return nums;
        int[] res = new int[size];
        for (int i = 0; i < nums.length; i++) {
            res[i] = nums[i];
        }
        return res;
    }

    void insert(int[] nums, int index, int num) {
        for (int i = nums.length - 1; i > index; i--) {
            nums[i] = nums[i - 1];
        }
        nums[index] = num;
    }

    void delete(int[] nums, int index) {
        for (int i = index; i < nums.length - 1; i++) {
            nums[i] = nums[i + 1];
        }
    }

    void traverse(int[] nums) {
        System.out.println("nums are: ");
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    int find(int[] nums, int num) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        array arr = new array();
        nums = arr.extend(nums, 12);
        System.out.println("length: " + nums.length);
        arr.insert(nums, 10, 10);
        arr.delete(nums, 5);
        arr.traverse(nums);
        System.out.println("index: " + arr.find(nums, 7));
    }
}
