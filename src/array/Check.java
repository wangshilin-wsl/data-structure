package array;

import java.util.ArrayList;
import java.util.List;

/*力扣：1752. 检查数组是否经排序和轮转得到
        给你一个数组 nums 。nums 的源数组中，所有元素与 nums 相同，但按非递减顺序排列
        如果 nums 能够由源数组轮转若干位置（包括 0 个位置）得到，则返回 true ；否则，返回 false 。
        源数组中可能存在 重复项 。
        注意：我们称数组 A 在轮转 x 个位置后得到长度相同的数组 B ，
        当它们满足 A[i] == B[(i+x) % A.length] ，其中 % 为取余运算。

        示例 1：
        输入：nums = [3,4,5,1,2]
        输出：true
        解释：[1,2,3,4,5] 为有序的源数组。
        可以轮转 x = 3 个位置，使新数组从值为 3 的元素开始：[3,4,5,1,2] 。

        示例 2：
        输入：nums = [2,1,3,4]
        输出：false
        解释：源数组无法经轮转得到 nums

        示例 3：
        输入：nums = [1,2,3]
        输出：true
        解释：[1,2,3] 为有序的源数组。
        可以轮转 x = 0 个位置（即不轮转）得到 nums 。

        提示：
        1 <= nums.length <= 100
        1 <= nums[i] <= 100*/
public class Check {
    public static void main(String[] args) {
        System.out.println(new Check().check(new int[]{3,4,5,1,2}));
    }

    /**
     * @Description 思路，对于x=0则有nums是递增(包括=)的
     * 对于x>0，肯定存在两个子数组，且每一个子数组都是递增的(包括=)
     * 所以找到第一个小于前面数字的数的下标index，如果不存在即x=0的情况
     * 如果存在，前面部分已经是递增的，所以判断该index以后是否是递增
     * 如果不是，则返回false，如果是，则还需判断前一个子数组是否都大于后一个子数组
     * 因为两个数组都是递增的，则判断前部分最小的nums[0]是否大于后半部分最大的nums[n-1]
     * @name check
     * @param nums
     * @return boolean
     * @throws
     **/
    public boolean check(int[] nums) {
        int n = nums.length, index = 0;
        //找到第一个比其前一个数小的数的下标
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                index = i;
                break;
            }
        }
        //整个数组就是递增的，那么肯定是可以通过转换得到的，x=0
        if (index == 0) {
            return true;
        }
        //index以前都是递增的，nums[index] < nums[index - 1]
        for (int i = index + 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                return false;
            }
        }
        //需要判断前一部分的最大值是否小于后一部分的最小值
        return nums[0] >= nums[n - 1];
    }

    /**
     * @methodName 先找到所有最小的数字的下标（数字是可以重复的），
     * 从每个下标开始遍历（如果到了末尾则从头开始来），判断是否递增，
     * 如果是递增表示是可以通过转换得到的
     * @Author WSL
     * @Description  
     * @Date 16:50 2022/11/27
     * @Param nums
     * @return boolean
     **/
    public boolean check1(int[] nums) {
        int n = nums.length, min = nums[0];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (min == nums[i]) {
                list.add(i);
            }
        }
        int num = 0;
        for (Integer integer : list) {
            int temp = min;
            for (int i = 1; i < n; i++) {
                int j = (i + integer) % n;
                if (nums[j] < temp) {
                    num++;
                    break;
                }
                temp = nums[j];
            }
        }

        return num < list.size();
    }
}
