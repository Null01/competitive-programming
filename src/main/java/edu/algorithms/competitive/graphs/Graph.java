package edu.algorithms.competitive.graphs;

/**
 *
 * @author andresfelipegarciaduran
 */
class Graph implements Comparable<Graph> {

    public int x, y, cost;

    public Graph(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;

    }

    @Override
    public int compareTo(Graph o) {
        return (int) (this.cost - o.cost);
    }

    @Override
    public String toString() {
        return this.cost + "";
    }

}
