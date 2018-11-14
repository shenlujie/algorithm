package 图论;

import java.util.Vector;

/**
 * @Description: 稀疏图（就是边数较少的图但不是绝对概念，使用邻接表实现）
 * @create: 2018/11/13
 * @Author: SLJ
 */
public class SparseGraph implements Graph{
    private int n;//顶点个数
    private int m;//边的条数
    private boolean directed;//是否为有向图
    private Vector<Integer>[] g;//邻接表

    public SparseGraph(int n, boolean directed) {
        this.n = n;
        this.directed = directed;
        m = 0;
        g = new Vector[n];
        for (int i = 0; i < g.length; i++) {
            g[i] = new Vector<>();
        }
    }

    //返回顶点个数
    public int V(){return n;}

    //返回边的条数
    public int E(){return m;}

    //增加一条v -- w的边
    public void addEdge(int v,int w){
        if (v < 0 || v >= n){
            throw new IllegalArgumentException("参数v 违法");
        }
        if ( w < 0 || w >= n){
            throw new IllegalArgumentException("参数w 违法");
        }
        g[v].add(w);
        if (v != w && !directed){
            g[w].add(v);
        }
        m ++;
    }

    //判断图中是否存在v--w的一条边
    public boolean hasEdge(int v,int w){
        if (v < 0 || v >= n){
            throw new IllegalArgumentException("参数v 违法");
        }
        if ( w < 0 || w >= n){
            throw new IllegalArgumentException("参数v 违法");
        }

        for (int i = 0; i < g[v].size(); i++) {
            if (g[v].elementAt(i) == w){
                return true;
            }
        }
        return false;
    }

    //打印输出这个图(邻接表)
    public void show(){
        for( int i = 0 ; i < n ; i ++ ){
            System.out.print("vertex " + i + ":\t");
            for( int j = 0 ; j < g[i].size() ; j ++ )
                System.out.print(g[i].elementAt(j) + "\t");
            System.out.println();
        }
    }

    //返回图中一个顶点的所有邻边
    // 由于java使用引用机制，返回一个Vector不会带来额外开销
    public Iterable<Integer> adj(int v){
        if (v < 0 || v >= n){
            throw new IllegalArgumentException("参数v 违法");
        }

        return g[v];
    }
}
