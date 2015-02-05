package com.bchetty.timeseries.iterators;

import com.bchetty.timeseries.TimeSeries;
import com.bchetty.timeseries.TimeSeries.Daily;
import java.util.Date;
import java.util.NoSuchElementException;
import org.joda.time.DateTime;

/**
 *
 * @author Babji, Chetty
 */
public class DailyIterator implements TimeSeriesIterator {
    private DateTime endDateTime;
    private DateTime cursor;
    private int increment;
    
    public DailyIterator(TimeSeries timeSeries) {
        if(timeSeries != null && timeSeries.getBeginDate() != null && timeSeries.getEndDate() != null) {
            this.cursor = new DateTime(timeSeries.getBeginDate());
            this.endDateTime = new DateTime(timeSeries.getEndDate());
            Daily daily = timeSeries.getDaily();
            this.increment = daily.getIncrement();
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public boolean hasNext() {
        return !cursor.isAfter(endDateTime);
    }

    @Override
    public Date next() {
        if(this.hasNext()) {
            Date currentDate = cursor.toDate();
            cursor = cursor.plusDays(increment);
            return currentDate;
        }
        
        throw new NoSuchElementException();
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
