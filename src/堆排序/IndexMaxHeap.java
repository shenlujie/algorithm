package 堆排序;

import java.util.Random;

/**
 * @Description: 使用动态数组为底层数据结构来实现最大堆
 * @create: 2018/11/11
 * @Author: SLJ
 */
public class IndexMaxHeap<E extends Comparable<E>> {
    private E[] data;
    private int[] indexes;
    private int count;
    private int capacity;

    public IndexMaxHeap(int capacity) {
        data = (E[]) new Comparable[capacity];
        indexes = new int[capacity];
        count = 0;
        this.capacity = capacity;
    }

    //进行heapify操作的构造函数
    public IndexMaxHeap(E[] arr){
        capacity = arr.length;
        data = arr;
        indexes = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            indexes[i] = i;
        }
        count = arr.length;
        for (int i = parent(arr.length-1); i >= 0 ; i--) {
            siftDown(i);
        }

    }

    public int getSize(){
        return count;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    private int parent(int index){
        if (index == 0){
            throw new IllegalArgumentException("index cannot be 0");
        }
        return (index-1)/2;
    }

    private int leftChild(int index){
        return (index*2)+1;
    }

    private int rightChild(int index){
        return (index*2)+2;
    }

    //用户调用的添加元素的操纵
    public void add(int index,E e){
        if (count >= capacity){
            throw new IllegalArgumentException("IndexMaxHeap has been full");
        }
        if (index < 0 || index >= capacity){
            throw new IllegalArgumentException("参数index违法");
        }
        data[index] = e;
        indexes[count] = index;
        count ++;
        siftUp(count - 1);
    }

    private void siftUp(int index){
        while (index > 0 && data[(indexes[parent(index)])].compareTo(data[(indexes[index])]) < 0){
            swap(parent(index),index);
            index = parent(index);
        }
    }

    //找出堆中最大元素并返回
    public E findMax(){
        if (isEmpty()){
            throw new IllegalArgumentException("cannot find 最大值 when data is empty");
        }
        return data[(indexes[0])];
    }

    //用户调用去除最大元素
    public E extractMax(){
        E e = findMax();
        swap(0,count - 1);
        count --;
        siftDown(0);

        return e;
    }

    private void siftDown(int index){

        while (leftChild(index) < count){
            int j = leftChild(index);
            if (j + 1 < count && data[(indexes[j+1])].compareTo(data[(indexes[j])]) > 0){
                j = rightChild(index);//把左子树和右子树中最大的值都赋给左子树，这样左子树的索引值代表的就是两个子树中最大值的索引
            }
            if (data[(indexes[j])].compareTo(data[(indexes[index])]) <= 0){//当前节点的值比两个子树的值都大的时候就已经完成了，跳出循环
                break;
            }
            swap(index,j);
            index = j;
        }
    }

    //交换indexs中的两个索引指向的元素
    private void swap(int a,int b){
        int item = indexes[a];
        indexes[a] = indexes[b];
        indexes[b] = item;
    }

    public static void main(String[] args) {
        IndexMaxHeap<Integer> maxHeap = new IndexMaxHeap<>(100);
        Random random = new Random();
        int n = 100;
        for (int i = 0; i < n; i++) {
            maxHeap.add(i,random.nextInt(Integer.MAX_VALUE));
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
        }

        for (int i = 1; i < n; i++) {
            if (arr[i-1] < arr[i]){
                throw new IllegalArgumentException("IndexMaxHeapTest failed");
            }
        }
        System.out.println("done");
    }


}
