package com.example.arrayBinaryTree;

import com.example.exceptions.EmptyCollectionException;
import com.example.interfaces.PriorityQueueADT;

/**
 * @author Micael
 */
public class PriorityArrayQueue<T> extends ArrayHeapMin<PriorityQueueNode<T>> implements PriorityQueueADT<T> {

    public PriorityArrayQueue() {
        super();
    }

    public PriorityArrayQueue(PriorityQueueNode<T> element) {
        super(element);
    }

    public PriorityArrayQueue(int capacity) {
        super(capacity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addElement(T element, int priority) {
        super.addElement(new PriorityQueueNode<>(element, priority));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T removeNext() throws EmptyCollectionException {
        return super.removeMin().getElement();
    }
}
