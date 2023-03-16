package com.example.graph;

import com.example.exceptions.*;
import com.example.interfaces.NetworkADT;


public class NetworkUndirected<T> extends NetworkDirected<T> implements NetworkADT<T> {

    public NetworkUndirected(int capacity) {
        super(capacity);
    }

    public NetworkUndirected() {
        super();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) throws EmptyCollectionException, ElementNotFoundException, VertexConnectionExeception {
        super.removeEdge(vertex1, vertex2);
        this.vertices[getIndex(vertex2)].neighbors.remove(new GraphEdge(getIndex(vertex1), DEFAULT_WEIGHT));
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void removeVertex(T vertex) throws EmptyCollectionException, ElementNotFoundException {
        int tempEdges = this.numberOfEdges;
        super.removeVertex(vertex);
        this.numberOfEdges += (tempEdges - this.numberOfEdges) / 2;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addEdge(T vertex1, T vertex2, double weight) throws ElementNotFoundException, VertexConnectionExeception {
        super.addEdge(vertex1, vertex2, weight);
        this.vertices[getIndex(vertex2)].neighbors.add(new GraphEdge(getIndex(vertex1), weight));
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addEdge(T vertex1, T vertex2) throws ElementNotFoundException, VertexConnectionExeception {
        super.addEdge(vertex1, vertex2);
        this.vertices[getIndex(vertex2)].neighbors.add(new GraphEdge(getIndex(vertex1), DEFAULT_WEIGHT));
    }

}
