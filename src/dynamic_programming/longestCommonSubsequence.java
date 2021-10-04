package dynamic_programming;

/*问题描述：最长公共子序列
        给定两个序列：X[1...m]和Y[1...n]，求在两个序列中同时出现的最长子序列的长度。

        假设 X 和 Y 的序列如下：

        X[1...m] = {A, B, C, B, D, A, B}
        Y[1...n] = {B, D, C, A, B, A}
        可以看出，X 和 Y 的最长公共子序列有 “BDAB”、“BCAB”、“BCBA”，即长度为4。
        dp[i][j]=dp[i-1][j-1]+1    if(a[i]==b[j])
        dp[i][j]=max(dp[i][j-1],dp[i-1][j])   if(a[i]!=b[j])
        */
public class longestCommonSubsequence {
    public static void main(String[] args) {
        System.out.println(dp("BDCABA","ABCBDAB"));
    }
    public static int dp(String a,String b){
        int[][] result=new int[a.length() + 1][b.length() + 1];//动态规划表
        for(int i=0;i<a.length();i++){//初始化其他格子
            for(int j=0;j<b.length();j++){
                if(a.charAt(i)==b.charAt(j)){
                    result[i+1][j+1]=result[i][j]+1;
                }else{
                    result[i+1][j+1]=Math.max(result[i][j+1],result[i+1][j]);
                }
            }
        }
        for(int i=0;i<result.length;i++){
            for(int j=0;j<result[i].length;j++){
                System.out.print(result[i][j]);
            }
            System.out.println();
        }
        return result[a.length()][b.length()];
    }
}

