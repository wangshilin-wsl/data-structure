package highmath;

/*力扣：1017. 负二进制转换
        给你一个整数 n ，以二进制字符串的形式返回该整数的 负二进制（base -2）表示。

        注意，除非字符串就是 "0"，否则返回的字符串中不能含有前导零。



        示例 1：

        输入：n = 2
        输出："110"
        解释：(-2)2 + (-2)1 = 2
        示例 2：

        输入：n = 3
        输出："111"
        解释：(-2)2 + (-2)1 + (-2)0 = 3
        示例 3：

        输入：n = 4
        输出："100"
        解释：(-2)2 = 4


        提示：

        0 <= n <= 109*/
public class BaseNeg2 {
    public static void main(String[] args) {
        System.out.println(new BaseNeg2().baseNeg2(6));
    }
    public String baseNeg2(int n) {
        //思路：
        //对于2^i,如果i为偶数时，此时2^i=(-2)^i
        //对于2^i,如果i为奇数时，此时2^i=(-2)^(i+1) + (-2)^i
        //可以将n变为二进制，在进行上面的转换进位
        if (n == 0) {
            return "0";
        }
        int[] bits = new int[32];
        for (int i = 0; i < 32 && n != 0; i++) {
            if ((n & 1) != 0) {
                //奇数
                if ((i & 1) != 0) {
                    bits[i + 1]++;
                }
                bits[i]++;
            }
            n >>= 1;
        }
        StringBuilder sb = new StringBuilder();
        int carry = 0, index = 0, one = 0;
        while (carry > 0 || index < 32) {
            int value = (bits[index] + carry);
            sb.append(value & 1);
            //进位，参照10进制,35
            //该位数字为35%10=5,进位为35/10，其实是(35 - 5)/10，因为/10都是整数，小数可以忽略的情况35/10=(35 - 5)/10
            carry = (value - (value & 1)) / (-2);
            if ((value & 1) == 1) {
                one = index;
            }
            index++;
        }
        return sb.reverse().toString().substring(31 - one);
    }
}
