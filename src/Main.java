import java.util.ArrayDeque;
import java.util.Arrays;
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

    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] res = new int[n];
        Arrays.sort(nums1);
        Integer[] ids = new Integer[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
        Arrays.sort(ids, (o1, o2) -> nums2[o1] - nums2[o2]);
        int left = 0, right = n - 1;
        for (int num : nums1) {
          int index = num > nums2[ids[left]] ? ids[left++] : ids[right--];
          res[index] = num;
        }
        return res;
    }


    public int maxAscendingSum(int[] nums) {
        int max = nums[0];
        int n = nums.length;
        int dp = nums[0];
        for (int i = 1; i < n; i++) {
            if(nums[i] > nums[i - 1]){
                dp += nums[i];
            }else {
                dp = nums[i];
            }
            if(dp > max){
                max = dp;
            }
        }
        return max;
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
