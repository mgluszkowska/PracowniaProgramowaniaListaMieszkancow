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

    public static void main (String[] args) throws SchedulerException {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobDetail job1 = newJob(JobWriteToFile.class)
                .withIdentity("job1", "group1")
                .build();

//        Trigger trigger = newTrigger()
//                .withIdentity("trigger1", "group1")
//                .startNow()
//                .withSchedule(simpleSchedule()
//                    .withIntervalInSeconds(30)
//                    .repeatForever())
//                .build();

        TriggerBuilder triggerbuild = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startNow().withSchedule(simpleSchedule()
                .withIntervalInSeconds(30)
                .repeatForever());

        Trigger trigger = triggerbuild.build();

        scheduler.scheduleJob(job1, trigger);

        scheduler.start();

        String city, name, surname, number;
        Person[] people = new Person[1000];

        //Tworze skaner, ktory bedzie zczytywal dane z konsoli
        Scanner console = new Scanner(System.in);

        SimpleDateFormat form =
                new SimpleDateFormat ("hh:mm:ss");

        Calendar cal = Calendar.getInstance();

        int i = 0;
        while(true) {
            //Pobieram dane z konsoli
            city = console.next();
            name = console.next();
            surname = console.next();
            number = console.next();

            if (isCorrect(number)) {
                //Tworze nowy obiekt typu classes.Person i wpisuje do tablicy people
                people[i] = new Person(city, name, surname, number);
                System.out.println("Nowa osoba: " + people[i].name + " " + people[i].surname + " " + form.format(cal.getTime()));
                citiesToPeople.put(people[i].getCity(), people[i]);
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

//    private static JobBuilder newTrigger() {
//    }

    public static void writeToFile() throws FileNotFoundException {
        Date logTime = new Date( );
        SimpleDateFormat form =
                new SimpleDateFormat ("hh:mm:ss");


        PrintWriter writeToFile = new PrintWriter("odp.txt");

        writeToFile.println("Update: " + form.format(logTime));

        //Ustalam klucze ???
        SetIterable<String> keys = Main.getCitiesToPeople().keySet();

        //Tworze liste kluczy
        MutableList<String> list = keys.toList();

        //Sortuje liste wg kluczy (miast)
        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);

        //Wypisuje kazde miasto, a dla niego wszystkich mieszkancow
        list.forEach(c -> {
            writeToFile.println(c);
            citiesToPeople.get(c).forEach(p -> {
                writeToFile.println("\t" + p.getName() + " " + p.getSurname() + " " + p.getPesel());
            });
        });
        writeToFile.close();
    }
}
