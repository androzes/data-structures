package com.androzes.util;

import com.androzes.model.BinaryTreeNode;

public class AvlTree<T extends Comparable<? super T>> extends BinarySearchTree<T> {

    public AvlTree() {
        super();
    }

    @Override
    public void insert(T object) {
        root = insertInternal(root, object);
    }

    private BinaryTreeNode<T> rotateLeft(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> n = node.right;
        node.right = n.left;
        n.left = node;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        n.height = Math.max(height(n.left), height(n.right)) + 1;
        return n;
    }

    private BinaryTreeNode<T> rotateRight(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> n = node.left;
        node.left = n.right;
        n.right = node;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        n.height = Math.max(height(n.left), height(n.right)) + 1;
        return n;
    }
    
    @Override
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

        return rebalance(node, val);
    }

    private BinaryTreeNode<T> rebalance(BinaryTreeNode<T> node, T val) {
        int balanceFactor = height(node.left) - height(node.right);
        if (balanceFactor < -1) {
            // RR
            if (val.compareTo(node.right.val) > 0) {
                return rotateLeft(node);
            }
            // RL
            else if (val.compareTo(node.right.val) < 0) {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
            
        }

        if (balanceFactor > 1) {
            // LL
            if (val.compareTo(node.left.val) < 0)
                return rotateRight(node);
            // LR
            else if (val.compareTo(node.left.val) > 0) {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }

        return node;
    }
    

}
