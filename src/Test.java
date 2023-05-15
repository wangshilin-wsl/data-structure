import java.util.*;

/*给你一个下标从 0开始的数组nums，它包含 n个 互不相同的正整数。
        请你对这个数组执行 m个操作，在第 i个操作中，
        你需要将数字operations[i][0] 替换成operations[i][1]。

        题目保证在第 i个操作中：

        operations[i][0]在nums中存在。
        operations[i][1]在nums中不存在。
        请你返回执行完所有操作后的数组。



        示例 1：

        输入：nums = [1,2,4,6], operations = [[1,3],[4,7],[6,1]]
        输出：[3,2,7,1]
        解释：我们对 nums 执行以下操作：
        - 将数字 1 替换为 3 。nums 变为 [3,2,4,6] 。
        - 将数字 4 替换为 7 。nums 变为 [3,2,7,6] 。
        - 将数字 6 替换为 1 。nums 变为 [3,2,7,1] 。
        返回最终数组 [3,2,7,1] 。
        示例 2：

        输入：nums = [1,2], operations = [[1,3],[2,1],[3,2]]
        输出：[2,1]
        解释：我们对 nums 执行以下操作：
        - 将数字 1 替换为 3 。nums 变为 [3,2] 。
        - 将数字 2 替换为 1 。nums 变为 [3,1] 。
        - 将数字 3 替换为 2 。nums 变为 [2,1] 。
        返回最终数组 [2,1] 。


        提示：

        n == nums.length
        m == operations.length
        1 <= n, m <= 105
        nums中所有数字 互不相同。
        operations[i].length == 2
        1 <= nums[i], operations[i][0], operations[i][1] <= 106
        在执行第i 个操作时，operations[i][0]在nums中存在。
        在执行第i个操作时，operations[i][1]在nums中不存在。*/
public class Test {
    int min = Integer.MAX_VALUE, abs = Integer.MAX_VALUE;

    public static void main(String[] args) {
        System.out.println(new Test().numPairsDivisibleBy60(new int[]{15,63,451,213,37,209,343,319}));
    }

    public int numPairsDivisibleBy60(int[] time) {
        int n = time.length;
        long sum = 0L;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int mod = time[i] % 60;
            map.put(mod, map.getOrDefault(mod, 0) + 1);
        }
        for (int ti : time) {
            int mod = ti % 60;
            int other = (60 - mod) % 60;
            if (!map.containsKey(other)) {
                continue;
            }
            int num = map.get(other);
            sum += mod == other ? num - 1 : num;
        }
        return (int)(sum / 2);
    }

    public String greatestLetter(String s) {
        int[] low = new int[26], upper = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isUpperCase(ch)) {
                upper[ch - 'A']++;
            }
            if (Character.isLowerCase(ch)) {
                low[ch - 'a']++;
            }
        }
        for (int i = 25; i >= 0; i--) {
            if (low[i] >= 1 && upper[i] >= 1) {
                return (char) ('A' + i) + "";
            }
        }
        return "";
    }

    public String getSmallestString(int n, int k) {
        char[] res = new char[n];
        for (int i = 0; i < n; i++) {
            res[i] = 'a';
        }
        int sub = k - n, index = n - 1;
        while (sub > 0) {
            if (++res[index] == 'z') {
                index--;
            }
            sub--;
        }

        return new String(res);
    }

    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        int[] res = new int[k];
        Map<Integer, Set<Integer>> activeMinutes = new HashMap<>();
        for (int[] log : logs) {
            int id = log[0], time = log[1];
            activeMinutes.putIfAbsent(id, new HashSet<Integer>());
            activeMinutes.get(id).add(time);
        }
        for (Map.Entry<Integer, Set<Integer>> entry : activeMinutes.entrySet()) {
            res[entry.getValue().size() - 1]++;
        }
        return res;
    }


    public boolean strongPasswordCheckerII(String password) {
        int n = password.length();
        if (n < 8 ) {
            return false;
        }
        boolean num = false, low = false, upper = false, special = false;
        Set<Character> specials = new HashSet<Character>() {{
            add('!');
            add('@');
            add('#');
            add('$');
            add('%');
            add('^');
            add('&');
            add('*');
            add('(');
            add(')');
            add('-');
            add('+');
        }};
        for (int i = 0; i < n; i++) {
            char ch = password.charAt(i);
            if (Character.isDigit(ch)) {
                num = true;
            }
            if (Character.isUpperCase(ch)) {
                upper = true;
            }
            if (Character.isLowerCase(ch)) {
                low = true;
            }
            if (specials.contains(ch)) {
                special = true;
            }
            if (i > 0 && ch == password.charAt(i - 1)) {
                return false;
            }
        }
        return special && num && upper && low;
    }

    public int minMaxGame(int[] nums) {
        while (nums.length != 1) {
            int[] temp = new int[nums.length / 2];
            for (int i = 0; i < temp.length; i++) {
                temp[i] = (i & 1) == 0 ? Math.min(nums[i * 2], nums[i * 2 + 1]) : Math.max(nums[i * 2], nums[i * 2 + 1]);
            }
            nums = temp;
        }
        return nums[0];
    }

    public int prefixCount(String[] words, String pref) {
        int res = 0;
        for (String word : words) {
            if (word.startsWith(pref)) {
                res++;
            }
        }
        return res;
    }


    public char repeatedCharacter(String s) {
       Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if (!set.add(temp)) {
                return temp;
            }
        }
        return 'a';
    }

    public int minMovesToSeat(int[] seats, int[] students) {
        int res = 0, n = seats.length;
        Arrays.sort(seats);
        Arrays.sort(students);
        for (int i = 0; i < n; i++) {
            res += Math.abs(seats[i] - students[i]);
        }
        return res;
    }

    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            map.put(i, 1);
        }
        for (int i : nums2) {
            map.put(i, map.getOrDefault(i, 0) | 2);
        }
        for (int i : nums3) {
            map.put(i, map.getOrDefault(i, 0) | 4);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int value = entry.getValue();
            if ((value & (value - 1)) != 0) {
                list.add(entry.getKey());
            }
        }
        return list;
    }

    public int minimumMoves(String s) {
        int n = s.length(), res = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'X') {
                i += 2;
                res++;
            }
        }
        return res;
    }

    public String largestMerge(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int i1 = 0,i2 = 0,n1 = word1.length(), n2 = word2.length();
        while (i1 < n1 && i2 < n2) {
            if (word1.charAt(i1) > word2.charAt(i2)) {
                sb.append(word1.charAt(i1++));
            } else if (word1.charAt(i1) < word2.charAt(i2)) {
                sb.append(word2.charAt(i2++));
            } else {
                //0表示
                int flag = 0, i, j;
                for (i = i1, j = i2; i < n1 && j < n2; i++, j++) {
                    if (word1.charAt(i) == word2.charAt(j)) {
                        continue;
                    }
                    flag = word1.charAt(i) > word2.charAt(j) ? 1 : 2;
                    break;
                }
                if (flag == 0) {
                    flag = 1;
                    for (i = i1 + 1; i < n1; i++) {
                        if (word1.charAt(i) == word1.charAt(i - 1)) {
                            continue;
                        }
                        flag = word1.charAt(i) > word1.charAt(i - 1) ? 1 : 2;
                        break;
                    }
                    for (j = i2 + 1; j < n2; j++) {
                        if (word2.charAt(j) == word2.charAt(j - 1)) {
                            continue;
                        }
                        flag = word2.charAt(j) > word2.charAt(j - 1) ? 2 : 1;
                        break;
                    }
                }
                sb.append(flag == 1 ? word1.charAt(i1++) : word2.charAt(i2++));
            }
        }
        sb.append(i1 < n1 ? word1.substring(i1, n1) : word2.substring(i2, n2));
        return sb.toString();
    }

    public int minOperations(int[] nums) {
        int res = 0, last = nums[0], n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] > last) {
                last = nums[i];
                continue;
            }
            res += last + 1 - nums[i];
            last = last + 1;
        }
        return res;
    }

    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        for (int baseCost : baseCosts) {
            dfs(toppingCosts, target, baseCost, 0);
        }
        return abs;
    }
    void dfs(int[] toppingCosts, int target, int sum, int index){
        if (index >= toppingCosts.length) {
            return;
        }
        int temp = toppingCosts[index];
        for (int i = 0; i < 3; i++) {
            int ab = Math.abs(target - sum - temp * i);
            if (ab < min) {
                min = ab;
                abs = sum + temp * i;
            } else if (ab == min) {
                abs = Math.min(sum + temp * i, abs);
            }
            dfs(toppingCosts, target, sum + temp * i, index + 1);
        }
    }

    public int secondHighest(String s) {
        int n = s.length();
        int[] num = new int[10];
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                num[ch - '0']++;
            }
        }
        int second = -1;
        for (int i = num.length - 1, index = 0; i >= 0; i--) {
            if (num[i] != 0) {
                index++;
                if (index == 2) {
                    second = i;
                    break;
                }
            }
        }
        return second;
    }

    public int[] minOperations(String boxes) {
        int n = boxes.length(), l = 0, r = 0;
        int[] left = new int[n], right = new int[n], res = new int[n];
        for (int i = 0; i < n; i++) {
            if (i -1 >= 0) {
                left[i] = left[i - 1] + l;
                right[n - i - 1] = right[n - i] + r;
            }
            if (boxes.charAt(i) == '1') {
                l++;
            }
            if (boxes.charAt(n - i - 1) == '1') {
                r++;
            }
        }
        for (int i = 0; i < n; i++) {
            res[i] = left[i] + right[i];
        }
        return res;
    }


    public double champagneTower(int poured, int query_row, int query_glass) {
        //第几层
        int num = 1;
        while (true) {
            if (poured < num) {
                break;
            }
            poured -= num;
            num++;
        }
        if (query_row + 1 < num) {
            return 1d;
        } else if (query_row + 1 > num) {
            return 0d;
        } else {
            if (num == 1) {
                return (double) poured;
            }
            double lv = (poured * 1.0d) / (num - 1);
            if (query_glass == 0 || query_glass == num - 1){
                return lv / 2;
            }
            return lv;
        }
    }

    public int largestAltitude(int[] gain) {
        int max = 0, last = 0;
        for (int i : gain) {
            last = i + last;
            max = Math.max(max, last);
        }
        return max;
    }
}
