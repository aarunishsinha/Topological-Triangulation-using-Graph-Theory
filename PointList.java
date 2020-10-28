package code;

public class PointList {
	private Point[] list;
	private int size;
	private int size1;
	public PointList() {
		this.list = new Point[10000];
		this.size = 0;
		this.size1 = 10000;
	}
	public void add(Point p) {
		if(size<size1) {
			list[size] = p;
			size++;
		}
		else {
			Point[] temp = list;
			list = new Point[size + 10000];
			for(int i= 0; i<size; i++) {
				list[i] = temp[i];
			}
			size1 = size + 10000;
			add(p);
		}
	}
	public int size() {
		return this.size;
	}
	public Point get(int a) {
		return list[a];
	}
	public boolean has(Point p) {
		boolean bo = false;
		for(int i= 0; i<this.size; i++) {
			if(list[i].getX() == p.getX() && list[i].getY() == p.getY() && list[i].getZ() == p.getZ()) {
				bo = true;
				break;
			}
		}
		return bo;
	}
	public Point search(Point p) {
		Point poi = null;
		for(int i = 0; i<size; i++) {
			if(list[i].equal(p)) {
				poi = list[i];
			}
		}
		return poi;
	}
}
