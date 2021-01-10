package edu.algorithms.competitive.geometry;

public class Circle {
	public double h, k, r;

	public Circle(double h, double k, double r) {
		this.r = r;
		this.h = h;
		this.k = k;
	}

	public Circle(Point p, double r) {
		this.r = r;
		this.h = p.x;
		this.k = p.y;
	}

	public boolean contains(Point p) {
		return (new Point(h, k).distance(p) <= this.r);
	}
}
