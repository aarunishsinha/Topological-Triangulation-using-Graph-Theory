package code;

public class TriangleList {
	private Triangle[] list;
	private int size;
	private int size1;
	public TriangleList() {
		this.list = new Triangle[10000];
		this.size = 0;
		this.size1 = 10000;
	}
	public void add(Triangle t) {
		if(size<size1) {
			list[size] = t;
			size++;
		}
		else {
			Triangle[] temp = list;
			list = new Triangle[size + 10000];
			for(int i = 0; i<size; i++) {
				list[i] = temp[i];
			}
			size1 = size + 10000;
			add(t);
		}
	}
	public int size() {
		return this.size;
	}
	public Triangle get(int a) {
		return list[a];
	}
	public boolean has(Triangle t) {
		boolean bo = false;
		for(int i= 0; i<this.size; i++) {
			if(list[i].equal(t)) {
				bo = true;
				break;
			}
		}
		return bo;
	}
	/*public boolean searchEdge(Edge e, Triangle t1, Triangle t2) {
		boolean lea = false;
		for(int i = 0; i<this.size; i++) {
			if(list[i].hasEdge(e) && list[i])
		}
		return lea;
	}*/
}