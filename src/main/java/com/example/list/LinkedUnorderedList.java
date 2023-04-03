package com.example.list;

import com.example.exceptions.*;
import com.example.interfaces.UnorderedListADT;
import com.example.stack.LinearNode;

import java.util.*;

/**
 * @author Micael
 */
public class LinkedUnorderedList<T> implements UnorderedListADT<T> {

    protected int count, modCount;
    private final LinearNode<T> front;
    private final LinearNode<T> rear;

    public LinkedUnorderedList() {
        this.count = 0;
        this.modCount = 0;
        this.front = new LinearNode<>();
        this.rear = new LinearNode<>();
        this.front.setNext(this.rear);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addToFront(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);
        newNode.setNext(front.getNext());
        front.setNext(newNode);
        this.modCount++;
        this.count++;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addToRear(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);
        LinearNode<T> current = front;

        while (current.getNext() != this.rear) {
            current = current.getNext();
        }

        newNode.setNext(this.rear);
        current.setNext(newNode);

        this.modCount++;
        this.count++;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addAfter(T element, T target) throws EmptyCollectionException, ElementNotFoundException {

        LinearNode<T> newNode;
        LinearNode<T> targetNode;
        LinearNode<T> current;
        boolean found;

        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }

        found = false;
        newNode = new LinearNode<>(element);
        targetNode = new LinearNode<>(target);
        current = front;

        while (current.getNext() != this.rear && !found) {
            if (current.getNext().getElement().equals(targetNode.getElement())) {
                found = true;
            }
            current = current.getNext();
        }

        if (!found) {
            throw new ElementNotFoundException(ElementNotFoundException.ELEMENT_NOT_FOUND);
        }

        newNode.setNext(current.getNext());
        current.setNext(newNode);

        this.modCount++;
        this.count++;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T removeFirst() {
        T removed = this.front.getElement();
        this.front.setNext(front.getNext());

        this.modCount--;
        this.count--;

        return removed;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T removeLast() throws EmptyCollectionException {

        T removed = null;
        LinearNode<T> current;

        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }

        current = this.front;
        removed = front.getNext().getElement();

        while (current.getNext().getNext() != this.rear) {
            removed = current.getNext().getElement();
            current = current.getNext();
        }

        current.setNext(this.rear);

        this.count--;
        this.modCount--;
        return removed;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {

        LinearNode<T> current;
        LinearNode<T> previous;
        boolean found;
        T removed;

        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }

        found = false;
        current = front;
        previous = current;
        removed = front.getNext().getElement();

        while (current.getNext() != this.rear && !found) {
            T toCompare = current.getNext().getElement();
            if (toCompare.equals(element)) {
                found = true;
                previous = current;
                removed = toCompare;
            }
            current = current.getNext();
        }

        if (!found) {
            throw new ElementNotFoundException(ElementNotFoundException.ELEMENT_NOT_FOUND);
        }

        previous.setNext(previous.getNext().getNext());

        this.modCount--;
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
        return front.getNext().getElement();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T last() throws EmptyCollectionException {

        LinearNode<T> current;
        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }
        current = front.getNext();
        while (current.getNext() != this.rear) {
            current = current.getNext();
        }
        return current.getElement();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean contains(T target) {
        boolean found = false;
        LinearNode<T> current = front.getNext();

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

    /**
     * Class that provides an Iterator for this Collection
     */
    private class BasicIterator implements Iterator<T> {

        private final String LIST_MODIFIED = "The list was been modified, you can´t use this Iterator";

        int expectedModCount;
        boolean okToRemove;
        LinearNode<T> current;

        public BasicIterator() {
            this.okToRemove = false;
            this.expectedModCount = modCount;
            this.current = front;
        }

        @Override
        public boolean hasNext() {
            if (hasModified()) {
                throw new ConcurrentModificationException(LIST_MODIFIED);
            }
            return this.current.getNext() != rear;
        }


        @Override
        public T next() {
            if (hasModified()) {
                throw new ConcurrentModificationException(LIST_MODIFIED);
            }
            if (!hasNext()) {
                throw new NoSuchElementException("There are no more elements");
            }
            this.okToRemove = true;
            this.current = this.current.getNext();
            return this.current.getElement();
        }

        private boolean hasModified() {
            return this.expectedModCount != modCount;
        }
    }
}
