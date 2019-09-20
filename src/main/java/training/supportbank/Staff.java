package training.supportbank;

import java.math.BigDecimal;

class Person {
    String name;
    BigDecimal balance = new BigDecimal("0.00");

    Person(String i) {
        this.name = i;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
