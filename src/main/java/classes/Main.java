package classes;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.SetIterable;
import org.eclipse.collections.impl.multimap.bag.HashBagMultimap;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import java.util.Calendar;

public class Main {

    static HashBagMultimap<String, Person> citiesToPeople = HashBagMultimap.newMultimap();

    //Tworze nowy plik z lista mieszkancow
    File myFile = new File("odp.txt");

    public static HashBagMultimap<String, Person> getCitiesToPeople() {
        return citiesToPeople;
    }

    static HashBagMultimap<String, Person> peselToPeople = HashBagMultimap.newMultimap();

    static Person[] people = new Person[1000];

    static int numberOfPeople = 0;

    public static void main (String[] args) throws SchedulerException {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobDetail job1 = newJob(JobWriteToFile.class)
                .withIdentity("job1", "group1")
                .build();

        JobDetail job2 = newJob(JobCountdown.class)
                .withIdentity("job2", "group2")
                .build();

        TriggerBuilder triggerbuild = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startNow()
                .withSchedule(cronSchedule("0/30 * * ? * *"));

        Trigger trigger = triggerbuild.build();

        TriggerBuilder triggerbuild2 = TriggerBuilder.newTrigger().withIdentity("trigger2", "group2").startNow()
                .withSchedule(cronSchedule("0 * * ? * *"));

        Trigger trigger2 = triggerbuild2.build();

        scheduler.scheduleJob(job1, trigger);
        scheduler.scheduleJob(job2, trigger2);

        scheduler.start();

        String city, name, surname, number;


        //Tworze skaner, ktory bedzie zczytywal dane z konsoli
        Scanner console = new Scanner(System.in);

        SimpleDateFormat form =
                new SimpleDateFormat ("hh:mm:ss");

        int i = 0;
        while(true) {
            //Pobieram dane z konsoli
            city = console.next();
            name = console.next();
            surname = console.next();
            number = console.next();

            if (isCorrect(number)) {
                //Tworze nowy obiekt typu classes.Person i wpisuje do tablicy people
                int index = findPesel(number);
                //System.out.println(index);

                if(index < 0) {
                    people[i] = new Person(city, name, surname, number);
                    numberOfPeople++;

                    System.out.println("Nowa osoba: " + people[i].name + " " + people[i].surname);
//                    Calendar cal = Calendar.getInstance();
//                    System.out.println("Nowa osoba: " + people[i].name + " " + people[i].surname + " " + form.format(cal.getTime()));
//                    citiesToPeople.put(people[i].getCity(), people[i]);
                }
                else {
                    //System.out.println(people[index].name);
                    people[index].city = city;
                    people[index].name = name;
                    people[index].surname = surname;
                    System.out.println("Zmieniono dane osoby o numerze pesel: " + people[index].pesel + ": " + people[index].name +
                            " " + people[index].surname);
                }
            }
            else {
                System.out.println("Bledny numer PESEL. Osoba nie zostanie zapisana do pliku.");
            }
            i++;
        }

        //scheduler.shutdown();
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


    public static int findPesel(String pesel) {
        for (int i = 0; i < numberOfPeople; i++) {
            if (people[i].pesel.equals(pesel) ) {
                return i;
            }
        }
        return -1;
    }

    public static void writeToFile() throws FileNotFoundException {
        Date logTime = new Date( );
        SimpleDateFormat form =
                new SimpleDateFormat ("hh:mm:ss");

        for (int i = 0; i < numberOfPeople; i++) {
            //System.out.println("Liczba ludzi na liscie people: " + numberOfPeople);
            citiesToPeople.clear();
            citiesToPeople.put(people[i].getCity(), people[i]);
            //System.out.println("Zapisalem do Multibag czlowieka o imieniu " + people[i].name);
        }

        PrintWriter writeToFile = new PrintWriter("odp.txt");

        writeToFile.println("Update: " + form.format(logTime));

        //Ustalam klucze ???

        SetIterable<String> keys = Main.getCitiesToPeople().keySet();
        //writeToFile.println("Wyciagam klucze");

        //Tworze liste kluczy
        MutableList<String> list = keys.toList();
        //writeToFile.println("Robie liste kluczy");


        //Sortuje liste wg kluczy (miast)
        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
        //writeToFile.println("Sortuje");


        //Wypisuje kazde miasto, a dla niego wszystkich mieszkancow
        //writeToFile.println("Bede wypisywal");

        list.forEach(c -> {
            writeToFile.println(c);
            citiesToPeople.get(c).forEach(p -> {
                writeToFile.println("\t" + p.getName() + " " + p.getSurname() + " " + p.getPesel());
            });
        });
        //writeToFile.println("Wypisalem");

        writeToFile.close();
    }
}
