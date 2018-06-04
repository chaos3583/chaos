package com.chaos.date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.lang3.time.DateUtils;

/**
 * 日期工具类
 *
 */
public class DateTimes {
	
	  private static AtomicReference<DateTimeProvider> PROVIDER = new AtomicReference<DateTimeProvider>();
	    static {
	        PROVIDER.set(new DefaultDateTimeProvider());
	    }
	
    private static final int MIN_YEAR = 0;
    private static final int MAX_YEAR = 9999;
    private static final int MIN_MONTH = 0;
    private static final int MAX_MONTH = 11;
    private static final int MIN_DAY_OF_MONTH = 1;
    private static final int MAX_DAY_OF_MONTH = 31;
    private static final int DAYS_OF_MONTH[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static String[] PATTERNS = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm","yyyyMMddHHmmssSSS" };
    
    
    /**
	 * 获取当前日期 格式为YYYYMMDD
	 * @return java.lang.String
	 */
	public static String getCurrentDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String s = df.format(new Date());
		return s;
	}
	/**
	 * 获取当前日期及时间 格式为YYYYMMDDHHmmss
	 * @return java.lang.String
	 */
	public static String getCurrentDateTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = df.format(new Date());
		return s;
	}

	/**
	 * 获取当前日期中的年
	 * @return java.lang.String
	 */
	public static String getCurrentYear() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		return df.format(new Date());
	}
   
	/**
	 * 获取当前日期中的日
	 * @return java.lang.String
	 */
	public static String getCurrentDay() {
		String day;
		SimpleDateFormat df = new SimpleDateFormat("d");
		day = df.format(new Date());
		return day;
	}

	/**
	 * 获取当前日期中的月
	 * @return java.lang.String
	 */
	public static String getCurrentMonth() {
		String month;
		SimpleDateFormat df = new SimpleDateFormat("M");
		month = df.format(new Date());
		return month;
	}

	/**
	 * 获取当前时间 格式为YYYY-MM-DD HH:MM:SS
	 * @return java.lang.String
	 */
	public static String getCurrentTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}
   
   
   
   
    
    /**
     * 日期格式化:date类型转换成想要的日期类型的格式
     * @param pattern: yyyy-MM-dd
     * return 格式：2017-06-13
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(false);//非严格解析日期
        return sdf.format(date);
    }
    
  
    
    /**
     * 日期格式化:长整型转换成想要的日期类型的格式
     * @param pattern 1497319472261 实际使用的时候要写成1497319472261L
     * return 格式：2017-06-13
     */
    public static String format(long date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(false);
        return sdf.format(new Date(date));
    }
    

    /**
     * 日期格式化: 长字符串(年月日时分秒)转换成日期格式(yyyy-MM-dd HH:mm:ss)
     * @param pattern ：20170613120101
     * return 格式：2017-06-13 12:01:01
     */
    public static String dateFormat(String dateStr) throws ParseException{
		  Date date = new SimpleDateFormat("yyyyMMddHHmmss").parse(dateStr); 
		  String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		  return nowDate;
	}
    
    
    
    /**
     * 日期格式化: 字符串(年-月-日)转换成日期格式(年月日)
     * @param pattern ：2017-06-13
     * return 格式：20170613
     */
    public static String dateFormatStr(String dateStr) throws ParseException{
  	  if(null!=dateStr &&!"".equals(dateStr)){
  		  Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr); 
  		  return new SimpleDateFormat("yyyyMMdd").format(date);
  	  }
  	  return dateStr;
	}
    
    
    /**
     * 日期格式化: 字符串(年月日)转换成日期格式(年-月-日)
     * @param pattern ：20170613
     * return 格式：2017-06-13
     */
    public static String dateFormatStrConver(String dateStr) throws ParseException{
	  if(null!=dateStr &&!"".equals(dateStr)){
		  Date date = new SimpleDateFormat("yyyyMMdd").parse(dateStr); 
		  return new SimpleDateFormat("yyyy-MM-dd").format(date);
	  }
	  return dateStr;
	}
  
    
    /**
     * 日期格式化: 字符串(年月)转换成日期格式(年-月)
     * @param pattern ：201706
     * return 格式：2017-06
     */
    public static String dateFormatStrConverMonth(String dateStr) throws ParseException{
  	  if(null!=dateStr &&!"".equals(dateStr)){
  		  Date date = new SimpleDateFormat("yyyyMM").parse(dateStr); 
  		  return new SimpleDateFormat("yyyy-MM").format(date);
  	  }
  	  return dateStr;
	}
 
     
    /**
     * 日期格式化：字符串转换成date
     * @param pattern
     * @param source
     * @return
     */
    public static Date parse(String pattern, String source) {
      try {
          SimpleDateFormat sdf = new SimpleDateFormat(pattern);
          sdf.setLenient(false);
          return sdf.parse(source);
      } catch(Exception e) {
          throw new IllegalArgumentException("failed to parse " + source + " by pattern: " + pattern, e);
      }
    }



    /**
     * 日期格式化
     * @param source
     * @return
     */
    public static Date parse(Object source) {

      if (source == null){
          return null;
      }
      try {
          return DateUtils.parseDate(source.toString(), PATTERNS);
      } catch (ParseException e) {
      	e.printStackTrace();
          return null;
      }
    }

  /**
   *验证是否是周末(星期六或者星期日)
   */
  public static boolean isWeekend(Date date) {
      final int dayOfWeek = getDayOfWeek(date);
      return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
  }

  /**
   * 验证是否是闰年
   * @param date
   * @return
   */
  public static boolean isLeapYear(Date date) {
      return isLeapYear(getYear(date));
  }

  public static boolean isLeapYear(int year) {
      return new GregorianCalendar().isLeapYear(year);
  }

  /**
   * 验证是否是同一天
   * @param d1
   * @param d2
   * @return
   */
  public static boolean isSameDay(Date d1, Date d2) {
      assertNotNull(d1, "invalid parameter d1");
      assertNotNull(d2, "invalid parameter d2");
      return date(d1).compareTo(date(d2)) == 0;
  }

  /**
   * 验证输入的年月日是否合法
   * @param year
   * @param month
   * @param dayOfMonth
   * @return
   */
  public static boolean isValidDate(int year, int month, int dayOfMonth) {
      if (year < MIN_YEAR || year > MAX_YEAR) {
          return false;
      }
      if (month < MIN_MONTH || month > MAX_MONTH) {
          return false;
      }
      if (dayOfMonth < MIN_DAY_OF_MONTH || dayOfMonth > MAX_DAY_OF_MONTH) {
          return false;
      }

      if (month == 1) {
          if (isLeapYear(year)) {
              if(dayOfMonth > 29) {
                  return false;
              }
          } else {
              if(dayOfMonth > 28) {
                  return false;
              }
          }
      } else {
          if(dayOfMonth > DAYS_OF_MONTH[month]) {
              return false;
          }
      }
      Date gregorianChange = new GregorianCalendar().getGregorianChange();
      final int gcYear = DateTimes.getYear(gregorianChange); // 1582
      final int gcMonth = DateTimes.getMonth(gregorianChange); // Calendar.OCTOBER
      if(year == gcYear && month == gcMonth && (dayOfMonth >= 5 && dayOfMonth <= 14)) {
          return false;
      }
      return true;
  }
  
    
    /**
     * 获取上个月的第一天(年月日)
     * @return格式：20170501
     */
    public static String getPreviousMonthFirst(){
    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	 Calendar calendar = Calendar.getInstance();
		 calendar.add(Calendar.MONTH, -1);
		 calendar.set(Calendar.DAY_OF_MONTH, 1);
	     return sdf.format(calendar.getTime());
    }
    
    /**
     * 获取上个月的最后一天(年月日)
     * @return格式：20170501
     */
    public static String getPreviousMonthEnd(){
    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	 Calendar calendar = Calendar.getInstance();
	     calendar.set(Calendar.DAY_OF_MONTH, 1); 
	     calendar.add(Calendar.DATE, -1);
	     return sdf.format(calendar.getTime());
	    
    }
    
    
    /**
     * 获取某年某月的最后一天
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year,int month)
	{
		Calendar cal = Calendar.getInstance();
		//设置年份
		cal.set(Calendar.YEAR,year);
		//设置月份
		cal.set(Calendar.MONTH, month-1);
		//获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		//格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String lastDayOfMonth = sdf.format(cal.getTime());
		return lastDayOfMonth;
	}


    public static Date date(Date date) {
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }
 
    
    /**
     * 根据传入的日期获取年月日
     * @param date
     * @return 格式：2017-06-13
     */
    public static java.sql.Date toSqlDate(Date date) {
        if(date == null) {
            return null;
        } else {
            return new java.sql.Date(date.getTime());
        }
    }


    /**
     * 根据传入的日期获取年月日时分秒毫秒         
     * @param date
     * @return 格式：2017-06-13 11:33:13.232
     */
    public static java.sql.Timestamp toTimestamp(Date date) {
        if(date == null) {
            return null;
        } else {
            return new java.sql.Timestamp(date.getTime());
        }
    }

    /**
     * long转换成date
     * @param milliSeconds:1497319472261L
     * @return 格式: Tue Jun 13 10:04:32 CST 2017

     */
    public static Date valueOf(long milliSeconds) {
        return new Date(milliSeconds);
    }

    /**
     * 根据传入的年月日获取日期
     * @param year
     * @param month
     * @param dayOfMonth
     * @return 格式：Mon Jun 12 00:00:00 CST 2017
     */
    public static Date valueOf(int year, int month, int dayOfMonth) {
        
        if(!isValidDate(year, month, dayOfMonth)) {
            throw new IllegalArgumentException("invalid parameters, year: " + year + ", month: " + month + ", dayOfMonth: " + dayOfMonth);
        }
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取年
     * @param date:1497319472261L
     * @return
     */
    public static int getYear(long date) {
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        return c.get(Calendar.YEAR);
    }

    
    /**
     * 获取年
     * @param date:1497319472261L
     * @return
     */
    public static int getYear(Date date) {
        assertNotNull(date, "invalid parameter date");
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    
    /**
     * 获取月份
     * @param date
     * @return
     */
    public static int getMonth(long date) {
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        return c.get(Calendar.MONTH);
    }

    public static int getMonth(Date date) {
        assertNotNull(date, "invalid parameter date");
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH);
    }

    
    /**
     * 判断是一月中的第几天
     * @param date
     * @return
     */
    public static int getDayOfMonth(long date) {
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static int getDayOfMonth(Date date) {
        assertNotNull(date, "invalid parameter date");
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    
    /**
     * 判断是一年中的第几周
     * @param date
     * @return
     */
    public static int getWeekOfYear(long date) {
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    public static int getWeekOfYear(Date date) {
        assertNotNull(date, "invalid parameter date");
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }


    
    /**
     * 判断是一周中的第几天
     * @param date
     * @return
     */
    public static int getDayOfWeek(long date) {
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public static int getDayOfWeek(Date date) {
        assertNotNull(date, "invalid parameter date");
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    
    /**
     * 判断是一天中的第几个小时
     * @param date
     * @return
     */
    public static int getHourOfDay(long date) {
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static int getHourOfDay(Date date) {
        assertNotNull(date, "invalid parameter date");
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    
    /**
     * 获取分钟
     * @param date
     * @return
     */
    public static int getMinute(long date) {
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        return c.get(Calendar.MINUTE);
    }

    public static int getMinute(Date date) {
        assertNotNull(date, "invalid parameter date");
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    
    /**
     * 获取秒
     * @param date
     * @return
     */
    public static int getSecond(long date) {
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        return c.get(Calendar.SECOND);
    }

    public static int getSecond(Date date) {
        assertNotNull(date, "invalid parameter date");
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.SECOND);
    }

    
    /**
     * 获取毫秒
     * @param date
     * @return
     */
    public static int getMilliSecond(long date) {
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        return c.get(Calendar.MILLISECOND);
    }

    public static int getMilliSecond(Date date) {
        assertNotNull(date, "invalid parameter date");
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MILLISECOND);
    }

    
    /**
     * 获取月份中的最后一天
     * @param date
     * @return
     */
    public static int getLastDayOfMonth(long date) {
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static int getLastDayOfMonth(Date date) {
        assertNotNull(date, "invalid parameter date");
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

   
   /**
    * 获取一周中的最后一天
    * @param date
    * @return
    */
    public static int getLastDayOfWeek(long date) {
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        return c.getActualMaximum(Calendar.DAY_OF_WEEK);
    }

    public static int getLastDayOfWeek(Date date) {
        assertNotNull(date, "invalid parameter date");
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getActualMaximum(Calendar.DAY_OF_WEEK);
    }

  

    
    /**
     * 年份累加
     * @param date
     * @param delta
     * @return
     */
    public static Date addYear(long date, int delta) {
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        c.add(Calendar.YEAR, delta);
        return c.getTime();
    }

    public static Date addYear(Date date, int delta) {
        assertNotNull(date, "invalid parameter date");
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, delta);
        return c.getTime();
    }

    
    /**
     * 月份累加
     * @param date
     * @param delta
     * @return
     */
    public static Date addMonth(long date, int delta) {
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        c.add(Calendar.MONTH, delta);
        return c.getTime();
    }

    public static Date addMonth(Date date, int delta) {
        assertNotNull(date, "invalid parameter date");
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, delta);
        return c.getTime();
    }
    
    public static interface DateTimeProvider {
        Date now();
    }
    
    public static class DefaultDateTimeProvider implements DateTimeProvider {
        @Override
        public Date now() {
            return new Date();
        }
    }
    
    private static void assertNotNull(Date date, String message) {
        if(date == null) {
            throw new IllegalArgumentException(message);
        }
    }
    
    
    /**
     * 日累加
     * @param date
     * @param delta
     * @return
     */
    public static Date addDayOfMonth(long date, int delta) {
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        c.add(Calendar.DAY_OF_MONTH, delta);
        return c.getTime();
    }

    public static Date addDayOfMonth(Date date, int delta) {
        assertNotNull(date, "invalid parameter date");
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, delta);
        return c.getTime();
    }

    
    
    /**
     * 周累加
     * @param date
     * @param delta
     * @return
     */
    public static Date addWeekOfYear(long date, int delta) {
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        c.add(Calendar.WEEK_OF_YEAR, delta);
        return c.getTime();
    }

    public static Date addWeekOfYear(Date date, int delta) {
        assertNotNull(date, "invalid parameter date");
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.WEEK_OF_YEAR, delta);
        return c.getTime();
    }

    
    /**
     * 小时累加
     * @param date
     * @param delta
     * @return
     */
    public static Date addHourOfDay(long date, int delta) {
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        c.add(Calendar.HOUR_OF_DAY, delta);
        return c.getTime();
    }

    public static Date addHourOfDay(Date date, int delta) {
        assertNotNull(date, "invalid parameter date");
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, delta);
        return c.getTime();
    }

    
    /**
     * 分钟累加
     * @param date
     * @param delta
     * @return
     */
    public static Date addMinute(long date, int delta) {
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        c.add(Calendar.MINUTE, delta);
        return c.getTime();
    }

    public static Date addMinute(Date date, int delta) {
        assertNotNull(date, "invalid parameter date");
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, delta);
        return c.getTime();
    }

    /**
     * 秒累加
     * @param date
     * @param delta
     * @return
     */
    public static Date addSecond(long date, int delta) {
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        c.add(Calendar.SECOND, delta);
        return c.getTime();
    }

    public static Date addSecond(Date date, int delta) {
        assertNotNull(date, "invalid parameter date");
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, delta);
        return c.getTime();
    }

    /**
     * 毫秒累加
     * @param date
     * @param delta
     * @return
     */
    public static Date addMilliSecond(long date, int delta) {
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        c.add(Calendar.MILLISECOND, delta);
        return c.getTime();
    }

    public static Date addMilliSecond(Date date, int delta) {
        assertNotNull(date, "invalid parameter date");
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MILLISECOND, delta);
        return c.getTime();
    }

    /**
	 * 比较两个日期 返回值为两个日期的差
	 * "2015-11-02","2015-11-03"
	 * @return int
	 * @param sDate1 java.lang.String
	 * @param sDate2 java.lang.String
	 */
	public static long compareDate(String sDate1, String sDate2) {
		DateFormat dateFormat = DateFormat.getDateInstance();
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = dateFormat.parse(sDate1);
			date2 = dateFormat.parse(sDate2);
		} catch (ParseException e) {
			System.err.println("Application log:Catch Exception in compareDate()");
			System.err.println("sDate1:" + sDate1);
			System.err.println("sDate2:" + sDate2);
			System.err.println(e.getMessage());
			e.printStackTrace();
			return 0;
		}

		long dif = 0;
		long lDate2 = date2.getTime();
		long lDate1 = date1.getTime();

		dif = (lDate2 - lDate1) / 1000 / 60 / 60 / 24;
		return dif;
	}

    
	/**
	 * 获取与当前日期相差若干天的日期
	 * @return java.lang.String
	 * @param dif int
	 */
	public static String getDate(int dif) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, dif);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-M-d");
		String s = df.format(calendar.getTime());
		return s;
	}

	/**
	 * 获取与当前日期相差若干天的日期
	 * @return java.lang.String
	 * @param dif int
	 */
	public static String getdifDate(int dif) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, dif);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String s = df.format(calendar.getTime());
		return s;
	}
	
	/**
	 * 获取与指定日期相差若干天的日期
	 * @return java.lang.String
	 * @param aDate java.lang.String
	 * @param dif int
	 */
	public static String getDate(String aDate, int dif) {
		DateFormat dateFormat = DateFormat.getDateInstance();
		Date date = null;
		try {
			date = dateFormat.parse(aDate);
		} catch (ParseException e) {
			System.err.println("Application log:Catch Exception in getDate()");
			System.err.println("aDate:" + aDate);
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, dif);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String s = df.format(calendar.getTime());
		return s;
	}

	/**
	 * 获取与指定日期相差若干天的日期
	 * @return java.lang.String
	 * @param aDate java.lang.String
	 * @param dif int
	 */
	public static String getDatenew(String aDate, int dif) {
		DateFormat dateFormat = DateFormat.getDateInstance();
		Date date = null;
		try {
			date = dateFormat.parse(aDate);
		} catch (ParseException e) {
			System.err.println("Application log:Catch Exception in getDate()");
			System.err.println("aDate:" + aDate);
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, dif);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-M-d");
		String s = df.format(calendar.getTime());
		return s;
	}
	
	/**
	 * 获取指定日期一个月前的一天
	 * @param aDate java.lang.String
	 * @return java.lang.String
	 */
	public static String getDateBeforeAMonth(String aDate) {
		DateFormat dateFormat = DateFormat.getDateInstance();
		java.util.Date date1 = null;
		try {
			date1 = dateFormat.parse(aDate);
		} catch (ParseException e) {
			System.err.println("Application log:Catch Exception in getDateBeforeAMonth(String)");
			System.err.println("aDate:" + aDate);
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		calendar.add(Calendar.MONTH, -1);
		calendar.add(Calendar.DATE, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String s = df.format(calendar.getTime());
		return s;
	}
	//修改增加方法用于assistant.schedule--------begin------------->
	/**
	 * 获取指定日期下个月的同一天
	 * @param aDate String
	 * @return java.lang.String
	 */
	public static String getDateAfterMonth(String aDate) {
		DateFormat dateFormat = DateFormat.getDateInstance();
		java.util.Date date1 = null;
		try {
			date1 = dateFormat.parse(aDate);
		} catch (ParseException e) {
			System.err.println("Application log:Catch Exception in getDateBeforeAMonth(String)");
			System.err.println("aDate:" + aDate);
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		calendar.add(Calendar.MONTH, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String s = df.format(calendar.getTime());
		return s;
	}

	/**
	    * 获取指定日期下个月的某一天
	    * @param aDate String
	    * @param n int
	    * @return java.util.Date
	    */
	public static String getDateAfterMonth(String aDate, int n) {
		DateFormat dateFormat = DateFormat.getDateInstance();
		java.util.Date date1 = null;
		try {
			date1 = dateFormat.parse(aDate);
		} catch (ParseException e) {
			System.err.println("Application log:Catch Exception in getDateBeforeAMonth(String)");
			System.err.println("aDate:" + aDate);
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, n);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String s = df.format(calendar.getTime());
		return s;
	}
	/**
	  * 获取指定日期所在月的最后一天
	  * @param selectDate String
	  * @return int
	  */
	public static int getLastDate(String selectDate) {
		// Validate
		int dates = 0;
		int year;
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(DateFormat.getDateInstance().parse(selectDate));
		} catch (ParseException e) {
		}
		year = calendar.get(Calendar.YEAR);
		System.out.println(calendar.get(Calendar.MONTH));
		switch (calendar.get(Calendar.MONTH) + 1) {
			case 1 :
				dates = 31;
				break;
			case 2 :
				if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
					dates = 29;
				} else {
					dates = 28;
				}
				break;
			case 3 :
				dates = 31;
				break;
			case 4 :
				dates = 30;
				break;
			case 5 :
				dates = 31;
				break;
			case 6 :
				dates = 30;
				break;
			case 7 :
				dates = 31;
				break;
			case 8 :
				dates = 31;
				break;
			case 9 :
				dates = 30;
				break;
			case 10 :
				dates = 31;
				break;
			case 11 :
				dates = 30;
				break;
			case 12 :
				dates = 31;
				break;
		}
		return dates;
	}
	//修改增加方法用于assisitant.schedule---------end---------->
	/**
	 * 获取当前日期后的一天
	 * @return java.lang.String
	 */
	public static String getNextDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-M-d");
		String s = df.format(calendar.getTime());
		return s;
	}

	/**
	 * 获取当前日期前的一天
	 * @return java.lang.String
	 */
	public static String getPreviousDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String s = df.format(calendar.getTime());
		return s;
	}
	
	/**
	 * 获取三个月前的日期
	 * @return java.lang.String
	 */
	public String getThreeMonthBeforeTime() {
		Date dNow = new Date();   //当前时间  
		Date dBefore = new Date();  
        Calendar calendar = Calendar.getInstance(); //得到日历  
        calendar.setTime(dNow);//把当前时间赋给日历  
        calendar.add(Calendar.MONTH, -3);  //设置为前3月  
        dBefore = calendar.getTime();   //得到前3月的时间  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式  
        String defaultStartDate = sdf.format(dBefore);    //格式化前3月的时间  
        return defaultStartDate;
	}
}
