package 插入排序;

import 选择排序.SelectionSort;
import 随机生成算法测试用例.SortTestHelper;

import java.util.Collections;

/**
 * @Description:
 * @create: 2018/11/10
 * @Author: SLJ
 */
public class InsertionSort {

    private InsertionSort() {}

    /**
    * 第一个元素看作一个数组，第二个元素和这个数组进行依次比较，小的交换位置
    * 第一个元素、第二个元素看作一个数组，第三个元素和和这个数组进行依次比较，小的交换位置。。
    * 依次执行
    */
    public static void insertSortVersion1(Comparable[] arr){
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j].compareTo(arr[j-1]) < 0){
                    swap(arr,j ,j-1 );
                }else{
                    break;
                }
            }
        }

    }

    /**
     * 将arr[i]复制一个副本e，j从i开始依次向前遍历，当arr[j-1]大于e，
     * 把arr[j-1]的元素赋值给arr[j]，j--，不满足就直接break掉
     * 当遍历完时，j的位置就是e要插入的位置
     * 优化：将第一版的交换操作改成赋值操作
     */
    public static void insertSortVersion2(Comparable[] arr){
        int n = arr.length;
        for (int i = 1; i < n; i++) {

            Comparable e = arr[i];//要比较元素的副本
            int j;//元素e应该插入的位置
            for (j = i; j > 0; j--) {
                if (arr[j-1].compareTo(e) > 0){
                    arr[j] = arr[j-1];
                }else{
                    break;
                }
            }
            arr[j] = e;
        }

    }

    private static void swap(Object[] arr,int i,int j){
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int n = 50000;
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        arr = SortTestHelper.generateNearlySortedArray(arr, 10);
        //Integer[] arr = SortTestHelper.generateRandomArray(n,0,n);
        Integer[] nArr = SortTestHelper.copyArray(arr);
        SortTestHelper.testSort(InsertionSort.class, "insertSortVersion2", arr);
        SortTestHelper.testSort(SelectionSort.class, "selectSort", nArr);
        /*System.out.println("排序是否成功:  " + SortTestHelper.isSorted(arr));
        SortTestHelper.printArray(arr, arr.length);*/
    }
}
