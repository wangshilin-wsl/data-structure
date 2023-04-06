package array;

import java.util.Arrays;

/*力扣：1053. 交换一次的先前排列
        给你一个正整数数组 arr（可能存在重复的元素），请你返回可在 一次交换（交换两数字 arr[i] 和 arr[j] 的位置）后得到的、按字典序排列小于 arr 的最大排列。

        如果无法这么操作，就请返回原数组。



        示例 1：

        输入：arr = [3,2,1]
        输出：[3,1,2]
        解释：交换 2 和 1
        示例 2：

        输入：arr = [1,1,5]
        输出：[1,1,5]
        解释：已经是最小排列
        示例 3：

        输入：arr = [1,9,4,6,7]
        输出：[1,7,4,6,9]
        解释：交换 9 和 7


        提示：

        1 <= arr.length <= 104
        1 <= arr[i] <= 104*/
public class PrevPermOpt1 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new PrevPermOpt1().prevPermOpt1(new int[]{1, 9, 4, 6, 7})));
    }
    public int[] prevPermOpt1(int[] arr) {
        //思路：需要找到比arr小的所有排列中的最大一个排列
        //假设左边找到的索引为l,右边的索引为r，原排列为arr，交换后的排列为afterArr
        //因为需要满足afterArr小于arr，则arr[l]的元素一定要比arr[r]大
        //需要满足afterArr最大，则尽量l偏右,则从后往前遍历，找到第一个严格大于右边的索引index
        int n = arr.length, index = -1;
        //找到第一个比右边大的
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            //找到index右边小于它的最大值，如果有多个相同的最大值则取最左边的一个
            int max = arr[index + 1], maxIndex = index + 1;
            for (int i = index + 2; i < n; i++) {
                if (arr[i] < arr[index] && arr[i] > max) {
                    max = arr[i];
                    maxIndex = i;
                }
            }

            int temp = arr[index];
            arr[index] = arr[maxIndex];
            arr[maxIndex] = temp;
        }
        return arr;
    }
}
