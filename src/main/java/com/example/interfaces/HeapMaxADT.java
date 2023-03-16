package com.example.interfaces;

import com.example.exceptions.EmptyCollectionException;

/**
 * @author Micael
 */
public interface HeapMaxADT<T> extends BinaryTreeADT<T> {

    /**
     * Adds the specified element to this heap in the appropriate position
     * according to its key value Note that equal elements are added to the
     * right.
     *
     * @param element the element to be added to this head
     */
    void addElement(T element);

    /**
     * Remove the element with the lowest value in this heap and returns a
     * reference to it.
     *
     * @return the element with the lowest value in this heap
     * @throws EmptyCollectionException if the heap is empty
     */
    T removeMax() throws EmptyCollectionException;

    /**
     * Returns a reference to the element with the lowest value in this heap.
     *
     * @return a reference to the element with the lowest value in this heap
     */
    T findMax() throws EmptyCollectionException;
}
