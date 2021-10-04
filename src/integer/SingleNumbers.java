package integer;
/*
一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。
        要求时间复杂度是O(n)，空间复杂度是O(1)。

        示例 1：
        输入：nums = [4,1,4,6]
        输出：[1,6] 或 [6,1]

        示例 2：
        输入：nums = [1,2,10,4,1,4,3,3]
        输出：[2,10] 或 [10,2]
        思路：让我们先来考虑一个比较简单的问题：
如果除了一个数字以外，其他数字都出现了两次，那么如何找到出现一次的数字？
答案很简单：全员进行异或操作即可。考虑异或操作的性质：对于两个操作数的每一位，相同结果为 00，不同结果为 11。
那么在计算过程中，成对出现的数字的所有位会两两抵消为 00，最终得到的结果就是那个出现了一次的数字。
那么这一方法如何扩展到找出两个出现一次的数字呢？如果我们可以把所有数字分成两组，使得：
两个只出现一次的数字在不同的组中；相同的数字会被分到相同的组中。
那么对两个组分别进行异或操作，即可得到答案的两个数字。这是解决这个问题的关键。
那么如何实现这样的分组呢？
算法:
先对所有数字进行一次异或，得到两个出现一次的数字的异或值。
在异或结果中找到任意为 11 的位。
根据这一位对所有的数字进行分组。
在每个组内进行异或操作，得到两个数字。


*/
public class SingleNumbers {
    public static void main(String[] args) {
        int[] ints = new SingleNumbers().singleNumbers(new int[]{4, 1, 4, 6});
        for (int anInt : ints) {
            System.out.print(anInt+" ");
        }
        System.out.println();
    }
    public int[] singleNumbers(int[] nums) {
        int a=0;
        for(int i=0;i<nums.length;i++){
            a^=nums[i];
        }
        int div=1;
        while((div&a)==0){//找到a中第一个为1的位
            div<<=1;
        }
        int n1=0,n2=0;
        for(int i=0;i<nums.length;i++) {
            if((nums[i]&div)!=0){
                n1^=nums[i];
            }else{
                n2^=nums[i];
            }
        }
        return new int[]{n1,n2};
    }
}
