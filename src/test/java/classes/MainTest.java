package classes;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void isCorrectTest1() {
        boolean verdict = Main.isCorrect("97062006000");
        assertTrue(verdict==true);
    }

    @Test
    public void isCorrectTest2() {
        boolean verdict = Main.isCorrect("9");
        assertTrue(verdict==false);
    }

    @Test
    public void isCorrectTest3() {
        boolean verdict = Main.isCorrect("12345678911");
        assertTrue(verdict==false);
    }
}