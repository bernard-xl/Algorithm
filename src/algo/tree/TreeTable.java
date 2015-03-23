package algo.tree;

import java.util.NoSuchElementException;

/**
 * Created by bernard on 14/3/15.
 */
public class TreeTable<Key extends Comparable<Key>, Value> {

    private class BinaryTreeNode {
        private Key key;
        private Value value;
        private BinaryTreeNode left, right;
        private int nodeCount;

        public BinaryTreeNode(Key key, Value value, int nodeCount) {
            this.key = key;
            this.value = value;
            this.nodeCount = nodeCount;
        }
    }

    private BinaryTreeNode root;

    public boolean isEmpty() {
        return size(root) == 0;
    }

    private int size() {
        return size(root);
    }

    private int size(BinaryTreeNode node) {
        if(node == null) return 0;
        else return node.nodeCount;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private BinaryTreeNode put(BinaryTreeNode node, Key key, Value value) {
        if(node == null) return new BinaryTreeNode(key, value, 1);

        int comparison = key.compareTo(node.key);

        if(comparison < 0) node.left = put(node.left, key, value);
        else if(comparison > 0) node.right = put(node.right, key, value);
        else node.value = value;

        node.nodeCount = 1 + size(node.left) + size(node.right);

        return node;
    }

    public Value min() {
        if(isEmpty())
            throw new NoSuchElementException("The table is empty.");
        return min(root).value;
    }

    private BinaryTreeNode min(BinaryTreeNode node) {
        if(node.left == null) return node;
        else return min(node.left);
    }

    public Value max() {
        if(isEmpty())
            throw new NoSuchElementException("The table is empty.");
        return max(root).value;
    }

    private BinaryTreeNode max(BinaryTreeNode node) {
        if(node.right == null) return node;
        else return max(node.right);
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(BinaryTreeNode node, Key key) {
        if(node == null) return null;

        int comparison = key.compareTo(node.key);

        if(comparison < 0) return get(node.left, key);
        else if(comparison > 0) return get(node.right, key);
        else return node.value;
    }

    public void deleteMin() {
        if(isEmpty())
            throw new NoSuchElementException("The table is empty.");
        root = deleteMin(root);
    }

    private BinaryTreeNode deleteMin(BinaryTreeNode node) {
        if(node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.nodeCount = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void deleteMax() {
        if(isEmpty())
            throw new NoSuchElementException("The table is empty");
        root = deleteMax(root);
    }

    public BinaryTreeNode deleteMax(BinaryTreeNode node) {
        if(node.right == null) return node.left;
        node.right = deleteMax(node.right);
        node.nodeCount = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void delete(Key key) {
        delete(root, key);
    }

    public BinaryTreeNode delete(BinaryTreeNode node, Key key) {
        if(node == null) return null;

        int comparison = key.compareTo(node.key);

        if(comparison < 0) node.left = delete(node.left, key);
        else if(comparison > 0) node.right = delete(node.right, key);
        else {
            if(node.left == null) return node.right;
            if(node.right == null) return node.left;

            BinaryTreeNode oldNode = node;
            node = min(node.right);
            node.right = deleteMin(oldNode.right);
            node.left = oldNode.left;
        }

        return node;
    }

    public Key floor(Key key) {
        return floor(root, key).key;
    }

    public BinaryTreeNode floor(BinaryTreeNode node, Key key) {
        if(node == null) return null;

        int comparison = key.compareTo(node.key);

        if(comparison == 0) return node;
        if(comparison < 0) return floor(node.left, key);

        BinaryTreeNode t = floor(node.right, key);
        return t == null? node : t;
    }

    public Key ceiling(Key key) {
        return ceiling(root, key).key;
    }

    public BinaryTreeNode ceiling(BinaryTreeNode node, Key key) {
        if(node == null) return null;

        int comparison = key.compareTo(node.key);

        if(comparison == 0) return node;
        if(comparison > 0) return ceiling(node.right, key);

        BinaryTreeNode t = ceiling(node.left, key);
        return t == null? node : t;
    }

    public Value select(int rank) {
        if(rank <= 0 || rank > size()) return null;
        return select(root, rank).value;
    }

    private BinaryTreeNode select(BinaryTreeNode node, int rank) {
        if(node == null) return null;
        int leftCount = size(node.left);
        if(rank < leftCount) return select(node.left, rank);
        else if(rank > leftCount) return select(node.right, rank - size(node.left) - 1);
        else return node;
    }

    public int rankOf(Key key) {
        return rankOf(root, key);
    }

    private int rankOf(BinaryTreeNode node, Key key) {
        if(node  == null) return -1;

        int comparison = key.compareTo(node.key);

        if(comparison < 0) return rankOf(node.left, key);
        if(comparison > 0) return rankOf(node.right, key);

        return size(node.left);
    }
}
