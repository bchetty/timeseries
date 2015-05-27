package com.bchetty.timeseries.iterators;

import com.bchetty.timeseries.TimeSeries;
import com.bchetty.timeseries.TimeSeries.Monthly.MonthlyWeekday;
import com.bchetty.timeseries.utils.enums.Month;
import com.bchetty.timeseries.utils.misc.DateTimeUtils;
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
    private MonthlyWeekday monthlyWeekday;
    private int monthIncrement;
    
    public MonthlyWeekdayIterator(TimeSeries timeSeries) {
        if(timeSeries != null && timeSeries.getBeginDate() != null && timeSeries.getEndDate() != null) {            
            this.currentDateTime = new DateTime(timeSeries.getBeginDate());
            this.endDateTime = new DateTime(timeSeries.getEndDate());
            TimeSeries.Monthly monthly = timeSeries.getMonthly();
            if(monthly != null) {
                monthlyWeekday = monthly.getMonthlyWeekday();
                if(monthlyWeekday != null) {
                    monthIncrement = monthlyWeekday.getIncrement();
                    Month month = Month.values()[currentDateTime.getMonthOfYear()];
                    currentDateTime = DateTimeUtils.getNthWeekdayOfMonth(currentDateTime.getYear(), month, 
                            monthlyWeekday.getWeekday(), monthlyWeekday.getWeekOfMonth());
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
            Month month = Month.values()[currentDateTime.getMonthOfYear() + monthIncrement];
            currentDateTime = DateTimeUtils.getNthWeekdayOfMonth(currentDateTime.getYear(), month, 
                    monthlyWeekday.getWeekday(), monthlyWeekday.getWeekOfMonth());
            return currentDate;
        }
        
        throw new NoSuchElementException();
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
