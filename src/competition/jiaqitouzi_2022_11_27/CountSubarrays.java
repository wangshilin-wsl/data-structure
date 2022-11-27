package competition.jiaqitouzi_2022_11_27;

import java.util.HashMap;
import java.util.Map;

/*力扣：6248. 统计中位数为 K 的子数组
        给你一个长度为 n 的数组 nums ，该数组由从 1 到 n 的 不同 整数组成。另给你一个正整数 k 。

        统计并返回 num 中的 中位数 等于 k 的非空子数组的数目。

        注意：

        数组的中位数是按 递增 顺序排列后位于 中间 的那个元素，如果数组长度为偶数，则中位数是位于中间靠 左 的那个元素。
        例如，[2,3,1,4] 的中位数是 2 ，[8,4,3,5,1] 的中位数是 4 。
        子数组是数组中的一个连续部分。


        示例 1：

        输入：nums = [3,2,1,4,5], k = 4
        输出：3
        解释：中位数等于 4 的子数组有：[4]、[4,5] 和 [1,4,5] 。
        示例 2：

        输入：nums = [2,3,1], k = 3
        输出：1
        解释：[3] 是唯一一个中位数等于 3 的子数组。


        提示：

        n == nums.length
        1 <= n <= 105
        1 <= nums[i], k <= n
        nums 中的整数互不相同*/
public class CountSubarrays {
    public static void main(String[] args) {
        System.out.println(new CountSubarrays().countSubarrays(new int[]{3, 2, 1, 4, 5}, 4));
    }
    //前缀和+map，前缀和为1或者0的
     /*提示 1
   把比 k 大的数变成 11，比 k 小的数变成 −1，k 变成 0。

   提示 2
   设 k 的下标为 pos，k 为子数组的中位数，等价于：

   子数组包含下标 pos；
   子数组的元素和等于 0 或 1。

   作者：endlesscheng
   链接：https://leetcode.cn/problems/count-subarrays-with-median-k/solution/deng-jie-zhuan-huan-pythonjavacgo-by-end-5w11/*/
    public int countSubarrays(int[] nums, int k) {
        int index = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == k) {
                index = i;
                break;
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        for (int i = index - 1; i >= 0; i--) {
            sum = nums[i] > k ? sum + 1 : sum - 1;
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        //index左边符合的总数
        int res = map.get(0) + map.getOrDefault(1, 0);
        sum = 0;
        for (int i = index + 1; i < n; i++) {
            sum = nums[i] > k ? sum + 1 : sum - 1;
            //结果为1、0,目前和为sum，则为  1- res 、-res
            //因为map.put(0, 1);只包含k的情况算进去了，所以下面相当于计算了比i小的所有情况
            res += map.getOrDefault(-sum, 0) + map.getOrDefault(1 - sum, 0);
        }
        return res;
    }


    public int countSubarrays1(int[] nums, int k) {
        int res = 0, index = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == k) {
                index = i;
                break;
            }
        }
        int lt = 0, gt = 0, eq = 0;
        for (int i = index; i >= 0; i--) {
            if (nums[i] > k) {
                gt++;
            } else if (nums[i] < k) {
                lt++;
            } else {
                eq++;
            }
            res += count(lt, gt, index - i + 1);
            int lt1 = lt, gt1 = gt, eq1 = eq;
            for (int j = index + 1; j < n; j++) {
                if (nums[j] > k) {
                    gt1++;
                } else if (nums[j] < k) {
                    lt1++;
                } else {
                    eq1++;
                }
                if (eq1 == 0) {
                    continue;
                }
                res += count(lt1, gt1, j - i + 1);
            }
        }
        return res;
    }
    public int count(int lt, int gt, int sum) {
        if ((sum & 1) == 1 && lt == gt) {
            //奇数
            return 1;
        }
        if ((sum & 1) == 0 && lt + 1 == gt) {
            //奇数
            return 1;
        }
        return 0;
    }
}
