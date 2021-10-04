package integer;
     /*   输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
        示例 1：
        输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
        输出：[1,2,3,6,9,8,7,4,5]

        示例 2：
        输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
        输出：[1,2,3,4,8,12,11,10,9,5,6,7]*/
public class SpiralOrder {
    public static void main(String[] args) {
        int[] ints = spiralOrder(new int[][]{{1,2,3,},{4,5,6},{7,8,9}});
        for (int anInt : ints) {
            System.out.print(anInt+" ");
        }
    }
    static int num;
    static int[] k;
    public static int[] spiralOrder(int[][] matrix) {
        if(matrix.length==0) return new int[]{};
        k=new int[matrix.length*matrix[0].length];
        hh(matrix,0,matrix[0].length-1,0,matrix.length-1);
        return k;
    }
    static void hh(int[][] matrix,int m1, int m2, int n1, int n2) {
        for (int i = m1; i <=m2; i++) {
            k[num] = matrix[n1][i];
            num++;
            if (num == matrix.length*matrix[0].length)
                return;
        }
        for (int i = n1+1; i <= n2; i++) {
            k[num] = matrix[i][m2];
            num++;
            if (num == matrix.length*matrix[0].length)
                return;
        }
        for (int i = m2-1; i >=m1; i--) {
            k[num] = matrix[n2][i];
            num++;
            if (num == matrix.length*matrix[0].length)
                return;
        }
        for (int i = n2-1; i >n1; i--) {
            k[num] = matrix[i][m1];
            num++;
            if (num == matrix.length*matrix[0].length)
                return;
        }
        hh(matrix,m1 + 1, m2 - 1, n1 + 1, n2 - 1);
    }
}
