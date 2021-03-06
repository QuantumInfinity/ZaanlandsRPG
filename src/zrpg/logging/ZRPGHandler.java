package zrpg.logging;

import java.util.logging.ConsoleHandler;
import java.util.logging.ErrorManager;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class ZRPGHandler extends ConsoleHandler {

	public ZRPGHandler() {
		setFormatter(new ZRPGFormatter());
	}
	
	@Override
	public void publish(LogRecord record) {
		if (getFormatter() == null)
			setFormatter(new ZRPGFormatter());

		try {
			String message = getFormatter().format(record);
			if (record.getLevel().intValue() >= Level.WARNING.intValue()) {
				System.err.write(message.getBytes());
			} else {
				System.out.write(message.getBytes());
			}
		} catch (Exception exception) {
			reportError(null, exception, ErrorManager.FORMAT_FAILURE);
		}
	}
}
