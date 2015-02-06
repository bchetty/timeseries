package com.bchetty.series.iterators;

import com.bchetty.timeseries.iterators.YearlyDayIterator;
import com.bchetty.timeseries.TimeSeries;
import com.bchetty.timeseries.TimeSeries.Yearly;
import com.bchetty.timeseries.TimeSeries.Yearly.YearlyDay;
import com.bchetty.timeseries.utils.enums.Month;
import java.util.NoSuchElementException;
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
public class YearlyDayIteratorTest {    
    public YearlyDayIteratorTest() {}
    
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
        DateTime febFour2015 = new DateTime(2015,2,4,0,0);
        System.out.println("febFour2015 : " + febFour2015);
        DateTime febFour2016 = new DateTime(2016,2,4,0,0);
        System.out.println("febFour2016 : " + febFour2016);
        TimeSeries timeSeries = new TimeSeries(febFour2015.toDate(), febFour2016.toDate());   
        
        YearlyDayIterator yearlyDayIterator = new YearlyDayIterator(timeSeries);
        assertTrue(yearlyDayIterator.hasNext());
    }

    /**
     * Test of next method, of class MonthlyDayIterator.
     */
    @Test
    public void testNext() {
        DateTime febFour2015 = new DateTime(2015,2,4,0,0);
        System.out.println("febFour2015 : " + febFour2015);
        DateTime febFour2017 = new DateTime(2017,2,4,0,0);
        System.out.println("febFour2017 : " + febFour2017);
        TimeSeries timeSeries = new TimeSeries(febFour2015.toDate(), febFour2017.toDate());
        Yearly yearly = new Yearly();
        YearlyDay yearlyDay = new YearlyDay();
        yearlyDay.setDay(11);
        yearlyDay.setMonth(Month.APRIL);
        yearly.setYearlyDay(yearlyDay);
        timeSeries.setYearly(yearly);
        
        YearlyDayIterator yearlyDayIterator = new YearlyDayIterator(timeSeries);
        DateTime dateTime1 = new DateTime(yearlyDayIterator.next());
        System.out.println("dateTime1 : " + dateTime1);
        assertTrue(dateTime1.getDayOfMonth() == 11);
        assertTrue(dateTime1.getMonthOfYear() == 4);
        assertTrue(dateTime1.getYear() == 2015);
        
        DateTime dateTime2 = new DateTime(yearlyDayIterator.next());
        System.out.println("dateTime2 : " + dateTime2);
        assertTrue(dateTime2.getDayOfMonth() == 11);
        assertTrue(dateTime2.getMonthOfYear() == 4);
        assertTrue(dateTime2.getYear() == 2016);
    }
    
    /**
     * Test exception
     */
    @Test(expected=NoSuchElementException.class)
    public void testNoSuchElementException1() {
        DateTime febFour2015 = new DateTime(2015,2,4,0,0);        
        DateTime febFour2017 = new DateTime(2017,2,4,0,0);        
        TimeSeries timeSeries = new TimeSeries(febFour2015.toDate(), febFour2017.toDate());
        Yearly yearly = new Yearly();
        YearlyDay yearlyDay = new YearlyDay();
        yearlyDay.setDay(11);
        yearlyDay.setMonth(Month.APRIL);
        yearly.setYearlyDay(yearlyDay);
        timeSeries.setYearly(yearly);
        
        YearlyDayIterator yearlyDayIterator = new YearlyDayIterator(timeSeries);
        yearlyDayIterator.next();
        yearlyDayIterator.next();
        
        yearlyDayIterator.next(); //This should throw NoSuchElementException
    }
}