package 图论;

import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;

/**
 * @Description: 利用广度优先遍历来求图的最小路径
 * @create: 2018/11/26
 * @Author: SLJ
 */
public class ShortestPath {
    private Graph graph;
    private int s;//出发的原点
    private boolean[] isVisited;//记录每个节点是否被访问过
    private int[] from;//记录每个节点的前一个节点
    private int[] order;//记录每个节点到原点的距离

    public ShortestPath(Graph graph, int s) {
        this.s = s;
        this.graph = graph;
        isVisited = new boolean[graph.V()];
        from = new int[graph.V()];
        order = new int[graph.V()];

        for (int i = 0; i < graph.V(); i++) {
            isVisited[i] = false;//初始化都没被访问过
            from[i] = -1;
            order[i] = -1;
        }

        //广度优先遍历求最短路径,和树的层序遍历一样
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(s);
        isVisited[s] = true;
        order[s] = 0;
        while (!queue.isEmpty()){
            int p = queue.remove();

            //对p的所有相邻节点进行同样操作
            for (int i:graph.adj(p)) {
                if (!isVisited[i]){
                    queue.add(i);
                    isVisited[i] = true;
                    from[i] = p;
                    order[i] = order[p] + 1;
                }
            }
        }
    }

    public boolean hasPath(int w){
        if ( w < 0 || w >= graph.V()){
            throw new IllegalArgumentException("参数w 违法");
        }

        return isVisited[w];
    }

    public void path(int w, Vector<Integer> vector){
        if ( w < 0 || w >= graph.V()){
            throw new IllegalArgumentException("参数w 违法");
        }
        Stack<Integer> stack = new Stack<>();
        int p = w;
        while (p != -1){
            stack.push(p);
            p = from[p];
        }

        vector.clear();
        while (!stack.isEmpty()){
            vector.add(stack.pop());
        }

    }

    public void showPath(int w){
        if ( w < 0 || w >= graph.V()){
            throw new IllegalArgumentException("参数w 违法");
        }
        Vector<Integer> vector = new Vector<>();
        path(w, vector);
        for (int i = 0; i < vector.size(); i++) {
            System.out.print(vector.get(i));
            if (i == vector.size() - 1){
                System.out.println();
            }else {
                System.out.print("-->");
            }
        }

    }

}
