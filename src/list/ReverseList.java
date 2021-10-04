package list;
     /*   定义一个函数，输入一个链表的头节点，
        反转该链表并输出反转后链表的头节点。

        示例:
        输入: 1->2->3->4->5->NULL
        输出: 5->4->3->2->1->NULL

        思路：     定义两个指针： prepre 和 curcur ；prepre 在前 curcur 在后。
                每次让 prepre 的 nextnext 指向 curcur ，实现一次局部反转
                局部反转完成之后， prepre 和 curcur 同时往前移动一个位置
                循环上述过程，直至 prepre 到达链表尾部
        */
public class ReverseList {
    public  class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

    public static void main(String[] args) {
        ListNode node1 = new ReverseList().new ListNode(1);
        ListNode node2 = new ReverseList().new ListNode(2);
        ListNode node3 = new ReverseList().new ListNode(3);
        ListNode node4 = new ReverseList().new ListNode(4);
        ListNode node5 = new ReverseList().new ListNode(5);
        node1.next=node2;node2.next=node3;node3.next=node4;node4.next=node5;
        ListNode listNode = new ReverseList().reverseList(node1);
        while(listNode!=null){
            System.out.print(listNode.val+" ");
            listNode=listNode.next;
        }
    }
    public  ListNode reverseList(ListNode head) {
        ListNode one=head;
        ListNode two=null;
        while(one!=null){
            ListNode temp=one.next;
            one.next=two;
            two=one;
            one=temp;
        }
        return two;
    }
}
