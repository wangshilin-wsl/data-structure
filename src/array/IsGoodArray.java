package array;
/*力扣：1250. 检查「好数组」
        给你一个正整数数组 nums，你需要从中任选一些子集，然后将子集中每一个数乘以一个 任意整数，并求出他们的和。

        假如该和结果为 1，那么原数组就是一个「好数组」，则返回 True；否则请返回 False。



        示例 1：

        输入：nums = [12,5,7,23]
        输出：true
        解释：挑选数字 5 和 7。
        5*3 + 7*(-2) = 1
        示例 2：

        输入：nums = [29,6,10]
        输出：true
        解释：挑选数字 29, 6 和 10。
        29*1 + 6*(-3) + 10*(-1) = 1
        示例 3：

        输入：nums = [3,6]
        输出：false


        提示：

        1 <= nums.length <= 10^5
        1 <= nums[i] <= 10^9*/
public class IsGoodArray {
     public boolean isGoodArray(int[] nums) {
        //裴蜀定理：裴蜀定理（或贝祖定理）得名于法国数学家艾蒂安·裴蜀，说明了对任何整数a、b和它们的最大公约数d，关于未知数x和y的线性不定方程（称为裴蜀等式）：若a,b是整数,且gcd(a,b)=d，那么对于任意的整数x,y,ax+by都一定是d的倍数，特别地，一定存在整数x,y，使ax+by=d成立。
        //它的一个重要推论是：a,b互质的充分必要条件是存在整数x,y使ax+by=1.
        //参考：https://leetcode.cn/problems/check-if-it-is-a-good-array/solution/bi-zhao-yan-jing-xue-shu-li-hua-bu-zhi-d-0gj7/
        //查看nums的所有元素的最大公约数是否是1
        int div = nums[0];
        for (int num : nums) {
            div = gcd(num, div);
            //因为可以在数组中任选几个数，则中途遇到了最大公约数为1就可以退出的
            if (div == 1) {
                return true;
            }
        }
        return false;
    }
    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}