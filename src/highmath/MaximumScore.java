package highmath;

import java.util.Arrays;

/*力扣：1753. 移除石子的最大得分
        你正在玩一个单人游戏，面前放置着大小分别为 a​​​​​​、b 和 c​​​​​​ 的 三堆 石子。

        每回合你都要从两个 不同的非空堆 中取出一颗石子，并在得分上加 1 分。当存在 两个或更多 的空堆时，游戏停止。

        给你三个整数 a 、b 和 c ，返回可以得到的 最大分数 。


        示例 1：

        输入：a = 2, b = 4, c = 6
        输出：6
        解释：石子起始状态是 (2, 4, 6) ，最优的一组操作是：
        - 从第一和第三堆取，石子状态现在是 (1, 4, 5)
        - 从第一和第三堆取，石子状态现在是 (0, 4, 4)
        - 从第二和第三堆取，石子状态现在是 (0, 3, 3)
        - 从第二和第三堆取，石子状态现在是 (0, 2, 2)
        - 从第二和第三堆取，石子状态现在是 (0, 1, 1)
        - 从第二和第三堆取，石子状态现在是 (0, 0, 0)
        总分：6 分 。
        示例 2：

        输入：a = 4, b = 4, c = 6
        输出：7
        解释：石子起始状态是 (4, 4, 6) ，最优的一组操作是：
        - 从第一和第二堆取，石子状态现在是 (3, 3, 6)
        - 从第一和第三堆取，石子状态现在是 (2, 3, 5)
        - 从第一和第三堆取，石子状态现在是 (1, 3, 4)
        - 从第一和第三堆取，石子状态现在是 (0, 3, 3)
        - 从第二和第三堆取，石子状态现在是 (0, 2, 2)
        - 从第二和第三堆取，石子状态现在是 (0, 1, 1)
        - 从第二和第三堆取，石子状态现在是 (0, 0, 0)
        总分：7 分 。
        示例 3：

        输入：a = 1, b = 8, c = 8
        输出：8
        解释：最优的一组操作是连续从第二和第三堆取 8 回合，直到将它们取空。
        注意，由于第二和第三堆已经空了，游戏结束，不能继续从第一堆中取石子。


        提示：

        1 <= a, b, c <= 105*/
public class MaximumScore {
    public static void main(String[] args) {
        new MaximumScore().maximumScore(4, 4, 6);
    }

    public int maximumScore(int a, int b, int c) {
        //使用贪心
        //假如a,b,c是按大小顺序的，如果a+b<=c那么最大肯定是a,b分别与c配对，则结果为a+b,c中较小的一个
        // 如果a+b>c则先选择总是选择a,b中大的一个和c匹配,设a,c匹配了k1次，b,c匹配了k2次
        //则有k1+k2=c,并且因为总是选择a,b中更大的一个去和c匹配，所有a,b相差不超过1
        //结果为k1 + k2 + (a - k1 + b - k2) / 2
        int sum = a + b + c;
        int max = Math.max(a, Math.max(b, c));
        return Math.min(sum - max, sum / 2);
    }

    /**
     * @methodName maximumScore1
     * @Author WSL
     * @Description  假如a,b,c是按大小顺序的，
     * 如果a+b<=c那么最大肯定是a,b分别与c配对，则结果为a+b,c中较小的一个，
     * 如果a+b>c则先a,b配对，在每次配对完成后，再次判断剩下的a,b是否<=c
     * @Date 19:02 2022/12/21
     * @return int
     **/
    public int maximumScore1(int a, int b, int c) {
        //假如a,b,c是按大小顺序的，如果a+b<=c那么最大肯定是a,b分别与c配对，则结果为a+b,c中较小的一个
        // 如果a+b>c则先a,b配对，在每次配对完成后，再次判断剩下的a,b是否<=c
        int[] nums = {a, b, c};
        Arrays.sort(nums);
        return hh(nums[0], nums[1], nums[2]);
    }
    public int hh(int a, int b, int c) {
        if (a + b <= c) {
            return a + b;
        } else {
            return 1 + hh(a - 1, b - 1, c);
        }
    }

}
