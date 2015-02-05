package com.bchetty.timeseries.model;

import java.util.Date;

/**
 * Date Range Data Model
 * 
 * @author Babji, Chetty
 */
public class DateRange {
    private Date startDate;
    private Date endDate;

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
