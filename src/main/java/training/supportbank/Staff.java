package training.supportbank;

import java.math.BigDecimal;

public class Staff {

    static class Person {
        String name;
        BigDecimal balance = new BigDecimal("0.00");

        Person(String i) {
            this.name = i;
        }
    }

}
