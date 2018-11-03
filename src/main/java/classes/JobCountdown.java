package classes;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;


public class JobCountdown implements org.quartz.Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        LocalTime startDate = LocalTime.now();
        LocalTime endDate = LocalTime.of(12,0,0,0);
        long minutes = Duration.between(startDate, endDate).toMinutes() + 1;
        System.out.println(minutes + " minut do konca zajec/przerwy");
    }
}
