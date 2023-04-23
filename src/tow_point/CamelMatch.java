package tow_point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*力扣：1023. 驼峰式匹配
        如果我们可以将小写字母插入模式串 pattern 得到待查询项 query，那么待查询项与给定模式串匹配。（我们可以在任何位置插入每个字符，也可以插入 0 个字符。）

        给定待查询列表 queries，和模式串 pattern，返回由布尔值组成的答案列表 answer。只有在待查项 queries[i] 与模式串 pattern 匹配时， answer[i] 才为 true，否则为 false。

        示例 1：
        输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FB"
        输出：[true,false,true,true,false]
        示例：
        "FooBar" 可以这样生成："F" + "oo" + "B" + "ar"。
        "FootBall" 可以这样生成："F" + "oot" + "B" + "all".
        "FrameBuffer" 可以这样生成："F" + "rame" + "B" + "uffer".

        示例 2：
        输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
        输出：[true,false,true,false,false]
        解释：
        "FooBar" 可以这样生成："Fo" + "o" + "Ba" + "r".
        "FootBall" 可以这样生成："Fo" + "ot" + "Ba" + "ll".
        示例 3：

        输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBaT"
        输出：[false,true,false,false,false]
        解释：
        "FooBarTest" 可以这样生成："Fo" + "o" + "Ba" + "r" + "T" + "est".


        提示：

        1 <= queries.length <= 100
        1 <= queries[i].length <= 100
        1 <= pattern.length <= 100
        所有字符串都仅由大写和小写英文字母组成。*/
public class CamelMatch {
    public static void main(String[] args) {
        System.out.println(new CamelMatch().camelMatch(new String[]{"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"}, "FoBaT"));
    }

    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> answer = new ArrayList<>();
        for (String query : queries) {
            answer.add(match(query, pattern));
        }
        return answer;
    }
    //优化上一个代码，合并两个循环
    public Boolean match(String query, String pattern) {
        int n = query.length(), m = pattern.length(), index = 0;
        for (int i = 0; i < n; i++) {
            char ch = query.charAt(i);
            if (index < m && pattern.charAt(index) == ch) {
                index++;
            } else if (Character.isUpperCase(ch)) {
                //中途或者最后遇到了大写字符，返回false
                return false;
            }
        }
        return index == m;
    }
    //优化代码，以pattern为基准去匹配，考虑多个异常情况
    public Boolean match2(String query, String pattern) {
        int n = query.length(), m = pattern.length(),i = 0, j = 0;
        while (i < n && j < m) {
            if (query.charAt(i) == pattern.charAt(j)) {
                j++;
            } else if (Character.isUpperCase(query.charAt(i))) {
                //中途遇到了大写字符，返回false
                return false;
            }
            i++;
        }
        //query可能后面还有，需要查看后面是否有大写字符
        for (int k = i; k < n; k++) {
            if (Character.isUpperCase(query.charAt(k))) {
                return false;
            }
        }
        return j == m;
    }

    //索引匹配
    public Boolean match1(String query, String pattern) {
        int n = query.length(), m = pattern.length();
        Map<Character, List<Integer>> map = new HashMap<>();
        int queryUpper = 0, patternUpper = 0;
        for (int i = 0; i < n; i++) {
            char ch = query.charAt(i);
            List<Integer> list = map.getOrDefault(ch, new ArrayList<>());
            list.add(i);
            map.put(ch, list);
            queryUpper += Character.isUpperCase(ch) ? 1 : 0;
        }
        int lastIndex = -1;
        for (int i = 0; i < m; i++) {
            char ch = pattern.charAt(i);
            if (!map.containsKey(ch)) {
                if (Character.isUpperCase(ch)) {
                    return false;
                }
                if (Character.isLowerCase(ch)) {
                    continue;
                }
            }
            List<Integer> list = map.get(ch);
            boolean flag = true;
            for (Integer index : list) {
                if (index > lastIndex) {
                    lastIndex = index;
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return false;
            }
            patternUpper += Character.isUpperCase(ch) ? 1 : 0;
        }
        return patternUpper == queryUpper;
    }
}
