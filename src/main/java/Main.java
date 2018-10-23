import java.util.Scanner;

public class Main {

    public static void main (String[] args) {
        String city, name, surname, number;

        Scanner console = new Scanner(System.in);
        city = console.nextLine();
        System.out.println(city);

        name = console.next();
        System.out.println(name);

        surname = console.next();
        System.out.println(surname);

        number = console.next();
        System.out.println(number);

        System.out.println(isCorrect(number));
    }

    public static boolean isCorrect(String n) {
        char[] pesel = new char [12];
        pesel = n.toCharArray();
        System.out.println(pesel[0]);
        return true;
      }
}
