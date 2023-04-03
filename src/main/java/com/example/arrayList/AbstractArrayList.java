package com.example.arrayList;

import com.example.exceptions.*;
import com.example.interfaces.ListADT;

import java.util.*;

public abstract class AbstractArrayList<T> implements ListADT<T> {

    protected static final int DEFAULT_CAPACITY = 100;

    protected int count;
    protected int front;
    protected int rear;
    protected int modCount;
    protected T[] list;

    
    public AbstractArrayList(int initialCapacity) {
        this.count = 0;
        this.front = 0;
        this.rear = 0;
        this.list = (T[]) new Object[initialCapacity];
    }

    public AbstractArrayList() {
        this(DEFAULT_CAPACITY);
    }

    
    protected void expandCapacity() {
        T[] aux = (T[]) new Object[this.list.length * 2];
        System.arraycopy(this.list, 0, aux, 0, this.count);
        this.list = aux;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {

        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }
        return fastRemove(this.front);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T removeLast() throws EmptyCollectionException {

        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }
        --this.rear;
        T removed = this.list[this.rear];
        this.list[this.rear] = null;
        this.count--;
        this.modCount++;
        return removed;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T remove(T targetElement) throws EmptyCollectionException, ElementNotFoundException {

        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }
        //!!!Ordem comparação, evita caso expecifico

        boolean found = false;
        int i = 0;
        int indexTarget = -1;
        while (!found && i < this.rear) {
            if (this.list[i].equals(targetElement)) {
                found = true;
                indexTarget = i;
            }
            i++;
        }

        if (!found) {
            throw new ElementNotFoundException(ElementNotFoundException.ELEMENT_NOT_FOUND);
        }
        return fastRemove(indexTarget);
    }

    private T fastRemove(int index) {
        T removed = this.list[index];
        while (index + 1 < this.rear) {
            this.list[index] = this.list[++index];
        }
        this.list[--this.rear] = null;
        this.count--;
        this.modCount++;
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
        return this.list[this.front];
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }
        return this.list[this.rear - 1];
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean contains(T target) {

        boolean found = false;
        int i = 0;
        while (i < this.count && !found) {
            if (this.list[i].equals(target)) {
                found = true;
            }
            i++;
        }
        return found;
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

    /**
     * {@inheritDoc }
     */
    @Override
    public Iterator<T> iterator() {
        return new BasicIterator();
    }

    private class BasicIterator implements Iterator<T> {

        private final String LIST_MODIFIED = "The list was been modified, you can´t use this Iterator";

        private int currentIndex;
        private int expectedModCount;
        private boolean okToRemove;

        public BasicIterator() {
            this.currentIndex = 0;
            this.expectedModCount = modCount;
            this.okToRemove = false;
        }

        @Override
        public boolean hasNext() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException(LIST_MODIFIED);
            }
            return this.currentIndex < list.length && list[this.currentIndex] != null;
        }

        @Override
        public T next() {
            if (modCount != this.expectedModCount) {
                throw new ConcurrentModificationException(LIST_MODIFIED);
            }
            if (!hasNext()) {
                throw new NoSuchElementException("There are no more elements");
            }
            this.okToRemove = true;
            return list[this.currentIndex++];
        }

        @Override
        public void remove() {
            if (modCount != this.expectedModCount) {
                throw new ConcurrentModificationException(LIST_MODIFIED);
            }
            if (!this.okToRemove) {
                throw new IllegalStateException("You have no element to remove");
            }
            this.okToRemove = false;
            this.expectedModCount++;
            fastRemove(this.currentIndex++);
        }
    }

    protected void shiftRight(int start) {

        int posDecre = this.rear;
        while (posDecre != start) {
            this.list[posDecre] = this.list[--posDecre];
        }
        this.list[start] = null;
    }

    protected String printAll() {
        StringBuilder s = new StringBuilder();
        for (T t : list) {
            s.append(t);
            s.append(" ");
        }
        s.append("\n" + "Front: ").append(front);
        s.append("\n" + "Rear: ").append(rear);
        s.append("\n" + "Count: ").append(count);
        return s.toString();
    }

    @Override
    public String toString() {
        return printAll();
    }
}
