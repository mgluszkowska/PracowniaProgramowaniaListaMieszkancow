package classes;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;


public class JobCountdown implements org.quartz.Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date logTime = new Date( );
        SimpleDateFormat form =
                new SimpleDateFormat ("hh:mm:ss");

        int minutes;
        System.out.println(form.format(logTime) + " minut do konca zajec/przerwy");
    }
}
