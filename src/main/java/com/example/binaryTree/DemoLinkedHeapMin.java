package com.example.binaryTree;

import com.example.arrayBinaryTree.ArrayHeapMin;
import com.example.exceptions.EmptyCollectionException;
import com.example.interfaces.HeapMinADT;

import java.util.Iterator;
import java.util.logging.*;

public class DemoLinkedHeapMin {

    public static <T> void printHeap(HeapMinADT<T> heap) {
        for (Iterator<T> iterator = heap.iteratorLevelOrder(); iterator.hasNext(); ) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        HeapMinADT<String> heap = new ArrayHeapMin<>(9);

        heap.addElement("Chuck");
        heap.addElement("Sarah");
        heap.addElement("Casey");
        heap.addElement("Morgan");
        heap.addElement("Beckam");
        heap.addElement("Awesome");
        heap.addElement("Ellie");
        heap.addElement("Jeff");
        heap.addElement("Lester");

        try {
            heap.removeMin();
            heap.removeMin();
            printHeap(heap);
        } catch (EmptyCollectionException ex) {
            Logger.getLogger(DemoLinkedHeapMin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
