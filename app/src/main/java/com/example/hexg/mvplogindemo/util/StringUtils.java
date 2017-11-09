package com.example.hexg.mvplogindemo.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringUtils {

    /**
     * 判读字符串是否为空
     *
     * @param inputString
     * @return
     */
    public static boolean isNullOrEmpty(@Nullable String inputString) {
        return null == inputString || inputString.trim().equals("");
    }

    /**
     * 比较两个字符串是否相等
     *
     * @param arg1
     * @param arg2
     * @return
     */
    public static boolean compareEquals(@NonNull String arg1, String arg2) {
        if (StringUtils.isNullOrEmpty(arg1)) {
            return StringUtils.isNullOrEmpty(arg2);
        } else {
            return arg1.equals(arg2);
        }
    }

    @Nullable
    public static String copy(@Nullable String inputString) {
        if (inputString == null) {
            return null;
        }
        return inputString;
    }

    public static String formatDuration(int hour, int min, int sec) {

        StringBuilder strBuider = new StringBuilder();
        if (hour < 10) {
            strBuider.append(0);
        }
        strBuider.append(hour).append(":");
        if (min < 10) {
            strBuider.append(0);
        }
        strBuider.append(min).append(":");
        if (sec < 10) {
            strBuider.append(0);
        }
        strBuider.append(sec);

        return strBuider.toString();
    }

    // 手机校验1，建议使用2
    public static boolean isTelPhoneNumber(@NonNull String phoneNumber) {
        Pattern p = Pattern.compile("^13[0-9][0-9]{8}$|15[0-9][0-9]{8}$|18[0-3,5-9][0-9]{8}$");
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }

    // 手机校验2
    public static boolean isPhoneNo(@NonNull String phoneNo) {
        Pattern p1 = Pattern.compile("^1(3[0-9]|4[5,7]|5[0-3,5-9]|(7[6-8])|8[0-9]|70)\\d{8}$");
        Pattern p = Pattern.compile("^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8]))\\d{8}$");
        Matcher m = p.matcher(phoneNo);
        return m.matches();
    }

    // 去除空格
    public static String trimSpace(@NonNull String str) {
        return str.trim().replace("\\s*", "");
    }

    // 设置过滤字符函数(过滤掉我们不需要的字符)
    public static String stringFilter(@NonNull String str) throws PatternSyntaxException {

        String regEx = "[/:*?<>|\"\n\t]";

        Pattern p = Pattern.compile(regEx);

        Matcher m = p.matcher(str);

        return m.replaceAll("");

    }

    public static boolean isEmailNumber(@NonNull String email) {
        Pattern p = Pattern
                .compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(" +
                        "([a-zA-Z0-9\\-]+\\.)" +
                        "+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * @param date
     * @param format yy-hh-mm
     * @return
     */
    public static String dateFomat(Date date, @NonNull String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.CHINA);
        return formatter.format(date);
    }

    public static String dateFormat(String date, @NonNull String format, @NonNull String toformat) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.CHINA);
        Date dt = null;
        try {
            dt = formatter.parse(date);
            formatter = new SimpleDateFormat(toformat, Locale.CHINA);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (dt != null)
            return formatter.format(dt);
        return "";
    }

    public static Date dateFomat(String date, @NonNull String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.CHINA);
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * 字符串转为经纬度
     *
     * @param str
     * @return
     */
    public static int stringToGeopoint(String str) {
        // int) (39.918* 1E6)
        int i = 0;
        try {
            float temp = Float.parseFloat(str);
            i = (int) (temp * 1E6);
        } catch (Exception ex) {
            i = 0;
        }
        return i;
    }

    public static String dateFormat(long ms) {
        long temp = System.currentTimeMillis() - ms;
        Date date = new Date(ms);
        if (temp < 86400000) {
            return dateFomat(date, "HH:mm");
        } else {
            return dateFomat(date, "MM-dd");
        }
    }


    //大写字母转为1-26，数字不变
    public static String graphemeToNumber(String str) {
        StringBuffer outStr = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            int c = (int) str.charAt(i);
            if (65 <= c && c <= 90) {
                outStr.append(c - 64);
            } else {
                outStr.append(c - 48);
            }
        }
        return outStr.toString();
    }

    //从字符串中取出数字
    public static String getNumberFromString(String str) {
        StringBuffer outStr = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            int c = (int) str.charAt(i);
            if (65 <= c && c <= 90) {
                continue;
            } else {
                outStr.append(c - 48);
            }
        }
        return outStr.toString();
    }
}
