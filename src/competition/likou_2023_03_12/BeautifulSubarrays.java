package competition.likou_2023_03_12;

import java.util.HashMap;
import java.util.Map;

/*力扣：6317. 统计美丽子数组数目 显示英文描述
        给你一个下标从 0 开始的整数数组nums 。每次操作中，你可以：

        选择两个满足 0 <= i, j < nums.length 的不同下标 i 和 j 。
        选择一个非负整数 k ，满足 nums[i] 和 nums[j] 在二进制下的第 k 位（下标编号从 0 开始）是 1 。
        将 nums[i] 和 nums[j] 都减去 2k 。
        如果一个子数组内执行上述操作若干次后，该子数组可以变成一个全为 0 的数组，那么我们称它是一个 美丽 的子数组。

        请你返回数组 nums 中 美丽子数组 的数目。

        子数组是一个数组中一段连续 非空 的元素序列。



        示例 1：

        输入：nums = [4,3,1,2,4]
        输出：2
        解释：nums 中有 2 个美丽子数组：[4,3,1,2,4] 和 [4,3,1,2,4] 。
        - 按照下述步骤，我们可以将子数组 [3,1,2] 中所有元素变成 0 ：
        - 选择 [3, 1, 2] 和 k = 1 。将 2 个数字都减去 21 ，子数组变成 [1, 1, 0] 。
        - 选择 [1, 1, 0] 和 k = 0 。将 2 个数字都减去 20 ，子数组变成 [0, 0, 0] 。
        - 按照下述步骤，我们可以将子数组 [4,3,1,2,4] 中所有元素变成 0 ：
        - 选择 [4, 3, 1, 2, 4] 和 k = 2 。将 2 个数字都减去 22 ，子数组变成 [0, 3, 1, 2, 0] 。
        - 选择 [0, 3, 1, 2, 0] 和 k = 0 。将 2 个数字都减去 20 ，子数组变成 [0, 2, 0, 2, 0] 。
        - 选择 [0, 2, 0, 2, 0] 和 k = 1 。将 2 个数字都减去 21 ，子数组变成 [0, 0, 0, 0, 0] 。
        示例 2：

        输入：nums = [1,10,4]
        输出：0
        解释：nums 中没有任何美丽子数组。


        提示：

        1 <= nums.length <= 105
        0 <= nums[i] <= 106*/
public class BeautifulSubarrays {
    public static void main(String[] args) {
        System.out.println(new BeautifulSubarrays().beautifulSubarrays(new int[]{4, 3, 1, 2, 4}));
    }

    public long beautifulSubarrays(int[] nums) {
        //思路：前缀按位异或
        //由题意可知，如果子数组的按位异或为0就是一个美丽的数组
        //使用pre记录前缀按位异或，i位置的按位异或为pre,则寻找之前异或和也为pre的位置j
        //则该段子数组[j,i]按位异或和则为0，就是一个美丽数组 i>j
        //[0,j]异或和为nums[0] ^ nums[1]...nums[j]=pre[i]
        //[0,i]异或和为nums[0] ^ nums[1]...nums[i]=pre[j]
        //pre[i]^pre[j]=(nums[0] ^ nums[1]...nums[j]) ^ (nums[0] ^ nums[1]...nums[i])
        //由a^b=c,则a^c=b,由a^a=0可知pre[i]^pre[j]=(nums[0] ^ nums[1]...nums[j]) ^ (nums[0] ^ nums[1]...nums[i])=nums[j]^nums[j+1]...nums[i]
        //而nums[j]^nums[j+1]...nums[i]就是[j, i]异或和，则是子数组的异或和，则只要pre[i]^pre[j]=0就是[j,i]的异或和为0则是一个美丽子数组
        //由于某个pre可能出现多次，所以使用map记录，一次遍历记录所有的前缀异或和
        long sum = 0L, pre = 0L;
        Map<Long, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(pre, map.getOrDefault(pre, 0) + 1);
            pre ^= num;
            if (map.containsKey(pre)) {
                sum += map.get(pre);
            }
        }
        return sum;
    }
}
