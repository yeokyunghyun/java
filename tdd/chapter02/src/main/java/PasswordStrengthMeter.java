package src.main.java;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String password) {

        if(password == null || password.isEmpty()) return PasswordStrength.INVALID;
        int metCounts = getMetCounts(password);

        if(metCounts <= 1) return PasswordStrength.WEAK;
        else if(metCounts == 2) return PasswordStrength.NORMAL;
        return PasswordStrength.STRONG;
    }

    private int getMetCounts(String password) {
        int metCounts = 0;
        if(password.length() >= 8) ++metCounts;
        if(isNumberContain(password)) ++metCounts;
        if(isUpperContain(password)) ++metCounts;

        return metCounts;
    }

    private boolean isNumberContain(String password) {
        for(char c : password.toCharArray()) {
            if (c >= '0' && c <= '9') {
                return true;
            }
        }
        return false;
    }

    private boolean isUpperContain(String password) {
        for(char c : password.toCharArray()) {
            if(Character.isUpperCase(c)) return true;
        }
        return false;
    }
}
