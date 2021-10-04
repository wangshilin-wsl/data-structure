package integer;
/*给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
        说明：
        你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

        示例 1:
        输入: [2,2,1]
        输出: 1

        示例 2:
        输入: [4,1,2,1,2]
        输出: 4
        思路：相同的两个数异或结果为0,0与任何数异或等于它本身，这题把所有的数进行异或得到的数为答案
        */
public class SingleNumber {
    public static void main(String[] args) {
        System.out.println(new SingleNumber().singleNumber(new int[]{4,1,2,1,2}));
    }
    public int singleNumber(int[] nums) {
        int res=0;
        for(int i=0;i<nums.length;i++){
            res^=nums[i];
        }
        return res;
    }
}
