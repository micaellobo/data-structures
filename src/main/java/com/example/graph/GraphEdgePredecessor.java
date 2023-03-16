package com.example.graph;

import java.util.Objects;

public class GraphEdgePredecessor implements Comparable<GraphEdgePredecessor> {

    protected int indexPredecessor;
    protected boolean visited;
    protected int indexVertex;
    protected double weight;

    public GraphEdgePredecessor(int indexVertex, double weight, int indexPredecessor) {
        this.visited = false;
        this.indexPredecessor = indexPredecessor;
        this.indexVertex = indexVertex;
        this.weight = weight;
    }

    public GraphEdgePredecessor(int indexVertex, double weight) {
        this(indexVertex, weight, 0);
    }

    /**
     * Compares two edges edge by the weight.
     *
     * @param graphEdgeWeightPredecessor {@link com.example.graph.GraphEdgePredecessor} Edge to compare
     * @return 1 if the current Edge has heigher weight than the given edge, 0
     * if same weight, -1 otherwise
     */
    @Override
    public int compareTo(GraphEdgePredecessor graphEdgeWeightPredecessor) {
        return Double.compare(this.weight, graphEdgeWeightPredecessor.weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GraphEdgePredecessor that)) return false;
        return getIndexVertex() == that.getIndexVertex();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIndexVertex());
    }

    public int getIndexVertex() {
        return indexVertex;
    }

    public double getWeight() {
        return weight;
    }

}
