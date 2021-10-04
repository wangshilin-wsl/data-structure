package sort;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = { 5,2,4,9,7,1,321,323,43,43,41,132};
        sort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i+"  ");
        }
    }
    public static void sort(int arr[], int low, int high) {
        int l = low;
        int h = high;
        int k = arr[low];
        while (l < h) {
                // 从后往前比较
                while (l < h && arr[h] >= k) { // 如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
                    h--;// h=6
                }
            if (l < h) {
                int temp = arr[h];
                arr[h] = arr[l];
                arr[l] = temp;
            }
            // 从前往后比较
            while (l < h && arr[l] <= k) { // 如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                l++;
            }
            if (l < h) {
                int temp = arr[h];
                arr[h] = arr[l];
                arr[l] = temp;
            }
            // 此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
        }
      //  System.out.println(l+"   "+h);
        // 递归
        if (l > low)//先判断l>low再次经行左边排序
            sort(arr, low, l - 1);// 左边序列。第一个索引位置到关键值索引-1
        if (h < high)//左边依次排序执行完递归后，弹栈进行右边排序
            sort(arr, h + 1, high);// 右边序列。从关键值索引+1到最后一个
    }
}
