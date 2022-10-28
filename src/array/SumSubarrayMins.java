package array;

/*力扣：907. 子数组的最小值之和
        给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。

        由于答案可能很大，因此 返回答案模 10^9 + 7 。



        示例 1：

        输入：arr = [3,1,2,4]
        输出：17
        解释：
        子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
        最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
        示例 2：

        输入：arr = [11,81,94,43,3]
        输出：444


        提示：

        1 <= arr.length <= 3 * 104
        1 <= arr[i] <= 3 * 104*/

import java.util.Stack;

public class SumSubarrayMins {
    public static void main(String[] args) {
        System.out.println(new SumSubarrayMins().sumSubarrayMins(new int[]{11,81,94,43,3}));
    }
    /**
     * @methodName sumSubarrayMins
     * @Author WSL
     * @Description  使用动态规划，n^2复杂度，部分超时
     * @Date 18:42 2022/10/28
     * @Param arr
     * @return int
     **/
    public int sumSubarrayMins1(int[] arr) {
        int mod = (int) (1e9 + 7);
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int dp = arr[i];
            sum += dp;
            sum %= mod;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < dp) {
                    dp = arr[j];
                }
                sum += dp;
                sum %= mod;
            }
        }
        return sum;
    }
    /**
     * @Description 单调栈+贡献值  i位置的元素贡献值，以arr[i]为最小元素的子数组个数 * arr[i]，所以往前找第一个小于它的，往后找第一个小于它的，找的过程可以使用单调栈，假如以arr[i]最小的数组的左边界为left,
     * 右边界为right,那么他们能形成子数组的个数为(i - left[i] + 1) * (right[i] - i + 1),即i位置的贡献值为(i - left[i] + 1) * (right[i] - i + 1) * arr[i]
     * 最小值是在一段连续数字中被筛选出来的，也就是说每个最小值都有一定的辐射范围。假设给定数组A=[3,1,2,4,1]，在一段连续数字3、1、2、4、1中，只要其中一段数字包含1，那么这段数字的最小值肯定为1，
     * 例如[3,1,2,4,1]、[3,1,2,4]、[3,1,2]、[1,2]等最小值都为1，我们把这叫做元素1的辐射范围。
     * 从动画中可以看到下标0的元素3辐射范围为[3]，下标1的元素1辐射范围为[3,1,2,4,1]，下标2的元素2辐射范围为[2,4]，下标3的元素4辐射范围为[4]，下标4的元素1辐射范围为[2,4,1]。
     * 每个元素E=A[i]的辐射范围都是一个连续数组，这个辐射范围内产生的所有子数组最小值都为E，因此E在每个子数组中对答案的贡献值都为E。如果这个辐射范围内的子数组有n个，那么总贡献值为n*E。
     * 那么这个辐射范围内能产生多少个子数组呢？我们枚举一下能产生多少个不同的左右边界对即可。假设辐射范围的左边界为left，右边界为right，元素E的下标为i，那么子数组的左边界应该在[left,i]中选取，
     * 子数组的右边界应该在[i,right]中选取。因此子数组个数为(i - left + 1) * (right - i + 1)(i−left+1)∗(right−i+1)，也就是说元素A[i]对答案的总贡献值为A[i]*(i - left + 1) * (right - i + 1)A[i]∗(i−left+1)∗(right−i+1)。
     * 那么我们只要计算出每个元素的贡献值，然后求和就好了。从上面可以看出求贡献值的话，i和A[i]已知，而关键在于确定辐射范围（也就是求左边界left和右边界right）。
     * 如何求取辐射范围呢？元素E是这个辐射范围的最小值，那么当从元素E的下标i向外扩展时，如果发现某个元素比E大，那么必定属于E的辐射范围，而如果某个元素比E小，那么肯定不属于这个辐射范围的（因为E是最小值，整个范围内不应该有比E更小的数）。
     * 因此只要我们向左\U0001f448找到第一个比A[i]小的数A[left]以及向右\U0001f449找到第一个比E小的数A[right]，就可以确定E的辐射范围为A[left+1:right]。这就叫做下一个更小/更大的数问题。解决这类问题的通用解法即为单调栈。
     * 下面我们具体整理一下求解思路，分为三步：
     * 利用单调栈向左找到第一个比A[i]小的数A[left]（遍历顺序为0->n-1)，也就是E辐射范围的左边界；
     * 利用单调栈向右找到第一个比A[i]小的数A[right]（遍历顺序为n-1->0)，也就是E辐射范围的右边界；
     * 将每个元素的贡献值求和得到最终答案，链接：https://leetcode.cn/problems/sum-of-subarray-minimums/solution/xiao-bai-lang-dong-hua-xiang-jie-bao-zhe-489q/
     * @name sumSubarrayMins
     * @param arr
     * @return int
     * @throws
     **/
    public int sumSubarrayMins(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int mod = (int) (1e9 + 7), n = arr.length;
        Stack<Integer> stack = new Stack<>();
        // 每个元素辐射范围的左边界
        int[] left = new int[n];
        // 每个元素辐射范围的右边界
        int[] right = new int[n];

        //找到左边界，单调递增栈
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        //清空栈
        stack.clear();
        //找到右边界，单调递减栈
        for (int i = n - 1; i >= 0 ; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        //计算对于i来说，(i - left[i] + 1) * (right[i] - i + 1) * arr[i]这个是i位置的贡献值
        //但是由于上方填充边界的时候其实是从-1，n，并且边界都是向外+1了的所以不用减1
        //单调栈+贡献值，不过小心long强转的问题。 sum = (sum + (long) (i - left[i]) * (right[i] - i) * arr[i]) % mod;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum = (sum + (long) (i - left[i]) * (right[i] - i) * arr[i]) % mod;
        }
        return (int)sum;
    }
}
