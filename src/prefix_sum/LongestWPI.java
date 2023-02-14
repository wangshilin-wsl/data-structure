package prefix_sum;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/*力扣：1124. 表现良好的最长时间段
        给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数。

        我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是「劳累的一天」。

        所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」。

        请你返回「表现良好时间段」的最大长度。



        示例 1：

        输入：hours = [9,9,6,0,6,6,9]
        输出：3
        解释：最长的表现良好时间段是 [9,9,6]。
        示例 2：

        输入：hours = [6,6,6]
        输出：0


        提示：

        1 <= hours.length <= 104
        0 <= hours[i] <= 16*/
public class LongestWPI {
    public static void main(String[] args) {
        new LongestWPI().longestWPI(new int[]{6,6,9});
    }
    //单调栈的变种用法
    public int longestWPI(int[] hours) {
        //1、使用单调递增栈记录前缀和
        //2、注意这个和传统意义的单调栈(找到最左边第一个大于的数)用法不同
        int len = hours.length, max = 0;
        int[] goodDay = new int[len + 1];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        for (int i = 1; i <= len; i++) {
            goodDay[i] = goodDay[i - 1] + (hours[i - 1] > 8 ? 1 : -1);
            if (goodDay[stack.peek()] > goodDay[i]) {
                stack.push(i);
            }
        }
        for (int i = len; i >= 1; i--) {
            while (!stack.isEmpty() && goodDay[stack.peek()] < goodDay[i]) {
                max = Math.max(max, i - stack.pop());
            }
        }

        return max;
    }

    //前缀和+hash表
    public int longestWPI2(int[] hours) {
        //1、可以把>8的天当做+1,<=8的天当做-1
        //2、前缀和s[i]为前i天劳累-轻松，如果s[i]>0则[0,i]就是表现良好的一个区间
        //3、如果s[i]<=0，则需要往前找比s[i]小的一天s[j]，j是最小的
        //4、由于每次都是+1或者-1，所以最左边第一次出现比s[i]小的一定是s[i]-1，但是由于可能又多个s[i]-1，所以遇到了不用更新
        //5、要比s[i]小，只能-1则最先出现的就是s[i] -1
        int len = hours.length, max = 0, goodDay = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            goodDay += hours[i] > 8 ? 1 : -1;
            //第三条，goodDay>0，[0,i]就是最长的表现良好的区间
            max = Math.max(max, goodDay > 0 ? i + 1 : i - map.getOrDefault(goodDay - 1, i));
            if (!map.containsKey(goodDay)) {
                map.put(goodDay, i);
            }
        }
        return max;
    }

    //暴力法
    public int longestWPI3(int[] hours) {
        int len = hours.length, max = 0, hard = 0, easy = 0, temp;
        for (int i = 0; i < len; i++) {
            hard = 0;
            easy = 0;
            for (int j = i; j < len; j++) {
                temp = hours[j] > 8 ? hard++ : easy++;
                if (hard > easy) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }
}
