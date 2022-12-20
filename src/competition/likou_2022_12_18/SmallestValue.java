package competition.likou_2022_12_18;

import java.util.ArrayList;
import java.util.List;

/*力扣：6266. 使用质因数之和替换后可以取到的最小值
        给你一个正整数 n 。
        请你将 n 的值替换为 n 的 质因数 之和，重复这一过程。
        注意，如果 n 能够被某个质因数多次整除，则在求和时，应当包含这个质因数同样次数。
        返回 n 可以取到的最小值。

        示例 1：

        输入：n = 15
        输出：5
        解释：最开始，n = 15 。
        15 = 3 * 5 ，所以 n 替换为 3 + 5 = 8 。
        8 = 2 * 2 * 2 ，所以 n 替换为 2 + 2 + 2 = 6 。
        6 = 2 * 3 ，所以 n 替换为 2 + 3 = 5 。
        5 是 n 可以取到的最小值。
        示例 2：

        输入：n = 3
        输出：3
        解释：最开始，n = 3 。
        3 是 n 可以取到的最小值。


        提示：

        2 <= n <= 105*/
public class SmallestValue {
    //思路，先将所有<=100000的质数找出来，在循环计算
    int N = 100010;

    public static void main(String[] args) {
        System.out.println(new SmallestValue().smallestValue(99955));
    }

    public int smallestValue(int n) {
        //得到所有100000以内的质数
        List<Integer> primers = new ArrayList<>();
        for (int i = 2; i < N; i++) {
            if (primer(i)) {
                primers.add(i);
            }
        }
        //循环计算
        int t = n;
        while (true) {
            int sum = 0;
            for (int i = 0; i < primers.size(); i++) {
                int x = primers.get(i);
                if (x > t) {
                    break;
                }
                int v = t;
                while (v % x == 0) {
                    sum += x;
                    v /= x;
                }
            }
            if (sum == t) {
                return sum;
            } else {
                t = sum;
            }
        }
    }
    public boolean primer(int n) {
        for (int i = 2; i <= n / i; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
