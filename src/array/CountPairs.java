package array;

/*力扣：1803. 统计异或值在范围内的数对有多少
        给你一个整数数组 nums （下标 从 0 开始 计数）以及两个整数：low 和 high ，请返回 漂亮数对 的数目。

        漂亮数对 是一个形如 (i, j) 的数对，其中 0 <= i < j < nums.length 且 low <= (nums[i] XOR nums[j]) <= high 。



        示例 1：

        输入：nums = [1,4,2,7], low = 2, high = 6
        输出：6
        解释：所有漂亮数对 (i, j) 列出如下：
        - (0, 1): nums[0] XOR nums[1] = 5
        - (0, 2): nums[0] XOR nums[2] = 3
        - (0, 3): nums[0] XOR nums[3] = 6
        - (1, 2): nums[1] XOR nums[2] = 6
        - (1, 3): nums[1] XOR nums[3] = 3
        - (2, 3): nums[2] XOR nums[3] = 5
        示例 2：

        输入：nums = [9,8,4,2,1], low = 5, high = 14
        输出：8
        解释：所有漂亮数对 (i, j) 列出如下：
        - (0, 2): nums[0] XOR nums[2] = 13
        - (0, 3): nums[0] XOR nums[3] = 11
        - (0, 4): nums[0] XOR nums[4] = 8
        - (1, 2): nums[1] XOR nums[2] = 12
        - (1, 3): nums[1] XOR nums[3] = 10
        - (1, 4): nums[1] XOR nums[4] = 9
        - (2, 3): nums[2] XOR nums[3] = 6
        - (2, 4): nums[2] XOR nums[4] = 5


        提示：

        1 <= nums.length <= 2 * 104
        1 <= nums[i] <= 2 * 104
        1 <= low <= high <= 2 * 104*/
public class CountPairs {
    public static void main(String[] args) {
        System.out.println(new CountPairs().countPairs(new int[]{1, 4, 2, 7}, 2, 6));
    }

    public int countPairs(int[] nums, int low, int high) {
        //利用了异或的性质：
        //若a^b = c , 则 a^c = b, b^c=a。
        //a^a = 0
        //思路：先记录nums中最大值max,最小值min，和每个数出现的次数flags
        //从min到max开始循环，判断是否flags有值，没有直接继续，如果有，则让它与low-high异或
        //如果结果在flags中就说明这两个的异或结果在low-high中
        int res = 0, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int[] flags = new int[20001];
        for (int num : nums) {
            flags[num]++;
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        for (int i = min; i <= max; i++) {
            if (flags[i] == 0) {
                continue;
            }
            //循环大小区间，利用异或的特性，如果i ^ j = xor,如果xor在flags（即xor在nums中）
            //则说明i ^ xor = j，又因为j是循环low-high所以，i和xor是一对漂亮数对
            for (int j = low; j <= high; j++) {
                int xor = i ^ j;
                if (xor >= 1 && xor <= 20000) {
                    //i和xor可能出现多次，并且到了xor也计算了，所以结果需要/2
                    res += flags[i] * flags[xor];
                }
            }
        }
        return res / 2;
    }
}
