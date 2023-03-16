package com.example.interfaces;

import com.example.exceptions.*;

/**
 * @author Micael
 */
public interface UnorderedListADT<T> extends ListADT<T> {

    /**
     * Adds the specified element to this list at the front
     *
     * @param element the element to be added to this list
     */
    void addToFront(T element);

    /**
     * Adds the specified element to this list at the rear
     *
     * @param element the element to be added to this list
     */
    void addToRear(T element);

    /**
     * Adds the specified element after an indicated element
     *
     * @param element the element to be added to this list
     * @param target  element that serves as indicator where the new element will be added
     */
    void addAfter(T element, T target) throws EmptyCollectionException, ElementNotFoundException;
}
