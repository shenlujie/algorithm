package 最小生成树.最小生成树算法;

import 最小生成树.Edge;
import 最小生成树.ReadWeightGraph;
import 最小生成树.SparseWeightGraph;
import 最小生成树.WeightGraph;

import java.util.Vector;

/**
 * @Description:
 * @create: 2018/11/27
 * @Author: SLJ
 */
public class Main {
    public static void main(String[] args) {
        WeightGraph<Double> graph = new SparseWeightGraph<>(5,false);
        new ReadWeightGraph(graph, "B:\\algorithm\\src\\最小生成树\\testG1");
        LazyPrimMST<Double> lazyPrimMST = new LazyPrimMST<>(graph);
        Vector<Edge<Double>> vector = lazyPrimMST.mstEdges();
        for (Edge<Double> edge:vector) {
            System.out.println(edge);
        }

        System.out.println();

        PrimMST<Double> primMST = new PrimMST<>(graph);
        Vector<Edge<Double>> vector1 = primMST.mstEdges();
        for (Edge<Double> edge:vector1) {
            System.out.println(edge);
        }
    }
}
