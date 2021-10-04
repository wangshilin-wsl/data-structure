package tree.non_recursive;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//非递归后序遍历
/*思路：
        用非递归的方式实现二叉树的后序遍历（LeetCode145）：
        用非递归的方式实现后序遍历有点麻烦。
        1、申请一个栈s1，然后将头节点压入栈s1中。
        2、从s1中弹出的节点记为cur，然后依次将cur的左孩子节点和右孩子节点压入s1中。
        3、在整个过程中，每一个从s1中弹出的节点都放进s2中。
        4、不断重复步骤2和步骤3，直到s1为空，过程停止。
        5、从s2中依次弹出节点并打印，打印的顺序就是后序遍历的顺序。*/
public class LeftRightRoot {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        List<Integer> list = postorderTraversal(n1);
        for (Integer integer : list) {
            System.out.print(integer + " ");
        }
    }

    public static List<Integer> postorderTraversal(TreeNode head) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        if (head != null) {
            stack1.push(head);
            while (!stack1.empty()) {
                head = stack1.pop();
                stack2.push(head);
                if (head.left != null) {
                    stack1.push(head.left);
                }
                if (head.right != null) {
                    stack1.push(head.right);
                }
            }
            while (!stack2.empty()) {
                list.add(stack2.pop().val);
            }
        }
        return list;
    }
}
