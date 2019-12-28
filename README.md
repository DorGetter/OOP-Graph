# OOP-Graph
this project will let you use a directed Graph data stracture.
this program can creates a directional graphs and support the following functionalities: 

1. calculating least cost valued pathes between two vertexes.
2. presents cost value of the shortest path between two (or multiple vertexes as targets). 
3. with Graph_GUI to visulazie it on the screen.

Example:
-PIC-

The project is build from the following classes:

## NodeV:
(implemnts node_data iterface)

This interface represents the set of operations applicable on a 
 node (vertex) in a (directional) weighted graph.
 
This calss will vreates the vertexes of the graph, eatch vertex will contain the follwing meta-data and a uniq ID:

my_id >> the uniq ID of the vertex.

point >> will be an Point 3D object that will hold the cordinates.

weight >> the qwight of the vertex

tag >> will be used to detarmin if  the graph is strogly connected / path from one vertex to the other.

## Edge:
(implemnts edge_data iterface)

This interface represents the set of operations applicable on a 
directional edge(src,dest) in a (directional) weighted graph.

this class will creates the edges of the graph, each edge will contain the followig meta-data: 

node_data src   >> the source vertex of the edge

node_data dest  >> the destenation vertex of the edge

double w        >> the "cost" of taking the travel on the edge.

int tag         >> 
