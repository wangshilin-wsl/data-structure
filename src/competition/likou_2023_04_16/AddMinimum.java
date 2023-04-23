package competition.likou_2023_04_16;

/*力扣：6375. 构造有效字符串的最少插入数 显示英文描述
        通过的用户数38
        尝试过的用户数48
        用户总通过次数38
        用户总提交次数50
        题目难度Medium
        给你一个字符串 word ，你可以向其中任何位置插入 "a"、"b" 或 "c" 任意次，返回使 word 有效 需要插入的最少字母数。

        如果字符串可以由 "abc" 串联多次得到，则认为该字符串 有效 。



        示例 1：

        输入：word = "b"
        输出：2
        解释：在 "b" 之前插入 "a" ，在 "b" 之后插入 "c" 可以得到有效字符串 "abc" 。
        示例 2：

        输入：word = "aaa"
        输出：6
        解释：在每个 "a" 之后依次插入 "b" 和 "c" 可以得到有效字符串 "abcabcabc" 。
        示例 3：

        输入：word = "abc"
        输出：0
        解释：word 已经是有效字符串，不需要进行修改。


        提示：

        1 <= word.length <= 50
        word 仅由字母 "a"、"b" 和 "c" 组成。*/
public class AddMinimum {
    public static void main(String[] args) {
        System.out.println(new AddMinimum().addMinimum("aaa"));
    }

    public int addMinimum(String word) {
        int n = word.length(), index = 0, min = 0;
        for (int i = 0; i < n; i++) {
            int ch = word.charAt(i) - 'a';
            if (ch != index) {
                min ++;
                i--;
            }
            index++;
            index %= 3;
        }
        //注意最后一个字符，比如最后一个为b，则需要加c,最后一个为a则需要加bc
        return index == 0 ? min : min + 3 - index;
    }
}
