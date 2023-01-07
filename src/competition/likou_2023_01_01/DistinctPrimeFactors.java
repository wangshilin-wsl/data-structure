package competition.likou_2023_01_01;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*6279. 数组乘积中的不同质因数数目 显示英文描述
        给你一个正整数数组 nums ，对 nums 所有元素求积之后，找出并返回乘积中 不同质因数 的数目。

        注意：

        质数 是指大于 1 且仅能被 1 及自身整除的数字。
        如果 val2 / val1 是一个整数，则整数 val1 是另一个整数 val2 的一个因数。


        示例 1：

        输入：nums = [2,4,3,7,10,6]
        输出：4
        解释：
        nums 中所有元素的乘积是：2 * 4 * 3 * 7 * 10 * 6 = 10080 = 25 * 32 * 5 * 7 。
        共有 4 个不同的质因数，所以返回 4 。
        示例 2：

        输入：nums = [2,4,8,16]
        输出：1
        解释：
        nums 中所有元素的乘积是：2 * 4 * 8 * 16 = 1024 = 210 。
        共有 1 个不同的质因数，所以返回 1 。


        提示：

        1 <= nums.length <= 104
        2 <= nums[i] <= 1000*/
public class DistinctPrimeFactors {
    int N = 1010;

    public static void main(String[] args) {
        System.out.println(new DistinctPrimeFactors().distinctPrimeFactors(new int[]{2,4,8,16}));
    }

    public int distinctPrimeFactors(int[] nums) {
        //得到所有100000以内的质数
        Set<Integer> res = new HashSet<>();
        List<Integer> primers = new ArrayList<>();
        Set<Integer> primersSet = new HashSet<>(primers);
        for (int i = 2; i < N; i++) {
            if (primer(i)) {
                primers.add(i);
            }
        }
        for (int num : nums) {
            if (primersSet.contains(num)) {
                res.add(num);
                continue;
            }
            for (int i = 0; i < primers.size(); i++) {
                int pri = primers.get(i);
                if (pri > num) {
                    break;
                }
                while (num % pri == 0) {
                    res.add(pri);
                    num /= pri;
                }
            }

        }

        return res.size();
    }
    public boolean primer(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
