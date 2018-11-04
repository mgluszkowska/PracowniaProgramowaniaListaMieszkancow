package classes;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class CountdownTest {

    @Test
    public void countMinutes1() {
        long minutes;
        LocalDateTime moment = LocalDateTime.of(2018, 11, 4, 7, 0 );
        minutes = Countdown.countMinutes(moment);
        assertTrue(minutes == 1515);
    }

    @Test
    public void countMinutes2() {
        long minutes;
        LocalDateTime moment = LocalDateTime.of(2018, 11, 4, 17, 0 );
        minutes = Countdown.countMinutes(moment);
        assertTrue(minutes == 915);
    }

    @Test
    public void countMinutes3() {
        long minutes;
        LocalDateTime moment = LocalDateTime.of(2018, 11, 3, 7, 0 );
        minutes = Countdown.countMinutes(moment);
        assertTrue(minutes == 2955);
    }

    @Test
    public void countMinutes4() {
        long minutes;
        LocalDateTime moment = LocalDateTime.of(2018, 11, 3, 17, 0 );
        minutes = Countdown.countMinutes(moment);
        assertTrue(minutes == 2355);
    }

    @Test
    public void countMinutes5() {
        long minutes;
        LocalDateTime moment = LocalDateTime.of(2018, 11, 2, 19, 0 );
        minutes = Countdown.countMinutes(moment);
        assertTrue(minutes == 3675);
    }

    @Test
    public void countMinutes6() {
        long minutes;
        LocalDateTime moment = LocalDateTime.of(2018, 11, 2, 7, 0 );
        minutes = Countdown.countMinutes(moment);
        assertTrue(minutes == 75);
    }

    @Test
    public void countMinutes7() {
        long minutes;
        LocalDateTime moment = LocalDateTime.of(2018, 11, 2, 9, 0 );
        minutes = Countdown.countMinutes(moment);
        assertTrue(minutes == 45);
    }

    @Test
    public void countMinutes8() {
        long minutes;
        LocalDateTime moment = LocalDateTime.of(2018, 11, 2, 9, 50 );
        minutes = Countdown.countMinutes(moment);
        assertTrue(minutes == 10);
    }

    @Test
    public void countMinutes9() {
        long minutes;
        LocalDateTime moment = LocalDateTime.of(2018, 11, 5, 7, 0 );
        minutes = Countdown.countMinutes(moment);
        assertTrue(minutes == 75);
    }

    @Test
    public void countMinutes10() {
        long minutes;
        LocalDateTime moment = LocalDateTime.of(2018, 11, 1, 20, 0 );
        minutes = Countdown.countMinutes(moment);
        assertTrue(minutes == 735);
    }

}