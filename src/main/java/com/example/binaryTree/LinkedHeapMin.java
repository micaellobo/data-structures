package com.example.binaryTree;

import com.example.exceptions.EmptyCollectionException;
import com.example.interfaces.HeapMinADT;

import java.util.NoSuchElementException;

/**
 * Heap implements a heap.
 */
public class LinkedHeapMin<T extends Comparable<? super T>> extends LinkedBinaryTree<T> implements HeapMinADT<T> {

    private HeapNode<T> lastNode;

    public LinkedHeapMin() {
        super();
        this.lastNode = null;
    }

    public LinkedHeapMin(T element) {
        super(element);
        this.lastNode = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addElement(T element) {

        if (element == null) {
            throw new NoSuchElementException("The element to add most not be null");
        }

        HeapNode<T> node = new HeapNode<>(element);

        if (isEmpty()) {
            this.root = node;
            this.lastNode = node;
        } else {
            HeapNode<T> nextParentAdd = getNextParentAdd();
            if (nextParentAdd.left == null) {
                nextParentAdd.left = node;
            } else {
                nextParentAdd.right = node;
            }
            node.parent = nextParentAdd;
            this.lastNode = node;
            heapifyAdd();
        }
        this.count++;
    }

    /**
     * Returns the node that will be the parent of the new node
     *
     * @return the node that will be a parent of the new node
     */
    private HeapNode<T> getNextParentAdd() {

        HeapNode<T> result = this.lastNode;

        //Quando parent à esquerda igual a elemento provavel haver lugar a sua direita
        while (result != this.root && result.parent.left != result) {
            result = result.parent;
        }

        if (result != this.root) {
            if (result.parent.right == null) {
                result = result.parent;
            } else {
                result = (HeapNode<T>) result.parent.right;
                while (result.left != null) {
                    result = (HeapNode<T>) result.left;
                }
            }
        } else {//Caso fiqui uma arvore cheia
            while (result.left != null) {
                result = (HeapNode<T>) result.left;
            }
        }

        return result;
    }

    /**
     * Reorders this heap after adding a node.
     */
    private void heapifyAdd() {

        HeapNode<T> current = this.lastNode;
        T elementAdd = current.element;

        while (current != this.root && elementAdd.compareTo(current.parent.element) < 0) {
            current.element = current.parent.element;
            current = current.parent;
        }
        current.element = elementAdd;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T removeMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }

        T removed = this.root.element;
        this.root.element = this.lastNode.element;

        if (this.count == 1) {
            this.root = null;
            this.lastNode = null;
        } else {
            HeapNode<T> newLast = getNewLastNode();
            if (this.lastNode.parent.left == this.lastNode) {
                this.lastNode.parent.left = null;
            } else {
                this.lastNode.parent.right = null;
            }
            this.lastNode = newLast;
            heapifyRemove();
        }
        this.count--;
        return removed;
    }

    private void heapifyRemove() {

        HeapNode<T> current = (HeapNode<T>) this.root;
        T lastElement = current.element;

        if (current.left == null && current.right == null) {
            current = null;
        } else if (current.left == null) {
            current = (HeapNode<T>) current.right;
        } else if (current.right == null) {
            current = (HeapNode<T>) current.left;
        } else {
            current = current.right.element.compareTo(current.left.element) > 0
                    ? (HeapNode<T>) current.left : (HeapNode<T>) current.right;
        }

        while (current != null && current.element.compareTo(lastElement) < 0) {

            current.parent.element = current.element;
            current.element = lastElement;

            if (current.left == null && current.right == null) {
                current = null;
            } else if (current.left == null) {
                current = (HeapNode<T>) current.right;
            } else if (current.right == null) {
                current = (HeapNode<T>) current.left;
            } else {
                current = current.right.element.compareTo(current.left.element) > 0
                        ? (HeapNode<T>) current.left : (HeapNode<T>) current.right;
            }
        }
    }

    private HeapNode<T> getNewLastNode() {

        HeapNode<T> result = this.lastNode;

        while (result != this.root && result.parent.left == result) {
            result = result.parent;
        }
        if (result != this.root) {
            result = (HeapNode<T>) result.parent.left;
            while (result.right != null) {
                result = (HeapNode<T>) result.right;
            }
        } else {
            while (result.right != null) {
                result = (HeapNode<T>) result.right;
            }
        }

        return result;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T findMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }
        return this.root.element;
    }

}
