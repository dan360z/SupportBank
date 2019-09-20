package training.supportbank;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws IOException {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<String> file = Files.readAllLines(Paths.get("Transactions2014.csv"), Charset.forName("windows-1252"));


        List<Person> csvPeopleList = new ArrayList<>();

        ArrayList<Person> peopleList = new ArrayList<>();

        List<Transaction> transactionList = new ArrayList<>();


        int count = 0;

        for (String line : file) {

            if (!(count == 0)) {

                String[] lineItems = line.split(",");

                //--CSV People List-----------------------------------
                csvPeopleList.add(new Person(lineItems[2]));

                //--Transactions--------------------------------
                LocalDate date = LocalDate.parse(lineItems[0], dateTimeFormatter);
                String desc = lineItems[3];
                String to = (lineItems[2]);
                String from  = (lineItems[1]);
                BigDecimal amount = new BigDecimal(lineItems[4]);

                transactionList.add(new Transaction(date, desc, to, from, amount));

            }

            count ++;

        }

        //----------------------------------------------------------------

        removeDuplicates(csvPeopleList, peopleList);

        //-----------------------------------------------------------------


        for (int i = 0; i < transactionList.size(); i++) {

            BigDecimal amount = transactionList.get(i).amount;
            BigDecimal payersBalance = getPerson(transactionList.get(i).from, peopleList).balance;
            BigDecimal receiversBalance = getPerson(transactionList.get(i).to, peopleList).balance;


            BigDecimal payersUpdatedBalance = payersBalance.subtract(amount);
            BigDecimal receiversUpdatedBalance = receiversBalance.add(amount);

            getPerson(transactionList.get(i).from, peopleList).setBalance(payersUpdatedBalance);
            getPerson(transactionList.get(i).to, peopleList).setBalance(receiversUpdatedBalance);

        }

        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter account holder name");

        String userInput = myObj.nextLine();

        System.out.println("\nBalance:");

        for (int i = 0; i < peopleList.size(); i++) {
            if (userInput.toLowerCase().equals(peopleList.get(i).name.toLowerCase())) {
                System.out.println("\n" + peopleList.get(i).name + "'s Balance: $" + peopleList.get(i).balance);
            }
        }

        System.out.println("\n--------------------------------------------------\n");

        System.out.println("Outgoing:\n");
        for (int i = 0; i < transactionList.size(); i++) {
            if (userInput.toLowerCase().equals(transactionList.get(i).from.toLowerCase())) {
                System.out.println(transactionList.get(i).from + " paid $" + transactionList.get(i).amount + " to " + transactionList.get(i).to + " on " + transactionList.get(i).date + ". Referrence: " + transactionList.get(i).narrative);
            }
        }
        System.out.println("\n--------------------------------------------------\n");
        System.out.println("Income:\n");
        for (int i = 0; i < transactionList.size(); i++) {
            if (userInput.toLowerCase().equals(transactionList.get(i).to.toLowerCase())) {
                System.out.println(transactionList.get(i).to + " received $" + transactionList.get(i).amount + " from " + transactionList.get(i).from + " on " + transactionList.get(i).date + ". Referrence: " + transactionList.get(i).narrative);
            }
        }

    }

    private static Person getPerson(String name, List<Person> peopleList) {

        for (int i = 0; i < peopleList.size(); i ++) {
            if (name.equals(peopleList.get(i).name)) {
                return peopleList.get(i);
            }
        }
        return null;
    }


    private static void removeDuplicates(List<Person>csvPeopleList, List<Person>peopleList) {

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
    }

}
