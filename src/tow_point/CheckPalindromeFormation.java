package tow_point;

/*力扣：1616. 分割两个字符串得到回文串
        给你两个字符串 a 和 b ，它们长度相同。请你选择一个下标，将两个字符串都在 相同的下标 分割开。由 a 可以得到两个字符串： aprefix 和 asuffix ，满足 a = aprefix + asuffix ，同理，由 b 可以得到两个字符串 bprefix 和 bsuffix ，满足 b = bprefix + bsuffix 。请你判断 aprefix + bsuffix 或者 bprefix + asuffix 能否构成回文串。

        当你将一个字符串 s 分割成 sprefix 和 ssuffix 时， ssuffix 或者 sprefix 可以为空。比方说， s = "abc" 那么 "" + "abc" ， "a" + "bc" ， "ab" + "c" 和 "abc" + "" 都是合法分割。

        如果 能构成回文字符串 ，那么请返回 true，否则返回 false 。

        注意， x + y 表示连接字符串 x 和 y 。



        示例 1：

        输入：a = "x", b = "y"
        输出：true
        解释：如果 a 或者 b 是回文串，那么答案一定为 true ，因为你可以如下分割：
        aprefix = "", asuffix = "x"
        bprefix = "", bsuffix = "y"
        那么 aprefix + bsuffix = "" + "y" = "y" 是回文串。
        示例 2：

        输入：a = "abdef", b = "fecab"
        输出：true
        示例 3：

        输入：a = "ulacfd", b = "jizalu"
        输出：true
        解释：在下标为 3 处分割：
        aprefix = "ula", asuffix = "cfd"
        bprefix = "jiz", bsuffix = "alu"
        那么 aprefix + bsuffix = "ula" + "alu" = "ulaalu" 是回文串。


        提示：

        1 <= a.length, b.length <= 105
        a.length == b.length
        a 和 b 都只包含小写英文字母*/
public class CheckPalindromeFormation {
    public static void main(String[] args) {
        System.out.println(new CheckPalindromeFormation().checkPalindromeFormation("x", "y"));
    }
    public boolean checkPalindromeFormation(String a, String b) {
        //思路，要么从a开头匹配，或者从b开始匹配
        return judgePali(a,b) || judgePali(b, a);
    }
    public boolean judgePali(String a, String b) {
        //该方法从a开头，b结尾开始互相匹配
        int n = a.length(), l = 0, r = n - 1;
        while (l < r && a.charAt(l) == b.charAt(r)) {
            l++;
            r--;
        }
        //说明a开头部分，和b结尾部分是相互匹配的，是回文
        if (l >= r) {
            return true;
        }
        //在匹配中遇到不一样的，则可能将l跳到b，则是在b中[l,r]范围匹配回文
        //也可能将r跳到a上，则是在a上[l,r]范围匹配回文
        return judgeAtSame(a, l, r) || judgeAtSame(b, l, r);
    }
    public boolean judgeAtSame(String a, int l, int r) {
        //该方法在a中[l, r]范围判断是否是回文
        while (l < r && a.charAt(l) == a.charAt(r)) {
            l++;
            r--;
        }
        return l >= r;
    }
}
