package 最小生成树.最小生成树算法;

import 最小生成树.Edge;
import 最小生成树.WeightGraph;

import java.util.Vector;

/**
 * @Description:
 * 最小生成树算法定理（横切法）：将联通图以任意方式横切为两部分，这两部分之间的横切边中权值最小的边一定属于最小生成树中的边
 * 未优化的最小生成树算法Prim,时间复杂度为O（ElogE），E是边数
 * 优化后的最小生成树算法Prim,时间复杂度为O（ElogV），V是节点数
 * @create: 2018/11/27
 * @Author: SLJ
 */
public class PrimMST<Weight extends Number & Comparable<Weight>> {

    private WeightGraph<Weight> graph;//带权图
    private IndexMinHeap<Weight> indexMinHeap;//利用最小堆找出横切边中权重最小的那条
    private Edge<Weight>[] toEdge;//访问的节点所对应的边
    private boolean[] marked;//标记数组，是否被划分到最小生成树区域
    private Vector<Edge<Weight>> mst;//最小生成树所有边的集合
    private Number mstWeight;//最小生成树的权重

    public PrimMST(WeightGraph<Weight> graph) {
        this.graph = graph;
        indexMinHeap = new IndexMinHeap<>(graph.V());
        toEdge = new Edge[graph.V()];
        marked = new boolean[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            marked[i] = false;
            toEdge[i] = null;
        }
        mst = new Vector<>();


        visit(0);//从0节点开始访问
        while (!indexMinHeap.isEmpty()){
            int v = indexMinHeap.extractMinIndex();
            if (toEdge[v] == null){
                throw new IllegalArgumentException("含有v节点的边为null");
            }
            mstEdges().add(toEdge[v]);//否则的话就满足最小生成树边的性质，添加到最小生成树中
            visit(v);//继续访问该节点
        }
        mstWeight = mst.firstElement().weight();
        for (Edge<Weight> e:mst) {
            mstWeight = mstWeight.doubleValue() + e.weight().doubleValue();
        }
    }

    public Vector<Edge<Weight>> mstEdges(){
        return mst;
    }

    public Number result(){
        return mstWeight;
    }

    private void visit(int v){
        if (v < 0 || v >= marked.length){
            throw new IllegalArgumentException("参数v不合法");
        }
        marked[v] = true;//将该节点标记为已访问
        for (Edge<Weight> i:graph.adj(v)) {//遍历该节点相连的所有边
            int w = i.other(v);//v的相邻边所对应的另一个节点
            if (!marked[w]){//如果这条边的另外一个节点w没有被访问过
                if (toEdge[w] == null){//如果该节点从来没有被考虑过，直接添加到索引堆中
                    indexMinHeap.add(w, i.weight());
                    toEdge[w] = i;
                }else if (i.weight().compareTo(toEdge[w].weight()) < 0 ){//如果曾经考虑过并且新的边的权值小于之前考虑的那条边的权值就更新
                    indexMinHeap.change(w, i.weight());
                    toEdge[w] = i;
                }
            }
        }

    }
}
