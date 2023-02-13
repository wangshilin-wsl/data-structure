package competition.haojinkeji_2023_02_12;

import java.util.HashMap;
import java.util.Map;

/*力扣：6356. 子字符串异或查询 显示英文描述
        给你一个 二进制字符串 s 和一个整数数组 queries ，其中 queries[i] = [firsti, secondi] 。

        对于第 i 个查询，找到 s 的 最短子字符串 ，它对应的 十进制值 val 与 firsti 按位异或 得到 secondi ，换言之，val ^ firsti == secondi 。

        第 i 个查询的答案是子字符串 [lefti, righti] 的两个端点（下标从 0 开始），如果不存在这样的子字符串，则答案为 [-1, -1] 。如果有多个答案，请你选择 lefti 最小的一个。

        请你返回一个数组 ans ，其中 ans[i] = [lefti, righti] 是第 i 个查询的答案。

        子字符串 是一个字符串中一段连续非空的字符序列。



        示例 1：

        输入：s = "101101", queries = [[0,5],[1,2]]
        输出：[[0,2],[2,3]]
        解释：第一个查询，端点为 [0,2] 的子字符串为 "101" ，对应十进制数字 5 ，且 5 ^ 0 = 5 ，所以第一个查询的答案为 [0,2]。第二个查询中，端点为 [2,3] 的子字符串为 "11" ，对应十进制数字 3 ，且 3 ^ 1 = 2 。所以第二个查询的答案为 [2,3] 。
        示例 2：

        输入：s = "0101", queries = [[12,8]]
        输出：[[-1,-1]]
        解释：这个例子中，没有符合查询的答案，所以返回 [-1,-1] 。
        示例 3：

        输入：s = "1", queries = [[4,5]]
        输出：[[0,0]]
        解释：这个例子中，端点为 [0,0] 的子字符串对应的十进制值为 1 ，且 1 ^ 4 = 5 。所以答案为 [0,0] 。


        提示：

        1 <= s.length <= 104
        s[i] 要么是 '0' ，要么是 '1' 。
        1 <= queries.length <= 105
        0 <= firsti, secondi <= 109*/
public class SubstringXorQueries {
    public static void main(String[] args) {
        new SubstringXorQueries().substringXorQueries("101101", new int[][]{{0,5},{1,2}});
    }
    //将所有的s的可能的值都存在map中直接查找
    public int[][] substringXorQueries(String s, int[][] queries) {
        //queries最大为1e9 < 2^30,所以列举s中最长30位对应的数字
        int[][] answer = new int[queries.length][2];
        int[] notFound = new int[]{-1, -1};
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {


        }

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            answer[i] = map.getOrDefault((query[0] ^ query[1]), notFound);
        }
        return answer;
    }


    //超时
    public int[][] substringXorQueries1(String s, int[][] queries) {
        int[][] answer = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            String res = Integer.toString(query[0] ^ query[1], 2);
            int index = s.indexOf(res);
            answer[i][0] = index;
            answer[i][1] = index == -1 ? -1 : index + res.length() - 1;
        }
        return answer;
    }
}
