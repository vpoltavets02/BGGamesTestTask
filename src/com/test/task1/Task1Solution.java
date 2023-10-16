package com.test.task1;

import java.util.*;

/*
    Idea:
    1. Get messages from the console
    2. Separate key and message text
    3. Save order of columns and their indexes from the key
    4. Creating and filling matrix with the letters and digraphs
    5. Converting matrix into the string with order from the key
    6. Returning result
 */

public class Task1Solution {
    //method 'main' is the entry point of the program
    public static void main(String[] args) {
        //getting the object of Scanner type for reading inputted data from console
        Scanner scanner = new Scanner(System.in);
        //message to ask user input their phrases
        System.out.println("Please enter your phrases: ");
        //variable for storing inputted phrases
        String input = "";
        //list for storing encrypted phrases
        List<String> result = new ArrayList<>();
        //loop for getting input data with condition to break cycle if message from user ends with 'EOF'
        while (!input.endsWith("EOF")) {
            //getting inputted data from console
            input = scanner.nextLine();
            //getting separated values of key and message
            String[] parts = input.split(" ");
            //adding encrypted message to the result list
            result.add(encrypt(parts[0], parts[1]));
        }
        //displaying result
        displayResult(result);
    }

    //method for parsing and saving values of key and its indexes
    public static Map<Integer, Integer> parseKey(String key) {
        //creating LinkedHashMap for storing values of key and its indexes in the same order
        Map<Integer, Integer> keyIndex = new LinkedHashMap<>();
        //loop for getting each character from String key representation
        for (int i = 0; i < key.length(); i++) {
            //saving separated value of the key and its index
            keyIndex.put(Character.getNumericValue(key.charAt(i)), i);
        }
        //returning created map
        return keyIndex;
    }

    //method for creating and filling matrix with digraphs and letters
    public static String[][] getFilledMatrix(int keyLength, String message) {
        //variable for storing number of rows
        int nRows = (int) Math.ceil(Math.sqrt(message.length()));
        //creating matrix with size nRows x keyLength
        String[][] matrix = new String[nRows][keyLength];
        //variable for monitoring current position in message while filling the matrix
        int textIndex = 0;
        //variable to check if we need to put digraph or letter in the cell
        boolean isDigraph = true;
        //loop for going through rows
        for (int i = 0; i < matrix.length; i++) {
            //loop for going through columns
            for (int j = 0; j < matrix[i].length; j++) {
                //fill matrix cell with letter if we didn't reach the end of the message
                matrix[i][j] = textIndex < message.length() ? String.valueOf(message.charAt(textIndex++)) : "";
                //check if is it a digraph cell
                if (isDigraph) {
                    //if it is a digraph cell add next letter
                    matrix[i][j] += textIndex < message.length() ? String.valueOf(message.charAt(textIndex++)) : "";
                    //change value on the opposite to fill next cell with letter
                    isDigraph = false;
                } else {
                    //change value on the opposite to fill next cell with digraph
                    isDigraph = true;
                }
            }
        }
        //returning filled matrix
        return matrix;
    }

    //method for converting matrix to one encrypted string
    public static String convertMatrixToString(String[][] matrix, Map<Integer, Integer> keyIndex) {
        //creating variable of StringBuilder type to unite all cells in one string
        StringBuilder builder = new StringBuilder();
        //loop for getting values from map by key
        for (int i = 0; i < keyIndex.size(); i++) {
            //loop for going through the matrix rows
            for (String[] strings : matrix) {
                //appending cell to string result
                builder.append(strings[keyIndex.get(i + 1)]);
            }
        }
        //returning string result
        return builder.toString();
    }

    //method for encrypting messages step by step
    public static String encrypt(String order, String message) {
        //getting a map with parsed keys and indexes
        Map<Integer, Integer> keyIndex = parseKey(order);
        //getting a filled matrix with letters and digraphs
        String[][] matrix = getFilledMatrix(keyIndex.size(), message);
        //returning result
        return convertMatrixToString(matrix, keyIndex);
    }

    //method for displaying the list of encrypted messages
    public static void displayResult(List<String> result) {
        //loop for going through the list of messages
        for (String s : result) {
            //displaying the value of encrypted message on the console
            System.out.println(s);
        }
    }
}