package edu.algorithms.competitive.geometry;

import java.util.Arrays;
import java.util.List;

public class Circle {
    public double x, y, r;

    public Circle(double x, double y, double r) {
        this.r = r;
        this.x = x;
        this.y = y;
    }

    public Circle(Point p, double r) {
        this.r = r;
        this.x = p.x;
        this.y = p.y;
    }

    public boolean contains(Point p) {
        return (new Point(x, y).distance(p) <= this.r);
    }

    public static int intersect(Circle a, Circle b) {
        double distSq = (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
        double radSumSq = (a.r + b.r) * (a.r + b.r);
        if (Double.compare(distSq, radSumSq) == 0)
            return 1; // Circle touch to each other.
        else if (distSq > radSumSq)
            return -1; // Circle not touch to each other.
        else
            return 0; // Circle intersect to each other
    }

    public static List<Point> intersectPoints(Circle c1, Circle c2) {
        double d = Math.sqrt(Math.pow(c1.x - c2.x, 2) + Math.pow(c1.y - c2.y, 2));
        double l = (Math.pow(c1.r, 2) - Math.pow(c2.r, 2) + Math.pow(d, 2)) / (2 * d);
        double h = Math.sqrt(Math.pow(c1.r, 2) - Math.pow(l, 2));

        double x1 = ((l * (c2.x - c1.x)) / d) + ((h * (c2.y - c1.y)) / d) + c1.x;
        double x2 = ((l * (c2.x - c1.x)) / d) - ((h * (c2.y - c1.y)) / d) + c1.x;

        double y1 = ((l * (c2.y - c1.y)) / d) - ((h * (c2.x - c1.x)) / d) + c1.y;
        double y2 = ((l * (c2.y - c1.y)) / d) + ((h * (c2.x - c1.x)) / d) + c1.y;

        return Arrays.asList(new Point(x1, y1), new Point(x2, y2));
    }

}
