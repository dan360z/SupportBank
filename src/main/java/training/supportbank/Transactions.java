package training.supportbank;

import java.math.BigDecimal;
import java.time.LocalDate;

class Transaction {
    LocalDate date;
    String narrative;
    String to;
    String from;
    BigDecimal amount;

    Transaction(LocalDate d, String n, String t, String f, BigDecimal a) {
        this.date = d;
        this.narrative = n;
        this.to = t;
        this.from = f;
        this.amount = a;
    }

}
