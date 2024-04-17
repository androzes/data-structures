package com.androzes.model;

public class BinaryTreeNode<T> extends Node<T> {

    public BinaryTreeNode<T> left;
    public BinaryTreeNode<T> right;
    public int height;

    public BinaryTreeNode(T val) {
        super(val);
        height = 1;
    }

}
