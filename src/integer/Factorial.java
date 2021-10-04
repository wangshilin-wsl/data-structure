package integer;

import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        System.out.println("请输入一个整数:");
        Scanner input=new Scanner(System.in);
        int number=input.nextInt();
        int[] a=new int[40000];//定义一个数组
        int c,temp,j,i,digit=1; //c表示进位，temp临时结果，digit表示位数
        a[0]=1;//初始值为1
        for(i=2;i<=number;i++){
            for(c=0,j=1;j<=digit;j++){
                temp=a[j-1]*i+c;
                a[j-1]=temp%10;
                c=temp/10;
            }
            while(c>0){
                digit++;
                a[digit-1]=c%10;
                c=c/10;
            }
        }
        for(int u=digit-1;u>=0;u--){
            System.out.print(a[u]);
        }
    }
}
