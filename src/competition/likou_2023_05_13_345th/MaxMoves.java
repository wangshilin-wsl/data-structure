package competition.likou_2023_05_13_345th;

/*力扣：6433. 矩阵中移动的最大次数 显示英文描述
        通过的用户数944
        尝试过的用户数1282
        用户总通过次数976
        用户总提交次数2047
        题目难度Medium
        给你一个下标从 0 开始、大小为 m x n 的矩阵 grid ，矩阵由若干 正 整数组成。

        你可以从矩阵第一列中的 任一 单元格出发，按以下方式遍历 grid ：

        从单元格 (row, col) 可以移动到 (row - 1, col + 1)、(row, col + 1) 和 (row + 1, col + 1) 三个单元格中任一满足值 严格 大于当前单元格的单元格。
        返回你在矩阵中能够 移动 的 最大 次数。



        示例 1：


        输入：grid = [[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]
        输出：3
        解释：可以从单元格 (0, 0) 开始并且按下面的路径移动：
        - (0, 0) -> (0, 1).
        - (0, 1) -> (1, 2).
        - (1, 2) -> (2, 3).
        可以证明这是能够移动的最大次数。
        示例 2：


        输入：grid = [[3,2,4],[2,1,9],[1,1,7]]
        输出：0
        解释：从第一列的任一单元格开始都无法移动。


        提示：

        m == grid.length
        n == grid[i].length
        2 <= m, n <= 1000
        4 <= m * n <= 105
        1 <= grid[i][j] <= 106*/
public class MaxMoves {
    public static void main(String[] args) {
        System.out.println(new MaxMoves().maxMoves(new int[][]{{2,4,3,5},{5,4,9,3},{3,4,2,11}, {10,9,13,15}}));
    }
    public int maxMoves(int[][] grid) {
        //动态规划，注意题目，能走的步数更多
        //dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j-1],dp[i+1][j-1])并且前提是上一个位置可以到
        int len = grid.length, width = grid[0].length, max = 0;
        int[][] dp = new int[len][width];
        for (int j = 1; j < width; j++) {
            for (int i = 0; i < len; i++) {
                int temp = 0;
                //严格递增，并且上一个是可以到达的（需要区分j=1的情况，第一列）
                if (grid[i][j] > grid[i][j - 1] && (dp[i][j - 1] != 0 || j == 1)) {
                    temp = Math.max(dp[i][j - 1] + 1, temp);
                }
                if (i - 1 >= 0 && grid[i][j] > grid[i - 1][j - 1] && (dp[i - 1][j - 1] != 0 || j == 1)) {
                    temp = Math.max(dp[i - 1][j - 1] + 1, temp);
                }
                if (i + 1 < len && grid[i][j] > grid[i + 1][j - 1] && (dp[i + 1][j - 1] != 0 || j == 1)) {
                    temp = Math.max(dp[i + 1][j - 1] + 1, temp);
                }
                dp[i][j] = temp;
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
}
