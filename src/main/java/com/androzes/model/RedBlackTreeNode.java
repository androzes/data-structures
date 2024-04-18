package com.androzes.model;

public class RedBlackTreeNode<T> extends Node<T> {

    public RedBlackTreeNode<T> left;
    public RedBlackTreeNode<T> right;
    public RedBlackTreeNode<T> parent;

    public Color color;

    public RedBlackTreeNode(T val) {
        super(val);
        color = Color.RED;
    }

    public enum Color {
        RED,
        BLACK
    }

    public RedBlackTreeNode<T> uncle() {        
        if (parent == null || parent.parent == null) {
            return null;
        }

        if (parent.parent.left == parent) {
            return parent.parent.right;
        } else if (parent.parent.right == parent) {
            return parent.parent.left;
        }

        return null;
    }

    public String toString() {
        return this.val + "#" + getColorSymbol(); 
    }

    private char getColorSymbol() {
        return (color == Color.RED) ? 'R': 'B';
    }

}
