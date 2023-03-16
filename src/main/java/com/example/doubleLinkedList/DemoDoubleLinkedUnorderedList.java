package com.example.doubleLinkedList;

import com.example.exceptions.*;
import com.example.interfaces.UnorderedListADT;

import java.util.logging.*;

public class DemoDoubleLinkedUnorderedList {

    public static void main(String[] args) {

        UnorderedListADT<Integer> list = new DoubleLinkedUnorderedList<>();

        list.addToFront(1);
        list.addToFront(2);
        list.addToFront(3);
        list.addToFront(5);

        System.out.println(list);

        try {
            list.addAfter(4, 3);
            list.addToRear(0);
            list.addToFront(8);
            list.addToRear(-1);
            list.removeFirst();
            list.removeLast();
        } catch (EmptyCollectionException | ElementNotFoundException ex) {
            Logger.getLogger(DemoDoubleLinkedUnorderedList.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("\n" + list);

        for (Integer integer : list) {
            System.out.print(integer + " ");
        }
        System.out.println();
    }

}
