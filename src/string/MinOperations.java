package string;

/*力扣：1758. 生成交替二进制字符串的最少操作数
        给你一个仅由字符 '0' 和 '1' 组成的字符串 s 。一步操作中，你可以将任一 '0' 变成 '1' ，或者将 '1' 变成 '0' 。

        交替字符串 定义为：如果字符串中不存在相邻两个字符相等的情况，那么该字符串就是交替字符串。例如，字符串 "010" 是交替字符串，而字符串 "0100" 不是。

        返回使 s 变成 交替字符串 所需的 最少 操作数。



        示例 1：

        输入：s = "0100"
        输出：1
        解释：如果将最后一个字符变为 '1' ，s 就变成 "0101" ，即符合交替字符串定义。
        示例 2：

        输入：s = "10"
        输出：0
        解释：s 已经是交替字符串。
        示例 3：

        输入：s = "1111"
        输出：2
        解释：需要 2 步操作得到 "0101" 或 "1010" 。


        提示：

        1 <= s.length <= 104
        s[i] 是 '0' 或 '1'*/
public class MinOperations {
    public static void main(String[] args) {
        System.out.println(new MinOperations().minOperations("0100"));
    }
    //模拟，要么是0开头，要么是1开头，他们两个动加起来的次数=s的长度
    public int minOperations(String s) {
        int n = s.length(), res = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch != ('0' + (i & 1))) {
                res++;
            }
        }
        return Math.min(res, n - res);
    }

    //动态规划
    public int minOperations1(String s) {
        int n = s.length();
        //dp[i][0]表示前i个，并且i不变的最小次数
        //dp[i][1]表示前i个，并且i变的最小次数
        int[][] dp = new int[n][2];
        dp[0][1] = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                dp[i][0] = dp[i -1][1];
                dp[i][1] = dp[i -1][0] + 1;
            } else {
                dp[i][0] = dp[i -1][0];
                dp[i][1] = dp[i -1][1] + 1;
            }
        }
        return Math.min(dp[n - 1][0], dp[n - 1][1]);
    }
}
