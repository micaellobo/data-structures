package com.example.arrayList;

import com.example.exceptions.*;
import com.example.interfaces.UnorderedListADT;

/**
 * @author Micael
 */
public class ArrayUnorderedList<T> extends AbstractArrayList<T> implements UnorderedListADT<T> {

    public ArrayUnorderedList() {
        super();
    }

    public ArrayUnorderedList(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addToFront(T element) {

        if (this.rear == this.list.length) {
            super.expandCapacity();
        }
        shiftRight(this.front);
        this.list[front] = element;
        this.rear++;
        this.count++;
        this.modCount++;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addToRear(T element) {

        if (this.rear == this.list.length) {
            super.expandCapacity();
        }
        this.list[this.rear] = element;
        this.rear++;
        this.count++;
        this.modCount++;
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
        int indexToAdd = 0;
        while (indexToAdd < this.rear && !found) {
            if (this.list[indexToAdd].equals(target)) {
                found = true;
            }
            indexToAdd++;
        }

        if (!found) {
            throw new ElementNotFoundException(ElementNotFoundException.TARGET_NOT_FOUND);
        }

        if (this.rear == this.list.length) {
            super.expandCapacity();
        }
        shiftRight(indexToAdd);
        this.list[indexToAdd] = element;
        this.rear++;
        this.count++;
        this.modCount++;
    }

}
