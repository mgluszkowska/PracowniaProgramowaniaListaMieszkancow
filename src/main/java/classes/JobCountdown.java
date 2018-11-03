package classes;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import static java.time.LocalDate.now;


public class JobCountdown implements org.quartz.Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {


        LocalDateTime now = LocalDateTime.of(2018, 11, 5, 8, 0); // LocalDateTime.now();
        LocalTime[] endDates = new LocalTime[12];
        endDates[0] = LocalTime.of(8, 15);
        endDates[1] = LocalTime.of(9, 45);
        endDates[2] = LocalTime.of(10, 0);
        endDates[3] = LocalTime.of(11, 30);
        endDates[4] = LocalTime.of(11, 45);
        endDates[5] = LocalTime.of(13, 15);
        endDates[6] = LocalTime.of(13, 45);
        endDates[7] = LocalTime.of(15, 15);
        endDates[8] = LocalTime.of(15, 30);
        endDates[9] = LocalTime.of(17, 0);
        endDates[10] = LocalTime.of(17, 15);
        endDates[11] = LocalTime.of(18, 45);

        int timeIndex = 0;
        for (int i=0; i<=11; i++) {
            if (now.toLocalTime().isBefore(endDates[i])) {
                timeIndex = i;
                break;
            }
        }

        //System.out.println(now.getDayOfWeek().name());
        long minutes = 0;
        if (now.getDayOfWeek().name().equals("FRIDAY")) {
            if (now.toLocalTime().isAfter(LocalTime.of(18, 45))) {
                LocalTime endTime = LocalTime.of(8, 15);
                LocalDateTime endDate = LocalDateTime.of(now().plusDays(2), endTime);
                minutes = Duration.between(now, endDate).toMinutes();
                System.out.println(minutes + " minut do konca przerwy");
            }
            else {
                System.out.println("Dzien pracy");
            }
        }
        else if (now.getDayOfWeek().name().equals("SATURDAY")) {
                LocalTime endTime = LocalTime.of(8, 15);
                LocalDateTime endDate = LocalDateTime.of(now().plusDays(2), endTime);
                minutes = Duration.between(now, endDate).toMinutes();
                System.out.println(minutes + " minut do konca przerwy");
        }
        else if (now.getDayOfWeek().name().equals("SUNDAY")) {
                LocalTime endTime = LocalTime.of(8, 15);
                LocalDateTime endDate = LocalDateTime.of(now.toLocalDate().plusDays(1), endTime);
                minutes = Duration.between(now, endDate).toMinutes();
                System.out.println(minutes + " minut do konca przerwy");
        }
        else {
            if (now.toLocalTime().isAfter(LocalTime.of(18, 45))) {
                LocalTime endTime = LocalTime.of(8, 15);
                LocalDateTime endDate = LocalDateTime.of(now.toLocalDate().plusDays(1), endTime);
                minutes = Duration.between(now, endDate).toMinutes();
                System.out.println(minutes + " minut do konca przerwy");
            }
            else {
                if (timeIndex %2 == 0) {
                    LocalDateTime endDate = LocalDateTime.of(now.toLocalDate(), endDates[timeIndex]);
                    minutes = Duration.between(now, endDate).toMinutes();
                    System.out.println(minutes + " minut do konca przerwy");
                }
                else {
                    LocalDateTime endDate = LocalDateTime.of(now.toLocalDate(), endDates[timeIndex]);
                    minutes = Duration.between(now, endDate).toMinutes();
                    System.out.println(minutes + " minut do konca zajec");
                }
            }
        }
    }
}
