package competition.likou_2023_04_16;

import java.util.Arrays;

/*力扣：6376. 一最多的行 显示英文描述
        通过的用户数0
        尝试过的用户数0
        用户总通过次数0
        用户总提交次数0
        题目难度Easy
        给你一个大小为 m x n 的二进制矩阵 mat ，请你找出包含最多 1 的行的下标（从 0 开始）以及这一行中 1 的数目。

        如果有多行包含最多的 1 ，只需要选择 行下标最小 的那一行。

        返回一个由行下标和该行中 1 的数量组成的数组。



        示例 1：

        输入：mat = [[0,1],[1,0]]
        输出：[0,1]
        解释：两行中 1 的数量相同。所以返回下标最小的行，下标为 0 。该行 1 的数量为 1 。所以，答案为 [0,1] 。
        示例 2：

        输入：mat = [[0,0,0],[0,1,1]]
        输出：[1,2]
        解释：下标为 1 的行中 1 的数量最多。该行 1 的数量为 2 。所以，答案为 [1,2] 。
        示例 3：

        输入：mat = [[0,0],[1,1],[0,0]]
        输出：[1,2]
        解释：下标为 1 的行中 1 的数量最多。该行 1 的数量为 2 。所以，答案为 [1,2] 。


        提示：

        m == mat.length
        n == mat[i].length
        1 <= m, n <= 100
        mat[i][j] 为 0 或 1*/
public class RowAndMaximumOnes {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new RowAndMaximumOnes().rowAndMaximumOnes(new int[][]{{0, 1}, {1, 0}})));
    }
    public int[] rowAndMaximumOnes(int[][] mat) {
        int n = mat.length, m = mat[0].length, max = 0, index = 0;
        for (int i = 0; i < n; i++) {
            int temp = 0;
            for (int j = 0; j < m; j++) {
                temp += mat[i][j];
            }
            if (temp > max) {
                max = temp;
                index = i;
            }
        }
        return new int[]{index, max};
    }
}
