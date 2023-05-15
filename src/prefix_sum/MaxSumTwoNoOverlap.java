package prefix_sum;

/*力扣：1031. 两个非重叠子数组的最大和
        给你一个整数数组 nums 和两个整数 firstLen 和 secondLen，请你找出并返回两个非重叠 子数组 中元素的最大和，长度分别为 firstLen 和 secondLen 。

        长度为 firstLen 的子数组可以出现在长为 secondLen 的子数组之前或之后，但二者必须是不重叠的。

        子数组是数组的一个 连续 部分。



        示例 1：

        输入：nums = [0,6,5,2,2,5,1,9,4], firstLen = 1, secondLen = 2
        输出：20
        解释：子数组的一种选择中，[9] 长度为 1，[6,5] 长度为 2。
        示例 2：

        输入：nums = [3,8,1,3,2,1,8,9,0], firstLen = 3, secondLen = 2
        输出：29
        解释：子数组的一种选择中，[3,8,1] 长度为 3，[8,9] 长度为 2。
        示例 3：

        输入：nums = [2,1,5,6,0,9,5,0,3,8], firstLen = 4, secondLen = 3
        输出：31
        解释：子数组的一种选择中，[5,6,0,9] 长度为 4，[0,3,8] 长度为 3。


        提示：

        1 <= firstLen, secondLen <= 1000
        2 <= firstLen + secondLen <= 1000
        firstLen + secondLen <= nums.length <= 1000
        0 <= nums[i] <= 1000*/
public class MaxSumTwoNoOverlap {
    public static void main(String[] args) {
        System.out.println(new MaxSumTwoNoOverlap().maxSumTwoNoOverlap(new int[]{0, 6, 5, 2, 2, 5, 1, 9, 4}, 1, 2));
    }

    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        //https://leetcode.cn/problems/maximum-sum-of-two-non-overlapping-subarrays/solution/tu-jie-mei-you-si-lu-yi-zhang-tu-miao-do-3lli/
        int n = nums.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        //该方法是求先fistLen，后secondLen的情况下最大值,分为左边，右边两部分
        //sumA是左边最大值
        int max = 0, sumA = 0, sumB = 0;
        //枚举所有右边的部分，i是结束位置,左边部分为[i - secondLen - firstLen, i - secondLen]
        //枚举所有右边部分为[i - secondLen,i]
        //由于右边部分的左端点i-secondLen是在不断增大的，所以在过程中记录最左边的部分的最大值
        //因为左边部分是[i - secondLen - firstLen,i - secondLen]一直是落后右边部分的，所以左右两部分是没有重合的
        for (int i = firstLen + secondLen; i < prefix.length; i++) {
            sumA = Math.max(sumA, prefix[i - secondLen] - prefix[i - secondLen - firstLen]);
            sumB = Math.max(sumB, prefix[i - firstLen] - prefix[i - secondLen - firstLen]);
            max = Math.max(max, sumA + prefix[i] - prefix[i - secondLen]);
            max = Math.max(max, sumB + prefix[i] - prefix[i - firstLen]);
        }
        return max;
    }


    public int maxSumTwoNoOverlap2(int[] nums, int firstLen, int secondLen) {
        //https://leetcode.cn/problems/maximum-sum-of-two-non-overlapping-subarrays/solution/tu-jie-mei-you-si-lu-yi-zhang-tu-miao-do-3lli/
        int n = nums.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        return Math.max(max2(prefix, firstLen, secondLen), max2(prefix, secondLen, firstLen));
    }
    public int max2(int[] prefix, int firstLen, int secondLen) {
        //该方法是求先fistLen，后secondLen的情况下最大值,分为左边，右边两部分
        //sumA是左边最大值
        int max = 0, sumA = 0;
        //枚举所有右边的部分，i是结束位置,左边部分为[i - secondLen - firstLen, i - secondLen]
        //枚举所有右边部分为[i - secondLen,i]
        //由于右边部分的左端点i-secondLen是在不断增大的，所以在过程中记录最左边的部分的最大值
        //因为左边部分是[i - secondLen - firstLen,i - secondLen]一直是落后右边部分的，所以左右两部分是没有重合的
        for (int i = firstLen + secondLen; i < prefix.length; i++) {
            sumA = Math.max(sumA, prefix[i - secondLen] - prefix[i - secondLen - firstLen]);
            max = Math.max(max, sumA + prefix[i] - prefix[i - secondLen]);
        }
        return max;
    }




    public int maxSumTwoNoOverlap3(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        return Math.max(max3(n, prefix, firstLen, secondLen), max3(n, prefix, secondLen, firstLen));
    }
    public int max3(int n, int[] prefix, int firstLen, int secondLen) {
        int max = 0;
        for (int i = 0; i < n && i + firstLen - 1 < n; i++) {
            int index = i + firstLen - 1;
            int sum1 = prefix[index + 1] - prefix[i];
            //这个地方有一些重复的循环
            //最右边的右边部分[n-secondLen,n]在每次左边部分都比较了一次，所以可以优化
            //优化，枚举右边部分，记录左边部分的最大值，所以只需要遍历一次
            for (int j = index + 1; j < n && j + secondLen - 1 < n; j++) {
                int sum2 = prefix[j + secondLen] - prefix[j];
                max = Math.max(max, sum1 + sum2);
            }
        }
        return max;
    }
}
