package com.example.interfaces;

import com.example.exceptions.*;

/**
 * NetworkADT defines the interface to a network.
 */
public interface NetworkADT<T> extends GraphADT<T> {

    /**
     * Inserts an edge between two vertices of this graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @param weight  the weight
     * @throws ElementNotFoundException if some element does not exist
     */
    void addEdge(T vertex1, T vertex2, double weight) throws ElementNotFoundException, VertexConnectionExeception;

    /**
     * Returns the weight of the shortest path in this network.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return the weight of the shortest path in this network
     */
    double shortestPathWeight(T vertex1, T vertex2);

}
