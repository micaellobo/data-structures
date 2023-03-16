package com.example.doubleLinkedList;

/**
 * @author Micael
 */
public class DoubleNode<T> {

    private T element;
    private DoubleNode<T> previous;
    private DoubleNode<T> next;

    /**
     * Creates an empty node.
     */
    public DoubleNode() {
        this(null);
    }

    /**
     * Creates a node storing the specified element.
     *
     * @param elem the element to be stored into the new node
     */
    public DoubleNode(T elem) {
        this.next = null;
        this.element = elem;
        this.previous = null;
    }

    /**
     * Returns the node that follows this one.
     *
     * @return the node that follows the current one
     */
    public DoubleNode<T> getNext() {
        return next;
    }

    /**
     * Returns the node that precedes this one
     *
     * @return the node that precedes the current one
     */
    public DoubleNode<T> getPrevious() {
        return previous;
    }

    /**
     * Sets the node that follows this one.
     *
     * @param next the node to be set as the one to follows the current one
     */
    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }

    /**
     * Sets the node that precedes this one.
     *
     * @param previous the node to be set as the one to precede the current one
     */
    public void setPrevious(DoubleNode<T> previous) {
        this.previous = previous;
    }

    /**
     * Returns the element stored in this node.
     *
     * @return the element stored in this node
     */
    public T getElement() {
        return element;
    }

    /**
     * Sets the element stored in this node.
     *
     * @param elem the element to be stored in this node
     */
    public void setElement(T elem) {
        this.element = elem;
    }
}
