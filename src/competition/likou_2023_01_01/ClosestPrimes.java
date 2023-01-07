package competition.likou_2023_01_01;

import java.util.ArrayList;
import java.util.List;

/*6280. 范围内最接近的两个质数 显示英文描述
        给你两个正整数 left 和 right ，请你找到两个整数 num1 和 num2 ，它们满足：

        left <= nums1 < nums2 <= right  。
        nums1 和 nums2 都是 质数 。
        nums2 - nums1 是满足上述条件的质数对中的 最小值 。
        请你返回正整数数组 ans = [nums1, nums2] 。如果有多个整数对满足上述条件，请你返回 nums1 最小的质数对。如果不存在符合题意的质数对，请你返回 [-1, -1] 。

        如果一个整数大于 1 ，且只能被 1 和它自己整除，那么它是一个质数。



        示例 1：

        输入：left = 10, right = 19
        输出：[11,13]
        解释：10 到 19 之间的质数为 11 ，13 ，17 和 19 。
        质数对的最小差值是 2 ，[11,13] 和 [17,19] 都可以得到最小差值。
        由于 11 比 17 小，我们返回第一个质数对。
        示例 2：

        输入：left = 4, right = 6
        输出：[-1,-1]
        解释：给定范围内只有一个质数，所以题目条件无法被满足。


        提示：

        1 <= left <= right <= 106*/
public class ClosestPrimes {
    public static void main(String[] args) {
        System.out.println(new ClosestPrimes().closestPrimes(10, 19));
    }
    public int[] closestPrimes(int left, int right) {
        //得到所有100000以内的质数
        List<Integer> primers = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (primer(i)) {
                primers.add(i);
            }
        }
        if (primers.size() <= 1) {
            return new int[]{-1, -1};
        }
        int n = primers.size(), min = Integer.MAX_VALUE;
        int[] res = new int[2];
        for (int i = 0; i < n - 1; i++) {
            int temp = primers.get(i + 1) - primers.get(i);
            if (temp < min) {
                min = temp;
                res[0] = primers.get(i);
                res[1] = primers.get(i + 1);

            }
        }
        return res;
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
