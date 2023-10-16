package com.test.task2;

import java.util.Scanner;

/*
    Idea:
    1. Get input number sequence
    2. Parse the last three numbers of this sequence into Integer format
    3. Find differences between last three numbers
    4. Find increment number
    5. Count next three numbers in this sequence
    6. Return result in the string format
 */

public class Task2Solution {
    //method 'main' is the entry point of the program
    public static void main(String[] args) {
        //getting the object of Scanner type for reading inputted data from console
        Scanner scanner = new Scanner(System.in);
        //message to ask user input their number sequence
        System.out.println("Please enter your sequence: ");
        //getting inputted line from console
        String input = scanner.nextLine();
        //printing result
        System.out.println(getResult(input));
    }

    //method for parsing three last elements from the sequence and returning result in the array
    public static int[] parseLastThreeElements(String[] input) {
        //variable for getting current position in the array
        int index = 0;
        //creating array for storing 3 last elements
        int[] output = new int[3];
        //loop in which we parse 3 last numbers from the string
        for (int i = input.length - 3; i < input.length; i++) {
            //parsing number from string to Integer
            output[index] = Integer.parseInt(input[i]);
            index++;
        }
        //returning result array
        return output;
    }

    //method for finding difference between 3 last elements
    public static int[] findDifference(int[] input) {
        //creating new array for storing result
        int[] output = new int[input.length - 1];
        //loop for filling created array
        for (int i = 0; i < input.length - 1; i++) {
            //finding difference between elements
            output[i] = input[i + 1] - input[i];
        }
        //returning result array
        return output;
    }

    //method for finding next three numbers
    public static String findNextThreeNums(int lastNum, int lastDif, int inc) {
        //creating variable of StringBuilder type to unite all values in one string
        StringBuilder builder = new StringBuilder();
        //loop in which we count next 3 numbers of the sequence
        for (int i = 1; i <= 3; i++) {
            //count next term
            lastDif += inc;
            //getting next number
            lastNum += lastDif;
            //appending calculated number to result string
            builder.append(lastNum)
                    .append(" ");
        }
        //returning result string
        return builder.toString().trim();
    }

    //method of obtaining the final result
    public static String getResult(String input) {
        //splitting the whole sequence into numbers in string format
        String[] nums = input.split(" ");
        //getting parsed last three numbers
        int[] lastThreeElements = parseLastThreeElements(nums);
        //getting differences between last numbers
        int[] differences = findDifference(lastThreeElements);
        //calculating increment number
        int incrementor = differences[1] - differences[0];
        //returning result in string format
        return findNextThreeNums(lastThreeElements[2], differences[1], incrementor);
    }
}