package dynamic_programming;

/*力扣：940. 不同的子序列 II
        给定一个字符串 s，计算 s 的 不同非空子序列 的个数。因为结果可能很大，
        所以返回答案需要对 10^9 + 7 取余 。
        字符串的 子序列 是经由原字符串删除一些（也可能不删除）字符但不改变剩余字符相对位置的一个新字符串。
        例如，"ace" 是 "abcde" 的一个子序列，但 "aec" 不是。

        示例 1：
        输入：s = "abc"
        输出：7
        解释：7 个不同的子序列分别是 "a", "b", "c", "ab", "ac", "bc", 以及 "abc"。

        示例 2：
        输入：s = "aba"
        输出：6
        解释：6 个不同的子序列分别是 "a", "b", "ab", "ba", "aa" 以及 "aba"。

        示例 3：
        输入：s = "aaa"
        输出：3
        解释：3 个不同的子序列分别是 "a", "aa" 以及 "aaa"。

        提示：
        1 <= s.length <= 2000
        s 仅由小写英文字母组成

        思路：为了方便，我们令 s 下标从 11 开始，定义 f[i][j]f[i][j] 为考虑前 ii 个字符，
        且结尾字符为 jj 的不同子序列的个数，其中 jj 的范围为 [0, 25][0,25] 代指小写字符 a-z。
        我们有显而易见的初始化条件 f[0][X] = 0f[0][X]=0，最终答案为 \sum_{i = 0}^{25}f[n][i]∑
        i=025

 f[n][i]。

不失一般性考虑 f[i][j]f[i][j] 该如何转移，根据 s[i]s[i] 是否为 jj 进行分情况讨论：

s[i] \neq js[i]


 =j : 由于状态定义限定了结尾字符必须是 jj，因而 s[i]s[i] 必然不会用到，此时有：
f[i][j] = f[i - 1][j]
f[i][j]=f[i−1][j]

s[i] = js[i]=j : 此时 s[i]s[i] 可作为结尾元素，同时由于我们统计的是「不同」的子序列个数，因而「以 jj 结尾的子序列方案数」与「以 s[i]s[i] 结尾的子序列方案数」完全等价。
对于以 s[i]s[i] 作为子序列结尾字符的方案数，容易想到其方案数等于「s[i]s[i] 单独作为子序列」+「s[i]s[i] 拼接在其余子序列后面形成新子序列」，即有：
f[i][j] = 1 + \sum_{k = 0}^{25} f[i - 1][k]
f[i][j]=1+
k=0
∑
25

 f[i−1][k]

作者：AC_OIer
链接：https://leetcode.cn/problems/distinct-subsequences-ii/solution/by-ac_oier-ph94/
       */
public class DistinctSubseqII {
    //动态规划
    int mod = (int)(1e9 + 7);

    public static void main(String[] args) {
        System.out.println(new DistinctSubseqII().distinctSubseqII("abc"));
    }

    public int distinctSubseqII(String s) {
        int length = s.length();
        //dp[i][j]表示考虑前i个字符，且以字符为j的不同子序列个数，其中j为 0~25(对应小写字符a~z)
        int dp[][] = new int[length + 1][26];

        //动态规划的遍历
        for (int i = 1; i <= length; i++) {
            int ch = s.charAt(i - 1) - 'a';
            //填充以26个字母结尾的子序列个数
            for (int j = 0; j < 26; j++) {
                if (ch == j){
                    //当j==最后一个字母的时候，前i个以j结尾=前i-1个以j(0~26)结尾+以s[i]为子序列的个数
                    int val = 1;
                    for (int k = 0; k < 26; k++) {
                        val = (val + dp[i - 1][k]) % mod;
                    }
                    dp[i][j] = val;
                }else {
                    //当j!=最后一个字母的时候，前i个以j结尾=前i-1个以j结尾
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        int res = 0;
        for (int i = 0; i < 26; i++) {
            res = (res + dp[length][i]) % mod;
        }
        return res;
    }
    //优化空间的动态规划
    public int distinctSubseqIIPlus(String s) {
        //因为dp[i]只依赖dp[i-1]，所有可以用一个int[26]数组存储
        int dp[] = new int[26];
        int length = s.length();

        //动态规划的遍历
        for (int i = 1; i <= length; i++) {
            int ch = s.charAt(i - 1) - 'a';
            //填充以26个字母结尾的子序列个数
            for (int j = 0; j < 26; j++) {
                if (ch == j){
                    //当j==最后一个字母的时候，前i个以j结尾=前i-1个以j(0~26)结尾+以s[i]为子序列的个数
                    int val = 1;
                    for (int k = 0; k < 26; k++) {
                        val = (val + dp[k]) % mod;
                    }
                    dp[j] = val;
                }else {
                    //当j!=最后一个字母的时候，前i个以j结尾=前i-1个以j结尾
                    dp[j] = dp[j];
                }
            }
        }

        int res = 0;
        for (int i = 0; i < 26; i++) {
            res = (res + dp[i]) % mod;
        }
        return res;
    }
}
