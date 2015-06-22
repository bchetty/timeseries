package com.bchetty.timeseries.utils.misc;

import com.bchetty.timeseries.utils.enums.Month;
import com.bchetty.timeseries.utils.enums.WeekOfMonth;
import com.bchetty.timeseries.utils.enums.Weekday;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

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
    
    /**
     * Merge Date-Object wth only date information, with a Date-Object with only
     * time information.
     *
     * @param date
     * @param time
     * @return
     */
    public static Date mergeDateTime(Date date, Date time) {
        Calendar timeCal = Calendar.getInstance();
        timeCal.setTime(time);

        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(date);

        copyTime(timeCal, dateCal);
        return dateCal.getTime();
    }
    
    /**
     * Copy Time (HH:mm:ss:ms) from 'fromCal' to 'toCal'.
     *
     * @param fromCal
     * @param toCal
     */
    public static void copyTime(Calendar fromCal, Calendar toCal) {
        toCal.set(Calendar.HOUR_OF_DAY, fromCal.get(Calendar.HOUR_OF_DAY));
        toCal.set(Calendar.MINUTE, fromCal.get(Calendar.MINUTE));
        toCal.set(Calendar.SECOND, fromCal.get(Calendar.SECOND));
        toCal.set(Calendar.MILLISECOND, fromCal.get(Calendar.MILLISECOND));
    }
    
    /**
     *
     * @param inputDate
     * @return
     */
    public static Date getStartOfDay(Date inputDate) {
        DateTime dateTime = new DateTime(inputDate);
        LocalDate localDate = dateTime.toLocalDate();
        DateTime dateWithJustDayInfo = localDate.toDateTimeAtStartOfDay(dateTime.getZone());

        return dateWithJustDayInfo.toDate();
    }
    
    /**
     * 
     * @param day
     * @param monthIncrement
     * @return 
     */
    public static DateTime getMonthWithDay(DateTime beginDateTime, int day, int monthIncrement) {
        if(day > 31) {
            return null;
        }
        
        DateTime temp = beginDateTime;
        while(true) {            
            int lastDayOfMonth = temp.dayOfMonth().withMaximumValue().getDayOfMonth();
            if(lastDayOfMonth >= day) {
                return temp.withDayOfMonth(day);
            } else {
                temp = temp.plusMonths(monthIncrement);
                continue;
            }
        }
    }
    
    /**
     * 
     * @param beginDateTime
     * @param month
     * @param day
     * @param yearIncrement
     * @return 
     */
    public static DateTime getYearWithMonthAndDay(DateTime beginDateTime, int month, int day, int yearIncrement) {
        if(day > 31) {
            return null;
        }
        
        //Handle special case --> Month: February and Day: 29
        if(month == 2 && day == 29) {
            DateTime temp = beginDateTime.withMonthOfYear(month);
            for(int i=0;i<4;i++) {                
                int lastDayOfMonth = temp.dayOfMonth().withMaximumValue().getDayOfMonth();
                if(lastDayOfMonth >= day) {
                    return temp.withDayOfMonth(day);
                } else {
                    temp = temp.plusYears(yearIncrement);
                }
            }            
        } else {
            DateTime temp = beginDateTime.withMonthOfYear(month);
            int lastDayOfMonth = temp.dayOfMonth().withMaximumValue().getDayOfMonth();
            if(lastDayOfMonth >= day) {
                return temp.withDayOfMonth(day);
            } else {
                return null;
            }
        }
        
        return null;
    }
}