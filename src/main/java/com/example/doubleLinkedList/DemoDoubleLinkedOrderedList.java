package com.example.doubleLinkedList;

import com.example.exceptions.*;

import java.util.Iterator;
import java.util.logging.*;

/**
 * @author Micael
 */
public class DemoDoubleLinkedOrderedList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        DoubleLinkedOrderedList<String> list = new DoubleLinkedOrderedList<>();

        list.add("-1");
        list.add("2");
        list.add("9");
        list.add("4");
        list.add("5");
        list.add("7");
        list.add("1");
        list.add("3");
        list.add("6");
        list.add("8");

        try {
            list.remove("4");
            list.removeFirst();
            list.removeLast();
        } catch (EmptyCollectionException | ElementNotFoundException ex) {
            Logger.getLogger(DemoDoubleLinkedOrderedList.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            Iterator itr = list.iterator();
            Iterator itr2 = list.iterator();

        } catch (RuntimeException ex) {
            Logger.getLogger(DemoDoubleLinkedOrderedList.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(list);

        list.invertList();

        System.out.println(list);

        list.invertList();

        System.out.println(list);

        list.invertList();

        System.out.println(list);

        System.out.println(list.invertPrint());

    }
}
