package tree.non_recursive;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//非递归先序遍历
/*
思路：
        用非递归的方式实现二叉树的先序遍历（LeetCode144）：
        1、申请一个栈stack，然后将头节点压入stack中。
        2、从stack中弹出栈顶节点，打印，再将其右孩子节点（不为空的话）先压入stack中，
        最后将其左孩子节点（不为空的话）压入stack中。
        3、不断重复步骤2，直到stack为空，全部过程结束。
*/
public class RootLeftRight {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode n1=new TreeNode(1);
        TreeNode n2=new TreeNode(2);
        TreeNode n3=new TreeNode(3);
        TreeNode n4=new TreeNode(4);
        TreeNode n5=new TreeNode(5);
        TreeNode n6=new TreeNode(6);
        TreeNode n7=new TreeNode(7);
        n1.left=n2;n1.right=n3;
        n2.left=n4;n2.right=n5;
        n3.left=n6;n3.right=n7;
        List<Integer> list = preorderTraversal(n1);
        for (Integer integer : list) {
            System.out.print(integer+" ");
        }
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
            while (!stack.empty()) {
                root = stack.pop();
                list.add(root.val);
                if (root.right != null) {
                    stack.push(root.right);
                }
                if (root.left != null) {
                    stack.push(root.left);
                }
            }
        }
        return list;
    }
}
