package com.example.arrayQueue;

import com.example.exceptions.EmptyCollectionException;
import com.example.interfaces.QueueADT;

public class CircularArrayQueue<T> implements QueueADT<T> {

    private static final int DEFAULT_CAPACITY = 5;

    private int count, front, rear;
    private T[] queue;

    
    public CircularArrayQueue(int initialCapacity) {
        this.count = 0;
        this.front = 0;
        this.rear = 0;
        this.queue = (T[]) new Object[initialCapacity];
    }

    public CircularArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    private boolean isFull() {
        return this.queue.length == this.size();
    }

    
    private void expandCapacity() {
        T[] aux = (T[]) new Object[this.queue.length * 2];

        int itr = this.front;
        for (int j = 0; j < this.count; j++) {
            aux[j] = this.queue[itr];
            itr = (itr + 1) % this.queue.length;
        }
        this.queue = aux;
        this.front = 0;
        this.rear = this.count;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void enqueue(T element) {

        if (isFull()) {
            expandCapacity();
        }
        this.queue[this.rear] = element;
        this.rear = (this.rear + 1) % this.queue.length;
        this.count++;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T dequeue() throws EmptyCollectionException {

        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }

        T removed = this.queue[this.front];
        this.queue[this.front] = null;
        this.front = (this.front + 1) % this.queue.length;
        this.count--;
        return removed;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }
        return this.queue[this.front];
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int size() {
        return this.count;
    }

    protected String Print() {
        StringBuilder s = new StringBuilder();
        int itr = this.front;
        while (itr != this.rear) {
            s.append(this.queue[itr]).append(" ");
            itr = (itr + 1) % this.queue.length;
        }
        s.append("\n");
        return s.toString();
    }

    //Apenas para ver melhor o que está a acontecer
    protected void printAll() {
        for (T t : this.queue) {
            System.out.println(t);
        }
        System.out.println("Lengh: " + this.queue.length);
        System.out.println("front: " + this.front);
        System.out.println("rear: " + this.rear);
    }

}
