package 最小生成树;

/**
 * @Description: 带权值的边 类
 * @create: 2018/11/27
 * @Author: SLJ
 */
public class Edge<Weight extends Number & Comparable> implements Comparable<Edge<Weight>> {
    private int a,b;
    private Weight weight;

    public Edge(int a, int b, Weight weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }

    public Edge(){}

    public int v(){
        return a;
    }

    public int w(){
        return b;
    }

    public Weight weight(){
        return weight;
    }

    public int other(int v){
        if (v != a && v != b){
            throw new IllegalArgumentException("参数v不合法");
        }
        return v == a ? b : a;
    }

    @Override
    public int compareTo(Edge<Weight> o) {
        if (weight.compareTo(o.weight()) < 0){
            return -1;
        }else if (weight.compareTo(o.weight()) > 0){
            return +1;
        }else {
            return 0;
        }
    }

    @Override
    public String toString(){
        return a + "-" + b + ":" + weight;
    }
}
