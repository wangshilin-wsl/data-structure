package string;

import java.util.ArrayList;
import java.util.List;

/*力扣：784. 字母大小写全排列
        给定一个字符串 s ，通过将字符串 s 中的每个字母转变大小写，我们可以获得一个新的字符串。
        返回 所有可能得到的字符串集合 。以 任意顺序 返回输出。

        示例 1：
        输入：s = "a1b2"
        输出：["a1b2", "a1B2", "A1b2", "A1B2"]

        示例 2:
        输入: s = "3z4"
        输出: ["3z4","3Z4"]

        提示:
        1 <= s.length <= 12
        s 由小写英文字母、大写英文字母和数字组成*/
public class LetterCasePermutation {
    /**
     * @methodName letterCasePermutation
     * @Author WSL
     * @Description  使用深度遍历我们发现大写字符与其对应的小写字符的 ASCII 的差为 32，32 这个值如果敏感的话，它是 2^5
     *   ，在编程语言中，可以表示为 1 << 5。而
     * 变换大小写这件事等价于：
     * 如果字符是小写字符，减去 32 得到大写字符；
     * 如果字符是大写字符，加上 32 得到小写字符。
     * 而这两者合并起来，就是给这个字符做一次不进位的加法，即异或上 1 << 5。
     * @Date 9:36 2022/10/30
     * @Param s
     * @return java.util.List<java.lang.String>
     **/
    List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        new LetterCasePermutation().letterCasePermutation("a1b2");
    }

    public List<String> letterCasePermutation(String s) {
        if (s == null || "".equals(s)) {
            return null;
        }
        dfs(s.toCharArray(), 0);
        return list;
    }
    public void dfs(char[] chars, int index){
        if (index == chars.length) {
            list.add(new String(chars));
            return;
        }
        dfs(chars, index + 1);
        char ch = chars[index];
        if (Character.isLetter(ch)) {
            //优化，如果32的二进制位为1说明是小写就需要-32即把改位置为0
            //如果是0则说明是大写，则需要+32，即把改位置为1
            //综上可以用异或
            chars[index] ^= (1 << 5);
            dfs(chars, index + 1);
        }
    }



    /**
     * @methodName letterCasePermutation
     * @Author WSL
     * @Description  使用递归
     * @Date 9:35 2022/10/30
     * @Param s
     * @return java.util.List<java.lang.String>
     **/
    public List<String> letterCasePermutation1(String s) {
        if (s == null || "".equals(s)) {
            return null;
        }
        return hh(s, 0);
    }
    List<String> hh(String s, int index){
        List<String> list = new ArrayList<>();
        if (index == s.length()) {
            list.add("");
            return list;
        }
        char ch = s.charAt(index);
        List<String> hh = hh(s, index + 1);
        if (Character.isDigit(ch)) {
            for (String s1 : hh) {
                list.add(ch + s1);
            }
        } else if (Character.isLetter(ch)) {
            for (String s1 : hh) {
                list.add(ch + s1);
                list.add((Character.isUpperCase(ch) ? Character.toLowerCase(ch) : Character.toUpperCase(ch)) + s1);
            }
        }
        return list;
    }
}
