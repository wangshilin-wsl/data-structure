package dynamic_programming;

import java.util.HashMap;
import java.util.Map;

/*力扣：1027. 最长等差数列
        给你一个整数数组 nums，返回 nums 中最长等差子序列的长度。

        回想一下，nums 的子序列是一个列表 nums[i1], nums[i2], ..., nums[ik] ，且 0 <= i1 < i2 < ... < ik <= nums.length - 1。并且如果 seq[i+1] - seq[i]( 0 <= i < seq.length - 1) 的值都相同，那么序列 seq 是等差的。



        示例 1：

        输入：nums = [3,6,9,12]
        输出：4
        解释：
        整个数组是公差为 3 的等差数列。
        示例 2：

        输入：nums = [9,4,7,2,10]
        输出：3
        解释：
        最长的等差子序列是 [4,7,10]。
        示例 3：

        输入：nums = [20,1,15,3,10,5,8]
        输出：4
        解释：
        最长的等差子序列是 [20,15,10,5]。


        提示：

        2 <= nums.length <= 1000
        0 <= nums[i] <= 500*/
public class LongestArithSeqLength {
    public static void main(String[] args) {
        System.out.println(new LongestArithSeqLength().longestArithSeqLength(new int[]{20, 1, 15, 3, 10, 5, 8}));
    }
    public int longestArithSeqLength(int[] nums) {
        //参考：https://leetcode.cn/problems/longest-arithmetic-subsequence/solution/ji-yi-hua-sou-suo-di-tui-chang-shu-you-h-czvx/
        int n = nums.length, max = 0;
        //map数组，表示以i结尾，key为方差的序列最长value
        Map<Integer, Integer>[] dp = new Map[n];
        //初始化
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
        }
        for (int i = 1; i < n; i++) {
            //知道尾项和方差，从尾项开始往前寻找
            for (int j = i - 1; j >= 0; j--) {
                int d = nums[i] - nums[j];
                //在从i往前遍历的时候，如果遇到多个相同的数，根据贪心则选择离i最近的
                //所以后续遇到了直接不管，肯定要比上一个短
                if (!dp[i].containsKey(d)) {
                    //默认是1，因为j也算一个
                    dp[i].put(d, dp[j].getOrDefault(d, 1) + 1);
                    max = Math.max(max, dp[i].get(d));
                }
            }
        }
        return max;
    }
}
