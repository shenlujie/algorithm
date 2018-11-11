package 随机生成算法测试用例;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * @Description:
 * @create: 2018/11/9
 * @Author: SLJ
 */
public class SortTestHelper {

    private SortTestHelper(){}


    private static void swap(Object[] arr,int i,int j){
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    //生成一个长度为n，元素大小范围从l到r的随机数组
    public  static Integer[] generateRandomArray(int n,int l,int r){
        Integer []arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int)(Math.random()*(r - l + 1) + l);
        }
        return arr;
    }

    //生成一个指定交换次数的接近顺序的数组
    public static Integer[] generateNearlySortedArray(Integer[] arr,int n){
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            swap(arr,random.nextInt(arr.length) ,random.nextInt(arr.length) );
        }

        return arr;
    }

    //检测该数组是否排序成功
    public static boolean isSorted(Comparable[] arr){
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(arr[i-1]) < 0){
                return false;
            }
        }
        return true;
    }

    //将该数组遍历输出
    public static void printArray(Integer[] arr,int n){
        System.out.print("[");
        for (int i = 0; i < n; i++) {
            if (i < n-1){
                System.out.print(arr[i] + " ,");
            }else {
                System.out.print(arr[i]);
            }
        }
        System.out.println("]");
    }

    //计算该类中的排序方法执行指定的数组所消耗时间
    public static void testSort(Class cla,String sortName,Comparable[] arr){
        long startTime = System.nanoTime();

        try {
            Method method = cla.getMethod(sortName,new Class[]{Comparable[].class});
            Object[] obj = new Object[]{arr};
            method.invoke(null,obj);
        } catch (Exception e) {
            e.printStackTrace();
        }


        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.format("%s 共执行了 %.8f s\n",sortName,time);
    }

    //复制一个数组
    public static Integer[] copyArray(Integer[] arr){
        Integer[] nArr = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nArr[i] = arr[i];
        }
        return nArr;
    }


}
