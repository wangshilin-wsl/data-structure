package deque;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/*面试题 17.09. 第 k 个数
        有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。
        注意，不是必须有这些素因子，而是必须不包含其他的素因子。
        例如，前几个数按顺序应该是 1，3，5，7，9，15，21。

        示例 1:
        输入: k = 5
        输出: 9
        思路：后面的数字由前面的某个数字*3,5,7而来，但是应考虑重复和顺序问题，
        故用hashset去重，用优先队列来存储
        */
public class GetKthMagicNumber {
    public static void main(String[] args) {
        System.out.println(getKthMagicNumber(5));
    }

    public static int getKthMagicNumber(int k) {
        Queue<Long> heap = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        set.add(1L);
        heap.add(1L);
        int[] nums = new int[]{3,5,7};
        Long res = 0L;
        for (int i = 0; i < k; i++) {
            res = heap.poll();
            for (int j = 0; j < nums.length; j++) {
                Long temp = res * nums[j];
                if(set.add(temp)){
                    heap.add(temp);
                }
            }
        }

        return res.intValue();
    }
}
