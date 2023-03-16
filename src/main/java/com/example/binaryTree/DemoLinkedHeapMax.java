package com.example.binaryTree;

import com.example.exceptions.EmptyCollectionException;
import com.example.interfaces.HeapMaxADT;

import java.util.Iterator;
import java.util.logging.*;

public class DemoLinkedHeapMax {

    public static <T extends Comparable<? super T>> void printHeap(LinkedHeapMin<T> heap) {
        for (Iterator<T> iterator = heap.iteratorLevelOrder(); iterator.hasNext(); ) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        HeapMaxADT<Integer> heap = new LinkedHeapMax<>();

        heap.addElement(1);
        heap.addElement(3);
        heap.addElement(4);
        heap.addElement(2);
        heap.addElement(5);
        heap.addElement(6);
        heap.addElement(7);
        heap.addElement(8);
        heap.addElement(9);
        heap.addElement(10);
        heap.addElement(11);
        heap.addElement(12);
        heap.addElement(13);
        heap.addElement(14);
        heap.addElement(15);

        while (!heap.isEmpty()) {
            try {
                System.out.print(heap.removeMax() + " ");
            } catch (EmptyCollectionException ex) {
                Logger.getLogger(DemoLinkedHeapMax.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//        DemoLinkedHeapMin.<Integer>printHeap((LinkedHeapMin<Integer>) heap);
    }

}
