package com.example.project3_studentroster;

/**
 * An object class that represents Resident students
 * Sub-class extended from Student class
 * @author Yuchen Wei, Denghao Sun
 */
public class Resident extends Student{
    private double aidAmount;

    /**
     * Constructor that creates an Internation student.
     * Initialize the number of aidAmount 0.
     * @param name of the student to be created.
     * @param major of the student to be created.
     * @param credit that the student is taking.
     */
    public Resident(String name, Major major, int credit){
        super(name, major, credit);
        this.aidAmount = 0;
    }

    /**
     * Change the value of aidAmount of an instance.
     * @param aidAmount is the number of financial aid from input.
     */
    @Override
    public void setAidAmount(double aidAmount) {
        this.aidAmount = aidAmount;
    }

    /**
     * Pass the value of aidAmount out.
     * @return aidAmount
     */
    @Override
    public double getAidAmount(){
        return this.aidAmount;
    }

    /**
     * Calculate the total fee of an Internation student.
     */
    @Override
    public void tuitionDue() {
        if (this.getFulltime()){
            this.setTuitionFee(R_FULLTIME_FEE + FULLTIME_U_FEE);
            if (this.getCredit() > REGULAR_CREDIT){
                this.setTuitionFee(this.getTuitionFee()+R_PARTTIME_FEE * (this.getCredit() - REGULAR_CREDIT));
            }
        }else {
            this.setTuitionFee(R_PARTTIME_FEE * this.getCredit() + PARTTIME_U_FEE);
        }
        this.setTuitionFee(this.getTuitionFee()-aidAmount);
        this.setTuitionFee(this.getTuitionFee() - this.getFeePaid());
    }

}

