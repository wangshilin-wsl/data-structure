package tree;
/*给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

        百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，
        最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大
        （一个节点也可以是它自己的祖先）。”

        例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]*/


public class LowestCommonAncestor {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        TreeNode t1=new TreeNode(6);
        TreeNode t2=new TreeNode(2);
        TreeNode t3=new TreeNode(8);
        TreeNode t4=new TreeNode(0);
        TreeNode t5=new TreeNode(4);
        TreeNode t6=new TreeNode(7);
        TreeNode t7=new TreeNode(9);
        TreeNode t8=new TreeNode(3);
        TreeNode t9=new TreeNode(5);
       t1.left=t2;t1.right=t3;
       t2.left=t3;t2.right=t5;
        t3.left=t6;t3.right=t7;
        t5.left=t8;t5.right=t9;
        System.out.println(lowestCommonAncestor(t1,t3,t4).val);
    }
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while(root!=null){
            if((p.val>=root.val&&q.val<=root.val)||(p.val<=root.val&&q.val>=root.val))
                break;
            if(p.val>root.val&&q.val>root.val) root=root.right;
            if(p.val<root.val&&q.val<root.val) root=root.left;
        }
        return root;
    }
}
