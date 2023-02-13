package dynamic_programming;
/*力扣：1223. 掷骰子模拟
        有一个骰子模拟器会每次投掷的时候生成一个 1 到 6 的随机数。

        不过我们在使用它时有个约束，就是使得投掷骰子时，连续 掷出数字 i 的次数不能超过 rollMax[i]（i 从 1 开始编号）。

        现在，给你一个整数数组 rollMax 和一个整数 n，请你来计算掷 n 次骰子可得到的不同点数序列的数量。

        假如两个序列中至少存在一个元素不同，就认为这两个序列是不同的。由于答案可能很大，所以请返回 模 10^9 + 7 之后的结果。



        示例 1：

        输入：n = 2, rollMax = [1,1,2,2,2,3]
        输出：34
        解释：我们掷 2 次骰子，如果没有约束的话，共有 6 * 6 = 36 种可能的组合。但是根据 rollMax 数组，数字 1 和 2 最多连续出现一次，所以不会出现序列 (1,1) 和 (2,2)。因此，最终答案是 36-2 = 34。
        示例 2：

        输入：n = 2, rollMax = [1,1,1,1,1,1]
        输出：30
        示例 3：

        输入：n = 3, rollMax = [1,1,1,2,2,3]
        输出：181


        提示：

        1 <= n <= 5000
        rollMax.length == 6
        1 <= rollMax[i] <= 15*/
class DieSimulator {
    public int dieSimulator(int n, int[] rollMax) {
        //dp[i][j][k]已经掷骰子i次，并且第i次为j,并且j已经连续出现了k次
        //如果第i次的点数==第i - 1次的点数dp[i][j][k + 1] = dp[i][j][k + 1] + dp[i - 1][j][k](j可能为1~6，需要循环)
        //如果第i次的点数!=第i - 1次的点数dp[i][j][k] = dp[i][j][k] + dp[i - 1][j][k](j可能为1~6，需要循环)
        int[][][] dp = new int[n + 1][7][16];
        int mod = (int)1e9 + 7;
        for (int j = 1; j <= 6; j++) {
            dp[1][j][1] = 1;
        }

        //i表示掷骰子的次数1~n
        for (int i = 2; i < n + 1; i++) {
            //j表示i - 1次掷骰子的点数
            for (int j = 1; j < 7; j++) {
                //k表示连续出现的次数
                for (int k = 1; k < rollMax[j - 1] + 1; k++) {
                    //p表示i次掷骰子的点数
                    for (int p = 1; p < 7; p++) {
                        if (p != j) {
                            dp[i][p][1] = (dp[i][p][1] + dp[i - 1][j][k])  % mod;
                        } else if (k + 1 <= rollMax[j - 1]) {
                            dp[i][p][k + 1] = (dp[i][p][k + 1] + dp[i - 1][j][k]) % mod;
                        }
                    }
                }
            }
        }

        int res = 0;
        for (int j = 1; j < 7; j++) {
            for (int k = 1; k < rollMax[j - 1] + 1; k++) {
                res = (res + dp[n][j][k]) % mod;
            }
        }
        return res;
    }
}