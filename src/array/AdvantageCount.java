package array;

/*力扣870题
    给定两个大小相等的数组nums1和nums2，
    nums1相对于 nums的优势可以用满足nums1[i] > nums2[i]
    的索引 i的数目来描述。
     返回 nums1的任意排列，使其相对于 nums2的优势最大化。

        示例 1：
        输入：nums1 = [2,7,11,15], nums2 = [1,10,4,11]
        输出：[2,11,7,15]

        示例 2：
        输入：nums1 = [12,24,8,32], nums2 = [13,25,32,11]
        输出：[24,32,8,12]

        提示：
        1 <= nums1.length <= 10^5
        nums2.length == nums1.length
        0 <= nums1[i], nums2[i] <= 10^9

        思路：田忌赛马
        类比田忌赛马，数组中最小值即下等马，最大值上等马
        每次用nums1中的下等马去跟nums2中的下等马pk
        如果干的过就干，干不过就用nums1中的下等马去当炮灰，去干nums2中的上等马
        在本题中，如果干的过，就用nums2中的下等马的下标当做nums1中的下等马的下标
        干不过，就用nums2中的上等马的下标当作nums1中的下等马的下标
        */

import java.util.Arrays;

public class AdvantageCount {

    public static void main(String[] args) {
        int[] ints = new AdvantageCount().advantageCount(new int[]{2, 7, 11, 15}, new int[]{1, 10, 4, 11});
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
    }

    public int[] advantageCount(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        int len = nums2.length;
        Integer[] index = new Integer [len];
        int[] res = new int[len];
        for(int i = 0; i < len; i++){
            index[i] = i;
        }
        //注意比较器引用的数组需要是对象，所以不能放基本数据类型的数组
        //根据nums2的值升序，来排序nums2的值的对应下标
        //排序后index[]中第一个元素是nums2中最小值的下标，第二个元素是nums2中第二小值的下标
        Arrays.sort(index , (a,b) -> (nums2[a] - nums2[b]));
        int left = 0;
        int right = len - 1;
        //遍历nums1
        for(int num : nums1){
            //index[left] 为 nums2中最小值的下标，index[right] 为nums2中最大值的下标
            //如果num比nums2中的最小值大，则本次res中num对应的下标为index[left],然后left++
            //否则本次res中num对应的下标为index[right],然后right--
            int i = num > nums2[index[left]] ? index[left++] : index[right--];
            res[i] = num;
        }
        return res;

    }
}
