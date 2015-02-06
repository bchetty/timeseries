package com.bchetty.series.iterators;

import com.bchetty.timeseries.iterators.WeekdayIterator;
import com.bchetty.timeseries.utils.enums.Weekday;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author b.chetty
 */
public class WeekdayIteratorTest {    
    public WeekdayIteratorTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}

    /**
     * Test of hasNext method, of class WeekdayIterator.
     */
    @Test
    public void testHasNext() {
        DateTime startOfThisWeek = DateTime.now().withDayOfWeek(Weekday.MONDAY.getWeekday());
        System.out.println("startOfThisWeek : " + startOfThisWeek);
        DateTime twoWeeksLater = startOfThisWeek.plusWeeks(2);
        System.out.println("Two Weeks After startOfThisWeek : " + twoWeeksLater);
        WeekdayIterator weekdayIter = new WeekdayIterator(startOfThisWeek, twoWeeksLater, Weekday.WEDNESDAY);
        assertTrue(weekdayIter.hasNext());
    }

    /**
     * Test of next method, of class WeekdayIterator.
     */
    @Test
    public void testNext() {
        DateTime febTwo2015 = new DateTime(2015,2,2,0,0);
        System.out.println("febTwo2015 : " + febTwo2015);
        DateTime fourWeeksLater = febTwo2015.plusWeeks(4);
        System.out.println("Four Weeks After febTwo2015 : " + fourWeeksLater);
        WeekdayIterator weekdayIter = new WeekdayIterator(febTwo2015, fourWeeksLater, Weekday.THURSDAY);
        DateTime firstWednesdayInRange = new DateTime(weekdayIter.next());
        System.out.println("Next Date : " + firstWednesdayInRange.toString());
        assertEquals(firstWednesdayInRange.getDayOfWeek(), Weekday.THURSDAY.getWeekday());
        assertEquals(firstWednesdayInRange.getDayOfMonth(),5);
        assertEquals(firstWednesdayInRange.getYear(), 2015);
    }    
}
