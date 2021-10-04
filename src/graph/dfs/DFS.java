package graph.dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
        你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。
        若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。池塘的大小是指相连接的水域的个数。
        编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。

        示例：

        输入：
        [
        [0,2,1,0],
        [0,1,0,1],
        [1,1,0,1],
        [0,1,0,1]
        ]
        输出： [1,2,4]*/
public class DFS {
    public static void main(String[] args) {
        int[] ints = pondSizes(new int[][]{{0, 2, 1, 0}, {0, 1, 0, 1}, {1, 1, 0, 1}, {0, 1, 0, 1}});
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
    public static int[] pondSizes(int[][] land) {
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<land.length;i++){
            for(int j=0;j<land[i].length;j++){
                if(land[i][j]==0){
                    list.add(hh(land,i,j));
                }
            }
        }
        Collections.sort(list);
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }
    static  int hh(int[][] land,int i,int j){
        if(i<0||i>=land.length||j<0||j>=land[0].length||land[i][j]!=0){
            return 0;
        }
        land[i][j]=1;
        return hh(land,i+1,j)+hh(land,i-1,j)+ hh(land,i,j+1)+hh(land,i,j-1)+1
                +hh(land,i+1,j+1)+hh(land,i-1,j-1)+ hh(land,i-1,j+1)+hh(land,i+1,j-1)
                ;
    }
}
