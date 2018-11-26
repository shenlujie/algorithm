package 图论;


/**
 * @Description: 求一个图的连通分量并通过深度优先遍历进行计算，判断两个节点之间是否存在路径
 * @create: 2018/11/26
 * @Author: SLJ
 */
public class Component {
    private Graph graph;
    private boolean[] isVisited;//记录每个节点是否被访问过
    private int ccount;//连通分量个数
    private int[] id;//将每个连通分量所有节点设置为相同的id

    public Component(Graph graph) {
        this.graph = graph;
        isVisited = new boolean[graph.V()];
        ccount = 0;
        id = new int[graph.V()];

        for (int i = 0; i < graph.V(); i++) {
            isVisited[i] = false;//初始化都没被访问过
            id[i] = -1;//所有id初始化为-1
        }

        for (int i = 0; i < graph.V(); i++) {
            if (!isVisited[i]){
                dfs(i);//对每个没有访问过的节点都进行深度优先遍历
                ccount ++;//当前节点相邻的节点都遍历完返回说明遍历完了一个连通分量
            }
        }
    }

    public int count(){
        return ccount;
    }

    public boolean isConnected(int v,int w){
        if (v < 0 || v >= graph.V()){
            throw new IllegalArgumentException("参数v 违法");
        }
        if ( w < 0 || w >= graph.V()){
            throw new IllegalArgumentException("参数w 违法");
        }

        return id[v] == id[w];
    }

    private void dfs(int v){
        isVisited[v] = true;
        id[v] = ccount;//将每个连通分量的所有节点设置为相同的值（巧妙的将该id设置为当前连通分量的个数，正好可以区分每个连通分量）
        for (int i:graph.adj(v)) {
            if (!isVisited[i]){
                dfs(i);//对v的所有相邻的节点进行递归深度优先遍历
            }
        }

    }
}
