package com.example.graph;

import com.example.exceptions.*;

import java.util.Iterator;

/**
 * @author Micael
 */
public class DemoNetworkUndirecered {

    static <T> String printUndiGraph2D(NetworkDirected<T> rede) {

        StringBuilder s = new StringBuilder();
        int i = 0;
        for (GraphVertex<T, GraphEdge> vertice : rede.vertices) {
            if (vertice != null) {
                s.append("Index(").append(i++).append("): ").append(vertice.element).append(" -> ").append("[");
                Iterator<GraphEdge> itr = vertice.neighbors.iterator();
                while (itr.hasNext()) {
                    GraphEdge edge = itr.next();
                    s.append("(").append(rede.vertices[edge.indexVertex].element).append(",").append(edge.weight).append(")");
                    if (itr.hasNext()) {
                        s.append(", ");
                    }
                }
                s.append("]\n");
            }
        }
        return s.toString();
    }

    static void Braganca() {

        NetworkUndirected<Integer> graph = new NetworkUndirected<>(8);

        try {
            graph.addVertex(0);
            graph.addVertex(1);
            graph.addVertex(2);
            graph.addVertex(3);
            graph.addVertex(4);
            graph.addVertex(5);
            graph.addVertex(6);
            graph.addVertex(8);

            graph.addEdge(0, 1, 6);
            graph.addEdge(0, 2, 10);
            graph.addEdge(1, 4, 14);
            graph.addEdge(1, 2, 12);
            graph.addEdge(1, 3, 11);
            graph.addEdge(2, 3, 12);
            graph.addEdge(2, 6, 8);
            graph.addEdge(3, 6, 3);
            graph.addEdge(3, 5, 6);
            graph.addEdge(6, 8, 16);
            graph.addEdge(5, 4, 4);
            graph.addEdge(5, 8, 12);
            graph.addEdge(4, 8, 6);

            printUndiGraph2D(graph);

            System.out.println("\n\nShortest");
            for (Iterator iterator = graph.iteratorShortestPath(0, 8); iterator.hasNext(); ) {
                System.out.print(iterator.next() + " ");
            }

            System.out.println("\n\nNumberEdges: " + graph.numberOfEdges);

            System.out.println("NumberVertices: " + graph.numberOfVertices);

        } catch (ElementNotFoundException | VertexConnectionExeception | ElementAlreadyExistsException ex) {
        }
    }

    public static void main(String[] args) {
        Braganca();
    }

}
