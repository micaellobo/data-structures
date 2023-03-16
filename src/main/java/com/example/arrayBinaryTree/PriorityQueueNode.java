package com.example.arrayBinaryTree;

/**
 * @author Micael
 */
public class PriorityQueueNode<T> implements Comparable<PriorityQueueNode<T>> {

    private static int nextOrder = 0;
    private final int priority;
    private final int order;
    private final T element;

    /**
     * Creates a new PriorityQueueNode with the specified data.
     *
     * @param elementAdd the element of the new priority queue node
     * @param prio       the integer priority of the new queue node
     */
    public PriorityQueueNode(T elementAdd, int prio) {
        this.element = elementAdd;
        this.priority = prio;
        this.order = ++nextOrder;
    }

    /**
     * Returns the element in this node.
     *
     * @return the element contained within this node
     */
    public T getElement() {
        return this.element;
    }

    /**
     * Returns the priority value for this node.
     *
     * @return the integer priority for this node
     */
    public int getPriority() {
        return this.priority;
    }

    /**
     * Returns the order for this node.
     *
     * @return the integer order for this node
     */
    public int getOrder() {
        return this.order;
    }

    /**
     * Returns a string representation for this node.
     */
    public String print() {
        return (this.element.toString() + "(" + this.priority + "," + this.order + ")");
    }

    /**
     * Returns the 1 if the current node has higher priority than the given node
     * and -1 otherwise.
     *
     * @param node the node to compare to this node
     * @return the integer result of the comparison of the obj node and this one
     */
    @Override
    public int compareTo(PriorityQueueNode<T> node) {
        if (this == node) {
            return 0;
        }

        int result = Integer.compare(this.priority, node.priority);
        if (result == 0) {
            result = Integer.compare(this.order, node.order);
        }
        return result;
    }

    @Override
    public String toString() {
        return print();
    }
}
