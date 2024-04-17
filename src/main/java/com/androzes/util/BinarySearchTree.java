package com.androzes.util;

import com.androzes.model.BinaryTreeNode;

public class BinarySearchTree<T extends Comparable<? super T>> implements ITree<T> {

    protected BinaryTreeNode<T> root;
    protected int size;

    public BinarySearchTree() {
        this.size = 0;
    }

    @Override
    public void insert(T object) {
        root = insertInternal(root, object);
    }

    protected BinaryTreeNode<T> insertInternal(BinaryTreeNode<T> node, T val) {
        if (node == null) {
            this.size += 1;
            return new BinaryTreeNode<T>(val);
        }

        if (val.compareTo(node.val) < 0) {
            node.left = insertInternal(node.left, val);
            node.height = node.left.height + 1;
        } else if (val.compareTo(node.val) > 0) {
            node.right = insertInternal(node.right, val);
            node.height = node.right.height + 1;
        }

        return node;
    }

    @Override
    public boolean delete(T object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public boolean exists(T object) {
        return existsInternal(root, object);
    }

    protected boolean existsInternal(BinaryTreeNode<T> node, T val) {
        if (node == null) {
            return false;
        }

        int compareResult = val.compareTo(node.val);

        if (compareResult < 0) {
            return existsInternal(node.left, val);
        } else if (compareResult > 0) {
            return existsInternal(node.right, val);
        }

        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        traverseInOrder(sb, root);
        sb.append("]");
        return sb.toString();    
    }

    public String toTreeString() {
        StringBuilder sb = new StringBuilder();
        travsereReverseInOrder(sb, root, 0, 5);
        return sb.toString();
    }

    public void travsereReverseInOrder(StringBuilder sb, BinaryTreeNode<T> node, int space, int increment) {
        if (node == null) {
            //sb.append(System.lineSeparator());
            return;
        }

        travsereReverseInOrder(sb, node.right, space+increment, increment);
        //sb.append(System.lineSeparator());

        sb.append(" ".repeat(space)).append(node.val).append(System.lineSeparator());
        
        travsereReverseInOrder(sb, node.left, space+increment, increment);
        //sb.append(System.lineSeparator());

    }

    public void traverseInOrder(StringBuilder sb, BinaryTreeNode<T> node) {
        if (node == null) {
            return;
        }

        traverseInOrder(sb, node.left);
        sb.append(node.val + " ");
        traverseInOrder(sb, node.right);
    }

    public int size() {
        return size;
    } 

    protected int height(BinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }
    
}
