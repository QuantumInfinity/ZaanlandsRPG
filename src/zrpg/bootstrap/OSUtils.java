package zrpg.bootstrap;

import java.util.Locale;

public class OSUtils {
	public enum OSType {
		Windows, MacOSX, Linux, Other
	};

	protected static OSType detectedOS;

	public static OSType getOperatingSystemType() {
		if (detectedOS == null) {
			String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
			if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0))
				detectedOS = OSType.MacOSX;
			else if (OS.indexOf("win") >= 0)
				detectedOS = OSType.Windows;
			else if (OS.indexOf("nux") >= 0)
				detectedOS = OSType.Linux;
			else
				detectedOS = OSType.Other;
		}
		return detectedOS;
	}

	public static class UnsupportedOSException extends RuntimeException {
		private static final long serialVersionUID = -4811942511439627417L;

		public UnsupportedOSException(OSType os) {
			super("Unsupported OS: " + os.name() + " (" + System.getProperty("os.name") + ") ");
		}
	}

	public static class UnsupportedArchitectureException extends RuntimeException {
		private static final long serialVersionUID = -4811942511439627417L;

		public UnsupportedArchitectureException(String arch) {
			super("Unsupported architecture: " + arch);
		}
	}
}
