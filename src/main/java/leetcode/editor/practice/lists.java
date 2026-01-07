import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lists {
    public static void main(String[] args) {
        List<Integer> nums1 = new ArrayList<>();
        Integer[] myArray = new Integer[] { 1, 2, 3, 4, 5 };
        List<Integer> nums = new ArrayList<>(Arrays.asList(myArray));

        int num = nums.get(2);
        System.out.println(num);
        nums.set(5, 4);
        nums.add(3);
    }

}
