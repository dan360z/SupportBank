package training.supportbank;

public class Main {
    public static void main(String args[]) {


        class Person {
            String name;
            double totalFunds = 0;
        }

        class Transaction {
            String date;
            String narrative;
            Person to;
            Person from;
            double amount;
        }



        /*
        Person jon = new Person();
        jon.name = "Jon A";

        Person sarah = new Person();
        sarah.name = "Sarah T";


        Transaction lunch = new Transaction();
        lunch.amount = 10;
        lunch.to = jon;
        lunch.from = sarah;

        jon.totalFunds += lunch.amount;
        sarah.totalFunds -= lunch.amount;*/

    }
}
