package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*力扣：792. 匹配子序列的单词数
        给定字符串 s 和字符串数组 words, 返回  words[i] 中是s的子序列的单词个数 。

        字符串的 子序列 是从原始字符串中生成的新字符串，可以从中删去一些字符(可以是none)，而不改变其余字符的相对顺序。

        例如， “ace” 是 “abcde” 的子序列。


        示例 1:

        输入: s = "abcde", words = ["a","bb","acd","ace"]
        输出: 3
        解释: 有三个是 s 的子序列的单词: "a", "acd", "ace"。
        Example 2:

        输入: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
        输出: 2


        提示:

        1 <= s.length <= 5 * 104
        1 <= words.length <= 5000
        1 <= words[i].length <= 50
        words[i]和 s 都只由小写字母组成。
        思路：用暴力法会超时
        优化：用map记录s中每个字符出现的下标，循环遍历words的时候直接使用map的下标来判断，
        查找下标的时候会涉及到查询，因为索引是有序的可以用二分查找法，如果不用有个别用例会超时。
        */
public class NumMatchingSubseq {
    public static void main(String[] args) {
        System.out.println(new NumMatchingSubseq().numMatchingSubseq("abcde", new String[]{"a","bb","acd","ace"}));
    }
    //map + 二分查找法
    public int numMatchingSubseq(String s, String[] words) {
        int res = 0;
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            List<Integer> list = map.getOrDefault(ch, new ArrayList<>());
            list.add(i);
            map.put(ch, list);
        }
        for (String word : words) {
            if (isSub(word, map)) {
                res++;
            }
        }
        return res;
    }
    public boolean isSub(String tar,  Map<Character, List<Integer>> map) {
        int lastIndex = -1;
        for (int i = 0; i < tar.length(); i++) {
            char ch = tar.charAt(i);
            List<Integer> list = map.get(ch);
            if (list == null) {
                return false;
            }
            int search = binSearch(0, list.size() - 1, list, lastIndex);
            if (search == -1){
                return false;
            }
            lastIndex = search;
        }
        return true;
    }
    public int binSearch(int low, int high, List<Integer> list, int target) {
        while (low <= high) {
            int mid = (low + high) / 2;
            if (list.get(mid) <= target) {
                low = mid + 1;
            } else {
                if (mid == low || list.get(mid - 1) <= target) {
                    return list.get(mid);
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}
