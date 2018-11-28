package 最小生成树.最小生成树算法;

/**
 * @Description: 最小堆
 * @create: 2018/11/27
 * @Author: SLJ
 */
public class MinHeap<Item extends Comparable> {

    private Item[] data;//底层数组
    private int count;//元素个数
    private int capacity;//树的容量

    public MinHeap(int capacity) {
        this.capacity = capacity;
        count = 0;
        data = (Item[]) new Comparable[capacity + 1];//底层数组从1开始存值
    }

    public MinHeap(Item[] items) {
        capacity = items.length;
        data = (Item[]) new Comparable[capacity + 1];
        for (int i = 0; i < capacity; i++) {
            data[i + 1] = items[i];
        }
        count = items.length;
        for (int i = count/2; i >= 1 ; i--) {//从最后一个叶子节点的父节点开始遍历
            shiftDown(i);
        }
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public int size(){
        return count;
    }

    public void insert(Item item){
        if (count + 1 > capacity){
            throw new IllegalArgumentException("最小堆已满，无法添加新元素");
        }
        data[count + 1] = item;//在堆的最后叶子空节点添加值
        count ++;
        shiftUp(count);
    }

    public Item extractMin(){
        Item min = data[1];//先获取根节点的值
        swap(1,count);//交换根节点和最后一个叶子节点
        count --;
        shiftDown(1);//进行下浮
        return min;
    }

    public Item getMin(){
        return data[1];
    }

    private void swap(int a,int b){
        Item item = data[a];
        data[a] = data[b];
        data[b] = item;
    }

    /*最小堆核心辅助函数*/
    private void shiftDown(int index){
        while (2 * index <= count){
            int j = 2 * index;
            if (j + 1 <= count && data[j + 1].compareTo(data[j]) < 0){
                j = j + 1;
            }
            if (data[index].compareTo(data[j]) < 0){
                break;
            }
            swap(index, j);
            index = j;
        }
    }

    private void shiftUp(int index){
        while (index > 1){

            if (data[index].compareTo(data[index/2]) < 0){
                swap(index, index/2);
            }

            index /= 2;
        }
    }

    public static void main(String[] args) {
        MinHeap<Integer> minHeap = new MinHeap<>(new Integer[]{3,5,8,9,6,4,7});
        while (!minHeap.isEmpty()){
            System.out.print(minHeap.extractMin() + "\t");
        }
    }
}
