package test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {
    private PasswordStrengthMeter meter = new PasswordStrengthMeter();
    private void assertStrength(String password, PasswordStrength strength) {
        PasswordStrength result = meter.meter(password);
        assertEquals(strength, result);
    }

    @Test
    void meetsAllCriteriaThenStrong() {
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
    }

    @Test
    void meetsTwoCriteriaWithoutLength() {
        assertStrength("ab1!AB", PasswordStrength.NORMAL);
    }

    @Test
    void meetsTwoCriteriaWithoutNumber() {
        assertStrength("abcABC!@#", PasswordStrength.NORMAL);

    }
}

//PasswordStrengthMeter클래스를 만들어야되고 그 중에 meter라는 값에 인자로 비밀번호가 넘어가면 그것에 따라 강함,중간,약함의 정도를 정해주는 메서드가 필요함