package n_queen;

import java.util.Scanner;

public class NQueen {
    static int n;
    static int[] place;
    static int sum=0;
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        n=scanner.nextInt();
        place=new int[n+1];
        backtrack(1);
        System.out.println(sum);
    }
    public static void backtrack(int t){
        if(t>n){
            sum++;
           /* for(int i=1;i<=n;i++){
                System.out.print(place[i]+" ");
            }
            System.out.println();*/
            return;
        }else {
            for(int i=1;i<=n;i++){
                place[t]=i;
                if(judge(t)){
                    backtrack(t+1);
                }
            }
        }
    }
    public static boolean judge(int t) {//用于判断第t行与之前已有的行之间是否有冲突
        for(int i=1;i<t;i++){
            if(place[i]==place[t]||Math.abs(place[i]-place[t])==Math.abs(t-i)){
                return false;
            }
        }
        return true;
    }
}
