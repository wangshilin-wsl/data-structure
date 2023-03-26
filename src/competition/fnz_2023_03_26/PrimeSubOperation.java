package competition.fnz_2023_03_26;

/*力扣：6355. 质数减法运算 显示英文描述
        给你一个下标从 0 开始的整数数组 nums ，数组长度为 n 。

        你可以执行无限次下述运算：

        选择一个之前未选过的下标 i ，并选择一个 严格小于 nums[i] 的质数 p ，从 nums[i] 中减去 p 。
        如果你能通过上述运算使得 nums 成为严格递增数组，则返回 true ；否则返回 false 。

        严格递增数组 中的每个元素都严格大于其前面的元素。



        示例 1：

        输入：nums = [4,9,6,10]
        输出：true
        解释：
        在第一次运算中：选择 i = 0 和 p = 3 ，然后从 nums[0] 减去 3 ，nums 变为 [1,9,6,10] 。
        在第二次运算中：选择 i = 1 和 p = 7 ，然后从 nums[1] 减去 7 ，nums 变为 [1,2,6,10] 。
        第二次运算后，nums 按严格递增顺序排序，因此答案为 true 。
        示例 2：

        输入：nums = [6,8,11,12]
        输出：true
        解释：nums 从一开始就按严格递增顺序排序，因此不需要执行任何运算。
        示例 3：

        输入：nums = [5,8,3]
        输出：false
        解释：可以证明，执行运算无法使 nums 按严格递增顺序排序，因此答案是 false 。


        提示：

        1 <= nums.length <= 1000
        1 <= nums[i] <= 1000
        nums.length == n*/
public class PrimeSubOperation {

    public static void main(String[] args) {
        new PrimeSubOperation().primeSubOperation(new int[]{4,9,6,10});
    }

    public boolean primeSubOperation(int[] nums) {
        //思路：因为只能减去一个质数，所以需要从后往前判断。
        //如果遇到比后面大的数字，找到第一个比差大的质数（贪心，减去最小的最好，防止前面的无法减）
        int n = nums.length;
        for (int i = n - 2; i >= 0; i--) {
            int sub = nums[i] - nums[i + 1];
            if (sub >= 0) {
                int opt = opt(sub + 1);
                if (opt >= nums[i]) {
                    return false;
                }
                nums[i] -= opt;
            }
        }
        return true;
    }

    public int opt(int sub) {
        while (true) {
            if (primer(sub)) {
                return sub;
            }
            sub++;
        }
    }

    public boolean primer(int n) {
        if (n == 1) {
            return false;
        }
        for (int i = 2; i <= n / i; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
