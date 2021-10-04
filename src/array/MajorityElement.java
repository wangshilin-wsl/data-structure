package array;

     /*   数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
        你可以假设数组是非空的，并且给定的数组总是存在多数元素。

        示例 1:
        输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
        输出: 2
        思路:用hashmap
        */

import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
    public static void main(String[] args) {
        System.out.println(new MajorityElement().majorityElement(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2}));
    }
    public int majorityElement(int[] nums) {
        Map<Integer,Integer> map=new HashMap<>();
        int result=-1;
        for(int temp:nums){
            if(map.containsKey(temp)){
                map.put(temp,map.get(temp)+1);
            }else{
                map.put(temp,1);
            }
            if(map.get(temp)>nums.length/2){
                result=temp;
                break;
            }
        }
        return result;
    }
}
