package dynamic_programming;

import java.util.*;

/*力扣：1326. 灌溉花园的最少水龙头数目
        在 x 轴上有一个一维的花园。花园长度为 n，从点 0 开始，到点 n 结束。

        花园里总共有 n + 1 个水龙头，分别位于 [0, 1, ..., n] 。

        给你一个整数 n 和一个长度为 n + 1 的整数数组 ranges ，其中 ranges[i] （下标从 0 开始）表示：如果打开点 i 处的水龙头，可以灌溉的区域为 [i -  ranges[i], i + ranges[i]] 。

        请你返回可以灌溉整个花园的 最少水龙头数目 。如果花园始终存在无法灌溉到的地方，请你返回 -1 。



        示例 1：



        输入：n = 5, ranges = [3,4,1,1,0,0]
        输出：1
        解释：
        点 0 处的水龙头可以灌溉区间 [-3,3]
        点 1 处的水龙头可以灌溉区间 [-3,5]
        点 2 处的水龙头可以灌溉区间 [1,3]
        点 3 处的水龙头可以灌溉区间 [2,4]
        点 4 处的水龙头可以灌溉区间 [4,4]
        点 5 处的水龙头可以灌溉区间 [5,5]
        只需要打开点 1 处的水龙头即可灌溉整个花园 [0,5] 。
        示例 2：

        输入：n = 3, ranges = [0,0,0,0]
        输出：-1
        解释：即使打开所有水龙头，你也无法灌溉整个花园。


        提示：

        1 <= n <= 104
        ranges.length == n + 1
        0 <= ranges[i] <= 100*/
public class MinTaps {
    public static void main(String[] args) {
        System.out.println(new MinTaps().minTaps(5, new int[]{3,4,1,1,0,0}));
    }

    public int minTaps(int n, int[] ranges) {
        //题解链接：https://leetcode.cn/problems/minimum-number-of-taps-to-open-to-water-a-garden/solution/guan-gai-hua-yuan-de-zui-shao-shui-long-tou-shu-3/
        //思路：非传统的动态规划,覆盖[0,n]的区域，计算每个水龙头覆盖的区域（去掉0,n以外的部分，不影响结果）
        int[][] intervals = new int[n + 1][];
        for (int i = 0; i <= n; i++) {
            int start = Math.max(0, i - ranges[i]);
            int end = Math.min(n, i + ranges[i]);
            intervals[i] = new int[]{start, end};
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        //dp[i]表示覆盖[0,i]的最小水龙头数量
        //对于第i个水龙头覆盖范围为[starti, endi]
        //那么对于[starti, endi]中任意的j，则有dp[j] = Math.min(dp[j], dp[starti] + 1)
        //dp[starti]表示[0,starti]覆盖最小水龙头数量，如果加上[starti, endi]肯定是可以覆盖[0,j]，因为j在[starti, endi]中
        //所以对所有范围水龙头排序（左边界从小到大），循环该数据，对于其中的每个i对经历以上步骤
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int[] interval : intervals) {
            int start = interval[0], end = interval[1];
            if (dp[start] == Integer.MAX_VALUE) {
                return -1;
            }
            for (int j = start; j <= end; j++) {
                dp[j] = Math.min(dp[j], dp[start] + 1);
            }
        }
        return dp[n];
    }
    //暴力法
    //1、现将有灌溉范围（range != 0）的水龙头的范围求出来
    //2、从0位置开始，每次都要寻找包含0在内，并且往右能够灌溉更远的水龙头，所以把1的数组按右侧由远到近排序，用一个set来装已经选中的
    public int minTaps1(int n, int[] ranges) {
        List<int[]> tapRanges = new ArrayList<>();
        for (int i = 0; i < ranges.length; i++) {
            if (ranges[i] == 0) {
                continue;
            }
            tapRanges.add(new int[]{i - ranges[i], i + ranges[i]});
        }
        Set<Integer> set = new HashSet<>();
        tapRanges.sort((o1, o2) -> o2[1] - o1[1]);
        int index = 0;
        while (index < n) {
            boolean flag = false;
            for (int i = 0; i < tapRanges.size(); i++) {
                int[] range = tapRanges.get(i);
                if (!set.contains(i) && range[0] <= index) {
                    index = range[1];
                    set.add(i);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return -1;
            }
        }
        return set.size();
    }
}
