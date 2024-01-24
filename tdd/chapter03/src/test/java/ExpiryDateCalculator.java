package src.test.java;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonth = payData.getPayAmount() / 10000;
        if(addedMonth >= 10) addedMonth = addedMonth + (addedMonth / 10) * 2;

        if (payData.getFirstBillingDate() != null) return expiryDateUsingFirstBillingDate(payData, addedMonth);
        return payData.getBillingDate().plusMonths(addedMonth);
    }


    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonth) {
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonth);
        if (!isSameDayOfMonth(payData.getFirstBillingDate(), candidateExp)) {
            /*1월 31일에 1달을 해서 2월 28이 되고 그 뒤에 2월 28일에 10000원을 하면 3월 31일이 되는 구조여야되는데
             * 예외상황에서는 4월 31일로 되는 경우이기 때문에 이 논리구조를 사용할 수 없음*/
            final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
            final int dayLenOfCandiMon = lastDayOfMonth(candidateExp);
            if (dayOfFirstBilling > dayLenOfCandiMon) {
                return candidateExp.withDayOfMonth(dayLenOfCandiMon); /*이건 첫 결제 1/31 -> 2/28에서 결제를 2달을 해서 4월 31로 가버려서 4월 30일로 조정돼서 나오는 상황*/
            } else {
                return candidateExp.withDayOfMonth(dayOfFirstBilling); /*이건 첫 결제 1/31 -> 2/28 에서 결제를 해서 3/31로 되는 상황*/
            }
        } else {
            return candidateExp;
        }
    }

    private boolean isSameDayOfMonth(LocalDate date1, LocalDate date2) {
        return date1.getDayOfMonth() == YearMonth.from(date2).lengthOfMonth();
    }

    private int lastDayOfMonth(LocalDate candidateExp) {
        return YearMonth.from(candidateExp).lengthOfMonth();
    }
}

//만약 2달이라고 했을때 1/31 -> 2/28 여기서 2달을 추가를 하면 4/31이 나와야되는데 4월 31일은 존재하지않음.