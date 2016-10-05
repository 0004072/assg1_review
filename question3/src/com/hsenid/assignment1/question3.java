package com.hsenid.assignment1;

import java.io.*;
import java.util.*;

/**
 * Class that implements the task given in question 3.
 */
public class question3
{
    private static BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
    public static void main( String[] args ) throws IOException
    {
        /*Subjects sb = new Subjects();

        Student arr[];
        String input;
        while(true){
            System.out.print("Number of students:");
            input = buf.readLine();
            if(input.matches("^[1]?\\d$")){
                arr = new Student[Integer.parseInt(input)];
                break;
            }
            System.out.println("Invalid input!");
        }

        int i = 0;
        String name, id;
        while(i < arr.length){
            System.out.print("Student's name:");
            name = buf.readLine();
            if(!name.matches("^[A-Za-z]{3,10}$")){
                System.out.printf("Invalid name \"%s\"! Should be 5-10 alphabetical characters!\n", name);
                continue;
            }

            id = String.format("%1$"+5+"s", (i+1)).replace(" ",  "0");

            Student std = new Student(id, name);
            System.out.printf("Adding %s's Marks...\n", name);
            for(int j = 0; j < sb.noOfSubjects(); j++){
                while(true){
                    System.out.printf("Marks for %s (leave blank for absent):", sb.subjectAtIndex(j));
                    input = buf.readLine();
                    if(input.matches("^[1]?\\d{1,2}(.\\d{1,2})?$")){
                        float f = Float.parseFloat(input);
                        if(f >= 0.0f && f <= 100.0f) {
                            std.addSubjectMarks(j, f);
                            break;
                        }
                    }
                    if(input.matches("^$")) {
                        std.addSubjectMarks(j, -1.0f);
                        break;
                    }
                    System.out.println("Invalid entry of marks! Should be a number between 0 and 100.");
                }
            }

            std.setTot();
            std.setAvg();
            std.setGrade();
            arr[i] = std;
            i++;
        }*/

        //test :: begin
        Subjects sb = new Subjects("maths", "sinhala", "science");
        Student a = new Student("001", "zain", 32.0f, -1.0f, 27.0f);
        Student b = new Student("002", "kasun", 78.0f, 79.0f, 83.0f);
        Student c = new Student("003", "mithila", 87.0f, 95.0f, 93.0f);
        Student d = new Student("004", "amila", 56.0f, 81.0f, 61.0f);
        Student arr[] = {a,b,c,d};
        for(Student s : arr) {
            s.setTot();
            s.setAvg();
            s.setGrade();
        }
        //test :: end

        System.out.println("Data acquisition is complete! You may now query these data...");
        String cmd;
        cmd:
        while(true){
            System.out.print("CMD:\\>");
            cmd = buf.readLine();
            switch(cmd){
                case "report-all":
                    for(Student std : arr){
                        System.out.println("______");
                        System.out.println(std.getName());
                        std.showReport(sb);
                        System.out.println("------");
                    }
                    break;

                case "showgrd":
                    Student.sort(arr, 'g');
                    for(Student s : arr)
                        System.out.printf("%s : %s\n", s.getName(), s.getGrade());
                    break;

                case "showsub":
                    sb.showAllSubs();
                    System.out.print("Subject name :");
                    System.out.flush();
                    String sub = buf.readLine();
                    Integer n = sb.indexOfSubject(sub);
                    if(n == -1)
                        System.out.println("Invalid subject name! Check your entry...");
                    else {
                        Student.sort(arr, n);
                        System.out.println("Marks for "+sub);
                        for(Student s : arr){
                            System.out.printf("%s : %f\n", s.getName(), s.getSubjectMarks(n));
                        }
                    }
                    break;
                case "x":
                case "exit":
                    System.out.println("Bye!");
                    break cmd;

                default:
                    System.out.println("Invalid command!");
            }
        }
    }
}