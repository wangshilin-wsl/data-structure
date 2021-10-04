package list;
/*
给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
        为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
        如果 pos 是 -1，则在该链表中没有环。
        说明：不允许修改给定的链表。

        示例 1：

        输入：head = [3,2,0,-4], pos = 1
        输出：tail connects to node index 1
        解释：链表中有一个环，其尾部连接到第二个节点。
        思路：有环的时候，设环前的长度为a，环的长度为b
        用双指针法，一个指针为one,一个为two，two每次走两个，one每次走一个，当他们第一次相遇的时候
        设one的路程为s，则two的路程为2s，如果有环的时候two比one多走nb
        2s-s=nb;---->s=nb,说明one走了nb，这个时候将head赋值给two，让two和one都每次走一个，在相遇的时候two走了a，
        则one走了a+nb，这个相遇的点一定是环的入口点；
*/
public class DetectCycle {
    class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }
    public ListNode detectCycle(ListNode head) {
        ListNode two=head;
        ListNode one=head;
        while(true){
            if(two==null||two.next==null) return null;
            one=one.next;
            two=two.next.next;
            if(one==two) break;
        }
        two=head;
        while(true){
            if(one==two){
                return one;
            }
            one=one.next;
            two=two.next;
        }
    }
}
