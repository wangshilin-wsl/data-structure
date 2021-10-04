package tree.non_recursive;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//非递归中序遍历
/*思路：
        用非递归的方式实现二叉树的中序遍历（LeetCode94）：
        1、申请一个栈stack，初始时令cur=head
        2、先把cur压入栈中，依次把左边界压入栈中，即不停的令cur=cur.left，重复步骤2
        3、不断重复2，直到为null，从stack中弹出一个节点，记为node，打印node的值，
        并令cur=node.right,重复步骤2
        4、当stack为空且cur为空时，整个过程停止。*/
public class LeftRootRight {
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
        List<Integer> list = inorderTraversal(n1);
        for (Integer integer : list) {
            System.out.print(integer + " ");
        }
    }

    public static List<Integer> inorderTraversal(TreeNode head) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (head != null) {
            while (head != null || !stack.empty()) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    list.add(head.val);
                    head = head.right;
                }
            }
        }
        return list;
    }
}
