package tracker.Models;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseName;
    private List<Integer> ids;

    private int enrollmentNumber;
    private int activity;
    private int totalScore;


    public Course (String courseName) {
        this.courseName = courseName;
        this.ids = new ArrayList<>();
        this.enrollmentNumber = 0;
        this.activity = 0;
        this.totalScore = 0;
    }

    public int getEnrollmentNumber() {
        return enrollmentNumber;
    }

    public void setEnrollmentNumber(int enrollmentNumber) {
        this.enrollmentNumber = enrollmentNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void addIdAndEnrollment (int newId, int newScore){
        if(!ids.contains(newId)){
            ids.add(newId);
            increaseEnrollment();
        }
        increaseActivity();
        increaseTotalScore(newScore);

    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public double getAverageScore() {
        if(enrollmentNumber == 0){
            return 0.0;
        }
        return (double) totalScore/enrollmentNumber;
    }


    public void increaseEnrollment() {
        enrollmentNumber++;
    }

    public void increaseActivity(){
        activity++;
    }

    public void increaseTotalScore(int newScore){
        totalScore = totalScore + newScore ;
    }


}
