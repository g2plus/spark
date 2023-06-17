package com.ruoyi.common.utils;

import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateFormatUtils;

import static java.util.Calendar.DATE;

/**
 * 时间工具类
 *
 * @author ruoyi
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};


    private static ThreadLocal<Calendar> calendarLocal = ThreadLocal.withInitial(() -> Calendar.getInstance());


    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow() {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format) {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date) {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts) {
        try {
            return new SimpleDateFormat(format).parse(ts);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算相差天数
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        return Math.abs((int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24)));
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 增加 LocalDateTime ==> Date
     */
    public static Date toDate(LocalDateTime temporalAccessor) {
        ZonedDateTime zdt = temporalAccessor.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    /**
     * 增加 LocalDate ==> Date
     */
    public static Date toDate(LocalDate temporalAccessor) {
        LocalDateTime localDateTime = LocalDateTime.of(temporalAccessor, LocalTime.of(0, 0, 0));
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }


    /**
     * 将date转换为localDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime convertDateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
        return localDateTime;
    }

    /**
     * 将时间转换为LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate convertDateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        LocalDate localDate = zonedDateTime.toLocalDate();
        return localDate;
    }


    /**
     * 获取n天后的日期
     */
    public static String getDateStr(int days) {
        SimpleDateFormat spformat = new SimpleDateFormat(YYYY_MM_DD);
        Calendar calendar = DateUtils.calendarLocal.get();
        try {
            calendar.setTime(spformat.parse(getDate()));
        } catch (ParseException e) {
            return null;
        }
        calendar.add(DATE, days);
        return spformat.format(calendar.getTime());
    }


    /**
     * 获取n天后的日期
     */
    public static Date getDate(int days) {
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(days);
        Date date = Date.from(futureDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date;
    }


    /**
     * 计算两个时间差,只返回天数
     *
     * @param endDate
     * @param nowDate
     * @return
     */
    public static long getDaysBetween(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return (long) day;
    }


    /**
     * 计算两个时间差,只返回天数
     *
     * @param start
     * @param end
     * @return
     */
    public static long getDaysBetween(LocalDate start, LocalDate end) {
        // 计算日期差异
        long daysDiff = ChronoUnit.DAYS.between(start, end);
        return daysDiff;
    }


    /**
     * 时间校验工具类
     *
     * @param dateTimeString
     * @param formatPattern
     * @return
     */
    public static boolean validateDateTimeFormat(String dateTimeString, String formatPattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);
            formatter.parse(dateTimeString);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    /**
     * iso8601时间转换
     * 2023-06-17T18:46:00+08:00
     *
     * @param timeString
     * @return
     */
    public static LocalDateTime convertToDateTime(String timeString) {
        try {
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(timeString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            ZonedDateTime zonedDateTime = offsetDateTime.atZoneSameInstant(ZoneOffset.UTC);
            return zonedDateTime.toLocalDateTime();
        } catch (Exception e) {
            // 转换失败，返回 null 或者抛出异常，根据实际需求来处理
            return null;
        }
    }

    /**
     * 获取两个时间的所有月份
     *
     * @param startDate yyyy-MM-dd
     * @param endDate   yyyy-MM-dd
     * @return
     */
    public static List<String> calculateMonthsBetweenDates(String startDateString, String endDateString) {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalDate endDate = LocalDate.parse(endDateString);
        List<String> months = new ArrayList<>();
        YearMonth startYearMonth = YearMonth.from(startDate);
        YearMonth endYearMonth = YearMonth.from(endDate);
        while (!startYearMonth.isAfter(endYearMonth)) {
            months.add(startYearMonth.toString());
            startYearMonth = startYearMonth.plusMonths(1);
        }
        return months;
    }

    /**
     * 计算两个日期之间的年份
     *
     * @param startDateString yyyy-MM-dd
     * @param endDateString   yyyy-MM-dd
     * @return
     */
    public static List<Integer> calculateYearsBetweenDates(String startDateString, String endDateString) {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalDate endDate = LocalDate.parse(endDateString);
        List<Integer> years = new ArrayList<>();
        int startYear = startDate.getYear();
        int endYear = endDate.getYear();
        for (int year = startYear; year <= endYear; year++) {
            years.add(year);
        }
        return years;
    }


    /**
     * 计算两个日期之间的所有日期
     *
     * @param startDateString yyyy-MM-dd
     * @param endDateString   yyyy-MM-dd
     * @return
     */
    public static List<String> calculateDatesBetween(String startDateString, String endDateString) {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalDate endDate = LocalDate.parse(endDateString);
        List<String> dates = new ArrayList<>();
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            String dateString = currentDate.format(DateTimeFormatter.ISO_DATE);
            dates.add(dateString);
            currentDate = currentDate.plusDays(1);
        }
        return dates;
    }

    /**
     * 返回两个日期为指定周几的所有日期
     *
     * @param startDateString yyyy-MM-dd
     * @param endDateString   yyyy-MM-dd
     * @param dayOfWeek
     * @return
     */
    public static List<LocalDate> calculateMondaysBetweenDates(String startDateString, String endDateString, int dayNum) {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalDate endDate = LocalDate.parse(endDateString);
        List<LocalDate> days = new ArrayList<>();
        LocalDate currentDate = startDate;
        DayOfWeek dayOfWeek = getDayOfWeek(dayNum);
        while (!currentDate.isAfter(endDate)) {
            if (currentDate.getDayOfWeek() == dayOfWeek) {
                days.add(currentDate);
            }
            currentDate = currentDate.plusDays(1);
        }
        return days;
    }


    public static DayOfWeek getDayOfWeek(int dayOfWeek) {
        switch (dayOfWeek) {
            case 0:
                return DayOfWeek.MONDAY;
            case 1:
                return DayOfWeek.TUESDAY;
            case 2:
                return DayOfWeek.WEDNESDAY;
            case 3:
                return DayOfWeek.THURSDAY;
            case 4:
                return DayOfWeek.FRIDAY;
            case 5:
                return DayOfWeek.SATURDAY;
            case 6:
                return DayOfWeek.SUNDAY;
        }
        return null;
    }


    /**
     * 返回两个日期为指定周几的所有日期
     *
     * @param startDateString yyyy-MM-dd
     * @param endDateString   yyyy-MM-dd
     * @param dayOfWeek
     * @return
     */
    public static List<LocalDate> calculateMondaysBetweenDates(String startDateString, String endDateString, DayOfWeek dayOfWeek) {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalDate endDate = LocalDate.parse(endDateString);
        List<LocalDate> days = new ArrayList<>();
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            if (currentDate.getDayOfWeek() == dayOfWeek) {
                days.add(currentDate);
            }
            currentDate = currentDate.plusDays(1);
        }
        return days;
    }


    /**
     * 计算两个时间内 所有的周数
     *
     * @param start 开始时间 日期格式yyyy-MM-dd
     * @param end   结束时间 日期格式yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static List<String> getWeeks(String start, String end) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sdf.parse(start);
        Date parse2 = sdf.parse(end);
        List<String> dateList = new ArrayList<>();
        Calendar c1 = Calendar.getInstance();
        c1.setTime(parse);
        //转为周一
        int weekYear = c1.get(Calendar.YEAR);
        int weekOfYear = c1.get(Calendar.WEEK_OF_YEAR);
        c1.setWeekDate(weekYear, weekOfYear, Calendar.MONDAY);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(parse2);
        int weekYear2 = c2.get(Calendar.YEAR);
        int weekOfYear2 = c2.get(Calendar.WEEK_OF_YEAR);
        c2.setWeekDate(weekYear2, weekOfYear2, Calendar.SUNDAY);
        while (true) {
            int weekNum = c1.get(Calendar.WEEK_OF_YEAR);
            dateList.add(c1.getWeekYear() + "-" + (weekNum > 9 ? weekNum : "0" + weekNum));
            if (c1.getTimeInMillis() >= c2.getTimeInMillis()) {
                break;
            }
            c1.setTimeInMillis(c1.getTimeInMillis() + 1000 * 60 * 60 * 24 * 7);
        }
        return dateList;
    }


    /**
     * 计算两个时间内 所有的周数 yyyy-w
     *
     * @param startDateString yyyy-MM-dd
     * @param endDateString   yyyy-MM-dd
     * @return
     */
    public static List<String> calculateWeeksBetweenDates(String startDateString, String endDateString) {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalDate endDate = LocalDate.parse(endDateString);
        List<String> weeks = new ArrayList<>();
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            int weekNumber = currentDate.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
            String weekString = String.format("%04d-%02d", currentDate.getYear(), weekNumber);
            weeks.add(weekString);
            currentDate = currentDate.plusWeeks(1);
        }
        if (endDate.getDayOfWeek() == DayOfWeek.MONDAY) {
            int weekNumber = endDate.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
            String weekString = String.format("%04d-%02d", endDate.getYear(), weekNumber);
            weeks.add(weekString);
        }
        return weeks;
    }


    /**
     * 计算日期是当前月的第几周
     *
     * @param dateString yyyy-MM-dd
     * @return
     */
    public static int calculateWeekNumberOfMonth(String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekNumber = date.get(weekFields.weekOfMonth());
        return weekNumber;
    }

    /**
     * 计算日期是本年的第几周
     */
    public static int calculateWeekNumberOfYear(String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
        return weekNumber;
    }


    /**
     * 计算当前日期是周几
     *
     * @param dateString
     * @return
     */
    public static DayOfWeek calculateDayOfWeek(String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek;
    }


    /**
     * 计算两个时间间隔的天数
     *
     * @param begin
     * @param end
     * @return
     */
    public static Long getDaysDiff(LocalDateTime begin, LocalDateTime end) {
        long between = ChronoUnit.DAYS.between(begin, end);
        return between;
    }


    /**
     * 计算两个时间间隔的天数
     *
     * @param begin
     * @param end
     * @return
     */
    public static Long getDaysDiff(Date begin, Date end) {
        long between = Math.abs(end.getTime() - begin.getTime());
        return TimeUnit.DAYS.convert(between, TimeUnit.MILLISECONDS) + 1;
    }


    /***
     * 本月进行结算 不跨月
     * Map<String,Map<String,Date>>
     *     获取每个月的开始时间和结束时间
     *     第一个String参数 格式yyyy-MM)
     *     Map<String,Date>
     *         String “begin” 合同开始时间
     *         String “end” 合同的结束时间
     * @param begin 日期格式 yyyy-MM-dd
     * @param end 日期格式 yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static Map<String, Map<String, Date>> getBeginAndEnd(String begin, String end) throws ParseException {
        List<String> months = calculateMonthsBetweenDates(begin, end);
        Map<String, Map<String, Date>> dateMap = new HashMap<String, Map<String, Date>>(24);
        for (int i = 0; i < months.size(); i++) {
            Map<String, Date> beginEndMap = new HashMap<String, Date>(24);
            String dateStr = months.get(i);
            Date beginDate = null;
            Date endDate = null;
            if (i == 0) {
                beginDate = DateUtils.getBegin(begin, true);
                if (months.size() == 1) {
                    if (begin.equals(end)) {
                        endDate = DateUtils.getEndOfDay(end);
                    }
                    //如果没有超过一个月的时间,合同到期的前1s钟作为结束时间
                    endDate = DateUtils.getEnd(end, true);
                } else {
                    endDate = DateUtils.getEnd(dateStr);
                }
            } else if (i < months.size() - 1) {
                beginDate = DateUtils.getBegin(dateStr);
                endDate = DateUtils.getEnd(dateStr);
            } else {
                beginDate = DateUtils.getBegin(dateStr);
                endDate = DateUtils.getEnd(end, true);
            }
            beginEndMap.put("begin", beginDate);
            beginEndMap.put("end", endDate);
            dateMap.put(dateStr, beginEndMap);
        }
        //根据年月进行排序
        LinkedHashMap<String, Map<String, Date>> sortedDateMap = dateMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal, newVal) -> oldVal, LinkedHashMap::new));
        return sortedDateMap;
    }


    /***
     * 获取某天的结束时间
     * 例如2022-10-29
     * 2022-10-29 23:59;59
     * @param dateStr
     * @return
     */
    public static Date getEndOfDay(String dateStr) throws ParseException {
        Calendar calendar = DateUtils.calendarLocal.get();
        calendar.setTime(DateUtils.parseDate(dateStr, DateUtils.YYYY_MM_DD));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 计算支付的截至日期
     *
     * @param payNum   取值范围1-82
     * @param nowMonth
     * @return
     */
    public static String getDeadLine(int payNum, String nowMonth) {
        //将年月转化为整型
        String[] yearMonth = getYearMonth(nowMonth);
        int yearNum = Integer.parseInt(yearMonth[0]);
        int monthNum = Integer.parseInt(yearMonth[1]);
        //小于29日
        if (payNum <= 28) {
            return nowMonth + "-" + (payNum < 10 ? "0" + payNum : payNum);
        }
        if (payNum >= 29 && payNum <= 31) {
            boolean flag = validateDay(yearNum, monthNum, payNum);
            if (!flag) {
                return getLastDay(yearNum, monthNum);
            } else {
                return nowMonth + "-" + (payNum < 10 ? "0" + payNum : payNum);
            }
        }
        //如果传入的数值为32，则取每月的最后一天
        if (payNum == 32) {
            return getLastDay(yearNum, monthNum);
        }
        //如果传入的数据小于82,表示为次月某天
        if (payNum >= 51 && payNum < 82) {
            payNum -= 50;
            if (monthNum == 12) {
                yearNum += 1;
                return yearNum + "-" + "01" + "-" + (payNum < 10 ? "0" + payNum : payNum);
            } else {
                int nextMonth = monthNum + 1;
                if (nextMonth < 10) {
                    return yearNum + "-" + 0 + nextMonth + "-" + (payNum < 10 ? "0" + payNum : payNum);
                } else {
                    return yearNum + "-" + nextMonth + "-" + (payNum < 10 ? "0" + payNum : payNum);
                }
            }
        }
        //如果输入的数值等于82
        if (payNum == 82) {
            payNum -= 50;
            if (monthNum == 12) {
                return getLastDay(yearNum + 1, 1);
            }
            return getLastDay(yearNum, monthNum + 1);
        }
        return null;
    }


    /**
     * 输入年月，返回当月最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastDay(int year, int month) {
        Calendar calendar = DateUtils.calendarLocal.get();
        //设置年份
        calendar.set(Calendar.YEAR, year);
        //设置月份(中国月份-1)
        calendar.set(Calendar.MONTH, month - 1);
        //获取当前月最小值
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中的月份，当月+1月-1天等于当月最后一天
        calendar.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.YYYY_MM_DD);
        String date = sdf.format(calendar.getTime());
        return date;
    }


    /**
     * 输入年月，返回当月最后一天
     *
     * @param yearMonthString yyyy-MM
     * @return
     */
    public static String getLastDayOfMonth(String yearMonthString) {
        YearMonth yearMonth = YearMonth.parse(yearMonthString);
        int lastDay = yearMonth.lengthOfMonth();
        return yearMonthString + "-" + lastDay;
    }


    /**
     * 以周期的实际天数周期的开始与结束
     * 最后一天也算上 endDayPlus(1)
     *
     * @param startDate
     * @param stopDate
     * @param gap
     * @return
     */
    public static Map<String, Map<String, Date>> getBeginAndEnd(Date startDate, Date stopDate, Integer gap) {
        // 合同开始时间
        LocalDateTime contractStartDateTime = DateUtils.convertDateToLocalDateTime(startDate);
        // 合同结束时间
        LocalDateTime contractEndDateTime = DateUtils.convertDateToLocalDateTime(stopDate);

        LocalDateTime periodStartDateTime = contractStartDateTime;
        LocalDateTime periodEndDateTime = periodStartDateTime.plusMonths(gap).minusSeconds(1);

        Map<String, Map<String, Date>> retMap = new HashMap<>();

        while (periodEndDateTime.isBefore(contractEndDateTime)) {
            retMap = extracted(periodStartDateTime, periodEndDateTime, retMap);
            periodStartDateTime = periodEndDateTime.plusSeconds(1);
            periodEndDateTime = periodStartDateTime.plusMonths(gap).minusSeconds(1);
        }

        retMap = extracted(periodStartDateTime, contractEndDateTime, retMap);

        //根据年月时进行排序，从小到大
        LinkedHashMap<String, Map<String, Date>> sortedDateMap = retMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal, newVal) -> oldVal, LinkedHashMap::new));

        return sortedDateMap;
    }

    private static Map<String, Map<String, Date>> extracted(LocalDateTime periodStartDateTime, LocalDateTime periodEndDateTime, Map<String, Map<String, Date>> retMap) {
        Integer year = periodStartDateTime.getYear();
        Integer month = periodStartDateTime.getMonth().getValue();
        String monthStr = "";
        if (month < 10) {
            monthStr = "0" + month.toString();
        } else {
            monthStr = month.toString();
        }
        String key = year.toString() + "-" + monthStr;
        Map<String, Date> subMap = new HashMap<>();
        subMap.put("begin", DateUtils.toDate(periodStartDateTime));
        subMap.put("end", DateUtils.toDate(periodEndDateTime));
        retMap.put(key, subMap);
        return retMap;
    }

    /**
     * 计算当前日期之后的日期
     *
     * @param endTime
     * @param day
     * @return
     */
    public static Date endDayPlus(Date endTime, int day) {
        LocalDateTime localDateTime = DateUtils.convertDateToLocalDateTime(endTime).plusDays(day).minusSeconds(1);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }


    /**
     * yyyy-MM
     * 对字符串进行切割，返回年和月
     */
    public static String[] getYearMonth(String nowMonth) {
        return nowMonth.split("-");
    }


    /**
     * 检验这个月份的用户输入日期是否合法
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static boolean validateDay(int year, int month, int day) {
        Calendar calendar = DateUtils.calendarLocal.get();
        //设置年份
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int actualMaximum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (day > actualMaximum) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * dateStr 参数格式yyyy-MM
     *
     * @return
     */
    public static Date getBegin(String dateStr) throws ParseException {
        Calendar calendar = DateUtils.calendarLocal.get();
        calendar.setTime(DateUtils.parseDate(dateStr, DateUtils.YYYY_MM));
        calendar.set(Calendar.DATE, 1);
        return calendar.getTime();
    }


    /**
     * dateStr 参数格式yyyy-MM-dd
     * flag 是否以当前日期作为开始
     *
     * @return
     */
    public static Date getBegin(String dateStr, boolean flag) throws ParseException {
        Calendar calendar = DateUtils.calendarLocal.get();
        calendar.setTime(DateUtils.parseDate(dateStr, DateUtils.YYYY_MM_DD));
        if (!flag) {
            calendar.set(Calendar.DATE, 1);
        }
        return calendar.getTime();
    }


    /**
     * dateStr 参数格式yyyy-MM
     *
     * @return
     */
    public static Date getEnd(String dateStr) throws ParseException {
        Calendar calendar = DateUtils.calendarLocal.get();
        calendar.setTime(DateUtils.parseDate(dateStr, DateUtils.YYYY_MM));
        //月份+1
        calendar.add(Calendar.MONTH, 1);
        //获取上个月的一天
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }


    /**
     * dateStr 参数格式yyyy-MM-dd
     * flag:是否以当前日期作为结束日期
     *
     * @return
     */
    public static Date getEnd(String dateStr, boolean flag) throws ParseException {
        Calendar calendar = DateUtils.calendarLocal.get();
        calendar.setTime(DateUtils.parseDate(dateStr, DateUtils.YYYY_MM_DD));
        if (!flag) {
            //获取当前日期的前一天的最后日期作为结束时间
            //月份+1
            calendar.add(Calendar.MONTH, 1);
            //获取上个月的一天
            calendar.set(Calendar.DAY_OF_MONTH, 0);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
        } else {
            calendar.add(Calendar.DATE, 0);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
        }
        return calendar.getTime();
    }


    /**
     * 返回n天前及指定日期的所有日期
     *
     * @param currentDate
     * @param n
     * @return
     */
    public static List<String> getPreviousNDays(LocalDate currentDate, int n) {
        List<String> dateList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dateList.add(currentDate.minusDays(i).toString());
        }
        return dateList;
    }


    /**
     * 返回n天前及指定日期的所有日期
     *
     * @param currentDate
     * @param n
     * @return
     */
    public static List<String> getPreviousNDays(Date currentDate, int n) {
        List<String> dateList = new ArrayList<>();

        LocalDate localDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        for (int i = 0; i < n; i++) {
            dateList.add(localDate.minusDays(i).toString());
        }

        return dateList;
    }

    /**
     * 返回n天前及指定日期的所有日期
     *
     * @param currentDate yyyy-MM-dd
     * @param n
     * @return
     */
    public static List<String> getPreviousNDays(String currentDate, int n) {
        List<String> dateList = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(currentDate, formatter);

        for (int i = 0; i < n; i++) {
            dateList.add(localDate.minusDays(i).toString());
        }

        return dateList;
    }


    /**
     * 返回n月前及指定月的所有月份
     *
     * @param yearMonth yyyy-MM
     * @param n
     * @return
     */
    public static List<String> getPreviousNMonths(String currentMonth, int n) {
        List<String> monthList = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        YearMonth currentYearMonth = YearMonth.parse(currentMonth, formatter);

        for (int i = 0; i < n; i++) {
            YearMonth previousMonth = currentYearMonth.minusMonths(i);
            monthList.add(previousMonth.format(formatter));
        }

        return monthList;
    }


    /**
     * 返回n年前及指定年的所有年份
     *
     * @param currentYear
     * @param n
     * @return
     */
    public static List<String> getPreviousNYears(int currentYear, int n) {
        List<String> yearList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int previousYear = currentYear - i;
            yearList.add(Integer.toString(previousYear));
        }

        return yearList;
    }


    /**
     * 计算日期之间间隔个月
     *
     * @param begin yyyy-MM-dd
     * @param end   yyyy-MM-dd
     * @return
     */
    public static int getMonthsBetween(String begin, String end) {
        List<String> list = calculateMonthsBetweenDates(begin, end);
        return list.size();
    }

    /**
     * 计算日期之间间隔个月
     *
     * @param begin
     * @param end
     * @return
     */
    public static int getMonthDifference(Date start, Date end) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(start);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(end);

        int yearDiff = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
        int monthDiff = cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);

        int totalMonthDiff = yearDiff * 12 + monthDiff;

        if (cal2.get(Calendar.DAY_OF_MONTH) < cal1.get(Calendar.DAY_OF_MONTH)) {
            totalMonthDiff--;
        }

        return totalMonthDiff;
    }


    /**
     * 计算每个周期的开始的的开始与结束时间
     *
     * @param startDateTime
     * @param endDateTime
     * @param secondsInMonth
     * @return
     */
    public static Map<String, Map<String, Date>> getPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime, int gap) {

        long periodDays = 30 * gap;

        LocalDateTime periodStartDateTime = startDateTime;

        LocalDateTime periodEndDateTime = periodStartDateTime.plusDays(periodDays).minusSeconds(1);

        Map<String, Map<String, Date>> retMap = new HashMap<>();

        while (periodEndDateTime.isBefore(endDateTime)) {
            retMap = extracted(periodStartDateTime, periodEndDateTime, retMap);
            periodStartDateTime = periodEndDateTime.plusSeconds(1);
            periodEndDateTime = periodStartDateTime.plusDays(periodDays).minusSeconds(1);
        }

        retMap = extracted(periodStartDateTime, endDateTime, retMap);

        //根据年月时进行排序，从小到大
        LinkedHashMap<String, Map<String, Date>> sortedDateMap = retMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal, newVal) -> oldVal, LinkedHashMap::new));

        return sortedDateMap;
    }


    /**
     * 使用场景:判断是否租用满一个月
     * 参数说明,begin,end为一个月份内的两个时间
     * 将日期转换为yyyy-MM-dd格式，然后判断间隔是否超过本月的实际天数
     *
     * @param begin
     * @param end
     * @return
     */
    public static Map<String, Object> isOneMonth(Date begin, Date end) {
        Map<String, Object> map = new HashMap<String, Object>();
        Integer.parseInt(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, begin).substring(8, 10));
        Integer beginNum = Integer.parseInt(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, begin).substring(8, 10));
        Integer endNum = Integer.parseInt(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, end).substring(8, 10));
        Integer used = endNum - beginNum + 1;
        Calendar calendar = DateUtils.calendarLocal.get();
        calendar.setTime(begin);
        Integer days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (used < days) {
            map.put("isOneMonth", false);
            map.put("used", used);
            map.put("days", days);
        } else {
            map.put("isOneMonth", true);
            map.put("used", used);
            map.put("days", days);
        }
        return map;
    }


    /**
     * @param begin
     * @param end
     * @return
     */
    public static Map<String, Object> isOnePeriod(Date begin, Date end, Integer gap) {
        LocalDateTime periodStart = DateUtils.convertDateToLocalDateTime(begin);
        LocalDateTime periodEnd = DateUtils.convertDateToLocalDateTime(end);
        LocalDateTime periodActualEnd = periodStart.plusMonths(gap).minusDays(1);
        Map<String, Object> map = new HashMap<>();
        //把当天的时间也算上一天
        if (periodEnd.isBefore(periodActualEnd)) {
            map.put("isOnePeriod", false);
            map.put("used", getDaysDiff(periodStart, periodEnd) + 1);
            map.put("days", getDaysDiff(periodStart, periodActualEnd) + 1);
            return map;
        } else {
            map.put("isOnePeriod", true);
            map.put("used", getDaysDiff(periodStart, periodEnd) + 1);
            map.put("days", getDaysDiff(periodStart, periodActualEnd) + 1);
            return map;
        }
    }


}
