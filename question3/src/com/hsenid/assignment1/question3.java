package com.hsenid.assignment1;

import java.io.*;
import java.util.*;

public class question3
{
    private static BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
    public static void main( String[] args ) throws IOException
    {
        Subjects sb = new Subjects();

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
                    System.out.printf("Marks for %s:", sb.subjectAtIndex(j));
                    input = buf.readLine();
                    if(input.matches("^[1]?\\d{1,2}(.\\d{1,2})?$")){
                        float f = Float.parseFloat(input);
                        if(f >= 0.0f && f <= 100.0f) {
                            std.addSubjectMarks(j, f);
                            break;
                        }
                    }
                    System.out.println("Invalid entry of marks! Should be a number between 0 and 100.");
                }
            }

            std.setTot();
            std.setAvg();
            std.setGrade();
            arr[i] = std;
            i++;
        }

        //test :: begin
        /*Subjects sb = new Subjects("maths", "sinhala", "science");
        Student a = new Student("001", "zain", 32.0f, 33.0f, 27.0f);
        Student b = new Student("002", "kasun", 78.0f, 79.0f, 83.0f);
        Student c = new Student("003", "mithila", 87.0f, 95.0f, 93.0f);
        Student d = new Student("004", "amila", 56.0f, 81.0f, 61.0f);
        Student arr[] = {a,b,c,d};
        for(Student s : arr) {
            s.setTot();
            s.setAvg();
            s.setGrade();
        }*/
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
                    break cmd;

                default:
                    System.out.println("Invalid command!");
            }
        }
    }
}

/**
 * Blueprint of the student object managed in the system.
 */
class Student{
    private BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

    private String name, id, grade;
    private float tot = 0.0f, avg = 0.0f;
    private Map<Integer, Float> sub_marks = new HashMap<>();

    /**
     * Constructor that interactively instantiates the Student class.
     * @throws IOException
     */
    public Student() throws IOException {
        //Initial Implementation. Hence no validations!
        System.out.print("Student's ID:");
        System.out.flush();
        this.id = buf.readLine();

        System.out.print("Student's Name:");
        System.out.flush();
        this.name = buf.readLine();
    }

    /**
     * Constructor that is used to hardcode and instantiate the Student class. Used for testing purposes.
     * @param id ID of the student
     * @param name Name of the student
     * @throws IOException
     */
    public Student(String id, String name) throws IOException {
        this.id = id;
        this.name = name;
    }

    /**
     * Constructor that is used to hardcode and instantiate the Student class. Used for testing purposes.
     * @param id ID of the student
     * @param name Name of the student
     * @param marks Marks that the student has scored
     */
    public Student(String id, String name, float ... marks){
        this.id = id;
        this.name = name;
        for(int i = 0; i < marks.length; i++){
            this.sub_marks.put(i, marks[i]);
        }
    }

    /**
     * Getter of the name
     * @return Student.name of the instance
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Student.name of the instance.
     * @param name Value that the instance of Student.name should take.
     */
    public void setName(String name) {
        this.name = name;
    }

    public float getTot() {
        return tot;
    }

    public void setTot() {
        Iterator i = sub_marks.entrySet().iterator();
        while(i.hasNext()){
            Map.Entry pair = (Map.Entry)i.next();
            float mk = (Float)pair.getValue();
            if(!(mk < 0.0f))
                this.tot += mk;
        }
    }

    public float getAvg() {
        return avg;
    }

    public void setAvg() {
        // TODO: 10/4/16 Improve this in the next commit to avoid absent marks.
        // TODO: 10/5/16 Test the method for ommitting the absent marks. 
        int i = 0;
        Iterator it = this.sub_marks.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry sub = (Map.Entry)it.next();
            float mark = (Float)sub.getValue();
            if(mark >= 0.0f)
                i++;
        }
        this.avg = this.tot/((float)i);
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade() {
        String grade;
        if(this.avg >= 75.0f)
            this.grade = "A";

        else if(this.avg >= 55.0f)
            this.grade = "B";

        else if(this.avg >= 35.0f)
            this.grade = "C";

        else if(this.avg >= 25.0f)
            this.grade = "S";

        else
            this.grade = "F";
    }

    public void setGrade(String str){
        this.grade = str;
    }

    public void addSubject(Integer id){
        System.out.println(this.sub_marks.put(id, -1.0f));
    }

    public void addSubjectMarks(Integer id, Float m){
        System.out.println(this.sub_marks.putIfAbsent(id, m));
    }

    public float getSubjectMarks(Integer id){
        return sub_marks.get(id);
    }

    public void changeMarks(Integer id, Float oldVal, Float newVal){
        System.out.println(this.sub_marks.replace(id, oldVal, newVal));
    }

    public void showReport(Subjects sb){
        Iterator i = sub_marks.entrySet().iterator();
        while(i.hasNext()){
            Map.Entry sub = (Map.Entry)i.next();
            System.out.printf("%s = %s\n", sb.subjectAtIndex((Integer)sub.getKey()), sub.getValue());
        }
    }

    public static void swap(Student arr[], int i, int j){
        Student tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void sort(Student arr[], char cr) {
        while (true) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - 1; j++) {
                String cur = "", next = "";
                switch (cr) {
                    case 'n':
                        cur = arr[j].getName();
                        next = arr[j + 1].getName();
                        break;
                    case 'g':
                        cur = arr[j].getGrade();
                        next = arr[j + 1].getGrade();
                        break;
                }

                for (int i = 0; ; i++) {
                    if (i == cur.length() || i == next.length())
                        break;

                    if (cur.charAt(i) < next.charAt(i))
                        break;

                    else if (cur.charAt(i) == next.charAt(i))
                        continue;

                    else {
                        swap(arr, j, j + 1);
                        swapped = true;
                        break;
                    }
                }
            }
            if (!swapped)
                break;
        }
    }

    /**
     * Sorts a given array of com.hsenid.assignment1.Student based on the marks for a particular subject, on the ascending order.
     * @param arr array that needs to be sorted.
     * @param i index of the subject in the subjects array as a java.lang.Integer object.
     */
    public static void sort(Student arr[], Integer i){
        while(true){
            boolean swapped = false;
            for(int j = 0; j < arr.length-1; j++){
                float markj = arr[j].getSubjectMarks(i);
                float markj1 = arr[j+1].getSubjectMarks(i);

                if(markj > markj1){
                    swap(arr, j, j+1);
                    swapped = true;
                }
            }
            if(!swapped)
                break;
        }
    }
}

/**
 * Represents the set of subjects that is offered in the class. Contains the data of all subjects in a collection, and the common operations that needs to be performed over the data set.
 */
class Subjects{
    /**
     * This is used for the input capturing purposes within the class.
     */
    private BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

    /**
     * An array of strings that contains the subject names. JVM generated indices of the entries are used as the identifiers inside the com.hsenid.assignment1.Student.
     */
    private String[] subjects;

    /**
     * Plain constructor that interactively builds the instance with user's inputs.
     * @throws IOException in an event of java.io.IOException
     */
    public Subjects() throws IOException{
        int nos = 0;
        String input;
        while (true) {
            System.out.print("How many subjects do you wish to have?");
            System.out.flush();
            input = buf.readLine();
            if(input.matches("^[1]?\\d$")){
                nos = Integer.parseInt(input);
                break;
            }
            System.out.println("Invalid number!");
        }

        subjects = new String[nos];
        int i=0;
        while(true){
            if(i == nos)
                break;
            System.out.printf("Name of Subject %d:", i+1);
            input = buf.readLine();
            //Subject name could be validated here
            if(!true) {
                //If the validation fails
                continue;
            }
            subjects[i] = input;
            i++;
        }
    }

    /**
     * Instantiates the class with the given array of subjects.
     * @param subs List of subjects that should be held in the object instance.
     */
    public Subjects(String ... subs){
        this.subjects = subs;
    }

    /**
     * Finds the index at which a given subject is held in the Subjects.subjects array.
     * @param subject string value representing the name of the subject.
     * @return index that the given string is held in if the string was found, -1 otherwise.
     */
    public int indexOfSubject(String subject){
        for(int i = 0; i<subjects.length; i++){
            if(subjects[i].matches(subject))
                return i;
        }
        return -1;
    }

    /**
     * Finds the subject name held at the given index.
     * @param i index that needs to be checked at.
     * @return Returns the subject name as a String if found. Will display an error message and return null otherwise.
     */
    public String subjectAtIndex(int i){
        String sb = null;
        try {
            sb = subjects[i];
        }
        catch(ArrayIndexOutOfBoundsException ex){
            System.out.println("No subject assigned!");
        }
        return sb;
    }

    /**
     * Changes an existing subject's name to a given value. Displays a message indicating the success/failure state.
     * @param sbOld Old name of the subject
     * @param sbNew New name that the subject should take
     */
    public void setSubject(String sbOld, String sbNew){
        int i;
        for(i = 0; i<subjects.length; i++){
            if(subjects[i].matches(sbOld)){
                subjects[i] = sbNew;
                System.out.printf("Subject name changed! %s to %s\n", sbOld, sbNew);
                return;
            }
        }
        System.out.println("Subject name does not exist!");
    }

    /**
     * Finds the number of registered subjects in the system.
     * @return the existing number of subjects.
     */
    public int noOfSubjects(){
        return this.subjects.length;
    }

    /**
     * Displays all available subjects, registered in the system.
     */
    public void showAllSubs(){
        for(int i = 0; i < subjects.length; i++)
            System.out.printf("%1$"+15+"s", String.format("(%d)%s ", i+1, subjects[i]));
        System.out.println();
    }
}
