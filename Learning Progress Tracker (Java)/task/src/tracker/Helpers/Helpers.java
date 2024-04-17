package tracker.Helpers;

import tracker.Exceptions.IncorrectFormatException;
import tracker.Models.UserInput;
import tracker.Models.Score;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Helpers {

    public UserInput validate (String userInput){
        List<String> inputScores = Arrays.asList(userInput.split(" "));
        String strUserId = inputScores.get(0);
        if(inputScores.size() != 5 ){
            return new UserInput(false,strUserId, inputScores, UserInput.Message.SIZE);
        }

        inputScores = new ArrayList<>(inputScores.subList(1, inputScores.size()));
        if(isInteger(strUserId)){
            return new UserInput(true,strUserId, inputScores, UserInput.Message.PASS);
        }

       return new UserInput(false,strUserId, inputScores, UserInput.Message.ID);
    }

    public Score addUserScores (String strUserId, List<String> inputScores) throws IncorrectFormatException {
        int userId = -1;
        List<Integer> userScores = null;
        userId = Integer.parseInt(strUserId);
        userScores = convertStringsToInts(inputScores);
        return new Score(userId, userScores);

    }

    public boolean isInteger(String id){
        try{
            Integer.parseInt(id);
            return true;
        }catch (NumberFormatException ex){
            return false;
        }
    }


    private List<Integer> convertStringsToInts(List<String> grades) throws IncorrectFormatException {
        List<Integer> convertedScores = new ArrayList<>();
        for(String strScore: grades){
            try{
                int intScore = Integer.parseInt(strScore);
                if(intScore >= 0){
                    convertedScores.add(intScore);
                }
                else{
                    break;
                }
            } catch (NumberFormatException ignored) {
            }
        }
        return convertedScores;
    }
}
