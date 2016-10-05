package com.hsenid.assignment1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Blueprint of the student object managed in the system.
 */
public class Student{
    private BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

    private String name, id, grade;
    private float tot = 0.0f, avg = 0.0f;
    private Map<Integer, Float> sub_marks = new HashMap<>();

    /**
     * Constructor that interactively instantiates the Student class.
     * @throws IOException in case an I/O problem arises.
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
     * @throws IOException in case an I/O problem arises.
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

    /**
     * Retrieve the total marks of the relevant instance.
     * @return the total mark as a float value.
     */
    public float getTot() {
        return tot;
    }

    /**
     * Sets the total value for the instance. Iterates through the Student.sub_marks instance and sums up the marks.
     * All the mark entries in the map with a value less than zero will be ignored.
     */
    public void setTot() {
        Iterator i = sub_marks.entrySet().iterator();
        while(i.hasNext()){
            Map.Entry pair = (Map.Entry)i.next();
            float mk = (Float)pair.getValue();
            if(!(mk < 0.0f))
                this.tot += mk;
        }
    }

    /**
     * Gets the average
     * @return the current value of the Student.avg.
     */
    public float getAvg() {
        return avg;
    }

    /**
     * Sets the average mark of the student based on the total. Here, the absent subjects are ignored.
     */
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

    /**
     * Gets the current grade of the student.
     * @return returns the present grade ranging in A,B,C,S,F.
     */
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

    /**
     * Sets the grade of the student.
     * @param str value that should be held under Student.grade
     */
    public void setGrade(String str){
        this.grade = str;
    }

    /**
     * Sets the subject ID.
     * @param id the value that the key of the Map.Entry should take
     */
    public void addSubject(Integer id){
        System.out.println(this.sub_marks.put(id, -1.0f));
    }

    /**
     * Adds a subject with marks to the Map.
     * @param id the value that the key of Map.Entry should take
     * @param m the value that should be stored under the key in Map.Entry
     */
    public void addSubjectMarks(Integer id, Float m){
        System.out.println(this.sub_marks.putIfAbsent(id, m));
    }

    /**
     * Gets the marks of a particular subject.
     * @param id Key of Map.Entry that the value under should be retrieved.
     * @return marks for the given ID.
     */
    public float getSubjectMarks(Integer id){
        return sub_marks.get(id);
    }

    /**
     * Changes the marks of a given subject
     * @param id value of Map.Entry that the Map.
     * @param oldVal old value for marks
     * @param newVal new value for marks
     */
    public void changeMarks(Integer id, Float oldVal, Float newVal){
        System.out.println(this.sub_marks.replace(id, oldVal, newVal));
    }

    /**
     * Generates the report of performance for each student that contains marks for each subject, total and average.
     * @param sb Array of subjects that the students have taken.
     */
    public void showReport(Subjects sb){
        Iterator i = sub_marks.entrySet().iterator();
        while(i.hasNext()){
            Map.Entry sub = (Map.Entry)i.next();
            float mk = (Float)sub.getValue();
            String s = mk < 0.0f ? "ab" : Float.toString(mk);
            System.out.printf("%s = %s\n", sb.subjectAtIndex((Integer)sub.getKey()), s);
        }
        System.out.printf("Total : %f\n", this.getTot());
        System.out.printf("Average : %f\n", this.getAvg());
    }

    /**
     * Swaps two student objects held in an array of type Student.
     * @param arr array that contains the two values that should be swapped
     * @param i index to be swapped
     * @param j index to be swapped with
     */
    public static void swap(Student arr[], int i, int j){
        Student tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * Sorts an array containing Student objects based on either grade, or by name.
     * @param arr array object needs to be sorted
     * @param cr criteria that should be sorted on(n = name &amp;&amp; g = grade)
     */
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