package edu.algorithms.competitive.geometry;

public class Point implements Comparable<Point> {

    public double x, y;

    public Point(double x, double y) {
        this.x = (float) x;
        this.y = (float) y;
    }

    public Point() {
    }

    public double distance(Point point) {
        return Math.sqrt((this.x - point.x) * (this.x - point.x)
                + (this.y - point.y) * (this.y - point.y));
    }

    @Override
    public boolean equals(Object obj) {
        Point p = (Point) obj;
        return (this.x == p.x && this.y == p.y);
    }

    @Override
    public int compareTo(Point o) {
        if (Double.compare(o.x, this.x) == 0) {
            return Double.compare(o.y, this.y);
        }
        return Double.compare(o.x, this.x);
    }

    public String signed(double r) {
        return (r < 0.0) ? "-" : "+";
    }

    @Override
    public String toString() {
        return "[x=" + x + " y=" + y + "]";
    }
}
