package com.example.doubleLinkedList;

import com.example.interfaces.OrderedListADT;

import java.util.NoSuchElementException;

public class DoubleLinkedOrderedList<T extends Comparable<? super T>> extends AbstractDoubleLinkedList<T> implements OrderedListADT<T> {

    public DoubleLinkedOrderedList() {
        super();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void add(T element) {

        if (element == null) {
            throw new NoSuchElementException("The element to add should not be null");
        }
        boolean placed = false;

        DoubleNode<T> newNode = new DoubleNode<>(element);
        DoubleNode<T> current = this.front.getNext();

        while (current != this.rear && !placed && current.getElement().compareTo(element) >= 0) {
            current = current.getNext();
        }

        newNode.setPrevious(current.getPrevious());
        newNode.setNext(current);
        current.getPrevious().setNext(newNode);
        current.setPrevious(newNode);
        this.modCount++;
        this.count++;
    }

    @Override
    protected String print() {
        return super.print();
    }

    protected void invertList() {
        DoubleNode<T> itrHead = this.front.getNext();
        DoubleNode<T> itrTail = this.rear.getPrevious();
        int i = this.count / 2;
        while (i != 0) {
            T aux = itrHead.getElement();

            itrHead.setElement(itrTail.getElement());
            itrTail.setElement(aux);

            itrHead = itrHead.getNext();
            itrTail = itrTail.getPrevious();
            i--;
        }

    }

    protected String invertPrint() {
        StringBuilder s = new StringBuilder();

        DoubleNode<T> current = this.rear.getPrevious();
        while (current != this.front) {
            s.append(current.getElement()).append(" ");
            current = current.getPrevious();
        }
        return s.toString();
    }

}
