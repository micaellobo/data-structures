package com.example.binaryTree;

/**
 * @author Micael
 */
public class HeapNode<T> extends BinaryTreeNode<T> {

    protected HeapNode<T> parent;

    /**
     * Creates a new heap node with the specified data.
     *
     * @param element the data to be contained within the new heap nodes
     */
    HeapNode(T element) {
        super(element);
        this.parent = null;
    }
}
