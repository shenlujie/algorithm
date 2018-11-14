package 图论;

import java.util.Vector;

/**
 * @Description: 稠密图（就是接近完全图的图但不是绝对概念，使用邻接矩阵实现）
 * @create: 2018/11/12
 * @Author: SLJ
 */
public class DenseGraph implements Graph{

    private int n;//顶点个数
    private int m;//边的条数
    private boolean directed;//是否为有向图
    private boolean[][] g;//邻接矩阵

    public DenseGraph(int n,boolean directed) {
        assert n>0;
        this.n = n;
        m = 0;
        this.directed = directed;
        g = new boolean[n][n];
    }

    public int V(){return n;}

    public int E(){return m;}

    public void addEdge(int v,int w){
        if (v < 0 || v >= n){
            throw new IllegalArgumentException("参数v 违法");
        }
        if ( w < 0 || w >= n){
            throw new IllegalArgumentException("参数v 违法");
        }
        if (hasEdge(v,w )){
            return;
        }
        g[v][w] = true;
        if (!directed){
            g[w][v] = true;
        }
        m ++;

    }

    public boolean hasEdge(int v,int w){
        if (v < 0 || v >= n){
            throw new IllegalArgumentException("参数v 违法");
        }
        if ( w < 0 || w >= n){
            throw new IllegalArgumentException("参数v 违法");
        }

        return g[v][w];
    }

    //打印输出这个图(邻接表)
    public void show(){
        for( int i = 0 ; i < n ; i ++ ){
            for( int j = 0 ; j < n ; j ++ )
                if (g[i][j]){
                    System.out.print(1+"\t");
                }else {
                    System.out.print(0+"\t");
                }

            System.out.println();
        }
    }

    //返回图中一个顶点的所有邻边
    // 由于java使用引用机制，返回一个Vector不会带来额外开销
    public Iterable<Integer> adj(int v){
        if (v < 0 || v >= n){
            throw new IllegalArgumentException("参数v 违法");
        }

        Vector<Integer> vector = new Vector<>();
        for (int i = 0; i < n; i++) {
            if (g[v][i]){
                vector.add(i);
            }
        }
        return vector;
    }
}
