package competition.jiaqitouzi_2022_11_27;

/*力扣：6246. 追加字符以获得子序列
        给你两个仅由小写英文字母组成的字符串 s 和 t 。

        现在需要通过向 s 末尾追加字符的方式使 t 变成 s 的一个 子序列 ，返回需要追加的最少字符数。

        子序列是一个可以由其他字符串删除部分（或不删除）字符但不改变剩下字符顺序得到的字符串。



        示例 1：

        输入：s = "coaching", t = "coding"
        输出：4
        解释：向 s 末尾追加字符串 "ding" ，s = "coachingding" 。
        现在，t 是 s ("coachingding") 的一个子序列。
        可以证明向 s 末尾追加任何 3 个字符都无法使 t 成为 s 的一个子序列。
        示例 2：

        输入：s = "abcde", t = "a"
        输出：0
        解释：t 已经是 s ("abcde") 的一个子序列。
        示例 3：

        输入：s = "z", t = "abcde"
        输出：5
        解释：向 s 末尾追加字符串 "abcde" ，s = "zabcde" 。
        现在，t 是 s ("zabcde") 的一个子序列。
        可以证明向 s 末尾追加任何 4 个字符都无法使 t 成为 s 的一个子序列。


        提示：

        1 <= s.length, t.length <= 105
        s 和 t 仅由小写英文字母组成*/
public class AppendCharacters {
    public static void main(String[] args) {
        System.out.println(new AppendCharacters().appendCharacters("coaching", "coding"));
    }

    public int appendCharacters(String s, String t) {
        int i = 0, j = 0, n1 = s.length(), n2 = t.length();
        while (i < n1 && j < n2) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                i++;
            }
        }
        return n2 - j;
    }
}
