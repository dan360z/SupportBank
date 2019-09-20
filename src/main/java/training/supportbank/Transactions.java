package training.supportbank;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transactions {

    static class Transaction {
        LocalDate date;
        String narrative;
        Staff.Person to;
        Staff.Person from;
        BigDecimal amount;

        Transaction(String n, Staff.Person t, Staff.Person f, BigDecimal a) {
            this.narrative = n;
            this.to = t;
            this.from = f;
            this.amount = a;
        }

        void removeAllMoney() {
            amount = new BigDecimal(0);
        }
    }

}
