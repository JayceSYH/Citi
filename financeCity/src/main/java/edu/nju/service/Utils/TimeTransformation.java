package edu.nju.service.Utils;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Sun YuHao on 2016/8/28.
 */
public class TimeTransformation {
    static public final long microSecond = 1;
    static public final long second = 1000;
    static public final long minute = second * 60;
    static public final long hour = minute * 60;
    static public final long day = hour * 24;
    static public final long month = day * 30;
    static public final long year = day * 365;

    static public double getTimeFromNow(Timestamp timestamp, long type) {

        return ((double)timestamp.getTime() - System.currentTimeMillis()) / type;
    }

    static public double getTimeFromNow(Date date, long type) {
        String time = date.toString() + " 00:00:00";
        Timestamp timestamp = Timestamp.valueOf(time);

        return getTimeFromNow(timestamp, type);
    }

    static public double getTimeFromDate(Date start, Date end, long type) {
        return getTimeFromNow(end, type) - getTimeFromNow(start, type);
    }

    static public double getTimeAfter(Timestamp timestamp, double length, long lengthType, long resultType) {
        return (timestamp.getTime() + length * lengthType) / resultType;
    }

    static public double getTimeAfter(Date date, double length, long lengthType, long resultType) {
        return (date.getTime() + length * lengthType) / resultType;
    }
}
