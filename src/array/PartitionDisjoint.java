package array;

/*力扣：915. 分割数组
        给定一个数组 nums ，将其划分为两个连续子数组 left 和 right， 使得：
        left 中的每个元素都小于或等于 right 中的每个元素。
        left 和 right 都是非空的。
        left 的长度要尽可能小。
        在完成这样的分组后返回 left 的 长度 。
        用例可以保证存在这样的划分方法。

        示例 1：
        输入：nums = [5,0,3,8,6]
        输出：3
        解释：left = [5,0,3]，right = [8,6]

        示例 2：
        输入：nums = [1,1,1,0,6,12]
        输出：4
        解释：left = [1,1,1,0]，right = [6,12]

        提示：

        2 <= nums.length <= 105
        0 <= nums[i] <= 106
        可以保证至少有一种方法能够按题目所描述*/
public class PartitionDisjoint {
    public static void main(String[] args) {
        System.out.println(new PartitionDisjoint().partitionDisjoint1(new int[]{5,0,3,8,6}));
    }
    /**
     * @methodName partitionDisjoint1
     * @Author WSL
     * @Description  暴力法优化，第一次循环把每个位置对应的最小值记录下来，第二次遍历，记录当前最大值，如果当前最大值小于下一个位置的最小值，就返回。
     * @Date 20:56 2022/10/24
     * @Param nums
     * @return int
     **/
    public int partitionDisjoint1(int[] nums) {
        int[] minArr = new int[nums.length];
        int min = Integer.MAX_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            min = Math.min(min, nums[i]);
            minArr[i] = min;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(max, nums[i]);
            if (max <= minArr[i + 1]){
                return i + 1;
            }
        }
        return -1;
    }
    /**
     * @methodName partitionDisjoint2
     * @Author WSL
     * @Description  一次循环，用两个变量记录最左边的边界索引和最大值
     * @Date 20:56 2022/10/24
     * @Param nums
     * @return int
     **/
    public int partitionDisjoint2(int[] nums) {
        int n = nums.length;
        int left = 0, leftMax = nums[0], curMax = nums[0];
        for (int i = 0; i < n; i++) {
            curMax = Math.max(curMax, nums[i]);
            if (nums[i] < leftMax){
                leftMax = curMax;
                left = i;
            }
        }
        return left + 1;
    }
}
