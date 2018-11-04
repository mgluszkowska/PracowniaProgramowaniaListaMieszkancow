package classes;

import org.junit.Test;
import java.time.LocalDateTime;
import static org.junit.Assert.*;

public class isClassTest {

    @Test
    public void isClassTest1() {
        boolean verdict = Countdown.isClass(LocalDateTime.of(2018, 11, 5, 8, 30));
        assertTrue(verdict == true);
    }

    @Test
    public void isClassTest2() {
        boolean verdict = Countdown.isClass(LocalDateTime.of(2018, 11, 5, 9, 50));
        assertTrue(verdict == false);
    }

    @Test
    public void isClassTest3() {
        boolean verdict = Countdown.isClass(LocalDateTime.of(2018, 11, 5, 19, 30));
        assertTrue(verdict == false);
    }

    @Test
    public void isClassTest4() {
        boolean verdict = Countdown.isClass(LocalDateTime.of(2018, 11, 4, 8, 30));
        assertTrue(verdict == false);
    }

}