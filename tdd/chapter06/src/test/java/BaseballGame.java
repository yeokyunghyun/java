package src.test.java;

public class BaseballGame {

    private char[] answer;
    public BaseballGame(String answer) {
        this.answer = answer.toCharArray();
        if(this.answer[0] == this.answer[1] || this.answer[1] == this.answer[2] || this.answer[0] == this.answer[2]) {
            throw new IllegalStateException("겹치는 숫자가 있습니다.");
        }
    }

    public Score guess(String guessNumber) {
        char[] guessNumberArr = guessNumber.toCharArray();
        Score score = new Score();

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                if(answer[i] == guessNumberArr[j]) {
                    if(i == j) {
                        score.plusStrike();
                    } else{
                        score.plusBall();
                    }
                }
            }
        }
        return score;
    }
}
