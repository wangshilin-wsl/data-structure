package array;

/*力扣：982. 按位与为零的三元组
        给你一个整数数组 nums ，返回其中 按位与三元组 的数目。

        按位与三元组 是由下标 (i, j, k) 组成的三元组，并满足下述全部条件：

        0 <= i < nums.length
        0 <= j < nums.length
        0 <= k < nums.length
        nums[i] & nums[j] & nums[k] == 0 ，其中 & 表示按位与运算符。

        示例 1：

        输入：nums = [2,1,3]
        输出：12
        解释：可以选出如下 i, j, k 三元组：
        (i=0, j=0, k=1) : 2 & 2 & 1
        (i=0, j=1, k=0) : 2 & 1 & 2
        (i=0, j=1, k=1) : 2 & 1 & 1
        (i=0, j=1, k=2) : 2 & 1 & 3
        (i=0, j=2, k=1) : 2 & 3 & 1
        (i=1, j=0, k=0) : 1 & 2 & 2
        (i=1, j=0, k=1) : 1 & 2 & 1
        (i=1, j=0, k=2) : 1 & 2 & 3
        (i=1, j=1, k=0) : 1 & 1 & 2
        (i=1, j=2, k=0) : 1 & 3 & 2
        (i=2, j=0, k=1) : 3 & 2 & 1
        (i=2, j=1, k=0) : 3 & 1 & 2
        示例 2：

        输入：nums = [0,0,0]
        输出：27


        提示：

        1 <= nums.length <= 1000
        0 <= nums[i] < 216*/
public class CountTriplets {
    public static void main(String[] args) {
        new CountTriplets().countTriplets(new int[]{2,1,3});
    }
    public int countTriplets(int[] nums) {
        //思路：先用一个双层循环找出所有二元数组对应的值，并记录他出现的次数
        //在循环一次nums，查看[0, 2^16)中能和它&值为0的
        //由题意可知最大数不超过2^16，按位与结果也不会超过2^16
        int len = nums.length, sum = 0, n = 1 << 16;
        //记录nums所有二元数组按位与的结果次数
        int[] cnt = new int[n];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                cnt[nums[i] & nums[j]]++;
            }
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < n; j++) {
                if ((nums[i] & j) == 0) {
                    sum += cnt[j];
                }
            }
        }
        return sum;
    }
}
