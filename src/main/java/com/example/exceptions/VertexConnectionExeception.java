package com.example.exceptions;

/**
 * @author Micael
 */
public class VertexConnectionExeception extends Exception {

    public static String ALREADY_CONNECTED = "Those vertices are already connected";
    public static String NO_CONNECTION = "Those vertices are not connected";
    public static String NEGATIVE_WEIGHT = "Negative weight is not allowed";

    /**
     * Creates a new instance of <code>VertexConnectionExeception</code> without
     * detail message.
     */
    public VertexConnectionExeception() {
    }

    /**
     * Constructs an instance of <code>VertexConnectionExeception</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public VertexConnectionExeception(String msg) {
        super(msg);
    }
}
