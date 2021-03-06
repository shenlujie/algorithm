package 图论;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Description:
 * @create: 2018/11/14
 * @Author: SLJ
 */
public class ReadGraph {

    public ReadGraph(Graph graph,String fileName) {
        readGraph(graph, fileName);
    }

    private void readGraph(Graph graph,String fileName){
        FileReader reader = null;
        BufferedReader bufferedReader = null;

        try {
            File file = new File(fileName);
            if (file.exists()){
                reader = new FileReader(file);
                bufferedReader = new BufferedReader(reader);
                String firstLine = bufferedReader.readLine();
                String[] arr = firstLine.split(" ");
                int V = Integer.valueOf(arr[0]);
                int E = Integer.valueOf(arr[1]);

                if (V != graph.V()){
                    throw new IllegalArgumentException("文件Graph的第一行参数存在问题");
                }
                for (int i = 0; i < E; i++) {
                    String line = bufferedReader.readLine();
                    String[] brr = line.split(" ");
                    int v = Integer.valueOf(brr[0]);
                    int w = Integer.valueOf(brr[1]);
                    graph.addEdge(v, w);
                }
            }else {
                throw new IllegalArgumentException("文件不存在");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null){
                    reader.close();
                }
                if (bufferedReader != null){
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
