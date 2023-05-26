package tree;

/*力扣：1080. 根到叶路径上的不足节点
        给你二叉树的根节点 root 和一个整数 limit ，请你同时删除树中所有 不足节点 ，并返回最终二叉树的根节点。

        假如通过节点 node 的每种可能的 “根-叶” 路径上值的总和全都小于给定的 limit，则该节点被称之为 不足节点 ，需要被删除。

        叶子节点，就是没有子节点的节点。



        示例 1：


        输入：root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1
        输出：[1,2,3,4,null,null,7,8,9,null,14]
        示例 2：


        输入：root = [5,4,8,11,null,17,4,7,1,null,null,5,3], limit = 22
        输出：[5,4,8,11,null,17,4,7,null,null,null,5]
        示例 3：


        输入：root = [1,2,-3,-5,null,4,null], limit = -1
        输出：[1,null,-3,4]


        提示：

        树中节点数目在范围 [1, 5000] 内
        -105 <= Node.val <= 105
        -109 <= limit <= 109*/
public class SufficientSubset {

    int limit;
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        this.limit = limit;
        return suff(root, 0);
    }

    //返回root删除不足节点之后的树
    public TreeNode suff(TreeNode root, int sum) {
        //是叶子节点则可以判断是否是不足节点
        if (root.left == null && root.right == null) {
            return (sum + root.val) >= limit ? root : null;
        }
        if (root.left != null) {
            root.left = suff(root.left, sum + root.val);
        }
        if (root.right != null) {
            root.right = suff(root.right, sum + root.val);
        }
        //如果root的左右都是null，则说明返回的左右都为不足节点，则必须删除root节点
        return (root.left == null && root.right == null) ? null : root;
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}


