package array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author WSL
 * @version 1.0.0
 * @ClassName FindPairs.java
 * @Description TODO
 * @createTime 2022年06月16日 19:16:00
 */
/*力扣：第532题
532. 数组中的 k-diff 数对
        给你一个整数数组 nums 和一个整数 k，请你在数组中找出 不同的 k-diff 数对，并返回不同的 k-diff 数对 的数目。

        k-diff 数对定义为一个整数对 (nums[i], nums[j]) ，并满足下述全部条件：

        0 <= i, j < nums.length
        i != j
        nums[i] - nums[j] == k
        注意，|val| 表示 val 的绝对值。



        示例 1：

        输入：nums = [3, 1, 4, 1, 5], k = 2
        输出：2
        解释：数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
        尽管数组中有两个 1 ，但我们只应返回不同的数对的数量。
        示例 2：

        输入：nums = [1, 2, 3, 4, 5], k = 1
        输出：4
        解释：数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5) 。
        示例 3：

        输入：nums = [1, 3, 1, 5, 4], k = 0
        输出：1
        解释：数组中只有一个 0-diff 数对，(1, 1) 。
        提示：

        1 <= nums.length <= 104
        -107 <= nums[i] <= 107
        0 <= k <= 107
        思路：两个set，一个用于装数组的值，一个用于装结果的，遍历数组，既然知道其中一个值num，
        那么另外一个值就是num - k或者num + k,
        所以将结果num-k或者num+k放入set中，注意这个地方不能直接放num+k，我们将数对中较小的一个
        放入结果set中（可以标记一个唯一的数对），如果将num+k放入,当一个数对的最大值和一个数对
        的最小值一样的时候，判断是同一个数对，显然是不对的，所以需要将一个可以唯一表示数对的一个数
        放入set中，最大值或者最小值。

        */
public class FindPairs {
    public int findPairs1(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> temp = new HashSet<>();
        List<Integer> res;
        int ret = 0;
        for (int num : nums) {
            if(!set.add(num)){
                if(temp.add(num)){
                    ret++;
                }
            }
        }
        if(k == 0){
            return ret;
        }
        ret = 0;
        res = new ArrayList<>(set);
        for (int i = 0; i < res.size(); i++) {
            for (int j = i + 1; j < res.size(); j++) {
                if(Math.abs(res.get(i) - res.get(j)) == k){
                    ret++;
                }
            }
        }
        return ret;
    }

    public int findPairs2(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> temp = new HashSet<>();
        for (int num : nums) {
            if(temp.contains(num - k)){
                set.add(num - k);
            }
            if(temp.contains(k + num)){
                set.add(num);
            }
            temp.add(num);
        }
        return set.size();
    }
}
