package competition.sfkj_2023_05_21_346;

/*力扣：6441. 求一个整数的惩罚数 显示英文描述
        通过的用户数567
        尝试过的用户数622
        用户总通过次数577
        用户总提交次数714
        题目难度Medium
        给你一个正整数 n ，请你返回 n 的 惩罚数 。

        n 的 惩罚数 定义为所有满足以下条件 i 的数的平方和：

        1 <= i <= n
        i * i 的十进制表示的字符串可以分割成若干连续子字符串，且这些子字符串对应的整数值之和等于 i 。


        示例 1：

        输入：n = 10
        输出：182
        解释：总共有 3 个整数 i 满足要求：
        - 1 ，因为 1 * 1 = 1
        - 9 ，因为 9 * 9 = 81 ，且 81 可以分割成 8 + 1 。
        - 10 ，因为 10 * 10 = 100 ，且 100 可以分割成 10 + 0 。
        因此，10 的惩罚数为 1 + 81 + 100 = 182
        示例 2：

        输入：n = 37
        输出：1478
        解释：总共有 4 个整数 i 满足要求：
        - 1 ，因为 1 * 1 = 1
        - 9 ，因为 9 * 9 = 81 ，且 81 可以分割成 8 + 1 。
        - 10 ，因为 10 * 10 = 100 ，且 100 可以分割成 10 + 0 。
        - 36 ，因为 36 * 36 = 1296 ，且 1296 可以分割成 1 + 29 + 6 。
        因此，37 的惩罚数为 1 + 81 + 100 + 1296 = 1478


        提示：

        1 <= n <= 1000*/
public class PunishmentNumber {
    public static void main(String[] args) {
        System.out.println(new PunishmentNumber().punishmentNumber(37));
    }

    public int punishmentNumber(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            int pow = i * i;
            if (judge(pow, i)) {
                res += pow;
            }
        }
        return res;
    }

    public boolean judge(int pow, int n) {
        if (pow == n) {
            return true;
        }
        int temp = pow, num = 1;
        while (temp != 0) {
            num *= 10;
            temp /= 10;

            int other = pow - temp * num;
            if (other + temp == n) {
                return true;
            }
            //可能是多个子数组，需要递归
            if (judge(temp, n - other)) {
                return true;
            }
        }
        return false;
    }
}
