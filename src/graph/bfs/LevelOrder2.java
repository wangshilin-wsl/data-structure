package graph.bfs;

import java.util.*;
/*
从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。

        例如:
        给定二叉树: [3,9,20,null,null,15,7],

        3
        / \
        9  20
        /  \
        15   7
        返回其层次遍历结果：

        [
        [3],
        [9,20],
        [15,7]
        ]
*/

public class LevelOrder2 {
    public static void main(String[] args) {

    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list=new ArrayList<>();
        if(root==null) return list;
        Deque<TreeNode> deque=new LinkedList<>();
        deque.add(root);
        while(!deque.isEmpty()){
            List<Integer> list1=new ArrayList<>();
            for(int i=deque.size()-1;i>=0;i--){//这里要从高位往地位走，如果反着新加入的结点会提前输出
                TreeNode temp=deque.poll();
                list1.add(temp.val);
                if(temp.left!=null) deque.add(temp.left);
                if(temp.right!=null) deque.add(temp.right);
            }
            list.add(list1);
        }
        return list;
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
