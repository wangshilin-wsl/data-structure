package search;

/*力扣：878. 第 N 个神奇数字
        一个正整数如果能被 a 或 b 整除，那么它是神奇的。
        给定三个整数 n , a , b ，返回第 n 个神奇的数字。因为答案可能很大，
        所以返回答案 对 109 + 7 取模 后的值。

        示例 1：
        输入：n = 1, a = 2, b = 3
        输出：2

        示例 2：
        输入：n = 4, a = 2, b = 3
        输出：6

        提示：
        1 <= n <= 10^9
        2 <= a, b <= 4 * 10^4
        */

public class NthMagicalNumber {

    public static void main(String[] args) {
        System.out.println(new NthMagicalNumber().nthMagicalNumber(4, 2, 3));
    }

    public int nthMagicalNumber(int n, int a, int b) {
        //参考：https://leetcode.cn/problems/nth-magical-number/solution/er-fen-da-an-rong-chi-yuan-li-by-endless-9j34/
        //二分法依赖：数字x，x可以整除a,b的个数是n，那么x越大n也一定越大是单调递增的，所以可以用二分法
        //思路，使用二分法来查询（n越大,对应的数字越大），能整除a或者b的数字个数=整除a + 整除b - 既能整除a又能整除b来计算（即a,b的最小公倍数）
        //对于x，可以整除a的个数=x/a
        //对于x可以整除a或者b的个数= x/a + x/b - x/lcm(a,b)     lcm(a,b)求a,b的最小公倍数
        //a,b最小公倍数，lcm(a,b) = a * b /gcd(a,b)      gcd(a,b)求的是a,b的最大公约数
        //计算 gcd(a,b)可以用辗转相除法
        //a,b最大公约数为p,最小公倍数为q，那么存在这样的关系 a * b = p * q
        //下界：0   上界： min(a, b) * n一定包含n个
        //为什么二分循环结束时，得到的一定是一个神奇数字？
        //答：设答案为 x，循环结束时，≤x 的神奇数字有 n 个，而≤x−1 的神奇数字不足 n 个（可结合视频中的红蓝染色来理解）。只有当x是一个神奇数字时，才会出现这种情况。
        //这也同时说明，在二分循环中，我们不能在计算结果恰好等于 n 的时候，直接返回答案，而是要继续二分。
        long mod = (long)1e9 + 7, left = 0L, right = (long) Math.min(a, b) * n;
        //lcm是最小公倍数
        int lcm = a * b / gcd(a, b);
        while (left < right) {
            //除2优化成右移1位
            long mid = (left + right) >> 1;
            //这个地方不能反，不能把==的情况放到下面
            if (count(mid, a, b, lcm) >= n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return (int) (left % mod);
    }
    long count(long x, int a, int b, int lcm) {
        return x / a + x / b - x / lcm;
    }
    //求最大公约数
    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
    /**
     * @methodName nthMagicalNumber1
     * @Author WSL
     * @Description  暴力法，将所有的列举出来，超时了
     * @Date 21:08 2022/11/22
     * @return int
     **/
    public int nthMagicalNumber1(int n, int a, int b) {
        int mod = (int)1e9 + 7;
        int first = a, second = b,res = 0, num1 = 0, num2 = 0;
        for (int i = 0; i < n; i++) {
            if (num1 > num2) {
                res = second;
                second += b;
                if (second >= mod) {
                    second %= mod;
                    num2++;
                }
            } else if (num1 < num2) {
                res = first;
                first += a;
                if (first >= mod) {
                    first %= mod;
                    num1++;
                }
            } else {
                if (first > second) {
                    res = second;
                    second += b;
                    if (second >= mod) {
                        second %= mod;
                        num2++;
                    }
                } else if (first < second) {
                    res = first;
                    first += a;
                    if (first >= mod) {
                        first %= mod;
                        num1++;
                    }
                } else {
                    res = first;
                    first += a;
                    if (first >= mod) {
                        first %= mod;
                        num1++;
                    }
                    second += b;
                    if (second >= mod) {
                        second %= mod;
                        num2++;
                    }
                }
            }

        }
        return res;
    }
}
