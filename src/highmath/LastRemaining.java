package highmath;

     /*   0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。
        求出这个圆圈里剩下的最后一个数字。
        例如，0、1、2、3、4这5个数字组成一个圆圈，
        从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。

        示例 1：
        输入: n = 5, m = 3
        输出: 3

        示例 2：
        输入: n = 10, m = 17
        输出: 2
         
        限制：
        1 <= n <= 10^5
        1 <= m <= 10^6
        思路：
        这个问题是以弗拉维奥·约瑟夫命名的，他是1世纪的一名犹太历史学家。
        他在自己的日记中写道，他和他的40个战友被罗马军队包围在洞中。
        他们讨论是自杀还是被俘，最终决定自杀，并以抽签的方式决定谁杀掉谁。
        约瑟夫斯和另外一个人是最后两个留下的人。
        约瑟夫斯说服了那个人，他们将向罗马军队投降，不再自杀。
        约瑟夫斯把他的存活归因于运气或天意，他不知道是哪一个。 —— 【约瑟夫问题】维基百科
        数学解法，O(n)O(n)
        这么著名的约瑟夫环问题，是有数学解法的！

        力扣：剑指 Offer 62. 圆圈中最后剩下的数字


*/

public class LastRemaining {
    public static void main(String[] args) {
        System.out.println(lastRemaining(10,17));
    }
   /* //方法一，这个时间用多了
    public static int lastRemaining(int n, int m) {
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<n;i++){
            list.add(i);
        }
        int i=0;
        while(list.size()!=1){
            i=(m-1+i)%list.size();
            list.remove(i);
        }
        return list.get(0);
    }*/
   public static int lastRemaining(int n, int m) {
           int ans = 0;
           // 最后一轮剩下2个人，所以从2开始反推
           for (int i = 2; i <= n; i++) {
               ans = (ans + m) % i;
           }
           return ans;

   }
}
