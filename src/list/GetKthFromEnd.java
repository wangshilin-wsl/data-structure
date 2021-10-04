package list;
     /*   输入一个链表，输出该链表中倒数第k个节点。
        为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
        例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。
        这个链表的倒数第3个节点是值为4的节点。

         
        示例：
        给定一个链表: 1->2->3->4->5, 和 k = 2.
        返回链表 4->5.

        思路：双指针法
*/
public class GetKthFromEnd {
    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

    public static void main(String[] args) {
        ListNode l1=new ListNode(1);
        ListNode l2=new ListNode(2);
        ListNode l3=new ListNode(3);
        ListNode l4=new ListNode(4);
        ListNode l5=new ListNode(5);
        l1.next=l2;l2.next=l3;l3.next=l4;l4.next=l5;
        ListNode node = getKthFromEnd(l1, 2);
        while(node!=null){
            System.out.print(node.val+" ");
            node=node.next;
        }
    }
    public static ListNode getKthFromEnd(ListNode head, int k) {
        ListNode first=head,second=head;
        int i=1;
        while(first!=null){
            if(i==k+1) break;
            i++;
            first=first.next;
        }
        while(first!=null){
            second=second.next;
            first=first.next;
        }
        return second;
    }
}
