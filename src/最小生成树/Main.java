package 最小生成树;

import 图论.DenseGraph;

/**
 * @Description:
 * @create: 2018/11/27
 * @Author: SLJ
 */
public class Main {

    public static void main(String[] args) {
        WeightGraph weightGraph1 = new DenseWeightGraph(5, false);
        new ReadWeightGraph(weightGraph1,"B:\\algorithm\\src\\最小生成树\\testG1" );
        weightGraph1.show();

        WeightGraph weightGraph2 = new SparseWeightGraph(5, false);
        new ReadWeightGraph(weightGraph2, "B:\\algorithm\\src\\最小生成树\\testG1");
        weightGraph2.show();
    }
}
