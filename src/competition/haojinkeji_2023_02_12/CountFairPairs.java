package competition.haojinkeji_2023_02_12;

import java.util.Arrays;

/*力扣：6355. 统计公平数对的数目 显示英文描述
        给你一个下标从 0 开始、长度为 n 的整数数组 nums ，和两个整数 lower 和 upper ，返回 公平数对的数目 。

        如果 (i, j) 数对满足以下情况，则认为它是一个 公平数对 ：

        0 <= i < j < n，且
        lower <= nums[i] + nums[j] <= upper


        示例 1：

        输入：nums = [0,1,7,4,4,5], lower = 3, upper = 6
        输出：6
        解释：共计 6 个公平数对：(0,3)、(0,4)、(0,5)、(1,3)、(1,4) 和 (1,5) 。
        示例 2：

        输入：nums = [1,7,9,2,5], lower = 11, upper = 11
        输出：1
        解释：只有单个公平数对：(2,3) 。


        提示：

        1 <= nums.length <= 105
        nums.length == n
        -109 <= nums[i] <= 109
        -109 <= lower <= upper <= 109*/
public class CountFairPairs {
    public static void main(String[] args) {
        System.out.println(new CountFairPairs().countFairPairs(new int[]{0, 1, 7, 4, 4, 5}, 3, 6));
    }

    //在暴力法基础上优化，查找的时候用二分，所以排序后二分法找上下界
    public long countFairPairs(int[] nums, int lower, int upper) {
        int n = nums.length;
        long sum = 0L;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            //lower <= nums[i] + x <= upper
            //故 lower - nums[i] <= x <= upper - nums[i]
            //则是查找在[lower - nums[i], upper - nums[i]]的数个数
            int max = upper - nums[i];
            int min = lower - nums[i];
            int rightIndex = binSearchUpper(nums, i + 1, n - 1, max);
            int leftIndex = binSearchLower(nums, i + 1, n - 1, min);
            sum += (rightIndex - leftIndex + 1);
        }
        return sum;
    }
    //找第一个<=target的数
    public int binSearchUpper(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + right >> 1;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
    //找第一个>=target的数
    public int binSearchLower(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + right >> 1;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    //暴力法，超时
    public long countFairPairs1(int[] nums, int lower, int upper) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = nums[i] + nums[j];
                if (temp <= upper && temp >= lower) {
                    sum++;
                }
            }
        }
        return sum;
    }
}
