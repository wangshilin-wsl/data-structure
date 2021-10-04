package list;
           /* 输入两个链表，找出它们的第一个公共节点。
            如果两个链表没有交点，返回 null.
            在返回结果后，两个链表仍须保持原有的结构。
            可假定整个链表结构中没有循环。
            程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
            思路：
            方法一
(时间复杂度O(m+n+max(m,n))),空间复杂度O(1))
1.遍历l1,l2俩个链表计算出长度。
2.链表长的指针向后移动到俩个链表长度差的位置。
3.一一进行匹配
总结：让长的链表从和短链表一样长度位置开始匹配
本题使用以下方法
方法二
时间复杂度O(n+m),空间复杂度O(1)
设l1的不同部分为长度n,l2长度为m,俩链表相同部分为t;
则l1=n+t,l2=m+t;
可知l1+m=l2+n;
总结：两指针遍历俩个链表，至少第二次会进行匹配。若长度相同，则第一次进行匹配。

*/

public class GetIntersectionNode {
    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }
    public static void main(String[] args) {
        ListNode n1=new ListNode(0);
        ListNode n2=new ListNode(1);
        ListNode n3=new ListNode(2);
        ListNode n4=new ListNode(3);
        ListNode n5=new ListNode(4);
        ListNode n6=new ListNode(9);
        n1.next=n6;n6.next=n2;n2.next=n3;n3.next=n5;
        n4.next=n3;
        System.out.println(getIntersectionNode(n1,n4).val);
    }
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headB==null||headA==null) return null;
        ListNode a=headA;
        ListNode b=headB;
        while(a!=b){
            a=(a==null)? headB:a.next;
            b=(b==null)?headA:b.next;
        }
        return a;
    }
}
