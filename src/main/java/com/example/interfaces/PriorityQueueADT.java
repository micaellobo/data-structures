package com.example.interfaces;

import com.example.exceptions.EmptyCollectionException;

public interface PriorityQueueADT<T> {

    /**
     * Removes the next highest priority element from this priority queue and
     * returns a reference to it.
     *
     * @return a reference to the next highest priority element in this queue
     */
    T removeNext() throws EmptyCollectionException;

    /**
     * Adds the given element to this PriorityQueue.
     *
     * @param element  the element to be added to the priority queue
     * @param priority the integer priority of the element to be added
     */
    void addElement(T element, int priority);

    /**
     * Returns true if this priorityQueue tree is empty and false otherwise.
     *
     * @return true if this priorityQueue tree is empty
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in this priorityQueue.
     *
     * @return the integer number of elements in this priorityQueue
     */
    int size();

// /**
//     * Returns a reference to the element with the lowest value in this
//     * priorityQueue.
//     *
//     * @return a reference to the element with the lowest value in this
//     * priorityQueue
//     */
//    public T findMin() throws EmptyCollectionException;
//
//    /**
//     * Removes element with the lowest value from this priorityQueue.
//     *
//     * @return the element with the lowest value from this priorityQueue
//     */
//    public T removeMin() throws EmptyCollectionException;
//
//    /**
//     * Adds the specified element to this priorityQueue.
//     *
//     * @param obj the element to added to this priorityQueue
//     */
//    public void addElement(T obj);
}
