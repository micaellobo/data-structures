package com.example.arrayBinaryTree;

import com.example.arrayList.ArrayUnorderedList;
import com.example.exceptions.*;
import com.example.interfaces.BinaryTreeADT;

import java.util.Iterator;

/**
 * @author Micael
 */
public abstract class ArrayBinaryTree<T> implements BinaryTreeADT<T> {

    protected int count;
    protected T[] tree;
    protected static final int DEFAULT_CAPACITY = 10;

    /**
     * Creates an empty binary tree.
     */
    
    public ArrayBinaryTree(int capacity) {
        this.count = 0;
        this.tree = (T[]) new Object[capacity];
    }

    /**
     {@inheritDoc}
     */
    
    public ArrayBinaryTree(T element) {
        this.count = 1;
        this.tree = (T[]) new Object[DEFAULT_CAPACITY];
        this.tree[0] = element;
    }

    public ArrayBinaryTree() {
        this(DEFAULT_CAPACITY);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public T find(T targetElement) throws ElementNotFoundException {

        T temp = findFast(targetElement);
        boolean found = temp == null;

        if (!found) {
            throw new ElementNotFoundException(ElementNotFoundException.ELEMENT_NOT_FOUND);
        }
        return temp;
    }

    private T findFast(T targetElement) {
        T temp = null;
        boolean found = false;

        for (int ct = 0; ct < this.count && !found; ct++) {
            if (targetElement.equals(this.tree[ct])) {
                found = true;
                temp = this.tree[ct];
            }
        }
        return temp;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T getRoot() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }
        return this.tree[0];
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
    public boolean contains(T targetElement) {
        return findFast(targetElement) != null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<>();
        inOrder(0, templist);
        return templist.iterator();
    }

    protected void inOrder(int node, ArrayUnorderedList<T> templist) {

        if (node < this.tree.length) {
            if (this.tree[node] != null) {
                inOrder(node * 2 + 1, templist);
                templist.addToRear(this.tree[node]);
                inOrder((node + 1) * 2, templist);
            }
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<>();
        preOrder(0, templist);
        return templist.iterator();
    }

    protected void preOrder(int node, ArrayUnorderedList<T> templist) {

        if (node < this.tree.length) {
            if (this.tree[node] != null) {
                templist.addToRear(this.tree[node]);
                preOrder(node * 2 + 1, templist);
                preOrder((node + 1) * 2, templist);
            }
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<>();
        postOrder(0, templist);
        return templist.iterator();
    }

    protected void postOrder(int node, ArrayUnorderedList<T> templist) {

        if (node < this.tree.length) {
            if (this.tree[node] != null) {
                postOrder(node * 2 + 1, templist);
                postOrder((node + 1) * 2, templist);
                templist.addToRear(this.tree[node]);
            }
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Iterator<T> iteratorLevelOrder() {
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        int i = 0;
        while (this.tree[i] != null) {
            resultList.addToRear(this.tree[i]);
            i++;
        }
        return resultList.iterator();
    }

}
