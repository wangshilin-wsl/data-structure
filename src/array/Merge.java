package array;

import java.util.*;
/*56. 合并区间
以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
        请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。

        示例 1：
        输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
        输出：[[1,6],[8,10],[15,18]]
        解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].

        示例2：
        输入：intervals = [[1,4],[4,5]]
        输出：[[1,5]]
        解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。

        提示：
        1 <= intervals.length <= 104
        intervals[i].length == 2
        0 <= starti <= endi <= 104
        思路：先排序，在一直用list最后一个区间和新的对比，如果新的最小大于list最后一个最大的一个
        就直接加入，不然直接修改List最后一个的最大值就好了
        */

public class Merge {
    public static void main(String[] args) {
        new Merge().merge(new int[][]{{1,3},{2,6},{8,10},{15,18}});
    }

    public int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        for (int i = 0; i < intervals.length; i++) {
            int left = intervals[i][0], right = intervals[i][1];
            if(list.isEmpty() || list.get(list.size() - 1)[1] < left){
                list.add(new int[]{left, right});
            }else {
                list.get(list.size() - 1)[1] = Math.max(right, list.get(list.size() - 1)[1]);
            }
        }
        return list.toArray(new int[list.size()][2]);
    }
}
