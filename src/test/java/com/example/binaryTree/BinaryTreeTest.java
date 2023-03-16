package com.example.binaryTree;

import com.example.exceptions.*;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;


public class BinaryTreeTest {

    /**
     * Remover quando a direita é null, com rotação direita esquerda Slide 50
     */
    @Test
    void removeMinRightNull_AndRotateRightLeft() {

        LinkedBinarySearchTree<Integer> expected = new LinkedBinarySearchTree<>();

        expected.addElement(13);
        expected.addElement(10);
        expected.addElement(15);
        expected.addElement(5);
        expected.addElement(11);
        expected.addElement(17);

        LinkedBinarySearchTree<Integer> actual = new LinkedBinarySearchTree<>();

        actual.addElement(10);
        actual.addElement(5);
        actual.addElement(15);
        actual.addElement(3);
        actual.addElement(13);
        actual.addElement(17);
        actual.addElement(11);

        assertDoesNotThrow(actual::removeMin);
        assertEquals(expected.size(), actual.size());
        assertEquals(13, actual.root.element);
        assertEquals(10, actual.root.left.element);
        assertEquals(15, actual.root.right.element);

        Iterator<Integer> itrExpected = expected.iteratorInOrder();
        Iterator<Integer> itrActual = actual.iteratorInOrder();

        while (itrExpected.hasNext()) {
            Integer expec = itrExpected.next();
            Integer actu = itrActual.next();
            assertEquals(expec, actu);
        }
    }

    /**
     * Remover quando a direita não é null, Slide 24 1º remove
     */
    @Test
    void removeMinRightNotNull() {

        LinkedBinarySearchTree<Integer> expected = new LinkedBinarySearchTree<>();

        expected.addElement(10);
        expected.addElement(15);
        expected.addElement(7);
        expected.addElement(13);

        LinkedBinarySearchTree<Integer> actual = new LinkedBinarySearchTree<>();

        actual.addElement(10);
        actual.addElement(5);
        actual.addElement(15);
        actual.addElement(3);
        actual.addElement(7);
        actual.addElement(13);

        assertDoesNotThrow(actual::removeMin);
        assertDoesNotThrow(actual::removeMin);

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.root.element, actual.root.element);

        Iterator<Integer> itrExpected = expected.iteratorInOrder();
        Iterator<Integer> itrActual = actual.iteratorInOrder();
        while (itrExpected.hasNext()) {
            Integer expec = itrExpected.next();
            Integer actu = itrActual.next();
            assertEquals(expec, actu);
        }
    }

    /**
     * Quando o min é o root
     */
    @Test
    void removeMinRoot() {

        LinkedBinarySearchTree<Integer> expected = new LinkedBinarySearchTree<>();

        expected.addElement(15);

        LinkedBinarySearchTree<Integer> actual = new LinkedBinarySearchTree<>();

        actual.addElement(10);
        actual.addElement(15);

        assertDoesNotThrow(actual::removeMin);

        assertEquals(expected.size(), actual.size());
        assertEquals(15, actual.root.element);

        Iterator<Integer> itrExpected = expected.iteratorInOrder();
        Iterator<Integer> itrActual = actual.iteratorInOrder();

        while (itrExpected.hasNext()) {
            Integer expec = itrExpected.next();
            Integer actu = itrActual.next();
            assertEquals(expec, actu);
        }
    }

    /**
     * Quando o max quando esquerda não é null, Slide 24
     */
    @Test
    void removeMaxLeftNotNull() {

        LinkedBinarySearchTree<Integer> expected = new LinkedBinarySearchTree<>();

        expected.addElement(10);
        expected.addElement(5);
        expected.addElement(13);
        expected.addElement(3);
        expected.addElement(7);

        LinkedBinarySearchTree<Integer> actual = new LinkedBinarySearchTree<>();

        actual.addElement(10);
        actual.addElement(5);
        actual.addElement(15);
        actual.addElement(3);
        actual.addElement(7);
        actual.addElement(13);

        assertDoesNotThrow(actual::removeMax);

        assertEquals(expected.size(), actual.size());
        assertEquals(10, actual.root.element);

        Iterator<Integer> itrExpected = expected.iteratorInOrder();
        Iterator<Integer> itrActual = actual.iteratorInOrder();

        while (itrExpected.hasNext()) {
            Integer expec = itrExpected.next();
            Integer actu = itrActual.next();
            assertEquals(expec, actu);
        }
    }

    /**
     * Quando o max quando esquerda é null, Slide 24, adicionado num 17
     */
    @Test
    void removeMaxLeftNull() {

        LinkedBinarySearchTree<Integer> expected = new LinkedBinarySearchTree<>();

        expected.addElement(10);
        expected.addElement(5);
        expected.addElement(15);
        expected.addElement(3);
        expected.addElement(7);
        expected.addElement(13);

        LinkedBinarySearchTree<Integer> actual = new LinkedBinarySearchTree<>();

        actual.addElement(10);
        actual.addElement(5);
        actual.addElement(15);
        actual.addElement(3);
        actual.addElement(7);
        actual.addElement(13);
        actual.addElement(17);

        assertDoesNotThrow(actual::removeMax);

        assertEquals(expected.size(), actual.size());
        assertEquals(10, actual.root.element);

        Iterator<Integer> itrExpected = expected.iteratorInOrder();
        Iterator<Integer> itrActual = actual.iteratorInOrder();

        while (itrExpected.hasNext()) {
            Integer expec = itrExpected.next();
            Integer actu = itrActual.next();
            assertEquals(expec, actu);
        }
    }

    /**
     * Quando o max é o root
     */
    @Test
    void removeMaxRoot() {

        LinkedBinarySearchTree<Integer> expected = new LinkedBinarySearchTree<>();

        expected.addElement(5);

        LinkedBinarySearchTree<Integer> actual = new LinkedBinarySearchTree<>();

        actual.addElement(10);
        actual.addElement(5);

        assertDoesNotThrow(actual::removeMax);

        assertEquals(expected.size(), actual.size());
        assertEquals(5, actual.root.element);

        Iterator<Integer> itrExpected = expected.iteratorInOrder();
        Iterator<Integer> itrActual = actual.iteratorInOrder();

        while (itrExpected.hasNext()) {
            Integer expec = itrExpected.next();
            Integer actu = itrActual.next();
            assertEquals(expec, actu);
        }
    }

    /**
     * Testar rotação Esquerda Direita, removendo 2x o max
     */
    @Test
    void RotateLeftRight() {

        LinkedBinarySearchTree<Integer> expected = new LinkedBinarySearchTree<>();

        expected.addElement(6);
        expected.addElement(5);
        expected.addElement(10);
        expected.addElement(3);
        expected.addElement(8);
        expected.addElement(13);

        LinkedBinarySearchTree<Integer> actual = new LinkedBinarySearchTree<>();

        actual.addElement(10);
        actual.addElement(5);
        actual.addElement(13);
        actual.addElement(3);
        actual.addElement(6);
        actual.addElement(15);
        actual.addElement(14);
        actual.addElement(8);
        /**
         * ANTES DE REMOVER
         * ------------------------------10-------------------------------------
         * --------------------5-------------------13---------------------------
         * --------------3---------6---------------------15---------------------
         * --------------------------8----------------14------------------------
         */
        assertDoesNotThrow(actual::removeMax);
        assertDoesNotThrow(actual::removeMax);
        /**
         * DEPOIS DE REMOVER
         * ------------------------------10(-2)---------------------------------
         * --------------------5(1)----------------13---------------------------
         * -----------------3--------6------------------------------------------
         * ----------------------------8----------------------------------------
         */
        assertEquals(expected.size(), actual.size());
        assertEquals(6, actual.root.element);
        assertEquals(5, actual.root.left.element);
        assertEquals(10, actual.root.right.element);

        Iterator<Integer> itrExpected = expected.iteratorInOrder();
        Iterator<Integer> itrActual = actual.iteratorInOrder();

        while (itrExpected.hasNext()) {
            Integer expec = itrExpected.next();
            Integer actu = itrActual.next();
            assertEquals(expec, actu);
        }
        /**
         * REPOIS DA ROTAÇÃO
         * ------------------------------6(-2)----------------------------------
         * -------------------5(1)--------------------10------------------------
         * --------------3------------------------8-------13--------------------
         * ---------------------------------------------------------------------
         */
    }

    /**
     * Testar remoção folha esquerda do root, slide 50
     */
    @Test
    void RemoveLeafLeftRoot() {

        LinkedBinarySearchTree<Integer> expected = new LinkedBinarySearchTree<>();

        expected.addElement(13);
        expected.addElement(10);
        expected.addElement(15);
        expected.addElement(5);
        expected.addElement(11);
        expected.addElement(17);

        LinkedBinarySearchTree<Integer> actual = new LinkedBinarySearchTree<>();

        actual.addElement(10);
        actual.addElement(5);
        actual.addElement(15);
        actual.addElement(3);
        actual.addElement(13);
        actual.addElement(17);
        actual.addElement(11);

        /**
         * ANTES DE REMOVER
         * ------------------------------10-------------------------------------
         * --------------------5-------------------15---------------------------
         * --------------3--------------------13---------17---------------------
         * --------------------------------11-----------------------------------
         */
        try {
            assertEquals(3, actual.removeElement(3));
        } catch (ElementNotFoundException | EmptyCollectionException ex) {
        }

        /**
         * DEPOIS DE REMOVER
         * ------------------------------10-------------------------------------
         * --------------------5-------------------15---------------------------
         * -----------------------------------13---------17---------------------
         * --------------------------------11-----------------------------------
         */
        /**
         * REPOIS DA ROTAÇÃO
         * ------------------------------13-------------------------------------
         * --------------------10----------------15-----------------------------
         * ---------------5---------11-----------------17-----------------------
         * ---------------------------------------------------------------------
         */
        assertEquals(expected.size(), actual.size());
        assertEquals(13, actual.root.element);
        assertEquals(10, actual.root.left.element);
        assertEquals(15, actual.root.right.element);

        Iterator<Integer> itrExpected = expected.iteratorInOrder();
        Iterator<Integer> itrActual = actual.iteratorInOrder();

        while (itrExpected.hasNext()) {
            Integer expec = itrExpected.next();
            Integer actu = itrActual.next();
            assertEquals(expec, actu);
        }
    }

    /**
     * Testar remoção folha direita do root, slide 50
     */
    @Test
    void RemoveLeafRightRoot() {

        LinkedBinarySearchTree<Integer> expected = new LinkedBinarySearchTree<>();

        expected.addElement(13);
        expected.addElement(10);
        expected.addElement(15);
        expected.addElement(3);
        expected.addElement(5);
        expected.addElement(11);

        LinkedBinarySearchTree<Integer> actual = new LinkedBinarySearchTree<>();

        actual.addElement(10);
        actual.addElement(5);
        actual.addElement(15);
        actual.addElement(3);
        actual.addElement(13);
        actual.addElement(17);
        actual.addElement(11);

        /**
         * ANTES DE REMOVER
         * ------------------------------10-------------------------------------
         * --------------------5-------------------15---------------------------
         * --------------3--------------------13---------17---------------------
         * --------------------------------11-----------------------------------
         */
        try {
            assertEquals(17, actual.removeElement(17));
        } catch (ElementNotFoundException | EmptyCollectionException ex) {
        }

        /**
         * DEPOIS DE REMOVER
         * ------------------------------10-------------------------------------
         * --------------------5-------------------15---------------------------
         * ---------------3--------------------13-------------------------------
         * --------------------------------11-----------------------------------
         */
        /**
         * REPOIS DA ROTAÇÃO
         * ------------------------------10-------------------------------------
         * --------------------5----------------13------------------------------
         * ---------------3----------------11-------15--------------------------
         * ---------------------------------------------------------------------
         */
        assertEquals(expected.size(), actual.size());
        assertEquals(10, actual.root.element);
        assertEquals(5, actual.root.left.element);
        assertEquals(13, actual.root.right.element);
        assertEquals(15, actual.root.right.right.element);

        Iterator<Integer> itrExpected = expected.iteratorInOrder();
        Iterator<Integer> itrActual = actual.iteratorInOrder();

        while (itrExpected.hasNext()) {
            Integer expec = itrExpected.next();
            Integer actu = itrActual.next();
            assertEquals(expec, actu);
        }
    }

    @Test
    void RemoveInternNode() {

        LinkedBinarySearchTree<Integer> expected = new LinkedBinarySearchTree<>();

        expected.addElement(6);
        expected.addElement(3);
        expected.addElement(10);
        expected.addElement(1);
        expected.addElement(4);
        expected.addElement(2);
        expected.addElement(8);
        expected.addElement(14);
        expected.addElement(7);
        expected.addElement(9);
        expected.addElement(12);
        expected.addElement(15);
        expected.addElement(11);
        expected.addElement(13);
        /**
         * DEPOIS DE REMOVER
         * ---------------------------------6-----------------------------------
         * -----------------3------------------------------10-------------------
         * ------------1--------4--------------------8-------------14-----------
         * --------------2----------------------7--------9------12-----15-------
         * --------------------------------------------------11----13-----------
         */
        LinkedBinarySearchTree<Integer> actual = new LinkedBinarySearchTree<>();

        actual.addElement(15);
        actual.addElement(14);
        actual.addElement(5);
        actual.addElement(3);
        actual.addElement(10);
        actual.addElement(1);
        actual.addElement(4);
        actual.addElement(9);
        actual.addElement(8);
        actual.addElement(11);
        actual.addElement(2);
        actual.addElement(13);
        actual.addElement(6);
        actual.addElement(7);
        actual.addElement(12);

        /**
         * ANTES DE REMOVER
         * ---------------------------------5-----------------------------------
         * -----------------3------------------------------10-------------------
         * ------------1--------4--------------------8-------------14-----------
         * --------------2----------------------6--------9------12-----15-------
         * --------------------------------------- 7---------11----13-----------
         */
        try {
            assertEquals(5, actual.removeElement(5));
        } catch (ElementNotFoundException | EmptyCollectionException ex) {
        }

        /**
         * DEPOIS DE REMOVER NAO FAZ ROTAÇÃO
         * ---------------------------------6-----------------------------------
         * -----------------3------------------------------10-------------------
         * ------------1--------4--------------------8-------------14-----------
         * --------------2----------------------7--------9------12-----15-------
         * --------------------------------------------------11----13-----------
         */
        assertEquals(expected.size(), actual.size());
        assertEquals(6, actual.root.element);
        assertEquals(3, actual.root.left.element);
        assertEquals(10, actual.root.right.element);
        assertEquals(7, actual.root.right.left.left.element);
        assertNull(actual.root.right.left.left.right);
        assertEquals(14, actual.root.right.right.element);

        Iterator<Integer> itrExpected = expected.iteratorInOrder();
        Iterator<Integer> itrActual = actual.iteratorInOrder();

        while (itrExpected.hasNext()) {
            Integer expec = itrExpected.next();
            Integer actu = itrActual.next();
            assertEquals(expec, actu);
        }
    }

    @Test
    void RemoveElementAllSubTree() {

        LinkedBinarySearchTree<Integer> expected = new LinkedBinarySearchTree<>();

        expected.addElement(8);
        expected.addElement(4);
        expected.addElement(2);
        expected.addElement(6);
        expected.addElement(1);
        expected.addElement(3);
        expected.addElement(5);
        expected.addElement(7);
        expected.addElement(10);
        expected.addElement(12);
        expected.addElement(9);
        expected.addElement(11);

        LinkedBinarySearchTree<Integer> actual = new LinkedBinarySearchTree<>();

        actual.addElement(8);
        actual.addElement(4);
        actual.addElement(2);
        actual.addElement(6);
        actual.addElement(1);
        actual.addElement(3);
        actual.addElement(5);
        actual.addElement(7);
        actual.addElement(12);
        actual.addElement(10);
        actual.addElement(14);
        actual.addElement(9);
        actual.addElement(11);
        actual.addElement(13);
        actual.addElement(15);

        /**
         * ANTES DE REMOVER
         * ---------------------------------8-----------------------------------
         * -----------------4------------------------------12-------------------
         * ------------2--------6--------------------10-------------14-----------
         * --------1------3---5---7-------------9--------11------13-----15-------
         */
        try {
            assertEquals(15, actual.removeElement(15));
            assertEquals(14, actual.removeElement(14));
            assertEquals(13, actual.removeElement(13));
        } catch (ElementNotFoundException | EmptyCollectionException ex) {
        }

        /**
         * DEPOIS DE REMOVER
         * ---------------------------------8-----------------------------------
         * -----------------4------------------------------12-------------------
         * ------------2--------6--------------------10-------------------------
         * --------1------3---5---7-------------9--------11---------------------
         */
        /**
         * DEPOIS DA ROTAÇÃO
         * ---------------------------------8-----------------------------------
         * -----------------4------------------------------10-------------------
         * ------------2--------6---------------------9----------12-------------
         * --------1------3---5---7--------------------------11------------------
         */
        assertEquals(expected.size(), actual.size());
        assertEquals(8, actual.root.element);
        assertEquals(4, actual.root.left.element);
        assertEquals(10, actual.root.right.element);
        assertEquals(9, actual.root.right.left.element);
        assertNull(actual.root.right.left.right);
        assertNull(actual.root.right.left.left);
        assertEquals(12, actual.root.right.right.element);
        assertNull(actual.root.right.right.right);
        assertEquals(11, actual.root.right.right.left.element);

        Iterator<Integer> itrExpected = expected.iteratorInOrder();
        Iterator<Integer> itrActual = actual.iteratorInOrder();

        while (itrExpected.hasNext()) {
            Integer expec = itrExpected.next();
            Integer actu = itrActual.next();
            assertEquals(expec, actu);
        }
    }

    @Test
    void RemoveElementAllRootRight() {

        LinkedBinarySearchTree<Integer> expected = new LinkedBinarySearchTree<>();

        expected.addElement(4);
        expected.addElement(2);
        expected.addElement(8);
        expected.addElement(1);
        expected.addElement(3);
        expected.addElement(6);
        expected.addElement(10);
        expected.addElement(5);
        expected.addElement(7);

        LinkedBinarySearchTree<Integer> actual = new LinkedBinarySearchTree<>();

        actual.addElement(8);
        actual.addElement(4);
        actual.addElement(2);
        actual.addElement(6);
        actual.addElement(1);
        actual.addElement(3);
        actual.addElement(5);
        actual.addElement(7);
        actual.addElement(12);
        actual.addElement(10);
        actual.addElement(14);
        actual.addElement(9);
        actual.addElement(11);
        actual.addElement(13);
        actual.addElement(15);

        /**
         * ANTES DE REMOVER
         * ---------------------------------8-----------------------------------
         * -----------------4------------------------------12-------------------
         * ------------2--------6--------------------10-------------14-----------
         * --------1------3---5---7-------------9--------11------13-----15-------
         */
        try {
            assertEquals(15, actual.removeElement(15));
            assertEquals(14, actual.removeElement(14));
            assertEquals(13, actual.removeElement(13));
            assertEquals(12, actual.removeElement(12));
            assertEquals(9, actual.removeElement(9));
            assertEquals(11, actual.removeElement(11));
        } catch (ElementNotFoundException | EmptyCollectionException ex) {
        }
        /**
         * FINAL DEPOIS DAS ROTAÇOES
         * ---------------------------------4-----------------------------------
         * -----------------2------------------------------8--------------------
         * ------------1--------3--------------------6----------10--------------
         * ---------------------------------------5-----7-----------------------
         */

        assertEquals(expected.size(), actual.size());
        assertEquals(4, actual.root.element);
        assertEquals(2, actual.root.left.element);
        assertEquals(3, actual.root.left.right.element);
        assertEquals(1, actual.root.left.left.element);
        assertEquals(8, actual.root.right.element);
        assertEquals(6, actual.root.right.left.element);
        assertEquals(7, actual.root.right.left.right.element);
        assertNull(actual.root.right.left.right.right);
        assertEquals(5, actual.root.right.left.left.element);
        assertNull(actual.root.right.left.left.right);
        assertEquals(5, actual.root.right.left.left.element);
        assertEquals(10, actual.root.right.right.element);
        assertNull(actual.root.right.right.right);
        assertNull(actual.root.right.right.left);

        Iterator<Integer> itrExpected = expected.iteratorInOrder();
        Iterator<Integer> itrActual = actual.iteratorInOrder();

        while (itrExpected.hasNext()) {
            Integer expec = itrExpected.next();
            Integer actu = itrActual.next();
            assertEquals(expec, actu);
        }
    }

}
