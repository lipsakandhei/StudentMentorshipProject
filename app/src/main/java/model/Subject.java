package model;

/**
 * Created by HP on 31-10-2017.
 */

public class Subject {
    String subject_name;
    String subject_code;
    String time_slot;
    String tutorial_slot;
    int credits;

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }


    public String getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }


    public String getTime_slot() {
        return time_slot;
    }

    public void setTime_slot(String time_slot) {
        this.time_slot = time_slot;
    }


    public String getTutorial_slot() {
        return tutorial_slot;
    }

    public void setTutorial_slot(String tutorial_slot) {
        this.tutorial_slot = tutorial_slot;
    }


    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
