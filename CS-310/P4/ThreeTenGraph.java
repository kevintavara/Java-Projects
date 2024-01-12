import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.DirectedGraph;

import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.graph.util.EdgeType;

import org.apache.commons.collections15.Factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import java.util.HashMap; //or use ThreeTenMap!
import java.util.HashSet; //or use ThreeTenSet!

import java.util.NoSuchElementException;

/**
 * This class implements a ThreeTenGraph using a HashMap.
 * 
 * @author Kevin Tavara
 */
class ThreeTenGraph implements Graph<ThreeTenNode, ThreeTenEdge>, DirectedGraph<ThreeTenNode, ThreeTenEdge> {

	/**
	 * Storage used to store the map.
	 */
	Map<ThreeTenNode, Map<ThreeTenNode, ThreeTenEdge>> storage;

	/**
	 * Creates a new graph. Initializing all appropriate instance variables.
	 */
	public ThreeTenGraph() {
		storage = new HashMap<ThreeTenNode, Map<ThreeTenNode, ThreeTenEdge>>();
	}

	/**
	 * Returns all edges in this graph.
	 * 
	 * @return Collection of all edges in this graph
	 */
	public Collection<ThreeTenEdge> getEdges() {
		HashSet<ThreeTenEdge> edges = new HashSet<ThreeTenEdge>();
		for (ThreeTenNode vertex : this.getVertices()) {
			for (ThreeTenNode neighbor : storage.get(vertex).keySet()) {
				ThreeTenEdge edge = storage.get(vertex).get(neighbor);
				edges.add(edge);
			}
		}
		return edges;
	}

	/**
	 * Returns all vertices in this graph.
	 * 
	 * @return Collection of all vertices in this graph
	 */
	public Collection<ThreeTenNode> getVertices() {
		return this.storage.keySet();
	}

	/**
	 * Returns the number of edges in this graph.
	 * 
	 * @return the number of edges in this graph
	 */
	public int getEdgeCount() {
		return this.getEdges().size();
	}

	/**
	 * Returns the number of vertices in this graph.
	 * 
	 * @return the number of vertices in this graph
	 */
	public int getVertexCount() {
		return this.getVertices().size();
	}

	/**
	 * If directedEdge is a directed edge in this graph, returns the source.
	 * 
	 * @param directedEdge edge 
	 * @return source of directedEdge
	 */
	public ThreeTenNode getSource(ThreeTenEdge directedEdge) {
		if (getEdgeType(directedEdge) == EdgeType.DIRECTED) {
			for (ThreeTenNode vertex : this.getVertices()) {
				for (ThreeTenNode neighbor : storage.get(vertex).keySet()) {
					ThreeTenEdge edge = storage.get(vertex).get(neighbor);
					if (edge.equals(directedEdge))
						return vertex;
				}
			}
		}
		return null;
	}

	/**
	 * If directedEdge is a directed edge in this graph, returns the destination.
	 * 
	 * @param directedEdge edge
	 * @return the destination of directedEdge
	 */
	public ThreeTenNode getDest(ThreeTenEdge directedEdge) {
		if (getEdgeType(directedEdge) == EdgeType.DIRECTED) {
			for (ThreeTenNode vertex : this.getVertices()) {
				for (ThreeTenNode neighbor : storage.get(vertex).keySet()) {
					ThreeTenEdge edge = storage.get(vertex).get(neighbor);
					if (edge.equals(directedEdge))
						return neighbor;
				}
			}
		}
		return null;
	}

	/**
	 * Returns a Collection of the predecessors of vertex in this graph.
	 * 
	 * @param vertex the vertex whose predecessors are to be returned
	 * @return Collection view of the predecessors of vertex in this graph
	 */
	public Collection<ThreeTenNode> getPredecessors(ThreeTenNode vertex) {
		HashSet<ThreeTenNode> predecessors = new HashSet<ThreeTenNode>();
		for (ThreeTenNode u : this.getVertices()) {
			for (ThreeTenNode v : storage.get(u).keySet()) {
				if (v.equals(vertex))
					predecessors.add(u);
			}
		}
		return predecessors;
	}

	/**
	 * Returns a Collection of the successors of vertex in this graph.
	 * 
	 * @param vertex the vertex whose predecessors are to be returned
	 * @return Collection view of the successors of vertex in this graph
	 */
	public Collection<ThreeTenNode> getSuccessors(ThreeTenNode vertex) {
		HashSet<ThreeTenNode> successors = new HashSet<ThreeTenNode>();
		for (ThreeTenNode u : this.getVertices()) {
			for (ThreeTenNode v : storage.get(u).keySet()) {
				if (u.equals(vertex))
					successors.add(v);
			}
		}
		return successors;
	}

	/**
	 * Returns a Collection of the incoming edges to vertex in this graph.
	 * 
	 * @param vertex whose incoming edges are to be returned
	 * @return Collection of the incoming edges to vertex in this graph
	 */
	public Collection<ThreeTenEdge> getInEdges(ThreeTenNode vertex) {
		HashSet<ThreeTenEdge> inEdges = new HashSet<ThreeTenEdge>();
		for (ThreeTenNode u : this.getVertices()) {
			for (ThreeTenNode v : storage.get(u).keySet()) {
				if (v.equals(vertex))
					inEdges.add(storage.get(u).get(v));
			}
		}
		return inEdges;
	}

	/**
	 * Returns a Collection of the outgoing edges to vertex in this graph.
	 * 
	 * @param vertex whose outgoing edges are to be returned
	 * @return Collection of the outgoing edges to vertex in this graph
	 */
	public Collection<ThreeTenEdge> getOutEdges(ThreeTenNode vertex) {
		HashSet<ThreeTenEdge> outEdges = new HashSet<ThreeTenEdge>();
		for (ThreeTenNode u : this.getVertices()) {
			for (ThreeTenNode v : storage.get(u).keySet()) {
				if (u.equals(vertex))
					outEdges.add(storage.get(u).get(v));
			}
		}
		return outEdges;
	}

	/**
	 * Returns an edge that connects v1 to v2.
	 * 
	 * @param v1 vertex
	 * @param v2 vertex
	 * @return an edge that connects v1 to v2
	 */
	public ThreeTenEdge findEdge(ThreeTenNode v1, ThreeTenNode v2) {
		for (ThreeTenNode u : this.getVertices()) {
			for (ThreeTenNode v : storage.get(u).keySet()) {
				if (u.equals(v1) && v.equals(v2))
					return storage.get(u).get(v);
			}
		}
		return null;
	}

	/**
	 * Adds edge e between vertex v1 v2 to this graph.
	 * 
	 * @param e  the edge to be added
	 * @param v1 the first vertex to be connected
	 * @param v2 the second vertex to be connected
	 * @return true if the add is successful, false otherwise
	 */
	public boolean addEdge(ThreeTenEdge e, ThreeTenNode v1, ThreeTenNode v2) {
		if (storage.get(v1) != null && storage.get(v2) != null) {
			storage.get(v1).put(v2, e);
			return true;
		}
		return false;
	}

	/**
	 * Adds vertex to this graph.
	 * 
	 * @param vertex the vertex to add
	 * @return true if the add is successful, and false otherwise
	 * @throws IllegalArgumentException if vertex is null
	 */
	public boolean addVertex(ThreeTenNode vertex) {
		if (vertex == null)
			throw new IllegalArgumentException("Vertex cannot be null");
		if (storage.get(vertex) == null) {
			storage.put(vertex, new HashMap<ThreeTenNode, ThreeTenEdge>());
			return true;
		}
		return false;
	}

	/**
	 * Removes edge from this graph.
	 * 
	 * @param edge the edge to remove
	 * @return true if the removal is successful, false otherwise
	 */
	public boolean removeEdge(ThreeTenEdge edge) {
		for (ThreeTenNode u : this.getVertices()) {
			for (ThreeTenNode v : storage.get(u).keySet()) {
				ThreeTenEdge e = storage.get(u).get(v);
				if (edge.equals(e)) {
					storage.get(u).remove(v);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Removes vertex from this graph.
	 * 
	 * @param vertex the vertex to remove
	 * @return true if the removal is successful, false otherwise
	 */
	public boolean removeVertex(ThreeTenNode vertex) {
		if (vertex == null || storage.get(vertex) == null)
			return false;
		storage.remove(vertex);
		for (ThreeTenNode u : this.getVertices()) {
			for (ThreeTenNode v : storage.get(u).keySet()) {
				if (v.equals(vertex))
					storage.get(u).remove(v);
			}
		}
		return true;
	}

	// --------------------------------------------------------
	// testing code goes here... edit this as much as you want!
	// --------------------------------------------------------

	/**
	 * Method not used turns to string.
	 * 
	 * @return string
	 */
	public String toString() {
		return null;
	}

	/**
	 * Method used to test ThreeTenGraph.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		// constructs a graph

		ThreeTenGraph graph1 = new ThreeTenGraph();
		for (int i = 0; i < 3; i++) {
			graph1.addVertex(new ThreeTenNode("" + i));
		}
		for (ThreeTenNode n : graph1.getVertices()) {
			graph1.addEdge(new ThreeTenEdge((int) (Math.random() * 100)), n, n);
		}

		if (graph1.getVertexCount() == 3 && graph1.getEdgeCount() == 3) {
			System.out.println("Yay 1");
		}

		// create a set of nodes and edges to test with
		ThreeTenNode[] nodes = { new ThreeTenNode("A"), new ThreeTenNode("B"), new ThreeTenNode("X"), new ThreeTenNode("7"), new ThreeTenNode("M"), new ThreeTenNode("D"), new ThreeTenNode("33"), new ThreeTenNode("Cat"), new ThreeTenNode("2"), new ThreeTenNode("-1") };

		ThreeTenEdge[] edges = { new ThreeTenEdge(10000000), new ThreeTenEdge(4), new ThreeTenEdge(Integer.MAX_VALUE), new ThreeTenEdge(Integer.MIN_VALUE), new ThreeTenEdge(500), new ThreeTenEdge(600000) };

		// constructs a graph
		ThreeTenGraph graph2 = new ThreeTenGraph();
		for (ThreeTenNode n : nodes) {
			graph2.addVertex(n);
		}
		graph2.addEdge(edges[0], nodes[0], nodes[1]);
		graph2.addEdge(edges[1], nodes[1], nodes[2]);
		graph2.addEdge(edges[2], nodes[3], nodes[2]);
		graph2.addEdge(edges[3], nodes[6], nodes[7]);
		graph2.addEdge(edges[4], nodes[8], nodes[9]);
		graph2.addEdge(edges[5], nodes[9], nodes[0]);

		if (graph2.containsVertex(new ThreeTenNode("A")) && graph2.containsEdge(new ThreeTenEdge(10000000))) {
			System.out.println("Yay 2");
		}

		// lot more testing here...

		// If your graph "looks funny" you probably want to check:
		// getVertexCount(), getVertices(), getInEdges(vertex),
		// and getIncidentVertices(incomingEdge) first. These are
		// used by the layout class.
	}

	// ********************************************************************************
	// YOU MAY, BUT DON'T NEED TO EDIT THINGS IN THIS SECTION
	// This is a good place to look for "optimizing" your code to be faster.
	// ********************************************************************************

	/**
	 * Returns true if this graph's vertex collection contains vertex.
	 * 
	 * @param vertex the vertex whose presence is being queried
	 * @return true iff this graph contains a vertex vertex
	 */
	public boolean containsVertex(ThreeTenNode vertex) {
		return getVertices().contains(vertex);
	}

	/**
	 * Returns true if this graph's edge collection contains edge.
	 * 
	 * @param edge the edge whose presence is being queried
	 * @return true iff this graph contains an edge edge
	 */
	public boolean containsEdge(ThreeTenEdge edge) {
		for (ThreeTenEdge e : this.getEdges()) {
			if (e.equals(edge))
				return true;
		}
		return false;
	}

	/**
	 * Returns true if vertex and edge are incident to each other.
	 * 
	 * @param vertex to check
	 * @param edge to check
	 * @return true if vertex and edge are incident to each other
	 */
	public boolean isIncident(ThreeTenNode vertex, ThreeTenEdge edge) {
		return getIncidentEdges(vertex).contains(edge);
	}

	/**
	 * Returns true if v1 and v2 share an incident edge.
	 * 
	 * @param v1 the first vertex to test
	 * @param v2 the second vertex to test
	 * @return true if v1 and v2 share an incident edge
	 */
	public boolean isNeighbor(ThreeTenNode v1, ThreeTenNode v2) {
		return getNeighbors(v1).contains(v2);
	}

	/**
	 * Returns the collection of vertices in this graph that are connected to edge.
	 * 
	 * @param edge the edge whose incident vertices are to be returned
	 * @return the collection of vertices which are connected to edge
	 */
	public Collection<ThreeTenNode> getIncidentVertices(ThreeTenEdge edge) {
		if (!containsEdge(edge))
			return null;
		ArrayList<ThreeTenNode> nodes = new ArrayList<>();
		nodes.add(getSource(edge));
		nodes.add(getDest(edge));
		return nodes;
	}

	/**
	 * Returns the collection of vertices which are connected to vertex.
	 * 
	 * @param vertex the vertex whose neighbors are to be returned
	 * @return the collection of vertices which are connected to vertex
	 */
	public Collection<ThreeTenNode> getNeighbors(ThreeTenNode vertex) {
		if (!containsVertex(vertex))
			return null;
		ArrayList<ThreeTenNode> nodes = new ArrayList<>();
		nodes.addAll(getPredecessors(vertex));
		for (ThreeTenNode n : getSuccessors(vertex)) {
			if (!nodes.contains(n)) {
				nodes.add(n);
			}
		}
		return nodes;
	}

	/**
	 * Returns the collection of edges in this graph which are connected to vertex.
	 * 
	 * @param vertex the vertex whose incident edges are to be returned
	 * @return the collection of edges which are connected to vertex
	 */
	public Collection<ThreeTenEdge> getIncidentEdges(ThreeTenNode vertex) {
		if (!containsVertex(vertex))
			return null;
		ArrayList<ThreeTenEdge> edges = new ArrayList<>();
		edges.addAll(getInEdges(vertex));
		edges.addAll(getOutEdges(vertex));
		return edges;
	}

	/**
	 * Returns true if v1 is a predecessor of v2 in this graph.
	 * 
	 * @param v1 the first vertex to be queried
	 * @param v2 the second vertex to be queried
	 * @return true if v1 is a predecessor of v2, and false otherwise.
	 */
	public boolean isPredecessor(ThreeTenNode v1, ThreeTenNode v2) {
		return getPredecessors(v1).contains(v2);
	}

	/**
	 * Returns true if v1 is a successor of v2 in this graph.
	 * 
	 * @param v1 the first vertex to be queried
	 * @param v2 the second vertex to be queried
	 * @return true if v1 is a successor of v2, and false otherwise.
	 */
	public boolean isSuccessor(ThreeTenNode v1, ThreeTenNode v2) {
		return getSuccessors(v1).contains(v2);
	}

	/**
	 * Returns the number of edges incident to vertex.
	 * 
	 * @param vertex the vertex whose degree is to be returned
	 * @return the degree of this node
	 */
	public int degree(ThreeTenNode vertex) {
		return getIncidentEdges(vertex).size();
	}

	/**
	 * Returns the number of vertices that are adjacent to vertex.
	 * 
	 * @param vertex the vertex whose neighbor count is to be returned
	 * @return the number of neighboring vertices
	 */
	public int getNeighborCount(ThreeTenNode vertex) {
		return getNeighbors(vertex).size();
	}

	/**
	 * Returns the number of incoming edges incident to vertex.
	 * 
	 * @param vertex the vertex whose in degree is to be calculated
	 * @return the number of incoming edges incident to vertex
	 */
	public int inDegree(ThreeTenNode vertex) {
		return getInEdges(vertex).size();
	}

	/**
	 * Returns the number of outgoing edges incident to vertex.
	 * 
	 * @param vertex the vertex whose outdegree is to be calculated
	 * @return the number of outgoing edges incident to vertex
	 */
	public int outDegree(ThreeTenNode vertex) {
		return getOutEdges(vertex).size();
	}

	/**
	 * Returns the number of predecessors that vertex has in this graph.
	 * 
	 * @param vertex the vertex whose predecessor count is to be returned
	 * @return the number of predecessors that vertex has in this graph
	 */
	public int getPredecessorCount(ThreeTenNode vertex) {
		return getPredecessors(vertex).size();
	}

	/**
	 * Returns the number of successors that vertex has in this graph.
	 * 
	 * @param vertex the vertex whose successor count is to be returned
	 * @return the number of successors that vertex has in this graph
	 */
	public int getSuccessorCount(ThreeTenNode vertex) {
		return getSuccessors(vertex).size();
	}

	/**
	 * Returns the vertex at the other end of edge from vertex.
	 * 
	 * @param vertex the vertex to be queried
	 * @param edge   the edge to be queried
	 * @return the vertex at the other end of edge from vertex
	 */
	public ThreeTenNode getOpposite(ThreeTenNode vertex, ThreeTenEdge edge) {
		Pair<ThreeTenNode> p = getEndpoints(edge);
		if (p.getFirst().equals(vertex))
			return p.getSecond();
		else 
			return p.getFirst();
	}

	/**
	 * Returns all edges that connects v1 to v2.
	 * 
	 * @param v1 vertex
	 * @param v2 vertex
	 * @return a collection containing all edges that connect v1 to v2
	 */
	public Collection<ThreeTenEdge> findEdgeSet(ThreeTenNode v1, ThreeTenNode v2) {
		ThreeTenEdge edge = findEdge(v1, v2);
		if (edge == null)
			return null;

		ArrayList<ThreeTenEdge> ret = new ArrayList<>();
		ret.add(edge);
		return ret;

	}

	/**
	 * Returns true if vertex is the source of edge.
	 * 
	 * @param vertex the vertex to be queried
	 * @param edge   the edge to be queried
	 * @return true iff vertex is the source of edge
	 */
	public boolean isSource(ThreeTenNode vertex, ThreeTenEdge edge) {
		return getSource(edge).equals(vertex);
	}

	/**
	 * Returns true if vertex is the destination of edge.
	 * 
	 * @param vertex the vertex to be queried
	 * @param edge   the edge to be queried
	 * @return true iff vertex is the destination of edge
	 */
	public boolean isDest(ThreeTenNode vertex, ThreeTenEdge edge) {
		return getDest(edge).equals(vertex);
	}

	/**
	 * Adds edge e to this graph such that it connects vertex v1 to v2.
	 * 
	 * @param e        the edge to be added
	 * @param v1       the first vertex to be connected
	 * @param v2       the second vertex to be connected
	 * @param edgeType the type to be assigned to the edge
	 * @return true if the add is successful, false otherwise
	 */
	public boolean addEdge(ThreeTenEdge e, ThreeTenNode v1, ThreeTenNode v2, EdgeType edgeType) {
		// NOTE: Only undirected edges allowed

		if (edgeType != EdgeType.DIRECTED) {
			throw new IllegalArgumentException();
		}

		return addEdge(e, v1, v2);
	}

	/**
	 * Adds edge to this graph.
	 * 
	 * @param edge to add
	 * @param vertices to add edge to
	 * @return true if the add is successful, and false otherwise
	 * @throws IllegalArgumentException if edge or vertices is null
	 */
	public boolean addEdge(ThreeTenEdge edge, Collection<? extends ThreeTenNode> vertices) {
		if (edge == null || vertices == null || vertices.size() != 2) {
			return false;
		}

		ThreeTenNode[] vs = (ThreeTenNode[]) vertices.toArray();
		return addEdge(edge, vs[0], vs[1]);
	}

	/**
	 * Adds edge to this graph with type edge_type. 
	 * 
	 * @param edge to add
	 * @param vertices to add edge to
	 * @param edgeType the type of edge
	 * @return true if the add is successful, and false otherwise
	 * @throws IllegalArgumentException if edge or vertices is null
	 */
	public boolean addEdge(ThreeTenEdge edge, Collection<? extends ThreeTenNode> vertices, EdgeType edgeType) {
		if (edge == null || vertices == null || vertices.size() != 2) {
			return false;
		}

		ThreeTenNode[] vs = (ThreeTenNode[]) vertices.toArray();
		return addEdge(edge, vs[0], vs[1], edgeType);
	}

	/**
	 * Returns the number of edges of type edge_type in this graph.
	 * 
	 * @param edgeType the type of edge for which the count is to be returned
	 * @return the number of edges of type edge_type in this graph
	 */
	public int getEdgeCount(EdgeType edgeType) {
		if (edgeType != EdgeType.UNDIRECTED)
			return getEdgeCount();
		return 0;
	}

	/**
	 * Returns the collection of edges in this graph which are of type edge_type.
	 * 
	 * @param edgeType the type of edges to be returned
	 * @return the collection of edges which are of type edge_type
	 */
	public Collection<ThreeTenEdge> getEdges(EdgeType edgeType) {
		if (edgeType != EdgeType.UNDIRECTED)
			return getEdges();
		return null;
	}

	/**
	 * Returns the number of vertices that are incident to edge.
	 * 
	 * @param edge the edge whose incident vertex count is to be returned
	 * @return the number of vertices that are incident to edge.
	 */
	public int getIncidentCount(ThreeTenEdge edge) {
		return getIncidentVertices(edge).size();
	}

	/**
	 * Returns the end points of edge as a Pair.
	 * 
	 * @param edge whose end points are to be returned
	 * @return the end points 
	 */
	public Pair<ThreeTenNode> getEndpoints(ThreeTenEdge edge) {
		Object[] ends = getIncidentVertices(edge).toArray();
		return new Pair<>((ThreeTenNode) ends[0], (ThreeTenNode) ends[1]);
	}

	// ********************************************************************************
	// DO NOT EDIT ANYTHING BELOW THIS LINE (except to edit/fix the JavaDocs)
	// ********************************************************************************

	/**
	 * Returns the code Factory that creates an instance of this graph type.
	 * 
	 * @return a {@code Factory} that creates an instance of this graph type.
	 */
	public static Factory<Graph<ThreeTenNode, ThreeTenEdge>> getFactory() {
		return new Factory<Graph<ThreeTenNode, ThreeTenEdge>>() {
			public Graph<ThreeTenNode, ThreeTenEdge> create() {
				return new ThreeTenGraph();
			}
		};
	}

	/**
	 * Returns the code Factory that creates an instance of this graph type.
	 * 
	 * @return a {@code Factory} that creates an instance of this graph type.
	 */
	public static Factory<DirectedGraph<ThreeTenNode, ThreeTenEdge>> getDirectedFactory() {
		return new Factory<DirectedGraph<ThreeTenNode, ThreeTenEdge>>() {
			public DirectedGraph<ThreeTenNode, ThreeTenEdge> create() {
				return new ThreeTenGraph();
			}
		};
	}

	/**
	 * Returns the edge type of edge in this graph.
	 * 
	 * @param edge to check
	 * @return the EdgeType of edge, or null if edge has no defined type
	 */
	public EdgeType getEdgeType(ThreeTenEdge edge) {
		return EdgeType.DIRECTED;
	}

	/**
	 * Returns the default edge type for this graph.
	 * 
	 * @return the default edge type for this graph
	 */
	public EdgeType getDefaultEdgeType() {
		return EdgeType.DIRECTED;
	}
}
