package com.example.graph;

import com.example.arrayList.ArrayUnorderedList;
import com.example.arrayQueue.CircularArrayQueue;
import com.example.arrayStack.ArrayStack;
import com.example.exceptions.*;
import com.example.interfaces.*;
import com.example.list.LinkedOrderedList;
import com.example.queue.LinkedQueue;

import java.util.Iterator;

public class GraphDirected<T> implements GraphADT<T> {

    protected static final int DEFAULT_CAPACITY = 100;

    protected GraphVertex<T, Integer>[] vertices;
    protected int numberOfVertices;
    protected int numberOfEdges;


    public GraphDirected(int capacity) {
        this.vertices = (GraphVertex<T, Integer>[]) new GraphVertex[capacity];
        this.numberOfVertices = 0;
        this.numberOfEdges = 0;
    }

    public GraphDirected() {
        this(DEFAULT_CAPACITY);
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
    public void addEdge(T vertex1, T vertex2) throws ElementNotFoundException, VertexConnectionExeception {
        int indexVertex1 = getIndex(vertex1);
        int indexVertex2 = getIndex(vertex2);

        if (!isIndexValid(indexVertex1) || !isIndexValid(indexVertex2)) {
            throw new ElementNotFoundException(ElementNotFoundException.ELEMENT_NOT_FOUND);
        }

        if (isNeighbor(indexVertex1, indexVertex2)) {
            throw new VertexConnectionExeception(VertexConnectionExeception.ALREADY_CONNECTED);
        }

        this.vertices[indexVertex1].neighbors.add(indexVertex2);

        this.numberOfEdges++;
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

        //removes the incident edges on the removed vertex
        for (int i = 0; i < this.numberOfVertices; i++) {
            try {
                this.vertices[i].neighbors.remove(indexVertex);
                this.numberOfEdges--; //decrement number of edges
            } catch (ElementNotFoundException | EmptyCollectionException ignored) {
            }
        }

        //Decrement the number of edges going from removed vertex
        this.numberOfEdges -= this.vertices[indexVertex].neighbors.size();
        shiftDelete(indexVertex);
        this.numberOfVertices--;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) throws EmptyCollectionException, ElementNotFoundException, VertexConnectionExeception {

        int indexVertex1;
        int indexVertex2;

        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }

        indexVertex1 = getIndex(vertex1);
        indexVertex2 = getIndex(vertex2);

        if (!isIndexValid(indexVertex1) || isIndexValid(indexVertex2)) {
            throw new ElementNotFoundException(ElementNotFoundException.ELEMENT_NOT_FOUND);
        }
        if (!isNeighbor(indexVertex1, indexVertex2)) {
            throw new VertexConnectionExeception(VertexConnectionExeception.NO_CONNECTION);
        }
        try {
            this.vertices[indexVertex1].neighbors.remove(indexVertex2);
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
                for (Integer neighbor : this.vertices[indexCurrent].neighbors) {
                    if (!visited[neighbor]) {
                        traversalStack.push(neighbor);
                        resultList.addToRear(this.vertices[neighbor].element);
                        visited[neighbor] = true;
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
     * {@inheritDoc }
     */
    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) {

        Integer indexCurrent;
        boolean targetFound = false;
        int[] predecessor = new int[this.numberOfVertices];
        boolean[] visited = new boolean[this.numberOfVertices];

        QueueADT<Integer> traversalQueue = new CircularArrayQueue<>(this.numberOfVertices);
        UnorderedListADT<T> resultList = new ArrayUnorderedList<>(this.numberOfVertices);

        int indexStart = getIndex(startVertex);
        int indexTarget = getIndex(targetVertex);

        if (!isIndexValid(indexStart) && !isIndexValid(indexTarget)) {
            return resultList.iterator();
        }

        traversalQueue.enqueue(indexStart);
        predecessor[indexStart] = -1;
        visited[indexStart] = true;
        while (!targetFound && !traversalQueue.isEmpty()) {
            try {
                indexCurrent = traversalQueue.dequeue();
                for (int i = 0; i < this.numberOfVertices; i++) {
                    if (isNeighbor(indexCurrent, i) && !visited[i]) {
                        predecessor[i] = indexCurrent;
                        traversalQueue.enqueue(i);
                        visited[i] = true;
                    }
                }
            } catch (EmptyCollectionException ignored) {
            }
        }

        int indexPrecedor = predecessor[indexTarget];
        while (!(vertices[indexPrecedor].element.equals(startVertex))) {
            resultList.addToFront(vertices[indexPrecedor].element);
            indexPrecedor = predecessor[indexPrecedor];
        }
        resultList.addToRear(targetVertex);
        resultList.addToFront(startVertex);
        return resultList.iterator();
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
                for (Integer neighbor : this.vertices[index].neighbors) {
                    if (!visited[neighbor]) {
                        traversalQueue.enqueue(neighbor);
                        visited[neighbor] = true;
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

    /**
     * Makes a shift left in array of vertices, and fix the resulting invalid
     * connections after the shift
     *
     * @param indexVertex start shift index on the left
     */
    protected void shiftDelete(int indexVertex) {

        for (int i = indexVertex; i < this.numberOfVertices - 1; i++) {
            vertices[i] = vertices[i + 1];//shift vertex to the left
        }

        this.vertices[this.numberOfVertices - 1] = null;

        for (int i = 0; i < this.numberOfVertices - 1; i++) {
            Iterator<Integer> itrNeighbors = vertices[i].neighbors.iterator();//Save the connections
            vertices[i].neighbors = new LinkedOrderedList<>();//Clear connections
            while (itrNeighbors.hasNext()) {
                int neighbor = itrNeighbors.next();
                if (neighbor > indexVertex) {//if it is necessary to correct the connection
                    vertices[i].neighbors.add(neighbor - 1);
                } else {
                    vertices[i].neighbors.add(neighbor);
                }
            }
        }
    }

    protected int getIndex(T vertex) {
        int i = 0;
        int indexVertex = -1;
        while (i < numberOfVertices && indexVertex == -1) {
            if (vertices[i].element.equals(vertex)) {
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
        GraphVertex<T, Integer>[] temp = (GraphVertex<T, Integer>[]) new GraphVertex[this.vertices.length * 2];
        System.arraycopy(this.vertices, 0, temp, 0, this.numberOfVertices);
        this.vertices = temp;
    }

    protected boolean isNeighbor(T vertex1, T vertex2) {
        return isNeighbor(getIndex(vertex1), getIndex(vertex2));
    }

    protected boolean isNeighbor(int vertex1, int vertex2) {
        return vertices[vertex1].neighbors.contains(vertex2);
    }
}
