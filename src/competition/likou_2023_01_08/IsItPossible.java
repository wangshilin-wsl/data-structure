package competition.likou_2023_01_08;

import java.util.HashMap;
import java.util.Map;

/*力扣：6284. 使字符串总不同字符的数目相等 显示英文描述
        给你两个下标从 0 开始的字符串 word1 和 word2 。

        一次 移动 由以下两个步骤组成：

        选中两个下标 i 和 j ，分别满足 0 <= i < word1.length 和 0 <= j < word2.length ，
        交换 word1[i] 和 word2[j] 。
        如果可以通过 恰好一次 移动，使 word1 和 word2 中不同字符的数目相等，则返回 true ；否则，返回 false 。



        示例 1：

        输入：word1 = "ac", word2 = "b"
        输出：false
        解释：交换任何一组下标都会导致第一个字符串中有 2 个不同的字符，而在第二个字符串中只有 1 个不同字符。
        示例 2：

        输入：word1 = "abcc", word2 = "aab"
        输出：true
        解释：交换第一个字符串的下标 2 和第二个字符串的下标 0 。之后得到 word1 = "abac" 和 word2 = "cab" ，各有 3 个不同字符。
        示例 3：

        输入：word1 = "abcde", word2 = "fghij"
        输出：true
        解释：无论交换哪一组下标，两个字符串中都会有 5 个不同字符。


        提示：

        1 <= word1.length, word2.length <= 105
        word1 和 word2 仅由小写英文字母组成。*/
public class IsItPossible {
    public static void main(String[] args) {
        System.out.println(new IsItPossible().isItPossible("aa", "ab"));
    }

    public boolean isItPossible(String word1, String word2) {
        //对暴力法进行优化，先对word1、word2进行预处理，把对应的字符出现次数用数组记下来
        int n1 = word1.length(), n2 = word2.length(), num1 = 0, num2 = 0;
        int[] nums1 = new int[26], nums2 = new int[26];
        for (int i = 0; i < n1; i++) {
            nums1[word1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < n2; i++) {
            nums2[word2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (nums1[i] > 0) {
                num1++;
            }
            if (nums2[i] > 0) {
                num2++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (nums1[i] == 0) {
                continue;
            }
            for (int j = 0; j < 26; j++) {
                if (nums2[j] == 0) {
                    continue;
                }
                int temp1 = num1, temp2 = num2;
                if (nums1[i] <= 1) {
                    temp1--;
                }
                if (nums2[j] <= 1) {
                    temp2--;
                }
                if (nums1[j] == 0 || (nums1[j] == 1 && i == j)) {
                    temp1++;
                }
                if (nums2[i] == 0 || (nums2[i] == 1 && i == j)) {
                    temp2++;
                }
                if (temp1 == temp2) {
                    return true;
                }
            }
        }
       return false;
    }
    public boolean isItPossible1(String word1, String word2) {
        //暴力法
        int n1 = word1.length(), n2 = word2.length();
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < n1; i++) {
            char ch = word1.charAt(i);
            map1.put(ch, map1.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < n2; i++) {
            char ch = word2.charAt(i);
            map2.put(ch, map2.getOrDefault(ch, 0) + 1);
        }
        int num1 = map1.size(), num2 = map2.size();
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                char ch1 = word1.charAt(i);
                char ch2 = word2.charAt(j);
                int temp1 = num1, temp2 = num2;
                if (map1.getOrDefault(ch1, 0) <= 1) {
                    temp1--;
                }
                if (map2.getOrDefault(ch2, 0) <= 1) {
                    temp2--;
                }
                if (!map1.containsKey(ch2) || (map1.get(ch2) == 1 && ch1 == ch2)) {
                    temp1++;
                }
                if (!map2.containsKey(ch1) || (map2.get(ch1) == 1 && ch1 == ch2)) {
                    temp2++;
                }
                if (temp1 == temp2) {
                    return true;
                }
            }
        }
        return false;
    }
}
