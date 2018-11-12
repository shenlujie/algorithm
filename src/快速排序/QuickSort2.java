package 快速排序;

import 归并排序.MergeSort;
import 随机生成算法测试用例.SortTestHelper;

import java.util.Arrays;

/**
 * @Description: 当序列中存在大量重复元素时，将小于等于e的元素置于左侧，将大于等于e的元素置于右侧，这样能够使得
 * 重复元素可以分散到左右两侧，避免到同一侧降低性能。
 * 也就是所谓的双路快排
 * @create: 2018/11/11
 * @Author: SLJ
 */
public class QuickSort2 {

    private QuickSort2(){}

    public static void quickSort2(Comparable[] arr){
        int n = arr.length;
        _quickSort2(arr, 0,n-1 );

    }

    //对arr[l...r]部分进行快排
    private static void _quickSort2(Comparable[] arr,int l,int r){
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

        int p = _partition2(arr,l,r);
        _quickSort2(arr,l , p-1);
        _quickSort2(arr, p+1, r);
    }

    private static int _partition2(Comparable[] arr,int l,int r){
        swap(arr,(int) Math.random()*(r - l)+l,l);
        Comparable e = arr[l];
        int i = l+1;
        int j = r;
        while (true) {
            while (arr[i].compareTo(e) < 0 && i <= r){
                i ++;
            }
            while (arr[j].compareTo(e) > 0 && j >= l+1){
                j --;
            }
            if (i > j){
                break;
            }
            swap(arr, i, j);
            i ++;
            j --;
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
        SortTestHelper.testSort(QuickSort2.class, "quickSort2",brr );
        SortTestHelper.testSort(MergeSort.class, "mergeSort",nArr );
        System.out.println(SortTestHelper.isSorted(arr));
        System.out.println(SortTestHelper.isSorted(nArr));
    }
}
