package training.supportbank;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) throws IOException {

        List<String> file = Files.readAllLines(Paths.get("Transactions2014.csv"), Charset.forName("windows-1252"));


        List<Staff.Person> csvPeopleList = new ArrayList<>();

        ArrayList<Staff.Person> peopleList = new ArrayList<>();

        List<Transactions.Transaction> transactionList = new ArrayList<>();


        int count = 0;

        for (String line : file) {

            if (!(count == 0)) {

                String[] lineItems = line.split(",");

                //--CSV People List-----------------------------------
                csvPeopleList.add(new Staff.Person(lineItems[2]));

                //--Transactions--------------------------------
                String desc = lineItems[3];
                Staff.Person to = new Staff.Person(lineItems[2]);
                Staff.Person from  = new Staff.Person(lineItems[1]);
                BigDecimal amount = new BigDecimal(lineItems[4]);

                transactionList.add(new Transactions.Transaction(desc, to, from, amount));

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
                peopleList.add(new Staff.Person(csvPeopleList.get(i).name));
            }
        }

        //peopleList = removeDuplicates();

        //-----------------------------------------------------------------

        for (int i = 0; i < transactionList.size(); i ++) {

            System.out.println(transactionList.get(i).narrative);
            System.out.println(transactionList.get(i).to.name);
            System.out.println(transactionList.get(i).from.name);
            System.out.println(transactionList.get(i).amount);

        }

    }
    /*
    removeDuplicates(popList, newList) {
        ArrayList<Person> people = new ArrayList<Person>();

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

        return people;
    }*/
}
