package com.example.list;

import com.example.arrayList.ArrayUnorderedList;
import com.example.exceptions.EmptyCollectionException;
import com.example.interfaces.*;
import com.example.stack.LinkedStack;

/**
 * @author Micael
 */
public class DemoLinkedOrderedList {

    public static void main(String[] args) {

        OrderedListADT<Integer> list = new LinkedOrderedList<>();

        list.add(8);
        list.add(4);
        list.add(2);
        list.add(6);
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(7);
        list.add(12);
        list.add(10);
        list.add(14);
        list.add(9);
        list.add(11);
        list.add(13);
        list.add(15);

        UnorderedListADT<Integer> temp = new ArrayUnorderedList<>(list.size());
        StackADT<Integer> stack = new LinkedStack<>();
        for (Integer integer : list) {
            stack.push(integer);
        }
        while (!stack.isEmpty()) {
            try {
                temp.addToRear(stack.pop());
            } catch (EmptyCollectionException ex) {
            }
        }
        for (Integer integer : temp) {
            System.out.print(integer + " ");
        }

    }

}
