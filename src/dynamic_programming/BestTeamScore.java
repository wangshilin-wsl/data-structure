package dynamic_programming;

import java.util.Arrays;

/*力扣：1626. 无矛盾的最佳球队
        假设你是球队的经理。对于即将到来的锦标赛，你想组合一支总体得分最高的球队。球队的得分是球队中所有球员的分数 总和 。

        然而，球队中的矛盾会限制球员的发挥，所以必须选出一支 没有矛盾 的球队。如果一名年龄较小球员的分数 严格大于 一名年龄较大的球员，则存在矛盾。同龄球员之间不会发生矛盾。

        给你两个列表 scores 和 ages，其中每组 scores[i] 和 ages[i] 表示第 i 名球员的分数和年龄。请你返回 所有可能的无矛盾球队中得分最高那支的分数 。



        示例 1：

        输入：scores = [1,3,5,10,15], ages = [1,2,3,4,5]
        输出：34
        解释：你可以选中所有球员。
        示例 2：

        输入：scores = [4,5,6,5], ages = [2,1,2,1]
        输出：16
        解释：最佳的选择是后 3 名球员。注意，你可以选中多个同龄球员。
        示例 3：

        输入：scores = [1,2,3,5], ages = [8,9,10,1]
        输出：6
        解释：最佳的选择是前 3 名球员。


        提示：

        1 <= scores.length, ages.length <= 1000
        scores.length == ages.length
        1 <= scores[i] <= 106
        1 <= ages[i] <= 1000*/
public class BestTeamScore {
    public static void main(String[] args) {
        new BestTeamScore().bestTeamScore(new int[]{1,3,5,10,15}, new int[]{1,2,3,4,5});
    }
    public int bestTeamScore(int[] scores, int[] ages) {
        //思路：排序（先按年龄升序，年龄相同的情况用分数升序）
        //无矛盾，相当于排序后的分数是严格非递减(>=)的
        //动态规划，dp[i]表示选择以i为结尾的最大分数
        //遍历[0,i-1]，scores[i] >= scores[temp]，则dp[i] = dp[temp] + score[i]
        //记录最大值即可
        int n = scores.length;
        //scores下标排序，或者用 int[][] people = new int[n][2];
        //people[i][0]，表示分数，people[i][1]表示年龄
        Integer[] index = new Integer[n];
        for (int i = 0; i < n; i++) {
            index[i] = i;
        }
        //对分数先排序（按年龄升序，年龄相同的情况用分数升序）
        Arrays.sort(index, (o1, o2) -> ages[o1] != ages[o2] ? ages[o1] - ages[o2] : scores[o1] - scores[o2]);
        int[] dp = new int[n];
        int maxScore = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            //最坏的情况，只有i自己，赋值
            dp[i] = scores[index[i]];
            for (int j = 0; j < i; j++) {
                if (scores[index[i]] >= scores[index[j]]) {
                    dp[i] = Math.max(dp[i], dp[j] + scores[index[i]]);
                }
            }
            maxScore = Math.max(maxScore, dp[i]);
        }
        return maxScore;
    }
}
