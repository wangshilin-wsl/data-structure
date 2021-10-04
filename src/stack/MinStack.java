package stack;

import java.util.Stack;
     /*   定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，
        调用 min、push 及 pop 的时间复杂度都是 O(1)。

        示例:
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.min();   --> 返回 -3.
        minStack.pop();
        minStack.top();      --> 返回 0.
        minStack.min();   --> 返回 -2.
        提示：
        各函数的调用总次数不超过 20000 次*/

public class MinStack {
    Stack<Integer> a;
    Stack<Integer> b;
    /** initialize your data structure here. */
    public MinStack() {
        a=new Stack<>();
        b=new Stack<>();
    }

    public void push(int x) {
        a.push(x);
        if(b.isEmpty()||b.peek()>=x){
            b.push(x);
        }
    }

    public void pop() {
        int temp=a.pop();
        if(b.peek()==temp)
            b.pop();
    }

    public int top() {
        return a.peek();
    }

    public int min() {
        return b.peek();
    }
}
