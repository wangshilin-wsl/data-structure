package dynamic_programming;
       /* 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。
        打印出s的所有可能的值出现的概率。
        你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i
        小的那个的概率。

        示例 1:
        输入: 1
        输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]

        示例 2:
        输入: 2
        输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
         
        限制：
        1 <= n <= 11*/
public class TwoSum {
    public static void main(String[] args) {
        double[] sum = new TwoSum().twoSum(2);
        for (double v : sum) {
            System.out.println(v);
        }
    }
    public double[] twoSum(int n) {
        double[] result=new double[]{1/6d,1/6d,1/6d,1/6d,1/6d,1/6d};
        double[] temp;
        for(int i=2;i<=n;i++){
            temp=new double[i*5+1];
            for(int k=0;k<result.length;k++){
                for(int j=0;j<6;j++){
                    temp[k+j]+=result[k]/6;
                }
            }
            result=temp;
        }
        return  result;
    }
}
