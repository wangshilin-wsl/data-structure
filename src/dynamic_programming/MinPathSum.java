package dynamic_programming;
       /* 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，
        使得路径上的数字总和为最小。
        说明：每次只能向下或者向右移动一步。

        示例:

        输入:
        [
        [1,3,1],
        [1,5,1],
        [4,2,1]
        ]
        输出: 7
        解释: 因为路径 1→3→1→1→1 的总和最小。
*/
public class MinPathSum {
    public static void main(String[] args) {
        System.out.println(new MinPathSum().minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}}));
    }
    public int minPathSum(int[][] grid) {
        int[][] min=new int[grid.length][grid[0].length];//min[i][j]表示到（i,j）的最小值
        min[0][0]=grid[0][0];
        for(int i=1;i<grid.length;i++){
            min[i][0]=min[i-1][0]+grid[i][0];//初始化第一列的值，只能由它的上面往下走
        }
        for(int i=1;i<grid[0].length;i++){
            min[0][i]=min[0][i-1]+grid[0][i];//初始化第一行的值，只能由它的左边往右走
        }
        for(int i=1;i<grid.length;i++){
            for(int j=1;j<grid[i].length;j++){
                min[i][j]=Math.min(min[i-1][j],min[i][j-1])+grid[i][j];
            }
        }
        return  min[grid.length-1][grid[0].length-1];
    }
}
