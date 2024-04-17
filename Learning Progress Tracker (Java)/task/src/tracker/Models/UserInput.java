package tracker.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


public class UserInput {
    private boolean isValid;
    private String id;
    private List<String> scores;
    private Message message;

    public UserInput(boolean isValid, String id, List<String> scores, Message message) {
        this.isValid = isValid;
        this.id = id;
        this.scores = scores;
        this.message = message;
    }


    public String getId() {
        return id;
    }

    public List<String> getScores() {
        return scores;
    }

    public Message getMessage() {
        return message;
    }

    public enum Message {
        ID, SIZE, PASS
    }

}
