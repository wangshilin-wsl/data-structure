/*给你一个下标从 0开始的数组nums，它包含 n个 互不相同的正整数。
        请你对这个数组执行 m个操作，在第 i个操作中，
        你需要将数字operations[i][0] 替换成operations[i][1]。

        题目保证在第 i个操作中：

        operations[i][0]在nums中存在。
        operations[i][1]在nums中不存在。
        请你返回执行完所有操作后的数组。



        示例 1：

        输入：nums = [1,2,4,6], operations = [[1,3],[4,7],[6,1]]
        输出：[3,2,7,1]
        解释：我们对 nums 执行以下操作：
        - 将数字 1 替换为 3 。nums 变为 [3,2,4,6] 。
        - 将数字 4 替换为 7 。nums 变为 [3,2,7,6] 。
        - 将数字 6 替换为 1 。nums 变为 [3,2,7,1] 。
        返回最终数组 [3,2,7,1] 。
        示例 2：

        输入：nums = [1,2], operations = [[1,3],[2,1],[3,2]]
        输出：[2,1]
        解释：我们对 nums 执行以下操作：
        - 将数字 1 替换为 3 。nums 变为 [3,2] 。
        - 将数字 2 替换为 1 。nums 变为 [3,1] 。
        - 将数字 3 替换为 2 。nums 变为 [2,1] 。
        返回最终数组 [2,1] 。


        提示：

        n == nums.length
        m == operations.length
        1 <= n, m <= 105
        nums中所有数字 互不相同。
        operations[i].length == 2
        1 <= nums[i], operations[i][0], operations[i][1] <= 106
        在执行第i 个操作时，operations[i][0]在nums中存在。
        在执行第i个操作时，operations[i][1]在nums中不存在。*/
public class Test {
    public static void main(String[] args) {
    }




}
