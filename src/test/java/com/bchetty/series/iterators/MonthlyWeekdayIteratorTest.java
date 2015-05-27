package com.bchetty.series.iterators;

import com.bchetty.timeseries.TimeSeries;
import com.bchetty.timeseries.TimeSeries.Monthly;
import com.bchetty.timeseries.TimeSeries.Monthly.MonthlyWeekday;
import com.bchetty.timeseries.iterators.MonthlyWeekdayIterator;
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
public class MonthlyWeekdayIteratorTest {
    public MonthlyWeekdayIteratorTest() {}
    
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
        
        MonthlyWeekdayIterator monthlyWeekdayIterator = new MonthlyWeekdayIterator(timeSeries);
        assertTrue(monthlyWeekdayIterator.hasNext());
    }
    
    /**
     * Test of hasNext method, of class MonthlyDayIterator.
     */
    @Test
    public void testNext1() {
        DateTime may282015 = new DateTime(2015,5,28,0,0);
        System.out.println("may282015 : " + may282015);
        DateTime june282015 = new DateTime(2015,6,28,0,0);
        System.out.println("june282015 : " + june282015);
        
        TimeSeries timeSeries = new TimeSeries(may282015.toDate(), june282015.toDate());        
        MonthlyWeekday monthlyWeekday = new MonthlyWeekday();
        monthlyWeekday.setIncrement(1);
        monthlyWeekday.setWeekOfMonth(WeekOfMonth.THREE);
        monthlyWeekday.setWeekday(Weekday.TUESDAY);
        Monthly monthly = new Monthly();
        monthly.setMonthlyWeekday(monthlyWeekday);
        timeSeries.setMonthly(monthly);
        MonthlyWeekdayIterator monthlyWeekdayIterator = new MonthlyWeekdayIterator(timeSeries);
        
        DateTime dateTime1 = new DateTime(monthlyWeekdayIterator.next());
        System.out.println("dateTime1 : " + dateTime1);
        assertTrue(dateTime1.getDayOfWeek() == (Weekday.TUESDAY.ordinal() + 1));
        assertTrue(dateTime1.getDayOfMonth() == 16);
        assertTrue(dateTime1.getMonthOfYear() == 6);
        assertTrue(dateTime1.getYear() == 2015);
    }
    
    /**
     * Test exception
     */
    @Test(expected=NoSuchElementException.class)
    public void testNoSuchElementException1() {
        DateTime may282015 = new DateTime(2015,5,28,0,0);
        System.out.println("may282015 : " + may282015);
        DateTime june282015 = new DateTime(2015,6,28,0,0);
        System.out.println("june282015 : " + june282015);
        
        TimeSeries timeSeries = new TimeSeries(may282015.toDate(), june282015.toDate());        
        MonthlyWeekday monthlyWeekday = new MonthlyWeekday();
        monthlyWeekday.setIncrement(1);
        monthlyWeekday.setWeekOfMonth(WeekOfMonth.THREE);
        monthlyWeekday.setWeekday(Weekday.TUESDAY);
        Monthly monthly = new Monthly();
        monthly.setMonthlyWeekday(monthlyWeekday);
        timeSeries.setMonthly(monthly);
        
        MonthlyWeekdayIterator monthlyWeekdayIterator = new MonthlyWeekdayIterator(timeSeries);        
        monthlyWeekdayIterator.next();
        monthlyWeekdayIterator.next(); //This should throw NoSuchElementException
    }
    
    /**
     * Test of hasNext method, of class MonthlyDayIterator.
     */
    @Test
    public void testNext2() {
        DateTime may282015 = new DateTime(2015,5,28,0,0);
        System.out.println("may282015 : " + may282015);
        DateTime may282016 = new DateTime(2016,5,28,0,0);
        System.out.println("may282016 : " + may282016);
        
        TimeSeries timeSeries = new TimeSeries(may282015.toDate(), may282016.toDate());        
        MonthlyWeekday monthlyWeekday = new MonthlyWeekday();
        monthlyWeekday.setIncrement(1);
        monthlyWeekday.setWeekOfMonth(WeekOfMonth.THREE);
        monthlyWeekday.setWeekday(Weekday.TUESDAY);
        Monthly monthly = new Monthly();
        monthly.setMonthlyWeekday(monthlyWeekday);
        timeSeries.setMonthly(monthly);
        MonthlyWeekdayIterator monthlyWeekdayIterator = new MonthlyWeekdayIterator(timeSeries);
        
        DateTime dateTime1 = new DateTime(monthlyWeekdayIterator.next());
        System.out.println("dateTime1 : " + dateTime1);
        assertTrue(dateTime1.getDayOfWeek() == (Weekday.TUESDAY.ordinal() + 1));
        assertTrue(dateTime1.getDayOfMonth() == 16);
        assertTrue(dateTime1.getMonthOfYear() == 6);
        assertTrue(dateTime1.getYear() == 2015);
        
        DateTime dateTime2 = new DateTime(monthlyWeekdayIterator.next());
        System.out.println("dateTime2 : " + dateTime2);
        assertTrue(dateTime2.getDayOfWeek() == (Weekday.TUESDAY.ordinal() + 1));
        assertTrue(dateTime2.getDayOfMonth() == 21);
        assertTrue(dateTime2.getMonthOfYear() == 7);
        assertTrue(dateTime2.getYear() == 2015);
        
        DateTime dateTime3 = new DateTime(monthlyWeekdayIterator.next());
        System.out.println("dateTime3 : " + dateTime3);
        assertTrue(dateTime3.getDayOfWeek() == (Weekday.TUESDAY.ordinal() + 1));
        assertTrue(dateTime3.getDayOfMonth() == 18);
        assertTrue(dateTime3.getMonthOfYear() == 8);
        assertTrue(dateTime3.getYear() == 2015);
        
        DateTime dateTime4 = new DateTime(monthlyWeekdayIterator.next());
        System.out.println("dateTime4 : " + dateTime4);
        assertTrue(dateTime4.getDayOfWeek() == (Weekday.TUESDAY.ordinal() + 1));
        assertTrue(dateTime4.getDayOfMonth() == 15);
        assertTrue(dateTime4.getMonthOfYear() == 9);
        assertTrue(dateTime4.getYear() == 2015);
        
        DateTime dateTime5 = new DateTime(monthlyWeekdayIterator.next());
        System.out.println("dateTime5 : " + dateTime5);
        assertTrue(dateTime5.getDayOfWeek() == (Weekday.TUESDAY.ordinal() + 1));
        assertTrue(dateTime5.getDayOfMonth() == 20);
        assertTrue(dateTime5.getMonthOfYear() == 10);
        assertTrue(dateTime5.getYear() == 2015);
        
        DateTime dateTime6 = new DateTime(monthlyWeekdayIterator.next());
        System.out.println("dateTime6 : " + dateTime6);
        assertTrue(dateTime6.getDayOfWeek() == (Weekday.TUESDAY.ordinal() + 1));
        assertTrue(dateTime6.getDayOfMonth() == 17);
        assertTrue(dateTime6.getMonthOfYear() == 11);
        assertTrue(dateTime6.getYear() == 2015);
        
        DateTime dateTime7 = new DateTime(monthlyWeekdayIterator.next());
        System.out.println("dateTime7 : " + dateTime7);
        assertTrue(dateTime7.getDayOfWeek() == (Weekday.TUESDAY.ordinal() + 1));
        assertTrue(dateTime7.getDayOfMonth() == 15);
        assertTrue(dateTime7.getMonthOfYear() == 12);
        assertTrue(dateTime7.getYear() == 2015);
        
        DateTime dateTime8 = new DateTime(monthlyWeekdayIterator.next());
        System.out.println("dateTime8 : " + dateTime8);
        assertTrue(dateTime8.getDayOfWeek() == (Weekday.TUESDAY.ordinal() + 1));
        assertTrue(dateTime8.getDayOfMonth() == 19);
        assertTrue(dateTime8.getMonthOfYear() == 1);
        assertTrue(dateTime8.getYear() == 2016);
        
        DateTime dateTime9 = new DateTime(monthlyWeekdayIterator.next());
        System.out.println("dateTime9 : " + dateTime9);
        assertTrue(dateTime9.getDayOfWeek() == (Weekday.TUESDAY.ordinal() + 1));
        assertTrue(dateTime9.getDayOfMonth() == 16);
        assertTrue(dateTime9.getMonthOfYear() == 2);
        assertTrue(dateTime9.getYear() == 2016);
        
        DateTime dateTime10 = new DateTime(monthlyWeekdayIterator.next());
        System.out.println("dateTime10 : " + dateTime10);
        assertTrue(dateTime10.getDayOfWeek() == (Weekday.TUESDAY.ordinal() + 1));
        assertTrue(dateTime10.getDayOfMonth() == 15);
        assertTrue(dateTime10.getMonthOfYear() == 3);
        assertTrue(dateTime10.getYear() == 2016);
        
        DateTime dateTime11 = new DateTime(monthlyWeekdayIterator.next());
        System.out.println("dateTime11 : " + dateTime11);
        assertTrue(dateTime11.getDayOfWeek() == (Weekday.TUESDAY.ordinal() + 1));
        assertTrue(dateTime11.getDayOfMonth() == 19);
        assertTrue(dateTime11.getMonthOfYear() == 4);
        assertTrue(dateTime11.getYear() == 2016);
        
        DateTime dateTime12 = new DateTime(monthlyWeekdayIterator.next());
        System.out.println("dateTime12 : " + dateTime12);
        assertTrue(dateTime12.getDayOfWeek() == (Weekday.TUESDAY.ordinal() + 1));
        assertTrue(dateTime12.getDayOfMonth() == 17);
        assertTrue(dateTime12.getMonthOfYear() == 5);
        assertTrue(dateTime12.getYear() == 2016);
    }
    
    /**
     * Test exception
     */
    @Test(expected=NoSuchElementException.class)
    public void testNoSuchElementException2() {
        DateTime may282015 = new DateTime(2015,5,28,0,0);
        System.out.println("may282015 : " + may282015);
        DateTime may282016 = new DateTime(2016,5,28,0,0);
        System.out.println("may282016 : " + may282016);
        
        TimeSeries timeSeries = new TimeSeries(may282015.toDate(), may282016.toDate());        
        MonthlyWeekday monthlyWeekday = new MonthlyWeekday();
        monthlyWeekday.setIncrement(1);
        monthlyWeekday.setWeekOfMonth(WeekOfMonth.THREE);
        monthlyWeekday.setWeekday(Weekday.TUESDAY);
        Monthly monthly = new Monthly();
        monthly.setMonthlyWeekday(monthlyWeekday);
        timeSeries.setMonthly(monthly);
        MonthlyWeekdayIterator monthlyWeekdayIterator = new MonthlyWeekdayIterator(timeSeries);
        
        monthlyWeekdayIterator.next();
        monthlyWeekdayIterator.next();
        monthlyWeekdayIterator.next();
        monthlyWeekdayIterator.next();
        monthlyWeekdayIterator.next();
        monthlyWeekdayIterator.next();
        monthlyWeekdayIterator.next();
        monthlyWeekdayIterator.next();
        monthlyWeekdayIterator.next();
        monthlyWeekdayIterator.next();
        monthlyWeekdayIterator.next();
        monthlyWeekdayIterator.next();
        
        monthlyWeekdayIterator.next(); //This should throw NoSuchElementException
    }
}