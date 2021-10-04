package tree;
/*
给定一棵二叉搜索树，请找出其中第k大的节点。
        输入: root = [3,1,4,null,2], k = 1
           3
          / \
         1   4
          \
           2
        输出: 4

        输入: root = [5,3,6,2,4,null,null,1], k = 3
             5
            / \
           3   6
          / \
         2   4
        /
        1
        输出: 4

        思路：根据以上性质，易得二叉搜索树的 右中左遍历倒序 为 递减序列 。
因此，求 “二叉搜索树第 k 大的节点” 可转化为求 “此树的右中左遍历倒序的第 k个节点”。
*/

public class KthLargest {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    public static void main(String[] args) {

    }
    int res=0;
    int temp=0;
    public int kthLargest(TreeNode root, int k) {
        hh(root,k);
        return res;

    }
    public void hh(TreeNode root,int k){
        if(root==null) return;
        hh(root.right,k);
        temp++;
        if(temp==k){
            res=root.val;
            return;
        }

        hh(root.left,k);
    }
}
