package 最小生成树.最小生成树算法;

public interface UF {
    int getSize();

    boolean isConnected(int p, int q);

    void unionElements(int p, int q);
}
