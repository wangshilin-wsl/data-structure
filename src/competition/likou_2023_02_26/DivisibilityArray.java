package competition.likou_2023_02_26;

import java.util.Arrays;

/*力扣：6368. 找出字符串的可整除数组 显示英文描述
        给你一个下标从 0 开始的字符串 word ，长度为 n ，由从 0 到 9 的数字组成。另给你一个正整数 m 。

        word 的 可整除数组 div  是一个长度为 n 的整数数组，并满足：

        如果 word[0,...,i] 所表示的 数值 能被 m 整除，div[i] = 1
        否则，div[i] = 0
        返回 word 的可整除数组。



        示例 1：

        输入：word = "998244353", m = 3
        输出：[1,1,0,0,0,1,1,0,0]
        解释：仅有 4 个前缀可以被 3 整除："9"、"99"、"998244" 和 "9982443" 。
        示例 2：

        输入：word = "1010", m = 10
        输出：[0,1,0,1]
        解释：仅有 2 个前缀可以被 10 整除："10" 和 "1010" 。


        提示：

        1 <= n <= 105
        word.length == n
        word 由数字 0 到 9 组成
        1 <= m <= 109*/
public class DivisibilityArray {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new DivisibilityArray().divisibilityArray("998244353", 3)));
    }
    public int[] divisibilityArray(String word, int m) {
        //思路，只用判断余数，注意，前面的余数需要*10，因为m最大可能为1e9，所以*10可能会超过int的最大值，得用Long装
        int n = word.length();
        long mod = 0L;
        int[] div = new int[n];
        for (int i = 0; i < n; i++) {
            int num = word.charAt(i) - '0';
            long temp = (mod * 10 + num) % m;
            if (temp == 0) {
                div[i] = 1;
            }
            mod = temp;
        }
        return div;
    }

}
