package 快速排序;

import 归并排序.MergeSort;
import 随机生成算法测试用例.SortTestHelper;

import java.util.Arrays;

/**
 * @Description:
 * 快排算法的效率的核心就在于能够使得初始比较的元素处于整个序列的中央位置
 * 当序列本身就接近顺序的情况下，选择第一个元素作为初始比较的元素会使得时间复杂度降为O（n^2），优化见下方
 * 当序列本身存在大量重复元素的情况下，即使随机选择初始比较的元素也会存在重复的元素要么都大于e,要么都小于e，
 * 使得时间复杂度降为O（n^2），优化见QuickSort2
 * @create: 2018/11/11
 * @Author: SLJ
 */
public class QuickSort {

    private QuickSort(){}

    public static void quickSort(Comparable[] arr){
        int n = arr.length;
        _quickSort(arr, 0,n-1 );

    }

    //对arr[l...r]部分进行快排
    private static void _quickSort(Comparable[] arr,int l,int r){
        /*if (l >= r){
            return;
        }*/
        //优化为下面这步，当r-l <= 15时，采用插入排序
        if (r - l <= 15){
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

        int p = _partition(arr,l,r);
        _quickSort(arr,l , p-1);
        _quickSort(arr, p+1, r);
    }

    private static int _partition(Comparable[] arr,int l,int r){
        swap(arr,(int) Math.random()*(r - l)+l,l);
        Comparable e = arr[l];
        int j = l;
        for (int i = l+1; i <= r; i++) {
            if (arr[i].compareTo(e) <= 0){
                swap(arr, j+1, i);
                j ++;
            }
        }
        swap(arr, l, j);
        return j;
    }

    private static void swap(Object[] arr,int i,int j){
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] arr = SortTestHelper.generateRandomArray(n,0 ,10 );
        Arrays.sort(arr);
        Integer[] brr = SortTestHelper.generateNearlySortedArray(arr, 2);
        Integer[] nArr = SortTestHelper.copyArray(brr);
        SortTestHelper.testSort(QuickSort.class, "quickSort",brr );
        SortTestHelper.testSort(MergeSort.class, "mergeSort",nArr );
        System.out.println(SortTestHelper.isSorted(arr));
        System.out.println(SortTestHelper.isSorted(nArr));
    }
}
