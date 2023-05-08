package com.example.champomatch;

public class Tuple {
    private double score;
    private Single single;

    public Tuple(double score, Single single) {
        this.score = score;
        this.single = single;
    }

    public double getScore() {
        return score;
    }

    public Single getSingle() {
        return single;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setSingle(Single single) {
        this.single = single;
    }

}
