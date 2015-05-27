package com.bchetty.timeseries.iterators;

import com.bchetty.timeseries.TimeSeries;
import com.bchetty.timeseries.TimeSeries.Yearly.YearlyDay;
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
                    currentDateTime = currentDateTime.withMonthOfYear(yearlyDay.getMonth().getMonth())
                                                     .withDayOfMonth(yearlyDay.getDay());
                    DateTime startDateTime = new DateTime(timeSeries.getBeginDate());
                    if(currentDateTime.isBefore(startDateTime)) {
                        currentDateTime = currentDateTime.plusYears(yearIncrement);
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
            currentDateTime = currentDateTime.plusYears(yearIncrement);
            return currentDate;
        }
        
        throw new NoSuchElementException();
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
