package tree;

public class SumNumbers {
   /* 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
    例如，从根到叶子节点路径 1->2->3 代表数字 123。
    计算从根到叶子节点生成的所有数字之和。
    说明: 叶子节点是指没有子节点的节点。

    示例 1:

    输入: [1,2,3]
            1
            / \
            2   3
    输出: 25
    解释:
    从根到叶子节点路径 1->2 代表数字 12.
    从根到叶子节点路径 1->3 代表数字 13.
    因此，数字总和 = 12 + 13 = 25.
    示例 2:

    输入: [4,9,0,5,1]
            4
            / \
            9   0
             / \
            5   1
    输出: 1026
    解释:
    从根到叶子节点路径 4->9->5 代表数字 495.
    从根到叶子节点路径 4->9->1 代表数字 491.
    从根到叶子节点路径 4->0 代表数字 40.
    因此，数字总和 = 495 + 491 + 40 = 1026.*/

     public static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
      }

    public static void main(String[] args) {
        TreeNode t1=new TreeNode(1);
        TreeNode t2=new TreeNode(2);
        TreeNode t3=new TreeNode(3);
        t1.left=t2;t1.right=t3;
        System.out.println(sumNumbers(t1));
    }
   static int result=0;
    public static  int sumNumbers(TreeNode root) {
        hh(root,0);
        return result;
    }
   static void hh(TreeNode root,int sum){
        if(root==null){
            return ;
        }
        sum=sum*10+root.val;
        if(root.left==null&&root.right==null){
            result+=sum;
            return ;
        }
        hh(root.left,sum);
        hh(root.right,sum);
    }
}
