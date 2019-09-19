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


        List<Person> csvPeopleList = new ArrayList<>();

        List<Person> peopleList = new ArrayList<>();

        List<Transaction> transactionList = new ArrayList<>();


        int count = 0;

        for (String line : file) {

            if (!(count == 0)) {

                String[] lineItems = line.split(",");

                //--People-----------------------------------
                csvPeopleList.add(new Person(lineItems[2]));

                //--Transactions--------------------------------
                String desc = lineItems[3];
                Person to = new Person(lineItems[2]);
                Person from  = new Person(lineItems[1]);
                BigDecimal amount = new BigDecimal(lineItems[4]);

                transactionList.add(new Transaction(desc, to, from, amount));

            }

            count ++;

        }

        //--Builds People List------------------------------------------------------

        for (int i = 0; i < csvPeopleList.size(); i++) {

            boolean Duplicates = false;

            for (int x = i + 1; x < csvPeopleList.size(); x++) {

                if (csvPeopleList.get(x).name.equals(csvPeopleList.get(i).name)) {
                    Duplicates = true;
                }
            }

            if (!Duplicates) {
                peopleList.add(new Person(csvPeopleList.get(i).name));
            }
        }

        //-----------------------------------------------------------------

        for (int i = 0; i < peopleList.size(); i++) {

            //System.out.println(transactionList.get(i).from.name + " gave " + transactionList.get(i).amount + " to " + transactionList.get(i).to.name);
            System.out.println(peopleList.get(i).name);
        }

        System.out.println(peopleList.size());
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
