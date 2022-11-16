package array;

/*力扣：775. 全局倒置与局部倒置
        给你一个长度为 n 的整数数组 nums ，表示由范围 [0, n - 1] 内所有整数组成的一个排列。
        全局倒置 的数目等于满足下述条件不同下标对 (i, j) 的数目：
        0 <= i < j < n  nums[i] > nums[j]
        局部倒置 的数目等于满足下述条件的下标 i 的数目：
        0 <= i < n - 1
        nums[i] > nums[i + 1]
        当数组 nums 中 全局倒置 的数量等于 局部倒置 的数量时，返回 true ；否则，返回 false 。

        示例 1：
        输入：nums = [1,0,2]
        输出：true
        解释：有 1 个全局倒置，和 1 个局部倒置。

        示例 2：
        输入：nums = [1,2,0]
        输出：false
        解释：有 2 个全局倒置，和 1 个局部倒置。

        提示：
        n == nums.length
        1 <= n <= 105
        0 <= nums[i] < n nums 中的所有整数 互不相同
        nums 是范围 [0, n - 1] 内所有数字组成的一个排列
        思路：
         一个局部倒置一定是一个全局倒置，所有只要有非局部倒置则两个一定不相等，返回false
        非局部倒置 对于i < j - 1 有nums[i] > nums[j]  j不是i相邻的前方元素
        则从后往前遍历，记录当前最小值，如果只要nums[i] > min则存在非局部倒置
        min记录的是[i + 2,n-1]的最小值*/
public class IsIdealPermutation {
    public static void main(String[] args) {
        System.out.println(new IsIdealPermutation().isIdealPermutation(new int[]{1, 0, 2}));
    }
    public boolean isIdealPermutation(int[] nums) {
        //一个局部倒置一定是一个全局倒置，所有只要有非局部倒置则两个一定不相等，返回false
        //非局部倒置 对于i < j - 1 有nums[i] > nums[j]  j不是i相邻的前方元素
        //则从后往前遍历，记录当前最小值，如果只要nums[i] > min则存在非局部倒置
        //min记录的是[i + 2,n-1]的最小值
        int n = nums.length, min = nums[n - 1];
        for (int i = n - 3; i >= 0; i--) {
            if (nums[i] > min) {
                return false;
            }
            min = Math.min(min, nums[i + 1]);
        }
        return true;
    }
}
