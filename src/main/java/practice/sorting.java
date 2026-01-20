package practice;

public class sorting {
    static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    static int medianThree(int[] nums, int left, int mid, int right) {
        int l = nums[left], m = nums[mid], r = nums[right];
        if ((l <= m && m <= r) || (r <= m && m <= l)) {
            return mid;
        }
        if ((m <= l && l <= r) || (r <= l && l <= m)) {
            return left;
        }
        return right;
    }

    static int partition(int[] nums, int left, int right) {
        // 中位数优化哨兵
        // int med = medianThree(nums, left, left + (right - left) / 2, right);
        // swap(nums, left, med);
        int i = left, j = right;
        while (i < j) {
            while (i < j && nums[j] >= nums[left]) {
                j--;
            }
            while (i < j && nums[i] <= nums[left]) {
                i++;
            }
            swap(nums, i, j);
        }
        swap(nums, i, left);
        return i;
    }

    static void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = partition(nums, left, right);
        quickSort(nums, left, pivot - 1);
        quickSort(nums, pivot + 1, right);
        // 快排深度优化：有序数组
        // while (left < right) {
        // int pivot = partition(nums, left, right);
        // if (pivot - left < right - pivot) {
        // quickSort(nums, left, pivot);
        // left = pivot + 1;
        // } else {
        // quickSort(nums, pivot, right);
        // right = pivot - 1;
        // }
        // }
    }

    static void merge(int[] nums, int left, int mid, int right) {
        int[] tmp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            tmp[k++] = nums[i++];
        }
        while (j <= right) {
            tmp[k++] = nums[j++];
        }
        for (int k2 = 0; k2 < tmp.length; k2++) {
            nums[left + k2] = tmp[k2];
        }
    }

    static void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    static void siftDown(int[] nums, int n, int i) {
        while (true) {
            int l = 2 * i + 1, r = 2 * i + 2;
            int ma = i;
            if (l < n && nums[ma] < nums[l]) {
                ma = l;
            }
            if (r < n && nums[ma] < nums[r]) {
                ma = r;
            }
            if (ma == i) {
                break;
            }
            swap(nums, i, ma);
            i = ma;
        }
    }

    static void heapSort(int[] nums) {
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            siftDown(nums, nums.length, i);
        }
        for (int i = nums.length - 1; i > 0; i--) {
            swap(nums, i, 0);
            siftDown(nums, i, 0);
        }
    }

    public static void main(String[] args) {
        int[] nums = { 5, 3, 2, 9, 0, 1, 5, 4, 7 };
        heapSort(nums);
        // mergeSort(nums, 0, nums.length - 1);
        for (int i : nums) {
            System.out.println(i);
        }
    }
}
