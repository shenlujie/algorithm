package 选择排序;
/**
*@Description: 将第一个数与其后的所有数进行比较，比他小的就和他交换，所有数比较完之后第一个数就是最小的。
 *             将第二个数与其后的所有数行比较，比他小的就和他交换，所有数比较完之后第二个数就是第二小的。
*@create: 2018/10/26
*@Author: SLJ
*/
public class Main {
    public static void main(String[] args) {
        //整型
        Integer[] a = {10,9,8,7,6,5,4,3,2,1};
        selectSort(a,a.length);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();

        //浮点型
        Float[] b = {10f,9f,8f,7f,6.4f,5.6f,4.5f,3.2f,2.1f,1.3f};
        selectSort(b,b.length);
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + " ");
        }
        System.out.println();

        //字符串
        String[] c = {"D","B","C","A"};
        selectSort(c,c.length);
        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i] + " ");
        }
        System.out.println();
    }

    //T extends Comparable说明T类型可利用Comparable进行比较
    public static <T extends Comparable> void selectSort(T[] a,int n){
        for (int i = 0; i < n; i++) {
            //寻找[i,n)中最小的值minNum
            for (int j = i+1; j < n; j++) {
                if (a[j].compareTo(a[i]) < 0){
                    T temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
}
