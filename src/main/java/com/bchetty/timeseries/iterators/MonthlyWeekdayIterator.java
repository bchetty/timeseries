package com.bchetty.timeseries.iterators;

import com.bchetty.timeseries.TimeSeries;
import com.bchetty.timeseries.TimeSeries.Monthly.MonthlyWeekday;
import java.util.Date;
import java.util.NoSuchElementException;
import org.joda.time.DateTime;

/**
 *
 * @author Babji, Chetty
 */
public class MonthlyWeekdayIterator implements TimeSeriesIterator {
    private final DateTime endDateTime;
    private DateTime currentDateTime;
    private int monthIncrement;    
    
    public MonthlyWeekdayIterator(TimeSeries timeSeries) {
        if(timeSeries != null && timeSeries.getBeginDate() != null && timeSeries.getEndDate() != null) {            
            this.currentDateTime = new DateTime(timeSeries.getBeginDate());
            this.endDateTime = new DateTime(timeSeries.getEndDate());
            TimeSeries.Monthly monthly = timeSeries.getMonthly();
            if(monthly != null) {
                MonthlyWeekday monthlyWeekday = monthly.getMonthlyWeekday();
                if(monthlyWeekday != null) {
                    this.monthIncrement = monthlyWeekday.getIncrement();                    
                    currentDateTime = currentDateTime.withDayOfWeek(monthlyWeekday.getWeekday().ordinal());
                    DateTime startDateTime = new DateTime(timeSeries.getBeginDate());
                    if(currentDateTime.isBefore(startDateTime)) {
                        currentDateTime = currentDateTime.plusMonths(monthIncrement);
                    }
                }
            }            
        } else {
            throw new IllegalStateException();
        }
    }
    
    @Override
    public boolean hasNext() {
        return !currentDateTime.isAfter(endDateTime);
    }

    @Override
    public Date next() {
        if(this.hasNext()) {
            Date currentDate = currentDateTime.toDate();
            currentDateTime = currentDateTime.plusMonths(monthIncrement);
            return currentDate;
        }
        
        throw new NoSuchElementException();
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
