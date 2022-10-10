package dynamic_programming;

/*力扣：801. 使序列递增的最小交换次数
        我们有两个长度相等且不为空的整型数组 nums1 和 nums2 。
        在一次操作中，我们可以交换 nums1[i] 和 nums2[i]的元素。
        例如，如果 nums1 = [1,2,3,8] ， nums2 =[5,6,7,4] ，
        你可以交换 i = 3 处的元素，得到 nums1 =[1,2,3,4] 和 nums2 =[5,6,7,8] 。
        返回 使 nums1 和 nums2 严格递增 所需操作的最小次数 。
        数组 arr 严格递增 且  arr[0] < arr[1] < arr[2] < ... < arr[arr.length - 1] 。

        注意：
        用例保证可以实现操作。

        示例 1:
        输入: nums1 = [1,3,5,4], nums2 = [1,2,3,7]
        输出: 1
        解释:
        交换 A[3] 和 B[3] 后，两个数组如下:
        A = [1, 3, 5, 7] ， B = [1, 2, 3, 4]
        两个数组均为严格递增的。

        示例 2:
        输入: nums1 = [0,3,5,8,9], nums2 = [2,1,4,6,9]
        输出: 1

        提示:
        2 <= nums1.length <= 10^5
        nums2.length == nums1.length
        0 <= nums1[i], nums2[i] <= 2 * 10^5
        */
public class MinSwap {
    public int minSwap(int[] nums1, int[] nums2) {
        int res = 0, n = nums1.length;
        //dp[i][0]表示到位置i为止使数组nums1和nums2满足严格递增并且位置i不进行交换操作的最小操作数
        //dp[i][1]表示到位置i为止使数组nums1和nums2满足严格递增并且位置i进行交换操作的最小操作数
        //因为后面一个状态只依赖前面的一个状态，所以可以用一个常数空间来存储，a,b
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = 1;
        for (int i = 1; i < n; i++) {
            boolean same = (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]);
            boolean noSame = (nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]);
            if(same && noSame){
                //两个都满足
                //dp[i][0] = min(dp[i - 1][0], dp[i - 1][1])   i不交换，i-1交换或者不交换都可以 ，取最小的
                //dp[i][1] = min(dp[i - 1][0], dp[i - 1][1]) + 1  i交换，i-1交换或者不交换都可以 ，取最小的
                dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + 1;
            }else if(same){
                //满足同一个里面前面大于后面，不满足不同一个里面前面大于后面
                //dp[i][0] = dp[i - 1][0]  i不交换，i-1也不能交换
                //dp[i][1] = dp[i-1][1] + 1  i交换，i-1必须交换
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i-1][1] + 1;
            }else if (noSame){
                //满足不同一个里面前面大于后面，不满足同一个里面前面大于后面
                //dp[i][0] = dp[i - 1][1]  i不交换，i-1必须交换
                //dp[i][1] = dp[i - 1][0] + 1  i交换，i-1必须不交换
                dp[i][0] = dp[i - 1][1];
                dp[i][1] = dp[i - 1][0] + 1;
            }
        }
        return Math.min(dp[n - 1][0], dp[n - 1][1]);
    }


    //优化
    public int minSwap1(int[] nums1, int[] nums2) {
        //dp[i][0]表示到位置i为止使数组nums1和nums2满足严格递增并且位置i不进行交换操作的最小操作数
        //dp[i][1]表示到位置i为止使数组nums1和nums2满足严格递增并且位置i进行交换操作的最小操作数
        //因为后面一个状态只依赖前面的一个状态，所以可以用一个常数空间来存储，a,b，注意在for中要用两个临时变量
        int n = nums1.length, one = 0, two = 1;

        for (int i = 1; i < n; i++) {
            int tempOne = one, tempTwo = two;
            boolean same = (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]);
            boolean noSame = (nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]);
            if(same && noSame){
                //两个都满足
                //dp[i][0] = min(dp[i - 1][0], dp[i - 1][1])   i不交换，i-1交换或者不交换都可以 ，取最小的
                //dp[i][1] = min(dp[i - 1][0], dp[i - 1][1]) + 1  i交换，i-1交换或者不交换都可以 ，取最小的
                one = Math.min(tempOne, tempTwo);
                two= Math.min(tempOne, tempTwo) + 1;
            }else if(same){
                //满足同一个里面前面大于后面，不满足不同一个里面前面大于后面
                //dp[i][0] = dp[i - 1][0]  i不交换，i-1也不能交换
                //dp[i][1] = dp[i-1][1] + 1  i交换，i-1必须交换
                two = tempTwo + 1;
            }else if (noSame){
                //满足不同一个里面前面大于后面，不满足同一个里面前面大于后面
                //dp[i][0] = dp[i - 1][1]  i不交换，i-1必须交换
                //dp[i][1] = dp[i - 1][0] + 1  i交换，i-1必须不交换
                one = tempTwo;
                two = tempOne + 1;
            }
        }
        return Math.min(one, two);
    }

}
