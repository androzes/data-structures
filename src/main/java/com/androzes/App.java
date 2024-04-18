package com.androzes;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.androzes.util.AvlTree;
import com.androzes.util.BinarySearchTree;
import com.androzes.util.RedBlackTree;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        AvlTree<Integer> avl = new AvlTree<>();
        RedBlackTree<Integer> rbt = new RedBlackTree<>();


        // int[] values = {12, 19, 50, 2, 1, 8, 5, 3, 4, 6, 7, 8, 11};

        Set<Integer> values = randomNumbers(25, 100);
        System.out.println("Values picked: " + values);

        for (int val: values) {
            bst.insert(val);
            avl.insert(val);
            rbt.insert(val);
        }

        System.out.println("BST:" + System.lineSeparator() + bst.toTreeString());
        System.out.println("AVL:" + System.lineSeparator() + avl.toTreeString());
        System.out.println("RBT:" + System.lineSeparator() + rbt.toTreeString());
    }

    public static Set<Integer> randomNumbers(int size, int bound) {
        Set<Integer> picked = new HashSet<>();

        Random rnd = new Random();

        while (picked.size() < size) {
            picked.add(rnd.nextInt(-bound, bound + 1));
        }
        return picked;
    }
}
