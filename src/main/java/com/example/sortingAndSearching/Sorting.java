package com.example.sortingAndSearching;

import com.example.doubleLinkedList.DoubleNode;
import com.example.interfaces.UnorderedListADT;

public class Sorting {

    /**
     * Sorts the specified array of integers using the selection sort algorithm.
     *
     * @param <T>  parameterized type
     * @param data the array to be sorted
     */
    public static <T extends Comparable<? super T>> void selectionSort(T[] data) {
        int min;
        T temp;
        for (int index = 0; index < data.length - 1; index++) {
            min = index;
            for (int scan = index + 1; scan < data.length; scan++) {
                if (data[scan].compareTo(data[min]) < 0) {
                    min = scan;
                }
            }
            temp = data[min];
            data[min] = data[index];
            data[index] = temp;
        }
    }

    /**
     * Sorts the specified array of objects using an insertion sort algorithm.
     *
     * @param <T>  parameterized type
     * @param data the array to be sorted
     */
    public static <T extends Comparable<? super T>> void insertionSort(T[] data) {
        for (int index = 1; index < data.length; index++) {
            T key = data[index];
            int position = index;
            //Shift larger values to the right
            while (position > 0 && data[position - 1].compareTo(key) > 0) {
                data[position] = data[position - 1];
                position--;
            }
            data[position] = key;
        }
    }

    /**
     * Sorts the specified array of objects using a bubble sort algorithm.
     *
     * @param <T>  parameterized type
     * @param data the array to be sorted
     */
    public static <T extends Comparable<? super T>> void bubbleSort(T[] data) {
        int position, scan;
        T temp;
        for (position = data.length - 1; position >= 0; position--) {
            for (scan = 0; scan <= position - 1; scan++) {
                if (data[scan].compareTo(data[scan + 1]) > 0) {
                    //Swap the values
                    temp = data[scan];
                    data[scan] = data[scan + 1];
                    data[scan + 1] = temp;
                }
            }
        }
    }

    /**
     * Sorts the specified array of objects using the quick sort algorithm.
     *
     * @param <T>  parameterized type
     * @param data the array to be sorted
     */
    public static <T extends Comparable<? super T>> void quickSort(T[] data) {
        quickSort(data, 0, data.length - 1);
    }

    /**
     * Sorts the specified array of objects using the quick sort algorithm.
     *
     * @param <T>        parameterized type
     * @param data       the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex   the end index of the array
     */
    public static <T extends Comparable<? super T>> void quickSort(T[] data, int startIndex, int endIndex) {
        int indexOfPartition;
        if (endIndex - startIndex > 0) {
            // Create partitions
            indexOfPartition = findPartition(data, startIndex, endIndex);
            //Sort the left side
            quickSort(data, startIndex, indexOfPartition - 1);
            //Sort the right side
            quickSort(data, indexOfPartition + 1, endIndex);
        }
    }

    /**
     * Used by the quick sort algorithm to find the partition.
     *
     * @param <T>        parameterized type
     * @param data       the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex   the end index of the array
     */
    private static <T extends Comparable<? super T>> int findPartition(T[] data, int startIndex, int endIndex) {
        T pivot = data[endIndex];
        int i = (startIndex - 1); // index of smaller element
        for (int j = startIndex; j < endIndex; j++) {
            if (data[j].compareTo(pivot) <= 0) {
                i++;
                T temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }
        }
        // move partition element to partition index
        T temp = data[i + 1];
        data[i + 1] = data[endIndex];
        data[endIndex] = temp;

        return i + 1;
    }

    /**
     * Sorts the specified array of objects using the merge sort algorithm.
     *
     * @param <T>  parameterized type
     * @param data the array to be sorted
     */
    public static <T extends Comparable<? super T>> void mergeSort(T[] data) {
        mergeSort(data, 0, data.length - 1);
    }

    /**
     * Sorts the specified array of objects using the merge sort algorithm.
     *
     * @param <T>        parameterized type
     * @param data       the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex   the end index of the array
     */
    public static <T extends Comparable<? super T>> void mergeSort(T[] data, int startIndex, int endIndex) {

        T[] temp;
        int index1, left, right;
        if (startIndex == endIndex)
            return;

        // find the length and the midpoint of the list
        int size = endIndex - startIndex + 1;
        int pivot = (startIndex + endIndex) / 2;
        temp = (T[]) (new Comparable[size]);
        mergeSort(data, startIndex, pivot); // sort left half of list
        mergeSort(data, pivot + 1, endIndex); // sort right half of list

        // copy sorted data
        for (index1 = 0; index1 < size; index1++) {
            temp[index1] = data[startIndex + index1];
        }

        // merge the two sorted lists
        left = 0;
        right = pivot - startIndex + 1;
        for (index1 = 0; index1 < size; index1++) {
            if (right <= endIndex - startIndex) {
                if (left <= pivot - startIndex) {
                    if (temp[left].compareTo(temp[right]) > 0) {
                        data[index1 + startIndex] = temp[right++];
                    } else {
                        data[index1 + startIndex] = temp[left++];
                    }
                } else {
                    data[index1 + startIndex] = temp[right++];
                }
            } else {
                data[index1 + startIndex] = temp[left++];
            }
        }
    }

    /**
     * Sorts the specified array of objects using the heap sort algorithm.
     *
     * @param <T>  parameterized type
     * @param data the array to be sorted
     */
    public static <T extends Comparable<? super T>> void heapSort(T[] data) {
        int n = data.length, i;
        int low = n / 2 - 1, high = n - 1;
        // Build initial heap
        for (i = low; i >= 0; i--) {
            heapify(data, i, n);
        }
        for (i = high; i > 0; i--) {
            T temp = data[0];
            data[0] = data[i];
            data[i] = temp;
            heapify(data, 0, i);
        }
    }

    /**
     * Sort the heap
     *
     * @param <T>      parameterized type
     * @param data     the array to be sorted
     * @param position in the heap
     * @param size     of the actual data
     */
    private static <T extends Comparable<? super T>> void heapify(T[] data, int position, int size) {
        int low = 2 * position + 1;
        int high = 2 * position + 2;
        int largest;
        T temp;
        if (low < size && data[low].compareTo(data[position]) > 0) {
            largest = low;
        } else {
            largest = position;
        }
        if (high < size && data[high].compareTo(data[largest]) > 0) {
            largest = high;
        }
        if (largest != position) {
            temp = data[largest];
            data[largest] = data[position];
            data[position] = temp;
            heapify(data, largest, size);
        }
    }
}
