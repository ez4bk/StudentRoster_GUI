package com.example.project3_studentroster;

/**
 * Manipulate the Student array that performs as a roster.
 * Add, remove, and find students in the roster.
 * Sort the roster by students' names or the last date they paid the fee
 * Resize the array if more students are added.
 *
 * @author Yuchen Wei, Denghao Sun
 */
public class Roster {
    private static final int NOT_FOUND = -1;

    private Student[] roster;
    private int size; //keep track of the number of students in the roster

    /**
     * The method to find if a student exists in the roster.
     * Compare profiles of students to find.
     * @param student to be found in the roster.
     * @return i, the index of the target student in the array, NOT_FOUND = -1 if a student is not in found the roster
     */
    private int find(Student student) {
        for (int i = 0; i < size; i++){
            if (roster[i] == null) return NOT_FOUND;
            if (roster[i].getProfile().equals(student.getProfile())) return i;
        }
        return NOT_FOUND;
    }

    /**
     * Expand the Student array by increasing the size by 4.
     */
    private void grow() {
        Student[] newRoster = new Student[size + 4];

        for (int i = 0; i < size; i++) {
            newRoster[i] = roster[i];
        }
        roster = newRoster;
    }

    /**
     * Add a new student to the roster.
     * Initialize the array if there is not a roster.
     * Find if a student is already in the roster by using find() method, stop adding if one is.
     * Expand the array if necessary by using grow() method.
     * @param student to be added.
     * @return true if a student is successfully added, false if not.
     */

    public boolean add(Student student) {
        if (roster == null || size == 0) {
            roster = new Student[4];
            size = 1;
            roster[0] = student;
            return true;
        }

        if (find(student) != NOT_FOUND) return false;

        if (roster.length == size) grow();

        roster[size] = student;
        size++;
        return  true;
    }

    /**
     * Remove a student from the roster if one exists in the roster.
     * @param student is the target student to be removed.
     * @return true if a student is successfully removed, false if not.
     */

    public boolean remove(Student student) {
        if (roster == null || size == 0) return false;
        if (find(student) == NOT_FOUND) return false;

        for (int i = find(student); i < size-1; i++){
            roster[i] = roster[i+1];
        }
        size--;
        return true;
    }

    /**
     * Pass the private Student array, the roster out.
     * @return roster, the Student array.
     */
    public Student[] getRoster(){
        return roster;
    }

    /**
     * Pass the number of students in the roster out.
     * @return size, the length of the Student array.
     */
    public int getSize(){
        return size;
    }

    /**
     * Helper method that determines the order of two names.
     * @param student1 is the first student to be compared.
     * @param student2 is the second student to be compared.
     * @return 1 if student1 is alphabetically ahead of student2,
     *          0 if the two students have the same name,
     *          -1 if student2 is alphabetically ahead of student1,
     */
    private int compareName(String student1, String student2) {
        String[] arrOfName1 = student1.split(" ", 3);
        String[] arrOfName2 = student2.split(" ", 3);
        String st1LastName = arrOfName1[0];
        String st1FirstName = arrOfName1[1];
        String st2LastName = arrOfName2[0];
        String st2FirstName = arrOfName1[1];

        if ((st1FirstName + st1LastName).compareTo(st2FirstName + st2LastName) >= 0) {
            return 1;
        }
        else return -1;
    }

    /**
     * Sort the roster by students' names.
     */
    public void sortByName() {
        for (int i = 0; i < size-1; i++) {
            for (int j = i+1; j < size; j++) {
                String Name1 = roster[i].getProfile().getName();
                String Name2 = roster[j].getProfile().getName();
                if (compareName(Name1, Name2) == 1) {
                    Student[] temp = new Student[1];
                    temp[0] = roster[i];
                    roster[i] = roster[j];
                    roster[j] = temp[0];
                }
            }
        }
    }

    /**
     * Sort the roster by students' last payment date.
     */
    public void sortByPaymentDate() {
        for (int i = 0; i < size-1; i++) {
            for (int j = i+1; j < size; j++) {
                Date date1 = roster[i].getFeePaidDate();
                Date date2 = roster[j].getFeePaidDate();
                if (date1 == null || date2 == null) {
                    continue;
                }
                else if (date1.compareTo(date2) >= 0) {
                    Student[] temp = new Student[1];
                    temp[0] = roster[i];
                    roster[i] = roster[j];
                    roster[j] = temp[0];
                }
            }
        }
    }
}

