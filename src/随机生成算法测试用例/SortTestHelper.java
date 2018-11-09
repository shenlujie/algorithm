package 随机生成算法测试用例;

import java.lang.reflect.Method;

/**
 * @Description:
 * @create: 2018/11/9
 * @Author: SLJ
 */
public class SortTestHelper {

    private SortTestHelper(){}

    public  static Integer[] generateRandomArray(int n,int l,int r){
        Integer []arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int)(Math.random()*(r - l + 1) + l);
        }
        return arr;
    }

    public static boolean isSorted(Comparable[] arr){
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(arr[i-1]) < 0){
                return false;
            }
        }
        return true;
    }

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
}
