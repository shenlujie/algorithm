package 选择排序;

import 随机生成算法测试用例.SortTestHelper;

/**
*@Description: 将第一个数与其后的所有数进行比较，比他小的就和他交换，所有数比较完之后第一个数就是最小的。
 *             将第二个数与其后的所有数行比较，比他小的就和他交换，所有数比较完之后第二个数就是第二小的。
*@create: 2018/10/26
*@Author: SLJ
*/
public class SelectionSort {

    public static void main(String[] args) {
        int n = 10000;
        Integer[] arr = SortTestHelper.generateRandomArray(n,0 ,n);
        SortTestHelper.testSort(SelectionSort.class, "selectSort", arr);
        System.out.println("排序是否成功 " + SortTestHelper.isSorted(arr));
        SortTestHelper.printArray(arr,arr.length);
    }

    public static void selectSort(Comparable[] a){
        int n = a.length;
        for (int i = 0; i < n; i++) {
            //寻找[i,n)中最小的值minNum
            for (int j = i+1; j < n; j++) {
                if (a[j].compareTo(a[i]) < 0){
                    swap(a,i ,j );
                }
            }
        }
    }

    private static void swap(Object[] arr,int i,int j){
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
