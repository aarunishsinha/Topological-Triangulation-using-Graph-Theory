package code;

public class Triangle implements TriangleInterface {
	private Point A, B, C;
	private Edge AB, BC, CA;
	public boolean marker;
	public TriangleList neighbours;
	private Edge[] ed  = new Edge[3];
	private Point[] poi = new Point[3];
	public Triangle(float[] t_coor) {
		this.A = new Point(t_coor[0], t_coor[1], t_coor[2]);
		this.B = new Point(t_coor[3], t_coor[4], t_coor[5]);
		this.C = new Point(t_coor[6], t_coor[7], t_coor[8]);
		AB = new Edge(this.A, this.B);
		BC = new Edge(this.B, this.C);
		CA = new Edge(this.C, this.A);
		poi[0] = this.A;
		poi[1] = this.B;
		poi[2] = this.C;
		ed[0] = this.AB;
		ed[1] = this.BC;
		ed[2] = this.CA;
		marker = false;
		neighbours = new TriangleList();
	}
	@Override
	public Point[] triangle_coord() {
		return this.poi;
	}
	public Edge[] triangle_edges() {
		return this.ed;
	}
	public boolean equal(Triangle t) {
		boolean bo = false;
		if((this.A.equal(t.triangle_coord()[0]) && this.B.equal(t.triangle_coord()[1]) && this.C.equal(t.triangle_coord()[2]))) {
			bo = true;
		}
		if((this.A.equal(t.triangle_coord()[0]) && this.B.equal(t.triangle_coord()[2]) && this.C.equal(t.triangle_coord()[1]))) {
			bo = true;
		}
		if((this.A.equal(t.triangle_coord()[1]) && this.B.equal(t.triangle_coord()[0]) && this.C.equal(t.triangle_coord()[2]))) {
			bo = true;
		}
		if((this.A.equal(t.triangle_coord()[1]) && this.B.equal(t.triangle_coord()[2]) && this.C.equal(t.triangle_coord()[0]))) {
			bo = true;
		}
		if((this.A.equal(t.triangle_coord()[2]) && this.B.equal(t.triangle_coord()[0]) && this.C.equal(t.triangle_coord()[1]))) {
			bo = true;
		}
		if((this.A.equal(t.triangle_coord()[2]) && this.B.equal(t.triangle_coord()[1]) && this.C.equal(t.triangle_coord()[0]))) {
			bo = true;
		}
		return bo;
	}
	public boolean hasPoint(Point p) {
		boolean b = false;
		if(p.equal(A) || p.equal(B) || p.equal(C)) {
			b = true;
		}
		return b;
	}
	public boolean hasEdge(Edge e) {
		boolean bol = false;
		if(e.equal(AB) || e.equal(BC) || e.equal(CA))
			bol = true;
		return bol;
	}
}
