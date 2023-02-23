package highmath.binary_gray_code;

import java.util.ArrayList;
import java.util.List;

/*力扣：1238. 循环码排列
        给你两个整数 n 和 start。你的任务是返回任意 (0,1,2,,...,2^n-1) 的排列 p，并且满足：

        p[0] = start
        p[i] 和 p[i+1] 的二进制表示形式只有一位不同
        p[0] 和 p[2^n -1] 的二进制表示形式也只有一位不同


        示例 1：

        输入：n = 2, start = 3
        输出：[3,2,0,1]
        解释：这个排列的二进制表示是 (11,10,00,01)
        所有的相邻元素都有一位是不同的，另一个有效的排列是 [3,1,0,2]
        示例 2：

        输出：n = 3, start = 2
        输出：[2,6,7,5,4,0,1,3]
        解释：这个排列的二进制表示是 (010,110,111,101,100,000,001,011)


        提示：

        1 <= n <= 16
        0 <= start < 2^n*/
public class CircularPermutation {
    public static void main(String[] args) {
        System.out.println(new CircularPermutation().circularPermutation(2, 3));
    }


    //优化
    public List<Integer> circularPermutation(int n, int start) {
        //前置89. 格雷编码
        //格雷码介绍：https://blog.csdn.net/jingfengvae/article/details/51691124
        //1 << n就是2^n
        //数字i对应的格雷码就是i ^ (i >> 1)
        //在前置提进阶：要求start开头，则第一个数字的格雷码表示数为start，由于0的格雷码就是0所以前置题目不用考虑
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) {
            list.add(i ^ (i >> 1) ^ start);
        }
        return list;
    }




    public List<Integer> circularPermutation1(int n, int start) {
        //前置89. 格雷编码
        //格雷码介绍：https://blog.csdn.net/jingfengvae/article/details/51691124
        //1 << n就是2^n
        //数字i对应的格雷码就是i ^ (i >> 1)
        //在前置提进阶：要求start开头，则第一个数字的格雷码表示数为start，由于0的格雷码就是0所以前置题目不用考虑
        List<Integer> list = new ArrayList<>();
        int len = (1 << n), code = 0;
        //先找到start对应的格雷码
        for (int i = 0; i < len; i++) {
            if ((i ^ (i >> 1)) == start) {
                code = i;
                break;
            }
        }
        for (int i = 0; i < len; i++) {
            //从start开始，index本来为(i + start) % len
            //但是由于len = 2^n所以可以写成下面形式
            int index = (i + code) & (len - 1);
            list.add(index ^ (index >> 1));
        }
        return list;
    }
}
