package com.test;

import com.xoa.util.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Atest{

    private static SimpleDateFormat wyq1 = new SimpleDateFormat(
            "HH:mm:ss");

    public static void main(String[] args) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String str = "09:26:55";//sdf.format(date);

        System.out.println(str);


        String re_time = null;
        Date d;
        try {
            d = wyq1.parse(str);
            System.out.println(d);
            long l = d.getTime();
            String str1 = String.valueOf(l);
            re_time = str1;
            System.out.println(re_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
