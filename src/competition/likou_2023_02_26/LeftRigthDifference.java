package competition.likou_2023_02_26;

import java.util.Arrays;

/*力扣：6369. 左右元素和的差值 显示英文描述
        给你一个下标从 0 开始的整数数组 nums ，请你找出一个下标从 0 开始的整数数组 answer ，其中：

        answer.length == nums.length
        answer[i] = |leftSum[i] - rightSum[i]|
        其中：

        leftSum[i] 是数组 nums 中下标 i 左侧元素之和。如果不存在对应的元素，leftSum[i] = 0 。
        rightSum[i] 是数组 nums 中下标 i 右侧元素之和。如果不存在对应的元素，rightSum[i] = 0 。
        返回数组 answer 。



        示例 1：

        输入：nums = [10,4,8,3]
        输出：[15,1,11,22]
        解释：数组 leftSum 为 [0,10,14,22] 且数组 rightSum 为 [15,11,3,0] 。
        数组 answer 为 [|0 - 15|,|10 - 11|,|14 - 3|,|22 - 0|] = [15,1,11,22] 。
        示例 2：

        输入：nums = [1]
        输出：[0]
        解释：数组 leftSum 为 [0] 且数组 rightSum 为 [0] 。
        数组 answer 为 [|0 - 0|] = [0] 。


        提示：

        1 <= nums.length <= 1000
        1 <= nums[i] <= 105*/
public class LeftRigthDifference {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new LeftRigthDifference().leftRigthDifference(new int[]{10, 4, 8, 3})));
    }

    public int[] leftRigthDifference(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n], left = new int[n], right = new int[n];
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                left[i] = left[i - 1] + nums[i - 1];
                right[n - 1 - i] = right[n - i] + nums[n - i];
            }
        }
        for (int i = 0; i < n; i++) {
            answer[i] = Math.abs(left[i] - right[i]);
        }
        return answer;
    }
}
