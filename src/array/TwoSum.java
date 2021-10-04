package array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WSL
 * @version 1.0.0
 * @ClassName TwoSum.java
 * @Description TODO
 * @createTime 2021年09月08日 19:46:00
 */
/*
给定一个整数数组 nums和一个整数目标值 target,请你在该数组中找出 和为目标值 target
        的那两个整数,并返回它们的数组下标。

        你可以假设每种输入只会对应一个答案。但是,数组中同一个元素在答案里不能重复出现。

        你可以按任意顺序返回答案。

        示例 1：

        输入：nums = [2,7,11,15], target = 9
        输出：[0,1]
        解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
        示例 2：

        输入：nums = [3,2,4], target = 6
        输出：[1,2]
        示例 3：

        输入：nums = [3,3], target = 6
        输出：[0,1]
        解题思路：
        遍历数据将每个数字放入到HashMap中，判断target-nums[i]存在不？
*/

public class TwoSum {
    public static void main(String[] args) {
        int[] ints = new TwoSum().twoSum(new int[]{2, 7, 11, 15}, 9);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
           if(map.containsKey(target - nums[i])){
               return new int[]{i, map.get(target - nums[i])};
           }else {
               map.put(nums[i],i);
           }
        }
        return null;
    }
}
