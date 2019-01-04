package 归并排序;

import 希尔排序.ShellSort;
import 随机生成算法测试用例.SortTestHelper;

/**
 * @Description: 递归实现归并排序并优化
 * @create: 2018/11/11
 * @Author: SLJ
 */
public class MergeSort {

    /**
     * 启用归并排序的最小容量
     */
    private static final int MIN_SIZE = 15;

    /**
     * 归并排序
     *
     * @param arr 用户输入数组
     */
    public static void mergeSort(Comparable[] arr){
        int n = arr.length;
        mergeSort(arr,0,n-1);
    }

    /**
     * 递归实现的自顶向下归并排序，先分割，再归并
     *
     * @param arr 要排序的数组
     * @param l 左指针
     * @param r 右指针
     */
    private static void mergeSort(Comparable[] arr,int l,int r){
        /*if (l >= r){
            return;
        }*/
        //优化为下面这步，当r-l <= 15时，采用插入排序
        if (r - l <= MIN_SIZE){
            for (int i = l+1; i <= r ; i++) {

                Comparable e = arr[i];
                int j;
                for (j = i; j > l; j --) {
                    if (arr[j-1].compareTo(e) > 0){
                        arr[j] = arr[j-1];
                    }else {
                        break;
                    }
                }
                arr[j] = e;
            }
            return;
        }

        // （l+r）/2可能整数溢出
        int mid = l + (r-l)/2;
        mergeSort(arr, l,mid );
        mergeSort(arr,mid+1 ,r );
        //merge(arr,l,mid,r); 优化为下面这步
        if (arr[mid].compareTo(arr[mid+1]) > 0){
            merge(arr,l,mid,r);
        }
    }

    /**
     * 归并操作
     *
     * @param arr 要归并的数组
     * @param l 左指针
     * @param mid 中指针
     * @param r 右指针
     */
    private static void merge(Comparable[] arr,int l,int mid,int r){
        //将arr复制一个副本
        Comparable[] aux  = new Comparable[r-l+1];
        for (int i = l; i <= r; i++) {
            aux[i-l] = arr[i];
        }

        //比较副本中的两边i、j值的大小决定arr中k位置的值
        int i = l;
        int j = mid+1;

        //k作索引来遍历arr
        for (int k = l; k <= r; k++) {
            //如果i越界，剩下的arr的空位全由aux[j - l]赋值
            if (i > mid){
                arr[k] = aux[j-l];
                j ++;
            //如果j越界，剩下的arr的空位全由aux[i - l]赋值
            }else if (j > r){
                arr[k] = aux[i-l];
                i ++;
            }else if (aux[i-l].compareTo(aux[j-l]) < 0){
                arr[k] = aux[i-l];
                i ++;
            }else {
                arr[k] = aux[j-l];
                j ++;
            }
        }
    }

    public static void main(String[] args) {
        int n = 500000;
        Integer[] arr = SortTestHelper.generateRandomArray(n,0 ,n );
        Integer[] nArr = SortTestHelper.copyArray(arr);
        SortTestHelper.testSort(ShellSort.class, "shellSort", arr);
        SortTestHelper.testSort(MergeSort.class, "mergeSort", nArr);
        System.out.println(SortTestHelper.isSorted(arr));
        System.out.println(SortTestHelper.isSorted(nArr));
    }
}
