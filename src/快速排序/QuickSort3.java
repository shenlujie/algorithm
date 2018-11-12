package 快速排序;

import 归并排序.MergeSort;
import 随机生成算法测试用例.SortTestHelper;

import java.util.Arrays;

/**
 * @Description:
 * 将快速排序分成三路，小于e,等于e,大于e
 * @create: 2018/11/11
 * @Author: SLJ
 */
public class QuickSort3 {

    private QuickSort3(){}

    public static void quickSort3(Comparable[] arr){
        int n = arr.length;
        _quickSort3(arr, 0,n-1 );

    }

    //对arr[l...r]部分进行快排
    private static void _quickSort3(Comparable[] arr,int l,int r){
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

        //partition操作
        swap(arr,(int) Math.random()*(r - l)+l,l);
        Comparable e = arr[l];
        int lt = l;
        int gt = r+1;
        int i = l+1;
        while (i < gt){
            if (arr[i].compareTo(e) < 0){
                swap(arr,i , lt+1);
                lt ++;
                i ++;
            }else if (arr[i].compareTo(e) > 0){
                swap(arr, i, gt-1);
                gt --;
            }else {
                i ++;
            } //arr[i] == e
        }
        swap(arr, l, lt);
        _quickSort3(arr, l, lt-1);
        _quickSort3(arr, gt, r);
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
        SortTestHelper.testSort(QuickSort3.class, "quickSort3",brr );
        SortTestHelper.testSort(MergeSort.class, "mergeSort",nArr );
        System.out.println(SortTestHelper.isSorted(arr));
        System.out.println(SortTestHelper.isSorted(nArr));
    }
}
