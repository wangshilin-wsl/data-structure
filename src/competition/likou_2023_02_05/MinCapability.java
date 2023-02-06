package competition.likou_2023_02_05;

/*力扣： 6346. 打家劫舍 IV 显示英文描述
        沿街有一排连续的房屋。每间房屋内都藏有一定的现金。现在有一位小偷计划从这些房屋中窃取现金。

        由于相邻的房屋装有相互连通的防盗系统，所以小偷 不会窃取相邻的房屋 。

        小偷的 窃取能力 定义为他在窃取过程中能从单间房屋中窃取的 最大金额 。

        给你一个整数数组 nums 表示每间房屋存放的现金金额。形式上，从左起第 i 间房屋中放有 nums[i] 美元。

        另给你一个整数数组 k ，表示窃贼将会窃取的 最少 房屋数。小偷总能窃取至少 k 间房屋。

        返回小偷的 最小 窃取能力。



        示例 1：

        输入：nums = [2,3,5,9], k = 2
        输出：5
        解释：
        小偷窃取至少 2 间房屋，共有 3 种方式：
        - 窃取下标 0 和 2 处的房屋，窃取能力为 max(nums[0], nums[2]) = 5 。
        - 窃取下标 0 和 3 处的房屋，窃取能力为 max(nums[0], nums[3]) = 9 。
        - 窃取下标 1 和 3 处的房屋，窃取能力为 max(nums[1], nums[3]) = 9 。
        因此，返回 min(5, 9, 9) = 5 。
        示例 2：

        输入：nums = [2,7,9,3,1], k = 2
        输出：2
        解释：共有 7 种窃取方式。窃取能力最小的情况所对应的方式是窃取下标 0 和 4 处的房屋。返回 max(nums[0], nums[4]) = 2 。


        提示：

        1 <= nums.length <= 105
        1 <= nums[i] <= 109
        1 <= k <= (nums.length + 1)/2*/
public class MinCapability {
    //超时了
    int minCapability = Integer.MAX_VALUE;
    int[] arr;
    int length;

    public static void main(String[] args) {
        System.out.println(new MinCapability().minCapability(new int[]{9,6,20,21,8}, 3));
    }

    //思路：看到【最大化的最小值】、【最小化的最大值】就要想到二分法来解题
    //1、设偷窃能力为n，满足至少可以偷窃k间房子，当n增加的时候，k一定是跟着增加的(非递减),拥有了单调性
    //2、在判断偷窃能力为capability时，是否可以偷窃k间房子，使用的是动态规划
    //dp[i]：表示在前i个房间窃取金额不超过c的最大房屋个数
    //如果不选第i个房子,dp[i] = dp[i - 1]
    //如果选第i个房子，dp[i] = dp[i - 2] + 1
    //但是至少要k个，所以dp[i]尽可能的要大dp[i] = max(dp[i - 1], dp[i - 2] + 1)
    //可以优化空间，使用2个数代替
    public int minCapability(int[] nums, int k) {
        int low = 0, high = (int)1e9;
        while (low + 1 < high) {
            int mid = low + high >> 1;
            if (judge(nums, k, mid)) {
                high = mid;
            } else {
                low = mid;
            }
        }
        return high;
    }

    public boolean judge(int[] nums, int k, int capability) {
        int dp0 = 0, dp1 = 0;
        for (int num : nums) {
            if (capability < num) {
                dp0 = dp1;
            } else {
                int temp = dp1;
                dp1 = Math.max(dp1, dp0 + 1);
                dp0 = temp;
            }
            if (dp1 >= k) {
                return true;
            }
        }
        return dp1 >= k;
    }

    public int minCapability1(int[] nums, int k) {
        arr = new int[k];
        length = k;
        dfs(0, nums);
        return minCapability;
    }
    void dfs(int n, int[] nums){
        if (n >= length) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < length; i++) {
                max = Math.max(max, nums[arr[i]]);
            }
            minCapability = Math.min(minCapability, max);
        } else {
            int start = n == 0 ? 0 : arr[n - 1] + 2;
            for (int i = start; i < nums.length; i++) {
                arr[n] = i;
                dfs(n + 1, nums);
            }
        }
    }
}
