package com.example.queue;

import com.example.exceptions.EmptyCollectionException;
import com.example.interfaces.QueueADT;
import com.example.stack.LinearNode;

public class LinkedQueue<T> implements QueueADT<T> {

    private int count;
    private LinearNode<T> front, rear;

    public LinkedQueue() {
        this.count = 0;
        this.front = null;
        this.rear = null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void enqueue(T element) {

        LinearNode<T> newNode = new LinearNode<>(element);

        if (isEmpty()) {
            this.front = newNode;
            this.rear = this.front;
        } else {
            this.rear.setNext(newNode);
            this.rear = newNode;
        }
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
        T removed = this.front.getElement();
        this.front = this.front.getNext();
        this.count--;
        if (this.count == 0) {
            this.rear = null;
        }
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

        return this.front.getElement();
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

    protected String print() {

        StringBuilder s = new StringBuilder();

        LinearNode<T> current = this.front;
        while (current != null) {
            s.append(current.getElement()).append(" ");
            current = current.getNext();
        }
        s.append("\n");

        return s.toString();
    }

}
