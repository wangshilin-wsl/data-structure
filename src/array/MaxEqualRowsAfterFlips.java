package array;

import java.util.HashMap;
import java.util.Map;
/*力扣：1072. 按列翻转得到最大值等行数
        给定 m x n 矩阵 matrix 。

        你可以从中选出任意数量的列并翻转其上的 每个 单元格。（即翻转后，单元格的值从 0 变成 1，或者从 1 变为 0 。）

        返回 经过一些翻转后，行与行之间所有值都相等的最大行数 。



        示例 1：

        输入：matrix = [[0,1],[1,1]]
        输出：1
        解释：不进行翻转，有 1 行所有值都相等。
        示例 2：

        输入：matrix = [[0,1],[1,0]]
        输出：2
        解释：翻转第一列的值之后，这两行都由相等的值组成。
        示例 3：

        输入：matrix = [[0,0,0],[0,0,1],[1,1,0]]
        输出：2
        解释：翻转前两列的值之后，后两行由相等的值组成。


        提示：

        m == matrix.length
        n == matrix[i].length
        1 <= m, n <= 300
        matrix[i][j] == 0 或 1*/
public class MaxEqualRowsAfterFlips {
    public static void main(String[] args) {
        System.out.println(new MaxEqualRowsAfterFlips().maxEqualRowsAfterFlips(new int[][]{{0,1}, {1,1}}));
    }
   public int maxEqualRowsAfterFlips(int[][] matrix) {
        //如果翻转固定的某些列，可以使得两个不同的行都变成由相同元素组成的行，那么我们称这两行为本质相同的行。例如 001 和 110 就是本质相同的行。
        //本质相同的行有什么特点呢？可以发现，本质相同的行只存在两种情况，一种是由 0 开头的行，另一种是由 1 开头的行。
        // 在开头的元素确定以后，由于翻转的列已经固定，所以可以推断出后续所有元素是 0 还是 1。
        //为了方便统计本质相同的行的数量，我们让由 1 开头的行全部翻转，翻转后行内元素相同的行即为本质相同的行。
        //之后我们将每一行转成字符串形式存储到哈希表中，遍历哈希表得到最多的本质相同的行的数量即为答案。
        int n = matrix.length, m = matrix[0].length, max = 0;
        Map<String, Integer> cntMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            //拿到i行本质行
            char[] chs = new char[m];
            //第一个是否为0，需要翻转开头为1的行
            boolean flag = matrix[i][0] == 0;
            for (int j = 0; j < m; j++) {
                //翻转开头为1的行
                //优化代码：chs[j] = (char)('0' + matrix[i][j] ^ matrix[i][0]);
                chs[j] = (char)('a' + (flag ? matrix[i][j] : matrix[i][j] ^ 1));
            }
            String str = new String(chs);
            cntMap.put(str, cntMap.getOrDefault(str, 0) + 1);
            max = Math.max(max, cntMap.get(str));
        }
        return max;
    }

}