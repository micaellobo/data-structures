package com.example.arrayBinaryTree;

import com.example.exceptions.EmptyCollectionException;
import com.example.interfaces.HeapMinADT;

/**
 * ArrayHeap provides an array implementation of a minHeap.
 */
public class ArrayHeapMin<T extends Comparable<? super T>> extends ArrayBinaryTree<T> implements HeapMinADT<T> {

    public ArrayHeapMin(int capacity) {
        this.count = 0;
        this.tree = createNewArray(capacity);
    }

    public ArrayHeapMin(T element) {
        this.count = 1;
        this.tree = createNewArray(DEFAULT_CAPACITY);
        this.tree[0] = element;
    }

    public ArrayHeapMin() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addElement(T obj) {
        if (obj == null) {
            throw new ClassCastException("The element to add most not be null");
        }
        if (this.count == this.tree.length) {
            expandCapacity();
        }
        this.tree[this.count++] = obj;
        if (this.count > 1) {
            heapifyAdd();
        }
    }

    
    private T[] createNewArray(int size) {
        return (T[]) new Comparable[size];
    }

    /**
     * Expand the capacity of the array Heap
     */
    private void expandCapacity() {
        T[] tempTree = createNewArray(this.tree.length * 2);
        System.arraycopy(tree, 0, tempTree, 0, this.count);
        this.tree = tempTree;
    }

    /**
     * Adds the specified element to this heap in the appropriate position
     * according to its key value. Note that equal elements are added to the
     * right.
     */
    protected void heapifyAdd() {

        int currentIndex = this.count - 1;
        int parentIndex = (currentIndex - 1) / 2;

        while ((currentIndex != 0) && (this.tree[currentIndex].compareTo(this.tree[parentIndex]) < 0)) {
            T temp = this.tree[currentIndex];
            this.tree[currentIndex] = this.tree[parentIndex];
            this.tree[parentIndex] = temp;
            currentIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
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
        T minElement = this.tree[0];
        this.tree[0] = this.tree[count - 1];
        heapifyRemove();
        this.count--;
        return minElement;
    }

    /**
     * Reorders this heap to maintain the ordering property.
     */
    private void heapifyRemove() {
        int indexParent = 0;
        int indexLeft = 1;
        int indexRight = 2;
        int indexNext;
        if ((this.tree[indexLeft] == null) && (tree[indexRight] == null)) {
            indexNext = this.count;
        } else if (this.tree[indexLeft] == null) {
            indexNext = indexRight;
        } else if (this.tree[indexRight] == null) {
            indexNext = indexLeft;
        } else {
            indexNext = this.tree[indexLeft].compareTo(this.tree[indexRight]) < 0 ? indexLeft : indexRight;
        }
        while ((indexNext < this.count) && indexLeft < this.count && indexRight < this.count && ((Comparable) this.tree[indexNext]).compareTo(this.tree[indexParent]) < 0) {
            if (this.tree[indexNext].compareTo(this.tree[indexParent]) < 0) {
                T temp = this.tree[indexNext];
                this.tree[indexNext] = this.tree[indexParent];
                this.tree[indexParent] = temp;

                indexParent = indexNext;

                indexLeft = (2 * indexParent) + 1;
                indexRight = 2 * (indexParent + 1);

                if (indexLeft < this.count && indexRight < this.count) {
                    if ((this.tree[indexLeft] == null) && (this.tree[indexRight] == null)) {
                        indexNext = this.count;
                    } else if (this.tree[indexLeft] == null) {
                        indexNext = indexRight;
                    } else if (this.tree[indexRight] == null) {
                        indexNext = indexLeft;
                    } else {
                        indexNext = this.tree[indexLeft].compareTo(this.tree[indexRight]) < 0 ? indexLeft : indexRight;
                    }
                }
            }
        }
        this.tree[this.count - 1] = null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T findMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException(EmptyCollectionException.EMPTY_COLLECTION);
        }
        return this.tree[0];
    }
}
