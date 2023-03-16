package com.example.binaryTree;

import com.example.exceptions.*;
import com.example.interfaces.BinarySearchTreeADT;

/**
 * Implementation of an AVL binary search tree
 * <p>
 */
public class LinkedBinarySearchTree<T extends Comparable<? super T>> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {

    protected T elementRemovedTemp;
    private BinaryTreeNode<T> nodeRemovedTemp;

    /**
     * Creates an empty binary search tree.
     */
    public LinkedBinarySearchTree() {
        super();
        this.elementRemovedTemp = null;
        this.nodeRemovedTemp = null;
    }

    /**
     * Creates an AVL binary search with the specified element as its root.
     *
     * @param element the element that will be the root of the new binary search
     *                tree
     */
    public LinkedBinarySearchTree(T element) {
        super(element);
        this.elementRemovedTemp = null;
        this.nodeRemovedTemp = null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addElement(T element) {
        if (element == null) {
            throw new NullPointerException("The element to add should not be null");
        }
        this.root = insert(this.root, element);
        this.count++;
    }

    private BinaryTreeNode<T> insert(BinaryTreeNode<T> node, T element) {
        if (node == null) {
            node = new BinaryTreeNode<>(element);
        } else if (element.compareTo(node.element) < 0) {
            node.left = insert(node.left, element);
        } else {
            node.right = insert(node.right, element);

        }
        return balance(node);
    }

    protected BinaryTreeNode<T> balance(BinaryTreeNode<T> node) {
        updateHeight(node);
        if (getFactorBalance(node) < -1) {//Caused to the left
            if (getFactorBalance(node.left) == 1) {//Caused to the left by right child
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);
        } else if (getFactorBalance(node) > 1) {//Caused to the right
            if (getFactorBalance(node.right) == -1) {//Caused to the right by left child
                node.right = rotateRight(node.right);
            }
            node = rotateLeft(node);
        }
        return node;
    }

    protected BinaryTreeNode<T> rotateRight(BinaryTreeNode<T> subTreeRight) {
        BinaryTreeNode<T> newRoot = subTreeRight.left;
        subTreeRight.left = newRoot.right;
        newRoot.right = subTreeRight;
        updateHeight(subTreeRight);
        updateHeight(newRoot);
        return newRoot;
    }

    protected BinaryTreeNode<T> rotateLeft(BinaryTreeNode<T> subTreeLeft) {
        BinaryTreeNode<T> newRoot = subTreeLeft.right;
        subTreeLeft.right = newRoot.left;
        newRoot.left = subTreeLeft;
        updateHeight(subTreeLeft);
        updateHeight(newRoot);
        return newRoot;
    }

    private int getFactorBalance(BinaryTreeNode<T> node) {
        return height(node.right) - height(node.left);
    }

    private void updateHeight(BinaryTreeNode<T> node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int height(BinaryTreeNode<T> node) {
        return node == null ? -1 : node.height;
    }

    private T getRemovedElement() {
        T element = this.elementRemovedTemp;
        this.elementRemovedTemp = null;
        return element;
    }

    private void setRemovedElement(T element) {
        this.elementRemovedTemp = element;
    }

    private BinaryTreeNode<T> getRemovedNode() {
        BinaryTreeNode<T> temp = this.nodeRemovedTemp;
        this.nodeRemovedTemp = null;
        return temp;

    }

    private void setRemovedNode(BinaryTreeNode<T> node) {
        this.nodeRemovedTemp = node;
    }

    /**
     * Returns a reference to a node that will replace the one specified for
     * removal. In the case where the removed node has two children, the inorder
     * successor is used as its replacement.
     *
     * @param node the node to be removeed
     * @return a reference to the replacing node
     */
    protected BinaryTreeNode<T> replace(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> result = null;
        if ((node.left == null) && (node.right == null)) {
            result = null;
        } else if ((node.left != null) && (node.right == null)) {
            result = node.left;
        } else if ((node.left == null) && (node.right != null)) {
            result = node.right;
        } else {//Remover node sucessor ao node que queremos remover e trocar de lugar
            BinaryTreeNode<T> newSubRightTree = removeMinSubTree(node.right);
            BinaryTreeNode<T> successorInOrder = getRemovedNode();
            successorInOrder.right = newSubRightTree;
            successorInOrder.left = node.left;
            result = balance(successorInOrder);
        }
        return result;
    }

    protected BinaryTreeNode<T> removeMinSubTree(BinaryTreeNode<T> subTree) {
        T removed = getRemovedElement();
        if (subTree.left == null) {
            setRemovedNode(subTree);
            subTree = subTree.right;
        } else {
            subTree = deleteMin(subTree);
        }
        setRemovedElement(removed);
        return subTree;
    }

    /**
     {@inheritDoc}
     */
    @Override
    public T removeElement(T targetElement) throws ElementNotFoundException, EmptyCollectionException {

        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }
        T removed;
        if (this.root.element.compareTo(targetElement) == 0) {
            removed = this.root.element;
            this.root = replace(this.root);
        } else {
            this.root = remove(this.root, targetElement);
            removed = getRemovedElement();
            if (removed == null) {//Caso não tenha encontrado
                throw new ElementNotFoundException(ElementNotFoundException.ELEMENT_NOT_FOUND);
            }
        }
        //Para ser possivel voltar a colocar a null
        this.count--;
        return removed;
    }

    private BinaryTreeNode<T> remove(BinaryTreeNode<T> parent, T targetElement) {

        int comparationParent = targetElement.compareTo(parent.element);
        BinaryTreeNode<T> current = comparationParent > 0 ? parent.right : parent.left;

        if (current != null && getRemovedElement() == null) {
            if (targetElement.compareTo(current.element) == 0) {
                setRemovedElement(current.element);
                if (parent.left == current) {
                    parent.left = replace(current);
                } else {
                    parent.right = replace(current);
                }
            } else if (comparationParent > 0) {
                parent.right = remove(current, targetElement);
            } else {
                parent.left = remove(current, targetElement);
            }
        }
        return balance(parent);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void removeAllOccurrences(T targetElement) throws ElementNotFoundException, EmptyCollectionException {

        removeElement(targetElement);

        boolean allRemoved = false;
        while (!allRemoved) {
            try {
                removeElement(targetElement);
            } catch (ElementNotFoundException | EmptyCollectionException e) {
                allRemoved = true;
            }
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T removeMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }
        T removed;

        if (this.root.left == null) {
            removed = this.root.element;
            this.root = this.root.right;
        } else {
            this.root = deleteMin(this.root);
            removed = getRemovedElement();
        }
        count--;
        return removed;
    }

    private BinaryTreeNode<T> deleteMin(BinaryTreeNode<T> parent) {
        if (parent.left.left == null) {
            setRemovedElement(parent.left.element);
            setRemovedNode(parent.left);
            if (parent.left.right == null) {
                parent.left = null;
            } else {
                parent.left = parent.left.right;
            }
        } else {
            deleteMin(parent.left);
        }
        return balance(parent);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T removeMax() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }
        T removed;

        if (this.root.right == null) {
            removed = this.root.element;
            this.root = root.left;
        } else {
            this.root = deleteMax(root);
            removed = getRemovedElement();
        }
        this.count--;
        return removed;
    }

    private BinaryTreeNode<T> deleteMax(BinaryTreeNode<T> parent) {

        if (parent.right.right == null) {
            setRemovedElement(parent.right.element);
            if (parent.right.left == null) {
                parent.right = null;
            } else {
                parent.right = parent.right.left;
            }
        } else {
            deleteMax(parent.right);
        }
        return balance(parent);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T findMin() throws EmptyCollectionException {

        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }

        BinaryTreeNode<T> current = this.root;

        if (this.root.left != null) {
            current = this.root.left;
            while (current.left != null) {
                current = current.left;
            }
        }
        return current.element;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T findMax() throws EmptyCollectionException {

        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }

        BinaryTreeNode<T> current = root;

        if (this.root.right != null) {
            current = this.root.right;
            while (current.right != null) {
                current = current.right;
            }
        }
        return current.element;
    }

}
