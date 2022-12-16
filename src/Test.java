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
        System.out.println(new Test().minOperations(new int[]{8}));
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
