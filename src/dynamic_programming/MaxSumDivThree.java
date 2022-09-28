package dynamic_programming;

/*力扣：1262. 可被三整除的最大和
        给你一个整数数组 nums，请你找出并返回能被三整除的元素最大和。

        示例 1：
        输入：nums = [3,6,5,1,8]
        输出：18
        解释：选出数字 3, 6, 1 和 8，它们的和是 18（可被 3 整除的最大和）。

        示例 2：
        输入：nums = [4]
        输出：0
        解释：4 不能被 3 整除，所以无法选出数字，返回 0。

        示例 3：
        输入：nums = [1,2,3,4,4]
        输出：12
        解释：选出数字 1, 3, 4 以及 4，它们的和是 12（可被 3 整除的最大和）。

        提示：
        1 <= nums.length <= 4 * 10^4
        1 <= nums[i] <= 10^4

        思路：动态规划，dp[i][0]表示num[0...i]中能整除3的最大和
        dp[i][1]表示num[0...i]中余1的最大之和。
        dp[i][0]=dp[i-1][0]和dp[i-1][0]+num[i]中较大的一个    num[i]%3==0
        dp[i][0]=dp[i-1][0]和dp[i-1][2]+num[i]中较大的一个    num[i]%3==1
        dp[i][0]=dp[i-1][0]和dp[i-1][1]+num[i]中较大的一个    num[i]%3==2
        其他的dp[i][1]和dp[i][2]也是一样的
        */
public class MaxSumDivThree {
    public static void main(String[] args) {
        new MaxSumDivThree().maxSumDivThree(new int[]{3,6,5,1,8});
    }

    public int maxSumDivThree(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len + 1][3];
        dp[0][1] = Integer.MIN_VALUE;
        dp[0][2] = Integer.MIN_VALUE;
        for (int i = 1; i <= nums.length; i++) {
            int temp = nums[i - 1];
            if(nums[i - 1] % 3 == 0){
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][0] + temp);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][1] + temp);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][2] + temp);
            }else if (nums[i - 1] % 3 == 1){
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] + temp);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + temp);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + temp);
            }else if(nums[i - 1] % 3 == 2){
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + temp);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2] + temp);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][0] + temp);
            }
        }
        return dp[len][0];
    }
}
