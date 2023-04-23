package graph.bfs;

import java.util.*;

/*力扣：1042. 不邻接植花
        有 n 个花园，按从 1 到 n 标记。另有数组 paths ，其中 paths[i] = [xi, yi] 描述了花园 xi 到花园 yi 的双向路径。在每个花园中，你打算种下四种花之一。

        另外，所有花园 最多 有 3 条路径可以进入或离开.

        你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。

        以数组形式返回 任一 可行的方案作为答案 answer，其中 answer[i] 为在第 (i+1) 个花园中种植的花的种类。花的种类用  1、2、3、4 表示。保证存在答案。



        示例 1：

        输入：n = 3, paths = [[1,2],[2,3],[3,1]]
        输出：[1,2,3]
        解释：
        花园 1 和 2 花的种类不同。
        花园 2 和 3 花的种类不同。
        花园 3 和 1 花的种类不同。
        因此，[1,2,3] 是一个满足题意的答案。其他满足题意的答案有 [1,2,4]、[1,4,2] 和 [3,2,1]
        示例 2：

        输入：n = 4, paths = [[1,2],[3,4]]
        输出：[1,2,1,2]
        示例 3：

        输入：n = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
        输出：[1,2,3,4]


        提示：

        1 <= n <= 104
        0 <= paths.length <= 2 * 104
        paths[i].length == 2
        1 <= xi, yi <= n
        xi != yi
        每个花园 最多 有 3 条路径可以进入或离开*/
public class GardenNoAdj {
    Map<Integer, List<Integer>> path = new HashMap<>();
    int len;
    int[] col;
    boolean flag = false;

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new GardenNoAdj().gardenNoAdj(3, new int[][]{{1, 2}, {2, 3}, {3, 1}})));
    }

    public int[] gardenNoAdj(int n, int[][] paths) {
       //邻接表存储图
        List<Integer>[] path = new List[n];
        for (int i = 0; i < n; i++) {
            path[i] = new ArrayList<>();
        }
        for (int[] ints : paths) {
            path[ints[0] - 1].add(ints[1] - 1);
            path[ints[1] - 1].add(ints[0] - 1);
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            //所有关联的花园
            List<Integer> list = path[i];
            //从花园中找到没有使用的颜色
            boolean[] col = new boolean[5];
            for (Integer index : list) {
                col[res[index]] = true;
            }
            //找到未使用的颜色
            for (int j = 1; j < 5; j++) {
                if (!col[j]) {
                    res[i] = j;
                    break;
                }
            }
        }
        return res;
    }

    public int[] gardenNoAdj1(int n, int[][] paths) {
       len = n;
       col = new int[len];
        for (int[] ints : paths) {
            List<Integer> list = path.getOrDefault(ints[0], new ArrayList<>());
            list.add(ints[1]);
            path.put(ints[0], list);

            List<Integer> list1 = path.getOrDefault(ints[1], new ArrayList<>());
            list1.add(ints[0]);
            path.put(ints[1], list1);
        }
        bfs(0);
        return col;
    }
    void bfs(int n) {
        if (n < len) {
            for (int i = 1; i < 5; i++) {
                if (flag) {
                    return;
                }
                col[n] = i;
                if (judge(n)) {
                    bfs(n + 1);
                }
            }
        } else {
            flag = true;
        }
    }
    boolean judge(int n) {
        List<Integer> list = path.getOrDefault(n + 1, new ArrayList<>());
        for (Integer integer : list) {
            if (col[n] == col[integer - 1]){
                return false;
            }
        }
        return true;
    }
}
