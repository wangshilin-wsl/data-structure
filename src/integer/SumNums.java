package integer;
/*
求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。

         

        示例 1：

        输入: n = 3
        输出: 6
        示例 2：

        输入: n = 9
        输出: 45
思路：
常见的逻辑运算符有三种，即 “与 \&\&&& ”，“或 ||∣∣ ”，“非 !! ” ；而其有重要的短路效应，如下所示：


        if(A && B)  // 若 A 为 false ，则 B 的判断不会执行（即短路），直接判定 A && B 为 false

        if(A || B) // 若 A 为 true ，则 B 的判断不会执行（即短路），直接判定 A || B 为 true
        本题需要实现 “当 n = 1 时终止递归” 的需求，可通过短路效应实现。


        n > 1 && sumNums(n - 1) // 当 n = 1 时 n > 1 不成立 ，此时 “短路” ，终止后续递归
*/

public class SumNums {
    public static void main(String[] args) {
        System.out.println(new SumNums().sumNums(3));
    }
    public int sumNums(int n) {
        boolean a=n>1&&(n+=sumNums(n-1))>0;
        return n;
    }
}
