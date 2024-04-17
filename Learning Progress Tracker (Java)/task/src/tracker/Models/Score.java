package tracker.Models;

import java.util.List;

public class Score {
    private int id;
    private List<Integer> scores;

    public Score(int id, List<Integer> scores) {
        this.id = id;
        this.scores = scores;
    }

    public int getId() {
        return id;
    }


    public List<Integer> getScores() {
        return scores;
    }

}
