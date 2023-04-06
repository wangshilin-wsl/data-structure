package competition.axlh_2023_04_02;

import java.util.Arrays;

/*力扣：6364. 老鼠和奶酪 显示英文描述
        通过的用户数172
        尝试过的用户数195
        用户总通过次数173
        用户总提交次数208
        题目难度Medium
        有两只老鼠和 n 块不同类型的奶酪，每块奶酪都只能被其中一只老鼠吃掉。

        下标为 i 处的奶酪被吃掉的得分为：

        如果第一只老鼠吃掉，则得分为 reward1[i] 。
        如果第二只老鼠吃掉，则得分为 reward2[i] 。
        给你一个正整数数组 reward1 ，一个正整数数组 reward2 ，和一个非负整数 k 。

        请你返回第一只老鼠恰好吃掉 k 块奶酪的情况下，最大 得分为多少。



        示例 1：

        输入：reward1 = [1,1,3,4], reward2 = [4,4,1,1], k = 2
        输出：15
        解释：这个例子中，第一只老鼠吃掉第 2 和 3 块奶酪（下标从 0 开始），第二只老鼠吃掉第 0 和 1 块奶酪。
        总得分为 4 + 4 + 3 + 4 = 15 。
        15 是最高得分。
        示例 2：

        输入：reward1 = [1,1], reward2 = [1,1], k = 2
        输出：2
        解释：这个例子中，第一只老鼠吃掉第 0 和 1 块奶酪（下标从 0 开始），第二只老鼠不吃任何奶酪。
        总得分为 1 + 1 = 2 。
        2 是最高得分。


        提示：

        1 <= n == reward1.length == reward2.length <= 105
        1 <= reward1[i], reward2[i] <= 1000
        0 <= k <= n*/
public class MiceAndCheese {
    public static void main(String[] args) {
        System.out.println(new MiceAndCheese().miceAndCheese(new int[]{1, 1, 3, 4}, new int[]{4, 4, 1, 1}, 2));
    }
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        //思路：首先假设都是老鼠2吃了(分数为sum)，那么现在需要选择其中k块是老鼠1吃，则分数=(sum + reward1[i] - reward2[i])其中有k个这样的i;
        //要分数足够大，则reward1[i] - reward2[i]要足够大，所以使用一个索引数据，根据reward1[i] - reward2[i]也就是差值降序排序
        //在遍历index选择前k个给老鼠1吃即可
        int n = reward1.length, sum = 0;
        Integer[] indexs = new Integer[n];
        for (int i = 0; i < n; i++) {
            indexs[i] = i;
            sum += reward2[i];
        }
        Arrays.sort(indexs, (o1, o2) -> (reward1[o2] - reward2[o2]) - (reward1[o1] - reward2[o1]));
        for (int i = 0; i < k; i++) {
            int index = indexs[i];
            sum += (reward1[index] - reward2[index]);
        }
        return sum;
    }
}
