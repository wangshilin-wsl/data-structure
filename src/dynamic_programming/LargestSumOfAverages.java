package dynamic_programming;

/*力扣: 813. 最大平均值和的分组
        给定数组 nums 和一个整数 k 。我们将给定的数组 nums 分成 最多 k 个相邻的非空子数组 。 分数 由每个子数组内的平均值的总和构成。

        注意我们必须使用 nums 数组中的每一个数进行分组，并且分数不一定需要是整数。

        返回我们所能得到的最大 分数 是多少。答案误差在 10-6 内被视为是正确的。



        示例 1:

        输入: nums = [9,1,2,3,9], k = 3
        输出: 20.00000
        解释:
        nums 的最优分组是[9], [1, 2, 3], [9]. 得到的分数是 9 + (1 + 2 + 3) / 3 + 9 = 20.
        我们也可以把 nums 分成[9, 1], [2], [3, 9].
        这样的分组得到的分数为 5 + 2 + 6 = 13, 但不是最大值.
        示例 2:

        输入: nums = [1,2,3,4,5,6,7], k = 4
        输出: 20.50000


        提示:

        1 <= nums.length <= 100
        1 <= nums[i] <= 104
        1 <= k <= nums.length
        思路：前缀和+dp
        命题：平均值和最大的分组的子数组数目必定是 kk。
        为了方便计算子数组的平均值，我们使用一个数组 prefix 来保存数组nums 的前缀和。
        我们使用 dp[i][j] 表示 nums 在区间 [0,i−1] 被切分成 j 个子数组的最大平均值和，
        显然 i≥j，计算分两种情况讨论：

当 j=1 时，dp[i][j] 是对应区间 [0,i−1] 的平均值；

当 j > 1时，我们将可以将区间 [0, i - 1] 分成 [0,x−1] 和 [x,i−1] 两个部分，其中 x≥j−1，
那么dp[i][j] 等于所有这些合法的切分方式的平均值和的最大值。

作者：LeetCode-Solution
链接：https://leetcode.cn/problems/largest-sum-of-averages/solution/zui-da-ping-jun-zhi-he-de-fen-zu-by-leet-09xt/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        */
public class LargestSumOfAverages {
    public static void main(String[] args) {
        System.out.println(new LargestSumOfAverages().largestSumOfAverages(new int[]{9,1,2,3,9}, 3));
    }

    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        //前缀和
        double[] prefix = new double[n + 1];
        //dp[i][j]可以对空间进行优化
        double[][] dp = new double[n + 1][k + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
            dp[i + 1][1] = prefix[i + 1] / (i + 1);
        }

        for (int j = 2; j <= k; j++) {
            for (int i = j; i <= n; i++) {
                for (int x = j - 1; x < i; x++) {
                   dp[i][j] = Math.max(dp[i][j], dp[x][j - 1] + (prefix[i] - prefix[x]) / (i - x));
                }
            }
        }
        return dp[n][k];
    }
}
