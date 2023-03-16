package com.example.stack;

import com.example.exceptions.EmptyCollectionException;
import com.example.interfaces.StackADT;

public class LinkedStack<T> implements StackADT<T> {

    private int count;
    private LinearNode<T> top;

    public LinkedStack() {
        this.count = 0;
        this.top = null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void push(T element) {

        LinearNode<T> newNode = new LinearNode<>(element);

        if (this.top == null) {
            this.top = newNode;
        } else {
            newNode.setNext(this.top);
            this.top = newNode;
        }
        this.count++;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T pop() throws EmptyCollectionException {

        LinearNode<T> toRemove;

        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }
        toRemove = this.top;
        this.top = toRemove.getNext();
        this.count--;

        return toRemove.getElement();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T peek() throws EmptyCollectionException {

        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }
        return this.top.getElement();
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

        LinearNode<T> current = this.top;
        while (current != null) {
            s.append(current.getElement()).append(" ");
            current = current.getNext();
        }
        return s.toString();
    }

}
