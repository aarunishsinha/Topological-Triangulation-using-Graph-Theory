package code;


public class Shape implements ShapeInterface
{
	public static PointList points = new PointList();
	public static EdgeList edges = new EdgeList();
	public static TriangleList triangles = new TriangleList();
	
//	public static int triangle_cnt = 0;
	public int mesh = -1;
	
//	to check collinearity of 3 points
	private boolean iscollinear(float[] t_c) {
		float i = (t_c[1] - t_c[4])*(t_c[2] - t_c[8]) - (t_c[1] - t_c[7])*(t_c[2] - t_c[5]);
		float j = (t_c[0] - t_c[3])*(t_c[2] - t_c[8]) - (t_c[0] - t_c[6])*(t_c[2] - t_c[5]);
		float k = (t_c[0] - t_c[3])*(t_c[1] - t_c[7]) - (t_c[0] - t_c[6])*(t_c[1] - t_c[4]);
		float is = i*i + j*j + k*k;
		if(is == 0) {
			return true;
		}
		else
			return false;
	}
	
	@Override
	public boolean ADD_TRIANGLE(float [] triangle_coord) {
		Point A,B,C;
		A = new Point(triangle_coord[0], triangle_coord[1], triangle_coord[2]);
		B = new Point(triangle_coord[3], triangle_coord[4], triangle_coord[5]);
		C = new Point(triangle_coord[6], triangle_coord[7], triangle_coord[8]);
		if(iscollinear(triangle_coord)) {
			return false;
		}
		else {
			Triangle t = new Triangle(triangle_coord);
			triangles.add(t);
//			triangle_cnt++;
//			adding points to PointList
			if(points.has(A)) {
				if(points.search(A).neighbours.has(B)) {}
				else {
					points.search(A).neighbours.add(B);
				}
				if(points.search(A).neighbours.has(C)) {}
				else {
					points.search(A).neighbours.add(C);
				}
			}
			else {
				points.add(A);
				points.search(A).neighbours.add(B);
				points.search(A).neighbours.add(C);
			}
			if(points.has(B)) {
				if(points.search(B).neighbours.has(A)) {}
				else {
					points.search(B).neighbours.add(A);
				}
				if(points.search(B).neighbours.has(C)) {}
				else {
					points.search(B).neighbours.add(C);
				}
			}
			else {
				points.add(B);
				points.search(B).neighbours.add(A);
				points.search(B).neighbours.add(C);;
			}if(points.has(C)) {
				if(points.search(C).neighbours.has(B)) {}
				else {
					points.search(C).neighbours.add(B);
				}
				if(points.search(C).neighbours.has(A)) {}
				else {
					points.search(C).neighbours.add(A);
				}
			}
			else {
				points.add(C);
				points.search(C).neighbours.add(B);
				points.search(C).neighbours.add(A);
			}
//			Adding edges to EdgeList
			Edge AB, BC, CA;
			AB = new Edge(A, B);
			BC = new Edge(B, C);
			CA = new Edge(C, A);
			if(edges.has(AB)) {
				for(int i = 0; i<edges.size(); i++) {
					if(edges.get(i).equal(AB)) {
						edges.get(i).edgecnt++;
						break;
					}
				}
			}
			else {
				edges.add(AB);
			}
			if(edges.has(BC)) {
				for(int i = 0; i<edges.size(); i++) {
					if(edges.get(i).equal(BC)) {
						edges.get(i).edgecnt++;
						break;
					}
				}
			}
			else {
				edges.add(BC);
			}
			if(edges.has(CA)) {
				for(int i = 0; i<edges.size(); i++) {
					if(edges.get(i).equal(CA)) {
						edges.get(i).edgecnt++;
						break;
					}
				}
			}
			else {
				edges.add(CA);
			}
		}
		return true;
	}
	
	@Override
	public int TYPE_MESH() {
		int impro = 0;
		int semi = 0;
		for(int i= 0; i<edges.size(); i++) {
			if(edges.get(i).edgecnt == 2) {
			}
			if(edges.get(i).edgecnt < 2) {
				semi = 2;
			}
			if(edges.get(i).edgecnt > 2) {
				impro = 3;
			}
		}
		if(impro>0) {
			mesh = 3;
		}
		else if(semi > 0) {
			mesh = 2;
		}
		else {
			mesh = 1;
		}
		return mesh;
	}
	
	@Override
	public Edge[] EDGE_NEIGHBOR_TRIANGLE(float [] triangle_coord) {
		Triangle temp = new Triangle(triangle_coord);
		for(int i = 0; i<triangles.size(); i++) {
			if(triangles.get(i).equal(temp)) {
				return triangles.get(i).triangle_edges();
			}
		}
		return null;
	}
	
	@Override
	public Edge[] BOUNDARY_EDGES() {//sort the output array ***********
		EdgeList temp = new EdgeList();
		for(int i= 0; i<edges.size(); i++) {
			if(edges.get(i).edgecnt<2) {
				temp.add(edges.get(i));
			}
		}
		if(temp.size() == 0) {
			return null;
		}
		Edge[] ret = new Edge[temp.size()];
		for(int j = 0; j<temp.size(); j++) {
			ret[j] = temp.get(j);
		}
		for(int i = 0; i<ret.length; i++) {
			Edge key = ret[i];
			int j =i-1;
			while(j>=0 && ret[j].value()>key.value()) {
				ret[j+1] = ret[j];
				j = j-1;
			}
			ret[j+1] = key;
		}
		return ret;
	}
	@Override
	public Point[] NEIGHBORS_OF_POINT(float [] point_coordinates) {
		Point p = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]);
		PointList temp = new PointList();
		for(int i = 0; i<points.size(); i++) {
			if(points.get(i).equal(p)) {
				temp = points.get(i).neighbours;
			}
		}
		if(temp.size() == 0) {
			return null;
		}
		Point[] ret = new Point[temp.size()];
		for(int j = 0; j<temp.size(); j++) {
			ret[j] = temp.get(j);
		}
		return ret;
	}
	
	@Override
	public Point[] VERTEX_NEIGHBOR_TRIANGLE(float [] triangle_coord) {
		Triangle temp = new Triangle(triangle_coord);
		for(int i = 0; i<triangles.size(); i++) {
			if(triangles.get(i).equal(temp)) {
				return triangles.get(i).triangle_coord();
			}
		}
		return null;
	}
	@Override
	public Edge[] EDGE_NEIGHBORS_OF_POINT(float [] point_coordinates) {
		EdgeList temp = new EdgeList();
		Point p = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]);
		for(int i= 0; i<edges.size(); i++) {
			if(edges.get(i).hasPoint(p)) {
				temp.add(edges.get(i));
			}
		}
		if(temp.size() == 0) {
			return null;
		}
		Edge[] ret = new Edge[temp.size()];
		for(int j = 0; j<temp.size(); j++) {
			ret[j] = temp.get(j);
		}
		return ret;
	}
	
	@Override
	public Triangle[] FACE_NEIGHBORS_OF_POINT(float [] point_coordinates) {
		TriangleList temp = new TriangleList();
		Point p = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]);
		for(int i = 0; i<triangles.size(); i++) {
			if(triangles.get(i).hasPoint(p)) {
				temp.add(triangles.get(i));
			}
		}
		if(temp.size() == 0) {
			return null;
		}
		Triangle[] ret = new Triangle[temp.size()];
		for(int j = 0; j<temp.size(); j++) {
			ret[j] = temp.get(j);
		}
		return ret;
	}
	
	@Override
	public Triangle[] TRIANGLE_NEIGHBOR_OF_EDGE(float [] edge_coordinates) {
		TriangleList temp2 = new TriangleList();
		Point p1 = new Point(edge_coordinates[0], edge_coordinates[1], edge_coordinates[2]);
		Point p2 = new Point(edge_coordinates[3], edge_coordinates[4], edge_coordinates[5]);
		Edge e = new Edge(p1, p2);
		for(int i = 0; i<triangles.size(); i++) {
			if(triangles.get(i).hasEdge(e)) {
				temp2.add(triangles.get(i));
			}
		}
		if(temp2.size() == 0) {
			return null;
		}
		Triangle[] ret = new Triangle[temp2.size()];
		for(int j = 0; j<temp2.size(); j++) {
			ret[j] = temp2.get(j);
		}
		return ret;
	}
	
	@Override 
	public Triangle[] INCIDENT_TRIANGLES(float [] point_coordinates) {
		return FACE_NEIGHBORS_OF_POINT(point_coordinates);
	}
	
	@Override
	public Triangle[] EXTENDED_NEIGHBOR_TRIANGLE(float [] triangle_coord) {
		Point p1 = new Point(triangle_coord[0], triangle_coord[1], triangle_coord[2]);
		Point p2 = new Point(triangle_coord[3], triangle_coord[4], triangle_coord[5]);
		Point p3 = new Point(triangle_coord[6], triangle_coord[7], triangle_coord[8]);
		TriangleList temp1 = new TriangleList();
		for(int i = 0; i<triangles.size(); i++) {
			if((triangles.get(i).hasPoint(p1) || triangles.get(i).hasPoint(p2) || triangles.get(i).hasPoint(p3)) && !triangles.get(i).equal(new Triangle(triangle_coord))) {
				temp1.add(triangles.get(i));
			}
		}
		if(temp1.size() == 0) {
			return null;
		}
		if(!triangles.has(new Triangle(triangle_coord))) {
			return null;
		}
		Triangle[] ret = new Triangle[temp1.size()];
		for(int j = 0; j<temp1.size(); j++) {
			ret[j] = temp1.get(j);
		}
//		for(int i =0; i<ret.length; i++) {
//			System.out.println(ret[i].triangle_coord()[0].getX() + " " + ret[i].triangle_coord()[0].getY() + " " + ret[i].triangle_coord()[0].getZ() + " " + ret[i].triangle_coord()[1].getX() + " " + ret[i].triangle_coord()[1].getY() + " " + ret[i].triangle_coord()[1].getZ() + " " + ret[i].triangle_coord()[2].getX() + " " + ret[i].triangle_coord()[2].getY() + " " + ret[i].triangle_coord()[2].getZ());
//		}
		return ret;
	}
	@Override
	public Triangle[] NEIGHBORS_OF_TRIANGLE(float [] triangle_coord) {
		Point p1 = new Point(triangle_coord[0], triangle_coord[1], triangle_coord[2]);
		Point p2 = new Point(triangle_coord[3], triangle_coord[4], triangle_coord[5]);
		Point p3 = new Point(triangle_coord[6], triangle_coord[7], triangle_coord[8]);
		Edge e1 = new Edge(p1,p2);
		Edge e2 = new Edge(p2,p3);
		Edge e3 = new Edge(p3,p1);
		TriangleList temp3 = new TriangleList();
		for(int i = 0; i<triangles.size(); i++) {
			if((triangles.get(i).hasEdge(e1) || triangles.get(i).hasEdge(e2) || triangles.get(i).hasEdge(e3)) && !triangles.get(i).equal(new Triangle(triangle_coord))) {
				temp3.add(triangles.get(i));
			}
		}
		if(temp3.size() == 0) {
			return null;
		}
		if(!triangles.has(new Triangle(triangle_coord))) {
			return null;
		}
		Triangle[] ret = new Triangle[temp3.size()];
		for(int j = 0; j<temp3.size(); j++) {
			ret[j] = temp3.get(j);
		}
		return ret;
	}
	
	@Override
	public int COUNT_CONNECTED_COMPONENTS() {
		TriangleList comp = new TriangleList();
//		TriangleList[] compos = new TriangleList[10000];
		int size = 0;
		for(int i = 0; i<triangles.size(); i++) {
			if(!(triangles.get(i).marker)) {
//				compos[size] = component(triangles.get(i), comp);
				component(triangles.get(i), comp);
				size++;
				comp = new TriangleList();
			}
		}
		for(int j = 0; j<triangles.size(); j++) {
			triangles.get(j).marker = false;
		}
		return size;
	}
	private TriangleList component(Triangle t, TriangleList comp) {
		Point[] pe = t.triangle_coord();
		float[] coord = new float[9];
		coord[0] = pe[0].getX();
		coord[1] = pe[0].getY();
		coord[2] = pe[0].getZ();
		coord[3] = pe[1].getX();
		coord[4] = pe[1].getY();
		coord[5] = pe[1].getZ();
		coord[6] = pe[2].getX();
		coord[7] = pe[2].getY();
		coord[8] = pe[2].getZ();
		Triangle[] tmp = NEIGHBORS_OF_TRIANGLE(coord);
		if(tmp == null) {
			comp.add(t);
		}
		else {
		for(int i = 0; i<tmp.length; i++) {
			if(!(comp.has(tmp[i]))) {
				comp.add(tmp[i]);
				if(!(tmp[i].marker)) {
					tmp[i].marker = true;
					component(tmp[i], comp);
				}
			}
		}
		}
		return comp;
	}
	
	@Override
	public boolean IS_CONNECTED(float [] triangle_coord_1, float [] triangle_coord_2) {
		Triangle t1 = new Triangle(triangle_coord_1);
		Triangle t2 = new Triangle(triangle_coord_2);
		TriangleList tmp = new TriangleList();
		TriangleList cod = component(t1, tmp);
		for(int j = 0; j<triangles.size(); j++) {
			triangles.get(j).marker = false;
		}
		if(cod.has(t2)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public Point CENTROID_OF_COMPONENT (float[] point_coordinates) {
		Triangle t = null;
		Point p =  new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]);
		for(int i = 0; i<triangles.size(); i++) {
			if(triangles.get(i).hasPoint(p)) {
				t = triangles.get(i);
				break;
			}
		}
		TriangleList is = new TriangleList();
		TriangleList are = component(t, is);
		PointList poi = new PointList();
		for(int i = 0; i<are.size(); i++) {
			for(int j = 0; j<3; j++) {
				if(!(poi.has(are.get(i).triangle_coord()[j]))) {
					poi.add(are.get(i).triangle_coord()[j]);
				}
			}
		}
		float x= 0;
		float y= 0;
		float z= 0;
		for(int i= 0; i<poi.size(); i++) {
			x= x + poi.get(i).getX();
			y= y + poi.get(i).getY();
			z= z + poi.get(i).getZ();
		}
		x = x/poi.size();
		y = y/poi.size();
		z = z/poi.size();
		return new Point(x, y, z);
	}
	
	
	@Override
	public Point[] CENTROID() {
		PointList fin = new PointList();
		TriangleList comp = new TriangleList();
		TriangleList are = new TriangleList();
		for(int i = 0; i<triangles.size(); i++) {
			if(!(triangles.get(i).marker)) {
				are = component(triangles.get(i), comp);
				PointList poi = new PointList();
				for(int j = 0; j<are.size(); j++) {
					for(int k = 0;k<3; k++) {
						if(!(poi.has(are.get(j).triangle_coord()[k]))) {
							poi.add(are.get(j).triangle_coord()[k]);
						}
					}
				}
				comp = new TriangleList();
				float x= 0;
				float y= 0;
				float z= 0;
				for(int m= 0; m<poi.size(); m++) {
					x= x + poi.get(m).getX();
					y= y + poi.get(m).getY();
					z= z + poi.get(m).getZ();
				}
				x = x/poi.size();
				y = y/poi.size();
				z = z/poi.size();
//				System.out.println(x + " " + y + " " + z);// DELETE WHEN DONE
				fin.add(new Point(x,y,z));
			}
		}
		for(int j = 0; j<triangles.size(); j++) {
			triangles.get(j).marker = false;
		}
		Point[] ret = new Point[fin.size()];
		for(int i = 0; i<fin.size(); i++) {
			ret[i] = fin.get(i);
		}
		sort(ret, 0, ret.length-1);
//		for(int i = 0; i<ret.length; i++) {
//			System.out.println(ret[i].getX() + " " + ret[i].getY() + " " + ret[i].getZ());
//		}
		return ret;
	}
	private int partition(Point[] arr, int start, int end) {
		Point pivot = arr[end];
		int pindex = start;
		for(int i = start; i<end; i++) {
			if(arr[i].getX()<pivot.getX()) {
				Point temp = arr[i];
				arr[i] = arr[pindex];
				arr[pindex] = temp;
				pindex++;
			}
			if(arr[i].getX()==pivot.getX()) {
				if(arr[i].getY()<pivot.getY()) {
					Point temp = arr[i];
					arr[i] = arr[pindex];
					arr[pindex] = temp;
					pindex++;
				}
				if(arr[i].getY()==pivot.getY()) {
					if(arr[i].getZ()<pivot.getZ()) {
						Point temp = arr[i];
						arr[i] = arr[pindex];
						arr[pindex] = temp;
						pindex++;
					}
				}
			}
		}
		return pindex;
	}
	private void sort(Point[] arr, int start, int end) {
		if(start<end) {
			int pind = partition(arr, start, end);
			sort(arr, start, pind-1);
			sort(arr, pind+1, end);
		}
	}
	
	@Override
	public int MAXIMUM_DIAMETER() {
		TriangleList large = new TriangleList();
		int max = 0;
		TriangleList comp = new TriangleList();
		for(int i = 0; i<triangles.size(); i++) {
			if(!(triangles.get(i).marker)) {
				if(component(triangles.get(i), comp).size()>max) {
					large = component(triangles.get(i), comp);
				}
				comp = new TriangleList();
			}
		}
		if(large.size() == 1) {
			return 0;
		}
		for(int j = 0; j<triangles.size(); j++) {
			triangles.get(j).marker = false;
		}
		int[] dias = new int[10000];
		int dia_size = 0;
		for(int i = 0; i<large.size()-1; i++) {
			Triangle t1 = large.get(i);
			for(int j = i+1; j<large.size(); j++) {
				Triangle t2 = large.get(j);
				dias[dia_size] = diameter(t1, t2);
				dia_size++;
			}
		}
		int max_dia = 0;
		for(int i = 0;i<dia_size; i++) {
			if(dias[i]>max_dia) {
				max_dia = dias[i];
			}
		}
		return max_dia;
	}
	
	private int diameter(Triangle t1, Triangle t2) {
		TriangleList lol = new TriangleList();
		lol.add(t1);
		int lvl = 0;
		while(!(lol.has(t2))) {
			lol = lvl_neighbors(lol);
			lvl++;
		}
		return lvl;
	}
	
	private TriangleList lvl_neighbors(TriangleList lol) {
		TriangleList lit = new TriangleList();
		for(int i = 0; i<lol.size(); i++) {
			Point[] pe = lol.get(i).triangle_coord();
			float[] coord = new float[9];
			coord[0] = pe[0].getX();
			coord[1] = pe[0].getY();
			coord[2] = pe[0].getZ();
			coord[3] = pe[1].getX();
			coord[4] = pe[1].getY();
			coord[5] = pe[1].getZ();
			coord[6] = pe[2].getX();
			coord[7] = pe[2].getY();
			coord[8] = pe[2].getZ();
			Triangle[] top = NEIGHBORS_OF_TRIANGLE(coord);
			for(int j = 0; j<top.length; j++) {
				if(!(lit.has(top[j]))) {
					lit.add(top[j]);
				}
			}
		}
		return lit;
	}
	
	@Override
	public Point[] CLOSEST_COMPONENTS() {
		Point[] close = new Point[2];
		TriangleList comp = new TriangleList();
		TriangleList are = new TriangleList();
		PointList[] poins = new PointList[10000];
		int poi_s = 0;
		for(int i = 0; i<triangles.size(); i++) {
			if(!(triangles.get(i).marker)) {
				are = component(triangles.get(i), comp);
				PointList poi = new PointList();
				for(int j = 0; j<are.size(); j++) {
					for(int k = 0;k<3; k++) {
						if(!(poi.has(are.get(j).triangle_coord()[k]))) {
							poi.add(are.get(j).triangle_coord()[k]);
						}
					}
				}
				poins[poi_s] = poi;
				poi_s++;
				comp = new TriangleList();
			}
		}
		if(poi_s == 1) {
			return null;
		}
		for(int j = 0; j<triangles.size(); j++) {
			triangles.get(j).marker = false;
		}
		Point first = null;
		Point second = null;
		float closest = 10000000;
		for(int i = 0; i<poi_s-1; i++) {
			PointList l1 = poins[i];
			for(int j = i+1; j<poi_s; j++) {
				PointList l2 = poins[j];
				for(int k = 0; k<l1.size(); k++) {
					Point po1 = l1.get(k);
					for(int l = 0; l<l2.size(); l++) {
						Point po2 = l2.get(l);
						if(eu_dist(po1, po2)<closest) {
							closest = eu_dist(po1, po2);
							first = po1;
							second = po2;
						}
					}
				}
			}
		}
		close[0] = first;
		close[1] = second;
		return close;
	}
	
	private float eu_dist(Point a, Point b) {
		float x = a.getX() - b.getX();
		float y = a.getY() - b.getY();
		float z = a.getZ() - b.getZ();
		return (x*x + y*y + z*z);
	}
}

