package greed;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*力扣：1090. 受标签影响的最大值
        我们有一个 n 项的集合。给出两个整数数组 values 和 labels ，第 i 个元素的值和标签分别是 values[i] 和 labels[i]。还会给出两个整数 numWanted 和 useLimit 。

        从 n 个元素中选择一个子集 s :

        子集 s 的大小 小于或等于 numWanted 。
        s 中 最多 有相同标签的 useLimit 项。
        一个子集的 分数 是该子集的值之和。

        返回子集 s 的最大 分数 。



        示例 1：

        输入：values = [5,4,3,2,1], labels = [1,1,2,2,3], numWanted = 3, useLimit = 1
        输出：9
        解释：选出的子集是第一项，第三项和第五项。
        示例 2：

        输入：values = [5,4,3,2,1], labels = [1,3,3,3,2], numWanted = 3, useLimit = 2
        输出：12
        解释：选出的子集是第一项，第二项和第三项。
        示例 3：

        输入：values = [9,8,8,7,6], labels = [0,0,0,1,1], numWanted = 3, useLimit = 1
        输出：16
        解释：选出的子集是第一项和第四项。


        提示：

        n == values.length == labels.length
        1 <= n <= 2 * 104
        0 <= values[i], labels[i] <= 2 * 104
        1 <= numWanted, useLimit <= n*/
public class LargestValsFromLabels {
    public static void main(String[] args) {
        System.out.println(new LargestValsFromLabels().largestValsFromLabels(new int[]{5, 4, 3, 2, 1}, new int[]{1, 1, 2, 2, 3}, 3, 1));
    }
    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        //思路，贪心，最大值，那么按照value降序，从最大开始取，在取的过程中需要满足两个条件
        int n = values.length, cnt = 0, max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        Integer[] indexs = new Integer[n];
        for (int i = 0; i < n; i++) {
            indexs[i] = i;
        }
        Arrays.sort(indexs, (o1, o2) -> values[o2] - values[o1]);
        for (Integer index : indexs) {
            if (cnt >= numWanted) {
                break;
            }
            int value = values[index];
            int label = labels[index];
            int num = map.getOrDefault(label, 0);
            if (num >= useLimit) {
                continue;
            }
            max += value;
            map.put(label, num + 1);
            cnt++;
        }
        return max;
    }
}
