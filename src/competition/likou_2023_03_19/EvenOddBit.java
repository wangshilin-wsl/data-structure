package competition.likou_2023_03_19;

import java.util.Arrays;

/*力扣：6319. 奇偶位数 显示英文描述
        给你一个 正 整数 n 。

        用 even 表示在 n 的二进制形式（下标从 0 开始）中值为 1 的偶数下标的个数。

        用 odd 表示在 n 的二进制形式（下标从 0 开始）中值为 1 的奇数下标的个数。

        返回整数数组 answer ，其中 answer = [even, odd] 。



        示例 1：

        输入：n = 17
        输出：[2,0]
        解释：17 的二进制形式是 10001 。
        下标 0 和 下标 4 对应的值为 1 。
        共有 2 个偶数下标，0 个奇数下标。
        示例 2：

        输入：n = 2
        输出：[0,1]
        解释：2 的二进制形式是 10 。
        下标 1 对应的值为 1 。
        共有 0 个偶数下标，1 个奇数下标。


        提示：

        1 <= n <= 1000*/
public class EvenOddBit {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new EvenOddBit().evenOddBit(17)));
    }

    public int[] evenOddBit(int n) {
        int even = 0, odd = 0, index = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                int temp = (index & 1) == 1 ? odd++ : even++;
            }
            index++;
            n >>= 1;
        }
        return new int[]{even, odd};
    }
}
