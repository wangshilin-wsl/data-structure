package competition.jiaqitouzi_2023_02_19;

/*力扣：6365. 将整数减少到零需要的最少操作数 显示英文描述
        给你一个正整数 n ，你可以执行下述操作 任意 次：

        n 加上或减去 2 的某个 幂
        返回使 n 等于 0 需要执行的 最少 操作数。

        如果 x == 2i 且其中 i >= 0 ，则数字 x 是 2 的幂。



        示例 1：

        输入：n = 39
        输出：3
        解释：我们可以执行下述操作：
        - n 加上 20 = 1 ，得到 n = 40 。
        - n 减去 23 = 8 ，得到 n = 32 。

        - n 减去 25 = 32 ，得到 n = 0 。
        可以证明使 n 等于 0 需要执行的最少操作数是 3 。
        示例 2：

        输入：n = 54
        输出：3
        解释：我们可以执行下述操作：
        - n 加上 21 = 2 ，得到 n = 56 。
        - n 加上 23 = 8 ，得到 n = 64 。
        - n 减去 26 = 64 ，得到 n = 0 。
        使 n 等于 0 需要执行的最少操作数是 3 。


        提示：

        1 <= n <= 105*/
public class MinOperations {
    public static void main(String[] args) {
        System.out.println(new MinOperations().minOperations(54));
    }
    //思路，把n当做二进制来写，要变成0，则一定要把n所有的1消除掉
    //消除1的方式有两种，1、对应的位置+1,或者对应的位置-1
    //本题从最低的1开始，最低位的1的公式为x = n - (n & (n - 1))
    public int minOperations(int n) {
       if ((n & (n - 1)) == 0) {
           return 1;
       }
       int low = n - (n & (n - 1));
       return Math.min(minOperations(n - low), minOperations(n + low)) + 1;
    }
}
