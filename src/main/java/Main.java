import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main (String[] args) {
        String city, name, surname, number;

        Scanner console = new Scanner(System.in);
        //city = console.nextLine();
        //System.out.println(city);

        //name = console.next();
        //System.out.println(name);

        //surname = console.next();
        //System.out.println(surname);

        number = console.next();
        //System.out.println(number);

        System.out.println(isCorrect(number));

        File myFile = new File("listamieszkancow.txt");
        PrintWriter writeToFile = new PrintWriter("listamieszkancow.txt");
        writeToFile.println(number);
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
