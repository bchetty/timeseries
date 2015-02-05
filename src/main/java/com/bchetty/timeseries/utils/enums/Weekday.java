package com.bchetty.timeseries.utils.enums;

import org.joda.time.DateTimeConstants;

/**
 *
 * @author Babji, Chetty
 */
public enum Weekday {
    MONDAY(DateTimeConstants.MONDAY),
    TUESDAY(DateTimeConstants.TUESDAY),
    WEDNESDAY(DateTimeConstants.WEDNESDAY),
    THURSDAY(DateTimeConstants.THURSDAY),
    FRIDAY(DateTimeConstants.FRIDAY),
    SATURDAY(DateTimeConstants.SATURDAY),
    SUNDAY(DateTimeConstants.SUNDAY);
    
    private final int weekday;
    
    Weekday(int weekday) {
        this.weekday = weekday;
    }
    
    public int getWeekday() {
        return weekday;
    }
}
