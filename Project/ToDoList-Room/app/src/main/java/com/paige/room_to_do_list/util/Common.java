package com.paige.room_to_do_list.util;

import java.text.DateFormat;
import java.util.Date;

public class Common {

    public static String getFormattedDate(long timestamp){
        DateFormat dateFormat = DateFormat.getDateInstance();
        return dateFormat.format(new Date(timestamp).getTime());
    }

}
