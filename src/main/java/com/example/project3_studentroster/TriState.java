package com.example.project3_studentroster;

/**
 * An object class that represents TriState students
 * Sub-class extended from Student class
 * @author Yuchen Wei, Denghao Sun
 */
public class TriState extends NonResident{
    private String area;    //only 2 capital letters are allowed, "NJ", "NY", "CT"

    /**
     * Constructor that creates an TriState student.
     * @param name of the student to be created.
     * @param major of the student to be created.
     * @param credit that the student is taking.
     * @param area where the student comes from.
     */
    public TriState(String name, Major major, int credit, String area){
        super(name, major, credit);
        this.area = area;
    }

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

        if ((this.area).equals("NY")) this.setTuitionFee(this.getTuitionFee() - 4000);
        if ((this.area).equals("CT")) this.setTuitionFee(this.getTuitionFee() - 5000);
        this.setTuitionFee(this.getTuitionFee() - this.getFeePaid());
    }

    /**
     * Pass the area where the student is from out.
     * @return area where the student is from.
     */
    @Override
    public String getArea(){
        return this.area;
    }

}

