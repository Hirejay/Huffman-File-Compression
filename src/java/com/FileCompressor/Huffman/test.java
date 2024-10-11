/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FileCompressor.Huffman;

/**
 *
 * @author Jay Hire
 */
public class test {
    
    public static void main(String args[]){
        String i="E:/cri.txt";
        String o="E:/cri-com.txt";
        HuffmanCompressor h=new HuffmanCompressor();
        h.huffCompressor(i, o);
        HuffmanDecompressor hd=new HuffmanDecompressor();
        String p="E:/cri-dec.txt";
        hd.huffDecompressor(o, p);
    }
    
}
