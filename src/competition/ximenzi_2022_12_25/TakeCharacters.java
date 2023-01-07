package competition.ximenzi_2022_12_25;

import java.util.ArrayList;
import java.util.List;

/*力扣：6270. 每种字符至少取 K 个 显示英文描述
        给你一个由字符 'a'、'b'、'c' 组成的字符串 s 和一个非负整数 k 。每分钟，你可以选择取走 s 最左侧 还是 最右侧 的那个字符。

        你必须取走每种字符 至少 k 个，返回需要的 最少 分钟数；如果无法取到，则返回 -1 。



        示例 1：

        输入：s = "aabaaaacaabc", k = 2
        输出：8
        解释：
        从 s 的左侧取三个字符，现在共取到两个字符 'a' 、一个字符 'b' 。
        从 s 的右侧取五个字符，现在共取到四个字符 'a' 、两个字符 'b' 和两个字符 'c' 。
        共需要 3 + 5 = 8 分钟。
        可以证明需要的最少分钟数是 8 。
        示例 2：

        输入：s = "a", k = 1
        输出：-1
        解释：无法取到一个字符 'b' 或者 'c'，所以返回 -1 。


        提示：

        1 <= s.length <= 105
        s 仅由字母 'a'、'b'、'c' 组成
        0 <= k <= s.length*/
public class TakeCharacters {
    public static void main(String[] args) {
        System.out.println(new TakeCharacters().takeCharacters("aabaaaacaabc", 2));
    }
    public int takeCharacters(String s, int k) {
        int left = 0, right = s.length() - 1, map[] = new int[3], min = 0;
        for (; left < s.length() && (map[0] < k | map[1] < k | map[2] < k); min = ++left) {
            map[s.charAt(left) - 'a']++;
        }
        for (; left > 0; min = Math.min(min, s.length() - right + left - 1)) {
            for (map[s.charAt(--left) - 'a']--; right >= 0 && map[s.charAt(left) - 'a'] < k; right--) {
                map[s.charAt(right) - 'a']++;
            }
        }
        return map[0] < k | map[1] < k | map[2] < k ? -1 : min;
    }
    public int takeCharacters1(String s, int k) {
        List<Integer>[] indices = new List[3];
        for(int i = 0; i < 3; i++) {
            indices[i] = new ArrayList<>();
        }
        int n = s.length();
        int[][] ps = new int[3][n];
        for(int i = 0; i < n; i++) {
            int v = s.charAt(i) - 'a';
            indices[v].add(i);
            if(i > 0) {
                for(int j = 0; j < 3; j++) {
                    ps[j][i] += ps[j][i - 1];
                }
            }
            ps[v][i]++;
        }
        for(int i = 0; i < 3; i++) {
            if(ps[i][n - 1] < k) {
                return -1;
            }
        }
        if(k == 0) {
            return 0;
        }
        int ans = 0;
        for(int j = 0; j < 3; j++) {
            int need = k;
            ans = Math.max(ans, n - indices[j].get(indices[j].size() - need));
        }
        for(int i = 0; i < n; i++) {
            int maxBack = 0;
            for(int j = 0; j < 3; j++) {
                int need = k - ps[j][i];
                if(need > 0) {
                    maxBack = Math.max(maxBack, n - indices[j].get(indices[j].size() - need));
                }
            }
            int cand = i + 1 + maxBack;
            ans = Math.min(ans, cand);
        }
        return ans;
    }

    public int takeCharacters2(String s, int k) {
        if (k == 0) {
            return 0;
        }
        int n = s.length(), min = Integer.MAX_VALUE;
        int[] chars1 = new int[3], chars2 = new int[3];
        int[][] left = new int[3][n + 1], right = new int[3][n + 1];
        for (int i = 0; i < n; i++) {
            int index = n - i - 1;
            int le = s.charAt(i) - 'a';
            int ri = s.charAt(index) - 'a';
            chars1[le] ++;
            chars2[ri] ++;
            left[0][i + 1] = chars1[0];
            left[1][i + 1] = chars1[1];
            left[2][i + 1] = chars1[2];
            right[0][index] = chars2[0];
            right[1][index] = chars2[1];
            right[2][index] = chars2[2];
        }
        if (chars1[0] < k || chars1[1] < k || chars1[2] < k) {
            return -1;
        }
        for (int i = 0; i <= n; i++) {
            for (int j = n; j >= i; j--) {
                int a = left[0][i] + right[0][j], b = left[1][i] + right[1][j], c = left[2][i] + right[2][j];
                if (a >= k && b >= k && c >= k) {
                    min = Math.min(min, i + n - j);
                }
            }
        }
        return min;
    }
}
