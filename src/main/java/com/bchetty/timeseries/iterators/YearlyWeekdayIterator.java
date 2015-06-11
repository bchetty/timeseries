package com.bchetty.timeseries.iterators;

import com.bchetty.timeseries.TimeSeries;
import com.bchetty.timeseries.TimeSeries.Yearly.YearlyWeekday;
import com.bchetty.timeseries.utils.enums.Month;
import com.bchetty.timeseries.utils.misc.DateTimeUtils;
import java.util.Date;
import java.util.NoSuchElementException;
import org.joda.time.DateTime;

/**
 *
 * @author Babji, Chetty
 */
public class YearlyWeekdayIterator implements TimeSeriesIterator {
    private final DateTime endDateTime;
    private DateTime currentDateTime;
    private int yearIncrement = 1;
    private YearlyWeekday yearlyWeekday;

    public YearlyWeekdayIterator(TimeSeries timeSeries) {
        if (timeSeries != null && timeSeries.getBeginDate() != null && timeSeries.getEndDate() != null) {
            this.currentDateTime = new DateTime(timeSeries.getBeginDate());
            this.endDateTime = new DateTime(timeSeries.getEndDate());
            TimeSeries.Yearly yearly = timeSeries.getYearly();
            if (yearly != null) {
                yearlyWeekday = yearly.getYearlyWeekday();
                if (yearlyWeekday != null) {                    
                    currentDateTime = DateTimeUtils.getNthWeekdayOfMonth(currentDateTime.getYear(), yearlyWeekday.getMonth(),
                            yearlyWeekday.getWeekday(), yearlyWeekday.getWeekOfMonth());
                    DateTime startDateTime = new DateTime(timeSeries.getBeginDate());
                    if (currentDateTime.isBefore(startDateTime)) {
                        currentDateTime = DateTimeUtils.getNthWeekdayOfMonth(currentDateTime.getYear() + yearIncrement, yearlyWeekday.getMonth(),
                                yearlyWeekday.getWeekday(), yearlyWeekday.getWeekOfMonth());
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
        if (this.hasNext()) {
            Date currentDate = currentDateTime.toDate();
            currentDateTime = DateTimeUtils.getNthWeekdayOfMonth(currentDateTime.getYear() + yearIncrement, yearlyWeekday.getMonth(),
                    yearlyWeekday.getWeekday(), yearlyWeekday.getWeekOfMonth());
            return currentDate;
        }

        throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
