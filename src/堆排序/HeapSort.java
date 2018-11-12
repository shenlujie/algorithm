package 堆排序;

import 随机生成算法测试用例.SortTestHelper;

/**
 * @Description:
 * @create: 2018/11/11
 * @Author: SLJ
 */
public class HeapSort {

    private HeapSort(){}

    public static void heapSort1(Comparable[] arr){
        int n = arr.length;
        MaxHeap<Integer> heap = new MaxHeap<>();
        for (int i = 0; i < n; i++) {
            heap.add((Integer)arr[i]);
        }
        for (int i = n-1; i >= 0 ; i--) {
            arr[i] = heap.extractMax();
        }

    }

    public static void heapSort2(Comparable[] arr){
        int n = arr.length;
        MaxHeap<Integer> heap = new MaxHeap<>((Integer[]) arr);
        for (int i = n-1; i >= 0 ; i--) {
            arr[i] = heap.extractMax();
        }
    }

    public static void heapSort(Comparable[] arr){
        int n = arr.length;
        for (int i = (n-1-1)/2; i >= 0; i--) {
            _siftDown(arr,n,i);
        }
        for (int i = n-1; i >0 ; i--) {
            Comparable temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            _siftDown(arr,i,0);
        }
    }

    private static void _siftDown(Comparable[] arr,int n,int k){
        while (2*k+1 < n){
            int j = 2*k+1;
            if (j + 1 < n && arr[j+1].compareTo(arr[j]) > 0){
                j = 2*k+2;//把左子树和右子树中最大的值都赋给左子树，这样左子树的索引值代表的就是两个子树中最大值的索引
            }
            if (arr[j].compareTo(arr[k]) <= 0){//当前节点的值比两个子树的值都大的时候就已经完成了，跳出循环
                break;
            }
            Comparable temp = arr[k];
            arr[k] = arr[j];
            arr[j] = temp;
            k = j;
        }
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, n);
        Integer[] nArr = SortTestHelper.copyArray(arr);
        Integer[] brr = SortTestHelper.copyArray(arr);
        SortTestHelper.testSort(HeapSort.class,"heapSort1" ,arr );
        SortTestHelper.testSort(HeapSort.class, "heapSort2",nArr );
        SortTestHelper.testSort(HeapSort.class, "heapSort",brr );
        System.out.println(SortTestHelper.isSorted(arr));
        System.out.println(SortTestHelper.isSorted(nArr));
        System.out.println(SortTestHelper.isSorted(brr));
    }
}
