package competition.tulinjiaoyu_2022_12_11;

/*力扣：6260. 矩阵查询可获得的最大分数 显示英文描述
        给你一个大小为 m x n 的整数矩阵 grid 和一个大小为 k 的数组 queries 。

        找出一个大小为 k 的数组 answer ，且满足对于每个整数 queres[i] ，你从矩阵 左上角 单元格开始，重复以下过程：

        如果 queries[i] 严格 大于你当前所处位置单元格，如果该单元格是第一次访问，则获得 1 分，并且你可以移动到所有 4 个方向（上、下、左、右）上任一 相邻 单元格。
        否则，你不能获得任何分，并且结束这一过程。
        在过程结束后，answer[i] 是你可以获得的最大分数。注意，对于每个查询，你可以访问同一个单元格 多次 。

        返回结果数组 answer 。



        示例 1：


        输入：grid = [[1,2,3],[2,5,7],[3,5,1]], queries = [5,6,2]
        输出：[5,8,1]
        解释：上图展示了每个查询中访问并获得分数的单元格。
        示例 2：


        输入：grid = [[5,2,1],[1,1,2]], queries = [3]
        输出：[0]
        解释：无法获得分数，因为左上角单元格的值大于等于 3 。


        提示：

        m == grid.length
        n == grid[i].length
        2 <= m, n <= 1000
        4 <= m * n <= 105
        k == queries.length
        1 <= k <= 104
        1 <= grid[i][j], queries[i] <= 106*/
public class MaxPoints {
    public static void main(String[] args) {
        int[] ints = new MaxPoints().maxPoints(new int[][]{{1, 2, 3}, {2, 5, 7}, {5, 6, 2}}, new int[]{5, 6, 2});
        for (int anInt : ints) {
            System.out.print(anInt + "  ");
        }
    }

    public int[] maxPoints(int[][] grid, int[] queries) {
        return null;
    }


    /**
     * @methodName maxPoints
     * @Author WSL
     * @Description  深度遍历，超时
     * @Date 12:26 2022/12/11
     * @Param grid
     * @return int[]
     **/
    public int[] maxPoints1(int[][] grid, int[] queries) {
        int n = queries.length;
        int[] answer = new int[n];
        boolean[][] flag;
        for (int i = 0; i < n; i++) {
            flag = new boolean[grid.length][grid[0].length];
            answer[i] = dfs(grid, flag, 0, 0, queries[i]);
        }
        return answer;
    }
    public int dfs(int[][] grid, boolean[][] flag, int x, int y, int target){
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || flag[x][y] || grid[x][y] >= target) {
            return 0;
        }
        flag[x][y] = true;
        return dfs(grid, flag, x + 1, y, target) + dfs(grid, flag, x - 1, y, target) +
                dfs(grid, flag, x, y - 1, target) + dfs(grid, flag, x, y + 1, target) + 1;
    }
}
