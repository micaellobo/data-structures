package com.example.binaryTree;

import com.example.arrayList.ArrayUnorderedList;
import com.example.arrayQueue.CircularArrayQueue;
import com.example.exceptions.*;
import com.example.interfaces.BinaryTreeADT;

import java.util.Iterator;
import java.util.logging.*;

/**
 * @author Micael
 */
public abstract class LinkedBinaryTree<T> implements BinaryTreeADT<T> {

    protected int count;
    protected BinaryTreeNode<T> root;

    /**
     * Creates an empty binary tree.
     */
    public LinkedBinaryTree() {
        this.count = 0;
        this.root = null;
    }

    /**
     * Creates a binary tree with the specified element as its root.
     *
     * @param element the element that will become the root of the new binary
     *                tree
     */
    public LinkedBinaryTree(T element) {
        this.count = 1;
        this.root = new BinaryTreeNode<>(element);
    }

    @Override
    public T getRoot() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }
        return this.root.element;
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
        return findAgain(targetElement, root) != null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T find(T targetElement) throws ElementNotFoundException {

        BinaryTreeNode<T> current = findAgain(targetElement, root);

        if (current == null) {
            throw new ElementNotFoundException(ElementNotFoundException.ELEMENT_NOT_FOUND);
        }
        return (current.element);
    }

    private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next) {

        if (next == null) {
            return null;
        }

        if (next.element.equals(targetElement)) {
            return next;
        }

        BinaryTreeNode<T> temp = findAgain(targetElement, next.left);

        if (temp == null) {
            temp = findAgain(targetElement, next.right);
        }
        return temp;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        inOrder(this.root, resultList);
        return resultList.iterator();
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param node       the node to be used as the root for this traversal
     * @param resultList the temporary list for use in this traversal
     */
    protected void inOrder(BinaryTreeNode<T> node, ArrayUnorderedList<T> resultList) {
        if (node != null) {
            inOrder(node.left, resultList);
            resultList.addToRear(node.element);
            inOrder(node.right, resultList);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>(this.count);
        preOrder(this.root, resultList);
        return resultList.iterator();
    }

    protected void preOrder(BinaryTreeNode<T> node, ArrayUnorderedList<T> resultList) {
        if (node != null) {
            resultList.addToRear(node.element);
            preOrder(node.left, resultList);
            preOrder(node.right, resultList);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>(this.count);
        postOrder(this.root, tempList);
        return tempList.iterator();
    }

    protected void postOrder(BinaryTreeNode<T> node, ArrayUnorderedList<T> resultList) {
        if (node != null) {
            postOrder(node.left, resultList);
            postOrder(node.right, resultList);
            resultList.addToRear(node.element);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Iterator<T> iteratorLevelOrder() {

        CircularArrayQueue<BinaryTreeNode<T>> nodes = new CircularArrayQueue<>(this.count);
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        nodes.enqueue(this.root);

        while (!nodes.isEmpty()) {
            try {
                BinaryTreeNode<T> dequeueNode = nodes.dequeue();
                if (dequeueNode != null) {
                    nodes.enqueue(dequeueNode.left);
                    nodes.enqueue(dequeueNode.right);
                    resultList.addToRear(dequeueNode.element);
                }
            } catch (EmptyCollectionException ex) {
                Logger.getLogger(LinkedBinaryTree.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultList.iterator();
    }

}
