package dynamic_programming;
/*
        力扣：剑指offer.63

        假设把某股票的价格按照时间先后顺序存储在数组中，
        请问买卖该股票一次可能获得的最大利润是多少？

        示例 1:
        输入: [7,1,5,3,6,4]
        输出: 5
        解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，
        最大利润 = 6-1 = 5 。
        注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。

        示例 2:
        输入: [7,6,4,3,1]
        输出: 0
        解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。

        限制：
        0 <= 数组长度 <= 10^5

        分析：
        解题思路：
设共有 nn天，第 aa天买，第 b 天卖，则需保证 a <b ；可推出交易方案数共有：
(n - 1) + (n - 2) +...... + 2 + 1 = n(n - 1) / 2
(n−1)+(n−2)+⋯+2+1=n(n−1)/2

因此，暴力法的时间复杂度为 O(n^2)O(n
2
 ) 。考虑使用动态规划降低时间复杂度，以下按照流程解题。
动态规划解析：
状态定义： 设动态规划列表 dpdp ，dp[i]dp[i] 代表以 prices[i]prices[i] 为结尾的子数组的最大利润（以下简称为 前 ii 日的最大利润 ）。
转移方程： 由于题目限定 “买卖该股票一次” ，因此前 ii 日最大利润 dp[i]dp[i] 等于前 i - 1i−1 日最大利润 dp[i-1]dp[i−1] 和第 ii 日卖出的最大利润中的最大值。
前 i 日最大利润 = \max(前 (i-1) 日最大利润, 第 i 日价格 - 前 i 日最低价格)
前i日最大利润=max(前(i−1)日最大利润,第i日价格−前i日最低价格)

dp[i] = \max(dp[i - 1], prices[i] - \min(prices[0:i]))
dp[i]=max(dp[i−1],prices[i]−min(prices[0:i]))

初始状态： dp[0] = 0dp[0]=0 ，即首日利润为 00 ；
返回值： dp[n - 1]dp[n−1] ，其中 nn 为 dp 列表长度。

*/

public class MaxProfit {
    public static void main(String[] args) {
        System.out.println(new MaxProfit().maxProfit(new int[]{7,1,5,3,6,4}));
    }
    public int maxProfit(int[] prices) {
        if(prices.length==0) return 0;
       int[] dp=new int[prices.length];//dp[i]表示前i项的最大利润
        int min=prices[0];
        dp[0]=0;
        for(int i=1;i<prices.length;i++){
            dp[i]=Math.max(dp[i-1],prices[i]-min);//转移方程

            min=Math.min(min,prices[i]);//记录前i项的最小值
        }
        return dp[prices.length-1];
    }
}
