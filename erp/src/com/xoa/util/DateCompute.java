package com.xoa.util;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by gsb on 2017/6/27.
 */
public class DateCompute {
    /**
     *
     * 创建作者:   王曰岐
     * 创建日期:   2017-6-26 下午7:05:44
     * 方法介绍:   根据当前日期获取星期几
     * 参数说明:   @param dt
     * 参数说明:   @return
     * @return     String
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        //若一周第一天为星期天，则-1
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
         return weekDays[w];
    }
    /**
     *
     * 创建作者:   王曰岐
     * 创建日期:   2017-6-27 下午2:20:25
     * 方法介绍:   获取两个日期之间的所有日期（yyyy-MM-dd）
     * 注释部分：这里是获取两个日期之间的所有日期（不包涵start和end）
     * 参数说明:   @param start
     * 参数说明:   @param end
     * 参数说明:   @return
     * @return     List<Date>
     */
    public   static List<Date> getBetweenDates(Date start, Date end) {
        List<Date> result = new ArrayList<Date>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
           /* Calendar tempEnd = Calendar.getInstance();
           tempStart.add(Calendar.DAY_OF_YEAR, 1);
           tempEnd.setTime(end);
           while (tempStart.before(tempEnd)) {
               result.add(tempStart.getTime());
               tempStart.add(Calendar.DAY_OF_YEAR, 1);
           }*/
        while(start.getTime()<=end.getTime()){
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
            start = tempStart.getTime();
        }
        return result;
    }


    /**
     *
     * 创建作者:   牛江丽
     * 创建日期:   2017-7-12 下午15:19:25
     * 方法介绍:   获取本周日期的区间（周一到周日）
     * @return     String
     */
    public  static String getWeekInterval() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        String imptimeBegin = sdf.format(cal.getTime());
        cal.add(Calendar.DATE, 6);
        String imptimeEnd = sdf.format(cal.getTime());
        return imptimeBegin + "," + imptimeEnd;
    }

    /**
     *
     * 创建作者:   牛江丽
     * 创建日期:   2017-7-12 下午15:26:25
     * 方法介绍:   获取本月日期的区间
     * @return     String
     */
    public  static String getMonthInterval() {
        //获取当前月的日期区间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Date theDate = calendar.getTime();
        // 上个月第一天
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first);
        day_first = str.toString();
        // 上个月最后一天
        calendar.add(Calendar.MONTH, 1); // 加一个月
        calendar.set(Calendar.DATE, 1); // 设置为该月第一天
        calendar.add(Calendar.DATE, -1); // 再减一天即为上个月最后一天
        String day_last = df.format(calendar.getTime());
        StringBuffer endStr = new StringBuffer().append(day_last);
        day_last = endStr.toString();
        return day_first+","+day_last;
    }
    /**
     *
     * 创建作者:   牛江丽
     * 创建日期:   2017-7-19 下午11:58:25
     * 方法介绍:   给定一个日期，获取给定日期所在月的第一天和最后一天
     * @return     String
     */
    public  static String getMonthTime(String datadate) {
        String day_last = null;
        String day_first = null;
        Date date=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 try {
             date = df.parse(datadate);
             //创建日历
             Calendar calendar = Calendar.getInstance();
             calendar.setTime(date);
             calendar.add(Calendar.MONTH, 1);    //加一个月
             calendar.set(Calendar.DATE, 1);     //设置为该月第一天
             calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
             day_last = df.format(calendar.getTime());
             System.out.println("当月最后一天" + day_last);

             date = df.parse(datadate);
             //创建日历
             calendar.setTime(date);
             calendar.set(Calendar.DAY_OF_MONTH, 1);
             day_first = df.format(calendar.getTime());
             System.out.println("当月第一天" + day_first);

         } catch(Exception e){
                 e.printStackTrace();
         }
        return day_first+","+day_last;
    }
}
