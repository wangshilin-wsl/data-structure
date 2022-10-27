package deque;

import java.util.ArrayDeque;
import java.util.Deque;

/*力扣：862. 和至少为 K 的最短子数组
        给你一个整数数组 nums 和一个整数 k ，
        找出 nums 中和至少为 k 的 最短非空子数组 ，
        并返回该子数组的长度。如果不存在这样的 子数组 ，返回 -1 。
        子数组 是数组中 连续 的一部分。

        示例 1：
        输入：nums = [1], k = 1
        输出：1

        示例 2：
        输入：nums = [1,2], k = 4
        输出：-1

        示例 3：
        输入：nums = [2,-1,2], k = 3
        输出：3
        提示：

        1 <= nums.length <= 105
        -105 <= nums[i] <= 105
        1 <= k <= 109
        思路：先求出所有的前缀和，利用前缀和之差来表示子数组的和。
        暴力法，双层循环 遍历，超时。

        //使用双端队列来存储已经遍历过得节点s[i]-s[j]>=k
         //优化1：如果s[i]-s[j]>=k那么就算是i往后走，也只会让元素增多

         //优化2：如果s[i] <= s[j]就算后面有值使得子数组和s[x]-s[j]>=k，但是s[x]-s[j]一定是>=k
          //并且以i为左端点的子数组长度更短
          //但是需要注意这个地方需要从靠近i的地方往前找，也就是队尾位置往前找，上一个while保证了curr > preSum[queue.peekFirst()]，所以从队尾开始
        */
public class ShortestSubarray {
    public static void main(String[] args) {
        System.out.println(new ShortestSubarray().shortestSubarray(new int[]{2, -1, 2}, 3));
    }
    /**
     * @methodName shortestSubarray1
     * @Author WSL
     * @Description  使用前缀和并双层for暴力解决，但是会超时
     * @Date 18:26 2022/10/27
     * @Param nums
     * @return int
     **/
    public int shortestSubarray1(int[] nums, int k) {
        int length = nums.length;
        int[] preSum = new int[length + 1];
        for (int i = 0; i < length; i++) {
            preSum[i + 1] = nums[i] + preSum[i];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < length + 1; i++) {
            for (int j = i + 1; j < length + 1; j++) {
                int temp = preSum[j] - preSum[i];
                if (temp >= k){
                    min = Math.min(min, j - i);
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    /**
     * @methodName shortestSubarray
     * @Author WSL
     * @Description  优化，使用双端队列
     * @Date 18:27 2022/10/27
     * @Param nums
     * @return int
     **/
    public int shortestSubarray(int[] nums, int k) {
        int length = nums.length;
        long[] preSum = new long[length + 1];
        for (int i = 0; i < length; i++) {
            preSum[i + 1] = nums[i] + preSum[i];
        }
        int min = Integer.MAX_VALUE;
        //使用双端队列来存储已经遍历过得节点s[i]-s[j]>=k
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < length + 1; i++) {
            long curr = preSum[i];
            //优化1：如果s[i]-s[j]>=k那么就算是i往后走，也只会让元素增多
            while (!queue.isEmpty() && (curr - preSum[queue.peekFirst()]) >= k) {
                min = Math.min(min, i - queue.pollFirst());
            }
            //优化2：如果s[i] <= s[j]就算后面有值使得子数组和s[x]-s[j]>=k，但是s[x]-s[j]一定是>=k
            //并且以i为左端点的子数组长度更短
            //但是需要注意这个地方需要从靠近i的地方往前找，也就是队尾位置往前找，上一个while保证了curr > preSum[queue.peekFirst()]，所以从队尾开始
            while (!queue.isEmpty() && curr <= preSum[queue.peekLast()]){
                queue.pollLast();
            }
            queue.offerLast(i);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
