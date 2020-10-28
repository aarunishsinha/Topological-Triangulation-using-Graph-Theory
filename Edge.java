package code;

public class Edge implements EdgeInterface {
	private Point A, B;
	private float value;
	public int edgecnt;
	private Point[] e = new Point[2];
	public Edge(Point a, Point b) {
		this.A = a;
		this.edgecnt = 1;
		this.B = b;
		e[0] = this.A;
		e[1] = this.B;
		value = (a.getX() - b.getX())*(a.getX() - b.getX()) + (a.getY() - b.getY())*(a.getY() - b.getY()) + (a.getZ() - b.getZ())*(a.getZ() - b.getZ());
	}
	@Override
	public Point[] edgeEndPoints() {
		return this.e;
	}
	public float value() {
		return this.value;
	}
	public boolean equal(Edge e) {
		boolean bo = false;
			if((this.A.equal(e.edgeEndPoints()[0]) && this.B.equal(e.edgeEndPoints()[1])) || (this.B.equal(e.edgeEndPoints()[0]) && this.A.equal(e.edgeEndPoints()[1]))) {
				bo = true;
			}
		return bo;
	}
	public boolean hasPoint(Point p) {
		boolean b = false;
		if(p.equal(this.A) || p.equal(this.B)) {
			b = true;
		}
		return b;
	}
}
