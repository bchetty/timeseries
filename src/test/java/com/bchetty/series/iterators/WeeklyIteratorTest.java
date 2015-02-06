package com.bchetty.series.iterators;

import com.bchetty.timeseries.iterators.WeeklyIterator;
import com.bchetty.timeseries.TimeSeries;
import com.bchetty.timeseries.TimeSeries.Weekly;
import com.bchetty.timeseries.utils.enums.Weekday;
import java.util.ArrayList;
import java.util.List;
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
public class WeeklyIteratorTest {    
    public WeeklyIteratorTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}

    /**
     * Test of hasNext method, of class WeeklyIterator.
     */
    @Test
    public void testWeekly1() {
        DateTime startOfThisWeek = DateTime.now().withDayOfWeek(Weekday.MONDAY.getWeekday());
        System.out.println("startOfThisWeek : " + startOfThisWeek);
        DateTime twoWeeksLater = startOfThisWeek.plusWeeks(2);
        System.out.println("Two Weeks After startOfThisWeek : " + twoWeeksLater);
        TimeSeries timeSeries = new TimeSeries(startOfThisWeek.toDate(), twoWeeksLater.toDate());        
        WeeklyIterator weekdayIter = new WeeklyIterator(timeSeries);
        assertTrue(weekdayIter.hasNext());
    }
    
    /**
     * Test Weekly member of TimeSeries
     */
    @Test
    public void testWeekly2() {
        DateTime startOfThisWeek = DateTime.now().withDayOfWeek(Weekday.MONDAY.getWeekday());
        System.out.println("startOfThisWeek : " + startOfThisWeek);
        DateTime twoWeeksLater = startOfThisWeek.plusWeeks(2);
        System.out.println("Two Weeks After startOfThisWeek : " + twoWeeksLater);
        TimeSeries timeSeries = new TimeSeries(startOfThisWeek.toDate(), twoWeeksLater.toDate());                
        assertTrue(timeSeries.getWeekly() != null);
        assertTrue(timeSeries.getWeekly().getWeekdays() != null && !timeSeries.getWeekly().getWeekdays().isEmpty());
        assertTrue(timeSeries.getWeekly().getWeekdays().get(0).getWeekday() == Weekday.MONDAY.getWeekday());
    }

    /**
     * Test of next method, of class WeeklyIterator.
     */
    @Test
    public void testWeekly3() {
        DateTime febFour2015 = new DateTime(2015,2,4,0,0);
        System.out.println("febFour2015 : " + febFour2015);
        DateTime febFifteen2015 = new DateTime(2015,2,15,0,0);
        System.out.println("febFifteen2015 : " + febFifteen2015);
        
        TimeSeries timeSeries = new TimeSeries(febFour2015.toDate(), febFifteen2015.toDate());
        Weekly weekly = new Weekly();
        List<Weekday> weekdays = new ArrayList<Weekday>();
        weekdays.add(Weekday.MONDAY);
        weekdays.add(Weekday.THURSDAY);
        weekly.setWeekdays(weekdays);
        timeSeries.setWeekly(weekly);
        
        WeeklyIterator weeklyIter = new WeeklyIterator(timeSeries);        
        DateTime dateTime1 = new DateTime(weeklyIter.next());
        System.out.println("dateTime1 : " + dateTime1);
        assertTrue(dateTime1.getDayOfWeek() == Weekday.MONDAY.getWeekday());
        assertTrue(dateTime1.getDayOfMonth() == 9);
        
        DateTime dateTime2 = new DateTime(weeklyIter.next());
        System.out.println("dateTime2 : " + dateTime2);
        assertTrue(dateTime2.getDayOfWeek() == Weekday.THURSDAY.getWeekday());
        assertTrue(dateTime2.getDayOfMonth() == 5);
        
        DateTime dateTime3 = new DateTime(weeklyIter.next());
        System.out.println("dateTime3 : " + dateTime3);
        assertTrue(dateTime3.getDayOfWeek() == Weekday.THURSDAY.getWeekday());
        assertTrue(dateTime3.getDayOfMonth() == 12);
    }    
}