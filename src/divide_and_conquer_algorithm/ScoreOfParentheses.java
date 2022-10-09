package divide_and_conquer_algorithm;

import java.util.Stack;

/*力扣：856. 括号的分数
        给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
        () 得 1 分。
        AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
        (A) 得 2 * A 分，其中 A 是平衡括号字符串。

        示例 1：
        输入： "()"
        输出： 1

        示例 2：
        输入： "(())"
        输出： 2

        示例 3：
        输入： "()()"
        输出： 2

        示例 4：
        输入： "(()(()))"
        输出： 6

        提示：
        S 是平衡括号字符串，且只含有 ( 和 ) 。
        2 <= S.length <= 50
        */
public class ScoreOfParentheses {

    public static void main(String[] args) {
        System.out.println(new ScoreOfParentheses().scoreOfParentheses("(()(()))"));
    }
    /**
     * @methodName scoreOfParentheses
     * @Author WSL
     * @Description  第一种解法，使用分治法，利用（为1，）为-1来看是否是+还是嵌套
     * @Date 19:48 2022/10/9
     * @Param s
     * @return int
     **/
    public int scoreOfParentheses(String s) {
        return dfsScore(s, 0, s.length() - 1);
    }

    public int dfsScore(String s, int left, int right) {
        if (left + 1 == right && s.charAt(left) == '(' && s.charAt(right) == ')') {
            return 1;
        }

        int index = -1, sum = 0;
        for (int i = left; i <= right; i++) {
            sum += s.charAt(i) == '(' ? 1 : -1;
            if(sum == 0){
                index = i;
                break;
            }
        }
        if (index == right) {
            //*2
            return 2 * dfsScore(s, left + 1, right - 1);
        } else {
            //a+b
            return dfsScore(s, left, index) + dfsScore(s, index + 1, right);
        }
    }

    /**
     * @methodName scoreOfParentheses1
     * @Author WSL
     * @Description  第二种解法，使用栈来记录之前的分数。
     * @Date 19:46 2022/10/9
     * @Param s
     * @return int
     **/
    public int scoreOfParentheses1(String s) {
        Stack<Integer> st = new Stack<Integer>();
        st.push(0);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                st.push(0);
            } else {
                int v = st.pop();
                int top = st.pop() + Math.max(2 * v, 1);
                st.push(top);
            }
        }
        return st.peek();
    }
}
