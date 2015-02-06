package com.bchetty.series.iterators;

import com.bchetty.timeseries.iterators.TimeSeriesIterator;
import com.bchetty.timeseries.TimeSeries;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
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
public class DailyIteratorTest {
    public DailyIteratorTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}

    /**
     * Test TimeSeriesIterator Not Null
     */
    @Test
    public void testIteratorNotNull() {
        DateTime now = DateTime.now();
        DateTime fiveDaysLater = now.plusDays(5);
        TimeSeries timeSeries = new TimeSeries(now.toDate(), fiveDaysLater.toDate());
        timeSeries.setPattern(TimeSeries.Pattern.DAILY);
        
        TimeSeriesIterator timeSeriesIterator = timeSeries.iterator();
        assertNotNull(timeSeriesIterator);
    }
    
    /**
     * Test TimeSeriesIterator 'next' method functionality
     */
    @Test
    public void testNext() {
        DateTime now = DateTime.now();
        DateTime fiveDaysLater = now.plusDays(5);
        TimeSeries timeSeries = new TimeSeries(now.toDate(), fiveDaysLater.toDate());
        timeSeries.setPattern(TimeSeries.Pattern.DAILY);
        
        TimeSeriesIterator timeSeriesIterator = timeSeries.iterator();
        Date next1 = timeSeriesIterator.next();
        System.out.println("Now : " + now + "Five Days Later : " + fiveDaysLater + "Next1 Date : " + next1);
        assertEquals(next1, now.toDate());
        Date next2 = timeSeriesIterator.next();
        System.out.println("Next2 Date : " + next2);
        assertEquals(next2, now.plusDays(1).toDate());
    }
    
    /**
     * Test TimeSeriesIterator 'next' method functionality, in a loop
     */
    @Test
    public void testLoop() {
        DateTime now = DateTime.now();
        DateTime fiveDaysLater = now.plusDays(5);
        TimeSeries timeSeries = new TimeSeries(now.toDate(), fiveDaysLater.toDate());
        timeSeries.setPattern(TimeSeries.Pattern.DAILY);
        
        TimeSeriesIterator timeSeriesIterator = timeSeries.iterator();
        int index = 0;
        while(timeSeriesIterator.hasNext()) {
            System.out.println("Index : " + index++ + " Next : " + timeSeriesIterator.next());            
        }
        assertEquals(index, 6);
    }
}
