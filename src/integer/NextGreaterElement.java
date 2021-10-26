package integer;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
给你两个没有重复元素的数组nums1和nums2,其中nums1是nums2的子集。
        请你找出 nums1中每个元素在nums2中的下一个比其大的值。
        nums1中数字x的下一个更大元素是指x在nums2中对应位置的右边的第一个比x大的元素。
        如果不存在，对应位置输出 -1 。

        示例 1:

        输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
        输出: [-1,3,-1]
        解释:
        对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
        对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
        对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
        示例 2:

        输入: nums1 = [2,4], nums2 = [1,2,3,4].
        输出: [3,-1]
        解释:
            对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
        对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
         

        提示：

        1 <= nums1.length <= nums2.length <= 1000
        0 <= nums1[i], nums2[i] <= 10^4
        nums1和nums2中所有整数 互不相同
        nums1 中的所有整数同样出现在 nums2 中

        思路：单调栈+hashmap
        从左右边遍历nums2，栈是单调递增的，如果nums2[i]比栈顶元素大的，则先将栈顶元素出栈，
        一直到栈顶元素比nums2[i]大的，这个栈顶元素为nums2[i]右边第一个比他大的，如果栈为空
        则直接为-1，将(nums2[i], stack.peek())入map，map是存储nums2[i]和右边第一个比他大的数字。

*/

public class NextGreaterElement {
    public static void main(String[] args) {

    }
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>(nums2.length);
        for (int i = nums2.length - 1; i >= 0 ; i--) {
            while(!stack.isEmpty() && stack.peek() < nums2[i]){
                stack.pop();
            }
            map.put(nums2[i], stack.isEmpty()? -1:stack.peek());
            stack.push(nums2[i]);
        }
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }
}
