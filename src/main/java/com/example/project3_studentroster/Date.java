package com.example.project3_studentroster;

import java.util.StringTokenizer;
import java.util.Calendar;

/**
 * A date object class that stores, compares dates,and checks date validity.
 *
 * @author Yuchen Wei, Denghao Sun
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    public static final int TWENTYTWENTYONE = 2021;
    public static final int UP_TO_YEAR = Calendar.getInstance().get(Calendar.YEAR);
    public static final int UP_TO_MONTH = Calendar.getInstance().get(Calendar.MONTH) + 1;
    public static final int UP_TO_DAY = Calendar.getInstance().get(Calendar.DATE);
    public static final int[] BIG_MONTH = { 1, 3, 5, 7, 8, 10, 12 };
    public static final int[] SMALL_MONTH = { 4, 6, 9, 11 };

    /**
     * Constructor of a Date object that takes a string input of date in
     * "mm/dd/yyyy" format and tokenize it into corresponding fields to construct an
     * object.
     *
     * @param date a string input of date in "mm/dd/yyyy" format.
     */
    public Date(String date) {
        StringTokenizer dateToken = new StringTokenizer(date, "-");
        int[] dateNumber = new int[3];
        for (int i = 0; i < 3; i++) {
            dateNumber[i] = Integer.parseInt(dateToken.nextToken());
        }
        this.year = dateNumber[0];
        this.month = dateNumber[1];
        this.day = dateNumber[2];
    }

    /**
     * Non-parameter constructor of a Date object that creates an object with
     * today’s date
     */
    public Date() {
        this.year = UP_TO_YEAR;
        this.month = UP_TO_MONTH;
        this.day = UP_TO_DAY;
    }

    /**
     * A method that checks if the date object is valid and is after year 2021.
     *
     * @return true if the date is valid, false otherwise.
     */
    public boolean isValid() {
        // Verify validity of the year
        if (this.year != TWENTYTWENTYONE)
            return false;
        if (this.month > UP_TO_MONTH)
            return false;
        if (this.month == UP_TO_MONTH && this.day > UP_TO_DAY)
            return false;

        // Verify if a big or small months has correct number of days
        if (this.day < 1 || this.month < 1 || this.month > 12)
            return false;
        if (binarySearch(BIG_MONTH, this.month) >= 0 && this.day > 31)
            return false;
        if (binarySearch(SMALL_MONTH, this.month) >= 0 && this.day > 30)
            return false;
        if (this.month == 2 && this.day > 28)
            return false;

        return true;
    }

    /**
     * An override method that compare two dates.
     *
     * @param date the date to be compared with.
     * @return true if two dates are the same, false otherwise.
     */
    @Override
    public int compareTo(Date date) {

        if (this.year < date.year)
            return -1;
        if (this.year > date.year)
            return 1;

        if (this.month < date.month)
            return -1;
        if (this.month > date.month)
            return 1;

        if (this.day < date.day)
            return -1;
        if (this.day > date.day)
            return 1;

        return 0;
    }

    /**
     * A binary search method to find if the given month is in the array of big or
     * small months.
     *
     * @param monthArray either big month array or small month array to be searched.
     * @param key        the month number to be found in the array.
     * @return the index of key in the array if found, -1 if not found.
     */
    private int binarySearch(int[] monthArray, int key) {
        int left = 0, right = monthArray.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (monthArray[mid] == key)
                return mid;
            if (monthArray[mid] < key)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return -1;
    }

    /**
     * An override method that generates the date info as a string.
     *
     * @return the string variable of a date in "mm/dd/yyyy".
     */
    @Override
    public String toString() {
        String dateString = this.month + "/" + this.day + "/" + this.year;
        return dateString;
    }
}

