package com.chengmuxin.note.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

	public static String getDate() {
		SimpleDateFormat formatDate = new SimpleDateFormat("yy-MM-dd");
		Date curDate = new Date(System.currentTimeMillis());
		return formatDate.format(curDate);
	}

	public static String getTime() {
		SimpleDateFormat formatDate = new SimpleDateFormat("HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());
		return formatDate.format(curDate);
	}

}
