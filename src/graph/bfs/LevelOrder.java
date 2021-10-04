package graph.bfs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*  从上到下打印出二叉树的每个节点，
        同一层的节点按照从左到右的顺序打印。

        例如:
        给定二叉树: [3,9,20,null,null,15,7],

          3
         / \
        9  20
        /  \
        15   7
        返回：

        [3,9,20,15,7]
        思路：使用BFS，用队列
        */
public class LevelOrder {
    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
  public static void main(String[] args) {

    }
    public int[] levelOrder(TreeNode root) {
        if(root==null) return new int[0];
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> deque=new LinkedList<>();
        deque.addLast(root);
        while(!deque.isEmpty()){
            root=deque.pollFirst();
            list.add(root.val);
            if(root.left!=null) deque.addLast(root.left);
            if(root.right!=null) deque.addLast(root.right);
        }
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }
}
