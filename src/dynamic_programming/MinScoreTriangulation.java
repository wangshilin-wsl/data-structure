package dynamic_programming;

import java.util.HashMap;
import java.util.Map;

/*力扣：1039. 多边形三角剖分的最低得分
        你有一个凸的 n 边形，其每个顶点都有一个整数值。给定一个整数数组 values ，其中 values[i] 是第 i 个顶点的值（即 顺时针顺序 ）。

        假设将多边形 剖分 为 n - 2 个三角形。对于每个三角形，该三角形的值是顶点标记的乘积，三角剖分的分数是进行三角剖分后所有 n - 2 个三角形的值之和。

        返回 多边形进行三角剖分后可以得到的最低分 。


        示例 1：



        输入：values = [1,2,3]
        输出：6
        解释：多边形已经三角化，唯一三角形的分数为 6。
        示例 2：



        输入：values = [3,7,4,5]
        输出：144
        解释：有两种三角剖分，可能得分分别为：3*7*5 + 4*5*7 = 245，或 3*4*5 + 3*4*7 = 144。最低分数为 144。
        示例 3：



        输入：values = [1,3,1,4,1,5]
        输出：13
        解释：最低分数三角剖分的得分情况为 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13。


        提示：

        n == values.length
        3 <= n <= 50
        1 <= values[i] <= 100*/
public class MinScoreTriangulation {
    //思路：动态规划，dp[i][j]为由定点i到j组成多边形的最小值，那么答案就是dp[0][n - 1]
    //当i + 2 > j则表示i~j不足3个顶点，则最小为0
    //当i+2==j则表示正好是一个三角形，最小为values[i] * values[i+1] * values[i+2]
    //其他情况，则在(i, j)中找到k,则dp[i][j]=min(dp[i][k], dp[k][j], values[i] * values[j] * values[k]);
    //对于i,j可以用一个map来装结果，记忆搜索
    //https://leetcode.cn/problems/minimum-score-triangulation-of-polygon/solution/duo-bian-xing-san-jiao-pou-fen-de-zui-di-weqg/
    int[] values;
    int n;
    Map<Integer, Integer> result = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(new MinScoreTriangulation().minScoreTriangulation1(new int[]{1,3,1,4,1,5}));
    }

    //思路：动态规划，dp[i][j]为由定点i到j组成多边形的最小值，那么答案就是dp[0][n - 1]
    //当i + 2 > j则表示i~j不足3个顶点，则最小为0
    //当i+2==j则表示正好是一个三角形，最小为values[i] * values[i+1] * values[i+2]
    //其他情况，则在(i, j)中找到k,则dp[i][j]=min(dp[i][k], dp[k][j], values[i] * values[j] * values[k]);
    //使用常规的循环方法
    //由dp[i][j]=min(dp[i][k], dp[k][j], values[i] * values[j] * values[k]); dp[i][j]需要dp[i][k]和dp[k][j],k>i所以i需要逆序
    //当i确定的情况，dp[i][j]=min(dp[i][k], dp[k][j], values[i] * values[j] * values[k]); dp[i][j]需要dp[i][k]和dp[k][j]，j >k 所以需要j正序
    //由于j > k ，f[i][j]要从要从f[i][k]转移，需要先计算出f[i][k],所有j需要正序
    //https://leetcode.cn/problems/minimum-score-triangulation-of-polygon/solution/shi-pin-jiao-ni-yi-bu-bu-si-kao-dong-tai-aty6/
    public int minScoreTriangulation1(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        for (int i = n - 3; i >= 0; --i){
            for (int j = i + 2; j < n; ++j) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; ++k) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + values[i] * values[j] * values[k]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public int minScoreTriangulation(int[] values) {
        this.values = values;
        this.n = values.length;
        return dp(0, n - 1);
    }
    public int dp(int i, int j) {
        if (i + 2 > j) {
            //不足以组成三角形
            return 0;
        }
        if (i + 2 == j) {
            return values[i] * values[i + 1] * values[i + 2];
        }
        int key = i * n + j;
        if (!result.containsKey(key)) {
            int min = Integer.MAX_VALUE;
            //遍历i,j中间的点,是划分的所有三角形的和
            for (int k = i + 1; k < j; k++) {
                min = Math.min(min, dp(i, k) + dp(k, j) + values[i] * values[k] * values[j]);
            }
            result.put(key, min);
        }
        return result.get(key);
    }
}
