package 图论;

/**
 * @Description:
 * @create: 2018/11/14
 * @Author: SLJ
 */
public class Main {

    public static void main(String[] args) {
        String testG1 = "B:\\algorithm\\src\\图论\\testG1.txt";
        SparseGraph sparseGraph = new SparseGraph(13, false);
        new ReadGraph(sparseGraph, testG1);
        sparseGraph.show();

        System.out.println();

        DenseGraph denseGraph = new DenseGraph(13, false);
        new ReadGraph(denseGraph, testG1);
        denseGraph.show();
    }
}
