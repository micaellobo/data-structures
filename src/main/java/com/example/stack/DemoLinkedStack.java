package com.example.stack;

import com.example.exceptions.EmptyCollectionException;

import java.util.logging.*;

public class DemoLinkedStack {

    public static void main(String[] args) {

        LinkedStack<Integer> cars = new LinkedStack<>();

        cars.push(1);
        cars.push(2);
        cars.push(3);
        cars.push(-9);
        cars.push(5);
        cars.push(6);

        try {
            System.out.println("Pop: " + cars.pop());
            System.out.println("Pop: " + cars.pop());
            System.out.println("Pop: " + cars.pop());
            System.out.println("Pop: " + cars.pop());
            System.out.println("Pop: " + cars.pop());
            Object aux = cars.pop();
            System.out.println("Pop: " + aux);

            cars.push(10);
            cars.push(11);
            System.out.println(cars.size());
        } catch (EmptyCollectionException ex) {
            Logger.getLogger(DemoLinkedStack.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("\nPrint:\n" + cars);
    }

}
