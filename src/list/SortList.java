package list;
//链表的插入排序
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
public class SortList {
    public static void main(String[] args) {
        ListNode l1=new ListNode(4);
        ListNode l2=new ListNode(2);
        ListNode l3=new ListNode(1);
        ListNode l4=new ListNode(3);
        l1.next=l2;l2.next=l3;l3.next=l4;
        ListNode node = new SortList().sortList(l1);
        while(node!=null){
            System.out.println(node.val);
            node=node.next;
        }
    }
    public ListNode sortList(ListNode head) {
        if(head==null) return null;
        ListNode index=new ListNode(0);
        index.next=head;
        ListNode last=head;
        ListNode curr=head.next;
        while(curr!=null){
            if(last.val<=curr.val) last=last.next;
            else{
                ListNode pre=index;
                while(pre.next.val<=curr.val) pre=pre.next;
                last.next=curr.next;
                curr.next=pre.next;
                pre.next=curr;
            }
            curr=last.next;
        }
        return index.next;
    }
}
