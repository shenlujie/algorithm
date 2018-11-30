package 最短路径问题;

import 最小生成树.Edge;
import 最小生成树.WeightGraph;
import 最小生成树.最小生成树算法.IndexMinHeap;

import java.util.Stack;
import java.util.Vector;

/**
 * @Description: 单源最短路径问题解法之一
 * 1、图可以是有向图或无向图
 * 2、不可以存在负权值
 * @create: 2018/11/29
 * @Author: SLJ
 */
public class Dijkstra<Weight extends Number & Comparable<Weight>> {

    private int s;//设置出发节点
    private WeightGraph graph;//设置连通图
    private boolean[] marked;//标记每个节点是否被访问过（被访问的意思就是是否加入最短路径生成树）
    private Number[] distTo;//设置从出发点s到各个节点的当前最短路径权值
    private Edge<Weight>[] from;//设置各个节点的最短路径所对应的那条边，根据这个边可恢复整个路径的所有边
    private IndexMinHeap<Weight> indexMinHeap;//最小索引堆用来存储当前访问节点的所有邻边的最短路径权值，并能找出其中的最小值

    public Dijkstra(int s, WeightGraph graph) {
        if (s < 0 || s >= graph.V()){
            throw new IllegalArgumentException("参数s不合法");
        }
        this.s = s;
        this.graph = graph;
        marked = new boolean[graph.V()];
        distTo = new Number[graph.V()];
        from = new Edge[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            marked[i] = false;
            distTo[i] = 0.0;
            from[i] = null;
        }
        indexMinHeap = new IndexMinHeap<>(graph.V());

        /*dijkstra算法过程*/
        //对起始节点s进行初始化
        marked[s] = true;
        distTo[s] = 0.0;
        from[s] = new Edge(s, s, 0.0);
        indexMinHeap.add(s, (Weight) distTo[s]);

        while (!indexMinHeap.isEmpty()){
            int v = indexMinHeap.extractMinIndex();
            marked[v] = true;
            for (Object e:graph.adj(v)) {
                Edge<Weight> edge = (Edge<Weight>) e;
                int w = edge.other(v);
                if (!marked[w]){
                    //松弛操作
                    if (from[w] == null || distTo[v].doubleValue() + edge.weight().doubleValue() < distTo[w].doubleValue()){
                        distTo[w] = distTo[v].doubleValue() + edge.weight().doubleValue();
                        from[w] = edge;
                        if (indexMinHeap.contain(w)){
                            indexMinHeap.change(w, (Weight) distTo[w]);
                        }else {
                            indexMinHeap.add(w, (Weight)distTo[w]);
                        }
                    }
                }
            }
        }
    }

    //查看一个节点到出发点s的最短路径权值
    public Number shortestPathTo(int w){
        if (w < 0 || w >= graph.V() || !hasPathTo(w)){
            throw new IllegalArgumentException("参数w不合法");
        }

        return distTo[w];
    }

    //判断一个节点是否和s之间存在路径
    public boolean hasPathTo(int w){
        if (w < 0 || w >= graph.V()){
            throw new IllegalArgumentException("参数w不合法");
        }

        return marked[w];
    }

    //将一个节点到出发点s的最短路径上所有边存到Vector中返回
    public Vector<Edge<Weight>> shortestPath(int w){
        if (w < 0 || w >= graph.V() || !hasPathTo(w)){
            throw new IllegalArgumentException("参数w不合法");
        }

        //将from[w]的边倒序推进栈中，直到该边的左节点变为出发点s，再将该边推进栈
        Stack<Edge<Weight>> stack = new Stack<>();
        Edge<Weight> edge = from[w];
        while (edge.v() != s){

            stack.push(edge);
            w = edge.v();
            edge = from[w];
        }
        stack.push(edge);

        //将栈中的边依次去除添加到vector中并返回
        Vector<Edge<Weight>> vector = new Vector<>();
        while (!stack.empty()){
            vector.add(stack.pop());
        }

        return vector;
    }

    //将s到w的最短路径打印输出
    public void showPath(int w){
        if (w < 0 || w >= graph.V() || !hasPathTo(w)){
            throw new IllegalArgumentException("参数w不合法");
        }

        Vector<Edge<Weight>> path = shortestPath(w);
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i).v() + "->");
            if (i == path.size() - 1){
                System.out.print(path.get(i).w());
            }
        }
        System.out.println();
    }

}
