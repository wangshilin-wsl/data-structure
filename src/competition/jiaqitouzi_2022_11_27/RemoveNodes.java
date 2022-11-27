package competition.jiaqitouzi_2022_11_27;

import java.util.Deque;
import java.util.LinkedList;

/*力扣：6247. 从链表中移除节点
        给你一个链表的头节点 head 。

        对于列表中的每个节点 node ，如果其右侧存在一个具有 严格更大 值的节点，则移除 node 。

        返回修改后链表的头节点 head 。


        示例 1：
        输入：head = [5,2,13,3,8]
        输出：[13,8]
        解释：需要移除的节点是 5 ，2 和 3 。
        - 节点 13 在节点 5 右侧。
        - 节点 13 在节点 2 右侧。
        - 节点 8 在节点 3 右侧。

        示例 2：
        输入：head = [1,1,1,1]
        输出：[1,1,1,1]
        解释：每个节点的值都是 1 ，所以没有需要移除的节点。


        提示：

        给定列表中的节点数目在范围 [1, 105] 内
        1 <= Node.val <= 105*/
public class RemoveNodes {
    //由于是右边是否存在大于它的，所以利用递归(先到最里面，后面往上返回)从最右边的一个节点往左判断
    int max = Integer.MIN_VALUE;

    public static void main(String[] args) {

    }

    public ListNode removeNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        head.next = removeNodes(head.next);
        max = Math.max(max, head.val);
        if (head.val >= max) {
            return head;
        } else {
            return head.next;
        }
    }

    public ListNode removeNodes1(ListNode head) {
        //使用单调栈
        Deque<ListNode> deque = new LinkedList<>();
        while (head != null) {
            while (!deque.isEmpty() && deque.peek().val < head.val){
                deque.pop();
            }
            deque.push(head);
            head = head.next;
        }

        ListNode temp = deque.pollLast(), res = temp;
        while (!deque.isEmpty()) {
            temp.next = deque.pollLast();
            temp = temp.next;
        }
        return res;
    }

     public class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }
}
