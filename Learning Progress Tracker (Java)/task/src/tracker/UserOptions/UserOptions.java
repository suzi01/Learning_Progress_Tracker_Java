package tracker.UserOptions;

import tracker.Exceptions.IncorrectFormatException;
import tracker.Helpers.Helpers;
import tracker.InputScanner;
import tracker.Models.*;
import tracker.RegexChecker.RegexValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import static tracker.Constants.Constants.*;

public class UserOptions {
    private InputScanner inputScanner;
    private RegexValidator regexValidator;
    private Helpers helpers;
    private Students students;
    private Course java = new Course("Java");
    private Course spring = new Course("Spring");
    private Course databases = new Course("Databases");
    private Course dsa = new Course("DSA");
    private Courses courses;


    public UserOptions() {
        this.inputScanner = new InputScanner();
        this.regexValidator = new RegexValidator();
        this.students = new Students();
        this.helpers = new Helpers();
        this.courses = new Courses();
        courses.add(java);
        courses.add(dsa);
        courses.add(databases);
        courses.add(spring);
    }

    public void hint() {
        System.out.println("Enter 'exit' to exit the program");
    }

    public void errorInput() {
        System.out.println("Error: unknown command!");
    }

    public void noInput() {
        System.out.println("No input");
    }

    public void exit() {
        System.out.println("Bye");
        System.exit(0);
    }

    public int addStudents(int count) {
        System.out.println("Enter student credentials or 'back' to return:");
        String credentials = inputScanner.getUserInput();

        while (!credentials.equals("back")){
            if(credentials.split(" ").length <3){
                System.out.println("Incorrect credentials");
            }
            else {
                String [] words = credentials.split(" ");
                String firstName = words[0];
                String email = words[words.length-1];


                StringBuilder middlePart = new StringBuilder();
                for (int i = 1; i < words.length -1; i++){
                    middlePart.append(words[i]);
                    if (i < words.length - 2) {
                        middlePart.append(" ");
                    }
                }
                String lastName = middlePart.toString();
                String nameChecked = regexValidator.nameChecker(firstName,lastName);


                if(students.checkEmailUnique(email)){
                    System.out.println("This email is already taken");
                } else {
                    if(nameChecked.equals("passed") && regexValidator.emailChecker(email)){
                        Student student = new Student(firstName,lastName,email);
                        students.addStudents(student);
                        System.out.println("The student has been added.");
                        count++;
                    }
                    if(!nameChecked.equals("passed")){
                        System.out.println("Incorrect " + nameChecked + " name");

                    }
                    else if(!regexValidator.emailChecker(email)){
                        System.out.println("Incorrect email");
                    }
                }
            }
            credentials = inputScanner.getUserInput();
        }
        System.out.println("Total " + count + " students have been added.");
        return count;
    }

    public void addPoints() throws IncorrectFormatException {
        System.out.println("Enter an id and points or 'back' to return:");
        String userInput = inputScanner.getUserInput();
        while (!userInput.equals("back")){
            UserInput checkInput = helpers.validate(userInput);
            if(checkInput.getMessage() == UserInput.Message.PASS){
                Score score = helpers.addUserScores(checkInput.getId(), checkInput.getScores());
                sortPoints(checkInput.getId(), score);
                students.setScores(score);
            }
            else if (checkInput.getMessage() == UserInput.Message.ID){
                System.out.println("No student is found for id=" + checkInput.getId());
            }
            else {
                System.out.println("Incorrect points format." );
            }
            userInput = inputScanner.getUserInput();
        }
    }

    private void sortPoints(String id, Score score) {
        for(int i = 0; i < score.getScores().size(); i++){
            if(score.getScores().get(i) > 0){
                int addScore = score.getScores().get(i);
                switch (i){
                    case (0) -> java.addIdAndEnrollment(Integer.parseInt(id), addScore);
                    case (1) -> dsa.addIdAndEnrollment(Integer.parseInt(id), addScore);
                    case (2) -> databases.addIdAndEnrollment(Integer.parseInt(id), addScore);
                    case (3) -> spring.addIdAndEnrollment(Integer.parseInt(id), addScore);
                }
            }
        }
    }


    public void listStudents() {
        List<Integer> studentIds = students.getAllStudentIds();
        if (studentIds.isEmpty()){
            System.out.println("No students found");
        }else{
            System.out.println("Students:");
            for(int studentId: studentIds){
                System.out.println(studentId);
            }
        }
    }

    public void findUser() {
        System.out.println("Enter an id or 'back' to return:");
        String userId = inputScanner.getUserInput();
        while(!userId.equals("back")){
            int intUserId = Integer.parseInt(userId);
            if(students.userExists(intUserId)){
                System.out.println(students.getScores(intUserId));
            }
            else{
                System.out.println("No student is found for id=" + userId +".");
            }
            userId = inputScanner.getUserInput();
        }

    }

    public void statistics() {
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        if(!courses.anyEnrollments()){
            System.out.println("""
                    Most popular: n/a
                    Least popular: n/a
                    Highest activity: n/a
                    Lowest activity: n/a
                    Easiest course: n/a
                    Hardest course: n/a
                    """);
        }
        else {
            System.out.println("most popular: " + listToString(courses.mostPopular()));
            System.out.println("least popular: " + compareStats(courses.mostPopular(), courses.leastPopular()));
            System.out.println("highest activity: " + listToString(courses.highestActivity()));
            System.out.println("lowest activity: " + compareStats( courses.highestActivity(), courses.lowestActivity()));
            System.out.println("easiest course: " + listToString(courses.easiestCourse()));
            System.out.println("hardest course: " + compareStats(courses.easiestCourse(),courses.hardestCourse()));
        }


        String userInput = inputScanner.getUserInput();
        while(!userInput.equals("back")){
            switch (userInput.toLowerCase()){
                case("java") -> subjectStatistics("Java", java, 0, JAVA_POINTS);
                case("dsa") -> subjectStatistics("DSA", dsa, 1, DSA_POINTS);
                case("databases") -> subjectStatistics("Databases", databases, 2, DATABASES_POINTS);
                case("spring") -> subjectStatistics("Spring", spring, 3, SPRING_POINTS);
                default -> unknownStatics();
            }
            userInput = inputScanner.getUserInput();
        }
    }

    public void notifyStudent() {
        List<Notification> studentsToBeNotified = students.notificationFilter();
        if(studentsToBeNotified.isEmpty()){
            System.out.println("Total 0 students have bee notified");
        }
        else {
            for(Notification notification: studentsToBeNotified){
                sendNotification(notification);
            }
            System.out.printf("Total %d students have bee notified", studentsToBeNotified.size());
        }
    }

    private void sendNotification(Notification notification) {
        List<String> subjectsInNotification = notification.getSubjects();
        for (String subject: subjectsInNotification){
            System.out.printf("To: %s%n", notification.getEmailAddress());
            System.out.println("Re: Your Learning Progress");
            System.out.printf("Hello, %s %s! You have accomplished our %s course!%n", notification.getFirstName(), notification.getLastName(), subject);
        }
    }

    private String compareStats(List<String> firstList, List<String> secondList){
        if(firstList.equals(secondList)){
            return "n/a";
        }
        return listToString(secondList);
    }

    private String listToString(List<String> statSelection) {
        return String.join(", ", statSelection);

    }


    private void heading(String subject){
        System.out.println(subject);
        System.out.println("id\tpoints\tcompleted");
    }

    private void subjectStatistics(String subject, Course course, int position, int point_type){
        heading(subject);

        List<Map <Integer, Integer>> sortedSubject = statisticsCalc(course, position);
        if(sortedSubject != null ||!sortedSubject.isEmpty()) {
            sortedSubject.forEach(map -> map.forEach((id, score) ->
                    System.out.println(id + "\t" + score + "\t" +
                            new BigDecimal((double)score / point_type).setScale(3, RoundingMode.HALF_UP).scaleByPowerOfTen(2) + "%")));
        }
    }

    private void unknownStatics() {
        System.out.println("Unknown course");
    }

    private List<Map <Integer, Integer>> statisticsCalc(Course courseStat, int position){
        List<Student> selectedStudents = students.filterStudents(courseStat.getIds());
        if(!selectedStudents.isEmpty()) {
            return sortedScores(position, selectedStudents);
        }
        return new ArrayList<>();
    }

    private List<Map <Integer, Integer>> sortedScores(int position, List<Student> unsortedStudents){
        return unsortedStudents.stream()
                .map(stu -> Collections.singletonMap(stu.getId(), stu.getScores().get(position)))
                .sorted(Comparator.comparingInt((Map<Integer, Integer> map) -> map.values().iterator().next()).reversed())
                .collect(Collectors.toList());
    }


}
