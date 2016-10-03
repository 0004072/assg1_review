package com.hsenid.assignment1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class question2
{
    /**
     * Used by the entire class for capture user keyboard inputs.
     */
    private static BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

    public static void main( String[] args ) throws IOException
    {
        //Call your methods here
    }

    /**
     * Generates multiplication table based on the user's inputs.
     * @throws IOException if a problem occurred in I/O.
     */
    public static void multTbl() throws IOException{
        int span = 0, step = 0;
        String input = new String();
        while(true){
            System.out.print("Enter the span that your table should have:");
            System.out.flush();
            input = buf.readLine();
            if(input.matches("^\\d{1,2}")){
                span = Integer.parseInt(input);
                if(span <= +10 && span > 0)
                    break;
            }
            System.out.println("Invalid value! Should be in between 0 & +10!");
        }

        while(true){
            System.out.print("Enter the step size:");
            System.out.flush();
            input = buf.readLine();
            if(input.matches("^[+-]?\\d{1,2}")){
                step = Integer.parseInt(input);
                if(step <= +10 && step >= -10)
                    break;
            }
            System.out.println("Invalid value! Should be in between -10 & +10 except zero!");
        }

        for (int i = 0; i <= span; i++) {
            //System.out.print(String.format("%1$"+4+"s", String.format("%d|", i)));
            for (int j = 0, k = step > 0? 1: -1; j <= span*abs(step); j+=abs(step), k+=step) {
                String tmp = "";
                if (i == 0)
                    if (j == 0)
                        tmp += "x";
                    else
                        tmp = Integer.toString(k);
                else if (j == 0)
                    tmp = Integer.toString(i);
                else
                    tmp = Integer.toString(i * k);

                String val = String.format("%s", tmp);
                val = String.format("%1$" + 4 + "s", val);
                if(j == 0)
                    val = String.format("%s |", val);
                System.out.print(val);
            }

            if (i == 0) {
                System.out.println();
                for(int j = 0; j <= span*abs(step); j+=abs(step)){
                    String str = "----";
                    if(j == 0)
                        str = "------";
                    System.out.print(str);
                }
            }
            System.out.println();
        }
    }

    /**
     * Generates the power table up to a user-defined span. Span is restricted only between 1 and 6 for the purposes of formatting and precision errors that could possibly rise when handling negative powers.
     * @throws IOException in case of an I/O problem occurs.
     */
    public static void powTbl() throws IOException{
        int span = 0;
        String input = new String();
        while(true){
            System.out.print("Enter the span that your table should have:");
            System.out.flush();
            input = buf.readLine();
            if(input.matches("^\\d{1,2}")){
                span = Integer.parseInt(input);
                if(span <= 6 && span > 0)
                    break;
            }
            System.out.println("Invalid value! Should be a number in between 0 & +6!");
        }

        for (int i = 0; i <= span; i++) {
            //System.out.print(String.format("%1$"+4+"s", String.format("%d|", i)));
            for (int j = 0; j <= span; j+=1) {
                String tmp = "";
                if (i == 0)
                    if (j == 0)
                        tmp += "^";
                    else
                        tmp = Integer.toString(j);
                else if (j == 0)
                    tmp = Integer.toString(i);
                else
                    tmp = Integer.toString(pow(i, j));

                String val = String.format("%s", tmp);
                val = String.format("%1$" + 6 + "s", val);
                if(j == 0)
                    val = String.format("%s |", val);
                System.out.print(val);
            }

            if (i == 0) {
                System.out.println();
                for(int j = 0; j <= span; j+=1){
                    String str = "------";
                    if(j == 0)
                        str = "--------";
                    System.out.print(str);
                }
            }
            System.out.println();
        }
    }

    /**
     * Generates the absolute value of a given integer.
     * @param num Integer that the absolute of needs to be found
     * @return returns the absolute value of the integer given.
     */
    private static int abs(int num){
        if(num < 0)
            return num*(-1);
        return num;
    }

    /**
     * Finds the power of a given base to a given index.
     * @param base number that the power should be found of
     * @param index index that the base should be raised to. Should be a NON-NEGATIVE integer.
     * @return value of the base raised to the given index
     */
    private static int pow(int base, int index){
        if(index <= 0)
            return 1;
        return base * pow(base, --index);
    }
}
