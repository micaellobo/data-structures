package com.example.interfaces;

import com.example.exceptions.*;

import java.util.Iterator;

/**
 * GraphADT defines the interface to a graph data structure.
 */
public interface GraphADT<T> {

    /**
     * Adds a vertex to this graph, associating object with vertex.
     *
     * @param vertex the vertex to be added to this graph
     */
    void addVertex(T vertex) throws ElementAlreadyExistsException;

    /**
     * Removes a single vertex with the given value from this graph.
     *
     * @param vertex the vertex to be removed from this graph
     * @throws EmptyCollectionException if the graph is empty
     * @throws ElementNotFoundException if vertx1 or vertex2 does not exist
     */
    void removeVertex(T vertex) throws EmptyCollectionException, ElementNotFoundException;

    /**
     * Inserts an edge between two vertices of this graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @throws VertexConnectionExeception
     * @throws ElementNotFoundException   if vertx1 or vertex2 does not exist
     */
    void addEdge(T vertex1, T vertex2) throws ElementNotFoundException, VertexConnectionExeception;

    /**
     * Removes an edge between two vertices of this graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @throws EmptyCollectionException if the graph is empty
     * @throws ElementNotFoundException if vertx1 or vertex2 does not exist
     */
    void removeEdge(T vertex1, T vertex2) throws EmptyCollectionException, ElementNotFoundException, VertexConnectionExeception;

    /**
     * Returns a breadth first iterator starting with the given vertex.
     *
     * @param startVertex the starting vertex
     * @return a breadth first iterator beginning at the given vertex
     */
    Iterator iteratorBFS(T startVertex);

    /**
     * Returns a depth first iterator starting with the given vertex.
     *
     * @param startVertex the starting vertex
     * @return a depth first iterator starting at the given vertex
     */
    Iterator iteratorDFS(T startVertex);

    /**
     * Returns an iterator that contains the shortest path between the two
     * vertices.
     *
     * @param startVertex  the starting vertex
     * @param targetVertex the ending vertex
     * @return an iterator that contains the shortest path between the two
     * vertices
     */
    Iterator iteratorShortestPath(T startVertex, T targetVertex);

    /**
     * Returns true if this graph is empty, false otherwise.
     *
     * @return true if this graph is empty
     */
    boolean isEmpty();

    /**
     * Returns true if this graph is connected, false otherwise.
     *
     * @return true if this graph is connected
     */
    boolean isConnected();

    /**
     * Returns the number of vertices in this graph.
     *
     * @return the integer number of vertices in this graph
     */
    int size();

    /**
     * Returns a string representation of the adjacency matrix.
     *
     * @return a string representation of the adjacency matrix
     */
    @Override
    String toString();
}
