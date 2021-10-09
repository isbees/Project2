package uintTesting;
import static org.junit.Assert.*;

public class DateTest {

    @org.junit.Test
    public void isValid() {
        //Current date invalid
        Date test1 = new Date();
        assertFalse(test1.isValid());

        //Years < 2021 invalid
        Date test2 = new Date("1/20/2020");
        assertFalse(test2.isValid());

        //Years > 2021 invalid
        Date test3 = new Date("1/20/2022");
        assertFalse(test3.isValid());

        //Year = 2021, Month>Current month invalid
        Date test4 = new Date("12/10/2021");
        assertFalse(test4.isValid());

        //Year = 2021, Month=Current month, Day>Current day invalid
        Date test5 = new Date("10/28/2021");
        assertFalse(test5.isValid());

        //Year = 2021, Month<Current month, Day>Current day valid
        Date test6 = new Date("9/28/2021");
        assertTrue(test6.isValid());

        //Year = 2021, Month=Current month, Day<Current day valid
        Date test7 = new Date("10/08/2021");
        assertTrue(test7.isValid());
    }
}