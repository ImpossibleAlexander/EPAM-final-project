package ua.nure.kaplin.SummaryTask4.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldsValidator {
	private static final String REGEX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String REGEX_DATE = "\\d[0-9]\\d[0-9]-\\d[0-9]-\\d[0-9]\\s\\d[0-9]:\\d[0-9]:\\d[0-9]";
	
	public boolean validateFieldEmail(String email) {
		 Pattern p = Pattern.compile(REGEX_EMAIL);
		 Matcher m = p.matcher(email);
		 return m.matches();
	 }
	public boolean validateDate(String cardDate) {
		 Pattern p = Pattern.compile(REGEX_DATE);
		 Matcher m = p.matcher(cardDate);
		 return m.matches();
	 }
}
