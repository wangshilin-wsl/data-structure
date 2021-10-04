package search;
//二分查找法，必须要求数组是有序的
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = { 1,2,5,6,9,11,15,66};
        int i = method(arr, 0, arr.length - 1, 66);
        System.out.println(i);
    }
    public static int method(int[] nums, int low, int high, int target) {
        while (low <= high) {
            int middle = (low + high) / 2;
            if (target == nums[middle]) {
                return middle;
            } else if (target > nums[middle]){
                low = middle + 1;
            } else if (target < nums[middle]) {
                high = middle - 1;
            }
        }
        return -1;
    }
}
