package integer;
/*力扣166题，分数到小数
注意，当分子或者分母为负数的时候，最小负数变为正数int不够的情况，得先转为long
        给定两个整数，分别表示分数的分子numerator 和分母 denominator，
        以字符串形式返回小数 。
        如果小数部分为循环小数，则将循环的部分括在括号内。
        如果存在多个答案，只需返回 任意一个 。
        对于所有给定的输入，保证 答案字符串的长度小于 10^4 。
         
        示例 1：

        输入：numerator = 1, denominator = 2
        输出："0.5"

        示例 2：
        输入：numerator = 2, denominator = 1
        输出："2"

        示例 3：
        输入：numerator = 2, denominator = 3
        输出："0.(6)"

        示例 4：
        输入：numerator = 4, denominator = 333
        输出："0.(012)"

        示例 5：
        输入：numerator = 1, denominator = 5
        输出："0.2"
         
        提示：
        -2^31 <= numerator, denominator <= 2^(31 - 1)
        denominator != 0
*/

import java.util.HashMap;
import java.util.Map;

public class FractionToDecimal {
    public static void main(String[] args) {
        System.out.println(new FractionToDecimal().fractionToDecimal(-1,-2147483648));
    }

    public String fractionToDecimal(int numerator, int denominator) {
        long a = numerator, b = denominator;
        Map<Long, Integer> map = new HashMap<>();//记录余数和对应的位置的map
        StringBuilder res = new StringBuilder();//存储返回结果的
        if ((a < 0 && b > 0) || (a > 0 && b < 0)) {
            res.append("-");
        }
        a =Math.abs(a);
        b =Math.abs(b);
        res.append(a/b);
        a %= b;
        if (a == 0) {
            return res.toString();
        }
        res.append(".");
        a*=10;//加了小数点，*10
        while (a != 0 && !map.containsKey(a)) {
            map.put(a, res.length());
            res.append(a/b);
            a = (a%b)*10;
        }
        //存在循环小数的情况
        if(a!=0){
            res.insert(map.get(a),"(");
            res.append(")");
        }
        return res.toString();
    }
}
