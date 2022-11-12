package dynamic_programming;
/*790. 多米诺和托米诺平铺
        有两种形状的瓷砖：一种是 2 x 1 的多米诺形，另一种是形如 "L" 的托米诺形。两种形状都可以旋转。

        给定整数 n ，返回可以平铺 2 x n 的面板的方法的数量。返回对 109 + 7 取模 的值。
        平铺指的是每个正方形都必须有瓷砖覆盖。两个平铺不同，当且仅当面板上有四个方向上的相邻单元中的两个，使得恰好有一个平铺有一个瓷砖占据两个正方形。

        示例 1:
        输入: n = 3
        输出: 5
        解释: 五种不同的方法如上所示。

        示例 2:
        输入: n = 1
        输出: 1

        提示：
        1 <= n <= 1000
        思路：
        考虑这么一种平铺的方式：在第 i列前面的正方形都被瓷砖覆盖，
        在第 i 列后面的正方形都没有被瓷砖覆盖（i从 1 开始计数）。
        那么第 i 列的正方形有四种被覆盖的情况：

一个正方形都没有被覆盖，记为状态 0；
只有上方的正方形被覆盖，记为状态 1；
只有下方的正方形被覆盖，记为状态 2；
上下两个正方形都被覆盖，记为状态 3。

使用{dp}[i][s] 表示平铺到第 i 列时，各个状态 s 对应的平铺方法数量。考虑第i−1 列和第 i 列正方形，
它们之间的状态转移如下图（红色条表示新铺的瓷砖）：
链接：https://leetcode.cn/problems/domino-and-tromino-tiling/solution/duo-mi-nuo-he-tuo-mi-nuo-ping-pu-by-leet-7n0j/
*/
public class NumTillings {
    public static void main(String[] args) {
        System.out.println(new NumTillings().numTilings(5));
    }

    public int numTilings(int n) {
        int mod = (int)(1e9 + 7);
        //dp[i][0]表示第i列没有正方形被覆盖
        //dp[i][1]表示第i列只有上面一个正方形被覆盖
        //dp[i][2]表示第i列只有下面一个正方形被覆盖
        //dp[i][3]表示第i列上下两个正方形都被覆盖了
        //dp[i][0]=dp[i-1][3]
        //dp[i][1]=dp[i-1][0] + dp[i-1][2]
        //dp[i][2]=dp[i-1][0] + dp[i-1][1]
        //dp[i][3]=dp[i-1][0] + dp[i-1][1] + dp[i-1][2] + dp[i-1][3]
        int[][] dp = new int[n + 1][4];
        dp[0][3] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i][0]=dp[i-1][3];
            dp[i][1]=(dp[i-1][0] + dp[i-1][2]) % mod;
            dp[i][2]=(dp[i-1][0] + dp[i-1][1]) % mod;
            dp[i][3]=((dp[i-1][0] + dp[i-1][1]) % mod + (dp[i-1][2] + dp[i-1][3]) % mod) % mod;
        }
        return dp[n][3];
    }
}
