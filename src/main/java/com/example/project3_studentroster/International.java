package com.example.project3_studentroster;

/**
 * An object class that represents Internation students
 * Sub-class extended from Student class
 * @author Yuchen Wei, Denghao Sun
 */
public class International extends NonResident{
    private boolean studyAbroad;

    /**
     * Constructor that creates an Internation student.
     * Initialize the status of studyAbroad to false.
     * @param name of the student to be created.
     * @param major of the student to be created.
     * @param credit that the student is taking.
     */
    public International(String name, Major major, int credit){
        super(name, major, credit);
        this.studyAbroad = false;
    }

    /**
     * Change the status of studyAbroad of an instance and re-calculate fee if necessary.
     * @param studyAbroad is the status given to change to.
     */
    @Override
    public void setStudyAbroad(boolean studyAbroad) {
        this.studyAbroad = studyAbroad;
        if (this.studyAbroad) {
            this.setFeePaid(0);
            this.setFeePaidDate(null);
            if (this.getCredit()>12) this.setCredit(12);
            this.tuitionDue();
        }

    }

    /**
     * Calculate the total fee of an Internation student.
     */
    @Override
    public void tuitionDue() {
        if (!studyAbroad){
            this.setTuitionFee(N_FULLTIME_FEE + FULLTIME_U_FEE + I_ADDITIONAL_FEE);
            if (this.getCredit() > FULLTIME_CREDIT)
                this.setTuitionFee(this.getTuitionFee() + N_PARTTIME_FEE * (this.getCredit() - FULLTIME_CREDIT));
        }else {
            this.setTuitionFee(FULLTIME_U_FEE + I_ADDITIONAL_FEE);
        }
        this.setTuitionFee(this.getTuitionFee() - this.getFeePaid());
    }

    /**
     * Pass the value of studyAbroad out.
     * @return studyAbroad of the instance.
     */
    @Override
    public boolean getStudyAbroad() {
        return this.studyAbroad;
    }
}

