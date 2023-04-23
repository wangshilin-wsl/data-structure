package competition.likou_2023_04_08;

import java.util.*;

/*力扣：6359. 最小化数对的最大差值 显示英文描述
        通过的用户数378
        尝试过的用户数727
        用户总通过次数384
        用户总提交次数1172
        题目难度Medium
        给你一个下标从 0 开始的整数数组 nums 和一个整数 p 。请你从 nums 中找到 p 个下标对，每个下标对对应数值取差值，你需要使得这 p 个差值的 最大值 最小。同时，你需要确保每个下标在这 p 个下标对中最多出现一次。

        对于一个下标对 i 和 j ，这一对的差值为 |nums[i] - nums[j]| ，其中 |x| 表示 x 的 绝对值 。

        请你返回 p 个下标对对应数值 最大差值 的 最小值 。



        示例 1：

        输入：nums = [10,1,2,7,1,3], p = 2
        输出：1
        解释：第一个下标对选择 1 和 4 ，第二个下标对选择 2 和 5 。
        最大差值为 max(|nums[1] - nums[4]|, |nums[2] - nums[5]|) = max(0, 1) = 1 。所以我们返回 1 。
        示例 2：

        输入：nums = [4,2,1,2], p = 1
        输出：0
        解释：选择下标 1 和 3 构成下标对。差值为 |2 - 2| = 0 ，这是最大差值的最小值。


        提示：

        1 <= nums.length <= 105
        0 <= nums[i] <= 109
        0 <= p <= (nums.length)/2*/
public class MinimizeMax {
    public static void main(String[] args) {
        System.out.println(new MinimizeMax().minimizeMax(new int[]{8, 9, 1, 5, 4, 3, 6, 4, 3, 7}, 4));
    }

    //二分查找法
    public int minimizeMax(int[] nums, int p) {
        //前提：最小值是随p增大而增大的，0 <= nums[i] <= 109，所以在这个区间使用二分法
        int left = 0, right = (int) 1e9;
        Arrays.sort(nums);
        while (left < right) {
            int mid = left + right >> 1, count = 0;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] - nums[i - 1] <= mid) {
                    count++;
                    i++;
                }
            }
            if (count < p) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
