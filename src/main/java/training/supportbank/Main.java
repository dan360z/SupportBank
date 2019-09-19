package training.supportbank;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) throws IOException {

        class Person {
            String name;
            BigDecimal balance = new BigDecimal("0.00");

            Person(String i) {
                this.name = i;
            }
        }

        class Transaction {
            LocalDate date;
            String narrative;
            Person to;
            Person from;
            BigDecimal amount;

            Transaction(String n, Person t, Person f, BigDecimal a) {
                this.narrative = n;
                this.to = t;
                this.from = f;
                this.amount = a;
            }
        }


        //-------------------------------------------
        
        List<String> file = Files.readAllLines(Paths.get("Transactions2014.csv"), Charset.forName("windows-1252"));


        List<String> dates = new ArrayList<>();
        List<String> namesFrom = new ArrayList<>();
        List<String> namesTo = new ArrayList<>();
        List<String> description = new ArrayList<>();
        List<String> amount = new ArrayList<>();

        int count = 0;

        for (String line : file) {

            if (!(count == 0)) {

                String[] lineItems = line.split(",");

                dates.add(lineItems[0]);
                namesFrom.add(lineItems[1]);
                namesTo.add(lineItems[2]);
                description.add(lineItems[3]);
                amount.add(lineItems[4]);

                Person p = new Person(lineItems[1]);

                System.out.println(p.name);

            }

            count ++;

        }


    /*
        System.out.println("\n" + "📆 Dates list: " + dates + "\n");
        System.out.println("👩 ‍From list: " + namesFrom + "\n");
        System.out.println("👨 ‍To list: " + namesTo + "\n");
        System.out.println("📝 Narrative list: " + description + "\n");
        System.out.println("💵 Amount list: " + amount + "\n");
    /*

/*
        for (int i = 0; i < namesFrom.size(); i++) {

            System.out.println(namesFrom.get(i));

        }
*/

        //------------------------------------------------------------------

/*
        Person jon = new Person();
        jon.name = "Jon A";

        Person sarah = new Person();
        sarah.name = "Sarah T";

        Transaction lunch = new Transaction();
        lunch.date = null;
        lunch.narrative = "Lunch";
        lunch.amount = new BigDecimal("10.00");
        lunch.to = jon;
        lunch.from = sarah;

        jon.balance = jon.balance.add(lunch.amount);
        sarah.balance = sarah.balance.subtract(lunch.amount);

        System.out.println(jon.name + "'s Balance: " + "$" + jon.balance + " 🤑");
        System.out.println(sarah.name + "'s Balance: " + "$" + sarah.balance + " 😥");

*/
    }
}
