package greed;

import java.util.PriorityQueue;
import java.util.Queue;

/*力扣：LCP 33. 蓄水
        给定 N 个无限容量且初始均空的水缸，每个水缸配有一个水桶用来打水，第 i 个水缸配备的水桶容量记作 bucket[i]。小扣有以下两种操作：

        升级水桶：选择任意一个水桶，使其容量增加为 bucket[i]+1
        蓄水：将全部水桶接满水，倒入各自对应的水缸
        每个水缸对应最低蓄水量记作 vat[i]，返回小扣至少需要多少次操作可以完成所有水缸蓄水要求。

        注意：实际蓄水量 达到或超过 最低蓄水量，即完成蓄水要求。

        示例 1：

        输入：bucket = [1,3], vat = [6,8]

        输出：4

        解释：
        第 1 次操作升级 bucket[0]；
        第 2 ~ 4 次操作均选择蓄水，即可完成蓄水要求。
        vat1.gif

        示例 2：

        输入：bucket = [9,0,1], vat = [0,2,2]

        输出：3

        解释：
        第 1 次操作均选择升级 bucket[1]
        第 2~3 次操作选择蓄水，即可完成蓄水要求。

        提示：

        1 <= bucket.length == vat.length <= 100
        0 <= bucket[i], vat[i] <= 10^4*/
public class StoreWater {
    public static void main(String[] args) {
        System.out.println(new StoreWater().storeWater(new int[]{9,0,1}, new int[]{0,2,2}));
    }

    public int storeWater(int[] bucket, int[] vat) {
        int n = bucket.length, min = 0, abs = 0;
        //记录每次当前需要多少次能装满，从高到低，那么队头就是需要多少次装满
        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        for (int i = 0; i < n; i++) {
            if (vat[i] != 0) {
                if (bucket[i] == 0) {
                    min++;
                    bucket[i]++;
                }
                queue.offer(new int[]{(vat[i] + bucket[i] - 1) / bucket[i], i});
                abs += (bucket[i] >= vat[i] ? 0 : vat[i] - bucket[i]);
            }
        }
        if (queue.isEmpty()) {
            return min;
        }
        //所有可以直接倒满
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= abs; i++) {
            int[] ints = queue.poll();
            int num = ints[0];
            int index = ints[1];
            res = Math.min(res, min + num);
            bucket[index]++;
            queue.offer(new int[]{(vat[index] + bucket[index] - 1) / bucket[index], index});
            min++;
        }
        return res;
    }
}
