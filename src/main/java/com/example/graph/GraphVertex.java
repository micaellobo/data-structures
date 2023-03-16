package com.example.graph;

import com.example.interfaces.OrderedListADT;
import com.example.list.LinkedOrderedList;

import java.util.Objects;

public class GraphVertex<T, E extends Comparable<E>> {

    protected T element;
    protected OrderedListADT<E> neighbors;

    /**
     * Creates a new graph node with the specified data.
     *
     * @param element the element that will become a part of the new tree node
     */
    public GraphVertex(T element) {
        this.element = element;
        this.neighbors = new LinkedOrderedList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ((!(o instanceof GraphVertex<?, ?> that))) return false;
        return Objects.equals(getElement(), that.getElement());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getElement());
    }

    public T getElement() {
        return element;
    }

    public OrderedListADT<E> getNeighbors() {
        return neighbors;
    }

}
