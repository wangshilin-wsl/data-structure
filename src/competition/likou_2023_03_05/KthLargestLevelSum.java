package competition.likou_2023_03_05;

/*力扣：6308. 二叉树中的第 K 大层和 显示英文描述
        给你一棵二叉树的根节点 root 和一个正整数 k 。

        树中的 层和 是指 同一层 上节点值的总和。

        返回树中第 k 大的层和（不一定不同）。如果树少于 k 层，则返回 -1 。

        注意，如果两个节点与根节点的距离相同，则认为它们在同一层。



        示例 1：



        输入：root = [5,8,9,2,1,3,7,4,6], k = 2
        输出：13
        解释：树中每一层的层和分别是：
        - Level 1: 5
        - Level 2: 8 + 9 = 17
        - Level 3: 2 + 1 + 3 + 7 = 13
        - Level 4: 4 + 6 = 10
        第 2 大的层和等于 13 。
        示例 2：



        输入：root = [1,2,null,3], k = 1
        输出：3
        解释：最大的层和是 3 。


        提示：

        树中的节点数为 n
        2 <= n <= 105
        1 <= Node.val <= 106
        1 <= k <= n
*/

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class KthLargestLevelSum {

    public static void main(String[] args) {
        TreeNode node5 = new TreeNode(5);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        TreeNode node2 = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);
        TreeNode node6 = new TreeNode(6);
        node5.left = node8;
        node5.right = node9;

        node8.left = node2;
        node8.right = node1;

        node9.left = node3;
        node9.right = node7;

        node2.left = node4;
        node2.right = node6;
        System.out.println(new KthLargestLevelSum().kthLargestLevelSum(node5, 2));
    }

    public long kthLargestLevelSum(TreeNode root, int k) {
        //使用最大堆记录每一层的和
        Queue<Long> sumQueue = new PriorityQueue<Long>((o1, o2) -> o2 > o1 ? 1 : -1);
        //利用队列来广度遍历
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            long sum = 0L;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                sum += treeNode.val;
                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
            }
            sumQueue.offer(sum);
        }
        if (sumQueue.size() < k) {
            return -1;
        }
        long kTh = 0L;
        for (int i = 0; i < k; i++) {
            kTh = sumQueue.poll();
        }
        return kTh;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
