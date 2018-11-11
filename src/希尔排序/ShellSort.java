package 希尔排序;

import 插入排序.InsertionSort;
import 随机生成算法测试用例.SortTestHelper;

/**
 * @Description:
 * 设置默认步长为长度的1/2，每次步长为原步长的1/2
 * 对分组内的成员进行插入排序
 * @create: 2018/11/11
 * @Author: SLJ
 */
public class ShellSort {

    private ShellSort(){}

    public static void shellSort(Comparable[] arr){
        int n = arr.length;
        int step = n/3;//设置步长为数组长度的1/3
        while (step >= 1){
            for (int i = 0; i < step; i++) {//把数组分为step个组
                for (int j = i+step; j < n; j += step) {
                    /*对分组内的成员采用插入排序*/
                    Comparable e = arr[j];
                    int k;
                    for (k = j-step; k >= 0; k = k-step) {
                        if (arr[k].compareTo(e) > 0){
                            arr[k+step] = arr[k];
                        }else {
                            break;
                        }
                    }
                    arr[k+step] = e;
                }
            }
            step = step/3;
        }
    }

    public static void main(String[] args) {
        int n = 500000;
        Integer[] arr = SortTestHelper.generateRandomArray(n,0 ,n );
//        Integer[] nArr = SortTestHelper.copyArray(arr);
        SortTestHelper.testSort(ShellSort.class, "shellSort", arr);
//        SortTestHelper.testSort(InsertionSort.class, "insertSortVersion2", nArr);
        /*SortTestHelper.printArray(arr,arr.length );
        SortTestHelper.printArray(nArr,nArr.length );*/
    }
}
