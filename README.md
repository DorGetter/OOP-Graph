# OOP-Graph

## About the project:
this project will let you use a directed Graph data stracture.
this program can creates a directional graphs and support the following functionalities: 

1. calculating least cost valued pathes between two vertexes.
2. presents cost value of the shortest path between two (or multiple vertexes as targets). 
3. GUI using jframe to visialize the graph and use all the functionality from the GUI.
4. Can creat a graph on GUI, save or load graphs.
5. Add / remove vertexs or edges on a given graph.

Example:
-PIC-

The project is build from the following classes:

### NodeV:
(implemnts node_data iterface)

This interface represents the set of operations applicable on a 
 node (vertex) in a (directional) weighted graph.
 
This calss will vreates the vertexes of the graph, eatch vertex will contain the follwing meta-data and a uniq ID:

my_id >> the uniq ID of the vertex.

point >> will be an Point 3D object that will hold the cordinates.

weight >> the qwight of the vertex

tag >> will be used to detarmin if  the graph is strogly connected / path from one vertex to the other.

### Edge:
(implemnts edge_data iterface)

This interface represents the set of operations applicable on a 
directional edge(src,dest) in a (directional) weighted graph.

this class will creates the edges of the graph, each edge will contain the followig meta-data: 

node_data src   >> the source vertex of the edge

node_data dest  >> the destenation vertex of the edge

double w        >> the "cost" of taking the travel on the edge.


### Dgraph: 
(implemets graph interface) 
This interface represents a directional weighted graph.

The interface has a road-system or communication network in mind - and should support a large number of nodes (over 100,000).
The implementation should be based on an efficient compact representation.

##### Dgrap data structure:
 * HashMap vertex - will contain all the vertexes (node_data) in the graph.
 * HashMap edges - will contain all the edges (edge_data) in the graph.

### Algo graph: 
(implemets graph algorithems interface) 

This interface represents the "regular" Graph Theory algorithms including:

 * 0. clone();
 * 1. init(String file_name);
 * 2. save(String file_name); 
 * 3. isConnected(); >>Using a BFS algorithem.
 * 5. double shortestPathDist(int src, int dest); >>Djsktra algorithem.
 * 6. List<Node> shortestPath(int src, int dest); >>Djsktra algorithem.


 
