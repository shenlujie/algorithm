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
        Path path1 = new Path(sparseGraph, 0);
        path1.showPath(5);


        System.out.println();

        String testG1 = "B:\\algorithm\\src\\图论\\testG1.txt";
        DenseGraph denseGraph = new DenseGraph(13, false);
        new ReadGraph(denseGraph, testG1);
        denseGraph.show();
        Component component2 = new Component(denseGraph);
        System.out.println("稠密图的连通分量为：" + component2.count());
        System.out.println(component2.isConnected(0, 12));
        Path path2 = new Path(denseGraph, 0);
        path2.showPath(5);
    }
}
