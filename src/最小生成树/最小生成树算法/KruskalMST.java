package 最小生成树.最小生成树算法;

import 最小生成树.Edge;
import 最小生成树.WeightGraph;

import java.util.Vector;

/**
 * @Description: 最小生成树算法Kruskal规则：
 * 1、将图中所有边进行排序
 * 2、依次去除权值最小的边
 * 3、如果添加这条边使得最小生成树没有形成环，那么这条边就一定属于最小生成树
 * 4、如果添加这条边使得最小生成树形成了环，那么这条边就不符合规则，舍弃
 * @create: 2018/11/29
 * @Author: SLJ
 */
public class KruskalMST<Weight extends Number & Comparable<Weight>> {
    private WeightGraph<Weight> graph;
    private MinHeap<Edge<Weight>> minHeap;
    private UnionFind unionFind;
    private Vector<Edge<Weight>> mst;
    private Number mstWeight;

    public KruskalMST(WeightGraph<Weight> graph) {
        this.graph = graph;
        minHeap = new MinHeap<>(graph.E());
        unionFind = new UnionFind(graph.V());
        mst = new Vector<>();
        mstWeight = 0;

        /*Kruskal算法实现过程*/

        //将联通图中所有边全部添加到minHeap中（由于是无向图，避免每条边被添加两次）
        for (int i = 0; i < graph.V(); i++) {
            for (Edge<Weight> e:graph.adj(i)) {
                if (i < e.other(i)){
                    minHeap.insert(e);
                }
            }
        }

        //当minHeap为空或者最小生成树的size大于等于连通图节点数-1时就跳出循环
        while (!minHeap.isEmpty() && mst.size() < graph.V()-1){
            Edge<Weight> edge = minHeap.extractMin();
            if (unionFind.isConnected(edge.v(), edge.w())){
                continue;//如果这条边的两个端点已经联通了，就不必考虑了，因为再联通的话就会形成环
            }
            mstEdges().add(edge);
            unionFind.unionElements(edge.v(), edge.w());//将这两个端点联通
        }

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

}
