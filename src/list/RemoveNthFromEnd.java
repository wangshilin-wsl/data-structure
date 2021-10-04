package list;
/*给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

        示例：
        给定一个链表: 1->2->3->4->5, 和 n = 2.
        当删除了倒数第二个节点后，链表变为 1->2->3->5.
        说明：
        给定的 n 保证是有效的。
        进阶：
        你能尝试使用一趟扫描实现吗？
        在对链表进行操作时，一种常用的技巧是添加一个哑节点（dummy node），它的
        next 指针指向链表的头节点。这样一来，我们就不需要对头节点进行特殊的判断了。

        */

public class RemoveNthFromEnd {
    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    public static void main(String[] args) {
        ListNode l1=new ListNode(1);
        ListNode l2=new ListNode(2);
        ListNode l3=new ListNode(3);
        ListNode l4=new ListNode(4);
        ListNode l5=new ListNode(5);
        l1.next=l2;l2.next=l3;l3.next=l4;l4.next=l5;
        ListNode listNode = removeNthFromEnd(l1, 2);
        while(listNode!=null){
            System.out.print(listNode.val+"->");
            listNode=listNode.next;
        }
    }
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp=new ListNode();
        temp.next=head;
        int i=0;
        ListNode l1=temp;
        ListNode l2=temp;
        while(i!=n+1){
            l1=l1.next;
            i++;
        }
        while(l1!=null){
            l1=l1.next;
            l2=l2.next;
        }
        l2.next=l2.next.next;
        return temp.next;
    }
}
