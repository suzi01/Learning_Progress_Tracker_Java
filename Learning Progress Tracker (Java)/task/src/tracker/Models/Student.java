package tracker.Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Getter
//@Setter
public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private List<Integer> scores = new ArrayList<>();
    private boolean hasBeenNotified;

    private static int nextId = 0;

    public Student(String firstName, String lastName, String emailAddress) {
        this.id = generateNextId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.scores = List.of(0,0,0,0);
        this.hasBeenNotified = false;

    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public String getEmailAddress() {
        return emailAddress;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }

    public boolean isHasBeenNotified() {
        return hasBeenNotified;
    }

    public void setHasBeenNotified(boolean hasBeenNotified) {
        this.hasBeenNotified = hasBeenNotified;
    }

    private static synchronized int generateNextId(){
        return nextId++;
    }



}
