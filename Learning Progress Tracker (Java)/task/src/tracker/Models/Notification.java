package tracker.Models;

import java.util.List;

public class Notification {
    private String emailAddress;
    private String firstName;
    private String lastName;
    private List<String> subjects;

    public Notification(String emailAddress, String firstName, String lastName, List<String> subjects) {
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.subjects = subjects;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getSubjects() {
        return subjects;
    }

}
