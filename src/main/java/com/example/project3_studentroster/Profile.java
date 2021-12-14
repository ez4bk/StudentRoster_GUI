package com.example.project3_studentroster;

/**
 * An object class that represents a profile of a student.
 * Contains a student's name and major(in Enum class).
 * @author Yuchen Wei, Denghao Sun
 */
public class Profile {
    private String name;
    private Major major; //5 majors and 2-charater each: CS, IT, BA, EE, ME

    /**
     * Constructor that creates a profile.
     * @param name of the student to be created.
     * @param major of the student to be created.
     */
    public Profile(String name, Major major){
        this.name = name;
        this.major = major;
    }

    /**
     * Compare if two profiles are identical by comparing their names and majors.
     * @param obj to be compared with
     * @return true if two profiles are the same, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        Profile compareObj = (Profile) obj;
        if (compareObj.name.equals(this.name) && compareObj.major.equals(this.major)) {
            return true;
        }
        return false;
    }

    /**
     * Generate the a student's name and major info to a string.
     * @return info of a student that contains one's name and major.
     */
    @Override
    public String toString() {
        String info = this.name + ":" + this.major;
        return info;
    }

    /**
     * Pass the name of a student in the profile out.
     * @return the name of a student in the profile.
     */
    public String getName() {
        return this.name;
    }

}

