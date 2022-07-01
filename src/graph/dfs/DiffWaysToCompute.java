package graph.dfs;

import java.util.ArrayList;
import java.util.List;

/*力扣：241. 为运算表达式设计优先级

        给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，
        计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。
        生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 104 。

        示例 1：
        输入：expression = "2-1-1"
        输出：[0,2]
        解释：
        ((2-1)-1) = 0
        (2-(1-1)) = 2

        示例 2：
        输入：expression = "2*3-4*5"
        输出：[-34,-14,-10,-10,10]
        解释：
        (2*(3-(4*5))) = -34
        ((2*3)-(4*5)) = -14
        ((2*(3-4))*5) = -10
        (2*((3-4)*5)) = -10
        (((2*3)-4)*5) = 10

        提示：
        1 <= expression.length <= 20
        expression 由数字和算符 '+'、'-' 和 '*' 组成。
        输入表达式中的所有整数值在范围 [0, 99]

        分析：每个表达式都可以分成左右两个部分（用运算符号隔开），左边部分和右边部分分别计算
        得到的结果在交叉计算，一直这样递归下去，那么返回的条件就是没有遇到运算符的时候，并
        返回区间的数字。
        */
public class DiffWaysToCompute {
    char[] chars;
    public List<Integer> diffWaysToCompute(String expression) {
        chars = expression.toCharArray();
        return dfs(0, chars.length -1);
    }
    List<Integer> dfs(int l,int r){
        List<Integer> temp = new ArrayList<>();
        for (int i = l; i <= r; i++) {
            if(Character.isDigit(chars[i])){
                continue;
            }
            List<Integer> left = dfs(l, i - 1);
            List<Integer> right = dfs(i + 1, r);
            for (int i1 = 0; i1 < left.size(); i1++) {
                for (int i2 = 0; i2 < right.size(); i2++) {
                    if(chars[i] == '-'){
                        temp.add(left.get(i1) - right.get(i2));
                    }else if (chars[i] == '+'){
                        temp.add(left.get(i1) + right.get(i2));
                    }else {
                        temp.add(left.get(i1) * right.get(i2));
                    }
                }
            }
        }
        if(temp.isEmpty()){
            int res = 0;
            for (int i = l; i <= r; i++) {
                res = res * 10 + (chars[i] - '0');
            }
            temp.add(res);
        }

        return temp;
    }


    public static void main(String[] args) {
        System.out.println(new DiffWaysToCompute().diffWaysToCompute("2-1-1"));
    }
}
