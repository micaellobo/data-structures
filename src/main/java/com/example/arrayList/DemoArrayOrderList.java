package com.example.arrayList;

import com.example.exceptions.*;
import com.example.interfaces.OrderedListADT;

import java.util.logging.*;

/**
 * @author Micael
 */
public class DemoArrayOrderList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        OrderedListADT<String> list = new ArrayOrderedList<>();

        list.add("5");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("1");
        list.add("6");
        list.add("9");
        list.add("8");
        list.add("7");
        list.add("0");
        list.add("-1");

        try {
            list.removeFirst();

            list.remove("4");

            list.removeLast();

            System.out.println(list);

            list.add("4");

            for (String string : list) {
                System.out.print(string + " ");
            }
            System.out.println();

        } catch (EmptyCollectionException | ElementNotFoundException ex) {
            Logger.getLogger(DemoArrayOrderList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
