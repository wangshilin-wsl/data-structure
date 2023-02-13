package graph.dfs;

/**
 * @author WSL
 * @version 1.0.0
 * @ClassName DieSimulator.java
 * @Description TODO
 * @createTime 2022年02月12日 13:10:00
 */
/*
力扣：1020. 飞地的数量
给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。

        一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。

        返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。

         

        示例 1：


        输入：grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
        输出：3
        解释：有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
        示例 2：


        输入：grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
        输出：0
        解释：所有 1 都在边界上或可以到达边界。
         

        提示：

        m == grid.length
        n == grid[i].length
        1 <= m, n <= 500
        grid[i][j] 的值为 0 或 1

        思路：
        陆地分为可以到达边界的和不可到达边界的陆地，四条边上的陆地都是可以到达边界的陆地，并且和其接壤的
        也都为可以到达边界的陆地，最后遍历所有找出不能到达边界的陆地。
        */

class Solution {
    int m,n;
    boolean[][] flag;
    int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    public int numEnclaves(int[][] grid) {
        int ret = 0;
        m = grid.length;
        n = grid[0].length;
        flag = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            hh(grid,i,0);
            hh(grid,i,n - 1);
        }

        for (int j = 0; j < n; j++) {
            hh(grid,0,j);
            hh(grid,m - 1,j);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == 1 && !flag[i][j]){
                    ret++;
                }
            }
        }
        return ret;
    }

    public void hh(int[][] grid, int x, int y){
        if(x < 0 || y < 0 || x >= m || y >= n ||
                flag[x][y] || grid[x][y] == 0){
            return ;
        }
        flag[x][y] = true;
        for (int i = 0; i < dir.length; i++) {
            hh(grid,x + dir[i][0],y + dir[i][1]);
        }
    }
}
