package tracker.Models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Courses {

    private List<Course> courses = new ArrayList<>();


    public void add (Course course) {
        courses.add(course);
    }

    //     most students enrolled
    public List<String> mostPopular() {
        return courses.stream()
                .filter(course -> course.getEnrollmentNumber() == courses.stream()
                        .mapToInt(Course::getEnrollmentNumber)
                        .max()
                        .orElse(0))
                .map(Course::getCourseName)
                .collect(Collectors.toList());
    }

    //    least amount of students enrolled
    public List<String> leastPopular() {
        return courses.stream()
                .filter(course -> course.getEnrollmentNumber() == courses.stream()
                        .mapToInt(Course::getEnrollmentNumber)
                        .min()
                        .orElse(0))
                .map(Course::getCourseName)
                .collect(Collectors.toList());
    }

    //    highest number of submissions
    public List<String> highestActivity() {
        return courses.stream()
                .filter(course -> course.getEnrollmentNumber() == courses.stream()
                        .mapToInt(Course::getActivity)
                        .max()
                        .orElse(0))
                .map(Course::getCourseName)
                .collect(Collectors.toList());
    }

    //    least amount of submissions
    public List<String> lowestActivity() {
        return courses.stream()
                .filter(course -> course.getEnrollmentNumber() == courses.stream()
                        .mapToInt(Course::getActivity)
                        .min()
                        .orElse(0))
                .map(Course::getCourseName)
                .collect(Collectors.toList());
    }

    //    highest average score
    public List<String> easiestCourse() {
        return courses.stream()
                .max(Comparator.comparingDouble(Course::getAverageScore))
                .map(Course::getCourseName)
                .stream().collect(Collectors.toList());
    }

    //    lowest average score
    public List<String> hardestCourse() {
        return courses.stream()
                .min(Comparator.comparingDouble(Course::getAverageScore))
                .map(Course::getCourseName)
                .stream().toList();
    }

    public boolean anyEnrollments(){
        return courses.stream().anyMatch(course -> course.getEnrollmentNumber() > 0);
    }




}
