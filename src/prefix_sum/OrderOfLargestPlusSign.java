package prefix_sum;

import java.util.HashSet;
import java.util.Set;

/*力扣：764. 最大加号标志
        在一个 n x n 的矩阵 grid 中，除了在数组 mines 中给出的元素为 0，其他每个元素都为 1。
        mines[i] = [xi, yi]表示 grid[xi][yi] == 0
        返回  grid 中包含 1 的最大的 轴对齐 加号标志的阶数 。如果未找到加号标志，则返回 0 。
        一个 k 阶由 1 组成的 “轴对称”加号标志 具有中心网格 grid[r][c] == 1 ，
        以及4个从中心向上、向下、向左、向右延伸，长度为 k-1，由 1 组成的臂。注意，只有加号标志的所有网格要求为 1 ，别的网格可能为 0 也可能为 1 。

        示例 1：
        输入: n = 5, mines = [[4, 2]]
        输出: 2
        解释: 在上面的网格中，最大加号标志的阶只能是2。一个标志已在图中标出。

        示例 2：
        输入: n = 1, mines = [[0, 0]]
        输出: 0
        解释: 没有加号标志，返回 0 。

        提示：
        1 <= n <= 500
        1 <= mines.length <= 5000
        0 <= xi, yi < n
每一对 (xi, yi) 都 不重复*/
public class OrderOfLargestPlusSign {
    int[][] dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

    public static void main(String[] args) {
        System.out.println(new OrderOfLargestPlusSign().orderOfLargestPlusSign(5, new int[][]{{4, 2}}));
    }

    /**
     * @methodName orderOfLargestPlusSign
     * @Author WSL
     * @Description  使用4个数组记录4个方向的连续1的个数（前缀和），最后寻找每个位置中4个方向上最小值就是改+号的阶。后循环整个矩阵来找到最大的
     * @Date 21:24 2022/11/9
     * @Param
     * @return int
     **/
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        //记录每个方向上连续1的个数
        int[][] upper = new int[n + 2][n + 2], down = new int[n + 2][n + 2], left = new int[n + 2][n + 2], right = new int[n + 2][n + 2];
        //使用一维数组来表示为0的位置
        Set<Integer> set = new HashSet<>();
        for (int[] mine : mines) {
            set.add(mine[0] * n + mine[1]);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int x1 = i - 1, x2 = n - i, y1 = j - 1, y2 = n - j, i1 = n - i + 1, j1 = n - j + 1;
                //方向向上，则从小到大开始遍历
                int up = x1 * n + y1;
                if (!set.contains(up)) {
                    //该位置为1
                    upper[i][j] = upper[i - 1][j] + 1;
                    left[i][j] = left[i][j - 1] + 1;
                }
                int dow = x2 * n + y2;
                if (!set.contains(dow)) {
                    //该位置为1
                    down[i1][j1] = down[i1 + 1][j1] + 1;
                    right[i1][j1] = right[i1][j1 + 1] + 1;
                }
            }
        }
        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                max = Math.max(max, Math.min(Math.min(upper[i][j], down[i][j]), Math.min(left[i][j], right[i][j])));
            }
        }
        return max;
    }

    /**
     * @methodName orderOfLargestPlusSign1
     * @Author WSL
     * @Description 暴力法，循环整个矩形
     * @Date 21:17 2022/11/9
     * @Param null
     * @return
     **/
    public int orderOfLargestPlusSign1(int n, int[][] mines) {
        //查看0组成+的最大阶数
        int[][] grid = new int[n][n];
        for (int i = 0; i < mines.length; i++) {
            grid[mines[i][0]][mines[i][1]] = 1;
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    continue;
                }
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < dir.length; k++) {
                    min = Math.min(order(grid, i + dir[k][0], j + dir[k][1], k), min);
                }
                max = Math.max(max, min + 1);
            }
        }
        return max;
    }
    public int order(int[][] grid, int x, int y, int d){
        if (x < 0 || x >= grid.length || y < 0 || y >= grid.length || grid[x][y] == 1) {
            return 0;
        }
        return 1 + order(grid, x + dir[d][0], y + dir[d][1], d);
    }
}
