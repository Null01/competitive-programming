package edu.algorithms.competitive.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;
import java.util.TreeSet;

public class Polygon {

    public static final double EPSILON = 0.00000001;
    private static Point first_point;

    public double triangle_area(Point a, Point b, Point c) {
        return (a.x * b.y - a.y * b.x + a.y * c.x - a.x * c.y + b.x * c.y - c.x
                * b.y);
    }

    private Point[] remove_duplicates(Point p[]) {
        TreeSet<Point> tree = new TreeSet<>(Arrays.asList(p));
        int i = -1;
        Point[] points = new Point[tree.size()];
        for (Point pp : tree) {
            points[++i] = pp;
        }
        return points;
    }

    private boolean cw(Point a, Point b, Point c) {
        return triangle_area(a, b, c) < -EPSILON;
    }

    private boolean ccw(Point a, Point b, Point c) {
        double area = triangle_area(a, b, c);
        return (area > EPSILON) || Double.compare(area, 0.0) == 0;
    }

    private boolean collinear(Point a, Point b, Point c) {
        return Math.abs(triangle_area(a, b, c)) <= EPSILON;
    }

    public ArrayList<Point> convex_hull(Point in[]) {

        if (in.length <= 3) {
            ArrayList<Point> l = new ArrayList<>(Arrays.asList(in));
            Collections.reverse(l);
            return l;
        }

        in = remove_duplicates(in);
        int index = 0;
        for (int i = 0; i < in.length; i++) {
            if (in[i].y < in[index].y
                    || (in[i].y == in[index].y && in[i].x > in[index].x)) {
                index = i;
            }
        }

        Point temp = in[0];
        in[0] = in[index];
        in[index] = temp;

        first_point = in[0];
        Arrays.sort(in, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                if (collinear(first_point, p1, p2)) {
                    return (first_point.distance(p1) <= first_point
                            .distance(p2)) ? -1 : 1;
                }
                return (ccw(first_point, p1, p2)) ? -1 : 1;
            }
        });

        Point prev, now;
        Stack<Point> stack = new Stack<>();
        stack.push(first_point);

        for (int i = 1; i < in.length;) {
            if (stack.size() < 2) {
                stack.push(in[i++]);
            } else {
                now = stack.lastElement();
                stack.pop();
                prev = stack.lastElement();
                stack.push(now);
                if (ccw(prev, now, in[i])) {
                    // Dependiendo de la implementacion. Es posible que los punto colineales esten dentro del grid.
                    stack.push(in[i++]);
                } else {
                    stack.pop();
                }
            }
        }
        return new ArrayList<>(stack);
    }

}
