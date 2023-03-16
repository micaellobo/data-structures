package com.example.graph;

import com.example.arrayList.ArrayUnorderedList;
import com.example.arrayStack.ArrayStack;
import com.example.binaryTree.LinkedHeapMin;
import com.example.exceptions.*;
import com.example.interfaces.*;
import com.example.list.*;
import com.example.queue.LinkedQueue;

import java.util.Iterator;

/**
 * @param <T>
 * @author Micael
 */
public class NetworkDirected<T> implements NetworkADT<T> {

    protected static final int DEFAULT_CAPACITY = 100;
    protected static final int DEFAULT_WEIGHT = 1;

    protected GraphVertex<T, GraphEdge>[] vertices;
    protected int numberOfVertices;
    protected int numberOfEdges;

    public NetworkDirected(int capacity) {
        this.vertices = (GraphVertex<T, GraphEdge>[])  new GraphVertex[capacity];
        this.numberOfVertices = 0;
        this.numberOfEdges = 0;
    }

    public NetworkDirected() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addEdge(T vertex1, T vertex2, double weight) throws ElementNotFoundException, VertexConnectionExeception {
        int indexVertex1;
        int indexVertex2;

        if (weight < 0) {
            throw new VertexConnectionExeception(VertexConnectionExeception.NEGATIVE_WEIGHT);
        }

        indexVertex1 = getIndex(vertex1);
        indexVertex2 = getIndex(vertex2);

        if (!isIndexValid(indexVertex1) || !isIndexValid(indexVertex2)) {
            throw new ElementNotFoundException(ElementNotFoundException.ELEMENT_NOT_FOUND);
        }

        if (isNeighbor(indexVertex1, indexVertex2)) {
            throw new VertexConnectionExeception(VertexConnectionExeception.ALREADY_CONNECTED);
        }
        this.vertices[indexVertex1].neighbors.add(new GraphEdge(indexVertex2, weight));
        this.numberOfEdges++;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addEdge(T vertex1, T vertex2) throws ElementNotFoundException, VertexConnectionExeception {
        addEdge(vertex1, vertex2, DEFAULT_WEIGHT);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addVertex(T vertex) throws ElementAlreadyExistsException {
        if (exists(vertex)) {
            throw new ElementAlreadyExistsException(ElementAlreadyExistsException.ELEMENT_ALREADY_EXISTS);
        }
        if (this.numberOfVertices == this.vertices.length) {
            expandCapacity();
        }
        this.vertices[this.numberOfVertices++] = new GraphVertex<>(vertex);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void removeVertex(T vertex) throws EmptyCollectionException, ElementNotFoundException {

        int indexVertex;

        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }

        indexVertex = getIndex(vertex);

        if (!isIndexValid(indexVertex)) {
            throw new ElementNotFoundException(ElementNotFoundException.ELEMENT_NOT_FOUND);
        }

        //Remove connections from all vertex to the removed vertex
        for (int i = 0; i < this.numberOfVertices; i++) {
            try {
                this.vertices[i].neighbors.remove(new GraphEdge(indexVertex, DEFAULT_WEIGHT));
                this.numberOfEdges--;//Decrement number of edges
            } catch (ElementNotFoundException | EmptyCollectionException ignored) {
            }
        }

        //removes the number of edges going from removed vertex
        this.numberOfEdges -= this.vertices[indexVertex].neighbors.size();
        shiftDelete(indexVertex);
        this.numberOfVertices--;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) throws EmptyCollectionException, ElementNotFoundException, VertexConnectionExeception {

        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }

        int indexVertex1 = getIndex(vertex1);
        int indexVertex2 = getIndex(vertex2);

        if (!isIndexValid(indexVertex1) || !isIndexValid(indexVertex2)) {
            throw new ElementNotFoundException(ElementNotFoundException.ELEMENT_NOT_FOUND);
        }

        if (!NetworkDirected.this.isNeighbor(indexVertex1, indexVertex2)) {
            throw new VertexConnectionExeception(VertexConnectionExeception.NO_CONNECTION);
        }

        try {
            this.vertices[indexVertex1].neighbors.remove(new GraphEdge(indexVertex2, DEFAULT_WEIGHT));
        } catch (EmptyCollectionException ignored) {
        }
        this.numberOfEdges--;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Iterator<T> iteratorBFS(T startVertex) {
        return BFSList(startVertex).iterator();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Iterator<T> iteratorDFS(T startVertex) {

        int indexCurrent;
        boolean found;
        ArrayStack<Integer> traversalStack = new ArrayStack<>(this.numberOfVertices);
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>(this.numberOfVertices);
        boolean[] visited = new boolean[this.numberOfVertices];
        int indexVertex = getIndex(startVertex);

        if (!isIndexValid(indexVertex)) {
            return resultList.iterator();
        }

        traversalStack.push(indexVertex);
        resultList.addToRear(this.vertices[indexVertex].element);
        visited[indexVertex] = true;

        while (!traversalStack.isEmpty()) {
            try {
                indexCurrent = traversalStack.peek();
                found = false;
                for (int i = 0; (i < this.numberOfVertices) && !found; i++) {
                    if (this.isNeighbor(indexCurrent, i) && !visited[i]) {
                        traversalStack.push(i);
                        resultList.addToRear(this.vertices[i].element);
                        visited[i] = true;
                        found = true;
                    }
                }
                if (!found && !traversalStack.isEmpty()) {
                    traversalStack.pop();
                }
            } catch (EmptyCollectionException ignored) {
            }
        }
        return resultList.iterator();
    }

    /**
     * Class for the shortest path algorithm
     */
    protected class ShortestPath {

        private double pathLenght;

        ShortestPath() {
            this.pathLenght = Double.POSITIVE_INFINITY;
        }

        UnorderedListADT<T> getPath(T startVertex, T targetVertex) {

            int indexStart = getIndex(startVertex);
            int indexTarget = getIndex(targetVertex);
            boolean found = false;

            GraphEdgePredecessor[] predecessors = new GraphEdgePredecessor[numberOfVertices];
            HeapMinADT<GraphEdgePredecessor> minHeap = new LinkedHeapMin<>();
            UnorderedListADT<T> resultList = new LinkedUnorderedList<>();

            if (!isIndexValid(indexStart) || !isIndexValid(indexTarget) || indexStart == indexTarget) {
                return resultList;
            }

            predecessors[indexStart] = new GraphEdgePredecessor(indexStart, 0, -1);
            minHeap.addElement(predecessors[indexStart]);
            while (!found && !minHeap.isEmpty()) {
                try {
                    GraphEdgePredecessor currentVertex = minHeap.removeMin();
                    int indexCurrentVertex = currentVertex.indexVertex;

                    if (!currentVertex.visited) {
                        predecessors[indexCurrentVertex].visited = true;
                        for (GraphEdge neighbor : vertices[indexCurrentVertex].neighbors) {
                            int indexNeighbor = neighbor.indexVertex;
                            double costToNext = currentVertex.weight + neighbor.weight;
                            if (predecessors[indexNeighbor] == null) {
                                predecessors[indexNeighbor] = new GraphEdgePredecessor(indexNeighbor, Double.POSITIVE_INFINITY, indexCurrentVertex);
                            }
                            if (!predecessors[indexNeighbor].visited) {
                                minHeap.addElement(new GraphEdgePredecessor(indexNeighbor, costToNext, indexCurrentVertex));
                                if (costToNext < predecessors[indexNeighbor].weight) {
                                    predecessors[indexNeighbor].indexPredecessor = indexCurrentVertex;
                                    predecessors[indexNeighbor].weight = costToNext;
                                }
                            }
                        }
                        if (indexCurrentVertex == indexTarget) {
                            found = true;
                        }
                    }
                } catch (EmptyCollectionException ignored) {
                }
            }

            if (!found) {
                return resultList;
            }
            int indexPrecedor = indexTarget;
            while (indexPrecedor != -1) {
                resultList.addToFront(vertices[indexPrecedor].element);
                indexPrecedor = predecessors[indexPrecedor].indexPredecessor;
            }
            this.pathLenght = predecessors[indexTarget].weight;
            return resultList;
        }

        double getPathWeight(T startVertex, T targetVertex) {
            getPath(startVertex, targetVertex);
            double path = pathLenght;
            pathLenght = Double.POSITIVE_INFINITY;
            return path;
        }

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public double shortestPathWeight(T vertex1, T vertex2) {
        return new ShortestPath().getPathWeight(vertex1, vertex2);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) {
        return new ShortestPath().getPath(startVertex, targetVertex).iterator();
    }

    protected UnorderedListADT<T> BFSList(T startVertex) {

        int indexStart = getIndex(startVertex);
        QueueADT<Integer> traversalQueue = new LinkedQueue<>();
        UnorderedListADT<T> resultList = new ArrayUnorderedList<>();
        boolean[] visited = new boolean[this.numberOfVertices];

        if (!isIndexValid(indexStart)) {
            return resultList;
        }

        traversalQueue.enqueue(indexStart);
        visited[indexStart] = true;

        while (!traversalQueue.isEmpty()) {
            try {
                int index = traversalQueue.dequeue();
                resultList.addToRear(this.vertices[index].element);
                for (GraphEdge neighbor : this.vertices[index].neighbors) {
                    int indexNeighbor = neighbor.indexVertex;
                    if (!visited[indexNeighbor]) {
                        traversalQueue.enqueue(indexNeighbor);
                        visited[indexNeighbor] = true;
                    }
                }
            } catch (EmptyCollectionException ignored) {
            }
        }
        return resultList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isEmpty() {
        return this.numberOfVertices == 0;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isConnected() {
        boolean isConnected = true;
        int count = BFSList(this.vertices[0].element).size();
        int i = 1;
        while (isConnected && i < this.numberOfVertices) {
            if (BFSList(this.vertices[i].element).size() != count) {
                isConnected = false;
            }
            i++;
        }
        return isConnected;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int size() {
        return this.numberOfVertices;
    }

    protected void shiftDelete(int indexVertex) {
        for (int i = indexVertex; i < this.numberOfVertices - 1; i++) {
            this.vertices[i] = this.vertices[i + 1];//shift vertex to the left
        }
        this.vertices[this.numberOfVertices - 1] = null;

        for (int i = 0; i < this.numberOfVertices - 1; i++) {
            Iterator<GraphEdge> itrNeighbors = vertices[i].neighbors.iterator();//Save the connections
            vertices[i].neighbors = new LinkedOrderedList<>();//Clear connections
            while (itrNeighbors.hasNext()) {
                GraphEdge neighbor = itrNeighbors.next();
                if (neighbor.indexVertex > indexVertex) {//if it is necessary to correct the connection
                    this.vertices[i].neighbors.add(new GraphEdge(neighbor.indexVertex - 1, neighbor.weight));
                } else {
                    this.vertices[i].neighbors.add(neighbor);
                }
            }
        }
    }

    protected int getIndex(T vertex) {
        int i = 0;
        int indexVertex = -1;
        while (i < this.numberOfVertices && indexVertex == -1) {
            if (this.vertices[i].element.equals(vertex)) {
                indexVertex = i;
            }
            i++;
        }
        return indexVertex;
    }

    protected boolean exists(T vertex) {
        return isIndexValid(getIndex(vertex));
    }

    protected boolean isIndexValid(int indexVertex) {
        return indexVertex >= 0;
    }

    protected void expandCapacity() {
        GraphVertex<T, GraphEdge>[] temp = (GraphVertex<T, GraphEdge>[]) new GraphVertex[this.vertices.length * 2];
        System.arraycopy(this.vertices, 0, temp, 0, this.numberOfVertices);
        this.vertices = temp;
    }

    protected boolean isNeighbor(T vertex1, T vertex2) {
        return isNeighbor(getIndex(vertex1), getIndex(vertex2));
    }

    protected boolean isNeighbor(int vertex1, int vertex2) {
        return this.vertices[vertex1].neighbors.contains(new GraphEdge(vertex2, DEFAULT_WEIGHT));
    }
}
