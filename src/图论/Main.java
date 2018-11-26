package 图论;

/**
 * @Description:
 * @create: 2018/11/14
 * @Author: SLJ
 */
public class Main {

    public static void main(String[] args) {
        String testG2 = "B:\\algorithm\\src\\图论\\testG2.txt";
        SparseGraph sparseGraph = new SparseGraph(6, false);
        new ReadGraph(sparseGraph, testG2);
        sparseGraph.show();
        Component component1 = new Component(sparseGraph);
        System.out.println("稀疏图的连通分量为：" + component1.count());
        System.out.println(component1.isConnected(3, 4));
        Path dfs1 = new Path(sparseGraph, 0);
        System.out.println("稀疏图的深度优先遍历：");
        dfs1.showPath(4);
        ShortestPath bfs1 = new ShortestPath(sparseGraph, 0);
        System.out.println("稀疏图的广度优先遍历：");
        bfs1.showPath(4);


        System.out.println();

        String testG1 = "B:\\algorithm\\src\\图论\\testG1.txt";
        DenseGraph denseGraph = new DenseGraph(13, false);
        new ReadGraph(denseGraph, testG1);
        denseGraph.show();
        Component component2 = new Component(denseGraph);
        System.out.println("稠密图的连通分量为：" + component2.count());
        System.out.println(component2.isConnected(0, 12));
        Path dfs2 = new Path(denseGraph, 0);
        System.out.println("稠密图的深度优先遍历：");
        dfs2.showPath(5);
        ShortestPath bfs2 = new ShortestPath(denseGraph, 0);
        System.out.println("稠密图的广度优先遍历：");
        bfs2.showPath(5);
    }
}
