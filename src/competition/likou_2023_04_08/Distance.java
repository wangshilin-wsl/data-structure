package competition.likou_2023_04_08;

import java.util.*;

/*力扣：6360. 等值距离和 显示英文描述
        通过的用户数0
        尝试过的用户数2
        用户总通过次数0
        用户总提交次数2
        题目难度Medium
        给你一个下标从 0 开始的整数数组 nums 。现有一个长度等于 nums.length 的数组 arr 。对于满足 nums[j] == nums[i] 且 j != i 的所有 j ，arr[i] 等于所有 |i - j| 之和。如果不存在这样的 j ，则令 arr[i] 等于 0 。

        返回数组 arr 。



        示例 1：

        输入：nums = [1,3,1,1,2]
        输出：[5,0,3,4,0]
        解释：
        i = 0 ，nums[0] == nums[2] 且 nums[0] == nums[3] 。因此，arr[0] = |0 - 2| + |0 - 3| = 5 。
        i = 1 ，arr[1] = 0 因为不存在值等于 3 的其他下标。
        i = 2 ，nums[2] == nums[0] 且 nums[2] == nums[3] 。因此，arr[2] = |2 - 0| + |2 - 3| = 3 。
        i = 3 ，nums[3] == nums[0] 且 nums[3] == nums[2] 。因此，arr[3] = |3 - 0| + |3 - 2| = 4 。
        i = 4 ，arr[4] = 0 因为不存在值等于 2 的其他下标。
        示例 2：

        输入：nums = [0,5,3]
        输出：[0,0,0]
        解释：因为 nums 中的元素互不相同，对于所有 i ，都有 arr[i] = 0 。


        提示：

        1 <= nums.length <= 105
        0 <= nums[i] <= 109*/
public class Distance {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Distance().distance(new int[]{1, 3, 1, 1, 2})));
    }


    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] res = new long[n];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = map.getOrDefault(nums[i], new ArrayList<>());
            list.add(i);
            map.put(nums[i], list);
        }
        //将同样数字的索引进行排计算，不能用暴力法会超时
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> value = entry.getValue();
            long rSum = 0L, lSum = 0L;
            for (Integer integer : value) {
                rSum += integer;
            }
            Collections.sort(value);
            for (int i = 0; i < value.size(); i++) {
                rSum -= value.get(i);
                //公式：右边数字之和-左边数字之和-(左边个数-右边个数)*value.get(i)
                res[value.get(i)] = rSum - (long) (value.size() - 2 * i - 1) * value.get(i) - lSum;
                lSum += value.get(i);
            }
        }
        return res;
    }

    //超时
    public long[] distance1(int[] nums) {
        int n = nums.length;
        long[] res = new long[n];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = map.getOrDefault(nums[i], new ArrayList<>());
            list.add(i);
            map.put(nums[i], list);
        }
        for (int i = 0; i < n; i++) {
            long sum = 0L;
            List<Integer> list = map.get(nums[i]);
            for (Integer integer : list) {
                sum += Math.abs(integer - i);
            }
            res[i] = sum;
        }
        return res;
    }
}
