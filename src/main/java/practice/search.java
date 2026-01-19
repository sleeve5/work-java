package practice;

public class search {
    // 查找target的索引
    static int binarySearch(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] < target) {
                i = mid + 1;
            } else if (nums[mid] > target) {
                j = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    // 若要插入target，查找插入的位置(无重复target)
    static int binarySearchInsertionSimple(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] < target) {
                i = mid + 1;
            } else if (nums[mid] > target) {
                j = mid - 1;
            } else {
                return mid;
            }
        }
        return i;
    }

    // 若要插入target，查找插入的位置(可能有重复target)
    static int binarySearchInsertion(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] < target) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return i;
    }

    // 查找target，若存在给出其左边界，不存在返回-1
    static int binarySearchLeftEdge(int[] nums, int target) {
        int left = binarySearchInsertion(nums, target);
        if (left == nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

    static int binarySearchRightEdge(int[] nums, int target) {
        // 复用插入点写法
        // int right = binarySearchInsertion(nums, target + 1) - 1;
        // if (right < 0 || nums[right] != target) {
        // return -1;
        // }
        // return right;

        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] <= target) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        if (j < 0 || nums[j] != target) {
            return -1;
        }
        return j;
    }

    public static void main(String[] args) {
        int[] array1 = { 1, 3, 5, 7, 9, 11, 13, 17, 22, 29 };
        int[] array2 = { 1, 3, 5, 7, 9, 11, 11, 11, 11, 11, 11, 13, 17, 22, 29 };
        System.out.println(binarySearch(array1, 11));
        System.out.println(binarySearchInsertion(array2, 12));
    }
}
