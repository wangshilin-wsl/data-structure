package graph.dfs;
/*
请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
        路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。
        如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
        例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。

        [["a","b","c","e"],
        ["s","f","c","s"],
        ["a","d","e","e"]]

        但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，
        路径不能再次进入这个格子。

        示例 1：
        输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
        输出：true

        示例 2：
        输入：board = [["a","b"],["c","d"]], word = "abcd"
        输出：false

        思路：利用递归实现回溯
*/
public class Exist {
    public static void main(String[] args) {
        System.out.println(new Exist().exist(new char[][]{{'A','B','C','E'},{'S','F','E','S'},
                {'A','D','E','E'}},"ABCESEEEFS"));
    }
    public boolean exist(char[][] board, String word) {
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j]==word.charAt(0)){
                    if(hh(i,j,board,word,0)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean hh(int x,int y,char[][] board,String word,int i){
        if(x<0||x>=board.length||y<0||y>=board[0].length
                ||board[x][y]!=word.charAt(i)){
            return false;
        }
        if(board[x][y]==word.charAt(i)&&i==word.length()-1) return true;
        // System.out.println(x+" "+y);
        char temp=board[x][y];
        board[x][y]=' ';//表示该点被访问了，对于它的下层递归来说该点被访问了
        boolean a=hh(x+1,y,board,word,i+1)||hh(x-1,y,board,word,i+1)||
                hh(x,y+1,board,word,i+1)||hh(x,y-1,board,word,i+1);
        board[x][y]=temp;//x,y的点的递归结束了，所以还原board[x][y]的值，以供上层使用
        return a;
    }
}
