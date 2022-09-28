import java.util.ArrayDeque;
import java.util.Queue;

class ListNode {
      int val;
     ListNode next;
      ListNode(int x) { val = x; }
  }
public class Main {
    public static void main(String[] args) {
//        System.arraycopy();
//        Arrays.copyOf();
//        Arrays.copyOfRange()
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println(queue.poll());
        String a = "abc";
        String b = new String("abc");
        System.out.println(a == b);

        ListNode l1=new ListNode(4);
        ListNode l2=new ListNode(2);
        ListNode l3=new ListNode(1);
        ListNode l4=new ListNode(3);
        l1.next=l2;l2.next=l3;l3.next=l4;
        ListNode node = new Main().insertionSortList(l1);
        while(node!=null){
            System.out.println(node.val);
            node=node.next;
        }
    }
    public ListNode insertionSortList(ListNode head) {
        ListNode index=new ListNode(0);
        index.next=head;
        ListNode lastNode=head;
        ListNode curr=head.next;
        while(curr!=null){
            if(lastNode.val<=curr.val){
                lastNode=lastNode.next;
            }else{
                ListNode temp=index;
                while(temp.next.val<=curr.val){
                    temp=temp.next;
                }
                lastNode.next=curr.next;
                curr.next=temp.next;
                temp.next=curr;
            }
            curr=lastNode.next;
        }
        return  index.next;
    }

}
