package com.example.project3_studentroster;

import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * The user interface controller class that defines all actions of clicking and data manipulation.
 * Uses an instance of Roster class to process the tuitions. Handles wrong input, errors, and
 * exceptions.
 *
 * @author Yuchen Wei, Denghao Sun
 */
public class TuitionManagerController {

    Roster roster = new Roster();

    @FXML private TextField profileStudentNameTextField;
    @FXML private TextField tuitionDueTextField;
    @FXML private TextField creditHoursTextField;
    @FXML private RadioButton internationalRadioButton;
    @FXML private RadioButton plainPrint;
    @FXML private RadioButton namePrint;
    @FXML private RadioButton dateOrderPrint;
    @FXML private CheckBox studyAbroadCheckbox;
    @FXML private ToggleGroup statusGroup;
    @FXML private ToggleGroup profileMajorGroup;
    @FXML private ToggleGroup printType;
    @FXML private DatePicker datePicker;
    @FXML private TextField paymentStudentNameTextBox;
    @FXML private ToggleGroup paymentMajor;
    @FXML private TextField paymentAmount;
    @FXML private TextField financialAid;
    @FXML private TextArea outputText;

    /**
     * Helper method that checks if a Resident student's credit hours validity.
     *
     * @param credit number to be checked.
     * @return true if the credit hours is valid, false otherwise.
     */
    private boolean checkFullTimeCredit(int credit) {
        if (credit < 0) {
            outputText.appendText("Credit hours cannot be negative.\n");
            return false;
        } else if (credit >= 0 && credit < 3) {
            outputText.appendText("Minimum credit hours is 3.\n");
            return false;
        } else if (credit > 24) {
            outputText.appendText("Credit hours exceed the maximum 24.\n");
            return false;
        }
        return true;
    }

    /**
     * Helper method that checks if an International student's credit hours
     * validity.
     *
     * @param credit number to be checked.
     * @return true if the credit hours is valid, false otherwise.
     */
    private boolean checkInternationalCredit(int credit) {
        if (credit < 0) {
            outputText.appendText("Credit hours cannot be negative.\n");
            return false;
        } else if (credit >= 0 && credit < 3) {
            outputText.appendText("Minimum credit hours is 3.\n");
            return false;
        } else if (credit >= 3 && credit < Student.FULLTIME_CREDIT) {
            outputText.appendText("International students must enroll at least 12 credits.\n");
            return false;
        } else if (credit > 24) {
            outputText.appendText("Credit hours exceed the maximum 24.\n");
            return false;
        }
        return true;
    }


    /**
     * A helper method to create a Resident Student and add to the roster.
     * @param name contains the information of the student.
     * @param major contains the major of the student.
     */
    public void setStudyAbroadStatus(String name, String major) {
        Student student = new International(name, Major.valueOf(major), 0);
        for (int i = 0; i < roster.getSize(); i++) {
            if (roster.getRoster()[i].getProfile().equals(student.getProfile())
                    && roster.getRoster()[i] instanceof International) {
                roster.getRoster()[i].setStudyAbroad(true);
                outputText.appendText("Tuition updated.\n");
                tuitionDueTextField.setText(""+roster.getRoster()[i].getTuitionFee());
                break;
            }
            if (i == roster.getSize() - 1) {
                outputText.appendText("Couldn't find the international student.\n");
            }
        }
    }

    /**
     * A method to create a single tuition due of the student.
     * @param student the student who is about to get processed.
     */
    public void singleCalculate(Student student){
        if (student instanceof Resident) {
            student.tuitionDue();
        } else if (student instanceof NonResident) {
            student.tuitionDue();
        } else if (student instanceof TriState) {
            student.tuitionDue();
        } else if (student instanceof International) {
            student.tuitionDue();
        }
    }

    /**
     * A helper method to create a Resident Student and add to the roster.
     * @param name contains the information of the student.
     * @param major contains the major of the student.
     * @param creditHours contains the credits of the student.
     */
    private void addResident(String name, String major, int creditHours){
        Student student = new Resident(name, Major.valueOf(major), creditHours);
        if (!roster.add(student)) {
            outputText.appendText("Student is already in the roster.\n");
        } else{
            outputText.appendText("Student added.\n");
        }
    }

    /**
     * A helper method to create a NonResident Student and add to the roster.
     * @param name contains the information of the student.
     * @param major contains the major of the student.
     * @param creditHours contains the credits of the student.
     */
    private void addNonResident(String name, String major, int creditHours){
        Student student = new NonResident(name, Major.valueOf(major), creditHours);
        if (!roster.add(student)) {
            outputText.appendText("Student is already in the roster.\n");
        } else {
            outputText.appendText("Student added.\n");
        }
    }

    /**
     * A helper method to create a Tristate Student and add to the roster.
     * @param name contains the information of the student.
     * @param major contains the major of the student.
     * @param creditHours contains the credits of the student.
     * @param triArea contains the state area of the student.
     */
    private void addTristate(String name, String major, int creditHours, String triArea){
        Student student = new TriState(name, Major.valueOf(major), creditHours, triArea);
        if (!roster.add(student)) {
            outputText.appendText("Student is already in the roster.\n");
        } else {
            outputText.appendText("Student added.\n");
        }
    }

    /**
     * A helper method to create an Internation Student and add to the roster.
     * @param name contains the information of the student.
     * @param major contains the major of the student.
     * @param creditHours contains the credits of the student.
     * @param studyAbroad contains the status of studyAbroad of the student.
     */
    private void addInternation(String name, String major, int creditHours, boolean studyAbroad){
        Student student = new International(name, Major.valueOf(major), creditHours);
        if (studyAbroad){
            student.setStudyAbroad(true);
        }
        if (!roster.add(student)) {
            outputText.appendText("Student is already in the roster.\n");
        } else {
            outputText.appendText("Student added.\n");
        }

    }

    /**
     * A helper method to remove a Student from the roster.
     * @param name contains the information of the student.
     * @param major contains the major of the student.
     */
    private void removeStudent(String name, String major){
        Student student = new Student(name, Major.valueOf(major), 0);
        if (roster.remove(student)) {
            outputText.appendText("Student removed from the roster.\n");
        } else
            outputText.appendText("Student is not in the roster.\n");
    }

    /**
     * Set the Study Abroad status of the student.
     */
    @FXML
    protected void onSetStudyAbroad(){
        boolean flag = true;
        String profileMajorText = "";
        RadioButton selectedProfileMajor = (RadioButton) profileMajorGroup.getSelectedToggle();
        try{
            profileMajorText = selectedProfileMajor.getText();
        }catch (NullPointerException e){
            outputText.appendText("Select your Major!\n");
            flag = false;
        }

        String studentName = profileStudentNameTextField.getText();
        if(studentName.equals("")){
            outputText.appendText("Student name is empty!\n");
            flag = false;
        }

        RadioButton selectedStatus = (RadioButton) statusGroup.getSelectedToggle();
        String statusText = "";
        try{
            statusText = selectedStatus.getText();
        }catch (NullPointerException e){
            outputText.appendText("Select your status!\n");
            flag = false;
        }

        if(statusText.equals("International") && flag && studyAbroadCheckbox.isSelected()){
            setStudyAbroadStatus(studentName, profileMajorText);
        }
    }

    /**
     * Process the Add Student request and handle the exceptions.
     */
    @FXML
    protected void onAddStudentButtonClick() {
        boolean flag = true;
        String profileMajorText = "";
        RadioButton selectedProfileMajor = (RadioButton) profileMajorGroup.getSelectedToggle();
        try{
            profileMajorText = selectedProfileMajor.getText();
        }catch (NullPointerException e){
            outputText.appendText("Select your Major!\n");
            flag = false;
        }

        String studentName = profileStudentNameTextField.getText();
        if(studentName.equals("")){
            outputText.appendText("Student name is empty!\n");
            flag = false;
        }

        RadioButton selectedStatus = (RadioButton) statusGroup.getSelectedToggle();
        String statusText = "";
        try{
            statusText = selectedStatus.getText();
        }catch (NullPointerException e){
            outputText.appendText("Select your status!\n");
            flag = false;
        }
        if (selectedStatus!=internationalRadioButton && studyAbroadCheckbox.isSelected()){
            outputText.appendText("Only International students can study abroad! Please check your information.\n");
            flag = false;
        }

        int creditHour = 0;
        try {
            creditHour = Integer.parseInt(creditHoursTextField.getText());
        }catch (NumberFormatException e){
            outputText.appendText("Invalid credit hours.\n");
            flag = false;
        }catch (Exception e){
            outputText.appendText("Empty credit hours.\n");
            flag = false;
        }

        if (!checkFullTimeCredit(creditHour)){
            flag = false;
        }


        if (flag){
            if (statusText.equals("Resident")){
                addResident(studentName, profileMajorText, creditHour);
            }else if(statusText.equals("Non-Resident")){
                addNonResident(studentName, profileMajorText, creditHour);
            }else if(statusText.equals("New Jersey")){
                addTristate(studentName, profileMajorText, creditHour, "NJ");
            }else if(statusText.equals("New York")){
                addTristate(studentName, profileMajorText, creditHour, "NY");
            }else if(statusText.equals("Connecticut")){
                addTristate(studentName, profileMajorText, creditHour, "CT");
            }else if(statusText.equals("International")){
                if (checkInternationalCredit(creditHour)){
                    if(studyAbroadCheckbox.isSelected()){
                        addInternation(studentName, profileMajorText, creditHour, true);
                    }else{
                        addInternation(studentName, profileMajorText, creditHour, false);
                    }
                }
            }
        }


    }

    /**
     * Process the Remove Student request and handle the exceptions.
     */
    @FXML
    protected void onRemoveStudentButtonClick() {
        boolean flag = true;
        String profileMajorText = "";
        RadioButton selectedProfileMajor = (RadioButton) profileMajorGroup.getSelectedToggle();
        try{
            profileMajorText = selectedProfileMajor.getText();
        }catch (NullPointerException e){
            outputText.appendText("Select your Major!\n");
            flag = false;
        }

        String studentName = profileStudentNameTextField.getText();
        if(studentName.equals("")){
            outputText.appendText("Student name is empty!\n");
            flag = false;
        }

        if (flag){
            removeStudent(studentName, profileMajorText);
        }
    }

    /**
     * Process the Tuition Due request and handle the exceptions.
     */
    @FXML
    protected void onTuitionDueButtonClick() {
        boolean flag = true;
        String profileMajorText = "";
        RadioButton selectedProfileMajor = (RadioButton) profileMajorGroup.getSelectedToggle();
        try{
            profileMajorText = selectedProfileMajor.getText();
        }catch (NullPointerException e){
            outputText.appendText("Select your Major!\n");
            flag = false;
        }

        String studentName = profileStudentNameTextField.getText();
        if(studentName.equals("")){
            outputText.appendText("Student name is empty!\n");
            flag = false;
        }

        if (flag){
            Profile profile = new Profile(studentName, Major.valueOf(profileMajorText));
            for (int i = 0; i < roster.getSize(); i++){
                if (roster.getRoster()[i] == null) break;
                if (roster.getRoster()[i].getProfile().equals(profile)) {
                    singleCalculate(roster.getRoster()[i]);
                    tuitionDueTextField.setText(""+roster.getRoster()[i].getTuitionFee());
                }
            }
        }
    }

    /**
     * Check if the student is allowed to study abroad.
     */
    @FXML
    protected void onStudyAbroadChecked(){
        if (studyAbroadCheckbox.isSelected()){
            if(!internationalRadioButton.isSelected()){
                outputText.appendText("Only International students can study abroad!");
            }
        }

    }

    /**
     * Print the roster.
     */
    private void print() {
        Student[] printRoster = roster.getRoster();
        if (printRoster == null) {
            outputText.appendText("Student roster is empty!\n");
        }else {
            outputText.appendText("* list of students in the roster **\n");

            for (int i = 0; i < roster.getSize(); i++) {
                outputText.appendText(printRoster[i].toString()+"\n");
            }
            outputText.appendText(("* end of roster **\n"));
        }
    }

    /**
     * Print the roster sorted by student names.
     */
    private void printInNameOrder() {
        if (roster.getRoster() == null) {
            outputText.appendText(("Student roster is empty!\n"));
        }else {
            roster.sortByName();
            outputText.appendText("* list of students ordered by name **\n");
            for (int i = 0; i < roster.getSize(); i++) {
                outputText.appendText(roster.getRoster()[i].toString()+"\n");
            }
            outputText.appendText(("* end of roster **\n"));
        }
    }

    /**
     * print only the students who have made payments, ordered by the payment date.
     */
    private void printInPaymentDateOrder() {
        if (roster.getRoster() == null) {
            outputText.appendText(("Student roster is empty!\n"));
        }else {
            Student[] printRoster = roster.getRoster();

            outputText.appendText("* list of students made payments ordered by payment date **\n");
            for (int i = 0; i < roster.getSize(); i++) {
                if (roster.getRoster()[i].getFeePaidDate() == null) {
                    continue;
                }
                outputText.appendText(printRoster[i].toString()+"\n");
            }
            outputText.appendText(("* end of roster **\n"));
        }
    }

    /**
     * Print the roster by type, which is determined by which radion button the user chooses
     * when Print button is clicked.
     */
    @FXML
    protected void onPrintButtonClicked(){
        RadioButton selectedType = null;
        try{
            selectedType = (RadioButton) printType.getSelectedToggle();
        }catch (NullPointerException e){
            outputText.appendText("Select your print type!\n");
        }
        if(selectedType == plainPrint){
            print();
        }else if(selectedType == namePrint){
            printInNameOrder();
        }else if(selectedType == dateOrderPrint){
            printInPaymentDateOrder();
        }
    }

    /**
     * Calculate tuition fee for all student at once
     * when Calculate for All Button is clicked.
     */
    @FXML
    protected void onCalculateForAllClicked(){
        for (int i=0; i<roster.getSize(); i++){
            singleCalculate(roster.getRoster()[i]);
        }
        outputText.appendText("Calculation Complete!\n");
    }

    /**
     * Pay tuition for a single student when Pay button is clicked.
     */
    @FXML
    protected void onPayButtonClicked(){
        boolean flag = true;
        String paymentMajorText = "";
        RadioButton selectedProfileMajor = (RadioButton) paymentMajor.getSelectedToggle();
        try{
            paymentMajorText = selectedProfileMajor.getText();
        }catch (NullPointerException e){
            outputText.appendText("Select your Major!\n");
            flag = false;
        }

        String studentName = paymentStudentNameTextBox.getText();
        if(studentName.equals("")){
            outputText.appendText("Student name is empty!\n");
            flag = false;
        }

        Date paymentDate = null;
        try{
            paymentDate = new Date(datePicker.getValue()+"");
        }catch (NullPointerException e){
            outputText.appendText("Please choose payment date.\n");
            flag = false;
        }

        if(!paymentDate.isValid()){
            outputText.appendText("Invalid Date.\n");
            flag = false;
        }

        String paymentString = paymentAmount.getText();
        double payment = 0;
        try {
            payment = Double.parseDouble(paymentString);
        }catch (NumberFormatException e){
            outputText.appendText("Invalid payment amount.\n");
            flag = false;
        }catch (Exception e){
            outputText.appendText("Empty payment amount.\n");
            flag = false;
        }

        if (flag){
            Student student = new Student(studentName, Major.valueOf(paymentMajorText), 0);
            for (int i = 0; i < roster.getSize(); i++) {
                if (roster.getRoster()[i].getProfile().equals(student.getProfile())) {

                    if(roster.getRoster()[i].getTuitionFee() < payment){
                        outputText.appendText("Payment amount is larger than total tuition due!\n");

                    }else if(payment <= 0){
                        outputText.appendText("Payment amount is less than zero!\n");
                    }else {
                        roster.getRoster()[i].payment(payment, paymentDate);
                        outputText.appendText("Payment applied.\n");
                    }
                }
            }
        }
        else {
            outputText.appendText("Student is not in the roster!\n");
        }
    }

    /**
     * Set the financial aid amount of a student.
     */
    @FXML
    public void onSetButtonClicked() {
        boolean flag = true;
        String paymentMajorText = "";
        RadioButton selectedProfileMajor = (RadioButton) profileMajorGroup.getSelectedToggle();
        try{
            paymentMajorText = selectedProfileMajor.getText();
        }catch (NullPointerException e){
            outputText.appendText("Select your Major!\n");
            flag = false;
        }

        String studentName = paymentStudentNameTextBox.getText();
        if(studentName.equals("")){
            outputText.appendText("Student name is empty!\n");
            flag = false;
        }

        String financialAidText = financialAid.getText();
        double fAid = 0;
        try {
            fAid = Double.parseDouble(financialAidText);
        }catch (NumberFormatException e){
            outputText.appendText("Invalid financial aid amount.\n");
            flag = false;
        }catch (Exception e){
            outputText.appendText("Empty financial aid amount.\n");
            flag = false;
        }

        Student student = new Resident(studentName, Major.valueOf(paymentMajorText), 0);
        for (int i = 0; i < roster.getSize(); i++) {
            if (roster.getRoster()[i].getProfile().equals(student.getProfile())) {
                if (!(roster.getRoster()[i] instanceof Resident)) {
                    outputText.appendText("Not a resident student.\n");
                    break;
                } else if (roster.getRoster()[i].getCredit() < Student.FULLTIME_CREDIT) {
                    outputText.appendText("Parttime student doesn't qualify for the award.\n");
                    break;
                } else if (((Resident) roster.getRoster()[i]).getAidAmount() > 0) {
                    outputText.appendText("Awarded once already.\n");
                    break;
                } else if (fAid < 0 || fAid > 10000) {
                    outputText.appendText("Invalid amount.\n");
                    break;
                } else {
                    roster.getRoster()[i].setAidAmount(fAid);
                    outputText.appendText("Tuition updated.\n");
                    break;
                }
            }
        }
    }
}