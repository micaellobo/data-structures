package com.example.doubleLinkedList;

import com.example.exceptions.*;
import com.example.interfaces.UnorderedListADT;

public class DoubleLinkedUnorderedList<T> extends AbstractDoubleLinkedList<T> implements UnorderedListADT<T> {

    public DoubleLinkedUnorderedList() {
        super();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addToRear(T element) {
        fastAdd(element, this.rear.getPrevious());
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addToFront(T element) {
        fastAdd(element, this.front);
    }

    private void fastAdd(T element, DoubleNode<T> targetNode) {

        DoubleNode<T> newNode = new DoubleNode<>(element);

        newNode.setNext(targetNode.getNext());
        newNode.setPrevious(targetNode);
        targetNode.getNext().setPrevious(newNode);
        targetNode.setNext(newNode);
        this.modCount++;
        this.count++;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addAfter(T element, T target) throws EmptyCollectionException, ElementNotFoundException {

        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }

        boolean found = false;
        DoubleNode<T> targetNode = new DoubleNode<>();

        DoubleNode<T> current = this.front.getNext();
        while (current != this.rear && !found) {
            if (current.getElement().equals(target)) {
                targetNode = current;
                found = true;
            }
            current = current.getNext();
        }
        if (!found) {
            throw new ElementNotFoundException(ElementNotFoundException.ELEMENT_NOT_FOUND);
        }
        fastAdd(element, targetNode);
    }

    @Override
    protected String print() {
        return super.print();
    }

    protected String printBack() {
        StringBuilder s = new StringBuilder();

        DoubleNode<T> current = this.rear.getPrevious();
        while (current != this.front) {
            s.append(current.getElement()).append(" ");
            current = current.getPrevious();
        }
        return s.toString();
    }
}
