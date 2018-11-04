package classes;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;


import static classes.Main.writeToFile;

public class JobWriteToFile implements org.quartz.Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        Date logTime = new Date( );
        SimpleDateFormat form =
                new SimpleDateFormat ("hh:mm:ss");

        try {
            writeToFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}