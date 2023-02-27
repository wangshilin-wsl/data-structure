package string;

/*力扣：1247. 交换字符使得字符串相同
        有两个长度相同的字符串 s1 和 s2，且它们其中 只含有 字符 "x" 和 "y"，你需要通过「交换字符」的方式使这两个字符串相同。

        每次「交换字符」的时候，你都可以在两个字符串中各选一个字符进行交换。

        交换只能发生在两个不同的字符串之间，绝对不能发生在同一个字符串内部。也就是说，我们可以交换 s1[i] 和 s2[j]，但不能交换 s1[i] 和 s1[j]。

        最后，请你返回使 s1 和 s2 相同的最小交换次数，如果没有方法能够使得这两个字符串相同，则返回 -1 。



        示例 1：

        输入：s1 = "xx", s2 = "yy"
        输出：1
        解释：
        交换 s1[0] 和 s2[1]，得到 s1 = "yx"，s2 = "yx"。
        示例 2：

        输入：s1 = "xy", s2 = "yx"
        输出：2
        解释：
        交换 s1[0] 和 s2[0]，得到 s1 = "yy"，s2 = "xx" 。
        交换 s1[0] 和 s2[1]，得到 s1 = "xy"，s2 = "xy" 。
        注意，你不能交换 s1[0] 和 s1[1] 使得 s1 变成 "yx"，因为我们只能交换属于两个不同字符串的字符。
        示例 3：

        输入：s1 = "xx", s2 = "xy"
        输出：-1
        示例 4：

        输入：s1 = "xxyyxyxyxx", s2 = "xyyxyxxxyx"
        输出：4


        提示：

        1 <= s1.length, s2.length <= 1000
        s1, s2 只包含 'x' 或 'y'。*/
public class MinimumSwap {
    public static void main(String[] args) {
        System.out.println(new MinimumSwap().minimumSwap("xx", "yy"));
    }

    public int minimumSwap(String s1, String s2) {
        //贪心思路：先遍历，查看s1[i]!=s2[i]的个数
        //如果个数为奇数，肯定不可能交换之后相同，奇数不可能被均分
        //s1[i]=x,s2[i]=y我们成为xy,s1[i]=y,s2[i]=x我们成为yx
        //xy,xy可以通过一次变换得到xx,yy得一步
        //如果是yx,xy则需要先变为xy,xy，在变为xx,yy所以得两步
        //(xy,xy其实和yx,yx一样的)，所以需要查看x的个数
        //如果为奇数则为d/2+1,如果为偶数则为d/2;
        int d = 0, x = 0, n = s1.length();
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                d++;
                if (s1.charAt(i) == 'x') {
                    x++;
                }
            }
        }
        if ((d & 1) == 1) {
            return -1;
        }
        return (x & 1) == 0 ? d / 2 : d / 2 + 1;
    }
}
