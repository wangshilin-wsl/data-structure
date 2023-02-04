package tree;

import java.util.ArrayDeque;
import java.util.Deque;

/*力扣：1145. 二叉树着色游戏
        有两位极客玩家参与了一场「二叉树着色」的游戏。游戏中，给出二叉树的根节点 root，树上总共有 n 个节点，且 n 为奇数，其中每个节点上的值从 1 到 n 各不相同。

        最开始时：

        「一号」玩家从 [1, n] 中取一个值 x（1 <= x <= n）；
        「二号」玩家也从 [1, n] 中取一个值 y（1 <= y <= n）且 y != x。
        「一号」玩家给值为 x 的节点染上红色，而「二号」玩家给值为 y 的节点染上蓝色。

        之后两位玩家轮流进行操作，「一号」玩家先手。每一回合，玩家选择一个被他染过色的节点，将所选节点一个 未着色 的邻节点（即左右子节点、或父节点）进行染色（「一号」玩家染红色，「二号」玩家染蓝色）。

        如果（且仅在此种情况下）当前玩家无法找到这样的节点来染色时，其回合就会被跳过。

        若两个玩家都没有可以染色的节点时，游戏结束。着色节点最多的那位玩家获得胜利 ✌️。

        现在，假设你是「二号」玩家，根据所给出的输入，假如存在一个 y 值可以确保你赢得这场游戏，则返回 true ；若无法获胜，就请返回 false 。


        示例 1 ：


        输入：root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
        输出：true
        解释：第二个玩家可以选择值为 2 的节点。
        示例 2 ：

        输入：root = [1,2,3], n = 3, x = 1
        输出：false


        提示：

        树中节点数目为 n
        1 <= x <= n <= 100
        n 是奇数
        1 <= Node.val <= n
        树中所有值 互不相同*/
public class BtreeGameWinningMove {
    //一样的思路，不过优化了找X节点的思路
    int x, left = 0, right = 0;

    public static void main(String[] args) {

    }

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        int half = n / 2;
        this.x = x;
        getCount(root);
        return left > half || right > half || (n - 1 - left - right) > half;
    }
    int getCount(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int tempLeft = getCount(root.left);
        int tempRight = getCount(root.right);
        if (x == root.val) {
            left = tempLeft;
            right = tempRight;
        }
        return tempLeft + tempRight + 1;
    }



    //先找出X的节点，判断以X的根、X的左子树、X的右子树所在的区域是否有节点数大于n的一半，第二位选手直接堵在对应X的根节点、左子树根节点、右子树根节点即可堵住第一位选手不侵占自己的区域
    public boolean btreeGameWinningMove1(TreeNode root, int n, int x) {
        TreeNode xNode = null;
        int half = n / 2;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (temp.val == x) {
                xNode = temp;
                break;
            }
            if (temp.left != null) {
                queue.offer(temp.left);
            }
            if (temp.right != null) {
                queue.offer(temp.right);
            }
        }
        if (xNode == null) {
            return false;
        }
        int left = getCount1(xNode.left);
        int right = getCount1(xNode.right);
        int ro = n - 1 - left - right;
        return left > half || right > half || ro > half;
    }
    int getCount1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getCount1(root.left) + getCount1(root.right) + 1;
    }
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
