package dynamic_programming.stone;

/*力扣:1140. 石子游戏 II
        爱丽丝和鲍勃继续他们的石子游戏。许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]。游戏以谁手中的石子最多来决出胜负。

        爱丽丝和鲍勃轮流进行，爱丽丝先开始。最初，M = 1。

        在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M。然后，令 M = max(M, X)。

        游戏一直持续到所有石子都被拿走。

        假设爱丽丝和鲍勃都发挥出最佳水平，返回爱丽丝可以得到的最大数量的石头。



        示例 1：

        输入：piles = [2,7,9,4,4]
        输出：10
        解释：如果一开始Alice取了一堆，Bob取了两堆，然后Alice再取两堆。爱丽丝可以得到2 + 4 + 4 = 10堆。如果Alice一开始拿走了两堆，那么Bob可以拿走剩下的三堆。在这种情况下，Alice得到2 + 7 = 9堆。返回10，因为它更大。
        示例 2:

        输入：piles = [1,2,3,4,5,100]
        输出：104


        提示：

        1 <= piles.length <= 100
        1 <= piles[i] <= 104*/
public class StoneGameII {
    public static void main(String[] args) {
        System.out.println(new StoneGameII().stoneGameII(new int[]{2, 7, 9, 4, 4}));
    }
    //动态规划
    public int stoneGameII(int[] piles) {
        //前置：877
        //动态规划
        int len = piles.length, sum = 0;
        //dp[i][m]表示[i,len-1]拿m堆，选择者赢的个数
        int[][] dp = new int[len][len + 1];
        for (int i = len - 1; i >= 0; i--) {
            sum += piles[i];
            for (int m = 1; m <= len; m++) {
                if (i + 2 * m >= len) {
                    //可以都拿，最优就是都拿了
                    dp[i][m] = sum;
                } else {
                    int max = Integer.MIN_VALUE;
                    for (int x = 1; x <= 2 * m; x++) {
                        max = Math.max(max, sum -dp[i + x][Math.max(m, x)]);
                    }
                    dp[i][m] = max;
                }
            }
        }
        return dp[0][1];
    }


    //递归+记忆搜索
    public int stoneGameII1(int[] piles) {
        //前置：877
        //记忆搜索
        int len = piles.length;
        int[][] cache = new int[len][len + 1];
        int[] prefixSum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            prefixSum[i + 1] = prefixSum[i] + piles[i];
        }
        return (prefixSum[len] + pick(prefixSum, 0, cache, 1)) / 2;
    }
    //pick表示该选择者在区间[i,len - 1]赢的数量
    //可以拿多个，所以要用前缀和
    public int pick(int[] prefixSum, int i, int[][] cache, int m){
        //最优，如果剩下的都可以拿就都拿了
        if (i + 2 * m >= cache.length) {
            return prefixSum[cache.length] - prefixSum[i];
        }
        if (cache[i][m] != 0) {
            return cache[i][m];
        }
        int max = Integer.MIN_VALUE;
        for (int x = 1; x <= 2 * m; x++) {
            //可以拿x堆，则[i, i + x - 1]正好是x堆(i+x-1-i+1)，[i,i+x-1]的元素和为(prefixSum[i+x-1]-prefixSum[i-1])
            // 但是prefixSum的前缀和从1开始，所以得+1,则为(prefixSum[i+x]-prefixSum[i])
            //可以选择多次，并且每次可以选多个，所以这次的分数是[i,i+x]的和
            max = Math.max(max, prefixSum[i + x] - prefixSum[i] - pick(prefixSum, i + x, cache, Math.max(x, m)));
        }
        cache[i][m] = max;
        return max;
    }
}
