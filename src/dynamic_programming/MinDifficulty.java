package dynamic_programming;

/*力扣：1335. 工作计划的最低难度
        你需要制定一份 d 天的工作计划表。工作之间存在依赖，要想执行第 i 项工作，你必须完成全部 j 项工作（ 0 <= j < i）。

        你每天 至少 需要完成一项任务。工作计划的总难度是这 d 天每一天的难度之和，而一天的工作难度是当天应该完成工作的最大难度。

        给你一个整数数组 jobDifficulty 和一个整数 d，分别代表工作难度和需要计划的天数。第 i 项工作的难度是 jobDifficulty[i]。

        返回整个工作计划的 最小难度 。如果无法制定工作计划，则返回 -1 。



        示例 1：



        输入：jobDifficulty = [6,5,4,3,2,1], d = 2
        输出：7
        解释：第一天，您可以完成前 5 项工作，总难度 = 6.
        第二天，您可以完成最后一项工作，总难度 = 1.
        计划表的难度 = 6 + 1 = 7
        示例 2：

        输入：jobDifficulty = [9,9,9], d = 4
        输出：-1
        解释：就算你每天完成一项工作，仍然有一天是空闲的，你无法制定一份能够满足既定工作时间的计划表。
        示例 3：

        输入：jobDifficulty = [1,1,1], d = 3
        输出：3
        解释：工作计划为每天一项工作，总难度为 3 。
        示例 4：

        输入：jobDifficulty = [7,1,7,1,7,1], d = 3
        输出：15
        示例 5：

        输入：jobDifficulty = [11,111,22,222,33,333,44,444], d = 6
        输出：843


        提示：

        1 <= jobDifficulty.length <= 300
        0 <= jobDifficulty[i] <= 1000
        1 <= d <= 10*/
public class MinDifficulty {
    public static void main(String[] args) {
        System.out.println(new MinDifficulty().minDifficulty(new int[]{11, 111, 22, 222, 33, 333, 44, 444}, 6));
    }
    //递归优化暴力法，超时
    public int minDifficulty2(int[] jobDifficulty, int d) {
        //其实就是在jobDifficulty，分成d个子数组，保证其最大值的和最小
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }
        return comDiff2(jobDifficulty, d, 0);
    }
    public int comDiff2(int[] jobDifficulty, int d, int index) {
        //相对于上一次暴力法优化
        //1、d为1的时候遍历后面的就行
        //2、需要保证后面的肯定够d分
        int n = jobDifficulty.length, min = Integer.MAX_VALUE, maxDiff = 0;
        //优化1
        if (d == 1) {
            int max = 0;
            for (int i = index; i < jobDifficulty.length; i++) {
                max = Math.max(max, jobDifficulty[i]);
            }
            return max;
        }
        //i<n-d+1是优化2
        //保证[i+1,n)是最起码有d-1个,所以i<n-d+1
        for (int i = index; i < n - d + 1; i++) {
            maxDiff = Math.max(maxDiff, jobDifficulty[i]);
            min = Math.min(min, comDiff2(jobDifficulty, d - 1, i + 1) + maxDiff);
        }
        return min;
    }


    //记忆搜索，方法不对，还是超时
    public int minDifficulty1(int[] jobDifficulty, int d) {
        //其实就是在jobDifficulty，分成d个子数组，保证其最大值的和最小
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }
        int[][] caching = new int[n][d + 1];
        return comDiff1(jobDifficulty, d, 0, caching);
    }
    public int comDiff1(int[] jobDifficulty, int d, int index, int[][] caching) {
        //相对于上一次暴力法优化
        //1、d为1的时候遍历后面的就行
        //2、需要保证后面的肯定够d分
        int n = jobDifficulty.length, min = Integer.MAX_VALUE, maxDiff = 0;
        if (caching[index][d] != 0) {
            return caching[index][d];
        }
        //优化1
        if (d == 1) {
            int max = 0;
            for (int i = index; i < jobDifficulty.length; i++) {
                max = Math.max(max, jobDifficulty[i]);
            }
            return max;
        }
        //i<n-d+1是优化2
        //保证[i+1,n)是最起码有d-1个,所以i<n-d+1
        for (int i = index; i < n - d + 1; i++) {
            maxDiff = Math.max(maxDiff, jobDifficulty[i]);
            min = Math.min(min, comDiff1(jobDifficulty, d - 1, i + 1, caching) + maxDiff);
        }
        caching[index][d] = min;
        return min;
    }

    //动态规划，初始化边界
    public int minDifficulty0(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length, max = 0, min = Integer.MAX_VALUE;
        if (n < d) {
            return -1;
        }
        //dp[i][j]表示jobDifficulty从索引i开始，分成j(j[0, d])段的最小值
        //dp[i][j]=min(dp[k][j - 1]+jobDifficulty[i,k]的最大值)其中k范围[i+1,n]
        //i依赖k，所以i从大到小，j依赖j-1，所以j从小到大
        int[][] dp = new int[n + 1][d + 1];
        for (int i = n - 1; i >= 0; i--) {
            max = Math.max(max, jobDifficulty[i]);
            dp[i][1] = max;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 2; j <= d; j++) {
                max = 0;
                min = Integer.MAX_VALUE;
                for (int k = i; k < n - j + 1; k++) {
                    max = Math.max(max, jobDifficulty[k]);
                    min = Math.min(min, dp[k + 1][j - 1] + max);
                }
                dp[i][j] = min;
            }
        }
        return dp[0][d];
    }

    //不初始化边界
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }
        //dp[i][j]表示jobDifficulty从索引i开始，分成j(j[0, d])段的最小值
        //dp[i][j]=min(dp[k][j - 1]+jobDifficulty[i,k]的最大值)其中k范围[i+1,n]
        //i依赖k，所以i从大到小，j依赖j-1，所以j从小到大
        int[][] dp = new int[n + 1][d + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = -1;
        }
        dp[n][0] = 0;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= d; j++) {
                int max = 0, min = Integer.MAX_VALUE;
                for (int k = i; k < n - j + 1; k++) {
                    max = Math.max(max, jobDifficulty[k]);
                    if (dp[k + 1][j - 1] != -1) {
                        min = Math.min(min, dp[k + 1][j - 1] + max);
                    }
                }
                dp[i][j] = min == Integer.MAX_VALUE ? -1 : min;
            }
        }
        return dp[0][d];
    }
}
