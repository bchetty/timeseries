package com.bchetty.timeseries.utils.enums;

import org.joda.time.DateTimeConstants;

/**
 *
 * @author Babji, Chetty
 */
public enum Month {
    JANUARY(DateTimeConstants.JANUARY),
    FEBRUARY(DateTimeConstants.FEBRUARY),
    MARCH(DateTimeConstants.MARCH),
    APRIL(DateTimeConstants.APRIL),
    MAY(DateTimeConstants.MAY),
    JUNE(DateTimeConstants.JUNE),
    JULY(DateTimeConstants.JULY),
    AUGUST(DateTimeConstants.AUGUST),
    SEPTEMBER(DateTimeConstants.SEPTEMBER),
    OCTOBER(DateTimeConstants.OCTOBER),
    NOVEMBER(DateTimeConstants.NOVEMBER),
    DECEMBER(DateTimeConstants.DECEMBER);
    
    private final int month;
    
    Month(int month) {
        this.month = month;
    }
    
    public int getMonth() {
        return month;
    }
}