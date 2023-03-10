package slide_window;

import java.util.HashMap;
import java.util.Map;

/*力扣：1590. 使数组和能被 P 整除
        给你一个正整数数组 nums，请你移除 最短 子数组（可以为 空），使得剩余元素的 和 能被 p 整除。 不允许 将整个数组都移除。

        请你返回你需要移除的最短子数组的长度，如果无法满足题目要求，返回 -1 。

        子数组 定义为原数组中连续的一组元素。



        示例 1：

        输入：nums = [3,1,4,2], p = 6
        输出：1
        解释：nums 中元素和为 10，不能被 p 整除。我们可以移除子数组 [4] ，剩余元素的和为 6 。
        示例 2：

        输入：nums = [6,3,5,2], p = 9
        输出：2
        解释：我们无法移除任何一个元素使得和被 9 整除，最优方案是移除子数组 [5,2] ，剩余元素为 [6,3]，和为 9 。
        示例 3：

        输入：nums = [1,2,3], p = 3
        输出：0
        解释：和恰好为 6 ，已经能被 3 整除了。所以我们不需要移除任何元素。
        示例  4：

        输入：nums = [1,2,3], p = 7
        输出：-1
        解释：没有任何方案使得移除子数组后剩余元素的和被 7 整除。
        示例 5：

        输入：nums = [1000000000,1000000000,1000000000], p = 3
        输出：0


        提示：

        1 <= nums.length <= 105
        1 <= nums[i] <= 109
        1 <= p <= 109*/
public class MinSubarray {
    public static void main(String[] args) {
        System.out.println(new MinSubarray().minSubarray(new int[]{3, 1, 4, 2}, 6));
    }

    //前缀和f[i]表示[0,i)的和，不包括了i
    public int minSubarray(int[] nums, int p) {
        //思路：前缀和
        //先算出nums的和对p取余的结果mod，如果mod=0,说明就是可以整除的，直接返回0
        //遍历nums，记住每个每个下标对应前缀和对p取余的结果x
        //对于x，需要在之前找到一个x1，使得(x - x1 + p) % p = mod
        // (x - x1)则就表示前缀和的差，就是子数组的和[x1,x],所以需要一个map来记录x和index（下标）的关系，遇到相同的x直接覆盖，因为要最小的长度
        //x1 = (x - mod + p) % p
        int len = nums.length, mod = 0, min = len, modSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            mod = (nums[i] + mod) % p;
        }
        if (mod == 0) {
            return 0;
        }
        //解决符合的数组正好从0开始
        //获取map中value表示下标，key表示value以前的前缀和也可以解决
        for (int i = 0; i < len; i++) {
            map.put(modSum, i);
            modSum = (modSum + nums[i]) % p;
            int answer = (modSum - mod + p) % p;
            if (map.containsKey(answer)) {
                min = Math.min(min, i - map.get(answer) + 1);
            }
        }
        return min == len ? -1 : min;
    }

    //前缀和f[i]表示[0,i]的和，包括了i
    public int minSubarray2(int[] nums, int p) {
        //思路：前缀和
        //先算出nums的和对p取余的结果mod，如果mod=0,说明就是可以整除的，直接返回0
        //遍历nums，记住每个每个下标对应前缀和对p取余的结果x
        //对于x，需要在之前找到一个x1，使得(x - x1 + p) % p = mod
        // (x - x1)则就表示前缀和的差，就是子数组的和[x1,x],所以需要一个map来记录x和index（下标）的关系，遇到相同的x直接覆盖，因为要最小的长度
        //x1 = (x - mod + p) % p
        int len = nums.length, mod = 0, min = len, modSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            mod = (nums[i] + mod) % p;
        }
        if (mod == 0) {
            return 0;
        }
        //解决符合的数组正好从0开始
        //获取map中value表示下标，key表示value以前的前缀和也可以解决
        map.put(0, -1);
        for (int i = 0; i < len; i++) {
            modSum = (modSum + nums[i]) % p;
            map.put(modSum, i);
            int answer = (modSum - mod + p) % p;
            if (map.containsKey(answer)) {
                min = Math.min(min, i - map.get(answer));
            }
        }
        return min == len ? -1 : min;
    }


    public int minSubarray3(int[] nums, int p) {
        int len = nums.length, mod = 0, leftMod, min = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            mod = (nums[i] + mod) % p;
        }
        if (mod == 0) {
            return 0;
        }
        for (int i = 0; i < len; i++) {
            leftMod = nums[i] % p;
            if (leftMod % p == mod) {
                return 1;
            }
            for (int j = i + 1; j < len; j++) {
                leftMod = (leftMod + nums[j]) % p;
                if (leftMod % p == mod) {
                    min = Math.min(min, j - i + 1);
                }
            }
        }
        return min == Integer.MAX_VALUE || min == len ? -1 : min;
    }
}
