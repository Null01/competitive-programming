package edu.algorithms.competitive.geometry;

public class Triangle {

    public Point a, b, c;

    public Triangle() {

    }

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean contains(Point p) {
        Polygon polygon = new Polygon();
        double allArea = Math.abs(polygon.triangle_area(a, b, c));
        double a1 = Math.abs(polygon.triangle_area(p, b, c));
        double a2 = Math.abs(polygon.triangle_area(a, p, c));
        double a3 = Math.abs(polygon.triangle_area(a, b, p));
        return Double.compare(Math.abs(allArea - (a1 + a2 + a3)),
                Polygon.EPSILON) <= 0;
    }

    public double triangle_area(Point a, Point b, Point c) {
        return new Polygon().triangle_area(a, b, c);
    }

    public double radius_formed_by_triangle() {
        double a = this.a.distance(this.b);
        double b = this.a.distance(this.c);
        double c = this.b.distance(this.c);
        double sp = (a + b + c) / 2;
        double area_triangle = Math.sqrt(sp * (sp - a) * (sp - b) * (sp - c));
        return (a * b * c) / (4 * area_triangle);
    }

    public double radius_formed_by_sizes_triangle(double la, double lb, double lc) {
        if (la == 0 || lb == 0 || lc == 0) {
            return 0.0;
        }
        double sp = (la + lb + lc) / 2;
        double area_triangle = Math
                .sqrt(sp * (sp - la) * (sp - lb) * (sp - lc)) / sp;
        return area_triangle;
    }

    public Circle circle_generate_by_triangle() {
        // (x - h)^2 + (y - k)^2 = r^2
        // x^2 + y^2 + ax + by + c = 0
        double P1 = -a.x * a.x - a.y * a.y;
        double P2 = -b.x * b.x - b.y * b.y;
        double P3 = -c.x * c.x - c.y * c.y;
        double mat[][] = {{a.x, a.y, 1, P1}, {b.x, b.y, 1, P2},
        {c.x, c.y, 1, P3}};
        SystemEquation s = new SystemEquation(mat);
        double solve[] = s.solveSystem3X3();
        double x = solve[0] / 2.0;
        double y = solve[1] / 2.0;
        double r = Math.sqrt((x * x) + (y * y) - solve[2]);
        return new Circle(x, y, r);
    }

}
