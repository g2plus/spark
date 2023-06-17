package com.ruoyi.common.test;

import com.ruoyi.common.utils.DateUtils;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DateUtilsTest {

    public static void main(String[] args) throws ParseException {
        testGetPeriod();
    }


    /**
     * 测试获取n天之后的日期 clander 线程不安全 如何解决使用多个calendar对象 或者使用ThreadLocal本地线程处理
     */
    public static void testGetDateStr() {
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String day = DateUtils.getDateStr(1);
                    System.out.println(day);
                }
            });
            thread.start();
        }
    }


    /**
     * 测试获取当天的日期 创建了过个SimpleDateFormat对象
     */
    public static void testGetCurrentDate() {
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String day = DateUtils.getDate();
                    System.out.println(day);
                }
            });
            thread.start();
        }
    }

    /**
     * 测试获取几天后的日期
     */
    public static void testGetDate() {
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Date date = DateUtils.getDate(1);
                    System.out.println(date);
                }
            });
            thread.start();
        }
    }

    /**
     * 时间格式校验
     */
    public static void testValidateDateTimeFormat() {
        String dateTimeString = "2023-06-17 12:34:116";
        String formatPattern = "yyyy-MM-dd HH:mm:ss";
        boolean isValidFormat = DateUtils.validateDateTimeFormat(dateTimeString, formatPattern);

        if (isValidFormat) {
            System.out.println("时间格式校验通过");
        } else {
            System.out.println("时间格式校验不通过");
        }
    }

    public static void testConvertToDateTime() {
        String timeString = "2023-06-17T18:46:50+08:00";
        LocalDateTime localDateTime = DateUtils.convertToDateTime(timeString);
        if (localDateTime != null) {
            System.out.println("转换结果：" + localDateTime);
        } else {
            System.out.println("转换失败");
        }
    }

    public static void testToDate() {
        String timeString = "2023-06-17T18:46:50+08:00";
        LocalDateTime localDateTime = DateUtils.convertToDateTime(timeString);
        Date date = DateUtils.toDate(localDateTime);
        System.out.println(date);
    }


    public static void testGetMonthsBetween() {
        String startDateString = "2022-01-11";
        String endDateString = "2025-12-31";
        List<String> months = DateUtils.calculateMonthsBetweenDates(startDateString, endDateString);
        for (String month : months) {
            System.out.println(month);
        }
    }

    public static void testGetYearsBetween() {
        String startDateString = "2022-01-11";
        String endDateString = "2025-12-31";
        List<Integer> years = DateUtils.calculateYearsBetweenDates(startDateString, endDateString);
        for (int year : years) {
            System.out.println(year);
        }
    }

    public static void testCalculateDatesBetween() {
        String startDateString = "2022-01-20";
        String endDateString = "2022-02-21";
        List<String> dates = DateUtils.calculateDatesBetween(startDateString, endDateString);
        for (String date : dates) {
            System.out.println(date);
        }
    }

    public static void getWeeks() throws ParseException {
        String startDateString = "2022-01-20";
        String endDateString = "2022-02-21";
        List<String> weeks = DateUtils.getWeeks(startDateString, endDateString);
        for (String week : weeks) {
            System.out.println(week);
        }
    }


    public static void getYearWeek() {
        String startDateString = "2022-01-20";
        String endDateString = "2022-02-21";
        List<String> weeks = DateUtils.calculateWeeksBetweenDates(startDateString, endDateString);
        for (String week : weeks) {
            System.out.println(week);
        }
    }

    public static void getWeekNumberOfMonth() {
        String dateString = "2022-06-17";
        int weekNumber = DateUtils.calculateWeekNumberOfMonth(dateString);
        System.out.println("2022-06-17 is week number: " + weekNumber);
    }


    public static void getWeekNumberOfYear() {
        String dateString = "2022-06-17";
        int weekNumber = DateUtils.calculateWeekNumberOfYear(dateString);
        System.out.println("2022-06-17 is week number: " + weekNumber);
    }


    public static void getPrevousNDays1() {
        int n = 5; // 你想要获取的天数

        String currentDate = "2023-06-17";

        List<String> dateList = DateUtils.getPreviousNDays(currentDate, n);

        System.out.println("Previous " + n + " days:");
        for (String date : dateList) {
            System.out.println(date);
        }
    }


    public static void getPrevousNDays2() {
        int n = 5; // 你想要获取的天数

        Date currentDate = new Date();

        List<String> dateList = DateUtils.getPreviousNDays(currentDate, n);

        System.out.println("Previous " + n + " days:");
        for (String date : dateList) {
            System.out.println(date);
        }
    }


    public static void getPrevousNDays3() {
        int n = 5; // 你想要获取的天数

        List<String> dateList = DateUtils.getPreviousNDays(LocalDate.now(), n);

        System.out.println("Previous " + n + " days:");
        for (String date : dateList) {
            System.out.println(date);
        }
    }


    public static void getPreviousNMonths() {
        String currentMonth = "2023-06"; // 当前月份
        int n = 5; // 前n个月

        List<String> monthList = DateUtils.getPreviousNMonths(currentMonth, n);

        System.out.println("Previous " + n + " months:");
        for (String month : monthList) {
            System.out.println(month);
        }
    }

    public static void getPreviousNYears() {
        int currentYear = 2023; // 当前年份
        int n = 5; // 前n个年份

        List<String> yearList = DateUtils.getPreviousNYears(currentYear, n);

        System.out.println("Previous " + n + " years:");
        for (String year : yearList) {
            System.out.println(year);
        }
    }


    public static void getMonthsGap() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse("2022-01-11");
        Date date2 = sdf.parse("2023-01-31");

        int monthDifference = DateUtils.getMonthDifference(date1, date2);

        System.out.println("Month difference: " + monthDifference);
    }

    public static void caculateContractBeginAndEnd() throws ParseException {
        Map<String, Map<String, Date>> beginAndEnd = DateUtils.getBeginAndEnd("2023-01-05", "2025-01-04");
        Set<String> months = beginAndEnd.keySet();
        for (String month : months) {
            System.out.println(beginAndEnd.get(month));
        }
    }

    public static void caculateContractBeginAndEnd2() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse("2023-01-05");
        Date date2 = sdf.parse("2025-01-04");
        Map<String, Map<String, Date>> beginAndEnd = DateUtils.getBeginAndEnd(date1, date2, 1);
        Set<String> months = beginAndEnd.keySet();
        for (String month : months) {
            System.out.println(beginAndEnd.get(month));
        }
    }


    public static void testGetPeriod() {

        LocalDateTime startDateTime = LocalDateTime.of(2023, 1, 5, 0, 0, 0);

        LocalDateTime endDateTime = LocalDateTime.of(2053, 1, 4, 23, 59, 59);

        Map<String, Map<String, Date>> period = DateUtils.getPeriod(startDateTime,endDateTime,1);

        Set<String> monthKeys = period.keySet();

        for (String monthKey : monthKeys) {
            Map<String, Date> stringDateMap = period.get(monthKey);
            System.out.println(stringDateMap);
        }
    }

}
