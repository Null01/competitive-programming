package edu.algorithms.competitive.geometry;

import java.util.List;

public class Line {

    public static final double EPSILON = 0.00000001;
    private Point p1, p2;
    private final double ZERO = 1e-8;
    private final double INF = 1e100;

    public Line() {
    }

    public Line(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point intersectLine(Line l1, Line l2) {
        double m1, m2;
        // halla la tendecia de la pendiente.
        m1 = (equals(l1.p1.x, l1.p2.x)) ? INF : ((l1.p2.y - l1.p1.y) / (l1.p2.x - l1.p1.x));
        m2 = (equals(l2.p1.x, l2.p2.x)) ? INF : ((l2.p2.y - l2.p1.y) / (l2.p2.x - l2.p1.x));
        // determina si las rectas se interseptan.
        if (equals(m1, m2)) {
            // determina si la recta pertenese a la otra o existe intersepcion
            if (equals(l1.p1.y - m1 * l1.p1.x, l2.p1.y - m2 * l2.p1.x)) {
                // LAS DOS RECTAS SON UNA SOLA.
            } else {
                // NO INTERSEPCION ENTRE LINEAS
            }
        }

        Point out = new Point();
        // calcula el punto de intersepcion en X
        out.x = ((m1 * l1.p1.x - l1.p1.y) - (m2 * l2.p1.x - l2.p1.y)) / (m1 - m2);
        if (equals(l1.p1.x, l1.p2.x)) {
            out.x = l1.p1.x;
        }
        if (equals(l2.p1.x, l2.p2.x)) {
            out.x = l2.p1.x;
        }
        // calcula el punto de intersepacion en Y
        out.y = m1 * (out.x - l1.p1.x) + l1.p1.y;
        if (equals(l1.p1.x, l1.p2.x)) {
            out.y = m2 * (out.x - l2.p1.x) + l2.p1.y;
        }
        return out;
    }

    public boolean equals(double a, double b) {
        return (Math.abs((a) - (b)) < ZERO);
    }

    public long max_points_in_a_line(List<Point> points) {
        long max_points = 0, max_temp;
        int j;
        double[] temp = new double[points.size()];
        for (int i = 0; (max_points + 2) <= (points.size() - i); i++) {
            for (j = i + 1; j < points.size(); j++) {
                if (points.get(i).x == points.get(j).x) {
                    temp[j] = INF;
                } else {
                    temp[j] = (points.get(i).y - points.get(j).y) / (points.get(i).x - points.get(j).x);
                }
            }
            for (j = i + 1; j < points.size(); j++) {
                max_temp = 0;
                for (int k = j + 1; k < points.size(); k++) {
                    if (Math.abs(temp[j] - temp[k]) < ZERO) {
                        ++max_temp;
                    }
                }
                if (max_temp > max_points) {
                    max_points = max_temp;
                }
            }
        }
        return max_points + 2;
    }

}
