package com.hsenid.assignment1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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