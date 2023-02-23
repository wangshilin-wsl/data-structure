package dynamic_programming.stone;

/*力扣：486. 预测赢家
        给你一个整数数组 nums 。玩家 1 和玩家 2 基于这个数组设计了一个游戏。

        玩家 1 和玩家 2 轮流进行自己的回合，玩家 1 先手。开始时，两个玩家的初始分值都是 0 。每一回合，玩家从数组的任意一端取一个数字（即，nums[0] 或 nums[nums.length - 1]），取到的数字将会从数组中移除（数组长度减 1 ）。玩家选中的数字将会加到他的得分上。当数组中没有剩余数字可取时，游戏结束。

        如果玩家 1 能成为赢家，返回 true 。如果两个玩家得分相等，同样认为玩家 1 是游戏的赢家，也返回 true 。你可以假设每个玩家的玩法都会使他的分数最大化。



        示例 1：

        输入：nums = [1,5,2]
        输出：false
        解释：一开始，玩家 1 可以从 1 和 2 中进行选择。
        如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。如果玩家 2 选择了 5 ，那么玩家 1 则只剩下 1（或者 2 ）可选。
        所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
        因此，玩家 1 永远不会成为赢家，返回 false 。
        示例 2：

        输入：nums = [1,5,233,7]
        输出：true
        解释：玩家 1 一开始选择 1 。然后玩家 2 必须从 5 和 7 中进行选择。无论玩家 2 选择了哪个，玩家 1 都可以选择 233 。
        最终，玩家 1（234 分）比玩家 2（12 分）获得更多的分数，所以返回 true，表示玩家 1 可以成为赢家。


        提示：

        1 <= nums.length <= 20
        0 <= nums[i] <= 107*/
public class PredictTheWinner {
    public static void main(String[] args) {
        System.out.println(new PredictTheWinner().PredictTheWinner(new int[]{1, 5, 2}));
    }


    //动态规划
    public boolean PredictTheWinner(int[] nums) {
        //动态规划
        int len = nums.length;
        //在记忆搜索的基础上演变dp[i][j]表示在[i,j]当前玩家赢过对手的分数
        //i==j的时候，由于i<=j所以dp[i][j]=nums[i]
        //在dp[i][j]赢对方的分数,当选择i位置的时候分数为nums[i]，对手得分为dp[i + 1][j]
        //当选择j位置的时候分数为nums[j]，对手得分为dp[i][j - 1]
        //dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1])
        //由前置结果可以得出,i从大大小，j从小到大
        int[][] dp = new int[len][len];
        //初始化dp[i][i]
        for (int i = 0; i < len; i++) {
            dp[i][i] = nums[i];
        }
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }

        return dp[0][len - 1] >= 0;
    }

    //递归+记忆搜索
    public boolean PredictTheWinner1(int[] nums) {
        int len = nums.length;
        //记录基于[left, right]，当前做出选择的玩家，赢过对手的分数
        int[][] arr = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                arr[i][j] = Integer.MAX_VALUE;
            }
        }
        return choose(nums, 0, nums.length -1, arr) >= 0;
    }

    //返回当前做选择的玩家，基于当前区间[i,j]，赢过对手的分数。
    public int choose(int[] nums, int left, int right, int[][] arr) {
        //如果left==right，第一个人先选得nums[left]分，第二个人没得选o分，所以多nums[left]分
        if (left == right) {
            return nums[left];
        }
        //如果缓存中有，直接使用
        if (arr[left][right] != Integer.MAX_VALUE) {
            return arr[left][right];
        }
        //第一个人选择左边的
        //choose(nums, left + 1, right)表示的是第二个人选了，赢了多少分
        int lMark = nums[left] - choose(nums, left + 1, right, arr);
        //第一个人选择右边的
        //choose(nums, left, right - 1)表示的是第二个人选了，赢了多少分
        int rMark = nums[right] - choose(nums, left, right - 1, arr);
        //存储当前值
        arr[left][right] = Math.max(rMark, lMark);
        return arr[left][right];
    }
}
