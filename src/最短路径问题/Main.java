package 最短路径问题;

import 最小生成树.ReadWeightGraph;
import 最小生成树.SparseWeightGraph;
import 最小生成树.WeightGraph;

/**
 * @Description:
 * @create: 2018/11/29
 * @Author: SLJ
 */
public class Main {
    public static void main(String[] args) {
        WeightGraph<Double> graph = new SparseWeightGraph<>(5,true);
        new ReadWeightGraph(graph, "B:\\algorithm\\src\\最小生成树\\testG1");
        Dijkstra<Double> dijkstra = new Dijkstra<>(0, graph);
        dijkstra.showPath(4);

        WeightGraph<Double> weightGraph = new SparseWeightGraph<>(5,true);
        new ReadWeightGraph(weightGraph, "B:\\algorithm\\src\\最短路径问题\\testG2.txt");
        BellmanFord<Double> bellmanFord = new BellmanFord<>(0, weightGraph);
        if (bellmanFord.detectNegativeCycle()){
            System.out.println("图中存在负权环");
        }else {
            bellmanFord.showPath(3);
        }

    }
}
