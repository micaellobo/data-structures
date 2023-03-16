package com.example.exceptions;

/**
 * @author Micael
 */
public class EmptyCollectionException extends Exception {

    public static final String EMPTY_COLLECTION = "The collection is empty";

    public EmptyCollectionException() {
    }

    /**
     * Constructs an instance of <code>EmptyCollectionException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EmptyCollectionException(String msg) {
        super(msg);
    }
}
