package com.example.arrayBinaryTree;

import com.example.exceptions.EmptyCollectionException;
import com.example.interfaces.PriorityQueueADT;

import java.util.logging.*;

/**
 * @author Micael
 */
public class DemoPriorityQueue {

    public static void main(String[] args) {

        PriorityQueueADT<String> minHeap = new PriorityArrayQueue<>();

        minHeap.addElement("cli1", 3);
        minHeap.addElement("cli2", 3);
        minHeap.addElement("gra2", 1);
        minHeap.addElement("rodas2", 2);
        minHeap.addElement("gra3", 1);
        minHeap.addElement("cli3", 3);
        minHeap.addElement("rodas1", 2);
        minHeap.addElement("gra1", 1);
        try {
            System.out.println(minHeap.removeNext());
            System.out.println(minHeap.removeNext());
            System.out.println(minHeap.removeNext());
            System.out.println(minHeap.removeNext());
            System.out.println(minHeap.removeNext());
            System.out.println(minHeap.removeNext());
            System.out.println(minHeap.removeNext());
            System.out.println(minHeap.removeNext());
            System.out.println(minHeap.removeNext());
        } catch (EmptyCollectionException ex) {
            Logger.getLogger(DemoPriorityQueue.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        System.out.println(minHeap);
    }

}
