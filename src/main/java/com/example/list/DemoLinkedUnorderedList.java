package com.example.list;

public class DemoLinkedUnorderedList {

    public static void main(String[] args) {
        LinkedUnorderedList<String> list = new LinkedUnorderedList<>();

        list.addToRear("1");
//            list.addToRear("3");
//            list.addToRear("5");
//            list.addToRear("7");
//
//            list.addAfter("2", "1");
//            list.addAfter("4", "3");
//            list.addAfter("6", "5");

//            list.removeLast();
        System.out.println("last " + list.contains("1"));

        for (String string : list) {
            System.out.print(string + " ");
        }
    }

}
