package com.example.graph;

import com.example.exceptions.*;
import com.example.interfaces.GraphADT;

public class GraphUndirected<T> extends GraphDirected<T> implements GraphADT<T> {

    public GraphUndirected(int capacity) {
        super(capacity);
    }

    public GraphUndirected() {
        super();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) throws EmptyCollectionException, ElementNotFoundException, VertexConnectionExeception {
        super.removeEdge(vertex1, vertex2);
        this.vertices[getIndex(vertex2)].neighbors.remove(getIndex(vertex1));
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void removeVertex(T vertex) throws EmptyCollectionException, ElementNotFoundException {
        int tempEdges = this.numberOfEdges;
        super.removeVertex(vertex);
        //Because the directed graph remove edges in duplicate
        this.numberOfEdges += (tempEdges - this.numberOfEdges) / 2;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addEdge(T vertex1, T vertex2) throws ElementNotFoundException, VertexConnectionExeception {
        super.addEdge(vertex1, vertex2);
        this.vertices[getIndex(vertex2)].neighbors.add(getIndex(vertex1));
    }

}
