package 最小生成树;

import java.util.Vector;

/**
 * @Description: 带权值的稠密图
 * @create: 2018/11/12
 * @Author: SLJ
 */
public class DenseWeightGraph<Weight extends Number & Comparable> implements WeightGraph{

    private int n;//顶点个数
    private int m;//边的条数
    private boolean directed;//是否为有向图
    private Edge<Weight>[][] g;//边信息的邻接矩阵

    public DenseWeightGraph(int n, boolean directed) {
        assert n>0;
        this.n = n;
        m = 0;
        this.directed = directed;
        g = new Edge[n][n];
        for (int i = 0; i < n; i++) {
            for (int i1 = 0; i1 < n; i1++) {
                g[i][i1] = null;
            }
        }
    }

    public int V(){return n;}

    public int E(){return m;}

    @Override
    public void addEdge(Edge e) {
        if (e.v() < 0 || e.v() >= n){
            throw new IllegalArgumentException("参数v 违法");
        }
        if ( e.w() < 0 || e.w() >= n){
            throw new IllegalArgumentException("参数v 违法");
        }
        if (hasEdge(e.v(),e.w())){
            return;
        }
        g[e.v()][e.w()] = e;
        if (!directed){
            g[e.w()][e.v()] = e;
        }
        m ++;
    }

    @Override
    public boolean hasEdge(int v, int w){
        if (v < 0 || v >= n){
            throw new IllegalArgumentException("参数v 违法");
        }
        if ( w < 0 || w >= n){
            throw new IllegalArgumentException("参数v 违法");
        }

        return g[v][w] != null;
    }

    //打印输出这个图(邻接表)
    @Override
    public void show(){
        for( int i = 0 ; i < n ; i ++ ){
            for( int j = 0 ; j < n ; j ++ )
                if (g[i][j] != null){
                    System.out.print(g[i][j].weight() + "\t\t");
                }else {
                    System.out.print("n\t\t");
                }

            System.out.println();
        }
    }

    //返回图中一个顶点的所有邻边
    // 由于java使用引用机制，返回一个Vector不会带来额外开销
    @Override
    public Iterable<Edge<Weight>> adj(int v){
        if (v < 0 || v >= n){
            throw new IllegalArgumentException("参数v 违法");
        }

        Vector<Edge<Weight>> vector = new Vector<>();
        for (int i = 0; i < n; i++) {
            if (g[v][i] != null){
                vector.add(g[v][i]);
            }
        }
        return vector;
    }
}
