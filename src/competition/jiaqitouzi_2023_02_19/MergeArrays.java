package competition.jiaqitouzi_2023_02_19;

import java.util.*;

/*力扣：6362. 合并两个二维数组 - 求和法 显示英文描述

        给你两个 二维 整数数组 nums1 和 nums2.

        nums1[i] = [idi, vali] 表示编号为 idi 的数字对应的值等于 vali 。
        nums2[i] = [idi, vali] 表示编号为 idi 的数字对应的值等于 vali 。
        每个数组都包含 互不相同 的 id ，并按 id 以 递增 顺序排列。

        请你将两个数组合并为一个按 id 以递增顺序排列的数组，并符合下述条件：

        只有在两个数组中至少出现过一次的 id 才能包含在结果数组内。
        每个 id 在结果数组中 只能出现一次 ，并且其对应的值等于两个数组中该 id 所对应的值求和。如果某个数组中不存在该 id ，则认为其对应的值等于 0 。
        返回结果数组。返回的数组需要按 id 以递增顺序排列。



        示例 1：

        输入：nums1 = [[1,2],[2,3],[4,5]], nums2 = [[1,4],[3,2],[4,1]]
        输出：[[1,6],[2,3],[3,2],[4,6]]
        解释：结果数组中包含以下元素：
        - id = 1 ，对应的值等于 2 + 4 = 6 。
        - id = 2 ，对应的值等于 3 。
        - id = 3 ，对应的值等于 2 。
        - id = 4 ，对应的值等于5 + 1 = 6 。
        示例 2：

        输入：nums1 = [[2,4],[3,6],[5,5]], nums2 = [[1,3],[4,3]]
        输出：[[1,3],[2,4],[3,6],[4,3],[5,5]]
        解释：不存在共同 id ，在结果数组中只需要包含每个 id 和其对应的值。


        提示：

        1 <= nums1.length, nums2.length <= 200
        nums1[i].length == nums2[j].length == 2
        1 <= idi, vali <= 1000
        数组中的 id 互不相同
        数据均按 id 以严格递增顺序排列*/
public class MergeArrays {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new MergeArrays().mergeArrays(new int[][]{{1, 2}, {2, 3}, {4, 5}}, new int[][]{{1, 4}, {3, 2}, {4, 1}})));
    }

    //可以使用treemap
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int[] num : nums1) {
            map.put(num[0], num[1]);
        }
        for (int[] num : nums2) {
            map.put(num[0], map.getOrDefault(num[0], 0) + num[1]);
        }
        int[][] res = new int[map.size()][2];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            res[index][0] = entry.getKey();
            res[index][1] = entry.getValue();
            index++;
        }
        return res;
    }


    public int[][] mergeArrays1(int[][] nums1, int[][] nums2) {
        int n = nums1.length, m = nums2.length, i1 = 0, i2 = 0;
        List<int[]> list = new ArrayList<>();
        while (i1 < n && i2 < m) {
            if (nums1[i1][0] > nums2[i2][0]) {
                list.add(nums2[i2++]);
            } else if (nums1[i1][0] < nums2[i2][0]) {
                list.add(nums1[i1++]);
            } else {
                list.add(new int[]{nums2[i2][0], nums1[i1][1] + nums2[i2][1]});
                i1++;
                i2++;
            }
        }
        if (i1 < n) {
            for (int i = i1; i < n; i++) {
                list.add(nums1[i]);
            }
        } else {
            for (int i = i2; i < m; i++) {
                list.add(nums2[i]);
            }
        }
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            res[i][0] = list.get(i)[0];
            res[i][1] = list.get(i)[1];
        }
        return res;
    }
}
