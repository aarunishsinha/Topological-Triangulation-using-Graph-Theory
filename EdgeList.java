package code;

public class EdgeList {
	private Edge[] list;
	private int size;
	private int size1;
	public EdgeList() {
		this.list = new Edge[10000];
		this.size = 0;
		this.size1 = 10000;
	}
	public void add(Edge e) {
		if(size<size1) {
			list[size] = e;
			size++;
		}
		else {
			Edge[] temp = list;
			list = new Edge[size + 10000];
			for(int i = 0;i<size; i++) {
				list[i] = temp[i];
			}
			size1 = size + 10000;
			add(e);
		}
	}
	public int size() {
		return this.size;
	}
	public Edge get(int a) {
		return list[a];
	}
	public boolean has(Edge e) {
		boolean bo = false;
		for(int i = 0; i<this.size; i++) {
			if((list[i].edgeEndPoints()[0].equal(e.edgeEndPoints()[0]) && list[i].edgeEndPoints()[1].equal(e.edgeEndPoints()[1])) || (list[i].edgeEndPoints()[1].equal(e.edgeEndPoints()[0]) && list[i].edgeEndPoints()[0].equal(e.edgeEndPoints()[1]))) {
				bo = true;
				break;
			}
		}
		return bo;
	}
}
