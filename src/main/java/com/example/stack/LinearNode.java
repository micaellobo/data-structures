package com.example.stack;

import java.util.Objects;

public class LinearNode<T> {

    /**
     * reference to next node in list
     */
    private LinearNode<T> next;
    /**
     * element stored at this node
     */
    private T element;

    /**
     * Creates an empty node.
     */
    public LinearNode() {
        this.next = null;
        this.element = null;
    }

    /**
     * Creates a node storing the specified element.
     *
     * @param elem element to be stored
     */
    public LinearNode(T elem) {
        this.next = null;
        this.element = elem;

    }

    /**
     * Returns the node that follows this one.
     *
     * @return LinearNode reference to next node
     */
    public LinearNode<T> getNext() {
        return this.next;
    }

    /**
     * Sets the node that follows this one.
     *
     * @param node node to follow this one
     */
    public void setNext(LinearNode<T> node) {
        this.next = node;
    }

    /**
     * Returns the element stored in this node.
     *
     * @return T element stored at this node
     */
    public T getElement() {
        return this.element;
    }

    /**
     * Sets the element stored in this node.
     *
     * @param elem element to be stored at this node
     */
    public void setElement(T elem) {
        this.element = elem;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof final LinearNode<?> other)) {
            return false;
        }
        return Objects.equals(this.element, other.element);
    }

    protected String print() {
        return this.element.toString();
    }
}
