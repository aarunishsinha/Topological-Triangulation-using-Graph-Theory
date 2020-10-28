# Topological-Triangulation-using-Graph-Theory
COL106 - Data Structures and Algorithms

Classes in src :- 
1. Driver
2. Edge
3. EdgeList
4. Point 
5. PontList
6. Shape
7. Triangle
8. TriangleList

Driver :
This class was provided and no changes were allowed.

Edge :
This class implements the interface EdgeInterface which has a function edgeEndPoints() to be implemented.
edgeEndPoints() returns an array of Point. In the constructor of class I have passed two Points which are
the endpoints of the edge I am creating. Also I have stored the edge length in the float "value". I have 
created a few more functions like value(), equal(Edge e) and hasPoint(Point p). value() returns a float 
which has the edge length. equal(Edge e) returns a boolean type checking if the given edge e is equal to 
the edge which calls this method. hasPoint(Point p) returns true if the given Point p is one of the endpoints 
of the edge which calls this function. All these methods take O(1) time complexity.

EdgeList :
In this class I've basically tried to implement an ArrayList kind of data structure using arrays which stores
edges and has some methods that can be used further ahead. This data structure has a private array of edges. 
It has a few methods like add(Edge e), size(), get(int a) and has(Edge e). add(Edge e) simply adds a new Edge 
to the array of edges. If the array is completely filled it adds extra space to the array. size() returns an 
integer value which the size of the EdgeList which calls it. has(Edge e) checks if the EdgeList contains the 
edge e and it does this in time complexity O(m), where m denotes the index at which the Edge e was found in 
the EdgeList and I break out of the loop as soon as it found the Edge e.

Point : 
This class implements the interface PointInterface which has four methods that need to be defined. getX() returns
the x-coordinate of the Point which calls this method, getY() returns the y-coordinate of the Point which calls 
this method and getZ() returns the z-coordinate of the Point which calls this method. getXYZcoordinate() returns 
and array of float type containing all the X, Y and Z coordinates of the Point. I have also implemented a function
to check if two points are same or not. equal(Point p) returns true if all the three coordinates of the Point p are
same as the Point calling this method. In the constructor I've also created PointList which stores all the neighbours
of the given Point. All the methods have O(1) time complexity.

PointList :
Similar to EdgeList, this class creates an ArrayList type data structure for storing Points. It has four methods: 
size() returns the size of the PointList in O(1) time, get(int a) returns the element at the ath position in 
the list in O(1) time, has(Point p) returns true if the PointList has point p in it with a time complexity of 
O(n) where n is the location of the point p in the list, search(Point p) returns the Point object which has 
the same coordinates as p in O(n) time, where n = size of PointList.

Triangle :
This class implements the interface TriangleInterface which has a function triangle_coord() which returns an 
array of Point containing all the vertices of the triangle. In the constructor of the function I have created 
three Point objects which store the vertices of the triangle, three edge objects storing the edges of the triangle, 
a boolean marker which helps during traversal, and a list of neighbors of the triangle. I have also stored all the 
vertices of the triangle in an array of Point which is returned in the when the method triangle_coord is called.
The method triangle_edges() returns an array of three Edges which are the edges of the triangle, equal(Triangle t)
returns true if the triangle calling this method is the same as t, hasPoint(Point p) returns true if the p is
one of the vertices of the triangle and hasEdge(Edge e) returns true if the triangle calling this method has the 
edge e. All these method have time complexity O(1).

TriangleList :
Similar to the two types of list defined above, this class creates an ArrsyList type data structure for storing 
triangles. The method add(Triangle t) add triangle t to list, size() returns the size of the TriangleList in O(1)
time complexity, get(int a) returns the Triangle at index a in the list in O(1) time, has(Triangle t) returns 
true if the TriangleList calling the method has t in time complexity O(n) where n = index at which the triangle
is present. 

Shape : 
This was the class in which methods had to implemented to respond to all the queries. It has a static PointList 
points which stores all the points in the Shape, static EdgeList edges storing all the edges in the shape, static
TriangleList tirangles which stores all the triangles in the shape , int mesh which is used in one of the queries.
There are a total of 18 queries that need to be answered :-

1. ADD_TRIANGLE(float[] triangle_coord)
	This method returns true if the triangle being added is valid i.e. the coordinated given are not collinear.
	To check this I created a helper functon boolean iscollinear(float[] t_c) which returns true if the give 
	coordinates are collinear. In ADD_TRIANGLE, I call iscollinear, if it returns false the I create a Triangle
	object and add it to the TriangleList triangles. I also add all the vertices and edges of the triangle in 
	points and edges respectively. I have also inserted all the neighbors of every point in theie respective 
	neighbour list.
	
2. TYPE_MESH()
	This method returns 1 if the shape is a proper mesh, 2 if it is a semi proper mesh and 3 if it is an improper
	mesh. If all the edges have exactly two triangles incident on them then this method returns 1, if there exists
	an edge with only one triangle incident on it the method returns 2 and if there is an edge which more than 2
	triangles incident on it returns 3. The time complexity of this method is O(n) where n = edges.size().
	
3. EDGE_NEIGHBOR_TRIANGLE(float [] triangle_coord)
	This method returns an array of Edges containing all the edges of the triangle whose coordinates have been 
	passed. If the triangle coordinates passed are invalid then the method will return null. It takes O(n) time 
	where n = number of triangles in the shape.
	
4. BOUNDARY_EDGES()
	The edges which have exactly one triangle incident on them are the boundary edges. So we search from the 
	EdgeList edges and add edges to a new array and return it. This takes O(n) where n = size of edges. Also 
	the returned array should be sorted for which i have deployed Insertion Sort.
	
5. NEIGHBORS_OF_POINT(float [] point_coordinates)
	In this method we have to return an array of Points containing all its neighbours. Since I had already stored
	the neighbors of every point in a list in the point, I just have to search the given point in the List containing
	all the points and return the array of its neighbours. This take O(n + m) where n= points.size() and m = number
	of neighbors of the given point.
	
6. VERTEX_NEIGHBOR_TRIANGLE(float [] triangle_coord)
	In this method we simply have to return an array of Points containing all the vertices of the given triangle.
	It takes O(n) to search the triangle in the list of triangles.
	
7. EDGE_NEIGHBORS_OF_POINT(float [] point_coordinates)
	In this method I have searched for all the edges which have the given point in them and stored them in an array.
	This method would return this array. This takes O(n + m) time where n= size of the list in which edges are stored
	and m = number of edges with point given.
	
8. FACE_NEIGHBORS_OF_POINT(float [] point_coordinates)
	In this method we have to return all the triangles containing the given point. I implemented this in the similar
	as the above method.
	
9. TRIANGLE_NEIGHBOR_OF_EDGE(float [] edge_coordinates)
	In this method we have return an array of triangles containing all the triangles which have the give edge. It 
	will also be implemented in the manner as the above function.
	
10. INCIDENT_TRIANGLES(float [] point_coordinates)
	Same as FACE_NEIGHBORS_OF_POINT and since I have added the triangles into the array in the order of their arrival
	the was no need for any kind of sorting.
	
11. EXTENDED_NEIGHBOR_TRIANGLE(float [] triangle_coord)
	Given the coorddinates of the vertices of the triangle, I created 3 Point object denoting the vertices and iterated
	over the list of triangles. If the triangle where the pointer is currently present has any one of the 3 Points add
	this Triangle into the array to be returned. This takes O(n + m) where n = number of triangles in the shape and 
	m = number of triangles containing any one of the three points.
	
12. NEIGHBORS_OF_TRIANGLE(float [] triangle_coord)
	In this method I created 3 Edge objects to store the edges of the given triangle and iterated over the list of all 
	triangles and if a triangle has any of the three edges it is a neighbor of the given triangle and store it into 
	the array to be returned. This takes O(n+m) time where n = number of triangles in the shape and m = number of
	triangles who are neighbors of the given triangle.
	
13. COUNT_CONNECTED_COMPONENTS()
	For this method I have created a helper function component(Triangle t, TriangleList comp) which returns a list 
	containing all the triangles in a component in O(logn). I call this helper function in the main method and iterate
	over all the triangles finding all the components. When a triangle is visited it is marked so that I do not have 
	visit it again and I do not end into an infinite loop. Everytime a component is found count is incremented. At
	the end all the triangles are unmarked and count is returned. This takes O(nlogm + n) where n= 
	number of triangles in the shape.
	
14. IS_CONNECTED(float [] triangle_coord_1, float [] triangle_coord_2)
	In this method I create a Triangle object with triangle_coord_1, find its component. If the component has the 
	second triangle I return true, else false. Before return I also unmark all the triangles. This is done in
	O(n + logm) where n = numbers of triangles in the shape and m for traversal in the finding the component.
	
15. CENTROID_OF_COMPONENT (float[] point_coordinates) 
	In this method I first find the first triangle in the List of triangles which contains the given point. Then,
	I find the component which contains this triangle and store all the points in this component in a PointList.
	This I simply calculate the centroid, and return in the form of a Point coordinate. This takes O(n) time.
	
16. CENTROID()
	This method is implemented in a similar manner as CENTROID_OF_COMPONENT. The difference here is that I have 
	found out list of points of all the components in the shape and centroid their centroids and return an array
	of centroids. This takes O(n*m) time. Since, the centroids have to be returned in a sorted manner I have 
	implemented Quick Sort for that.
	
17. MAXIMUM_DIAMETER()
	For this method I first found out the largest component and in this component I calculated the  minimum number of that 
	are required to move from one triangle to every other triangle. From this I found out the largest number of hops which
	is the maximum diameter. For calculating the minimum number of hops I have made a recursive helper function which 
	traverses overs the triangles of a component till it finds out the largest number of minimum hops. I have also implemented a helper 
	function for find the nth neighbours of a triangle which I have used to find the minimum number of hops. This has a
	time complexity of O(n^2) where n = size of the largest component.
	
18. CLOSEST_COMPONENTS()
	First I have stored the points in each individual component in a list of list of points. Then I have calculated the Euclidean
	distance of a point from one component to every Point of every other components and have updated the minimum distance and the 
	points which have this minimum distance. This has a poor time complexity of O(n^4). For calculating the minimum diatance 
	I have used a helper function eu_dist(Point a, Point b) which return the square of distance between two points a and b.
