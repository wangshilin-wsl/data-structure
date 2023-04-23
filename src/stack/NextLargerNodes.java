package stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*力扣：1019. 链表中的下一个更大节点
        给定一个长度为 n 的链表 head

        对于列表中的每个节点，查找下一个 更大节点 的值。也就是说，对于每个节点，找到它旁边的第一个节点的值，这个节点的值 严格大于 它的值。

        返回一个整数数组 answer ，其中 answer[i] 是第 i 个节点( 从1开始 )的下一个更大的节点的值。如果第 i 个节点没有下一个更大的节点，设置 answer[i] = 0 。



        示例 1：



        输入：head = [2,1,5]
        输出：[5,5,0]
        示例 2：



        输入：head = [2,7,4,3,5]
        输出：[7,0,5,5,0]


        提示：

        链表中节点数为 n
        1 <= n <= 104
        1 <= Node.val <= 109*/
public class NextLargerNodes {

    //优化，一次遍历
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> list = new ArrayList<>();
        //非严格单调递增栈
        Deque<int[]> stack = new ArrayDeque<>();
        int index = -1;
        while (head != null) {
            index++;
            //填充位置，后面直接覆盖
            list.add(0);
            //栈中存储的是需要对比的，找到栈中小于当前的，所以需要记录索引
            while (!stack.isEmpty() && stack.peek()[0] < head.val){
                list.set(stack.pop()[1], head.val);
            }
            stack.push(new int[]{head.val, index});
            head = head.next;
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
    //单调递增栈，先遍历链表将所有元素装到一个list中，在从大到小的遍历，使用栈存储最右边大于它的数字
    public int[] nextLargerNodes1(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int size = list.size();
        int[] res = new int[size];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = size - 1; i >= 0; i--) {
            while (!deque.isEmpty() && deque.peek() <= list.get(i)) {
                deque.pop();
            }
            res[i] = deque.isEmpty() ? 0 : deque.peek();
            deque.push(list.get(i));
        }

        return res;
    }
}
 class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
