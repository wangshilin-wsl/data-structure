package competition.meituan_2022_11_13;
/*
力扣：2022-11-13美团第二题中等
6234. 最小公倍数为 K 的子数组数目 显示英文描述

        给你一个整数数组 nums 和一个整数 k ，请你统计并返回 nums 的 子数组 中满足 元素最小公倍数为 k 的子数组数目。

        子数组 是数组中一个连续非空的元素序列。

        数组的最小公倍数 是可被所有数组元素整除的最小正整数。

        示例 1 ：
        输入：nums = [3,6,2,7,1], k = 6
        输出：4
        解释：以 6 为最小公倍数的子数组是：
        - [3,6,2,7,1] - 3,6
        - [3,6,2,7,1] - 6
        - [3,6,2,7,1] - 3, 6, 2
        - [3,6,2,7,1]- 6,2

        示例 2 ：
        输入：nums = [3], k = 2
        输出：0
        解释：不存在以 2 为最小公倍数的子数组。

        提示：
        1 <= nums.length <= 1000
        1 <= nums[i], k <= 1000
        */
public class SubarrayLCM {
    public static void main(String[] args) {
        System.out.println(new SubarrayLCM().subarrayLCM(new int[]{3,6,2,7,1}, 6));
    }

    /**
     * @methodName subarrayLCM
     * @Author WSL
     * @Description  使用动态规划,dp[i]表示必须包含nums[i]为结尾的子数组中满足条件的个数，则必须nums[i]
     * 1、当nums[i]大于k或者不能整除k则dp[i]=0
     * 2、当nums[i]<=k并且可以整除k，分以下两种情况
     *        1当dp[i - 1] > 0 则 dp[i] = dp[i - 1] + (temp == k ? 1 : 0);，单独就nums[i]这一种情况
     *        2当dp[i- 1]==0 则往前查看是否有满足条件的数组
     * @Date 12:00 2022/11/13
     * @Param nums
     * @return int
     **/
    public int subarrayLCM(int[] nums, int k) {
        int n = nums.length;
        int res = 0;
        int[] dp = new int[n];
        if (nums[0] == k) {
            dp[0] = 1;
            res+=1;
        }
        for (int i = 1; i < n; i++) {
            int temp = nums[i];
            if (temp > k || k % temp != 0) {
                dp[i] = 0;
            } else {
                if (dp[i - 1] > 0) {
                    dp[i] = dp[i - 1] + (temp == k ? 1 : 0);
                } else {
                    for (int j = i; j >= 0; j--) {
                        int m = get(j, i, nums, k);
                        if (m != 1) {
                            break;
                        }
                        dp[i] += m;
                    }
                }
            }
            res += dp[i];
        }
        return res;
    }
    public int get(int i, int j, int[] nums, int k) {
        int max = nums[i];
        for (int l = i; l <= j; l++) {
            max = Math.max(max, nums[l]);
        }
        while (true) {
            boolean flag = true;
            for (int l = i; l <= j; l++) {
                if (max % nums[l] !=0) {
                    flag = false;
                }
            }
            if (flag) {
                return max == k ? 1 : 0;
            }
            max++;
        }
    }
}
