package zrpg.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class ZRPGFormatter extends Formatter {
	// Class to format log messages
	
	Date date = new Date();
	Calendar cal = Calendar.getInstance();
	
	@Override
	public String format(LogRecord record) {
		
		StringBuffer sb = new StringBuffer();
		
		date.setTime(record.getMillis());
		cal.setTime(date);
		
		sb.append('[');
		sb.append(cal.get(Calendar.HOUR_OF_DAY));
		sb.append(':');
		sb.append(cal.get(Calendar.MINUTE));
		sb.append(':');
		sb.append(cal.get(Calendar.SECOND));
		sb.append("] ");
		sb.append(record.getLevel().getName());
		sb.append(": ");
		sb.append(record.getMessage());
		sb.append('\n');
		
		if (record.getThrown() != null)
			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				record.getThrown().printStackTrace(pw);
				pw.close();
				sb.append(sw.toString());
			} catch (Exception ex) {
			}
		
		return sb.toString();
	}
}
