package competition.likou_2023_03_19;

import java.util.HashMap;
import java.util.Map;

/*力扣：6321. 执行操作后的最大 MEX 显示英文描述
        给你一个下标从 0 开始的整数数组 nums 和一个整数 value 。

        在一步操作中，你可以对 nums 中的任一元素加上或减去 value 。

        例如，如果 nums = [1,2,3] 且 value = 2 ，你可以选择 nums[0] 减去 value ，得到 nums = [-1,2,3] 。
        数组的 MEX (minimum excluded) 是指其中数组中缺失的最小非负整数。

        例如，[-1,2,3] 的 MEX 是 0 ，而 [1,0,3] 的 MEX 是 2 。
        返回在执行上述操作 任意次 后，nums 的最大 MEX 。



        示例 1：

        输入：nums = [1,-10,7,13,6,8], value = 5
        输出：4
        解释：执行下述操作可以得到这一结果：
        - nums[1] 加上 value 两次，nums = [1,0,7,13,6,8]
        - nums[2] 减去 value 一次，nums = [1,0,2,13,6,8]
        - nums[3] 减去 value 两次，nums = [1,0,2,3,6,8]
        nums 的 MEX 是 4 。可以证明 4 是可以取到的最大 MEX 。
        示例 2：

        输入：nums = [1,-10,7,13,6,8], value = 7
        输出：2
        解释：执行下述操作可以得到这一结果：
        - nums[2] 减去 value 一次，nums = [1,-10,0,13,6,8]
        nums 的 MEX 是 2 。可以证明 2 是可以取到的最大 MEX 。


        提示：

        1 <= nums.length, value <= 105
        -109 <= nums[i] <= 109*/
public class FindSmallestInteger {
    public static void main(String[] args) {
        System.out.println(new FindSmallestInteger().findSmallestInteger(new int[]{3,0,3,2,4,2,1,1,0,4}, 5));
    }

    //优化，可以不适用map，直接使用数组
    public int findSmallestInteger(int[] nums, int value) {
        int[] count = new int[value];
        for (int num : nums) {
            count[(num % value + value) % value]++;
        }
        for (int i = 0;; i++) {
            if (--count[i % value] < 0) {
                return i;
            }
        }
    }

    public int findSmallestInteger1(int[] nums, int value) {
        //思路：将nums中所有数换成/value后的余数，并记录次数
        //最大的mex为[0,1,2..n-1]则为n
        //所以从0开始判断nums中是否可以+-value形成i[0,n-1]
        //mod = i % value，只要mod能在nums中取到，则i一定能取到 mod + j * value = i，符合题意一直加j个value就可以形成i
        //要注意nums[i]=-10,value=5的情况，它mod  value最小的值为0，需要特殊处理
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int mod = nums[i] >= 0 ? nums[i] % value : (Math.abs(nums[i]) % value == 0 ? 0 : value -  Math.abs(nums[i]) % value);
            map.put(mod, map.getOrDefault(mod, 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            int mod = i % value;
            if (map.getOrDefault(mod, 0) <= 0) {
                return i;
            }
            map.put(mod, map.get(mod) - 1);
        }
        return n;
    }
}
