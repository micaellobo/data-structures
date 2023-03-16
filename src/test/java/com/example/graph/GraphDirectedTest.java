package com.example.graph;

import com.example.exceptions.*;
import org.junit.jupiter.api.*;

import java.util.Iterator;
import java.util.logging.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Graph - https://gyazo.com/96a0f8542077b90d58f8ba874a9ed531
 * <p>
 * </p>
 * AdjList - https://gyazo.com/ae77c552720c92dda1a94b91e5d3a4eb
 *
 * @author Micael
 */
public class GraphDirectedTest {

    NetworkDirected<String> graph;
    NetworkUndirected<Integer> graphBrag;

    @BeforeEach
    void setupDirectedGraphBrag() {

        graphBrag = new NetworkUndirected<>(10);

        try {
            graphBrag.addVertex(0);
            graphBrag.addVertex(1);
            graphBrag.addVertex(2);
            graphBrag.addVertex(3);
            graphBrag.addVertex(4);
            graphBrag.addVertex(5);
            graphBrag.addVertex(6);
            graphBrag.addVertex(7);
            graphBrag.addVertex(8);
            graphBrag.addVertex(9);

            graphBrag.addEdge(0, 1, 6);
            graphBrag.addEdge(0, 2, 10);
            graphBrag.addEdge(1, 4, 14);
            graphBrag.addEdge(1, 2, 12);
            graphBrag.addEdge(1, 3, 11);
            graphBrag.addEdge(2, 3, 12);
            graphBrag.addEdge(2, 6, 8);
            graphBrag.addEdge(3, 6, 3);
            graphBrag.addEdge(3, 5, 6);
            graphBrag.addEdge(6, 8, 16);
            graphBrag.addEdge(5, 4, 4);
            graphBrag.addEdge(5, 8, 12);
            graphBrag.addEdge(4, 8, 6);
            graphBrag.addEdge(7, 2, 16);
            graphBrag.addEdge(7, 9, 8);
            graphBrag.addEdge(9, 6, 6);
            graphBrag.addEdge(9, 8, 13);

        } catch (ElementAlreadyExistsException | ElementNotFoundException | VertexConnectionExeception ex) {
            Logger.getLogger(GraphDirectedTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @BeforeEach
    void setupDirectedGraph() {
        graph = new NetworkDirected<>(18);
        try {
            graph.addVertex("0");
            graph.addVertex("1");
            graph.addVertex("2");
            graph.addVertex("3");
            graph.addVertex("4");
            graph.addVertex("5");
            graph.addVertex("6");
            graph.addVertex("7");
            graph.addVertex("8");
            graph.addVertex("9");
            graph.addVertex("10");
            graph.addVertex("11");
            graph.addVertex("12");
            graph.addVertex("13");
            graph.addVertex("14");
            graph.addVertex("15");
            graph.addVertex("16");
            graph.addVertex("17");

            graph.addEdge("0", "1", 3);
            graph.addEdge("0", "2", 8);
            graph.addEdge("0", "4", 9);
            graph.addEdge("0", "7", 5);
            graph.addEdge("2", "1", 9);
            graph.addEdge("2", "6", 1);
            graph.addEdge("3", "2", 7);
            graph.addEdge("3", "10", 4);
            graph.addEdge("4", "0", 5);
            graph.addEdge("4", "5", 9);
            graph.addEdge("5", "1", 3);
            graph.addEdge("5", "2", 2);
            graph.addEdge("6", "3", 3);
            graph.addEdge("6", "5", 7);
            graph.addEdge("6", "9", 3);
            graph.addEdge("6", "10", 6);
            graph.addEdge("7", "8", 6);
            graph.addEdge("7", "11", 1);
            graph.addEdge("7", "14", 7);
            graph.addEdge("8", "4", 4);
            graph.addEdge("8", "9", 7);
            graph.addEdge("8", "11", 8);
            graph.addEdge("9", "10", 2);
            graph.addEdge("9", "12", 1);
            graph.addEdge("10", "3", 3);
            graph.addEdge("11", "14", 4);
            graph.addEdge("12", "11", 9);
            graph.addEdge("13", "16", 4);
            graph.addEdge("13", "17", 4);
            graph.addEdge("14", "15", 5);
            graph.addEdge("15", "11", 5);
            graph.addEdge("15", "12", 7);
            graph.addEdge("15", "14", 8);
            graph.addEdge("16", "15", 8);
            graph.addEdge("16", "17", 9);
            graph.addEdge("17", "13", 8);

        } catch (ElementAlreadyExistsException | ElementNotFoundException | VertexConnectionExeception ex) {
            Logger.getLogger(GraphDirectedTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * https://gyazo.com/b5581ca130a112f6ff981f78b14ca38c
     */
    @Test
    void dijstra_0() {

        String[] expected = {"0", "7", "11", "14", "15"};
        String[] real = new String[expected.length];
        double expectedPathLenght = 15;

        int i = 0;

        for (Iterator<String> iterator = graph.iteratorShortestPath("0", "15"); iterator.hasNext(); ) {
            real[i] = iterator.next();
            i++;
        }
        assertArrayEquals(expected, real);
        assertEquals(expectedPathLenght, graph.shortestPathWeight("0", "15"));

    }

    /**
     * https://gyazo.com/b5581ca130a112f6ff981f78b14ca38c
     */
    @Test
    void dijstra_1() {

        String[] expectedPath = {};
        String[] realPath = new String[expectedPath.length];
        double expectedPathLenght = Double.POSITIVE_INFINITY;

        int i = 0;

        for (Iterator<String> iterator = graph.iteratorShortestPath("0", "16"); iterator.hasNext(); ) {
            realPath[i] = iterator.next();
            i++;
        }
        assertArrayEquals(expectedPath, realPath);
        assertEquals(expectedPathLenght, graph.shortestPathWeight("0", "16"));
    }

    /**
     * https://gyazo.com/b5581ca130a112f6ff981f78b14ca38c
     */
    @Test
    void dijstra_2() {

        String[] expected = {"0", "2", "6", "5"};
        String[] real = new String[expected.length];
        double expectedPathLenght = 16;

        int i = 0;

        for (Iterator<String> iterator = graph.iteratorShortestPath("0", "5"); iterator.hasNext(); ) {
            real[i] = iterator.next();
            i++;
        }
        assertArrayEquals(expected, real);
        assertEquals(expectedPathLenght, graph.shortestPathWeight("0", "5"));
    }

    /**
     * https://gyazo.com/b5581ca130a112f6ff981f78b14ca38c
     */
    @Test
    void dijstra_3() {

        String[] expected = {"0", "4"};
        String[] real = new String[expected.length];
        double expectedPathLenght = 9;

        int i = 0;

        for (Iterator<String> iterator = graph.iteratorShortestPath("0", "4"); iterator.hasNext(); ) {
            real[i] = iterator.next();
            i++;
        }
        assertArrayEquals(expected, real);
        assertEquals(expectedPathLenght, graph.shortestPathWeight("0", "4"));
    }

    /**
     * https://gyazo.com/bf3e3fef9882d4350b48f2dbcefcc1b0
     */
    @Test
    void dijstra_4() {

        String[] expected = {};
        String[] real = new String[expected.length];
        double expectedPathLenght = Double.POSITIVE_INFINITY;

        int i = 0;

        for (Iterator<String> iterator = graph.iteratorShortestPath("1", "1"); iterator.hasNext(); ) {
            real[i] = iterator.next();
            i++;
        }
        assertArrayEquals(expected, real);
        assertEquals(expectedPathLenght, graph.shortestPathWeight("1", "1"));
    }

    /**
     * https://gyazo.com/96c31da4a9b12f2f64c1d0f5ed95b016
     */
    @Test
    void dijstra_5() {

        String[] expected = {"7", "8", "4", "5", "2", "6"};
        String[] real = new String[expected.length];
        double expectedPathLenght = 22;

        int i = 0;

        for (Iterator<String> iterator = graph.iteratorShortestPath("7", "6"); iterator.hasNext(); ) {
            real[i] = iterator.next();
            i++;
        }
        assertArrayEquals(expected, real);
        assertEquals(expectedPathLenght, graph.shortestPathWeight("7", "6"));
    }

    /**
     * https://gyazo.com/96c31da4a9b12f2f64c1d0f5ed95b016
     */
    @Test
    void dijstra_6() {

        String[] expected = {"7", "11", "14", "15"};
        String[] real = new String[expected.length];
        double expectedPathLenght = 10;

        int i = 0;

        for (Iterator<String> iterator = graph.iteratorShortestPath("7", "15"); iterator.hasNext(); ) {
            real[i] = iterator.next();
            i++;
        }
        assertArrayEquals(expected, real);
        assertEquals(expectedPathLenght, graph.shortestPathWeight("7", "15"));
    }

    /**
     * https://gyazo.com/96c31da4a9b12f2f64c1d0f5ed95b016
     */
    @Test
    void dijstra_7() {

        String[] expected = {"7", "8", "9", "10"};
        String[] real = new String[expected.length];
        double expectedPathLenght = 15;

        int i = 0;

        for (Iterator<String> iterator = graph.iteratorShortestPath("7", "10"); iterator.hasNext(); ) {
            real[i] = iterator.next();
            i++;
        }
        assertArrayEquals(expected, real);
        assertEquals(expectedPathLenght, graph.shortestPathWeight("7", "10"));
    }

    /**
     * https://gyazo.com/96c31da4a9b12f2f64c1d0f5ed95b016
     * <p>
     * Não existe algum dos vertices
     */
    @Test
    void dijstra_8() {

        String[] expected = {};
        String[] real = new String[expected.length];
        double expectedPathLenght = Double.POSITIVE_INFINITY;

        int i = 0;

        for (Iterator<String> iterator = graph.iteratorShortestPath("-10", "30"); iterator.hasNext(); ) {
            real[i] = iterator.next();
            i++;
        }
        assertArrayEquals(expected, real);
        assertEquals(expectedPathLenght, graph.shortestPathWeight("-10", "30"));
    }

    /**
     * https://gyazo.com/96c31da4a9b12f2f64c1d0f5ed95b016
     * <p>
     * Não existe algum dos vertices
     */
    @Test
    void dijstra_9() {

        String[] expected = {};
        String[] real = new String[expected.length];
        double expectedPathLenght = Double.POSITIVE_INFINITY;

        int i = 0;

        for (Iterator<String> iterator = graph.iteratorShortestPath("7", "20"); iterator.hasNext(); ) {
            real[i] = iterator.next();
            i++;
        }
        assertArrayEquals(expected, real);
        assertEquals(expectedPathLenght, graph.shortestPathWeight("7", "20"));
    }

    /**
     * https://gyazo.com/96c31da4a9b12f2f64c1d0f5ed95b016
     * <p>
     * Não existe algum dos vertices
     */
    @Test
    void dijstra_10() {

        String[] expected = {};
        String[] real = new String[expected.length];
        double expectedPathLenght = Double.POSITIVE_INFINITY;

        int i = 0;

        for (Iterator<String> iterator = graph.iteratorShortestPath("20", "7"); iterator.hasNext(); ) {
            real[i] = iterator.next();
            i++;
        }
        assertArrayEquals(expected, real);
        assertEquals(expectedPathLenght, graph.shortestPathWeight("20", "7"));
    }

    /**
     * https://gyazo.com/8606cefd154a1790fb0d996c77d7534a
     * <p>
     */
    @Test
    void dijstra_11() {

        String[] expected = {"3", "2", "6", "9", "12", "11", "14", "15"};
        String[] real = new String[expected.length];
        double expectedPathLenght = 30;

        int i = 0;

        for (Iterator<String> iterator = graph.iteratorShortestPath("3", "15"); iterator.hasNext(); ) {
            real[i] = iterator.next();
            i++;
        }
        assertArrayEquals(expected, real);
        assertEquals(expectedPathLenght, graph.shortestPathWeight("3", "15"));
    }

    /**
     * https://gyazo.com/8606cefd154a1790fb0d996c77d7534a
     */
    @Test
    void dijstra_12() {

        String[] expected = {"3", "2", "6", "9", "12", "11", "14"};
        String[] real = new String[expected.length];
        double expectedPathLenght = 25;

        int i = 0;

        for (Iterator<String> iterator = graph.iteratorShortestPath("3", "14"); iterator.hasNext(); ) {
            real[i] = iterator.next();
            i++;
        }
        assertArrayEquals(expected, real);
        assertEquals(expectedPathLenght, graph.shortestPathWeight("3", "14"));
    }

    /**
     * https://gyazo.com/d7fbc7986eaa629389d74f2822ff62fa
     */
    @Test
    void dijstra_13() {

        int[] expected = {0, 1, 4, 8};
        int[] real = new int[expected.length];
        double expectedPathLenght = 26;

        int i = 0;

        for (Iterator<Integer> iterator = graphBrag.iteratorShortestPath(0, 8); iterator.hasNext(); ) {
            real[i] = iterator.next();
            i++;
        }
        assertArrayEquals(expected, real);
        assertEquals(expectedPathLenght, graphBrag.shortestPathWeight(0, 8));
    }

    /**
     * https://gyazo.com/d6e3ac0afd7d297a1cd3bd3035f70e7d
     */
    @Test
    void dijstra_14() {

        int[] expected = {0, 2, 6, 9};
        int[] real = new int[expected.length];
        double expectedPathLenght = 24;

        int i = 0;

        for (Iterator<Integer> iterator = graphBrag.iteratorShortestPath(0, 9); iterator.hasNext(); ) {
            real[i] = iterator.next();
            i++;
        }
        assertArrayEquals(expected, real);
        assertEquals(expectedPathLenght, graphBrag.shortestPathWeight(0, 9));
    }

    /**
     * https://gyazo.com/d6e3ac0afd7d297a1cd3bd3035f70e7d
     */
    @Test
    void dijstra_15() {

        int[] expected = {8, 4, 5, 3};
        int[] real = new int[expected.length];
        double expectedPathLenght = 16;

        int i = 0;

        for (Iterator<Integer> iterator = graphBrag.iteratorShortestPath(8, 3); iterator.hasNext(); ) {
            real[i] = iterator.next();
            i++;
        }
        assertArrayEquals(expected, real);
        assertEquals(expectedPathLenght, graphBrag.shortestPathWeight(8, 3));
    }

    /**
     * https://gyazo.com/c726cae78f07f58d3488f5b1a02ee07f
     */
    @Test
    void dijstra_16() {

        int[] expected = {5, 3, 6, 9, 7};
        int[] real = new int[expected.length];
        double expectedPathLenght = 23;

        int i = 0;

        for (Iterator<Integer> iterator = graphBrag.iteratorShortestPath(5, 7); iterator.hasNext(); ) {
            real[i] = iterator.next();
            i++;
        }
        assertArrayEquals(expected, real);
        assertEquals(expectedPathLenght, graphBrag.shortestPathWeight(5, 7));
    }

}
