package com.atguigu.servicebase.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String getNowDateLongStr(){
        Date now = new Date();
        // 指定格式化格式
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM/dd");
        return f.format(now);
    }

}
