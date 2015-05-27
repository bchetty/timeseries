package com.bchetty.series.iterators;

import com.bchetty.timeseries.TimeSeries;
import com.bchetty.timeseries.TimeSeries.Yearly.YearlyWeekday;
import com.bchetty.timeseries.iterators.YearlyWeekdayIterator;
import com.bchetty.timeseries.utils.enums.Month;
import com.bchetty.timeseries.utils.enums.WeekOfMonth;
import com.bchetty.timeseries.utils.enums.Weekday;
import java.util.NoSuchElementException;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author b.chetty
 */
public class YearlyWeekdayIteratorTest {
    public YearlyWeekdayIteratorTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}

    /**
     * Test of hasNext method, of class MonthlyDayIterator.
     */
    @Test
    public void testHasNext() {
        DateTime may282015 = new DateTime(2015,5,28,0,0);        
        DateTime may282016 = new DateTime(2016,5,28,0,0);
        TimeSeries timeSeries = new TimeSeries(may282015.toDate(), may282016.toDate());   
        
        YearlyWeekdayIterator yearlyWeekdayIterator = new YearlyWeekdayIterator(timeSeries);
        assertTrue(yearlyWeekdayIterator.hasNext());
    }
    
    /**
     * Test of hasNext method, of class MonthlyDayIterator.
     */
    @Test
    public void testNext1() {
        DateTime may282015 = new DateTime(2015,5,28,0,0);
        System.out.println("may282015 : " + may282015);
        DateTime may282017 = new DateTime(2017,5,28,0,0);
        System.out.println("may282017 : " + may282017);
        TimeSeries timeSeries = new TimeSeries(may282015.toDate(), may282017.toDate());
        
        TimeSeries.Yearly yearly = new TimeSeries.Yearly();
        YearlyWeekday yearlyWeekday = new YearlyWeekday();
        yearlyWeekday.setMonth(Month.JUNE);
        yearlyWeekday.setWeekOfMonth(WeekOfMonth.THREE);
        yearlyWeekday.setWeekday(Weekday.WEDNESDAY);
        yearly.setYearlyWeekday(yearlyWeekday);
        timeSeries.setYearly(yearly);
        
        YearlyWeekdayIterator yearlyWeekdayIterator = new YearlyWeekdayIterator(timeSeries);        
        DateTime dateTime1 = new DateTime(yearlyWeekdayIterator.next());
        System.out.println("dateTime1 : " + dateTime1);
        assertTrue(dateTime1.getDayOfWeek() == (Weekday.WEDNESDAY.ordinal() + 1));
        assertTrue(dateTime1.getDayOfMonth() == 17);
        assertTrue(dateTime1.getMonthOfYear() == 6);
        assertTrue(dateTime1.getYear() == 2015);
        
        DateTime dateTime2 = new DateTime(yearlyWeekdayIterator.next());
        System.out.println("dateTime2 : " + dateTime2);
        assertTrue(dateTime2.getDayOfWeek() == (Weekday.WEDNESDAY.ordinal() + 1));
        assertTrue(dateTime2.getDayOfMonth() == 15);
        assertTrue(dateTime2.getMonthOfYear() == 6);
        assertTrue(dateTime2.getYear() == 2016);
    }
    
    /**
     * Test exception
     */
    @Test(expected=NoSuchElementException.class)
    public void testNoSuchElementException1() {
        DateTime may282015 = new DateTime(2015,5,28,0,0);
        System.out.println("may282015 : " + may282015);
        DateTime may282016 = new DateTime(2016,5,28,0,0);
        System.out.println("may282016 : " + may282016);
        TimeSeries timeSeries = new TimeSeries(may282015.toDate(), may282016.toDate());
        
        TimeSeries.Yearly yearly = new TimeSeries.Yearly();
        YearlyWeekday yearlyWeekday = new YearlyWeekday();
        yearlyWeekday.setMonth(Month.JUNE);
        yearlyWeekday.setWeekOfMonth(WeekOfMonth.THREE);
        yearlyWeekday.setWeekday(Weekday.WEDNESDAY);
        yearly.setYearlyWeekday(yearlyWeekday);
        timeSeries.setYearly(yearly);
        
        YearlyWeekdayIterator yearlyWeekdayIterator = new YearlyWeekdayIterator(timeSeries);
        yearlyWeekdayIterator.next();
        yearlyWeekdayIterator.next(); //This should throw NoSuchElementException
    }
}