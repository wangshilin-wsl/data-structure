package integer;

import java.util.ArrayList;
import java.util.List;
/*给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。

        示例:
        输入: n = 4, k = 2
        输出:
        [
        [2,4],
        [3,4],
        [2,3],
        [1,2],
        [1,3],
        [1,4],
        ]*/

public class Combine {
    public static void main(String[] args) {
        List<List<Integer>> lists = new Combine().combine(4, 2);
        for(int i=0;i<lists.size();i++){
            for(int j=0;j<lists.get(i).size();j++){
                System.out.print(lists.get(i).get(j)+" ");
            }
            System.out.println();
        }
    }
    int[] temp;
    int count;
    int i=0;
    int sum;
    List<List<Integer>> list=new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        temp=new int[k+1];
        temp[0]=0;
        count=k;
        sum=n;
        hh(1);
        return list;
    }
    void hh(int k){
        if(k>count){
            list.add(new ArrayList<>());
            for(int j=1;j<=count;j++){
                list.get(i).add(temp[j]);
            }
            i++;
        }else{
            for(int j=temp[k-1]+1;j<=sum;j++){
                temp[k]=j;
                hh(k+1);
            }
        }
    }
}
