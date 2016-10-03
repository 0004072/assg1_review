package com.hsenid.assignment1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class that implements the task given in question 1.
 */
public class question1 {
    private static BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        double arr[] = builder();
        arr = bubbleSort(arr);

        System.out.print("Choose your orientation to display the items(H=horizontal V=Vertical):");
        System.out.flush();
        String or = buf.readLine();
        if (!or.matches("^[hHvV]$"))
            System.out.println("Invalid orientation! Using default...");
        for (double i : arr) {
            switch (or) {
                case "h":
                case "H":
                    System.out.print(i + " ");
                    break;

                case "v":
                case "V":
                    System.out.println(i);
                    break;

                default:
                    System.out.print(i + " ");
            }

        }
    }

    /**
     * Performs bubble sort over a given integer array, in the given order. Confirms the order during the execution of the method.
     *
     * @param arr the array that needs to be sorted
     * @return returns the sorted array.
     * @throws IOException in an occurrence of a problem in I/O
     */
    private static double[] bubbleSort(double arr[]) throws IOException {
        boolean desc = false;
        System.out.print("Select the sort order(A=Ascending/D=Descending):");
        System.out.flush();
        String ord = buf.readLine();
        if (ord.matches("^[Dd]"))
            desc = true;
        else
            System.out.println("Invalid order! Using default...");

        while (true) {
            boolean swapped = false;
            for (int i = 0; i < arr.length - 1; i++) {
                if (!desc) {
                    if (arr[i] > arr[i + 1]) {
                        swapped = true;
                        double tmp = arr[i];
                        arr[i] = arr[i + 1];
                        arr[i + 1] = tmp;
                    }
                } else {
                    if (arr[i] < arr[i + 1]) {
                        swapped = true;
                        double tmp = arr[i];
                        arr[i] = arr[i + 1];
                        arr[i + 1] = tmp;
                    }
                }
            }
            if (!swapped)
                break;
        }

        return arr;
    }

    /**
     * Builds the array based on the given input by the user. Asks for values one at a time. If the values are not matching the typical number format, an error message will be displayed.
     *
     * @return the array of integer values that needs to be sorted.
     * @throws IOException when there is a problem occurred in the I/O.
     */
    private static double[] builder() throws IOException {
        StringBuffer input = new StringBuffer();
        System.out.println("Insert the numbers you need sorted. You may either add one by one or a series of values which are comma separated (i.e 5,4,3). SHOULD stick to this format if you are giving a series of comma separated values.");
        cont_add:
        while (true) {
            System.out.print("values? >");
            System.out.flush();
            String num = buf.readLine();
            if (!num.matches("^([+-]?\\d+(\\.\\d+)?[,]?)+([+-]?\\d+(\\.\\d+)?)?$"))
                System.out.println("Invalid number detected!");

            else
                input.append(num);

            while (true) {
                System.out.print("Continue adding?(Y/N):");
                System.out.flush();
                String cont = buf.readLine();
                switch (cont) {
                    case "Y":
                    case "y":
                        input.append(",");
                        continue cont_add;

                    case "n":
                    case "N":
                        break cont_add;
                }
            }

        }

        String[] arr = input.toString().split(",");
        double toSort[] = new double[arr.length];
        for (int i = 0; i < arr.length; i++)
            toSort[i] = Double.parseDouble(arr[i]);

        return toSort;
    }
}
