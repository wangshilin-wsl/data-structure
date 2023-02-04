package competition.likou_2023_01_08;

/*力扣：6283. 正整数和负整数的最大计数 显示英文描述
        给你一个按 非递减顺序 排列的数组 nums ，返回正整数数目和负整数数目中的最大值。

        换句话讲，如果 nums 中正整数的数目是 pos ，而负整数的数目是 neg ，返回 pos 和 neg二者中的最大值。
        注意：0 既不是正整数也不是负整数。



        示例 1：

        输入：nums = [-2,-1,-1,1,2,3]
        输出：3
        解释：共有 3 个正整数和 3 个负整数。计数得到的最大值是 3 。
        示例 2：

        输入：nums = [-3,-2,-1,0,0,1,2]
        输出：3
        解释：共有 2 个正整数和 3 个负整数。计数得到的最大值是 3 。
        示例 3：

        输入：nums = [5,20,66,1314]
        输出：4
        解释：共有 4 个正整数和 0 个负整数。计数得到的最大值是 4 。


        提示：

        1 <= nums.length <= 2000
        -2000 <= nums[i] <= 2000
        nums 按 非递减顺序 排列。*/
public class MaximumCount {
    public static void main(String[] args) {
        System.out.println(new MaximumCount().maximumCount(new int[]{-2,-1,-1,1,2,3}));
    }

    public int maximumCount(int[] nums) {
        int a = 0,b = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
           if (nums[i] > 0) {
               a++;
           }
            if (nums[i] < 0) {
                b++;
            }
        }
        return Math.max(a, b);
    }
}
