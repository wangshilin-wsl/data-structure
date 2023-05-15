package greed;

import java.util.*;

/*力扣:1054. 距离相等的条形码
        在一个仓库里，有一排条形码，其中第 i 个条形码为 barcodes[i]。

        请你重新排列这些条形码，使其中任意两个相邻的条形码不能相等。 你可以返回任何满足该要求的答案，此题保证存在答案。



        示例 1：

        输入：barcodes = [1,1,1,2,2,2]
        输出：[2,1,2,1,2,1]
        示例 2：

        输入：barcodes = [1,1,1,1,2,2,3,3]
        输出：[1,3,1,3,2,1,2,1]


        提示：

        1 <= barcodes.length <= 10000
        1 <= barcodes[i] <= 10000*/
public class RearrangeBarcodes {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new RearrangeBarcodes().rearrangeBarcodes(new int[]{1,1,1,1,2,2,3,3})));

    }
    public int[] rearrangeBarcodes(int[] barcodes) {
        //思路：贪心，每次取出当前最大堆里面的频次最高的，还要注意是否与上一个相等
        int n = barcodes.length;
        Map<Integer, Integer> cntMap = new HashMap<>();
        int[] res = new int[n];
        for (int barcode : barcodes) {
            cntMap.put(barcode, cntMap.getOrDefault(barcode, 0) + 1);
        }
        Queue<int[]> heap = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        for (Map.Entry<Integer, Integer> entry : cntMap.entrySet()) {
            heap.offer(new int[]{entry.getKey(), entry.getValue()});
        }
        for (int i = 0; i < n; i++) {
            int[] ints = heap.poll();
            int cnt = ints[1], key = ints[0];
            //第一个，或者不等于上一个
            if (i == 0 || key != res[i - 1]) {
                res[i] = key;
                if (cnt > 1) {
                    heap.offer(new int[]{key, cnt - 1});
                }
            } else {
                //当前最多key与上一个一样，那么取出第二大的
                int[] intsTemp = heap.poll();
                int cntTemp = intsTemp[1], keyTemp = intsTemp[0];
                res[i] = keyTemp;
                if (cntTemp > 1) {
                    heap.offer(new int[]{keyTemp, cntTemp - 1});
                }
                heap.offer(new int[]{key, cnt});
            }
        }
        return res;
    }
}
