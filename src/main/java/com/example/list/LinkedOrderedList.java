package com.example.list;

import com.example.binaryTree.LinkedBinarySearchTree;
import com.example.exceptions.*;
import com.example.interfaces.OrderedListADT;

import java.util.Iterator;

/**
 * @author Micael
 */
public class LinkedOrderedList<T extends Comparable<? super T>> extends LinkedBinarySearchTree<T> implements OrderedListADT<T> {

    public LinkedOrderedList() {
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void add(T element) {
        if (element == null) {
            throw new NullPointerException("The element to be added must not be null");
        }
        super.addElement(element);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        return super.removeMin();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        return super.removeMax();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        return super.removeElement(element);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T first() throws EmptyCollectionException {
        return super.findMin();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T last() throws EmptyCollectionException {
        return super.findMax();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int size() {
        return super.size();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            final Iterator<T> iteratorInOrder = iteratorInOrder();

            @Override
            public boolean hasNext() {
                return this.iteratorInOrder.hasNext();
            }

            @Override
            public T next() {
                return this.iteratorInOrder.next();
            }

            @Override
            public void remove() {
                /**
                 * Não está implementado pois se usar o que já existe ele apenas
                 * remove a copia, é melhor não colocar a não remover
                 */
                Iterator.super.remove(); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }
}
