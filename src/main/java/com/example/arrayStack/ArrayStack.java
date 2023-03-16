package com.example.arrayStack;

import com.example.exceptions.EmptyCollectionException;
import com.example.interfaces.StackADT;

public class ArrayStack<T> implements StackADT<T> {

    private static final int DEFAULT_CAPACITY = 100;
    private int top;
    private T[] stack;

    
    public ArrayStack(int initialCapacity) {
        this.top = 0;
        this.stack = (T[]) new Object[initialCapacity];
    }

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void push(T element) {
        if (this.stack.length == this.size()) {
            expandCapacity();
        }
        this.stack[top] = element;
        this.top++;
    }

    
    private void expandCapacity() {
        T[] aux = (T[]) new Object[this.stack.length * 2];
        System.arraycopy(this.stack, 0, aux, 0, this.top);
        this.stack = aux;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T pop() throws EmptyCollectionException {

        T toRemove;

        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }
        this.top--;
        toRemove = this.stack[this.top];
        return toRemove;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T peek() {
        return this.stack[this.top - 1];
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isEmpty() {
        return this.top == 0;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int size() {
        return this.top;
    }

    protected String print() {

        StringBuilder s = new StringBuilder();

        int i = 0;
        while (i != this.top) {
            s.append(this.stack[i++]);
            s.append(" ");
        }
        return s.toString();
    }
}
