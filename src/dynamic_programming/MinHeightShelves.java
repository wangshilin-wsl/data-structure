package dynamic_programming;

import java.util.Arrays;

/*力扣：1105. 填充书架
        给定一个数组 books ，其中 books[i] = [thicknessi, heighti] 表示第 i 本书的厚度和高度。你也会得到一个整数 shelfWidth 。

        按顺序 将这些书摆放到总宽度为 shelfWidth 的书架上。

        先选几本书放在书架上（它们的厚度之和小于等于书架的宽度 shelfWidth ），然后再建一层书架。重复这个过程，直到把所有的书都放在书架上。

        需要注意的是，在上述过程的每个步骤中，摆放书的顺序与你整理好的顺序相同。

        例如，如果这里有 5 本书，那么可能的一种摆放情况是：第一和第二本书放在第一层书架上，第三本书放在第二层书架上，第四和第五本书放在最后一层书架上。
        每一层所摆放的书的最大高度就是这一层书架的层高，书架整体的高度为各层高之和。

        以这种方式布置书架，返回书架整体可能的最小高度。



        示例 1：



        输入：books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelfWidth = 4
        输出：6
        解释：
        3 层书架的高度和为 1 + 3 + 2 = 6 。
        第 2 本书不必放在第一层书架上。
        示例 2:

        输入: books = [[1,3],[2,4],[3,2]], shelfWidth = 6
        输出: 4


        提示：

        1 <= books.length <= 1000
        1 <= thicknessi <= shelfWidth <= 1000
        1 <= heighti <= 1000*/
public class MinHeightShelves {
    public static void main(String[] args) {
        System.out.println(new MinHeightShelves().minHeightShelves(new int[][]{{1, 3}, {2, 3}, {3, 2}}, 6));
    }
    //动态规划，由递归优化思路
    public int minHeightShelves(int[][] books, int shelfWidth) {
        //动态规划
        int n = books.length;
        //dp[i]表示以i + 1结尾最小高度
        //dp[i]=min(dp[i], dp[j])  j <= i,并且[j,i]的宽度<shelfWidth
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            int max = 0, width = 0;
            for (int j = i; j >= 0; j--) {
                width += books[j][0];
                max = Math.max(max, books[j][1]);
                if (width > shelfWidth) {
                    break;
                }
                //[j,i]分为一行柜子
                dp[i + 1] = Math.min(dp[i + 1], dp[j] + max);
            }
        }
        return dp[n];
    }


    //递归基础加上记忆搜索
    public int minHeightShelves1(int[][] books, int shelfWidth) {
        int[] cache = new int[books.length];
        return minHeight1(books, shelfWidth, 0, cache);
    }
    //该方法是求从index开始放书需要最低的柜子高度
    public int minHeight1(int[][] books, int shelfWidth, int index, int[] cache) {
        //没有书了，柜子高度为0
        if (index >= books.length) {
            return 0;
        }
        if (cache[index] != 0) {
            return cache[index];
        }
        int min = Integer.MAX_VALUE, max = 0, width = 0;
        for (int i = index; i < books.length; i++) {
            width += books[i][0];
            max = Math.max(max, books[i][1]);
            if (width > shelfWidth) {
                break;
            }
            min = Math.min(min, max + minHeight1(books, shelfWidth, i + 1, cache));
        }
        cache[index] = min;
        return min;
    }


    //递归，超时
    public int minHeightShelves2(int[][] books, int shelfWidth) {
        return minHeight2(books, shelfWidth, 0);
    }
    //该方法是求从index开始放书需要最低的柜子高度
    public int minHeight2(int[][] books, int shelfWidth, int index) {
        //没有书了，柜子高度为0
        if (index >= books.length) {
            return 0;
        }
        int min = Integer.MAX_VALUE, max = 0, width = 0;
        for (int i = index; i < books.length; i++) {
            width += books[i][0];
            max = Math.max(max, books[i][1]);
            if (width > shelfWidth) {
                break;
            }
            min = Math.min(min, max + minHeight2(books, shelfWidth, i + 1));
        }
        return min;
    }
}
