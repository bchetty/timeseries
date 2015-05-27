package com.bchetty.timeseries.utils.misc;

import com.bchetty.timeseries.utils.enums.Month;
import com.bchetty.timeseries.utils.enums.WeekOfMonth;
import com.bchetty.timeseries.utils.enums.Weekday;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author Babji, Chetty
 */
public class DateTimeUtils {
    private DateTimeUtils() {}
    
    /**
     * 
     * @param startDate
     * @param endDate
     * @param weekday
     * @return 
     */
    public static List<Date> getAllWeekdaysBetween(Date startDate, Date endDate, Weekday weekday) {
        return getAllWeekdaysBetween(startDate, endDate, weekday, 1);
    }
    
    /**
     * 
     * @param startDate
     * @param endDate
     * @param weekday
     * @param weekIncrement
     * @return 
     */
    public static List<Date> getAllWeekdaysBetween(Date startDate, Date endDate, Weekday weekday, int weekIncrement) {
        List<Date> datesList = null;
        if(startDate != null && endDate != null && weekday != null && weekIncrement != 0) {
            datesList = new ArrayList<Date>();
            
            DateTime startDateTime = new DateTime(startDate);
            DateTime endDateTime = new DateTime(endDate);
            
            DateTime thisWeekday = startDateTime.withDayOfWeek(weekday.getWeekday());
            startDateTime = (startDateTime.isAfter(thisWeekday)) ? thisWeekday.plusWeeks(1) : thisWeekday;
            
            while (startDateTime.isBefore(endDateTime)) {
                datesList.add(startDateTime.toDate());
                startDateTime = startDateTime.plusWeeks(weekIncrement);
            }
        }
        
        return datesList;
    }
    
    public static DateTime getNthWeekdayOfMonth(int year, Month month, Weekday weekday, WeekOfMonth weekOfMonth) {
        DateTime firstDayOfMonthInThatYear = new DateTime().withYear(year).withMonthOfYear(month.ordinal() + 1).withDayOfMonth(1);
        DateTime withWeekday = firstDayOfMonthInThatYear.withDayOfWeek(weekday.ordinal() + 1);
        return withWeekday.isBefore(firstDayOfMonthInThatYear) ? withWeekday.plusWeeks(weekOfMonth.ordinal() + 1) : withWeekday.plusWeeks(weekOfMonth.ordinal());
    }
}
