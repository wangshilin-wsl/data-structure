package graph.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

/*力扣：1091. 二进制矩阵中的最短路径
        给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。

        二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求：

        路径途经的所有单元格的值都是 0 。
        路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
        畅通路径的长度 是该路径途经的单元格总数。



        示例 1：


        输入：grid = [[0,1],[1,0]]
        输出：2
        示例 2：


        输入：grid = [[0,0,0],[1,1,0],[1,1,0]]
        输出：4
        示例 3：

        输入：grid = [[1,0,0],[1,1,0],[1,1,0]]
        输出：-1


        提示：

        n == grid.length
        n == grid[i].length
        1 <= n <= 100
        grid[i][j] 为 0 或 1*/
public class ShortestPathBinaryMatrix {
    public static void main(String[] args) {
        System.out.println(new ShortestPathBinaryMatrix().shortestPathBinaryMatrix(new int[][]{{0, 1}, {1, 0}}));
    }
    public int shortestPathBinaryMatrix(int[][] grid) {
        //使用广度优先遍历
        if (grid[0][0] == 1) {
            return -1;
        }
        int n = grid.length;
        int[][] dis = new int[n][n];
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        boolean[][] used = new boolean[n][n];
        Queue<int[]> queue = new ArrayDeque<>();
        dis[0][0] = 1;
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int x = pos[0];
            int y = pos[1];
            //如果能到达[n-1,n-1]则直接返回，因为是广度优先遍历，而且也是第一次到所以他经历的路径也是最短的
            if (x == n - 1 && y == n - 1) {
                return dis[x][y];
            }
            for (int[] ints : dir) {
                int nextX = x + ints[0];
                int nextY = y + ints[1];
                //越界，单元格不为1
                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n || grid[nextX][nextY] == 1 || used[nextX][nextY]) {
                    continue;
                }
                used[nextX][nextY] = true;
                dis[nextX][nextY] = dis[x][y] + 1;
                queue.offer(new int[]{nextX, nextY});
            }

        }
        return -1;
    }
}
