package array;

/*力扣：1802. 有界数组中指定下标处的最大值
        给你三个正整数 n、index 和 maxSum 。你需要构造一个同时满足下述所有条件的数组 nums（下标 从 0 开始 计数）：

        nums.length == n
        nums[i] 是 正整数 ，其中 0 <= i < n
abs(nums[i] - nums[i+1]) <= 1 ，其中 0 <= i < n-1
        nums 中所有元素之和不超过 maxSum
        nums[index] 的值被 最大化
        返回你所构造的数组中的 nums[index] 。

        注意：abs(x) 等于 x 的前提是 x >= 0 ；否则，abs(x) 等于 -x 。



        示例 1：

        输入：n = 4, index = 2,  maxSum = 6
        输出：2
        解释：数组 [1,1,2,1] 和 [1,2,2,1] 满足所有条件。不存在其他在指定下标处具有更大值的有效数组。
        示例 2：

        输入：n = 6, index = 1,  maxSum = 10
        输出：3


        提示：

        1 <= n <= maxSum <= 109
        0 <= index < n*/
public class MaxValue {
    public static void main(String[] args) {
        System.out.println(new MaxValue().maxValue(4, 2, 6));
    }
    public int maxValue(int n, int index, int maxSum) {
        //先把所有位置都置为1，因为绝对值不能超过1，每次从index位置往外num范围内都加1
        int avg = 1, mod = maxSum - n, left = index, right = n - index - 1, num = 0;
        while (mod > 0) {
            //已经到达数组边界，每次都需要整个数组+1，特殊判断
            if (num >= left && num >= right) {
                num += mod / n;
                mod = 0;
                break;
            }
            mod = mod - 1 - Math.min(num, right) - Math.min(num, left);
            num++;
        }
        return avg + num + (mod == 0 ? 0 : -1);
    }
}
