package com.FileCompressor.Huffman;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCompressor {

    public void huffCompressor(String inputFilePath, String outputFilePath) {
        try {
            FileInputStream inputStream = new FileInputStream(inputFilePath);
            byte[] input = new byte[inputStream.available()];
            int bytesRead = inputStream.read(input);
            if (bytesRead != input.length) {
                throw new IOException("Could not read the entire file");
            }
            PriorityQueue<Node> nodes = getNodes(input);
            Node root = createHuffTree(nodes);
            HashMap<Byte, String> huffmanCodes = generateHuffmanCodes(root);
            String encodedString = encode(input, huffmanCodes);
            byte[] encodedByte=toByteArray(encodedString);
            OutputStream outStream = new FileOutputStream(outputFilePath);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
            objectOutStream.writeObject(encodedByte);
            objectOutStream.writeObject(huffmanCodes);
            inputStream.close();
            objectOutStream.close();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public PriorityQueue<Node> getNodes(byte[] bytes) {
        PriorityQueue<Node> minheap = new PriorityQueue<>();
        HashMap<Byte, Integer> byteFreq = new HashMap<>();
        for (byte b : bytes) {
            byteFreq.put(b, byteFreq.getOrDefault(b, 0) + 1);
        }
        for (Map.Entry<Byte, Integer> entry : byteFreq.entrySet()) {
            minheap.add(new Node(entry.getKey(), entry.getValue()));
        }
        return minheap;
    }
     
     public Node createHuffTree(PriorityQueue<Node> minheap) {
        while (minheap.size() > 1) {
            Node left = minheap.poll();
            Node right = minheap.poll();
            Node parent = new Node(null, left.getFreq() + right.getFreq(), left, right);
            minheap.add(parent);
        }
        return minheap.poll();
    }


   

    
    

    private HashMap<Byte, String> generateHuffmanCodes(Node root) {
        HashMap<Byte, String> codes = new HashMap<>();
        generateHuffmanCodesRecursive(root, "", codes);
        return codes;
    }

    private void generateHuffmanCodesRecursive(Node node, String code, HashMap<Byte, String> codes) {
        if (node != null) {
            if (node.getNodeByte() != null) {
                codes.put(node.getNodeByte(), code);
            }
            generateHuffmanCodesRecursive(node.getLeft(), code + '0', codes);
            generateHuffmanCodesRecursive(node.getRight(), code + '1', codes);
        }
    }

    private String encode(byte[] input, HashMap<Byte, String> huffmanCodes) {
        StringBuilder encodedString = new StringBuilder();
        for (byte b : input) {
            encodedString.append(huffmanCodes.get(b));
        }
        return encodedString.toString();
    }

    private byte[] toByteArray(String encodedData) {
        int length = encodedData.length();
        byte[] byteArray = new byte[(length + 7) / 8];
        int idx=0;
        for (int i = 0; i < encodedData.length(); i+=8) {
            String temp;
            if(i+8>encodedData.length()){
                temp=encodedData.substring(i);
            }else{
                temp=encodedData.substring(i,i+8);
            }
            byteArray[idx]=(byte) Integer.parseInt(temp,2);
            idx++;
            
        }
        return byteArray;
    }

    
}
