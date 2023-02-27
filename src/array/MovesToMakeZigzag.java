package array;

/*力扣：1144. 递减元素使数组呈锯齿状
        给你一个整数数组 nums，每次 操作 会从中选择一个元素并 将该元素的值减少 1。

        如果符合下列情况之一，则数组 A 就是 锯齿数组：

        每个偶数索引对应的元素都大于相邻的元素，即 A[0] > A[1] < A[2] > A[3] < A[4] > ...
        或者，每个奇数索引对应的元素都大于相邻的元素，即 A[0] < A[1] > A[2] < A[3] > A[4] < ...
        返回将数组 nums 转换为锯齿数组所需的最小操作次数。



        示例 1：

        输入：nums = [1,2,3]
        输出：2
        解释：我们可以把 2 递减到 0，或把 3 递减到 1。
        示例 2：

        输入：nums = [9,6,1,6,2]
        输出：4


        提示：

        1 <= nums.length <= 1000
        1 <= nums[i] <= 1000*/
public class MovesToMakeZigzag {
    public static void main(String[] args) {
        System.out.println(new MovesToMakeZigzag().movesToMakeZigzag(new int[]{1, 2, 3}));
    }


    public int movesToMakeZigzag(int[] nums) {
        //贪心，思路：最后的结果要么偶数是小于周围的，要么是奇数是小于周围的。
        return Math.min(maxStep(nums, 0), maxStep(nums, 1));
    }
    public int maxStep(int[] nums, int mod){
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((i & 1) != mod) {
                continue;
            }
            int min = Integer.MAX_VALUE;
            //找到两边最大的一个
            if (i - 1 >= 0) {
                min = Math.min(min, nums[i - 1]);
            }
            if (i + 1 < nums.length) {
                min = Math.min(min, nums[i + 1]);
            }
            ans += min <= nums[i] ? Math.abs(min - nums[i]) + 1 : 0;
        }
        return ans;
    }
}
