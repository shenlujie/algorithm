package 最小生成树.最小生成树算法;

import 最小生成树.Edge;
import 最小生成树.WeightGraph;

import java.util.Vector;

/**
 * @Description:
 * 最小生成树算法定理（横切法）：将联通图以任意方式横切为两部分，这两部分之间的横切边中权值最小的边一定属于最小生成树中的边
 * 未优化的最小生成树算法Prim,时间复杂度为O（ElogE），E是边数
 * @create: 2018/11/27
 * @Author: SLJ
 */
public class LazyPrimMST<Weight extends Number & Comparable> {

    private WeightGraph<Weight> graph;//带权图
    private MinHeap<Edge<Weight>> minHeap;//利用最小堆找出横切边中权重最小的那条
    private boolean[] marked;//标记数组，是否被划分到最小生成树区域
    private Vector<Edge<Weight>> mst;//最小生成树所有边的集合
    private Number mstWeight;//最小生成树的权重

    public LazyPrimMST(WeightGraph<Weight> graph) {
        this.graph = graph;
        minHeap = new MinHeap<>(graph.E());
        marked = new boolean[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            marked[i] = false;
        }
        mst = new Vector<>();


        visit(0);//从0节点开始访问
        while (!minHeap.isEmpty()){
            Edge<Weight> edge = minHeap.extractMin();
            if (marked[edge.v()] == marked[edge.w()]){
                continue;//如果这条边是访问过的节点内部的则掠过
            }
            mstEdges().add(edge);//否则的话就满足最小生成树边的性质，添加到最小生成树中
            //这条边的哪个节点没有被访问过就接着访问该节点
            if (!marked[edge.v()]){
                visit(edge.v());
            }else {
                visit(edge.w());
            }
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
            if (!marked[i.other(v)]){//如果这条边的另外一个节点w没有被访问过，则将其存入最小堆
                minHeap.insert(i);
            }
        }

    }
}
