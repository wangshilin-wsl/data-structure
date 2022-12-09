package highmath;

/*力扣：1780. 判断一个数字是否可以表示成三的幂的和
        给你一个整数 n ，如果你可以将 n 表示成若干个不同的三的幂之和，请你返回 true ，否则请返回 false 。

        对于一个整数 y ，如果存在整数 x 满足 y == 3x ，我们称这个整数 y 是三的幂。



        示例 1：

        输入：n = 12
        输出：true
        解释：12 = 31 + 32
        示例 2：

        输入：n = 91
        输出：true
        解释：91 = 30 + 32 + 34
        示例 3：

        输入：n = 21
        输出：false


        提示：

        1 <= n <= 107*/
public class CheckPowersOfThree {
    public static void main(String[] args) {
        System.out.println(new CheckPowersOfThree().checkPowersOfThree(12));
    }

    /**
     * @Description 思路：3 ^ 1 = 三进制(10)3  并且 3^1 + 3^1 = 三进制(20)3
     * 因为指数不能重复，所以对应的三进制数上只能为0、1，
     * 所以将n转换成3进制(除3取余法)，每一位上都不能为2.
     * @name checkPowersOfThree
     * @param n
     * @return boolean
     * @throws
     **/
    public boolean checkPowersOfThree(int n) {
        //使用取余法将n变为3进制数，里面不能包含2的数字
        while (n > 0) {
            if (n % 3 == 2){
                return false;
            }
            n /= 3;
        }
        return true;
    }


    public boolean checkPowersOfThree1(int n) {
        int div = n / 3, mod = n % 3;
        if (mod == 2){
            return false;
        }
        if (div == 0) {
            return true;
        }
        return checkPowersOfThree1(div);
    }


}
