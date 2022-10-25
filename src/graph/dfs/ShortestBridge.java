package graph.dfs;

import java.util.ArrayList;
import java.util.List;

/*力扣：934. 最短的桥
        给你一个大小为 n x n 的二元矩阵 grid ，其中 1 表示陆地，0 表示水域。
        岛 是由四面相连的 1 形成的一个最大组，即不会与非组内的任何其他 1 相连。
        grid 中 恰好存在两座岛 。
        你可以将任意数量的 0 变为 1 ，以使两座岛连接起来，变成 一座岛 。
        返回必须翻转的 0 的最小数目。

        示例 1：
        输入：grid = [[0,1],[1,0]]
        输出：1

        示例 2：
        输入：grid = [[0,1,0],[0,0,0],[0,0,1]]
        输出：2

        示例 3：
        输入：grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
        输出：1

        提示：
        n == grid.length == grid[i].length
        2 <= n <= 100
        grid[i][j] 为 0 或 1
        grid 中恰有两个岛
        思路：先dfs遍历将第一个岛的1都置为-1，
        随后以第一个岛也就是-1发散，寻找第一个1，
        也就是另外一个岛屿，如果遇到的是0也将其置为-1
        */
public class ShortestBridge {
    int[][] dir = {{1,0},{-1, 0},{0,1},{0,-1}};

    public static void main(String[] args) {
        System.out.println(new ShortestBridge().shortestBridge(new int[][]{{0, 1, 0}, {0, 0, 0}, {0, 0, 1}}));
    }

    public int shortestBridge(int[][] grid) {
        int n = grid.length;
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1){
                    dfs(grid, i, j);
                    flag = true;
                    break;
                }
            }
            if(flag){
                break;
            }
        }
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == -1){
                    list.add(new int[]{i,j});
                }
            }
        }
        int res = 0;
        while (true) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                for (int[] ints1 : dir) {
                    int x = list.get(i)[0] + ints1[0];
                    int y = list.get(i)[1] + ints1[1];
                    if(x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == -1){
                        continue;
                    }
                    if(grid[x][y] == 1){
                        return res;
                    }

                    if(grid[x][y] == 0){
                        list.add(new int[]{x,y});
                        grid[x][y] = -1;
                    }
                }
            }
            res++;
        }
    }
    void dfs(int[][] grid, int i, int j){
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != 1){
            return ;
        }
        grid[i][j] = -1;
        for (int k = 0; k < dir.length; k++) {
            dfs(grid, i + dir[k][0], j + dir[k][1]);
        }
    }
}
