package integer;

import java.util.ArrayList;
import java.util.List;
      /*  输入一个正整数 target ，
        输出所有和为 target 的连续正整数序列（至少含有两个数）。
        序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。

        示例 1：
        输入：target = 9
        输出：[[2,3,4],[4,5]]

        示例 2：
        输入：target = 15
        输出：[[1,2,3,4,5],[4,5,6],[7,8]]
        思路：
                滑动窗口可以看成数组中框起来的一个部分。在一些数组类题目中，
                我们可以用滑动窗口来观察可能的候选结果。当滑动窗口从数组的左边滑到了右边，
                我们就可以从所有的候选结果中找到最优的结果。
*/
public class FindContinuousSequence {
    public static void main(String[] args) {
        int[][] sequence = findContinuousSequence(9);
        for(int i=0;i<sequence.length;i++){
            for(int j=0;j<sequence[i].length;j++){
                System.out.print(sequence[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static int[][] findContinuousSequence(int target) {
        int i=1;
        int j=2;
        int sum=3;
        List<int[]> result=new ArrayList<>();
        while(i<=target/2){
            if(sum<target){
                j++;
                sum+=j;
            }else if(sum>target){
                sum-=i;
                i++;
            }else{
                int[] temp=new int[j-i+1];
                for(int k=i;k<=j;k++){
                    temp[k-i]=k;
                }
                result.add(temp);
                sum-=i;
                i++;
                j++;
                sum+=j;
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
