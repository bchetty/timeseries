package com.bchetty.series.iterators;

import com.bchetty.timeseries.iterators.MonthlyDayIterator;
import com.bchetty.timeseries.TimeSeries;
import com.bchetty.timeseries.TimeSeries.Monthly;
import com.bchetty.timeseries.TimeSeries.Monthly.MonthlyDay;
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
public class MonthlyDayIteratorTest {    
    public MonthlyDayIteratorTest() {}
    
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
        DateTime marchFour2015 = new DateTime(2015,3,4,0,0);
        System.out.println("febFifteen2015 : " + marchFour2015);
        TimeSeries timeSeries = new TimeSeries(febFour2015.toDate(), marchFour2015.toDate());   
        
        MonthlyDayIterator monthlyDayIterator = new MonthlyDayIterator(timeSeries);
        assertTrue(monthlyDayIterator.hasNext());
    }

    /**
     * Test of next method, of class MonthlyDayIterator.
     */
    @Test
    public void testNext() {
        DateTime febFour2015 = new DateTime(2015,2,4,0,0);
        System.out.println("febFour2015 : " + febFour2015);
        DateTime aprilFour2015 = new DateTime(2015,4,4,0,0);
        System.out.println("febFifteen2015 : " + aprilFour2015);
        TimeSeries timeSeries = new TimeSeries(febFour2015.toDate(), aprilFour2015.toDate());
        Monthly monthly = new Monthly();
        MonthlyDay monthlyDay = new MonthlyDay();
        monthlyDay.setDay(15);
        monthlyDay.setIncrement(1);
        monthly.setMonthlyDay(monthlyDay);
        timeSeries.setMonthly(monthly);
        
        MonthlyDayIterator monthlyDayIterator = new MonthlyDayIterator(timeSeries);
        DateTime dateTime1 = new DateTime(monthlyDayIterator.next());
        System.out.println("dateTime1 : " + dateTime1);
        assertTrue(dateTime1.getDayOfMonth() == 15);
        assertTrue(dateTime1.getMonthOfYear() == 2);
        
        DateTime dateTime2 = new DateTime(monthlyDayIterator.next());
        System.out.println("dateTime2 : " + dateTime2);
        assertTrue(dateTime2.getDayOfMonth() == 15);
        assertTrue(dateTime2.getMonthOfYear() == 3);
    }  
    
    /**
     * Test exception
     */
    @Test(expected=NoSuchElementException.class)
    public void testNoSuchElementException1() {
        DateTime febFour2015 = new DateTime(2015,2,4,0,0);        
        DateTime aprilFour2015 = new DateTime(2015,4,4,0,0);        
        TimeSeries timeSeries = new TimeSeries(febFour2015.toDate(), aprilFour2015.toDate());
        Monthly monthly = new Monthly();
        MonthlyDay monthlyDay = new MonthlyDay();
        monthlyDay.setDay(15);
        monthlyDay.setIncrement(1);
        monthly.setMonthlyDay(monthlyDay);
        timeSeries.setMonthly(monthly);
        
        MonthlyDayIterator monthlyDayIterator = new MonthlyDayIterator(timeSeries);
        monthlyDayIterator.next();
        monthlyDayIterator.next();
        
        monthlyDayIterator.next(); //This should throw NoSuchElementException
    } 
    
    /**
     * Test exception
     */
    @Test(expected=NoSuchElementException.class)
    public void testNoSuchElementException2() {
        DateTime febFour2015 = new DateTime(2015,2,4,0,0);        
        DateTime aprilFour2015 = new DateTime(2015,4,4,0,0);        
        TimeSeries timeSeries = new TimeSeries(febFour2015.toDate(), aprilFour2015.toDate());
        Monthly monthly = new Monthly();
        MonthlyDay monthlyDay = new MonthlyDay();
        monthlyDay.setDay(15);
        monthlyDay.setIncrement(2);
        monthly.setMonthlyDay(monthlyDay);
        timeSeries.setMonthly(monthly);
        
        MonthlyDayIterator monthlyDayIterator = new MonthlyDayIterator(timeSeries);
        monthlyDayIterator.next();
        monthlyDayIterator.next();//This should throw NoSuchElementException
    } 
}