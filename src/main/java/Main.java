import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.SetIterable;
import org.eclipse.collections.impl.multimap.bag.HashBagMultimap;

import java.io.File;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Main {

    public static void main (String[] args) throws FileNotFoundException {
        String city, name, surname, number;

        //Tworze nowy plik z lista mieszkancow
        File myFile = new File("listamieszkancow.txt");
        //Tworze obiekt, ktory bedzie pisal do tego pliku
        PrintWriter writeToFile = new PrintWriter("listamieszkancow.txt");

        //Tworze skaner, ktory bedzie zczytywal dane z konsoli
        Scanner console = new Scanner(System.in);

        HashBagMultimap<String, Person> citiesToPeople = HashBagMultimap.newMultimap();

        //Pobieram dane z konsoli
        city = console.nextLine();
        name = console.next();
        surname = console.next();
        number = console.next();

        if (isCorrect(number)) {
            //Tworze nowy obiekt typu Person
            Person citizen = new Person(city, name, surname, number);
            System.out.println("Nowa osoba: " + citizen.name + citizen.surname);

            citiesToPeople.put(citizen.getCity(), citizen);



            //Zapisuje dane do pliku
//            System.out.println("Dane sa poprawne.");
//            writeToFile.println(number);
//            writeToFile.close();
        }

        //Ustalam klucze ???
        SetIterable<String> keys = citiesToPeople.keySet();

        //Tworze liste kluczy
        MutableList<String> list = keys.toList();

        //Sortuje liste wg kluczy (miast)
        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);

        //Wypisuje kazde miasto, a dla niego wszystkich mieszkancow
        list.forEach(c -> {
            System.out.println(c);
            citiesToPeople.get(c).forEach(p -> {
                System.out.println("\t" + p.getName() + " " + p.getSurname() + " " + p.getPesel());
            });
        });
    }

    public static boolean isCorrect(String n) {
        int[] pesel = new int[n.length()];
        if (n.length() != 11) {
            return false;
        } else {
            String[] stringArray = new String[13];
            stringArray = n.split("");
            char[] arr = new char[12];
            arr = n.toCharArray();

            int i;
            for (i = 0; i < stringArray.length; i++) {
                if (java.lang.Character.isDigit(arr[i]) == false) {
                    return false;
                } else {
                    pesel[i] = Integer.parseInt(stringArray[i]);
                }
            }
            int controlNumber = 9 * pesel[0] + 7 * pesel[1] + 3 * pesel[2] + pesel[3] + 9 * pesel[4] + 7 * pesel[5] + 3 * pesel[6] + pesel[7] + 9 * pesel[8] + 7 * pesel[9];
            if (controlNumber % 10 == pesel[10]) {
                return true;
            } else {
                return false;
            }
        }

    }
}
