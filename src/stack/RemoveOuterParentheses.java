package stack;
/*
力扣：1021. 删除最外层的括号 https://leetcode.cn/problems/remove-outermost-parentheses/
有效括号字符串为空 ""、"(" + A + ")"或A + B ，其中A 和B都是有效的括号字符串，+代表字符串的连接。

        例如，""，"()"，"(())()"和"(()(()))"都是有效的括号字符串。
        如果有效字符串 s 非空，且不存在将其拆分为 s = A + B的方法，我们称其为原语（primitive），其中A 和B都是非空有效括号字符串。

        给出一个非空有效字符串 s，考虑将其进行原语化分解，使得：s = P_1 + P_2 + ... + P_k，其中P_i是有效括号字符串原语。

        对 s 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 s 。



        示例 1：

        输入：s = "(()())(())"
        输出："()()()"
        解释：
        输入字符串为 "(()())(())"，原语化分解得到 "(()())" + "(())"，
        删除每个部分中的最外层括号后得到 "()()" + "()" = "()()()"。
        示例 2：

        输入：s = "(()())(())(()(()))"
        输出："()()()()(())"
        解释：
        输入字符串为 "(()())(())(()(()))"，原语化分解得到 "(()())" + "(())" + "(()(()))"，
        删除每个部分中的最外层括号后得到 "()()" + "()" + "()(())" = "()()()()(())"。
        示例 3：

        输入：s = "()()"
        输出：""
        解释：
        输入字符串为 "()()"，原语化分解得到 "()" + "()"，
        删除每个部分中的最外层括号后得到 "" + "" = ""。


        提示：

        1 <= s.length <= 105
        s[i] 为 '(' 或 ')'
        s 是一个有效括号字符串

        思路：使用栈，正括号的时候入栈，反括号的时候出栈，如果栈为空代表遇到了一个元语，
        用一个StringBuffer来装结果，如果可以用栈，这个地方可以用一个int代表栈
        */

import java.util.Stack;

public class RemoveOuterParentheses {
    public static void main(String[] args) {

    }
    //使用栈，并记录一个元语的下标
    public String removeOuterParentheses1(String s) {
        if(s == null || "".equals(s.trim())){
            return "";
        }
        Stack<String> stack = new Stack<>();
        StringBuffer sb = new StringBuffer();
        int temp = 0;
        for (int i = 0; i < s.length(); i++) {
            if('(' == s.charAt(i)){
                //碰到正括号,入栈
                stack.push(s.charAt(i) + "");
            }else{
                //遇到反括号，直接出栈
                stack.pop();
            }
            if(stack.isEmpty()){
                //栈为空，表示遇到了一个元语
                sb.append(s.substring(temp + 1, i));
                temp = i + 1;
            }
        }
        return sb.toString();
    }

    //使用栈，先判断是否是反括号，在栈不为空的时候往结果里面加入当前字符。
    public String removeOuterParenthesesPlus(String s) {
        StringBuffer res = new StringBuffer();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                res.append(c);
            }
            if (c == '(') {
                stack.push(c);
            }
        }
        return res.toString();
    }

}
