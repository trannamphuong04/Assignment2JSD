package fa.training.problem02.utils;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	
	
	public static boolean validateName(String name) {
		String regex = "^\\w{2,50}$";
	    Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(name);
		return (matcher.find()) ;
	}
	
	public static boolean validateDate(Date from_date, Date to_date) {
		return (from_date.before(to_date) || from_date.equals(to_date));
	}
}
