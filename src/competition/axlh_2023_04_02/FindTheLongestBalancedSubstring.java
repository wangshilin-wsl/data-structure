package competition.axlh_2023_04_02;

/*力扣6362. 最长平衡子字符串 显示英文描述
        通过的用户数0
        尝试过的用户数0
        用户总通过次数0
        用户总提交次数0
        题目难度Easy
        给你一个仅由 0 和 1 组成的二进制字符串 s 。

        如果子字符串中 所有的 0 都在 1 之前 且其中 0 的数量等于 1 的数量，则认为 s 的这个子字符串是平衡子字符串。请注意，空子字符串也视作平衡子字符串。

        返回  s 中最长的平衡子字符串长度。

        子字符串是字符串中的一个连续字符序列。



        示例 1：

        输入：s = "01000111"
        输出：6
        解释：最长的平衡子字符串是 "000111" ，长度为 6 。
        示例 2：

        输入：s = "00111"
        输出：4
        解释：最长的平衡子字符串是 "0011" ，长度为  4 。
        示例 3：

        输入：s = "111"
        输出：0
        解释：除了空子字符串之外不存在其他平衡子字符串，所以答案为 0 。


        提示：

        1 <= s.length <= 50
        '0' <= s[i] <= '1'*/
public class FindTheLongestBalancedSubstring {
    public static void main(String[] args) {
        System.out.println(new FindTheLongestBalancedSubstring().findTheLongestBalancedSubstring("01000111"));
    }
    //暴力法，枚举所有可能
    public int findTheLongestBalancedSubstring(String s) {
        if ("".equals(s)) {
            return 0;
        }
        int n = s.length(), max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (sub(s, i, j)) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }
    public boolean sub(String s, int i, int j) {
        if (((j - i + 1) & 1) == 1) {
            return false;
        }
        int mid = i + j >> 1;
        for (int k = i; k <= mid; k++) {
            if (s.charAt(k) != '0') {
                return false;
            }
        }
        for (int k = mid + 1; k <= j; k++) {
            if (s.charAt(k) != '1') {
                return false;
            }
        }
        return true;
    }
}
