package com.zf.util;

import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间工具类
 */
public class Times {
    private Times() {
        throw new UnsupportedOperationException();
    }

    /**
     * yyyy-MM-dd 的时间格式
     */
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * yyyy-MM-dd HH:mm:ss 的时间格式
     */
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将一个时间格式化为字符串
     * @param date 被格式化的时间
     * @param pattern 格式化时使用的格式
     * @return 格式化结果
     */
    @NonNull
    public static String format(@NonNull Date date, @NonNull String pattern) {
        DateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    /**
     * 用 yyyy-MM-dd 的格式将一个时间格式化为字符串
     * @param date 被格式化的时间
     * @return 格式化结果
     */
    @NonNull
    public static String format(@NonNull Date date) {
        return format(date, DATE_PATTERN);
    }

    @NotNull
    public static Date format(@NotNull String date) throws ParseException {
        DateFormat formatter = new SimpleDateFormat(DATETIME_PATTERN);
        return formatter.parse(date);
    }
    @NotNull
    public static Date formatShort(@NotNull String date) throws ParseException {
        DateFormat formatter = new SimpleDateFormat(DATE_PATTERN);
        return formatter.parse(date);
    }

    @NotNull
    public static Date paresDate(@NotNull String date) throws ParseException {
        DateFormat formatter = new SimpleDateFormat(DATETIME_PATTERN);
        return formatter.parse(date);
    }

    /**
     * 获取当天的开始时间
     * @return
     */
    public static String getCurStart(int dayNum) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH,dayNum);

        //一天的开始时间 yyyy:MM:dd 00:00:00
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        Date dayStart = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(dayStart);
    }

    /**
     * 获取当天的结束
     * @return
     */
    public static String getCurEnd(int dayNum) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH,dayNum);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //一天的结束时间 yyyy:MM:dd 23:59:59
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,999);
        Date dayEnd = calendar.getTime();
        return simpleDateFormat.format(dayEnd);
    }

    /**
     * 获取上一个月的今天
     */
    public static Date getPreMonth() throws ParseException {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MONTH,-1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return formatShort(simpleDateFormat.format(calendar.getTime()));
    }
}
