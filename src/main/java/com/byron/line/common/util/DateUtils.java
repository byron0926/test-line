package com.byron.line.common.util;

import com.byron.line.common.enums.DateTypeEnum;
import org.joda.time.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期工具类
 * <p>
 * Created by byron on 2017/5/10.
 */
public final class DateUtils {
    /**
     * 时区：中国标准时间 东八区
     */
    private static String TIMEZONE = "GMT+8";

    /**
     * yyyy-MM
     */
    public static String YYYY_MM = "yyyy-MM";
    public static String YYYY_MM_ = "yyyy年MM月";
    public static String YYYY_MM_1 = "yyyy-MM-1 00:00:00.000";
    public static String MM_ = "MM月";

    /**
     * yyyyMMdd
     */
    public static String YYYYMMDD = "yyyyMMdd";

    /**
     * yyyy-MM-dd
     */
    public static String YYYY_MM_DD = "yyyy-MM-dd";
    public static String YYYY_MM_DD_00 = "yyyy-MM-dd 00:00:00.000";
    public static String YYYY_MM_DD_59 = "yyyy-MM-dd 23:59:59";
    public static String YYYY_MM_DD_ = "yyyy年MM月dd日";
    public static String DD_ = "dd日";
    public static String MM_DD_ = "MM月dd日";

    /**
     * yyyy-MM-dd HH
     */
    public static String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    public static String YYYY_MM_DD_HH_00 = "yyyy-MM-dd HH:00:00.000";
    public static String YYYY_MM_DD_HH_59 = "yyyy-MM-dd HH:59:59.999";
    public static String YYYY_MM_DD_HH_ = "yyyy年MM月dd日 HH";
    public static String YYYY_MM_DD_HH__ = "yyyy年MM月dd日 HH:00:00";
    public static String YYYY_MM_DD_HH___ = "yyyy-MM-dd 00:00:00";
    public static String MM_DD_HH_ = "MM月dd日 HH";
    public static String DD_HH_ = "dd日 HH";
    public static String HH_ = "HH";

    /**
     * yyyyMMdd HH:mm:ss
     */
    public static String YYYYMMDD_HH_MM_SS = "yyyyMMdd HH:mm:ss";

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    /**
     * yyyy-MM-dd HH:mm:ss:sss
     */
    public static String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss:SSS";
    public static String YYYY_MM_DD_HH_MM_SS_SSS_ = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * yyyyMMddHHmmss
     */
    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * 设置时区
     */
    static {
        initTime();
        System.out.println(StringUtils.format("设置时区:[{0}]", TIMEZONE));
    }

    /**
     * string日期转换string类型
     *
     * @param date
     * @param format
     * @return
     */
    public static String stringToString(String date, String format) {
        return dateToString(stringToDate(date, YYYY_MM_DD_HH_MM_SS), format);
    }

    /**
     * date日期转换date类型
     * @param date
     * @param format
     * @return
     */
    public static Date dateToDate(Date date, String format){
        return stringToDate(dateToString(date,format),format);
    }

    /**
     * Date日期转换String类型
     *
     * @param date   目标日期
     * @param format 转换日期格式
     * @return
     */
    public static String dateToString(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * String日期转换Date类型
     *
     * @param date   目标日期String
     * @param format 转换日期格式
     * @return
     */
    public static Date stringToDate(String date, String format) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 增加秒
     * <p>
     * 可为负数，则为减法
     *
     * @param date  目标日期
     * @param sec   秒
     * @return
     */
    public static Date addSec(Date date, int sec){
        return new DateTime(date).plusSeconds(sec).toDate();
    }

    /**
     * 增加分钟
     * <p>
     * 可为负数，则为减法
     *
     * @param date  目标日期
     * @param mins  分钟
     * @return
     */
    public static Date addMin(Date date, int mins){
       return new DateTime(date).plusMinutes(mins).toDate();
    }

    /**
     * 增加小时
     * <p>
     * 可为负数，则为减法
     *
     * @param date  目标日期
     * @param hours 小时
     * @return
     */
    public static Date addHours(Date date, int hours) {
        return new DateTime(date).plusHours(hours).toDate();
    }

    /**
     * 增加天数
     * <p>
     * 可为负数，则为减法
     *
     * @param date 目标日期
     * @param days 天数
     * @return
     */
    public static Date addDay(Date date, int days) {
        return new DateTime(date).plusDays(days).toDate();
    }

    /**
     * 增加月数
     * <p>
     * 可为负数，则为减法
     *
     * @param date   目标日期
     * @param months 天数
     * @return
     */
    public static Date addMonth(Date date, int months) {
        return new DateTime(date).plusMonths(months).toDate();
    }

    /**
     * 增加年数
     * <p>
     * 可为负数，则为减法
     *
     * @param date  目标日期
     * @param years 年数
     * @return
     */
    public static Date addYear(Date date, int years) {
        return new DateTime(date).plusYears(years).toDate();
    }


    /**
     * 计算两个日期的日差值
     *
     * @param date1
     * @param date2
     * @return
     * @describle date1、date2类型支持String、java.util.Date、org.joda.time.DateTime类型
     */
    public static int datesBetweenDays(Object date1, Object date2) {
        DateTime dateTime1 = formatObject(date1), dateTime2 = formatObject(date2);
        if (null != dateTime1 && null != dateTime2) {
            return Math.abs(Days.daysBetween(dateTime1, dateTime2).getDays());
        }
        return -1;
    }

    /**
     * 计算两个日期的月差值
     *
     * @param date1
     * @param date2
     * @return
     * @describle date1、date2类型支持String、java.util.Date、org.joda.time.DateTime类型
     */
    public static int datesBetweenMonths(Object date1, Object date2) {
        DateTime dateTime1 = formatObject(date1), dateTime2 = formatObject(date2);
        if (null != dateTime1 && null != dateTime2) {
            return Math.abs(Months.monthsBetween(dateTime1, dateTime2).getMonths());
        }
        return -1;
    }

    /**
     * 计算两个日期的年差值
     *
     * @param date1
     * @param date2
     * @return
     * @describle date1、date2类型支持String、java.util.Date、org.joda.time.DateTime类型
     */
    public static int datesBetweenYears(Object date1, Object date2) {
        DateTime dateTime1 = formatObject(date1), dateTime2 = formatObject(date2);
        if (null != dateTime1 && null != dateTime2) {
            return Math.abs(Years.yearsBetween(dateTime1, dateTime2).getYears());
        }
        return -1;
    }

    /**
     * 计算两个日期相减得毫秒差值
     *
     * @param d_1
     * @param d_2
     * @param formart
     * @return
     */
    public static long datesBetweenDates(String d_1, String d_2, String formart) {
        if (StringUtils.isEmpty(d_1) || StringUtils.isEmpty(d_2)) {
            return -1;
        }
        long d_1_min = stringToDate(d_1, formart).getTime();
        long d_2_min = stringToDate(d_2, formart).getTime();
        return Math.abs(d_1_min - d_2_min);
    }

    /**
     * 获取当前月第一天
     *
     * @param format 日期转换格式
     * @return String
     */
    public static String getMonthStartDate(String format) {
        return dateToString(getMonthStartDate(), format);
    }

    /**
     * 获取当前月第一天
     *
     * @return Date
     */
    public static Date getMonthStartDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 获取当前周第一天
     *
     * @param format 日期转换格式
     * @return String
     */
    public static String getFirstDayOfWeek(String format) {
        return dateToString(getFirstDayOfWeek(), format);
    }

    /**
     * 获取当前周第一天
     *
     * @return Date
     */
    public static Date getFirstDayOfWeek() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 获取当前季度第一天
     *
     * @param format 日期转换格式
     * @return String
     */
    public static String getFirstDayOfSeason(String format) {
        return dateToString(getFirstDayOfSeason(), format);
    }

    /**
     * 获取当前季度第一天
     *
     * @return Date
     */
    public static Date getFirstDayOfSeason() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        switch (currentMonth) {
            case 1:
            case 2:
            case 3:
                c.set(Calendar.MONTH, 1);
                break;
            case 4:
            case 5:
            case 6:
                c.set(Calendar.MONTH, 3);
                break;
            case 7:
            case 8:
            case 9:
                c.set(Calendar.MONTH, 4);
                break;
            case 10:
            case 11:
            case 12:
                c.set(Calendar.MONTH, 9);
                break;
        }
        c.set(Calendar.DATE, 1);
        return stringToDate(dateToString(c.getTime(), YYYY_MM_DD) + " 00:00:00", YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取本年第一天
     *
     * @param format 日期转换格式
     * @return String
     */
    public static String getFirstDayOfYear(String format) {
        return dateToString(getFirstDayOfYear(), format);
    }

    /**
     * 获取本年第一天
     *
     * @return Date
     */
    public static Date getFirstDayOfYear() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, currentYear);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取当月最后一天日期
     *
     * @param format 日期转换格式
     * @return
     */
    public static Date getLastDayOfMonth(String format) {
        return getLastDayOfMonth(new Date(),format);
    }

    public static Date getLastDayOfMonth(Date date,String format) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return dateToDate(c.getTime(), format);
    }

    /**
     * 获取type下年月日
     * @param type DateTimeFieldType.dayOfMonth()日 DateTimeFieldType.monthOfYear()月 DateTimeFieldType.year()年
     * @param date 日期
     * @param formart 日期格式 date为string时 使用
     * @return
     */
    public static int getFieldValueByType(DateTimeFieldType type, Object date, String formart){
        if (StringUtils.isEmpty(date)){
            return -1;
        }
        DateTime dateTime = null;
        if (date instanceof String){
            dateTime = new DateTime(stringToDate((String) date,formart));
        } else if (date instanceof Date){
            dateTime = new DateTime(date);
        } else if (date instanceof DateTime){
            dateTime = (DateTime) date;
        }
        if (StringUtils.isEmpty(dateTime)){
            return -1;
        }
        return dateTime.get(type);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date getNow() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * 获取月份天数
     * <p>
     * 返回0 参数为空 -1 数据类型不对
     * string类型，必须为yyyy-MM-dd
     *
     * @param object string/date类型
     * @return
     */
    public static int getDaysOfMonth(Object object) {
        if (StringUtils.isEmpty(object)) {
            return 0;
        }
        Date date = null;
        if (object instanceof String) {
            date = stringToDate(String.valueOf(object), DateUtils.YYYY_MM_DD);
        } else if (object instanceof Date) {
            date = (Date) object;
        } else {
            return -1;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


    private static void initTime() {
        TimeZone time = TimeZone.getTimeZone(TIMEZONE);
        TimeZone.setDefault(time);
    }

    private static DateTime formatObject(Object date) {
        DateTime dateTime = null;
        if (date instanceof String) {
            dateTime = new DateTime(stringToDate((String) date, YYYYMMDD_HH_MM_SS));
        } else if (date instanceof Date) {
            dateTime = new DateTime(date);
        } else if (date instanceof DateTime) {
            dateTime = (DateTime) date;
        }
        return dateTime;
    }

    /**
     * 根据时间类型格式化查询日期
     *
     * @description: <p>
     * <b>note:</b>
     *
     * </p>
     */
    public static class FormartQueryTime {
        public static QueryTimeVo formartDateType(DateTypeEnum dateTypeEnum, String startDate, String enDate) {
            QueryTimeVo qtv = null;
            if (StringUtils.isNotEmpty(dateTypeEnum)) {
                qtv = new QueryTimeVo();
                Date now = new Date();
                switch (dateTypeEnum) {
                    case TODAY:
                        qtv.setEndDate(dateToString(now, YYYY_MM_DD_HH_MM_SS));
                        qtv.setStartDate(dateToString(new Date(), YYYY_MM_DD_HH___));
                        break;
                    case YESTERDAY:
                        qtv.setEndDate(dateToString(addDay(new Date(), -1), YYYY_MM_DD_59));
                        qtv.setStartDate(dateToString(addDay(new Date(), -1), YYYY_MM_DD_00));
                        break;
                    case THIS_MONTH:
                        qtv.setEndDate(dateToString(now, YYYY_MM_DD_HH_MM_SS));
                        qtv.setStartDate(dateToString(getMonthStartDate(), YYYY_MM_DD_00));
                        break;
                    case LAST_MONTH:
                        qtv.setEndDate(dateToString(getLastDayOfMonth(addMonth(new Date(),-1),YYYY_MM_DD_HH_MM_SS), YYYY_MM_DD_59));
                        qtv.setStartDate(dateToString(addMonth(now, -1), YYYY_MM_1));
                        break;
                }
            }
            return qtv;
        }
    }

    public static class QueryTimeVo {
        private String startDate;
        private String endDate;

        public QueryTimeVo() {
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        @Override
        public String toString() {
            return "QueryTimeVo{" +
                    "startDate='" + startDate + '\'' +
                    ", endDate='" + endDate + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        System.out.println(FormartQueryTime.formartDateType(DateTypeEnum.TODAY,null,null));
        System.out.println(FormartQueryTime.formartDateType(DateTypeEnum.YESTERDAY,null,null));
        System.out.println(FormartQueryTime.formartDateType(DateTypeEnum.THIS_MONTH,null,null));
        System.out.println(FormartQueryTime.formartDateType(DateTypeEnum.LAST_MONTH,null,null));
    }
}
