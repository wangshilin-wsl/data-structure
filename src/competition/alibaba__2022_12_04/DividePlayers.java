package competition.alibaba__2022_12_04;

import java.util.Arrays;

/*力扣：6254. 划分技能点相等的团队
        给你一个正整数数组 skill ，数组长度为 偶数 n ，其中 skill[i] 表示第 i 个玩家的技能点。
        将所有玩家分成 n / 2 个 2 人团队，使每一个团队的技能点之和 相等 。
        团队的 化学反应 等于团队中玩家的技能点 乘积 。
        返回所有团队的 化学反应 之和，如果无法使每个团队的技能点之和相等，则返回 -1 。

        示例 1：
        输入：skill = [3,2,5,1,3,4]
        输出：22
        解释：
        将玩家分成 3 个团队 (1, 5), (2, 4), (3, 3) ，每个团队的技能点之和都是 6 。
        所有团队的化学反应之和是 1 * 5 + 2 * 4 + 3 * 3 = 5 + 8 + 9 = 22 。

        示例 2：
        输入：skill = [3,4]
        输出：12
        解释：
        两个玩家形成一个团队，技能点之和是 7 。
        团队的化学反应是 3 * 4 = 12 。

        示例 3：
        输入：skill = [1,1,2,3]
        输出：-1
        解释：
        无法将玩家分成每个团队技能点都相等的若干个 2 人团队。

        提示：
        2 <= skill.length <= 105
        skill.length 是偶数
        1 <= skill[i] <= 1000
        思路：先找到分组的均分avg
        可以用map或者int数组记录所有数字，循环判断是否有可以组成avg
        优化：可以先排序，最大的一定只能和最小的组合，不然就直接可以返回-1了
        */
public class DividePlayers {

    public static void main(String[] args) {
        new DividePlayers().dividePlayers(new int[]{3,2,5,1,3,4});
    }
    public long dividePlayers(int[] skill) {
        int n = skill.length, m = n / 2, avg;
        long res = 0L;
        Arrays.sort(skill);
        avg = skill[0] + skill[n - 1];
        for (int i = 0; i < m; i++) {
            if (skill[i] + skill[n - i - 1] != avg){
                return -1;
            }
            res += skill[i] * skill[n - i - 1];
        }
        return res;
    }

    public long dividePlayers1(int[] skill) {
        int n = skill.length, sum = 0, m = n / 2, avg;
        long res = 0L;
        int[] index = new int[1001];
        for (int i = 0; i < n; i++) {
            sum += skill[i];
            index[skill[i]]++;
        }
        if (sum % m != 0){
            return -1;
        }
        avg = sum / m;

        for (int i = 0; i < n; i++) {
            int temp = avg - skill[i];
            if (index[temp] <= 0) {
                return -1;
            }
            index[temp]--;
            res += temp * skill[i];
        }
        return res / 2;
    }
}
