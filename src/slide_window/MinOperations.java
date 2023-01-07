package slide_window;

import java.util.Arrays;

/*力扣：1658. 将 x 减到 0 的最小操作数
        给你一个整数数组 nums 和一个整数 x 。每一次操作时，你应当移除数组 nums 最左边或最右边的元素，然后从 x 中减去该元素的值。请注意，需要 修改 数组以供接下来的操作使用。

        如果可以将 x 恰好 减到 0 ，返回 最小操作数 ；否则，返回 -1 。



        示例 1：

        输入：nums = [1,1,4,2,3], x = 5
        输出：2
        解释：最佳解决方案是移除后两个元素，将 x 减到 0 。
        示例 2：

        输入：nums = [5,6,7,8,9], x = 4
        输出：-1
        示例 3：

        输入：nums = [3,2,20,1,1,3], x = 10
        输出：5
        解释：最佳解决方案是移除后三个元素和前两个元素（总共 5 次操作），将 x 减到 0 。


        提示：

        1 <= nums.length <= 105
        1 <= nums[i] <= 104
        1 <= x <= 109
        思路：滑动窗口，左边界left,右边界right,表示选择的是0-left,right-n*/
public class MinOperations {
    public static void main(String[] args) {
        new MinOperations().minOperations(new int[]{1,1,4,2,3}, 5);
    }

    public int minOperations(int[] nums, int x) {
        //题目可以当做从左边或者右边选择一块当做答案，并且左边或者右边可以为空的
        //left表示从0-left为左边选择的，right表示从right-n是右边选择的
        //初始的时候left=-1表示左边没有选择任何一个，right表示右边都选择了0-n-1
        //如果lsum + rsum = x，说明我们找到了一组答案，对应的操作次数为(left+1)+(n−right)；
        //如果lsum + rsum > x，说明和过大，我们需要将right 向右移动一个位置；
        //如果lsum + rsum < x，说明和过小，我们需要将left 向右移动一个位置。
        int left = -1, right = 0, n = nums.length, lsum = 0, res = Integer.MAX_VALUE;
        int sum = Arrays.stream(nums).sum(), rsum = sum;
        if (sum < x) {
            return -1;
        }
        for (; left < n; left++) {
            if (left != -1) {
                lsum += nums[left];
            }
            while (right < n && lsum + rsum > x) {
                rsum -= nums[right];
                right++;
            }
            if (lsum + rsum == x) {
                res = Math.min(res, (left + 1) + (n - right));
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
