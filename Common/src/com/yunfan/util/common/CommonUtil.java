package com.yunfan.util.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

	/**
	 * 将时间转换成制定的字符串
	 * @param date
	 * @param timepattern
	 * @return
	 */
	public static String convertDate2Str(Date date, String timepattern){
		DateFormat df = new SimpleDateFormat(timepattern);
		if(null == date){
			return df.format(new Date());
		}
		return df.format(date);
	}
}
