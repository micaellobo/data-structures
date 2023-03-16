package com.example.graph;

import com.example.exceptions.*;

import java.util.Iterator;

public class DemoNetworkDirected {

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

    static <T> void livro() {
        NetworkDirected<String> rede = new NetworkDirected<>(9);

        try {
            rede.addVertex("A");
            rede.addVertex("B");
            rede.addVertex("C");
            rede.addVertex("D");
            rede.addVertex("E");
            rede.addVertex("F");
            rede.addVertex("G");
            rede.addVertex("H");
            rede.addVertex("I");
            rede.addVertex("J");

            rede.addEdge("A", "D", 5);
            rede.addEdge("A", "E", 4);
            rede.addEdge("A", "B", 2);
            rede.addEdge("B", "E", 1);
            rede.addEdge("C", "B", 3);
            rede.addEdge("D", "G", 2);
            rede.addEdge("E", "F", 3);
            rede.addEdge("E", "H", 6);
            rede.addEdge("F", "C", 4);
            rede.addEdge("F", "H", 3);
            rede.addEdge("G", "H", 1);
            rede.addEdge("H", "I", 1);
            rede.addEdge("I", "F", 1);

//            try {
//                rede.removeVertex("A");
//            } catch (EmptyCollectionException ex) {
//                System.out.println(ex.getMessage());
//            }
//            System.out.println(printUndiGraph2D(rede));
//
//            System.out.println("BFS");
//            for (Iterator iterator = rede.iteratorBFS("E"); iterator.hasNext();) {
//                System.out.print(iterator.next() + " ");
//            }
//            System.out.println("\n\nDFS");
//            for (Iterator iterator = rede.iteratorDFS("E"); iterator.hasNext();) {
//                System.out.print(iterator.next() + " ");
//            }
            System.out.println("\n\nShortest");
            for (Iterator<String> iterator = rede.iteratorShortestPath("A", "J"); iterator.hasNext(); )
                System.out.print(iterator.next() + " ");

            System.out.println("\n\nNumberEdges: " + rede.numberOfEdges);

            System.out.println("NumberVertices: " + rede.numberOfVertices);
        } catch (ElementNotFoundException | VertexConnectionExeception | ElementAlreadyExistsException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        livro();
    }

}
