package 最小生成树;

import java.util.Vector;

/**
 * @Description: 带权值的稀疏图
 * @create: 2018/11/13
 * @Author: SLJ
 */
public class SparseWeightGraph<Weight extends Number & Comparable> implements WeightGraph{
    private int n;//顶点个数
    private int m;//边的条数
    private boolean directed;//是否为有向图
    private Vector<Edge<Weight>>[] g;//邻接表

    public SparseWeightGraph(int n, boolean directed) {
        this.n = n;
        this.directed = directed;
        m = 0;
        g = new Vector[n];
        for (int i = 0; i < g.length; i++) {
            g[i] = new Vector<>();
        }
    }

    //返回顶点个数
    @Override
    public int V(){return n;}

    //返回边的条数
    @Override
    public int E(){return m;}

    //增加一条v -- w的边
    @Override
    public void addEdge(Edge e) {
        if (e.v() < 0 || e.v() >= n){
            throw new IllegalArgumentException("参数v 违法");
        }
        if ( e.w() < 0 || e.w() >= n){
            throw new IllegalArgumentException("参数w 违法");
        }
        g[e.v()].add(e);
        if (e.v() != e.w() && !directed){
            g[e.w()].add(new Edge(e.w(),e.v(),e.weight()));
        }
        m ++;
    }

    //判断图中是否存在v--w的一条边
    @Override
    public boolean hasEdge(int v,int w){
        if (v < 0 || v >= n){
            throw new IllegalArgumentException("参数v 违法");
        }
        if ( w < 0 || w >= n){
            throw new IllegalArgumentException("参数v 违法");
        }

        for (int i = 0; i < g[v].size(); i++) {
            if (g[v].elementAt(i).other(v) == w){
                return true;
            }
        }
        return false;
    }

    //打印输出这个图(邻接表)
    @Override
    public void show(){
        for( int i = 0 ; i < n ; i ++ ){
            System.out.print("vertex " + i + ":\t");
            for( int j = 0 ; j < g[i].size() ; j ++ )
                System.out.print( "to {" + g[i].elementAt(j).w() + "," + g[i].elementAt(j).weight() + "}\t");
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

        return g[v];
    }
}
