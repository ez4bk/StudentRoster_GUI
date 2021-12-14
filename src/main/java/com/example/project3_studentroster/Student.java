package com.example.project3_studentroster;

import java.util.Calendar;
import java.text.DecimalFormat;

/**
 * The super object class that represents a Student.
 * @author Yuchen Wei, Denghao Sun
 */
public class Student {
    public static final int REGULAR_CREDIT = 16;
    public static final int FULLTIME_CREDIT = 12;
    public static final double R_FULLTIME_FEE = 12536;
    public static final double R_PARTTIME_FEE = 404; //per credit our
    public static final double N_FULLTIME_FEE = 29737;
    public static final double N_PARTTIME_FEE = 966; //per credit hour
    public static final double FULLTIME_U_FEE = 3268;
    public static final double PARTTIME_U_FEE = 3268 * 0.8;
    public static final double I_ADDITIONAL_FEE = 2650;

    private Profile profile;
    private int credit;
    private double tuitionFee;
    private double feePaid;
    private Date feePaidDate;
    private boolean Fulltime; //true for full time, false for part time

    /**
     * Constructor that creates a Student.
     * Integrate the name and the major of the student into a profile.
     * @param name of the student to be created.
     * @param major of the student to be created.
     * @param credit that the student is taking.
     */
    public Student(String name, Major major, int credit){
        this.profile = new Profile(name, major);
        this.credit = credit;
        if (credit >= FULLTIME_CREDIT)
            this.Fulltime = true;
        else
            this.Fulltime = false;
        this.tuitionFee = 0;
        this.feePaid = 0;
    }

    /**
     * Generate all information of a student into one line of String.
     * @return info String that contains all information of a student
     */
    @Override
    public String toString() {
        String info = this.profile.toString() + ":" + this.credit + " credit hours:tuition due:%.2f"
                + tuitionFee + ":total payment:%.2f" + this.feePaid + ":last payment date:"
                + ((this.feePaidDate==null) ? "--/--/--" : this.feePaidDate.toString());
        if (this instanceof Resident) info += ":resident";
        if (this instanceof NonResident) info += ":non-resident";
        if (this instanceof TriState) info += ":non-resident(tri-state):" + this.getArea();
        if (this instanceof International) info += ":International" + (this.getStudyAbroad() ? ":study abroad" : "");
        return info;
    }

    /**
     * Pass the profile of a student, which contains the name and major, out.
     * @return profile of a student.
     */
    public Profile getProfile(){
        return this.profile;
    }

    /**
     * Pass the number of credit a student is taking out.
     * @return credit number that a student is taking.
     */
    public int getCredit(){
        return this.credit;
    }

    /**
     * Pass the amount of total tuition fee a student has.
     * @return tuitionFee in total.
     */
    public double getTuitionFee(){
        return  this.tuitionFee;
    }

    /**
     * Pass the date of the last payment out.
     * @return feePaidDate of the last payment.
     */
    public Date getFeePaidDate(){
        return this.feePaidDate;
    }

    /**
     * Pass the total amount of fee paid.
     * @return feePaid in total.
     */
    public double getFeePaid(){
        return this.feePaid;
    }

    /**
     * Pass the status if a student is a fulltime student out.
     * @return Fulltime status of a student.
     */
    public boolean getFulltime(){
        return this.Fulltime;
    }

    /**
     * Change the amount of tuitionFee a student needs to pay.
     * @param tuitionFee the input tuition fee to be stored.
     */
    public void setTuitionFee(double tuitionFee){
        this.tuitionFee = tuitionFee;
    }

    /**
     * Change the amount of fee a student has paid.
     * @param feePaid the input amount of fee paid.
     */
    public void setFeePaid(double feePaid){
        this.feePaid = feePaid;
    }

    /**
     * Change the date of the last payment.
     * @param feePaidDate the input date when a payment is made.
     */
    public void setFeePaidDate(Date feePaidDate){
        this.feePaidDate = feePaidDate;
    }

    /**
     * Change the credit hour of a student.
     * @param credit hour to be updated.
     */
    public void setCredit(int credit){
        this.credit = credit;
    }

    /**
     * Perform a payment.
     * Accumulate feePaid, record the date of the last payment, and deduct the total tuition fee.
     * @param feePaidDate the input date when a payment is made.
     * @param feePaid the input tuition fee to be stored.
     */
    public void payment(double feePaid, Date feePaidDate) {
        if (this.tuitionFee >= feePaid){
            this.feePaid = this.feePaid + feePaid;
            this.feePaidDate = feePaidDate;
            this.tuitionFee = this.tuitionFee - feePaid;
        }
    }

    /**
     * Calculate the total fee of an Internation student.
     * A method to be overrided in all sub-classes.
     */
    public void tuitionDue(){ }
    /**
     * Change the status of studyAbroad of an instance and re-calculate fee if necessary.
     * A method to be overrided in International.
     * @param studyAbroad is the status given to change to.
     */
    public void setStudyAbroad(boolean studyAbroad){}
    /**
     * Change the value of aidAmount of an instance.
     * A method to be overrided in Resident.
     * @param aidAmount is the number of financial aid from input.
     */
    public void setAidAmount(double aidAmount){}
    /**
     * Pass the area where the student is from out.
     * A method to be overrided in TriState.
     * @return area where the student is from.
     */
    public String getArea(){return "";}
    /**
     * Pass the value of studyAbroad out.
     * A method to be overrided in International.
     * @return studyAbroad of the instance.
     */
    public boolean getStudyAbroad() {return false;}

    /**
     * Pass the value of aidAmount out.
     * A method to be overrided in Resident.
     * @return aidAmount
     */
    public double getAidAmount(){return 0;}
}
