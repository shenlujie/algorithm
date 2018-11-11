package 归并排序;

import 希尔排序.ShellSort;
import 随机生成算法测试用例.SortTestHelper;

/**
 * @Description: 递归实现归并排序
 * @create: 2018/11/11
 * @Author: SLJ
 */
public class MergeSort {
    private MergeSort(){}

    public static void mergeSort(Comparable[] arr){
        int n = arr.length;
        _mergeSort(arr,0,n-1);
    }

    private static void _mergeSort(Comparable[] arr,int l,int r){
        if (l >= r){
            return;
        }

        int mid = l + (r-l)/2;// （l+r）/2可能整数溢出
        _mergeSort(arr, l,mid );
        _mergeSort(arr,mid+1 ,r );
        merge(arr,l,mid,r);
    }

    private static void merge(Comparable[] arr,int l,int mid,int r){
        Comparable[] aux  = new Comparable[r-l+1];//将arr复制一个副本
        for (int i = l; i <= r; i++) {
            aux[i-l] = arr[i];
        }

        //比较副本中的两边i、j值的大小决定arr中k位置的值
        int i = l;
        int j = mid+1;

        for (int k = l; k <= r; k++) {
            if (i > mid){
                arr[k] = aux[j-l];
                j ++;
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
        int n = 50000;
        Integer[] arr = SortTestHelper.generateRandomArray(n,0 ,n );
        Integer[] nArr = SortTestHelper.copyArray(arr);
        SortTestHelper.testSort(ShellSort.class, "shellSort", arr);
        SortTestHelper.testSort(MergeSort.class, "mergeSort", nArr);
        System.out.println(SortTestHelper.isSorted(arr));
        System.out.println(SortTestHelper.isSorted(nArr));
    }
}
