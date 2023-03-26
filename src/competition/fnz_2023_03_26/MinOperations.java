package competition.fnz_2023_03_26;

import java.util.*;

/*力扣：6357. 使数组元素全部相等的最少操作次数 显示英文描述
        给你一个正整数数组 nums 。

        同时给你一个长度为 m 的整数数组 queries 。第 i 个查询中，你需要将 nums 中所有元素变成 queries[i] 。你可以执行以下操作 任意 次：

        将数组里一个元素 增大 或者 减小 1 。
        请你返回一个长度为 m 的数组 answer ，其中 answer[i]是将 nums 中所有元素变成 queries[i] 的 最少 操作次数。

        注意，每次查询后，数组变回最开始的值。



        示例 1：

        输入：nums = [3,1,6,8], queries = [1,5]
        输出：[14,10]
        解释：第一个查询，我们可以执行以下操作：
        - 将 nums[0] 减小 2 次，nums = [1,1,6,8] 。
        - 将 nums[2] 减小 5 次，nums = [1,1,1,8] 。
        - 将 nums[3] 减小 7 次，nums = [1,1,1,1] 。
        第一个查询的总操作次数为 2 + 5 + 7 = 14 。
        第二个查询，我们可以执行以下操作：
        - 将 nums[0] 增大 2 次，nums = [5,1,6,8] 。
        - 将 nums[1] 增大 4 次，nums = [5,5,6,8] 。
        - 将 nums[2] 减小 1 次，nums = [5,5,5,8] 。
        - 将 nums[3] 减小 3 次，nums = [5,5,5,5] 。
        第二个查询的总操作次数为 2 + 4 + 1 + 3 = 10 。
        示例 2：

        输入：nums = [2,9,6,3], queries = [10]
        输出：[20]
        解释：我们可以将数组中所有元素都增大到 10 ，总操作次数为 8 + 1 + 4 + 7 = 20 。


        提示：

        n == nums.length
        m == queries.length
        1 <= n, m <= 105
        1 <= nums[i], queries[i] <= 109*/
public class MinOperations {
    public static void main(String[] args) {
        System.out.println(new MinOperations().minOperations(new int[]{3, 1, 6, 8}, new int[]{1, 5}));
    }

    public List<Long> minOperations(int[] nums, int[] queries) {
        //思路：对于query = queries[i],想法直接用（nums的和）-（query*n）（因为都变query所以和为n * query）
        //但是上面思路有问题，有的数字到query是加，有的是减，所以上面思路有问题
        //所以需要以query为分界线，前面的都是加，后面都是减，分两块计算。
        int n = nums.length, m = queries.length;
        long[] pre = new long[n + 1];
        List<Long> answer = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + nums[i];
        }
        for (int i = 0; i < m; i++) {
            int query = queries[i];
            int index = binSearch(nums, query);
            //左边都是小于query的，所以左边都是需要+1的，计算左边需要+1的总数
            long left = (long) query * index - pre[index];
            //右边都是大于query的，所以右边都是需要-1的，计算右边需要-1的总数
            long right = pre[n] - pre[index] - (long) query * (n - index);
            answer.add(left + right);
        }
        return answer;
    }

    public int binSearch(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + r >> 1;
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
}
