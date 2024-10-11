package com.FileCompressor.Huffman;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class HuffmanDecompressor {

    public void huffDecompressor(String inputFilePath, String outputFilePath) {
        try {
            FileInputStream inputStream = new FileInputStream(inputFilePath);
            ObjectInputStream objectInStream = new ObjectInputStream(inputStream);
            byte[] huffmanBytes = (byte[]) objectInStream.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) objectInStream.readObject();

            byte[] bytes = decomp(huffmanCodes, huffmanBytes);
            OutputStream outStream = new FileOutputStream(outputFilePath);
            outStream.write(bytes);
            inputStream.close();
            objectInStream.close();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] decomp(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length - 1);
            sb1.append(convertbyteInBit(!flag, b));
        }
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        java.util.List<Byte> list = new java.util.ArrayList<>();
        for (int i = 0; i < sb1.length();) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag && (i + count) <= sb1.length()) {
                String key = sb1.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            if (b != null) {
                list.add(b);
                i += count;
            } else {
                // Handle the case where b is null (possibly log an error or break the loop)
                break;
            }
        }
        byte[] b = new byte[list.size()];
        for (int j = 0; j < b.length; j++) {
            b[j] = list.get(j);
        }
        return b;
    }

    private static String convertbyteInBit(boolean flag, byte b) {
        int byte0 = b;
        if (flag) {
            byte0 |= 256;
        }
        String str0 = Integer.toBinaryString(byte0);
        if (flag || byte0 < 0) {
            return str0.substring(str0.length() - 8);
        } else {
            return str0;
        }
    }
}
