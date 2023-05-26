package highmath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*1073. 负二进制数相加
        给出基数为 -2 的两个数 arr1 和 arr2，返回两数相加的结果。

        数字以 数组形式 给出：数组由若干 0 和 1 组成，按最高有效位到最低有效位的顺序排列。例如，arr = [1,1,0,1] 表示数字 (-2)^3 + (-2)^2 + (-2)^0 = -3。数组形式 中的数字 arr 也同样不含前导零：即 arr == [0] 或 arr[0] == 1。

        返回相同表示形式的 arr1 和 arr2 相加的结果。两数的表示形式为：不含前导零、由若干 0 和 1 组成的数组。



        示例 1：

        输入：arr1 = [1,1,1,1,1], arr2 = [1,0,1]
        输出：[1,0,0,0,0]
        解释：arr1 表示 11，arr2 表示 5，输出表示 16 。
        示例 2：

        输入：arr1 = [0], arr2 = [0]
        输出：[0]
        示例 3：

        输入：arr1 = [0], arr2 = [1]
        输出：[1]


        提示：

        1 <= arr1.length, arr2.length <= 1000
        arr1[i] 和 arr2[i] 都是 0 或 1
        arr1 和 arr2 都没有前导0*/
public class AddNegabinary {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new AddNegabinary().addNegabinary(new int[]{1,1,1,1,1}, new int[]{1,0,1})));
    }
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        //思路：https://leetcode.cn/problems/adding-two-negabinary-numbers/solution/fu-er-jin-zhi-shu-xiang-jia-by-leetcode-nwktq/
        //(-2)^2进位1其实大小为|(-2)^3|但是符号相反，所以可以进位-1
        //所以进位的范围为[-1,1]
        //位置i的大小为[-1,3]，-1的情况由-(-2)^(i+1) = (-2)^i + (-2)^i可以得出-(-2)^i = (-2)^(i+1) + (-2)^i,所以i位置为1，进位为1
        int  n1 = arr1.length - 1, n2 = arr2.length - 1,carry = 0;
        List<Integer> ans = new ArrayList<>();
        while (n1 >= 0 || n2 >= 0 || carry != 0) {
            int temp = (n1 >= 0 ? arr1[n1] : 0) + (n2 >= 0 ? arr2[n2] : 0) + carry;
            //carry（进位）范围为[-1,1]；
            if (temp >= 0) {
                ans.add(temp % 2);
                carry = temp / -2;
            } else {
                ans.add(1);
                carry = 1;
            }
            n1--;
            n2--;
        }
        //去除前面多余的0，但是最起码也要留一位
        while (ans.size() > 1 && ans.get(ans.size() - 1) == 0) {
            ans.remove(ans.size() - 1);
        }
        int n = ans.size();
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            res[n - i - 1] = ans.get(i);
        }
        return res;
    }
}
