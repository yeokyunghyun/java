package src.test.java;

public class Score {
    private int strike;
    private int ball;

    public void plusStrike() {
        ++strike;
    }

    public void plusBall() {
        ++ball;
    }

    public int getBall() {
        return ball;
    }

    public int getStrike() {
        return strike;
    }
}
