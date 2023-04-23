package tow_point;

/*1147. 段式回文
        你会得到一个字符串 text 。你应该把它分成 k 个子字符串 (subtext1, subtext2，…， subtextk) ，要求满足:

        subtexti 是 非空 字符串
        所有子字符串的连接等于 text ( 即subtext1 + subtext2 + ... + subtextk == text )
        对于所有 i 的有效值( 即 1 <= i <= k ) ，subtexti == subtextk - i + 1 均成立
        返回k可能最大值。



        示例 1：

        输入：text = "ghiabcdefhelloadamhelloabcdefghi"
        输出：7
        解释：我们可以把字符串拆分成 "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)"。
        示例 2：

        输入：text = "merchant"
        输出：1
        解释：我们可以把字符串拆分成 "(merchant)"。
        示例 3：

        输入：text = "antaprezatepzapreanta"
        输出：11
        解释：我们可以把字符串拆分成 "(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)"。


        提示：

        1 <= text.length <= 1000
        text 仅由小写英文字符组成*/
public class LongestDecomposition {
    public static void main(String[] args) {
        System.out.println(new LongestDecomposition().longestDecomposition("ghiabcdefhelloadamhelloabcdefghi"));
    }

    //双指针：在回文的基础上，增加把一个字符串当做一个字符的功能
    public int longestDecomposition(String text) {
        int n = text.length(), max = 0, l = 0, r = n - 1;
        while (l <= r) {
            if (l == r) {
                return max + 1;
            }
            if (text.charAt(l) == text.charAt(r)) {
                l++;
                r--;
                max += 2;
            }
            for (int i = l; i <= r; i++) {
                if (text.charAt(i) == text.charAt(r) && text.substring(l, i + 1).equals(text.substring(r - i + l, r + 1))) {
                    max += i == r ? 1 : 2;
                    r = r - i + l - 1;
                    l = i + 1;
                    break;
                }
            }
        }
        return max;
    }

    //递归
    public int longestDecomposition1(String text) {
        if (text.isEmpty()) {
            return 0;
        }
        //枚举0,i
        int n = text.length();
        for (int i = 1; i < n; i++) {
            if (text.substring(0, i).equals(text.substring(n - i))) {
                return 2+ longestDecomposition(text.substring(i, n- i));
            }
        }
        //没有找到，则text整个当做一个回文段
        return 1;
    }
}
