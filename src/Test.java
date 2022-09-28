import java.util.*;

/**
 * @author WSL
 * @version 1.0.0
 * @ClassName Test.java
 * @Description TODO
 * @createTime 2022年09月26日 19:08:00
 */
public class Test {

    public static void main(String[] args) {
        int[] nums = new int[]{3,6,5,1,8};
        System.out.println(hh(nums));
    }

    public static int hh(int[] nums){
        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        list.add(new ArrayList<>());
        Map<Integer, List<Integer>> map = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] % 3 == 0){
                sum += nums[i];
            }else {
                int temp = nums[i] % 3;
                list.get(temp - 1).add(nums[i]);
            }
        }
        Collections.sort(list.get(0));
        Collections.sort(list.get(1));
        int n1 = list.get(0).size();
        int n2 = list.get(1).size();
        int min = Math.min(list.get(0).size(), list.get(1).size());
        for (int i = 0; i < min; i++) {
            sum += list.get(0).get(n1 - i -1) + list.get(1).get(n2 - i -1);
        }

        if(n1 - min > 3){
            int sum1 = 0;
            int j =0;
            for (int i = n1 - min; i >= 0; i--) {
                j++;
                sum1+=list.get(0).get(i);
                if(j % 3 == 0){
                    sum+=sum1;
                    sum1 = 0;
                }
            }
        }
        if (n2 - min > 3){
            int sum1 = 0;
            int j =0;
            for (int i = n2 - min; i >= 0; i--) {
                j++;
                sum1+=list.get(1).get(i);
                if(j % 3 == 0){
                    sum+=sum1;
                    sum1 = 0;
                }
            }
        }


        return sum;
    }
}
