package com.example.arrayStack;

import com.example.exceptions.EmptyCollectionException;
import com.example.interfaces.StackADT;

import java.util.logging.*;

public class DemoArrayStack {

    public static void main(String[] args) {

        StackADT<Integer> cars = new ArrayStack<>(2);

        cars.push(1);
        cars.push(2);
        cars.push(3);
        cars.push(-9);
        cars.push(5);
        cars.push(6);

        System.out.println("Is empty: " + cars.isEmpty());

        try {
            System.out.println("Peek: " + cars.peek());
            System.out.println("Pop: " + cars.pop());
            System.out.println("Pop: " + cars.pop());
            System.out.println(cars.size());
        } catch (EmptyCollectionException ex) {
            Logger.getLogger(DemoArrayStack.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("\nAll elements:\n" + cars);
    }

}
