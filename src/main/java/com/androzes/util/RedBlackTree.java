package com.androzes.util;

import com.androzes.model.RedBlackTreeNode;
import com.androzes.model.RedBlackTreeNode.Color;

public class RedBlackTree<T extends Comparable<? super T>> extends BinarySearchTree<T> {

    protected RedBlackTreeNode<T> root;
    protected int size;

    public RedBlackTree() {
        this.size = 0;
    }

    @Override
    public void insert(T object) {
        RedBlackTreeNode<T> node = root;
        RedBlackTreeNode<T> parent = null;
        int compareResult = 0;
        while(node != null) {
            parent = node;
            compareResult = object.compareTo(node.val);
            if (compareResult < 0) {
                node = node.left;
            } else if (compareResult > 0) {
                node = node.right;
            } else {
                return;
            }
        }

        RedBlackTreeNode<T> newNode = new RedBlackTreeNode<T>(object);
        if (parent == null) {
            root = newNode;
        } else if (compareResult < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        newNode.parent = parent;
        size +=1;

        System.out.println("Before fixing " + object + ":\n" + toTreeString());

        fixRedBlackPropertiesAfterInsert(newNode);

        System.out.println("After fixing " + object + ":\n" + toTreeString());
    }

    private void fixRedBlackPropertiesAfterInsert(RedBlackTreeNode<T> node) {
        if (node == root || node.parent == root) {
            root.color = Color.BLACK;
            return;
        }

        RedBlackTreeNode<T> parent = node.parent;
        RedBlackTreeNode<T> uncle = node.uncle();
        RedBlackTreeNode<T> grandparent = parent.parent;
        
        if (parent.color == Color.BLACK ) {
            return;
        }
        
        // if parent is RED, uncle is BLACK
        if (uncle == null || uncle.color == Color.BLACK) {
            // if parent is right child of grandparent    
            if (grandparent.right == parent) {
                // RL 
                if (parent.left == node) {
                    grandparent.right = rotateRight(parent);
                    parent = node;
                }

                // RR
                rotateLeft(grandparent);
                parent.color = Color.BLACK;
                grandparent.color = Color.RED;
            } else {
                // LR
                if (parent.right == node) {
                    grandparent.left = rotateLeft(parent);
                    parent = node;
                }

                // LL
                rotateRight(grandparent);
                parent.color = Color.BLACK;
                grandparent.color = Color.RED;
            }
        } else {
            // parent is RED, uncle is RED
            // recolor parent, uncle and grandparent
            node.parent.color = Color.BLACK;
            uncle.color = Color.BLACK;
            grandparent.color = Color.RED;

            fixRedBlackPropertiesAfterInsert(grandparent);
        }

    }

    private RedBlackTreeNode<T> rotateLeft(RedBlackTreeNode<T> node) {
        RedBlackTreeNode<T> n = node.right;
        node.right = n.left;
        n.left = node;

        // update parents
        n.parent = node.parent;
        node.parent = n;
        if (node.right != null) {
            node.left.parent = node;
        }

        if (n.parent == null) {
            root = n;
        } else if(n.parent.left == node) {
            n.parent.left = n;
        } else {
            n.parent.right = n;
        }

        return n;
    }

    private RedBlackTreeNode<T> rotateRight(RedBlackTreeNode<T> node) {
        RedBlackTreeNode<T> n = node.left;
        node.left = n.right;
        n.right = node;

        // update parents
        n.parent = node.parent;
        node.parent = n;
        if (node.left != null) {
            node.right.parent = node;
        }

        if (n.parent == null) {
            root = n;
        } else if(n.parent.left == node) {
            n.parent.left = n;
        } else {
            n.parent.right = n;
        }
        
        return n;
    }

    @Override
    public String toTreeString() {
        StringBuilder sb = new StringBuilder();
        travsereReverseInOrder(sb, root, 0, 5);
        return sb.toString();
    }

    protected void travsereReverseInOrder(StringBuilder sb, RedBlackTreeNode<T> node, int space, int increment) {
        if (node == null) {
            //sb.append(System.lineSeparator());
            return;
        }

        travsereReverseInOrder(sb, node.right, space+increment, increment);
        //sb.append(System.lineSeparator());

        sb.append(" ".repeat(space)).append(node).append(System.lineSeparator());
        
        travsereReverseInOrder(sb, node.left, space+increment, increment);
        //sb.append(System.lineSeparator());

    }
        
    
}
