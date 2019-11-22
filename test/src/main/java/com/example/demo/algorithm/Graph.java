package com.example.demo.algorithm;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 有向有权图的邻接表表示
 *
 * @author hfj
 * @date 2019-11-22
 */
public class Graph {

    /**
     * 邻接表
     */
    private LinkedList<Edge>[] edgesOfVertexes;
    /**
     * 顶点个数
     */
    private int numOfVertexes;

    public Graph(int numOfVertexes) {
        this.numOfVertexes = numOfVertexes;
        this.edgesOfVertexes = new LinkedList[numOfVertexes];
        for (int i = 0; i < numOfVertexes; ++i) {
            this.edgesOfVertexes[i] = new LinkedList<>();
        }
    }

    /**
     * 添加一条边
     *
     * @param s
     * @param t
     * @param w
     */
    public void addEdge(int s, int t, int w) {
        this.edgesOfVertexes[s].add(new Edge(s, t, w));
    }

    /**
     * 边
     */
    private class Edge {
        /**
         * sid 边的起始顶点编号
         * tid 边的终止顶点编号
         * w 权重
         */
        public int sid;
        public int tid;
        public int w;

        public Edge(int sid, int tid, int w) {
            this.sid = sid;
            this.tid = tid;
            this.w = w;
        }
    }

    /**
     * 顶点
     */
    private class Vertex implements Comparable<Vertex> {
        /**
         * id 顶点编号ID
         * dist 从起始顶点到这个顶点的距离
         */
        public int id;
        public int distance;

        public Vertex(int id, int dist) {
            this.id = id;
            this.distance = dist;
        }

        @Override
        public int compareTo(Vertex o) {
            // 按照dist从小到大排序
            if (o.distance > this.distance) {
                return -1;
            } else {
                return 1;
            }
        }
    }


    /**
     * 从顶点s到顶点t的最短路径
     *
     * @param s
     * @param t
     */
    public void dijkstra(int s, int t) {
        // 用来还原最短路径
        int[] predecessor = new int[this.numOfVertexes];
        // 所有顶点
        Vertex[] vertexes = new Vertex[this.numOfVertexes];
        for (int i = 0; i < numOfVertexes; ++i) {
            // 初始化始顶点到每个顶点的距离distance为无穷大
            vertexes[i] = new Vertex(i, Integer.MAX_VALUE);
        }
        // 小顶堆
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<>();
        // 标记是否进入过队列
        boolean[] beInQueue = new boolean[this.numOfVertexes];

        // 先把起始顶点放到队列中
        vertexQueue.add(vertexes[s]);
        vertexes[s].distance = 0;
        beInQueue[s] = true;

        // 队列不为空
        while (!vertexQueue.isEmpty()) {
            // 取distance最小的顶点
            Vertex minVertex = vertexQueue.poll();
            // 目标顶点为最小的时候，起点和目标顶点之间的最短路径产生了
            if (minVertex.id == t) {
                break;
            }
            // 寻找过程
            for (int i = 0; i < edgesOfVertexes[minVertex.id].size(); ++i) {
                // 取出一条目前最小顶点minVetex相连的边
                Edge e = edgesOfVertexes[minVertex.id].get(i);
                // minVetex相连的下一个顶点
                Vertex nextVertex = vertexes[e.tid];
                // 找到一条到nextVertex更短的路径
                if (minVertex.distance + e.w < nextVertex.distance) {
                    // 更新dist
                    nextVertex.distance = minVertex.distance + e.w;
                    //更新前驱顶点
                    predecessor[nextVertex.id] = minVertex.id;
                    // 如果没有在队列中
                    if (beInQueue[nextVertex.id] == false) {
                        // 就把它放到队列中
                        vertexQueue.add(nextVertex);
                        beInQueue[nextVertex.id] = true;
                    }
                }
            }
        }
        // 输出最短路径
        System.out.print(s);
        print(s, t, predecessor);
    }

    private void print(int s, int t, int[] predecessor) {
        if (s == t) {
            return;
        }
        print(s, predecessor[t], predecessor);
        System.out.print("->" + t);
    }


//    @Test
//    public void dijkstraTest(){
//        Graph graph = new Graph(9);
//        graph.addEdge(0,1,1);
//        graph.addEdge(0,2,2);
//        graph.addEdge(1,5,5);
//        graph.addEdge(1,3,3);
//        graph.addEdge(2,3,3);
//        graph.addEdge(5,6,6);
//        graph.addEdge(3,6,6);
//        graph.addEdge(6,7,7);
//        graph.dijkstra(0,3);
//    }
// 输出结果：0->1->3

}
