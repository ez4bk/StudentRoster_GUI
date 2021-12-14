package com.example.project3_studentroster;

/**
 * An object class that represents NonResident students
 * Sub-class extended from Student class
 * @author Yuchen Wei, Denghao Sun
 */
public class NonResident extends Student{

    /**
     * Constructor that creates an Internation student.
     * @param name of the student to be created.
     * @param major of the student to be created.
     * @param credit that the student is taking.
     */
    public NonResident(String name, Major major, int credit){
        super(name, major, credit);
    }

    /**
     * Calculate the total fee of an Internation student.
     */
    @Override
    public void tuitionDue() {
        if (this.getFulltime()){
            this.setTuitionFee(N_FULLTIME_FEE + FULLTIME_U_FEE);
            if (this.getCredit() > REGULAR_CREDIT){
                this.setTuitionFee(this.getTuitionFee()+N_PARTTIME_FEE * (this.getCredit() - REGULAR_CREDIT));
            }
        }else {
            this.setTuitionFee(N_PARTTIME_FEE * this.getCredit() + PARTTIME_U_FEE);
        }
        this.setTuitionFee(this.getTuitionFee() - this.getFeePaid());
    }

}
