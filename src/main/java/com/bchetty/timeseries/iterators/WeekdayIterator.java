package com.bchetty.timeseries.iterators;

import com.bchetty.timeseries.utils.enums.Weekday;
import java.util.Date;
import java.util.NoSuchElementException;
import org.joda.time.DateTime;

/**
 *
 * @author Babji, Chetty
 */
public class WeekdayIterator implements TimeSeriesIterator {
    private final DateTime endDateTime;
    private DateTime currentDateTime;
    private int weekIncrement;
    
    public WeekdayIterator(DateTime startDateTime, DateTime endDateTime, Weekday weekday) {
        this(startDateTime, endDateTime, weekday, 1);
    }
    
    public WeekdayIterator(DateTime startDateTime, DateTime endDateTime, Weekday weekday, int weekIncrement) {
        this.endDateTime = endDateTime;
        if(startDateTime != null && weekday != null && weekIncrement != 0) {
            currentDateTime = startDateTime.withDayOfWeek(weekday.getWeekday());
            this.weekIncrement = weekIncrement;
            if(currentDateTime.isBefore(startDateTime)) {
                currentDateTime = currentDateTime.plusWeeks(weekIncrement);
            }
        } else {
            throw new IllegalStateException("Required Params missing");
        }
    }
    
    public WeekdayIterator(Date startDate, Date endDate, Weekday weekday) {
        this((startDate != null) ? new DateTime(startDate) : null, (endDate != null) ? new DateTime(endDate) : null, weekday, 1);
    }
    
    public WeekdayIterator(Date startDate, Date endDate, Weekday weekday, int weekIncrement) {
        this((startDate != null) ? new DateTime(startDate) : null, (endDate != null) ? new DateTime(endDate) : null, weekday, weekIncrement);
    }
    
    @Override
    public boolean hasNext() {
        return !currentDateTime.isAfter(endDateTime);
    }

    @Override
    public Date next() {
        if(this.hasNext()) {
            Date nextDate = currentDateTime.toDate();
            currentDateTime = currentDateTime.plusWeeks(weekIncrement);
            return nextDate;
        }
        
        throw new NoSuchElementException();
    }
}
