package string;

/*力扣：1419. 数青蛙
        给你一个字符串 croakOfFrogs，它表示不同青蛙发出的蛙鸣声（字符串 "croak" ）的组合。由于同一时间可以有多只青蛙呱呱作响，所以 croakOfFrogs 中会混合多个 “croak” 。

        请你返回模拟字符串中所有蛙鸣所需不同青蛙的最少数目。

        要想发出蛙鸣 "croak"，青蛙必须 依序 输出 ‘c’, ’r’, ’o’, ’a’, ’k’ 这 5 个字母。如果没有输出全部五个字母，那么它就不会发出声音。如果字符串 croakOfFrogs 不是由若干有效的 "croak" 字符混合而成，请返回 -1 。



        示例 1：

        输入：croakOfFrogs = "croakcroak"
        输出：1
        解释：一只青蛙 “呱呱” 两次
        示例 2：

        输入：croakOfFrogs = "crcoakroak"
        输出：2
        解释：最少需要两只青蛙，“呱呱” 声用黑体标注
        第一只青蛙 "crcoakroak"
        第二只青蛙 "crcoakroak"
        示例 3：

        输入：croakOfFrogs = "croakcrook"
        输出：-1
        解释：给出的字符串不是 "croak" 的有效组合。


        提示：

        1 <= croakOfFrogs.length <= 105
        字符串中的字符只有 'c', 'r', 'o', 'a' 或者 'k'*/
public class MinNumberOfFrogs {
    public static void main(String[] args) {
        System.out.println(new MinNumberOfFrogs().minNumberOfFrogs("croakcroak"));
    }

    //模拟青蛙叫，查看每个时刻需要有多少个青蛙一起叫，记录最大值
    public int minNumberOfFrogs(String croakOfFrogs) {
        int n = croakOfFrogs.length(), res = 0, num = n / 5;
        //cnt[i]  (croak其中一个)当前已经发出i(c,r,o,a,k)音的青蛙个数
        if (n % 5 != 0) {
            return -1;
        }
        int[] cnt = new int['s'];
        for (int i = 0; i < n; i++) {
            char ch = croakOfFrogs.charAt(i);
            cnt[ch]++;
            int c = cnt['c'];
            int r = cnt['r'];
            int o = cnt['o'];
            int a = cnt['a'];
            int k = cnt['k'];
            //此时叫过c的青蛙一定要大于叫过r的青蛙.....
            if (c < r || r < o || o < a || a < k) {
                return -1;
            }
            if (ch == 'c') {
                //k表示已经有k个叫完了
                //c-k表示现在有多少个青蛙一起叫，记录其最大值
                res = Math.max(res, c - k);
            }
        }
        if (cnt['c'] != num || cnt['r'] != num || cnt['o'] != num || cnt['a'] != num || cnt['k'] != num) {
            return -1;
        }
        return res;
    }
}
