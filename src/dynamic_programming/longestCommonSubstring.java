package dynamic_programming;
/*
有两个字符串，这两个字符串可能会存在公共的部分，如字符串"abcdef" 和字符串"defg"
        ,这两个字符串之间有共同的字符串，“d”,“e”,“f”,“de”,“ef”,“def” 等。
        最长的公共子串就是"def"。
        dp[i][j]必须要包含a的第i个字符，b的第j个字符
        dp[i][j] = dp[i-1][j-1] +1   if(a.charAt(i) == b.charAt(j))
        dp[i][j] =  0        if(a.charAt(i) != b.charAt(j))
        */
public class longestCommonSubstring {
    public static void main(String[] args) {
        System.out.println(dp("abcdef","defg"));
    }
    public static int dp(String a,String b){
        int[][] result=new int[a.length()+1][b.length()+1];//result[i][j]表示a以第i个字符结尾的子串，b以第j个字符结尾子串的最大公共子串
        int max=Integer.MIN_VALUE;
        int x=0;
        for(int i=0;i<a.length();i++){
            for(int j=0;j<b.length();j++){
               if(a.charAt(i)==b.charAt(j)){
                    result[i+1][j+1]=result[i][j]+1;
                }else{
                    result[i+1][j+1]=0;
                }
                if(result[i+1][j+1]>max){
                    max=result[i+1][j+1];
                    x=i;
                }
            }
        }
        StringBuilder str=new StringBuilder();
        for(int i=max;i>0;i--){
            str.append(a.charAt(x));
            x--;
        }
        for(int i=1;i<result.length;i++){
            for(int j=1;j<result[i].length;j++){
                System.out.print(result[i][j]);
            }
            System.out.println();
        }
        System.out.println(str.reverse().toString());
        return max;
    }
}
