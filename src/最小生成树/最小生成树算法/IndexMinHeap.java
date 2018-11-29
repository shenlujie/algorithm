package 最小生成树.最小生成树算法;

import java.util.Random;

/**
 * @Description: 实现最小索引堆
 * 优化：添加reverse[]，存储每个i值在indexes[]中的索引，这样在change函数中就可以使用O（1）的复杂度来找到data[e]的索引值在indexes中的位置了
 * 有两个性质：
 * indexes[reverse[i]] = i  很好理解，reverse中存的就是i在indexes中的索引，所以indexes[索引]肯定是i
 * reverse[indexes[i]] = i  很好理解，indexes[i]是一个值，reverse[值]表示这个值在indexes中的索引，那就是i啊
 * @create: 2018/11/11
 * @Author: SLJ
 */
public class IndexMinHeap<E extends Comparable<E>> {
    private E[] data;
    private int[] indexes;
    private int[] reverse;
    private int count;
    private int capacity;

    public IndexMinHeap(int capacity) {
        data = (E[]) new Comparable[capacity];
        indexes = new int[capacity];
        reverse = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            reverse[i] = -1;//初始化将所有元素标记为-1,当堆中不含有该值时就为-1
        }
        count = 0;
        this.capacity = capacity;
    }

    //进行heapify操作的构造函数
    public IndexMinHeap(E[] arr){
        capacity = arr.length;
        data = arr;
        indexes = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            indexes[i] = i;
        }
        reverse = new int[capacity];
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
        if (count > capacity){
            throw new IllegalArgumentException("IndexMaxHeap has been full");
        }
        if (index < 0 || index >= capacity){
            throw new IllegalArgumentException("参数index违法");
        }
        data[index] = e;
        indexes[count] = index;
        reverse[index] = count;
        count ++;
        siftUp(count - 1);
    }

    private void siftUp(int index){
        while (index > 0 && data[(indexes[parent(index)])].compareTo(data[(indexes[index])]) > 0){
            swap(parent(index),index);
            index = parent(index);
        }
    }

    //找出堆中最大元素并返回
    public E findMin(){
        if (isEmpty()){
            throw new IllegalArgumentException("cannot find 最大值 when data is empty");
        }
        return data[(indexes[0])];
    }

    //找出堆中最大元素索引并返回
    public int findMinIndex(){
        if (isEmpty()){
            throw new IllegalArgumentException("cannot find 最大值 when data is empty");
        }
        return indexes[0];
    }

    //用户调用去除最大元素
    public E extractMin(){
        E e = findMin();
        swap(0,count - 1);
        reverse[indexes[count - 1]] = 0;
        count --;
        siftDown(0);

        return e;
    }

    //用户调用除去最大索引
    public int extractMinIndex(){
        int minIndex = indexes[0];
        swap(0,count - 1);
        reverse[indexes[count - 1]] = 0;
        count --;
        siftDown(0);
        return minIndex;
    }

    //查看索引i所在位置是否存在元素
    private boolean contain(int index){
        if (index < 0 || index >= capacity){
            throw new IllegalArgumentException("参数index不合法");
        }
        return reverse[index] != -1;
    }

    private void siftDown(int index){

        while (leftChild(index) < count){
            int j = leftChild(index);
            if (j + 1 < count && data[(indexes[j+1])].compareTo(data[(indexes[j])]) < 0){
                j = rightChild(index);//把左子树和右子树中最大的值都赋给左子树，这样左子树的索引值代表的就是两个子树中最大值的索引
            }
            if (data[(indexes[j])].compareTo(data[(indexes[index])]) >= 0){//当前节点的值比两个子树的值都大的时候就已经完成了，跳出循环
                break;
            }
            swap(index,j);
            index = j;
        }
    }

    public E getItem(int index){
        if (!contain(index)){
            throw new IllegalArgumentException("参数index不合法");
        }
        return data[index];
    }

    public void change(int index,E e){
        if (!contain(index)){
            throw new IllegalArgumentException("参数index不合法");
        }
        data[index] = e;
        /*for (int i = 0; i < count; i++) {
            if (indexes[i] == index){
                siftUp(i);
                siftDown(i);
                return;
            }
        }*/
        //对这步原本O（n）复杂度进行优化
        siftUp(reverse[index]);
        siftDown(reverse[index]);
    }

    //交换indexs中的两个索引指向的元素
    private void swap(int a,int b){
        int item = indexes[a];
        indexes[a] = indexes[b];
        indexes[b] = item;

        reverse[indexes[a]] = a;
        reverse[indexes[b]] = b;
    }

    public static void main(String[] args) {
        /*Integer[] brr = new Integer[]{36,53,24,51,78,85,60};
        IndexMaxHeap<Integer> maxHeap = new IndexMaxHeap<>(brr);
        maxHeap.change(3,100 );
        int n = brr.length;*/
        IndexMinHeap<Integer> maxHeap = new IndexMinHeap<>(10);
        Random random = new Random();
        int n = 10;
        for (int i = 0; i < n; i++) {
            maxHeap.add(i,random.nextInt(100));
        }
        maxHeap.change(3, 105);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMin();
        }

        for (int i = 1; i < n; i++) {
            if (arr[i-1] > arr[i]){
                throw new IllegalArgumentException("IndexMaxHeapTest failed");
            }
        }
        System.out.println("done");
    }


}
