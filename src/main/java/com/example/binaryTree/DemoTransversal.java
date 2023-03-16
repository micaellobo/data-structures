package com.example.binaryTree;

import java.util.Iterator;

public class DemoTransversal extends LinkedBinaryTree<String> {

    public DemoTransversal() {
        this.root = new BinaryTreeNode<>("F");
        this.root.right = new BinaryTreeNode<>("G");
        this.root.right.left = new BinaryTreeNode<>("H");
        this.root.right.right = new BinaryTreeNode<>("I");
        this.root.left = new BinaryTreeNode<>("B");
        this.root.left.right = new BinaryTreeNode<>("D");
        this.root.left.left = new BinaryTreeNode<>("E");
        this.root.left.left.left = new BinaryTreeNode<>("A");
        this.root.left.left.right = new BinaryTreeNode<>("C");

        this.count = 9;
    }

    public static void main(String[] args) {

        for (Iterator<String> iterator = new DemoTransversal().iteratorPreOrder(); iterator.hasNext(); ) {
            System.out.print(iterator.next() + " ");
        }
    }
}
