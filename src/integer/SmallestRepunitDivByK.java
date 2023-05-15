package integer;

import java.util.HashSet;
import java.util.Set;

/*力扣：1015. 可被 K 整除的最小整数
        给定正整数 k ，你需要找出可以被 k 整除的、仅包含数字 1 的最 小 正整数 n 的长度。

        返回 n 的长度。如果不存在这样的 n ，就返回-1。

        注意： n 不符合 64 位带符号整数。



        示例 1：

        输入：k = 1
        输出：1
        解释：最小的答案是 n = 1，其长度为 1。
        示例 2：

        输入：k = 2
        输出：-1
        解释：不存在可被 2 整除的正整数 n 。
        示例 3：

        输入：k = 3
        输出：3
        解释：最小的答案是 n = 111，其长度为 3。


        提示：

        1 <= k <= 105*/
public class SmallestRepunitDivByK {
    public static void main(String[] args) {
        System.out.println(new SmallestRepunitDivByK().smallestRepunitDivByK(1));
    }
    //遍历，记录余数，不能使用原数，优化，k可以整除2,5肯定不行，可以不使用set来判断了
    public int smallestRepunitDivByK(int k) {
        //优化,可以整除2,5的肯定不行
        if (k % 2 == 0 || k % 5 == 0) {
            return -1;
        }
        int reside = 1 % k, len = 1;

        //int如果表示原数一定会超时，所以只用记录每次对k的余数,那么肯定不会超过int的最大值
        //11%k=((1%k)+1)%k   1%k就是上一个的余数，找到了长度为n全是1的数字和长度为n-1全是1的数字   对k取余的关系
        while (reside != 0) {
            reside = (reside * 10 + 1) % k;
            len++;
        }
        return len;
    }

    //遍历，记录余数，不能使用原数，优化，k可以整除2,5肯定不行
    public int smallestRepunitDivByK1(int k) {
        //优化,可以整除2,5的肯定不行
        if (k % 2 == 0 || k % 5 == 0) {
            return -1;
        }
        int reside = 1 % k, len = 1;
        Set<Integer> set = new HashSet<>();
        //int如果表示原数一定会超时，所以只用记录每次对k的余数,那么肯定不会超过int的最大值
        //11%k=((1%k)+1)%k   1%k就是上一个的余数，找到了长度为n全是1的数字和长度为n-1全是1的数字   对k取余的关系
        //使用一个set记录是否有余数相等，余数相等则表示无限循环了
        while (reside != 0) {
            if (!set.add(reside)) {
                return -1;
            }
            reside = (reside * 10 + 1) % k;
            len++;
        }
        return len;
    }

    //遍历，记录余数，不能使用原数
    public int smallestRepunitDivByK2(int k) {
        int reside = 1 % k, len = 1;
        Set<Integer> set = new HashSet<>();
        //int如果表示原数一定会超时，所以只用记录每次对k的余数,那么肯定不会超过int的最大值
        //11%k=((1%k)+1)%k   1%k就是上一个的余数，找到了长度为n全是1的数字和长度为n-1全是1的数字   对k取余的关系
        //使用一个set记录是否有余数相等，余数相等则表示无限循环了
        while (reside != 0) {
            if (!set.add(reside)) {
                return -1;
            }
            reside = (reside * 10 + 1) % k;
            len++;
        }
        return len;
    }
}
