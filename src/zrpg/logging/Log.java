package zrpg.logging;

import java.util.logging.Logger;

public class Log {
	
	// Simple method to initialize the 'global' logging state
	public static void initLogger() {
		Logger logger = Logger.getLogger("zrpg");
		logger.setUseParentHandlers(false);
		logger.addHandler(new ZRPGHandler());
	}
}
