package graph.dfs;

import java.util.ArrayList;
import java.util.List;
/*力扣：886. 可能的二分法
        给定一组 n 人（编号为 1, 2, ..., n）， 我们想把每个人分进任意大小的两组。
        每个人都可能不喜欢其他人，那么他们不应该属于同一组。
        给定整数 n 和数组 dislikes ，其中 dislikes[i] = [ai, bi] ，
        表示不允许将编号为 ai 和  bi的人归入同一组。当可以用这种方法将所有人分进两组时，返
        回 true；否则返回 false。

        示例 1：
        输入：n = 4, dislikes = [[1,2],[1,3],[2,4]]
        输出：true
        解释：group1 [1,4], group2 [2,3]

        示例 2：
        输入：n = 3, dislikes = [[1,2],[1,3],[2,3]]
        输出：false

        示例 3：
        输入：n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
        输出：false

        提示：
        1 <= n <= 2000
        0 <= dislikes.length <= 104
        dislikes[i].length == 2
        1 <= dislikes[i][j] <= n
        a[i] < b[i]
        dislikes 中每一组都 不同
        思路：染色法，将1~n如果可以染成两个不同的色（1,2表示）则返回true,
        将所有不容的关系放到list数组中，g[i]表示和i不和的所有人。
*/
public class PossibleBipartition {
    public static void main(String[] args) {
        new PossibleBipartition().possibleBipartition(10, new int[][]{{1,2},{3,4},{5,6},{6,7},{8,9},{7,8}});
    }
    public boolean possibleBipartition(int n, int[][] dislikes) {
        int[] rel = new int[n + 1];
        List<Integer>[] g = new List[n + 1];
        for (int i = 0; i < g.length; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] ints : dislikes) {
            g[ints[0]].add(ints[1]);
            g[ints[1]].add(ints[0]);
        }

        for (int i = 1; i <= n; i++) {
            if(rel[i] == 0 && !dfs(i, 1, g, rel)){
                return false;
            }
        }

        return true;
    }
    boolean dfs(int currNode, int value, List<Integer>[] g, int[] rel){
        rel[currNode] = value;
        for (Integer num : g[currNode]) {
            if(rel[num] != 0 && rel[num] == rel[currNode]){
                return false;
            }
            if(rel[num] == 0 && !dfs(num, 3 - value, g, rel)){
                return false;
            }
        }
        return true;
    }
}
