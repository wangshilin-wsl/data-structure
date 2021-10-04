package tree;
     /*   请实现一个函数，用来判断一棵二叉树是不是对称的。
        如果一棵二叉树和它的镜像一样，那么它是对称的。

        例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
            1
           / \
          2   2
         / \ / \
        3  4 4  3
        但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
            1
           / \
          2   2
           \   \
           3    3

        示例 1：
        输入：root = [1,2,2,3,4,4,3]
        输出：true
        示例 2：

        输入：root = [1,2,2,null,3,null,3]
        输出：false
*/
public class TreeIsSymmetric {
    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    public static void main(String[] args) {
        TreeNode t1=new TreeNode(1);
        TreeNode t2=new TreeNode(2);
        TreeNode t3=new TreeNode(2);
        TreeNode t4=new TreeNode(3);
        TreeNode t5=new TreeNode(3);
        TreeNode t6=new TreeNode(4);
        TreeNode t7=new TreeNode(4);
        t1.left=t2 ;t1.right=t3;
        t2.left=t4;t2.right=t6;
        t3.left=t7;t3.right=t5;
        System.out.println(isSymmetric(t1));
    }
    public static boolean isSymmetric(TreeNode root) {
        return root==null?true:judge(root.left,root.right);
    }
    public static boolean  judge(TreeNode left,TreeNode right){
        if(left==null&&right==null)   return true;
        if(left==null||right==null||left.val!=right.val) return false;
        return judge(left.left,right.right)&&judge(left.right,right.left);
    }
}
