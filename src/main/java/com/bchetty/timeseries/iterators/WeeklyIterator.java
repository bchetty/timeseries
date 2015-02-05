package com.bchetty.timeseries.iterators;

import com.bchetty.timeseries.TimeSeries;
import com.bchetty.timeseries.utils.enums.Weekday;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.joda.time.DateTime;

/**
 *
 * @author Babji, Chetty
 */
public class WeeklyIterator implements TimeSeriesIterator {
    private DateTime startDateTime;
    private DateTime endDateTime;   
    private WeekdayIterator currentWeekdayIterator;
    private Iterator<Weekday> weekdaysIterator;
    private int increment;
        
    public WeeklyIterator(TimeSeries timeSeries) {
        if(timeSeries != null && timeSeries.getBeginDate() != null && timeSeries.getEndDate() != null) {
            this.startDateTime = new DateTime(timeSeries.getBeginDate());
            this.endDateTime = new DateTime(timeSeries.getEndDate());
            TimeSeries.Weekly weekly = timeSeries.getWeekly();            
            if(weekly != null) {
                this.increment = weekly.getIncrement();
                List<Weekday> weekdays = weekly.getWeekdays();
                if(weekdays != null && !weekdays.isEmpty() && increment != 0) {
                    weekdaysIterator = weekdays.iterator();
                    DateTime tempStartDateTime = new DateTime(startDateTime.toDate());
                    currentWeekdayIterator = new WeekdayIterator(tempStartDateTime, endDateTime, weekdaysIterator.next(), increment);
                }
            }
        } else {
            throw new IllegalStateException();
        }        
    }
    
    @Override
    public boolean hasNext() {
        if(currentWeekdayIterator != null) {
            if(currentWeekdayIterator.hasNext()) {
                return true;
            } else {
                if(weekdaysIterator != null && weekdaysIterator.hasNext()) {
                    DateTime tempStartDateTime = new DateTime(startDateTime.toDate());
                    currentWeekdayIterator = new WeekdayIterator(tempStartDateTime, endDateTime, weekdaysIterator.next(), increment);
                    return this.hasNext();
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }        
    }

    @Override
    public Date next() {
        if(this.hasNext()) {
            return currentWeekdayIterator.next();
        }
        
        throw new NoSuchElementException();
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
