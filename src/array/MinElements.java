package array;

/*力扣：1785. 构成特定和需要添加的最少元素
        给你一个整数数组 nums ，和两个整数 limit 与 goal 。数组 nums 有一条重要属性：abs(nums[i]) <= limit 。

        返回使数组元素总和等于 goal 所需要向数组中添加的 最少元素数量 ，添加元素 不应改变 数组中 abs(nums[i]) <= limit 这一属性。

        注意，如果 x >= 0 ，那么 abs(x) 等于 x ；否则，等于 -x 。



        示例 1：

        输入：nums = [1,-1,1], limit = 3, goal = -4
        输出：2
        解释：可以将 -2 和 -3 添加到数组中，数组的元素总和变为 1 - 1 + 1 - 2 - 3 = -4 。
        示例 2：

        输入：nums = [1,-10,9,1], limit = 100, goal = 0
        输出：1


        提示：

        1 <= nums.length <= 105
        1 <= limit <= 106
        -limit <= nums[i] <= limit
        -10^9 <= goal <= 10^9*/
public class MinElements {
    public static void main(String[] args) {
        System.out.println(new MinElements().minElements(new int[]{1,-1,1}, 3, -1));
    }
    public int minElements(int[] nums, int limit, int goal) {
        //先计算nums的和sum，用long装，看sum是limit的多少倍(使用公式，有点像mysql分页计算总页数的公式)
        long sum = 0L;
        for (int num : nums) {
            sum += num;
        }
        long abs = Math.abs(sum - goal);
        //接下来要计算abs可以分为几个limit
        //当abs可以整除limit，结果为abs/limit
        //当abs补鞥呢整除limit，结果为abs/limit + 1
        //结合以上两种情况 (abs + limit - 1) / limit
        return (int)((abs + limit - 1) / limit);
    }
    public int minElements1(int[] nums, int limit, int goal) {
        //先计算nums的和sum，用long装，看sum是limit的多少倍(while循环)，计算会超时
        long sum = 0L;
        for (int num : nums) {
            sum += num;
        }
        long abs = Math.abs(sum - goal);
        int num = 0;
        while (abs > 0) {
            num++;
            abs-=limit;
        }
        return num;
    }
}
