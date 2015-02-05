package com.bchetty.timeseries;

import com.bchetty.timeseries.iterators.DailyIterator;
import com.bchetty.timeseries.iterators.MonthlyDayIterator;
import com.bchetty.timeseries.iterators.MonthlyWeekdayIterator;
import com.bchetty.timeseries.iterators.TimeSeriesIterator;
import com.bchetty.timeseries.iterators.WeeklyIterator;
import com.bchetty.timeseries.iterators.YearlyDayIterator;
import com.bchetty.timeseries.iterators.YearlyWeekdayIterator;
import com.bchetty.timeseries.utils.enums.IterationType;
import com.bchetty.timeseries.utils.enums.Month;
import com.bchetty.timeseries.utils.enums.WeekOfMonth;
import com.bchetty.timeseries.utils.enums.Weekday;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author Babji, Chetty
 */
public class TimeSeries implements Serializable, Iterable<Date> {
    private static final long serialVersionUID = 4838055888690265412L;
    
    public enum Pattern { DAILY, WEEKLY, MONTHLY, YEARLY; }
    
    private Date beginDate = new Date(); //Default
    private Date endDate;
    private Pattern pattern = Pattern.DAILY; //Default
    private Daily daily;
    private Weekly weekly;
    private Monthly monthly;
    private Yearly yearly;
    
    public TimeSeries(Date beginDate, Date endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        init(beginDate);
    }
    
    public TimeSeries(Date beginDate) {
        this(beginDate, null);
    }
    
    private void init(Date date) {
        this.daily = new Daily();
        if(beginDate != null) {
            this.weekly = new Weekly(date);
            this.monthly = new Monthly(date);
            this.yearly = new Yearly(date);
        }        
    }
    
    @Override
    public TimeSeriesIterator iterator() {
        if(this.pattern != null && this.beginDate != null && this.endDate != null) {
            switch(this.pattern) {
                case DAILY:
                    return new DailyIterator(this);                    
                case WEEKLY:
                    return new WeeklyIterator(this);
                case MONTHLY:
                    if(this.getMonthly() != null && this.getMonthly().getIterationType() != null) {
                        switch(this.getMonthly().getIterationType()) {
                            case DAY:
                                return new MonthlyDayIterator(this);
                            case WEEKDAY:
                                return new MonthlyWeekdayIterator(this);
                        }                        
                    } else {
                        throw new IllegalStateException();                        
                    }                    
                case YEARLY:
                    if(this.getYearly() != null && this.getYearly().getIterationType() != null) {
                        switch(this.getYearly().getIterationType()) {
                            case DAY:
                                return new YearlyDayIterator(this);
                            case WEEKDAY:
                                return new YearlyWeekdayIterator(this);
                        }                        
                    } else {
                        throw new IllegalStateException();                        
                    } 
            }            
        }
        
        throw new IllegalStateException("Required Params missing : Pattern/beginDate/endDate");
    }
    
    /**
     * Static Nested Class for Daily-Iteration functionality
     */
    public static class Daily implements Serializable {
        private static final long serialVersionUID = -4329461819368951911L;
        private int increment = 1;
        public Daily() {}

        /**
         * @return the increment
         */
        public int getIncrement() {
            return increment;
        }

        /**
         * @param increment the increment to set
         */
        public void setIncrement(int increment) {
            this.increment = increment;
        }
    }
    
    /**
     * Static Nested Class for Weekly-Iteration functionality
     */
    public static class Weekly implements Serializable {
        private static final long serialVersionUID = -1882477513621629135L;
        
        private int increment = 1;
        private List<Weekday> weekdays;

        public Weekly() {}
        public Weekly(Date date) {
            if(date != null) {                
                getWeekdays().add(Weekday.values()[(new DateTime(date).getDayOfWeek())-1]);
            }
        }

        /**
         * @return the increment
         */
        public int getIncrement() {
            return increment;
        }

        /**
         * @param increment the increment to set
         */
        public void setIncrement(int increment) {
            this.increment = increment;
        }

        /**
         * @return the weekdays
         */
        public List<Weekday> getWeekdays() {
            if (this.weekdays == null) {
                this.weekdays = new ArrayList<>();
            }
            return weekdays;
        }

        /**
         * @param weekdays the weekdays to set
         */
        public void setWeekdays(List<Weekday> weekdays) {
            this.weekdays = weekdays;
        }
    }
    
    /**
     * Static Nested Class for Monthly-Iteration functionality
     */
    public static class Monthly implements Serializable {
        private static final long serialVersionUID = -8216102401392111952L;
        
        private IterationType iterationType;
        private MonthlyDay monthlyDay;
        private MonthlyWeekday monthlyWeekday;

        public Monthly() {}
        public Monthly(Date date) {
            this.monthlyDay = new MonthlyDay(date);
            this.monthlyWeekday = new MonthlyWeekday(date);
            this.iterationType = IterationType.DAY;
        }
        
        /**
        * Static Nested Class for Monthly-And-Day-Based-Iteration functionality
        */
        public static class MonthlyDay implements Serializable {
            private static final long serialVersionUID = 3342602954058695051L;
            
            private int increment = 1;
            private int day;
            public MonthlyDay() {}
            public MonthlyDay(Date date) {
                if(date != null) {
                    day = new DateTime(date).getDayOfMonth();
                }
            }

            /**
             * @return the increment
             */
            public int getIncrement() {
                return increment;
            }

            /**
             * @param increment the increment to set
             */
            public void setIncrement(int increment) {
                this.increment = increment;
            }

            /**
             * @return the day
             */
            public int getDay() {
                return day;
            }

            /**
             * @param day the day to set
             */
            public void setDay(int day) {
                this.day = day;
            }
        }
        
        /**
        * Static Nested Class for Monthly-And-WeekDay-Based-Iteration functionality
        */
        public static class MonthlyWeekday implements Serializable {
            private static final long serialVersionUID = -296654945421360713L;
            
            private int increment = 1;
            private WeekOfMonth weekOfMonth;
            private Weekday weekday;
            public MonthlyWeekday() {}
            public MonthlyWeekday(Date date) {
                this.weekOfMonth = WeekOfMonth.ONE;
                if(date != null) {
                    this.weekday = Weekday.values()[(new DateTime(date).getDayOfWeek())-1];
                }                
            }
            
            /**
             * @return the increment
             */
            public int getIncrement() {
                return increment;
            }

            /**
             * @param increment the increment to set
             */
            public void setIncrement(int increment) {
                this.increment = increment;
            }

            /**
             * @return the weekOfMonth
             */
            public WeekOfMonth getWeekOfMonth() {
                return weekOfMonth;
            }

            /**
             * @param weekOfMonth the weekOfMonth to set
             */
            public void setWeekOfMonth(WeekOfMonth weekOfMonth) {
                this.weekOfMonth = weekOfMonth;
            }

            /**
             * @return the weekday
             */
            public Weekday getWeekday() {
                return weekday;
            }

            /**
             * @param weekday the weekday to set
             */
            public void setWeekday(Weekday weekday) {
                this.weekday = weekday;
            }
        }
        
        /**
         * @return the iterationType
         */
        public IterationType getIterationType() {
            return iterationType;
        }

        /**
         * @param iterationType the iterationType to set
         */
        public void setIterationType(IterationType iterationType) {
            this.iterationType = iterationType;
        }

        /**
         * @return the monthlyDay
         */
        public MonthlyDay getMonthlyDay() {
            return monthlyDay;
        }

        /**
         * @param monthlyDay the monthlyDay to set
         */
        public void setMonthlyDay(MonthlyDay monthlyDay) {
            this.monthlyDay = monthlyDay;
        }

        /**
         * @return the monthlyWeekday
         */
        public MonthlyWeekday getMonthlyWeekday() {
            return monthlyWeekday;
        }

        /**
         * @param monthlyWeekday the monthlyWeekday to set
         */
        public void setMonthlyWeekday(MonthlyWeekday monthlyWeekday) {
            this.monthlyWeekday = monthlyWeekday;
        }
    }
    
    /**
     * Static Nested Class for Yearly-Iteration functionality
     */
    public static class Yearly implements Serializable {
        private static final long serialVersionUID = -7653819047888805919L;
        
        private IterationType iterationType;
        private YearlyDay yearlyDay;
        private YearlyWeekday yearlyWeekday;

        public Yearly() {}
        public Yearly(Date date) {
            this.yearlyDay = new YearlyDay(date);
            this.yearlyWeekday = new YearlyWeekday(date);
            this.iterationType = IterationType.DAY;
        }
        
        /**
        * Static Nested Class for Yearly-And-Day-Based-Iteration functionality
        */
        public static class YearlyDay implements Serializable {
            private static final long serialVersionUID = 8416233058522714211L;
            
            private int day;
            private Month month;
            
            public YearlyDay() {}
            public YearlyDay(Date date) {
                if(date != null) {
                    DateTime dateTime = new DateTime(date);
                    this.day = dateTime.getDayOfMonth();
                    this.month = Month.values()[dateTime.getMonthOfYear()-1];
                }
            }

            /**
             * @return the day
             */
            public int getDay() {
                return day;
            }

            /**
             * @param day the day to set
             */
            public void setDay(int day) {
                this.day = day;
            }

            /**
             * @return the month
             */
            public Month getMonth() {
                return month;
            }

            /**
             * @param month the month to set
             */
            public void setMonth(Month month) {
                this.month = month;
            }
        }
        
        /**
        * Static Nested Class for Yearly-And-WeekDay-Based-Iteration functionality
        */
        public static class YearlyWeekday implements Serializable{
            private static final long serialVersionUID = 3203913962219743092L;
            
            private WeekOfMonth weekOfMonth;
            private Weekday weekday;
            private Month month;
            
            public YearlyWeekday() {}
            public YearlyWeekday(Date date) {
                if(date != null) {
                    DateTime dateTime = new DateTime(date);
                    this.weekday = Weekday.values()[dateTime.getDayOfWeek()-1];
                    this.month = Month.values()[dateTime.getMonthOfYear()-1];
                    this.weekOfMonth = WeekOfMonth.ONE;
                }
            }

            /**
             * @return the weekOfMonth
             */
            public WeekOfMonth getWeekOfMonth() {
                return weekOfMonth;
            }

            /**
             * @param weekOfMonth the weekOfMonth to set
             */
            public void setWeekOfMonth(WeekOfMonth weekOfMonth) {
                this.weekOfMonth = weekOfMonth;
            }

            /**
             * @return the weekday
             */
            public Weekday getWeekday() {
                return weekday;
            }

            /**
             * @param weekday the weekday to set
             */
            public void setWeekday(Weekday weekday) {
                this.weekday = weekday;
            }

            /**
             * @return the month
             */
            public Month getMonth() {
                return month;
            }

            /**
             * @param month the month to set
             */
            public void setMonth(Month month) {
                this.month = month;
            }
        }
        
        /**
         * @return the iterationType
         */
        public IterationType getIterationType() {
            return iterationType;
        }

        /**
         * @param iterationType the iterationType to set
         */
        public void setIterationType(IterationType iterationType) {
            this.iterationType = iterationType;
        }

        /**
         * @return the yearlyDay
         */
        public YearlyDay getYearlyDay() {
            return yearlyDay;
        }

        /**
         * @param yearlyDay the yearlyDay to set
         */
        public void setYearlyDay(YearlyDay yearlyDay) {
            this.yearlyDay = yearlyDay;
        }

        /**
         * @return the yearlyWeekday
         */
        public YearlyWeekday getYearlyWeekday() {
            return yearlyWeekday;
        }

        /**
         * @param yearlyWeekday the yearlyWeekday to set
         */
        public void setYearlyWeekday(YearlyWeekday yearlyWeekday) {
            this.yearlyWeekday = yearlyWeekday;
        }
    }
    
    /**
     * @return the beginDate
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * @param beginDate the beginDate to set
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
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

    /**
     * @return the pattern
     */
    public Pattern getPattern() {
        return pattern;
    }

    /**
     * @param pattern the pattern to set
     */
    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    /**
     * @return the daily
     */
    public Daily getDaily() {
        return daily;
    }

    /**
     * @param daily the daily to set
     */
    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    /**
     * @return the weekly
     */
    public Weekly getWeekly() {
        return weekly;
    }

    /**
     * @param weekly the weekly to set
     */
    public void setWeekly(Weekly weekly) {
        this.weekly = weekly;
    }

    /**
     * @return the monthly
     */
    public Monthly getMonthly() {
        return monthly;
    }

    /**
     * @param monthly the monthly to set
     */
    public void setMonthly(Monthly monthly) {
        this.monthly = monthly;
    }

    /**
     * @return the yearly
     */
    public Yearly getYearly() {
        return yearly;
    }

    /**
     * @param yearly the yearly to set
     */
    public void setYearly(Yearly yearly) {
        this.yearly = yearly;
    }
}
