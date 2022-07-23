package array;

import java.util.HashMap;
import java.util.Map;

/*
力扣：剑指 Offer II 004. 只出现一次的数字
        给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。

        示例 1：
        输入：nums = [2,2,3,2]
        输出：3

        示例 2：
        输入：nums = [0,1,0,1,0,1,100]
        输出：100

        提示：
        1 <= nums.length <= 3 * 104
        -231 <= nums[i] <= 231 - 1
        nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次*/
public class SingleNumber {
    public static void main(String[] args) {
        System.out.println(new SingleNumber().singleNumber(new int[]{2,2,2,3}));
    }



    /**
     * @methodName singleNumber
     * @Author WSL
     * @Description  思路，遍历32位int的每一位，若位置的1的个数%3=1，说明单独的数字在这个位置的
     * 为1，将单独的数字32个位置都确认下来。
     * @Date 16:41 2022/7/23
     * @Param nums
     * @return int
     **/
    public int singleNumber(int[] nums) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            int total = 0;
            for (int num : nums) {
                if(((num >> i) & 1) == 1){
                    total ++;
                }
            }
            if(total % 3 == 1){
                ret += (1 << i);
            }
        }
        return ret;
    }

    /**
     * 上面的优化版本
     */
    public int singleNumberPlus(int[] nums) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            int total = 0;
            for (int num : nums) {
                total += (num >> i) & 1;
            }
            if(total % 3 == 1){
                ret |= (1 << i);
            }
        }
        return ret;
    }


    /**
     * @methodName singleNumber1
     * @Author WSL
     * @Description  思路用map记录每个数出现的次数，最后去找次数为1的
     * @Date 16:44 2022/7/23
     * @Param nums
     * @return int
     **/
    public int singleNumber1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() == 1){
                return entry.getKey();
            }
        }
        return -1;
    }
}
