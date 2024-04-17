package tracker;

import tracker.Exceptions.EmailException;
import tracker.Exceptions.IncorrectFormatException;
import tracker.UserOptions.UserOptions;


public class Main {
    static String userInput = "";
    static int count = 0;


    public static void main(String[] args) throws IncorrectFormatException {

        UserOptions userOptions = new UserOptions();
        InputScanner inputScanner = new InputScanner();

        System.out.println("Learning Progress Tracker");
        userInput = inputScanner.getUserInput();
        while (!userInput.equals("exit")){
            switch (userInput){
                case ("back") -> userOptions.hint();
                case ("") -> userOptions.noInput();
                case("add students") -> count = userOptions.addStudents(count);
                case("list") -> userOptions.listStudents();
                case("add points") -> userOptions.addPoints();
                case("find")-> userOptions.findUser();
                case("statistics") -> userOptions.statistics();
                case("notify") -> userOptions.notifyStudent();
                default -> userOptions.errorInput();
            }
            userInput = inputScanner.getUserInput();
        }
        userOptions.exit();
    }

}
