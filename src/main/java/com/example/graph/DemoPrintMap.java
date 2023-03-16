package com.example.graph;

import com.example.arrayList.ArrayUnorderedList;
import com.example.exceptions.EmptyCollectionException;
import com.example.interfaces.*;
import com.example.queue.LinkedQueue;

import java.util.Iterator;

public class DemoPrintMap<T> extends NetworkDirected<T> {

    private static final String ENTRY_DIVISION = "  ";
    private static final String ENTRY_DIVISION_LINE = "___";
    private static final int CARACTERS_DIVISION = 20;
    private static final String LINE = "_";
    private static final String COLUM = "|";
    private static final String BLANC = " ";

    public DemoPrintMap(int capacity) {
        super(capacity);
    }

    public DemoPrintMap() {
    }

    private int getMaxDivisionsByLevel() {
        int indexMaxNeighbor = 0;
        for (int i = 1; i < numberOfVertices; i++) {
            if (vertices[i].neighbors.size() > vertices[indexMaxNeighbor].neighbors.size()) {
                indexMaxNeighbor = i;
            }
        }
        return vertices[indexMaxNeighbor].neighbors.size();
    }

    public UnorderedListADT<Integer>[] getElementsByLevel(T startVertex) {

        int indexStart = getIndex(startVertex);
        QueueADT<Integer> traversalQueue = new LinkedQueue<>();
        UnorderedListADT<Integer>[] resultList = new UnorderedListADT[this.numberOfVertices];
        boolean[] visited = new boolean[this.numberOfVertices];
        int[] levels = new int[this.numberOfVertices];

        if (!isIndexValid(indexStart)) {
            return resultList;
        }

        for (int i = 0; i < resultList.length; i++) {
            resultList[i] = new ArrayUnorderedList<>(this.numberOfVertices);
        }
        traversalQueue.enqueue(indexStart);
        visited[indexStart] = true;
        resultList[0].addToRear(indexStart);
        while (!traversalQueue.isEmpty()) {
            try {
                int index = traversalQueue.dequeue();
                for (GraphEdge neighbor : this.vertices[index].neighbors) {
                    int indexNeighbor = neighbor.indexVertex;
                    if (!visited[indexNeighbor]) {
                        traversalQueue.enqueue(indexNeighbor);
                        visited[indexNeighbor] = true;
                        levels[indexNeighbor] = levels[index] + 1;
                        resultList[levels[index] + 1].addToRear(indexNeighbor);
                    }
                }
            } catch (EmptyCollectionException ex) {
            }
        }
        //Determinar numero de nivels;
        int lastLevel = 0;
        for (int i = 1; i < levels.length; i++) {
            if (levels[lastLevel] < levels[i]) {
                lastLevel = levels[i];
            }
        }

        //Tirar elementos vazios
        UnorderedListADT<Integer>[] aux = new UnorderedListADT[lastLevel + 1];
        System.arraycopy(resultList, 0, aux, 0, lastLevel + 1);
        return aux;
    }

    private void printDivision(int index, int numDivisions, int sizeNextLevel) {

        //Determinar numero de ciclos necessarios para imprimir cada caracter
        int lineCaracteres = (getMaxDivisionsByLevel() * CARACTERS_DIVISION) / numDivisions;

        String name = (String) vertices[index].element;

        //Imprimir linha com nome
        for (int i = 0; i < lineCaracteres - name.length(); i++) {
            if ((lineCaracteres - (name.length() / 2)) / 2 == i) {
                System.out.print(name);
            }
            System.out.print(BLANC);
        }
        if (numDivisions > 1) {
            System.out.print(COLUM);
        } else {
            System.out.print(COLUM + "\n" + COLUM);
        }

        int position = (lineCaracteres / sizeNextLevel) / 2;
        //Imprimir linha do proximo nivel
        for (int i = 0; i < lineCaracteres - (sizeNextLevel * ENTRY_DIVISION.length()); i++) {
            if (i == position) {
                System.out.print(ENTRY_DIVISION);
                position += position;
            }
            System.out.print(LINE);
        }
        System.out.print(COLUM);
    }

    private void printLineNextLevel(int lineCaracteres, int sizeNextLevel, int index) {

        int aux = (lineCaracteres / sizeNextLevel) / 2;
        int position = aux;

        for (int i = 0; i < lineCaracteres - (sizeNextLevel * ENTRY_DIVISION.length()); i++) {
            if (i == position) {
                if (vertices[index].neighbors.size() > 1) {
                    System.out.print(ENTRY_DIVISION);
                } else {
                    System.out.print(ENTRY_DIVISION_LINE);
                }
                position += aux * 2;
            }
            System.out.print(LINE);
        }
        System.out.print(COLUM);
    }

    public void printLevel(UnorderedListADT<Integer> elements, int sizeNextLevel) {

        Iterator<Integer> iteratorLevel = elements.iterator();
        int numDivisions = elements.size();

        //Determinar numero de ciclos necessarios para imprimir cada caracter
        int lineCaracteres = (getMaxDivisionsByLevel() * CARACTERS_DIVISION) / numDivisions;

        int aux = numDivisions;
        while (iteratorLevel.hasNext()) {
            int index = iteratorLevel.next();
            String name = (String) vertices[index].element;
            int lineSize = aux > 1 ? lineCaracteres - name.length() - 1 : lineCaracteres - name.length();

            //Imprimir linha com nome
            for (int i = 0; i < lineSize; i++) {
                if ((lineCaracteres - (name.length() / 2)) / 2 == i) {
                    System.out.print(name);
                }
                System.out.print(BLANC);
            }
            if (aux-- > 1) {
                System.out.print(COLUM);
            } else {
                System.out.print(COLUM + "\n" + COLUM);
            }
        }
        if (elements.contains(3)) {
            int a = 0;
        }
        int temp = numDivisions;
        for (Iterator<Integer> iterator = elements.iterator(); iterator.hasNext(); ) {
            int lineSize = temp > 1 ? lineCaracteres - 1 : lineCaracteres;
            printLineNextLevel(lineSize, sizeNextLevel, iterator.next());
            temp--;
        }
    }

    public void print2DMap() {
        int maxDivisionLevel = getMaxDivisionsByLevel();

        UnorderedListADT<Integer>[] elementsByLevel = getElementsByLevel(vertices[0].element);
        System.out.print(BLANC);
        for (int i = 0; i < maxDivisionLevel * CARACTERS_DIVISION; i++) {
            System.out.print(LINE);
        }

        for (int i = 0; i < elementsByLevel.length; i++) {
            System.out.print("\n|");
            int sizeNextLevel = (i + 1 == elementsByLevel.length) ? 1 : elementsByLevel[i + 1].size();
            printLevel(elementsByLevel[i], sizeNextLevel);
        }
    }

}
