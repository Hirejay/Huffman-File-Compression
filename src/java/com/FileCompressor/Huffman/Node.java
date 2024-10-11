package com.FileCompressor.Huffman;

/**
 * A class representing a node in the Huffman Tree.
 * Each node stores a byte, frequency, and pointers to left and right children.
 * Nodes are comparable based on their frequency for building the Huffman Tree.
 * 
 * @author Jay Hire
 */
public class Node implements Comparable<Node> {
    private Byte nodeByte; // Byte stored in the node (can be null for internal nodes)
    private int freq;      // Frequency of the byte
    private Node left;     // Left child node
    private Node right;    // Right child node

    // Constructor for leaf nodes
    public Node(Byte nodeByte, int freq) {
        this(nodeByte, freq, null, null);
    }

    // Constructor for internal nodes
    public Node(Byte nodeByte, int freq, Node left, Node right) {
        this.nodeByte = nodeByte;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node that) {
        // Compare nodes based on frequency
        int frequencyCompare = Integer.compare(this.freq, that.freq);
        if (frequencyCompare != 0) {
            return frequencyCompare;
        }

        // Handling null values for nodeByte
        if (this.nodeByte == null && that.nodeByte == null) {
            return 0;
        }
        if (this.nodeByte == null) {
            return -1;
        }
        if (that.nodeByte == null) {
            return 1;
        }
        return Byte.compare(this.nodeByte, that.nodeByte);
    }

    // Getters and Setters
    public Byte getNodeByte() {
        return nodeByte;
    }

    public void setNodeByte(Byte nodeByte) {
        this.nodeByte = nodeByte;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
