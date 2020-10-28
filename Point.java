package code;

public class Point implements PointInterface {
	private float X = 0, Y = 0, Z = 0;
	private float[] coor = new float[3];
	public Point next;
	public boolean mark;
	public PointList neighbours;
	public Point(float x, float y, float z) {
		this.X = x;
		this.Y = y;
		this.Z = z;
		coor[0] = x;
		coor[1] = y;
		coor[2] = z;
		next = null;
		mark = false;
		neighbours = new PointList();
	}
	
	@Override
	public float getX() {
		return this.X;
	}
	
	@Override
	public float getY() {
		return this.Y;
	}
	
	@Override
	public float getZ() {
		return this.Z;
	}
	
	@Override
	public float[] getXYZcoordinate() {
		return this.coor;
	}
	public boolean equal(Point p) {
		boolean bo = false;
		if(X == p.getX() && Y == p.getY() && Z == p.getZ()) {
			bo = true;
		}
		return bo;
	}
}
