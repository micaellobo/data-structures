package com.example.arrayList;

import com.example.interfaces.OrderedListADT;

public class ArrayOrderedList<T extends Comparable<? super T>> extends AbstractArrayList<T> implements OrderedListADT<T> {

    public ArrayOrderedList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayOrderedList(int initialCapacity) {
        this.count = 0;
        this.front = 0;
        this.rear = 0;
        this.list = createNewArray(initialCapacity);
    }

    
    private T[] createNewArray(int size) {
        return (T[]) new Comparable[size];
    }

    protected void expandCapacity() {
        T[] aux = createNewArray(this.list.length * 2);
        System.arraycopy(this.list, 0, aux, 0, this.count);
        this.list = aux;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void add(T element) {

        if (element == null)
            throw new NullPointerException("The element to must not be null");

        if (this.rear == this.list.length)
            this.expandCapacity();

        //  !!! Comparison order in the While, avoid specific case
        int indexToAdd = 0;
        while (indexToAdd < this.count && this.list[indexToAdd].compareTo(element) < 0) {
            indexToAdd++;
        }
        shiftRight(indexToAdd);
        this.list[indexToAdd] = element;
        this.rear++;
        this.count++;
        this.modCount++;
    }

}
