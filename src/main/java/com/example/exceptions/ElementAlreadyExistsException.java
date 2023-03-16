package com.example.exceptions;

/**
 * @author Micael
 */
public class ElementAlreadyExistsException extends Exception {

    public static String ELEMENT_ALREADY_EXISTS = "The element already exists";

    /**
     * Creates a new instance of <code>ElementAlreadyExists</code> without
     * detail message.
     */
    public ElementAlreadyExistsException() {
    }

    /**
     * Constructs an instance of <code>ElementAlreadyExists</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ElementAlreadyExistsException(String msg) {
        super(msg);
    }
}
