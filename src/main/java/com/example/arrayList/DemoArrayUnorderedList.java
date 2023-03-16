package com.example.arrayList;

import com.example.exceptions.*;
import com.example.interfaces.UnorderedListADT;

import java.util.logging.*;

/**
 * @author Micael
 */
public class DemoArrayUnorderedList {


    public static void main(String[] args) {

        UnorderedListADT<Integer> list = new ArrayUnorderedList<>();

        list.addToFront(1);
        list.addToFront(2);
        list.addToFront(3);

        list.addToRear(4);
        list.addToRear(5);
        list.addToRear(6);

        try {
            list.addAfter(0, 3);
        } catch (EmptyCollectionException | ElementNotFoundException ex) {
            Logger.getLogger(DemoArrayUnorderedList.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            list.remove(1);
        } catch (EmptyCollectionException | ElementNotFoundException ex) {
            Logger.getLogger(DemoArrayUnorderedList.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println();
        System.out.println(list);
    }

}
