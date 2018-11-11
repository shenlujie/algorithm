package 冒泡排序;

import 随机生成算法测试用例.SortTestHelper;

/**
 * @Description:
 * @create: 2018/11/10
 * @Author: SLJ
 */
public class BubbleSort {
    private BubbleSort(){}

    private static void swap(Object[] arr,int i,int j){
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void bubbleSort(Comparable[] arr){
        int n = arr.length;
        for (int i = 0; i < (n - 1); i++) {
            for (int j = 0; j < (n - 1) - i; j++) {
                if (arr[j].compareTo(arr[j+1]) > 0){
                    swap(arr, j, j+1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 50000;
        Integer[] arr = SortTestHelper.generateRandomArray(n,0 ,n );
        SortTestHelper.testSort(BubbleSort.class,"bubbleSort" ,arr );
        System.out.println(SortTestHelper.isSorted(arr));
    }
}
