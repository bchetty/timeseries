package com.bchetty.timeseries.iterators;

import com.bchetty.timeseries.TimeSeries;
import com.bchetty.timeseries.TimeSeries.Yearly.YearlyDay;
import com.bchetty.timeseries.utils.misc.DateTimeUtils;
import java.util.Date;
import java.util.NoSuchElementException;
import org.joda.time.DateTime;

/**
 *
 * @author Babji, Chetty
 */
public class YearlyDayIterator implements TimeSeriesIterator {
    private final DateTime endDateTime;
    private DateTime currentDateTime;
    private int yearIncrement = 1;
    
    public YearlyDayIterator(TimeSeries timeSeries) {
        if(timeSeries != null && timeSeries.getBeginDate() != null && timeSeries.getEndDate() != null) {
            this.currentDateTime = new DateTime(timeSeries.getBeginDate());
            this.endDateTime = new DateTime(timeSeries.getEndDate());
            TimeSeries.Yearly yearly = timeSeries.getYearly();
            if(yearly != null) {
                YearlyDay yearlyDay = yearly.getYearlyDay();                
                if(yearlyDay != null) {
                    currentDateTime = DateTimeUtils.getYearWithMonthAndDay(currentDateTime, yearlyDay.getMonth().getMonth(), 
                            yearlyDay.getDay(), yearIncrement);                    
                    DateTime startDateTime = new DateTime(timeSeries.getBeginDate());
                    if(currentDateTime != null && currentDateTime.isBefore(startDateTime)) {
                        currentDateTime = DateTimeUtils.getYearWithMonthAndDay(currentDateTime.plusYears(yearIncrement), 
                                currentDateTime.getMonthOfYear(), currentDateTime.getDayOfMonth(), yearIncrement);
                    }
                }
            }            
        } else {
            throw new IllegalStateException();
        }
    }
    
    @Override
    public boolean hasNext() {
        return (currentDateTime != null && !currentDateTime.isAfter(endDateTime));
    }

    @Override
    public Date next() {
        if(this.hasNext()) {
            Date currentDate = currentDateTime.toDate();
            currentDateTime = DateTimeUtils.getYearWithMonthAndDay(currentDateTime.plusYears(yearIncrement), 
                    currentDateTime.getMonthOfYear(), currentDateTime.getDayOfMonth(), yearIncrement);
            return currentDate;
        }
        
        throw new NoSuchElementException();
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
