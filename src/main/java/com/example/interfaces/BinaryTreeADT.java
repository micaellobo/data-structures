package com.example.interfaces;

import com.example.exceptions.*;

import java.util.Iterator;

/**
 * @author Micael
 */
public interface BinaryTreeADT<T> {

    /**
     * Returns a reference to the root element
     *
     * @return a reference to the root
     */
    T getRoot() throws EmptyCollectionException;

    /**
     * Returns true if this binary tree is empty and false otherwise.
     *
     * @return true if this binary tree is empty
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in this binary tree.
     *
     * @return the integer number of elements in this tree
     */
    int size();

    /**
     * Returns true if the binary tree contains an element that matches the
     * specified element and false otherwise.
     *
     * @param targetElement the element being sought in the tree
     * @return true if the tree contains the target element
     */
    boolean contains(T targetElement);

    /**
     * Returns a reference to the specified element if it is found in this
     * binary tree. Throws an exception if the specified element is not found.
     *
     * @param targetElement the element being sought in the tree
     * @return a reference to the specified element
     */
    T find(T targetElement) throws ElementNotFoundException;

    /**
     * Performs an inorder traversal on this binary tree by calling an
     * overloaded, recursive inorder method that starts with the root.
     *
     * @return an iterator over the elements of this binary tree
     */
    Iterator<T> iteratorInOrder();

    /**
     * Performs a preorder traversal on this binary tree by calling an
     * overloaded, recursive preorder method that starts with the root.
     *
     * @return an iterator over the elements of this binary tree
     */
    Iterator<T> iteratorPreOrder();

    /**
     * Performs a postorder traversal on this binary tree by calling an
     * overloaded, recursive postorder method that starts with the root.
     *
     * @return an iterator over the elements of this binary tree
     */
    Iterator<T> iteratorPostOrder();

    /**
     * Performs a levelorder traversal on the binary tree, using a queue.
     *
     * @return an iterator over the elements of this binary tree
     */
    Iterator<T> iteratorLevelOrder();

    /**
     * Returns the string representation of the binary tree.
     *
     * @return a string representation of the binary tree
     */
    @Override
    String toString();
}
