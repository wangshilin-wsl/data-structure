package competition.likou_2023_01_01;

/*6196. 将字符串分割成值不超过 K 的子字符串 显示英文描述
        给你一个字符串 s ，它每一位都是 1 到 9 之间的数字组成，同时给你一个整数 k 。

        如果一个字符串 s 的分割满足以下条件，我们称它是一个 好 分割：

        s 中每个数位 恰好 属于一个子字符串。
        每个子字符串的值都小于等于 k 。
        请你返回 s 所有的 好 分割中，子字符串的 最少 数目。如果不存在 s 的 好 分割，返回 -1 。

        注意：

        一个字符串的 值 是这个字符串对应的整数。比方说，"123" 的值为 123 ，"1" 的值是 1 。
        子字符串 是字符串中一段连续的字符序列。


        示例 1：

        输入：s = "165462", k = 60
        输出：4
        解释：我们将字符串分割成子字符串 "16" ，"54" ，"6" 和 "2" 。每个子字符串的值都小于等于 k = 60 。
        不存在小于 4 个子字符串的好分割。
        示例 2：

        输入：s = "238182", k = 5
        输出：-1
        解释：这个字符串不存在好分割。


        提示：

        1 <= s.length <= 105
        s[i] 是 '1' 到 '9' 之间的数字。
        1 <= k <= 109*/
public class MinimumPartition {
    public static void main(String[] args) {
        System.out.println(new MinimumPartition().minimumPartition("165462", 60));
    }

    public int minimumPartition(String s, int k) {
        int n = s.length(), res = 1;
        long dig = 0L;
        for (int i = 0; i < n; i++) {
            int num = s.charAt(i) - '0';
            if (num > k) {
                return -1;
            }
            dig = dig * 10 + num;
            if (dig > k) {
                dig = num;
                res++;
            }
        }
        return res;
    }
}
