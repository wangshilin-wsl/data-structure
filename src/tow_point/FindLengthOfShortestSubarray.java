package tow_point;

/*力扣：1574. 删除最短的子数组使剩余数组有序
        给你一个整数数组 arr ，请你删除一个子数组（可以为空），使得 arr 中剩下的元素是 非递减 的。

        一个子数组指的是原数组中连续的一个子序列。

        请你返回满足题目要求的最短子数组的长度。



        示例 1：

        输入：arr = [1,2,3,10,4,2,3,5]
        输出：3
        解释：我们需要删除的最短子数组是 [10,4,2] ，长度为 3 。剩余元素形成非递减数组 [1,2,3,3,5] 。
        另一个正确的解为删除子数组 [3,10,4] 。
        示例 2：

        输入：arr = [5,4,3,2,1]
        输出：4
        解释：由于数组是严格递减的，我们只能保留一个元素。所以我们需要删除长度为 4 的子数组，要么删除 [5,4,3,2]，要么删除 [4,3,2,1]。
        示例 3：

        输入：arr = [1,2,3]
        输出：0
        解释：数组已经是非递减的了，我们不需要删除任何元素。
        示例 4：

        输入：arr = [1]
        输出：0


        提示：

        1 <= arr.length <= 10^5
        0 <= arr[i] <= 10^9
        题解：https://leetcode.cn/problems/shortest-subarray-to-be-removed-to-make-array-sorted/solution/dong-hua-yi-xie-jiu-cuo-liang-chong-xie-iijwz/
        */
public class FindLengthOfShortestSubarray {
    public static void main(String[] args) {
        System.out.println(new FindLengthOfShortestSubarray().findLengthOfShortestSubarray(new int[]{1, 2, 3, 10, 4, 2, 3, 5}));
    }

    public int findLengthOfShortestSubarray(int[] arr) {
        //思路：双指针，枚举左端点left，右移右端点right,删除[left+1, right-1]其中的元素使得arr非递减
        //那么保证[0,left]非递减，[right,n-1]非递减
        //[right,n-1]非递减，则是第一步，从后往前遍历，找到第一个不符合的
        //[0,left]非递减,在枚举的时候遇到arr[i]<arr[i -1]则退出
        //1、right = n - 1，从后往前遍历，找到第一个arr[i - 1] > arr[i]，则[right, n- 1]是非递减的
        //2、枚举所有的左端点left，对于left，右移right，找到第一个arr[right] >= arr[left],删除[left+1,right-1]，则剩下的就是非递减的了[0,left][right, n- 1]
        //3、对于left，right不需要前移的理由，[0,left]是非递减的，arr[left] >=arr[left-1],对于arr[left-1]的right来说arr[left-1]<=arr[right]
        //所以arr[left]>= right之前的，所以right可以不用往前走的。
        int n = arr.length, r = n - 1;
        while (r > 0 && arr[r] >= arr[r - 1]) {
            r--;
        }
        if (r == 0) {
            return 0;
        }
        int res = r;
        for (int left = 0; left < n; left++) {
            while (r < n && arr[left] > arr[r]) {
                r++;
            }
            res = Math.min(res, r - left - 1);
            if (left + 1 < n && arr[left] > arr[left + 1]) {
                break;
            }
        }
        return res;
    }
}
