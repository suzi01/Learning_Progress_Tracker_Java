package tracker.Models;

import tracker.Constants.Constants;
import tracker.Exceptions.IncorrectFormatException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Students {


    private List<Student> students;

    public Students(){
        this.students = new ArrayList<>();
    }


    public void addStudents(Student student){
        students.add(student);
    }

    public List<Integer> getAllStudentIds(){
        return students.stream().map(Student::getId).collect(Collectors.toList());
    }

    public boolean checkEmailUnique(String email){
        return students.stream()
                .anyMatch(student -> student.getEmailAddress().equals(email));
    }

    public void setScores(Score score) throws IncorrectFormatException {
        if( score.getId() != -1 && score.getScores().size() == 4){
            if(userExists(score.getId())){
                for(Student student:students){
                    if(student.getId() == score.getId()){
                        if(student.getScores().isEmpty()){
                            student.setScores(score.getScores());
                        }else{
                            List<Integer> result = new ArrayList<>();
                            for(int i =0; i< score.getScores().size(); i++){
                                result.add(student.getScores().get(i) + score.getScores().get(i));
                            }
                            student.setScores(result);
                            }
                        System.out.println("Points updated");
                        break;
                    }
                }
            }
            else {
                System.out.println("No student is found for id=" + score.getId() +".");
            }
        } else {
            System.out.println("Incorrect points format.");
        }

    }

    public String getScores(int userId){
        Student foundStudent = students.stream()
                .filter(student -> student.getId() == userId)
                .findFirst()
                .orElse(null);

        List<Integer> user_scores = foundStudent.getScores();
            return String.format(userId + " points: Java=%d; DSA=%d; Databases=%d; Spring=%d",
                    user_scores.get(0),user_scores.get(1), user_scores.get(2) ,user_scores.get(3));


    }

    public boolean userExists (int userId){
        return students.stream()
                .anyMatch(student -> student.getId() == userId);
    }

    public List<Student> filterStudents (List<Integer> otherIds){
        return students.stream()
                .filter(student -> otherIds.contains(student.getId()))
                .collect(Collectors.toList());

    }
    
    public List<Notification> notificationFilter(){
        List<Notification> notificationList = new ArrayList<>();
        for( Student student : students){
            if(!student.isHasBeenNotified()){
                List<String> subjects = getStrings(student);
                if(!subjects.isEmpty()){
                    notificationList.add(new Notification(
                            student.getEmailAddress(),
                            student.getFirstName(),
                            student.getLastName(),
                            subjects));
                    student.setHasBeenNotified(true);
                }
            }
        }
        return notificationList;
    }

    private static List<String> getStrings(Student student) {
        List<String> subjects = new ArrayList<>();
        List<Integer> studentScore = student.getScores();
        if(studentScore.get(0) >= Constants.JAVA_POINTS ) {
            subjects.add("Java");
        }
        if(studentScore.get(1) >= Constants.DSA_POINTS ) {
            subjects.add("DSA");
        }
        if(studentScore.get(2) >= Constants.DATABASES_POINTS ) {
            subjects.add("Databases");
        }
        if(studentScore.get(3) >= Constants.SPRING_POINTS ) {
            subjects.add("Spring");
        }
        return subjects;
    }
}
