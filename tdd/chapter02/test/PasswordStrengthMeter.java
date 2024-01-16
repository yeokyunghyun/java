package test;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String password) {
        if(password.length() < 8) {
            return PasswordStrength.NORMAL;
        }

        boolean isNumberContain = isNumberContain(password);

        if(!isNumberContain) {
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
    }

    private boolean isNumberContain(String password) {
        for(char c : password.toCharArray()) {
            if (c >= '0' && c <= '9') {
                return true;
            }
        }
        return false;
    }
}
