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

        LocalDateTime now = LocalDateTime.now();

//        if (now.getDayOfWeek().name().equals("FRIDAY")) {
//            if (now.toLocalTime().isAfter(LocalTime.of(18, 45))) {
//                LocalTime endTime = LocalTime.of(8, 15);
//                LocalDateTime endDate = LocalDateTime.of(now().plusDays(1), endTime);
//                minutes = Duration.between(now, endDate).toMinutes();
//                System.out.println(minutes + " minut do konca przerwy");
//            }
//            else {
//                if (timeIndex %2 == 0) {
//                    LocalDateTime endDate = LocalDateTime.of(now.toLocalDate(), endDates[timeIndex]);
//                    minutes = Duration.between(now, endDate).toMinutes();
//                    System.out.println(minutes + " minut do konca przerwy");
//                }
//                else {
//                    LocalDateTime endDate = LocalDateTime.of(now.toLocalDate(), endDates[timeIndex]);
//                    minutes = Duration.between(now, endDate).toMinutes();
//                    System.out.println(minutes + " minut do konca zajec");
//                }
//            }
//        }
//        else if (now.getDayOfWeek().name().equals("SATURDAY") && now.toLocalTime().isBefore(LocalTime.of(8, 15))) {
//                LocalTime endTime = LocalTime.of(8, 15);
//                LocalDateTime endDate = LocalDateTime.of(now().plusDays(1), endTime);
//                minutes = Duration.between(now, endDate).toMinutes();
//                System.out.println(minutes + " minut do konca przerwy");
//        }
//        else if (now.getDayOfWeek().name().equals("SATURDAY") && now.toLocalTime().isAfter(LocalTime.of(8, 15))
//                || now.getDayOfWeek().name().equals("SUNDAY")) {
//                LocalTime endTime = LocalTime.of(8, 15);
//                LocalDateTime endDate = LocalDateTime.of(now().plusDays(1), endTime);
//                minutes = Duration.between(now, endDate).toMinutes();
//                System.out.println(minutes + " minut do konca przerwy");
//        }
//        else {
//            if (now.toLocalTime().isAfter(LocalTime.of(18, 45))) {
//                LocalTime endTime = LocalTime.of(8, 15);
//                LocalDateTime endDate = LocalDateTime.of(now.toLocalDate().plusDays(1), endTime);
//                minutes = Duration.between(now, endDate).toMinutes();
//                System.out.println(minutes + " minut do konca przerwy");
//            }
//            else {
//                if (timeIndex %2 == 0) {
//                    LocalDateTime endDate = LocalDateTime.of(now.toLocalDate(), endDates[timeIndex]);
//                    minutes = Duration.between(now, endDate).toMinutes();
//                    System.out.println(minutes + " minut do konca przerwy");
//                }
//                else {
//                    LocalDateTime endDate = LocalDateTime.of(now.toLocalDate(), endDates[timeIndex]);
//                    minutes = Duration.between(now, endDate).toMinutes();
//                    System.out.println(minutes + " minut do konca zajec");
//                }
//            }
//        }
        long minutes = 0;
        minutes = Countdown.countMinutes(now);
        System.out.println(minutes + " minut do końca przerwy/zajęć");
    }
}
