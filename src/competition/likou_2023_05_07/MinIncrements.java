package competition.likou_2023_05_07;

/*力扣：6419. 使二叉树所有路径值相等的最小代价 显示英文描述
        通过的用户数409
        尝试过的用户数544
        用户总通过次数433
        用户总提交次数685
        题目难度Medium
        给你一个整数 n 表示一棵 满二叉树 里面节点的数目，节点编号从 1 到 n 。根节点编号为 1 ，树中每个非叶子节点 i 都有两个孩子，分别是左孩子 2 * i 和右孩子 2 * i + 1 。

        树中每个节点都有一个值，用下标从 0 开始、长度为 n 的整数数组 cost 表示，其中 cost[i] 是第 i + 1 个节点的值。每次操作，你可以将树中 任意 节点的值 增加 1 。你可以执行操作 任意 次。

        你的目标是让根到每一个 叶子结点 的路径值相等。请你返回 最少 需要执行增加操作多少次。

        注意：

        满二叉树 指的是一棵树，它满足树中除了叶子节点外每个节点都恰好有 2 个节点，且所有叶子节点距离根节点距离相同。
        路径值 指的是路径上所有节点的值之和。


        示例 1：



        输入：n = 7, cost = [1,5,2,2,3,3,1]
        输出：6
        解释：我们执行以下的增加操作：
        - 将节点 4 的值增加一次。
        - 将节点 3 的值增加三次。
        - 将节点 7 的值增加两次。
        从根到叶子的每一条路径值都为 9 。
        总共增加次数为 1 + 3 + 2 = 6 。
        这是最小的答案。
        示例 2：



        输入：n = 3, cost = [5,3,3]
        输出：0
        解释：两条路径已经有相等的路径值，所以不需要执行任何增加操作。


        提示：

        3 <= n <= 105
        n + 1 是 2 的幂
        cost.length == n
        1 <= cost[i] <= 104*/
public class MinIncrements {
    //自底向上DFS。从最小的两层树开始平衡，每次加上左右两个字数差值即可
    int ans = 0;

    public static void main(String[] args) {
        System.out.println(new MinIncrements().minIncrements(15, new int[]{764,1460,2664,764,2725,4556,5305,8829,5064,5929,7660,6321,4830,7055,3761}));
        System.out.println(new MinIncrements().minIncrements(7, new int[]{1,5,2,2,3,3,1}));
    }

    public int minIncrements(int n, int[] cost) {
        dfs(1, cost);
        return ans;
    }
    //该函数返回index为子树的所有路径和的最大值
    public int dfs(int index, int[] cost) {
        if (index * 2 > cost.length) {
            // leaf node
            return cost[index - 1];
        }
        int left = dfs(index * 2, cost);
        int right = dfs(index * 2 + 1, cost);
        //需要把较小的加到较大的一致，需要Math.abs(left - right)步骤
        ans += Math.abs(left - right);
        return Math.max(left, right) + cost[index - 1];
    }
}
