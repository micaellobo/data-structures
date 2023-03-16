package com.example.doubleLinkedList;

import com.example.exceptions.*;
import com.example.interfaces.ListADT;

import java.util.*;

public abstract class AbstractDoubleLinkedList<T> implements ListADT<T> {

    protected int count, modCount;
    protected DoubleNode<T> front, rear;

    public AbstractDoubleLinkedList() {
        this.count = 0;
        this.modCount = 0;
        this.front = new DoubleNode<>();
        this.rear = new DoubleNode<>();
        this.front.setNext(this.rear);
        this.rear.setPrevious(front);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {

        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }

        T removed = this.front.getNext().getElement();
        fastRemove(this.front.getNext());
        return removed;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T removeLast() throws EmptyCollectionException {

        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }

        T removed = this.rear.getPrevious().getElement();
        fastRemove(this.rear.getPrevious());
        return removed;
    }

    private void fastRemove(DoubleNode<T> toRemove) {
        toRemove.getPrevious().setNext(toRemove.getNext());
        toRemove.getNext().setPrevious(toRemove.getPrevious());
        this.count--;
        this.modCount++;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {

        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }

        boolean found = false;
        DoubleNode<T> current = this.front.getNext();
        DoubleNode<T> toRemove = new DoubleNode<>();

        while (current != this.rear && !found) {
            if (current.getElement().equals(element)) {
                toRemove = current;
                found = true;
            }
            current = current.getNext();
        }

        if (!found) {
            throw new ElementNotFoundException(ElementNotFoundException.ELEMENT_NOT_FOUND);
        }
        fastRemove(toRemove);
        return toRemove.getElement();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }
        return this.front.getNext().getElement();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }
        return this.rear.getPrevious().getElement();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean contains(T target) {

        boolean found = false;

        DoubleNode<T> current = this.front.getNext();
        while (!found && current != this.rear) {
            if (current.getElement().equals(target)) {
                found = true;
            }
            current = current.getNext();
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

        private int expectedModCount;
        private boolean okToRemove;
        private DoubleNode<T> current;

        public BasicIterator() {
            this.expectedModCount = modCount;
            this.okToRemove = false;
            this.current = front;

        }

        @Override
        public boolean hasNext() {
            if (this.expectedModCount != modCount) {
                throw new ConcurrentModificationException(LIST_MODIFIED);
            }
            return this.current.getNext() != rear;
        }

        @Override
        public T next() {
            if (this.expectedModCount != modCount) {
                throw new ConcurrentModificationException(LIST_MODIFIED);
            }
            if (!hasNext()) {
                throw new NoSuchElementException("There are no more elementes");
            }
            this.okToRemove = true;
            this.current = this.current.getNext();
            return this.current.getElement();
        }

        @Override
        public void remove() {
            if (this.expectedModCount != modCount) {
                throw new ConcurrentModificationException(LIST_MODIFIED);
            }
            if (!this.okToRemove) {
                throw new IllegalStateException("You have no element to remove");
            }
            fastRemove(this.current);
            this.expectedModCount++;
            this.okToRemove = false;
        }
    }

    protected String print() {
        StringBuilder string = new StringBuilder();

        DoubleNode<T> current = this.front.getNext();
        while (current != this.rear) {
            string.append(current.getElement()).append(" ");
            current = current.getNext();
        }
        return string.toString();
    }

}
