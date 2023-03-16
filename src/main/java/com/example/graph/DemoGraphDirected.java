package com.example.graph;

import com.example.exceptions.*;

import java.util.Iterator;
import java.util.logging.*;

public class DemoGraphDirected {

    static <T> String printUndiGraph2D(GraphDirected<T> graph) {

        StringBuilder s = new StringBuilder();
        int i = 0;
        for (GraphVertex<T, Integer> vertice : graph.vertices) {
            if (vertice != null) {
                s.append("Index(").append(i++).append("): ").append(vertice.element).append(" -> ").append("[");
                Iterator<Integer> itr = vertice.neighbors.iterator();
                while (itr.hasNext()) {
                    s.append(itr.next());
                    if (itr.hasNext()) {
                        s.append(", ");
                    }
                }
                s.append("]\n");
            }
        }
        return s.toString();
    }

    static void livro() {
        try {
            GraphDirected<String> graph = new GraphDirected<>(9);

            graph.addVertex("A");
            graph.addVertex("B");
            graph.addVertex("C");
            graph.addVertex("D");
            graph.addVertex("E");
            graph.addVertex("F");
            graph.addVertex("G");
            graph.addVertex("H");
            graph.addVertex("I");

            graph.addEdge("A", "E");
            graph.addEdge("A", "D");
            graph.addEdge("A", "B");
            graph.addEdge("B", "E");
            graph.addEdge("C", "B");
            graph.addEdge("D", "G");
            graph.addEdge("E", "H");
            graph.addEdge("E", "F");
            graph.addEdge("F", "H");
            graph.addEdge("F", "C");
            graph.addEdge("G", "H");
            graph.addEdge("H", "I");
            graph.addEdge("I", "F");

            System.out.println(printUndiGraph2D(graph));
            System.out.println("BFS");
            for (Iterator iterator = graph.iteratorBFS("A"); iterator.hasNext(); ) {
                System.out.print(iterator.next() + " ");
            }
            System.out.println("\n\nDFS");
            for (Iterator iterator = graph.iteratorDFS("A"); iterator.hasNext(); ) {
                System.out.print(iterator.next() + " ");
            }
            System.out.println("\n\nShortest");
            for (Iterator iterator = graph.iteratorShortestPath("A", "C"); iterator.hasNext(); ) {
                System.out.print(iterator.next() + " ");
            }

            System.out.println("\n\nNumberEdges: " + graph.numberOfEdges);

            System.out.println("NumberVertices: " + graph.numberOfVertices);

        } catch (ElementNotFoundException | VertexConnectionExeception | ElementAlreadyExistsException ex) {
            Logger.getLogger(DemoGraphDirected.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static void removeTest() {
        //Grafo geredo auomaticamente pelo https://visualgo.net/en/graphds
        try {
            GraphDirected<String> graph = new GraphDirected<>(9);

            graph.addVertex("0");
            graph.addVertex("1");
            graph.addVertex("2");
            graph.addVertex("3");
            graph.addVertex("4");
            graph.addVertex("5");
            graph.addVertex("6");
            graph.addVertex("7");

            graph.addEdge("0", "1");
            graph.addEdge("0", "2");
            graph.addEdge("1", "2");
            graph.addEdge("1", "3");
            graph.addEdge("2", "3");
            graph.addEdge("2", "5");
            graph.addEdge("3", "4");
            graph.addEdge("7", "6");

            graph.removeVertex("2");

            System.out.println(printUndiGraph2D(graph));
            System.out.println("BFS");
            for (Iterator iterator = graph.iteratorBFS("0"); iterator.hasNext(); ) {
                System.out.print(iterator.next() + " ");
            }
            System.out.println("\n\nDFS");
            for (Iterator iterator = graph.iteratorDFS("0"); iterator.hasNext(); ) {
                System.out.print(iterator.next() + " ");
            }
            System.out.println("\n\nShortest");
            for (Iterator iterator = graph.iteratorShortestPath("0", "4"); iterator.hasNext(); ) {
                System.out.print(iterator.next() + " ");
            }

            System.out.println("\nNumberEdges: " + graph.numberOfEdges);
            System.out.println("NumberVertices: " + graph.numberOfVertices);

        } catch (ElementNotFoundException | VertexConnectionExeception | ElementAlreadyExistsException |
                 EmptyCollectionException ex) {
            Logger.getLogger(DemoGraphDirected.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        livro();
    }

}
