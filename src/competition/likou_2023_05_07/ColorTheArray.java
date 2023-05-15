package competition.likou_2023_05_07;

import java.util.Arrays;

/*力扣：6418. 有相同颜色的相邻元素数目 显示英文描述
        通过的用户数467
        尝试过的用户数510
        用户总通过次数470
        用户总提交次数580
        题目难度Medium
        给你一个下标从 0 开始、长度为 n 的数组 nums 。一开始，所有元素都是 未染色 （值为 0 ）的。

        给你一个二维整数数组 queries ，其中 queries[i] = [indexi, colori] 。

        对于每个操作，你需要将数组 nums 中下标为 indexi 的格子染色为 colori 。

        请你返回一个长度与 queries 相等的数组 answer ，其中 answer[i]是前 i 个操作 之后 ，相邻元素颜色相同的数目。

        更正式的，answer[i] 是执行完前 i 个操作后，0 <= j < n - 1 的下标 j 中，满足 nums[j] == nums[j + 1] 且 nums[j] != 0 的数目。



        示例 1：

        输入：n = 4, queries = [[0,2],[1,2],[3,1],[1,1],[2,1]]
        输出：[0,1,1,0,2]
        解释：一开始数组 nums = [0,0,0,0] ，0 表示数组中还没染色的元素。
        - 第 1 个操作后，nums = [2,0,0,0] 。相邻元素颜色相同的数目为 0 。
        - 第 2 个操作后，nums = [2,2,0,0] 。相邻元素颜色相同的数目为 1 。
        - 第 3 个操作后，nums = [2,2,0,1] 。相邻元素颜色相同的数目为 1 。
        - 第 4 个操作后，nums = [2,1,0,1] 。相邻元素颜色相同的数目为 0 。
        - 第 5 个操作后，nums = [2,1,1,1] 。相邻元素颜色相同的数目为 2 。
        示例 2：

        输入：n = 1, queries = [[0,100000]]
        输出：[0]
        解释：一开始数组 nums = [0] ，0 表示数组中还没染色的元素。
        - 第 1 个操作后，nums = [100000] 。相邻元素颜色相同的数目为 0 。


        提示：

        1 <= n <= 105
        1 <= queries.length <= 105
        queries[i].length == 2
        0 <= indexi <= n - 1
        1 <=  colori <= 105*/
public class ColorTheArray {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new ColorTheArray().colorTheArray(4, new int[][]{{0, 2}, {1, 2}, {3, 1}, {1, 1}, {2, 1}})));
    }
    public int[] colorTheArray(int n, int[][] queries) {
        int len = queries.length;
        int[] answer = new int[len], nums = new int[n];
        nums[queries[0][0]] = queries[0][1];
        for (int i = 1; i < len; i++) {
            int[] query = queries[i];
            int index = query[0];
            int color = query[1];
            answer[i] = answer[i - 1];
            if (color == nums[index]) {
                continue;
            }
            if (index - 1 >= 0) {
                if (nums[index - 1] == nums[index] && nums[index] != 0) {
                    answer[i]--;
                }
                if (nums[index - 1] == color && color != 0) {
                    answer[i]++;
                }
            }
            if (index + 1 < n) {
                if (nums[index + 1] == nums[index] && nums[index] != 0) {
                    answer[i]--;
                }
                if (nums[index + 1] == color && color != 0) {
                    answer[i]++;
                }
            }
            nums[index] = color;
        }
        return answer;
    }
}
