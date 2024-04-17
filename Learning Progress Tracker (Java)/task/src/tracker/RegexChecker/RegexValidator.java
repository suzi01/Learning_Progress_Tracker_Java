package tracker.RegexChecker;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexValidator {

    public boolean emailChecker(String email) {
        String regex = "^[a-zA-Z0-9.]+@[a-zA-Z0-9.]+\\.[a-zA-Z0-9.]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public String nameChecker(String firstName, String lastName) {
        if(firstName.length() < 2){
            return "first";
        }

        if(lastName.length() < 2){
            return "last";
        }

        boolean firstMatches = validateNamesRegex(firstName, "first");
        boolean secondMatches = validateNamesRegex(lastName, "second");


        if(secondMatches && firstMatches){
            return "passed";
        }
        if(!secondMatches){
            return "last";
        }
        else {
            return "first";
        }
    }

    private boolean validateNamesRegex(String name, String position){
        Matcher matcher = null;
        String firstNameRegex = "^[a-zA-Z]+(?:[' -][a-zA-Z]+)*$";
        String lastNameRegex = "^[a-zA-Z](?!.*[-.']{2})[a-zA-Z. '-]*[a-zA-Z]$";

        if(position.equals("first")){
            Pattern firstNamePattern = Pattern.compile(firstNameRegex);
            matcher = firstNamePattern.matcher(name);
        } else {
            Pattern lastNamePattern = Pattern.compile(lastNameRegex);
            matcher = lastNamePattern.matcher(name);
        }


        return matcher.find();

    }

}
