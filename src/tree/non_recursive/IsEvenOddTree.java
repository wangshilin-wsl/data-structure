package tree.non_recursive;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author WSL
 * @version 1.0.0
 * @ClassName IsEvenOddTree.java
 * @Description 奇偶树
 * @createTime 2021年12月25日 17:19:00
 */
/*力扣题号：1609
如果一棵二叉树满足下述几个条件，则可以称为 奇偶树 ：

        二叉树根节点所在层下标为 0 ，根的子节点所在层下标为 1 ，根的孙节点所在层下标为 2 ，依此类推。
        偶数下标 层上的所有节点的值都是 奇 整数，从左到右按顺序 严格递增
        奇数下标 层上的所有节点的值都是 偶 整数，从左到右按顺序 严格递减
        给你二叉树的根节点，如果二叉树为 奇偶树 ，则返回 true ，否则返回 false 。
        */

public class IsEvenOddTree {

     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode() {}
         TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }

    //第一种方式，先将所有节点以广度遍历的方法放到一个list中
    List<List<Integer>> lists = new ArrayList<>();
    public boolean isEvenOddTree1(TreeNode root) {
        hh(root, 0);
        //判断是否是奇偶树
        for (int i = 0; i < lists.size(); i++) {
            if(i %2 ==0){
                //偶数层，都为奇数，且递增
                for (int j = 0; j < lists.get(i).size(); j++) {
                    int temp = lists.get(i).get(j);
                    if(temp % 2==0){
                        return false;
                    }
                    if(j != 0 && lists.get(i).get(j) <= lists.get(i).get(j-1)){
                        return false;
                    }
                }
            }else {
                //奇数层，都为偶数，且递减
                for (int j = 0; j < lists.get(i).size(); j++) {
                    int temp = lists.get(i).get(j);
                    if(temp % 2==1){
                        return false;
                    }
                    if(j != 0 && lists.get(i).get(j) >= lists.get(i).get(j-1)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public void hh(TreeNode root, int level){
        if(root == null){
            return;
        }
        if(lists.size() <= level){
            lists.add(new ArrayList<>());
        }
        lists.get(level).add(root.val);
        hh(root.left, level + 1);
        hh(root.right, level + 1);
    }



    //第二种方式，用队列广度遍历，并在遍历的过程中判断是否是奇偶树
    public boolean isEvenOddTree2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            int last = level%2==1?Integer.MAX_VALUE:Integer.MIN_VALUE;
            //同一层级
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                if(level%2==1){
                    //奇数层级，都是偶数并且递减
                    if(temp.val%2==1){
                        return false;
                    }
                    if(temp.val >= last){
                        return false;
                    }
                    last=temp.val;
                }else {
                    //偶数层级，都是奇数并且递增
                    if(temp.val%2==0){
                        return false;
                    }
                    if(temp.val <= last){
                        return false;
                    }
                    last=temp.val;
                }
                if(temp.left!=null){
                    queue.add(temp.left);
                }
                if(temp.right!=null){
                    queue.add(temp.right);
                }
            }
            //层级加一
            level++;
        }
        return true;
    }

}
