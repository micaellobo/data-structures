package com.example.graph;

import com.example.exceptions.*;

import java.util.Iterator;
import java.util.logging.*;

public class DemoGraphUndirected {

    static <T> String printUndiGraph2D(GraphUndirected<T> graph) {

        StringBuilder s = new StringBuilder();
        int i = 0;

        for (GraphVertex<T, Integer> vertice : graph.vertices) {
            if (vertice != null) {
                s.append("Index(").append(i++).append("): ").append(vertice.element).append(" -> ").append("[");
                Iterator<Integer> itr = vertice.neighbors.iterator();
                while (itr.hasNext()) {
                    try {
                        s.append(itr.next());
                    } catch (NullPointerException ex) {
                        Logger.getLogger(DemoGraphUndirected.class.getName()).log(Level.SEVERE, null, ex);

                    }
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

        try {
            GraphUndirected<String> graph = new GraphUndirected<>();

            graph.addVertex("A");
            graph.addVertex("B");
            graph.addVertex("C");
            graph.addVertex("D");
            graph.addVertex("E");
            graph.addVertex("F");
            graph.addVertex("G");
            graph.addVertex("H");
            graph.addVertex("I");

            graph.addEdge("A", "B");
            graph.addEdge("A", "D");
            graph.addEdge("A", "E");
            graph.addEdge("B", "C");
            graph.addEdge("B", "E");
            graph.addEdge("C", "F");
            graph.addEdge("D", "G");
            graph.addEdge("E", "F");
            graph.addEdge("G", "H");
            graph.addEdge("H", "E");
            graph.addEdge("H", "I");
            graph.addEdge("H", "F");
            graph.addEdge("I", "F");

            System.out.println(printUndiGraph2D(graph));
            System.out.println("BFS:");
            for (Iterator iterator = graph.iteratorBFS("A"); iterator.hasNext(); ) {
                System.out.print(iterator.next() + " ");
            }
            System.out.println("\n\nDFS");
            for (Iterator iterator = graph.iteratorDFS("A"); iterator.hasNext(); ) {
                System.out.print(iterator.next() + " ");
            }
            System.out.println("\n\nShortest");
            for (Iterator iterator = graph.iteratorShortestPath("A", "H"); iterator.hasNext(); ) {
                System.out.print(iterator.next() + " ");
            }

            System.out.println("NumberEdges: " + graph.numberOfEdges);

            System.out.println("NumberVertices: " + graph.numberOfVertices);

        } catch (ElementAlreadyExistsException | ElementNotFoundException | VertexConnectionExeception ex) {
            Logger.getLogger(DemoGraphUndirected.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static <T> void BFSTest() {

        try {
            GraphUndirected<String> graph = new GraphUndirected<>();

            graph.addVertex("0");
            graph.addVertex("1");
            graph.addVertex("2");
            graph.addVertex("3");
            graph.addVertex("4");
            graph.addVertex("5");
            graph.addVertex("6");

            graph.addEdge("0", "1");
            graph.addEdge("0", "2");
            graph.addEdge("1", "3");
            graph.addEdge("1", "6");
            graph.addEdge("2", "5");
            graph.addEdge("3", "4");

            System.out.println(printUndiGraph2D(graph));
            System.out.println("BFS:");
            for (Iterator iterator = graph.iteratorBFS("0"); iterator.hasNext(); ) {
                System.out.print(iterator.next() + " ");
            }
            System.out.println("\n\nDFS");
            for (Iterator iterator = graph.iteratorDFS("0"); iterator.hasNext(); ) {
                System.out.print(iterator.next() + " ");
            }

        } catch (ElementAlreadyExistsException | ElementNotFoundException | VertexConnectionExeception ex) {
            Logger.getLogger(DemoGraphUndirected.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static <T> void removeTest() {
        //Grafo geredo auomaticamente pelo https://visualgo.net/en/graphds
        try {
            GraphUndirected<String> graph = new GraphUndirected<>();

            graph.addVertex("0");
            graph.addVertex("1");
            graph.addVertex("2");
            graph.addVertex("3");
            graph.addVertex("4");
            graph.addVertex("5");
            graph.addVertex("6");

            graph.addEdge("0", "1");
            graph.addEdge("0", "2");
            graph.addEdge("1", "2");
            graph.addEdge("1", "3");
            graph.addEdge("2", "4");
            graph.addEdge("3", "4");
            graph.addEdge("4", "5");
            graph.addEdge("5", "6");

            //Extra
            graph.addEdge("2", "6");
            graph.addEdge("2", "5");
            graph.addEdge("2", "3");

            graph.removeVertex("2");

            graph.addEdge("0", "5");

            graph.removeVertex("5");
            graph.removeVertex("0");

            System.out.println(printUndiGraph2D(graph));
            System.out.println("BFS");
            for (Iterator iterator = graph.iteratorBFS("1"); iterator.hasNext(); ) {
                System.out.print(iterator.next() + " ");
            }
            System.out.println("\n\nDFS");
            for (Iterator iterator = graph.iteratorDFS("1"); iterator.hasNext(); ) {
                System.out.print(iterator.next() + " ");
            }
            System.out.println("\n\nShortest");
            for (Iterator iterator = graph.iteratorShortestPath("0", "6 "); iterator.hasNext(); ) {
                System.out.print(iterator.next() + " ");
            }

            System.out.println("\nNumberEdges: " + graph.numberOfEdges);
            System.out.println("NumberVertices: " + graph.numberOfVertices);

        } catch (ElementAlreadyExistsException | ElementNotFoundException | VertexConnectionExeception |
                 EmptyCollectionException ex) {
            Logger.getLogger(DemoGraphUndirected.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {

//        livro();
//        BFSTest();
        removeTest();
    }

}
