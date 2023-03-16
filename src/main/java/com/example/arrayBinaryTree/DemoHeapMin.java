package com.example.arrayBinaryTree;

import com.example.exceptions.EmptyCollectionException;

import java.util.Iterator;

/**
 * @author Micael
 */
public class DemoHeapMin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws EmptyCollectionException {

        PriorityArrayQueue<String> heap = new PriorityArrayQueue<>(10);

        heap.addElement("MIcael", 30);
        heap.addElement("Pedro", 20);
        heap.addElement("Carlos", 50);
        heap.addElement("Daniel", 100);
        heap.addElement("Vasco", 10);

        for (Iterator<PriorityQueueNode<String>> iterator = heap.iteratorLevelOrder(); iterator.hasNext(); ) {
            PriorityQueueNode<String> aux = iterator.next();
            System.out.println(aux.getElement() + " " + aux.getPriority());
        }

        heap.removeNext();
        System.out.println("\n\n");
        for (Iterator<PriorityQueueNode<String>> iterator = heap.iteratorLevelOrder(); iterator.hasNext(); ) {
            PriorityQueueNode<String> aux = iterator.next();
            System.out.println(aux.getElement() + " " + aux.getPriority());
        }
        heap.removeNext();
        System.out.println("\n\n");

        for (Iterator<PriorityQueueNode<String>> iterator = heap.iteratorLevelOrder(); iterator.hasNext(); ) {
            PriorityQueueNode<String> aux = iterator.next();
            System.out.println(aux.getElement() + " " + aux.getPriority());
        }
        heap.removeNext();
        System.out.println("\n\n");

        for (Iterator<PriorityQueueNode<String>> iterator = heap.iteratorLevelOrder(); iterator.hasNext(); ) {
            PriorityQueueNode<String> aux = iterator.next();
            System.out.println(aux.getElement() + " " + aux.getPriority());
        }
        heap.removeNext();
        System.out.println("\n\n");

        for (Iterator<PriorityQueueNode<String>> iterator = heap.iteratorLevelOrder(); iterator.hasNext(); ) {
            PriorityQueueNode<String> aux = iterator.next();
            System.out.println(aux.getElement() + " " + aux.getPriority());
        }
    }

}
