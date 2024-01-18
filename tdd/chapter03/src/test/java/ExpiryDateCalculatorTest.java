package src.test.java;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {

    private ExpiryDateCalculator cal = new ExpiryDateCalculator();
    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        assertExpiryDate(PayData.builder()
                            .billingDate(LocalDate.of(2019, 3, 1))
                            .payAmount(10000)
                            .build(),
                LocalDate.of(2019, 4, 1));
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(PayData.builder()
                            .billingDate(LocalDate.of(2019, 1, 31))
                            .payAmount(10000)
                            .build(),
                LocalDate.of(2019, 2, 28)
        );
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부() {
        assertExpiryDate(PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(10000)
                .build()
                ,LocalDate.of(2019, 3, 31)
        );
    }

    @Test
    void 이만원_이상_납부하면_비례해서_만료일_계산() {
        assertExpiryDate(PayData.builder()
                .billingDate(LocalDate.of(2019, 3, 1))
                .payAmount(20000)
                .build(),
                LocalDate.of(2019, 5, 1)
        );

        assertExpiryDate(PayData.builder()
                .billingDate(LocalDate.of(2019, 3, 1))
                .payAmount(30000)
                .build(),
                LocalDate.of(2019, 6, 1));

        //예외 상황 테스트 추가 1월31일에 10000원을 내서 2월 28일이 되고, 그 다음 20000원을 내서 4월 31일이됨.

        assertExpiryDate(PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(20000)
                .build(), LocalDate.of(2019, 4, 30));
    }

    @Test
    void 십만원을_납부하면_1년_제공() {
        assertExpiryDate(PayData.builder()
                .billingDate(LocalDate.of(2019, 3, 1))
                .payAmount(100000)
                .build(),
                LocalDate.of(2020, 3, 1));
    }

    @Test
    void 십만원_이상_납부() {
        assertExpiryDate(PayData.builder()
                .billingDate(LocalDate.of(2019, 3, 1))
                .payAmount(130000)
                .build(), LocalDate.of(2020, 6, 1));

        assertExpiryDate(PayData.builder()
                .billingDate(LocalDate.of(2019, 1, 31))
                .payAmount(110000)
                .build(), LocalDate.of(2020, 2, 29));
    }

    @Test
    void 십만원_이상_납부_첫결제_존재() {
        assertExpiryDate(PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(120000)
                .build(), LocalDate.of(2020, 4, 30));
    }

    @Test
    void 윤달_십만원_납부() {
        assertExpiryDate(PayData.builder()
                .billingDate(LocalDate.of(2020, 2, 29))
                .payAmount(100000)
                .build(), LocalDate.of(2021, 2, 28));
    }

    private void assertExpiryDate(PayData payData, LocalDate expExpiryDate) {
        LocalDate result = cal.calculateExpiryDate(payData);
        assertEquals(expExpiryDate, result);
    }

}
