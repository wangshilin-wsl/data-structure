package competition.likou_2023_03_19;

/*力扣：6322. 检查骑士巡视方案 显示英文描述
        骑士在一张 n x n 的棋盘上巡视。在有效的巡视方案中，骑士会从棋盘的 左上角 出发，并且访问棋盘上的每个格子 恰好一次 。

        给你一个 n x n 的整数矩阵 grid ，由范围 [0, n * n - 1] 内的不同整数组成，其中 grid[row][col] 表示单元格 (row, col) 是骑士访问的第 grid[row][col] 个单元格。骑士的行动是从下标 0 开始的。

        如果 grid 表示了骑士的有效巡视方案，返回 true；否则返回 false。

        注意，骑士行动时可以垂直移动两个格子且水平移动一个格子，或水平移动两个格子且垂直移动一个格子。下图展示了骑士从某个格子出发可能的八种行动路线。




        示例 1：


        输入：grid = [[0,11,16,5,20],[17,4,19,10,15],[12,1,8,21,6],[3,18,23,14,9],[24,13,2,7,22]]
        输出：true
        解释：grid 如上图所示，可以证明这是一个有效的巡视方案。
        示例 2：


        输入：grid = [[0,3,6],[5,8,1],[2,7,4]]
        输出：false
        解释：grid 如上图所示，考虑到骑士第 7 次行动后的位置，第 8 次行动是无效的。


        提示：

        n == grid.length == grid[i].length
        3 <= n <= 7
        0 <= grid[row][col] < n * n
        grid 中的所有整数 互不相同*/
public class CheckValidGrid {
    public static void main(String[] args) {
        System.out.println(new CheckValidGrid().checkValidGrid(new int[][]{{0,3,6},{5,8,1},{2,7,4}}));
    }

    public boolean checkValidGrid(int[][] grid) {
        int[][] index = new int[grid.length * grid.length][];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                index[grid[i][j]] = new int[] { i, j };
            }
        }
        for (int i = 1; i < index.length; i++) {
            if (Math.abs(index[i][0] - index[i - 1][0]) * Math.abs(index[i][1] - index[i - 1][1]) != 2) {
                return false;
            }
        }
        return grid[0][0] == 0;
    }

    //第一种，使用递归，可以优化为，先寻找所有数字的下标记录下来，在看他们x,y乘积是否为2
    public boolean checkValidGrid1(int[][] grid) {
        return check(grid, 0, 0, 0);
    }

    public boolean check(int[][] grid, int i, int j, int index) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != index) {
            return false;
        }
        if (index == grid.length * grid[0].length - 1) {
            return grid[i][j] == index;
        }
        return check(grid, i + 2, j + 1, index + 1) || check(grid, i + 2, j - 1, index + 1) ||
        check(grid, i - 2, j + 1, index + 1) || check(grid, i - 2, j - 1, index + 1) ||
        check(grid, i + 1, j + 2, index + 1) || check(grid, i + 1, j - 2, index + 1) ||
        check(grid, i - 1, j + 2, index + 1) || check(grid, i - 1, j - 2, index + 1);
    }
}
