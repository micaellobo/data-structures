package com.example.arrayQueue;

import com.example.exceptions.EmptyCollectionException;
import com.example.interfaces.QueueADT;

import java.util.logging.*;

public class DemoArrayQueue {

    public static void main(String[] args) {

        QueueADT<String> aux = new CircularArrayQueue<>();

        try {
            aux.enqueue("1");
            aux.enqueue("2");
            aux.enqueue("3");
            aux.enqueue("4");
            aux.enqueue("5");
            aux.enqueue("6");
//
            aux.dequeue();
//            aux.dequeue();
//            aux.dequeue();

//            
//            
            aux.enqueue("7");
//            aux.enqueue("8");
//            aux.enqueue("9");
//            aux.enqueue("10");
//            aux.enqueue("11");
//            aux.enqueue("12");
//            aux.enqueue("13");
//            aux.enqueue("14");
            aux.dequeue();
            System.out.println("Size: " + aux.size() + "\n");
        } catch (EmptyCollectionException ex) {
            Logger.getLogger(DemoArrayQueue.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.print(aux);
    }

}
