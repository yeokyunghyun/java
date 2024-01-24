package src.test.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BaseballGameTest {
    @Test
    public void makeSameNumber() {
        assertThrows(IllegalStateException.class, () -> new BaseballGame("122"));
    }
    @Test
    public void allStrikeTest() {
        // given
        BaseballGame baseballGame = new BaseballGame("123");
        // when
        Score score = baseballGame.guess("123");
        // then
        assertEquals(3, score.getStrike());
    }

    @Test
    public void oneStrikeTwoBallTest() {
        //given
        BaseballGame baseballGame = new BaseballGame("123");
        //when
        Score score = baseballGame.guess("132");
        //then
        assertEquals(1, score.getStrike());
        assertEquals(2, score.getBall());
    }

    @Test
    public void noStrikeNoBallTest() {
        //given
        BaseballGame baseballGame = new BaseballGame("123");
        //when
        Score score = baseballGame.guess("456");
        //then
        assertEquals(0, score.getStrike());
        assertEquals(0, score.getBall());
    }
}
