package com.example.binaryTree;

import com.example.arrayList.ArrayUnorderedList;
import com.example.exceptions.*;
import com.example.interfaces.*;
import com.example.list.LinkedUnorderedList;
import com.example.queue.LinkedQueue;

import java.util.*;
import java.util.logging.*;

/**
 * @author Micael
 */
public class DemoLinkedBinaryTree implements Comparable<DemoLinkedBinaryTree> {

    String name;
    UnorderedListADT<String> aux;

    public DemoLinkedBinaryTree(String name) {
        this.name = name;
        this.aux = new LinkedUnorderedList<>();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DemoLinkedBinaryTree other = (DemoLinkedBinaryTree) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public int compareTo(DemoLinkedBinaryTree o) {
        return name.compareTo(o.name);
    }

    public static void printSearchTree(BinarySearchTreeADT tree, Iterator itr) {
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
        System.out.println();
    }

    public static <T> int getFactor(BinaryTreeNode<T> node) {
        int heightRight = node.right == null ? -1 : node.right.height;
        int heightLeft = node.left == null ? -1 : node.left.height;
        return heightRight - heightLeft;
    }

    public static <T extends Comparable<? super T>> void printTreeHeight(LinkedBinarySearchTree<T> tree) {

        LinkedQueue<BinaryTreeNode<T>> nodes = new LinkedQueue<>();
        ArrayUnorderedList<String> resultList = new ArrayUnorderedList<>();

        nodes.enqueue(tree.root);

        while (!nodes.isEmpty()) {
            try {
                BinaryTreeNode<T> dequeueNode = nodes.dequeue();
                if (dequeueNode != null) {
                    nodes.enqueue(dequeueNode.left);
                    nodes.enqueue(dequeueNode.right);
                    resultList.addToRear(dequeueNode.element + "(" + getFactor(dequeueNode) + ")");
                }
            } catch (EmptyCollectionException ex) {
                Logger.getLogger(LinkedBinaryTree.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (String s : resultList) {
            System.out.print(s + " ");
        }
    }

    static void testeTP() {
        LinkedBinarySearchTree<DemoLinkedBinaryTree> tree = new LinkedBinarySearchTree<>();

        DemoLinkedBinaryTree aux1 = new DemoLinkedBinaryTree("Pata de coelho");
        DemoLinkedBinaryTree aux2 = new DemoLinkedBinaryTree("Micael");
        DemoLinkedBinaryTree aux3 = new DemoLinkedBinaryTree("Flavio");

        aux1.aux.addToRear("1");
        aux1.aux.addToRear("2");
        aux1.aux.addToRear("3");

        aux2.aux.addToRear("11");
        aux2.aux.addToRear("12");
        aux2.aux.addToRear("13");

        aux3.aux.addToRear("21");
        aux3.aux.addToRear("22");
        aux3.aux.addToRear("23");

        tree.addElement(aux1);
        tree.addElement(aux2);
        tree.addElement(aux3);

        for (Iterator<DemoLinkedBinaryTree> iterator = tree.iteratorLevelOrder(); iterator.hasNext(); ) {
            DemoLinkedBinaryTree aux = iterator.next();
            System.out.println("Node: " + aux.name);
            for (String object : aux.aux) {
                System.out.print(object + " ");
            }
            System.out.println("\n");
        }

        try {
            tree.find(new DemoLinkedBinaryTree("Pata de coelho")).aux.addToRear("4");
            tree.find(new DemoLinkedBinaryTree("Micael")).aux.addToRear("4");
        } catch (ElementNotFoundException ex) {
            Logger.getLogger(DemoLinkedBinaryTree.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("After add: \n");
        for (Iterator<DemoLinkedBinaryTree> iterator = tree.iteratorLevelOrder(); iterator.hasNext(); ) {
            DemoLinkedBinaryTree aux = iterator.next();
            System.out.println("Node: " + aux.name);
            for (String object : aux.aux) {
                System.out.print(object + " ");
            }
            System.out.println("\n");
        }
    }

    static void demo() {
        LinkedBinarySearchTree<Integer> tree = new LinkedBinarySearchTree<>();

        tree.addElement(8);
        tree.addElement(4);
        tree.addElement(2);
        tree.addElement(6);
        tree.addElement(1);
        tree.addElement(3);
        tree.addElement(5);
        tree.addElement(7);
        tree.addElement(12);
        tree.addElement(10);
        tree.addElement(14);
        tree.addElement(9);
        tree.addElement(11);
        tree.addElement(13);
        tree.addElement(15);

        System.out.println("Find: " + tree.contains(15));

        /**
         * ---------------------------------8-----------------------------------
         * -----------------4------------------------------12-------------------
         * ------------2--------6--------------------10-------------14----------
         * --------1------3---5---7-------------9--------11------13-----15------
         */
//        try {
//            System.out.println("remove: " + tree.removeElement(1));
//            System.out.println("remove: " + tree.removeElement(2));
//            System.out.println("remove: " + tree.removeElement(3));
//            System.out.println("remove: " + tree.removeElement(4));
//            System.out.println("remove: " + tree.removeElement(5));
//            System.out.println("remove: " + tree.removeElement(6));
//        } catch (EmptyCollectionException | ElementNotFoundException ex) {
//            System.out.println(ex.getMessage());
//        }
        DemoLinkedBinaryTree.printTreeHeight(tree);
    }

    static void teste1() {
        LinkedBinarySearchTree<Integer> tree = new LinkedBinarySearchTree<>();

        tree.addElement(6);
        tree.addElement(45);
        tree.addElement(32);
//        tree.addElement(95);
//        tree.addElement(55);
//        tree.addElement(69);

        DemoLinkedBinaryTree.printTreeHeight(tree);
    }

    public static void main(String[] args) {
        teste1();
    }

}
