package tree;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
        /*输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。
        假设输入的前序遍历和中序遍历的结果中都不含重复的数字。

        例如，给出
        前序遍历 preorder = [3,9,20,15,7]
        中序遍历 inorder = [9,3,15,20,7]
        返回如下的二叉树：
        3
        / \
        9  20
        /  \
        15   7

        限制：
        0 <= 节点个数 <= 5000
*/
public class BuildTree {
    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    public static String treeNodeToString(TreeNode root) {
        if (root == null) {
            return "[]";
        }

        String output = "";
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (node == null) {
                output += "null, ";
                continue;
            }

            output += String.valueOf(node.val) + ", ";
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
    }//把树输出的
    public static void main(String[] args) throws IOException {

            int[] preorder ={3,9,20,15,7};

            int[] inorder = {9,3,15,20,7};

            TreeNode ret = buildTree(preorder, inorder);

            String out = treeNodeToString(ret);

            System.out.print(out);
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode result=hh(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
        return result;
    }
    static TreeNode hh(int[] preorder,int i,int j, int[] inorder,int i1,int j1){
        if(i>j) return null;
        TreeNode temp=new TreeNode(preorder[i]);
        int k=0;
        for(k=i1;k<=j1;k++){
            if(inorder[k]==preorder[i]) break;
        }
        temp.left=hh(preorder,i+1,k-i1+i,inorder,i1,k-1);
        temp.right=hh(preorder,k-i1+i+1,j,inorder,k+1,j1);
        return temp;
    }
}
