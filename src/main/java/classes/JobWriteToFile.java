package classes;

import org.apache.log4j.chainsaw.Main;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.SetIterable;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import classes.Main;

public class JobWriteToFile implements org.quartz.Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException, FileNotFoundException {

        Date logTime = new Date( );
        SimpleDateFormat form =
                new SimpleDateFormat ("hh:mm:ss");

        //Tworze nowy plik z lista mieszkancow
        File myFile = new File("odp.txt");
        PrintWriter writeToFile = new PrintWriter("odp.txt");

        writeToFile.println("Update: " + form.format(logTime));

        //Ustalam klucze ???
        SetIterable<String> keys = citiesToPeople.keySet();

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