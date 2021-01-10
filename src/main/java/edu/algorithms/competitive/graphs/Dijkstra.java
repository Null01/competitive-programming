package edu.algorithms.competitive.graphs;

import java.util.PriorityQueue;

/**
 *
 * @author andresfelipegarciaduran
 */
public class Dijkstra {

    public int board[][];
    public boolean visit[][];
    public int costs[][];

    public Dijkstra(int[][] board, boolean[][] visit, int[][] costs) {
        this.board = board;
        this.visit = visit;
        this.costs = costs;
    }

    public void search(int ry, int cx) {
        PriorityQueue<Graph> queue = new PriorityQueue();
        queue.add(new Graph(0, 0, board[0][0]));
        short dirX[] = {0, 1, 0, -1};
        short dirY[] = {-1, 0, 1, 0};
        costs[0][0] = board[0][0];
        int x, y;
        while (!queue.isEmpty()) {
            Graph graph = queue.poll();
            if (visit[graph.y][graph.x]) {
                continue;
            }
            visit[graph.y][graph.x] = true;

            if (graph.x == (cx - 1) && graph.y == (ry - 1)) {
                System.out.println(graph.cost);
                break;
            }
            for (int i = 0; i < 4; i++) {
                x = graph.x + dirX[i];
                y = graph.y + dirY[i];
                if (x < 0 || x >= cx || y < 0 || y >= ry) {
                    continue;
                }
                if (costs[y][x] == -1 || (board[y][x] + graph.cost) < costs[y][x]) {
                    costs[y][x] = board[y][x] + graph.cost;
                    queue.offer(new Graph(x, y, graph.cost + board[y][x]));
                }
            }
        }
    }

}
