package integer;

/**
 * @author WSL
 * @version 1.0.0
 * @ClassName CountPrimeSetBits.java
 * @Description 力扣：762. 二进制表示中质数个计算置位
 * @createTime 2022年04月05日 09:05:00
 */
/*
给你两个整left和right ，在闭区间 [left, right]范围内，统计并返回 计算置位位数为质数 的整数个数。

        计算置位位数 就是二进制表示中 1 的个数。
        例如， 21的二进制表示10101有 3 个计算置位。
        示例 1：

        输入：left = 6, right = 10
        输出：4
        解释：
        6 -> 110 (2 个计算置位，2 是质数)
        7 -> 111 (3 个计算置位，3 是质数)
        9 -> 1001 (2 个计算置位，2 是质数)
        10-> 1010 (2 个计算置位，2 是质数)
        共计 4 个计算置位为质数的数字。
        示例 2：

        输入：left = 10, right = 15
        输出：5
        解释：
        10 -> 1010 (2 个计算置位, 2 是质数)
        11 -> 1011 (3 个计算置位, 3 是质数)
        12 -> 1100 (2 个计算置位, 2 是质数)
        13 -> 1101 (3 个计算置位, 3 是质数)
        14 -> 1110 (3 个计算置位, 3 是质数)
        15 -> 1111 (4 个计算置位, 4 不是质数)
        共计 5 个计算置位为质数的数字。


        提示：

        1 <= left <= right <= 106
        0 <= right - left <= 104
*/

public class CountPrimeSetBits {
    /*思路:
   注意到 right<=10^6<2^20
 ，因此二进制中 1 的个数不会超过 19，而不超过 19 的质数只有

2, 3, 5, 7, 11, 13, 17, 19

我们可以用一个二进制数 mask=665772=10100010100010101100
来存储这些质数，其中 mask 二进制的从低到高的第 i 位为 1 表示 i 是质数，为 0 表示 i 不是质数。

设整数 x 的二进制中 1 的个数为 c，若 mask 按位与 2^c
  不为 0，则说明 c 是一个质数。
*/


    public int countPrimeSetBits(int left, int right) {
        int sum = 0;
        int rad = Integer.parseInt("10100010100010101100" ,2);
        for (int i = left; i <= right; i++) {
            int temp = 1 << Integer.bitCount(i);
            if((temp & rad)  != 0){
                sum++;
            }
        }
        return sum;
    }
}
