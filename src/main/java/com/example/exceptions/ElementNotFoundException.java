package com.example.exceptions;

/**
 * @author Micael
 */
public class ElementNotFoundException extends Exception {

    public static final String ELEMENT_NOT_FOUND = "Element not found";
    public static final String TARGET_NOT_FOUND = "Target element does no exist";

    /**
     * Creates a new instance of <code>ElementNotFoundException</code> without
     * detail message.
     */
    public ElementNotFoundException() {
    }

    /**
     * Constructs an instance of <code>ElementNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ElementNotFoundException(String msg) {
        super(msg);
    }
}
