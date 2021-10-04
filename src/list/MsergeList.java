package list;

public class MsergeList {
    public static class ListNode {
      int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public static void main(String[] args) {
        ListNode root1=new ListNode(1);
        ListNode l2=new ListNode(2);
        ListNode l3=new ListNode(4);
        root1.next=l2;
        l2.next=l3;
        ListNode root2=new ListNode(1);
        ListNode l4=new ListNode(3);
        ListNode l5=new ListNode(4);
        root2.next=l4;
        l4.next=l5;
        ListNode listNode = mergeTwoLists(root1, root2);
        while(listNode!=null){
            System.out.print(listNode.val+" ");
            listNode=listNode.next;

        }
    }
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode l3=new ListNode();
        ListNode l4=l3;//l4的作用是记录新链表的最后一个结点便于插入
        while(l1!=null&&l2!=null){
            if(l1.val<=l2.val){
                ListNode temp=new ListNode(l1.val);
                l4.next=temp;
                l4=temp;
                l1=l1.next;
            }else{
                ListNode temp=new ListNode(l2.val);
                l4.next=temp;
                l4=temp;
                l2=l2.next;
            }
        }
        l4.next=(l1==null) ? l2 : l1;
        return l3.next;
    }
}
