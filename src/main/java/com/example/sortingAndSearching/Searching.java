package com.example.sortingAndSearching;


public class Searching {

    /**
     * Searches the specified array of objects using a linear search algorithm.
     *
     * @param <T>    parameterized type
     * @param data   the array to be sorted
     * @param target the element being searched for
     * @return true if the desired element is found
     */
    public static <T extends Comparable<? super T>> boolean linearSearch(T[] data, T target) {
        for (T item : data) {
            if (item.compareTo(target) == 0)
                return true;
        }
        return false;
    }

    /**
     * Searches the specified array of objects using a binary search algorithm.
     *
     * @param <T>    parameterized type
     * @param data   the array to be sorted
     * @param target the element being searched for
     * @return true if the desired element is found
     */
    public static <T extends Comparable<? super T>> boolean binarySearch(T[] data, T target) {
        return binarySearch(data, 0, data.length - 1, target);
    }

    /**
     * Searches the specified array of objects using a binary search algorithm.
     *
     * @param <T>        parameterized type
     * @param data       the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex   the end index of the array
     * @param target     the element being searched for
     * @return true if the desired element is found
     */
    public static <T extends Comparable<? super T>> boolean binarySearch(T[] data, int startIndex, int endIndex, T target) {
        boolean found = false;
        int midpoint = (startIndex + endIndex) / 2;
        int compare = target.compareTo(data[midpoint]);

        if (compare == 0) {
            found = true;
        } else if (compare < 0) {
            if (startIndex <= midpoint - 1) {
                found = binarySearch(data, startIndex, midpoint - 1, target);
            }
        } else if (midpoint + 1 <= endIndex) {
            found = binarySearch(data, midpoint + 1, endIndex, target);
        }
        return found;
    }
}
