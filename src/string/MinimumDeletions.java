package string;

/*力扣：1653. 使字符串平衡的最少删除次数
        给你一个字符串 s ，它仅包含字符 'a' 和 'b' 。

        你可以删除 s 中任意数目的字符，使得 s 平衡 。当不存在下标对 (i,j) 满足 i < j ，且 s[i] = 'b' 的同时 s[j]= 'a' ，此时认为 s 是 平衡 的。

        请你返回使 s 平衡 的 最少 删除次数。



        示例 1：

        输入：s = "aababbab"
        输出：2
        解释：你可以选择以下任意一种方案：
        下标从 0 开始，删除第 2 和第 6 个字符（"aababbab" -> "aaabbb"），
        下标从 0 开始，删除第 3 和第 6 个字符（"aababbab" -> "aabbbb"）。
        示例 2：

        输入：s = "bbaaaaabb"
        输出：2
        解释：唯一的最优解是删除最前面两个字符。


        提示：

        1 <= s.length <= 105
        s[i] 要么是 'a' 要么是 'b' 。*/
public class MinimumDeletions {
    public static void main(String[] args) {
        System.out.println(new MinimumDeletions().minimumDeletions("aababbab"));
    }

    public int minimumDeletions(String s) {
        //思路：结果肯定是以某个边界为划分，左边全部是a,右边全部是b
        //所以记录左边a,右边b出现的次数（需要保留下来）
        //枚举所有的边界[0, len - 1]记录其中的最小值
        //优化空间,先找出b的数量
        int min = Integer.MAX_VALUE, len = s.length(), numsA = 0, numsB = 0;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == 'b') {
                numsB++;
            }
        }

        //以i的左边分分界线
        for (int i = 0; i <= len; i++) {
            //numsA和numsB表示保留下来的个数，所以删除的个数就是len - (numsA + numsB)
            min = Math.min(min, len - (numsA + numsB));
            //当前是a，说明左边的a增加了一个，如果是b，则右边的b减少了一个
            int temp = i != len && s.charAt(i) == 'a' ? numsA++ : numsB--;
        }
        return min;
    }



    public int minimumDeletions1(String s) {
        //思路：结果肯定是以某个边界为划分，左边全部是a,右边全部是b
        //所以记录左边a,右边b出现的次数（需要保留下来）
        //枚举所有的边界[0, len - 1]记录其中的最小值
        int min = Integer.MAX_VALUE, len = s.length();
        int[] numsA = new int[len + 2], numsB = new int[len + 2];
        for (int i = 0; i < len; i++) {
            int lastIndex = len - i - 1;
            //记录左侧a出现的次数
            numsA[i + 1] = s.charAt(i) == 'a' ? numsA[i] + 1 : numsA[i];
            //记录右侧b出现的次数
            numsB[lastIndex + 1] = s.charAt(lastIndex) == 'b' ? numsB[lastIndex + 2] + 1 : numsB[lastIndex + 2];
        }

        //以i的左边分分界线
        for (int i = 0; i <= len; i++) {
            //numsA和numsB表示保留下来的个数，所以删除的个数就是len - (numsA[i + 1] + numsB[i + 2])
            min = Math.min(min, len - (numsA[i] + numsB[i + 1]));
        }
        return min;
    }
}
