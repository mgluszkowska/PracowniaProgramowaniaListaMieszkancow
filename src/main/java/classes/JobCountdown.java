package classes;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.time.LocalDateTime;

public class JobCountdown implements org.quartz.Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        LocalDateTime now = LocalDateTime.now();
        long minutes;
        minutes = Countdown.countMinutes(now);
        if (Countdown.isClass(now)) {
            System.out.println(minutes + " minut do końca zajęć");
        }
        else {
            System.out.println(minutes + " minut do końca przerwy");
        }
    }
}
