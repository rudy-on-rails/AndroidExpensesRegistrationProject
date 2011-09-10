package androidexpensesregistration.helpers;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.util.Log;

public class DateHelper {
	public static final String DATE_FORMAT_NOW = "dd-MM-yyyy";
	public static final String DATE_ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
	public static final String JUST_TIME_STRING_FORMAT = "HH:mm:ss";
	
	public static String getNowDateToString() {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	    return sdf.format(cal.getTime());

	  }
	public static String convertDateToString(int Day, int Month, int Year) {
		DecimalFormat twoDigitsFormatter = new DecimalFormat("00");
		
		StringBuilder datesStringBuilder = 
		 new StringBuilder()         
         .append(twoDigitsFormatter.format(Day)).append("-")
         .append(twoDigitsFormatter.format(Month)).append("-")
         .append(Year).append(" ");
		return datesStringBuilder.toString();
	}
	
	public static Date parseScreenDateStringToDate(String screenString) {
		DateFormat formatter = new SimpleDateFormat(DATE_FORMAT_NOW);		
		try {
			return formatter.parse(screenString);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			Log.e("Parsing Failed...", "Parsing ISO8601 datetime failed", e);
		}
		return null;		
	}
	
	public static Date parseDateInputStringToDate(String inputString)  {
		DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return iso8601Format.parse(inputString);
		} catch (java.text.ParseException e) {
			Log.e("Parsing Failed...", "Parsing ISO8601 datetime failed", e);
		}
		return null;
	}
	
	public static Date now(){
		return new Date();		
	}
	
	public static String getNowTimeString(){
		DateFormat justTimeFormat = new SimpleDateFormat(JUST_TIME_STRING_FORMAT);
		return justTimeFormat.format(now());
	}
	
	public static String getNowPlusHoursString(int hoursToAdd){
		DateFormat justTimeFormat = new SimpleDateFormat(JUST_TIME_STRING_FORMAT);
		Date dateObject = now();
		dateObject.setHours(dateObject.getHours() + hoursToAdd);
		return justTimeFormat.format(dateObject);
	}
	
	public static String getNowMinusHoursString(int hoursToSubtract){
		DateFormat justTimeFormat = new SimpleDateFormat(JUST_TIME_STRING_FORMAT);
		Date dateObject = now();
		dateObject.setHours(dateObject.getHours() - hoursToSubtract);
		return justTimeFormat.format(dateObject);
	}
	
	public static String getFormattedDataStringForDB(Date date){		
		SimpleDateFormat sdf =
		new SimpleDateFormat(
		DATE_ISO_FORMAT);
		return sdf.format(date);
	} 
}
