package com.example.graph;

public class GraphEdge implements Comparable<GraphEdge> {

    protected int indexVertex;
    protected double weight;

    public GraphEdge(int indexVertex, double weight) {
        this.indexVertex = indexVertex;
        this.weight = weight;
    }

    /**
     * Returns the 1 if the current Edge has after than the given node, 0 if
     * same position, -1 otherwise.
     *
     * @param graphWeighNode {@link com.example.graph.GraphEdge} Edge to compare
     * @return 1 if the current Edge has after than the given node, 0 if same
     * position, -1 otherwise
     */
    @Override
    public int compareTo(GraphEdge graphWeighNode) {
        return Integer.compare(this.indexVertex, graphWeighNode.indexVertex);
    }

    @Override
    public int hashCode() {
        return 7;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof final GraphEdge other)) {
            return false;
        }
        return this.indexVertex == other.indexVertex;
    }

    public int getIndexVertex() {
        return indexVertex;
    }

    public double getWeight() {
        return weight;
    }

}
