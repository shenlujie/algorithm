package 图论;

import java.util.Stack;
import java.util.Vector;

/**
 * @Description: 寻路，找到两节点之间的路径
 * @create: 2018/11/26
 * @Author: SLJ
 */
public class Path {
    private Graph graph;
    private int s;//出发的原点
    private boolean[] isVisited;//记录每个节点是否被访问过
    private int[] from;//记录每个节点的前一个节点

    public Path(Graph graph,int s) {
        this.s = s;
        this.graph = graph;
        isVisited = new boolean[graph.V()];
        from = new int[graph.V()];

        for (int i = 0; i < graph.V(); i++) {
            isVisited[i] = false;//初始化都没被访问过
            from[i] = -1;//所有id初始化为-1
        }

        //寻路算法
        dfs(s);
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

    private void dfs(int v){
        isVisited[v] = true;
        for (int i:graph.adj(v)) {
            if (!isVisited[i]){
                from[i] = v;
                dfs(i);//对v的所有相邻的节点进行递归深度优先遍历
            }
        }

    }
}
