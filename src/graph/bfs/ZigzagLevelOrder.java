package graph.bfs;
import java.util.*;
/*
给定一个二叉树，返回其节点值的锯齿形层序遍历。
（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

        例如：
        给定二叉树 [3,9,20,null,null,15,7],

        3
        / \
        9  20
        /  \
        15   7
        返回锯齿形层序遍历如下：

        [
        [3],
        [20,9],
        [15,7]
        ]
        思路：在层次遍历的基础上，偶数层翻转
*/

public class ZigzagLevelOrder {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public static void main(String[] args) {

    }
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> lists=new ArrayList<>();
        if(root==null) return lists;
        Deque<TreeNode> deque=new ArrayDeque<>();
        boolean flag=false;//是否需要翻转
        deque.add(root);
        while(!deque.isEmpty()){
            List<Integer> l=new ArrayList<>();
            for(int i= deque.size()-1;i>=0;i--){
                TreeNode temp=deque.poll();
                l.add(temp.val);
                if(temp.left!=null) deque.add(temp.left);
                if(temp.right!=null) deque.add(temp.right);
            }
            if(flag){
                Collections.reverse(l);
            }
            lists.add(l);
            flag=!flag;
        }
        return lists;
    }
}
