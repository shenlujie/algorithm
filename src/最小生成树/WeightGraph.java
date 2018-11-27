package 最小生成树;

public interface WeightGraph<Weight extends Number & Comparable> {
    public int V();

    public int E();

    public void addEdge(Edge<Weight> e);

    boolean hasEdge( int v , int w );

    void show();

    Iterable<Edge<Weight>> adj(int v);
}
