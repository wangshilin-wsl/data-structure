package stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/*力扣：1106. 解析布尔表达式
        给你一个以字符串形式表述的 布尔表达式（boolean） expression，返回该式的运算结果。
        有效的表达式需遵循以下约定：
        "t"，运算结果为 True
        "f"，运算结果为 False
        "!(expr)"，运算过程为对内部表达式 expr 进行逻辑 非的运算（NOT）
        "&(expr1,expr2,...)"，运算过程为对 2 个或以上内部表达式 expr1, expr2, ... 进行逻辑 与的运算（AND）
        "|(expr1,expr2,...)"，运算过程为对 2 个或以上内部表达式 expr1, expr2, ... 进行逻辑 或的运算（OR）

        示例 1：
        输入：expression = "!(f)"
        输出：true

        示例 2：
        输入：expression = "|(f,t)"
        输出：true

        示例 3：
        输入：expression = "&(t,f)"
        输出：false

        示例 4：
        输入：expression = "|(&(t,f,t),!(t))"
        输出：false

        提示：
        1 <= expression.length <= 20000
        expression[i] 由 {'(', ')', '&', '|', '!', 't', 'f', ','} 中的字符组成。
        expression 是以上述形式给出的有效表达式，表示一个布尔值。*/
public class BarseBoolExpr {
    public static void main(String[] args) {
        new BarseBoolExpr().parseBoolExpr("|(&(t,f,t),!(t))");
    }
    /**
     * @methodName parseBoolExpr1
     * @Author WSL
     * @Description  使用栈，模拟boolean运算，优化一小部分
     * @Date 17:47 2022/11/5
     * @Param expression
     * @return boolean
     **/
    public boolean parseBoolExpr1(String expression) {
        Stack<Character> stack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == '(' || ch == 't' || ch == 'f') {
                stack.push(ch);
            } else if (ch == ')') {
                char res = stack.peek();
                char op = opStack.pop();
                while (!stack.isEmpty() && stack.peek() != '(') {
                    res = compute(res, stack.pop(), op);
                }
                stack.pop();
                if (op == '!') {
                    stack.push(res == 't' ? 'f' : 't');
                } else {
                    stack.push(res);
                }
            } else if (ch == '!' || ch == '|' || ch == '&') {
                opStack.push(ch);
            }
        }
        return !stack.isEmpty() && stack.peek() == 't';
    }
    public char compute(char ch1, char ch2, char op){
        boolean b1 = ch1 == 't';
        boolean b2 = ch2 == 't';
        return (op == '&' ? b1 && b2 : b1 || b2) ? 't' : 'f';
    }
    /**
     * @methodName parseBoolExpr
     * @Author WSL
     * @Description  使用ArrayDeque实现栈，因为Stack是线程安全的，会有加锁操作比较慢
     * @Date 17:47 2022/11/5
     * @Param expression
     * @return boolean
     **/
    public boolean parseBoolExpr(String expression) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == ',') {
                continue;
            } if (ch != ')') {
                stack.push(ch);
            } else {
                int t = 0, f = 0;
                while (!stack.isEmpty() && stack.peek() != '(') {
                    if (stack.pop() == 't') {
                        t++;
                    }else {
                        f++;
                    }
                }
                stack.pop();
                char op = stack.pop();
                if (op == '!') {
                    stack.push(t == 1 ? 'f' : 't');
                } else if (op == '&') {
                    stack.push(f > 0 ? 'f' : 't');
                } else if (op == '|') {
                    stack.push(t > 0 ? 't' : 'f');
                }
            }
        }
        return !stack.isEmpty() && stack.peek() == 't';
    }
}
