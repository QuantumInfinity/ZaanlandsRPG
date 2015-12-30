package zrpg.bootstrap;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import zrpg.bootstrap.OSUtils.OSType;
import zrpg.bootstrap.OSUtils.UnsupportedArchitectureException;
import zrpg.bootstrap.OSUtils.UnsupportedOSException;

public class Bootstrap {

	private static final Logger logger =  Logger.getLogger(Bootstrap.class.getName());
	
	// Load LWJGL natives
	public static boolean loadNatives() {
		logger.info("Loading natives...");
		try {
			loadLWJGL();
			logger.info("Natives succesfully loaded!");
			return true;
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error while loading natives!", e);
			System.exit(0);
		}
		return false;
	}
	
	private static void loadLWJGL() throws IOException {
		String arch = System.getProperty("sun.arch.data.model");
		if (arch.equals("64"))
			loadLWJGL64();
		else if (arch.equals("86"))
			loadLWJGL86();
		else
			throw new UnsupportedArchitectureException(arch);
	}

	private static void loadLWJGL64() throws IOException {
		OSType os = OSUtils.getOperatingSystemType();

		switch (os) {
		case Linux:
			logNativeInfo("Linux x64");
			NativeUtils.loadLibraryFromJar("/natives/libjemalloc.so");
			NativeUtils.loadLibraryFromJar("/natives/libglfw.so");
			NativeUtils.loadLibraryFromJar("/natives/liblwjgl.so");
			break;
		case MacOSX:
			logNativeInfo("MacOSX");
			NativeUtils.loadLibraryFromJar("/natives/libjemalloc.dylib");
			NativeUtils.loadLibraryFromJar("/natives/libglfw.dylib");
			NativeUtils.loadLibraryFromJar("/natives/liblwjgl.dylib");
			break;
		case Windows:
			logNativeInfo("Windows x64");
			NativeUtils.loadLibraryFromJar("/natives/jemalloc.dll");
			NativeUtils.loadLibraryFromJar("/natives/glfw.dll");
			NativeUtils.loadLibraryFromJar("/natives/lwjgl.dll");
			break;
		default:
			throw new UnsupportedOSException(os);
		}
	}

	private static void loadLWJGL86() throws IOException {
		OSType os = OSUtils.getOperatingSystemType();
		switch (os) {
		case Linux:
			logNativeInfo("Linux x86");
			NativeUtils.loadLibraryFromJar("/natives/libjemalloc32.so");
			NativeUtils.loadLibraryFromJar("/natives/libglfw32.so");
			NativeUtils.loadLibraryFromJar("/natives/liblwjgl32.so");
			break;
		case Windows:
			logNativeInfo("Windows x86");
			NativeUtils.loadLibraryFromJar("/natives/jemalloc32.dll");
			NativeUtils.loadLibraryFromJar("/natives/glfw32.dll");
			NativeUtils.loadLibraryFromJar("/natives/lwjgl32.dll");
			break;
		default:
			throw new UnsupportedOSException(os);
		}
	}

	private static void logNativeInfo(String platform) {
		logger.info("Platform: " + platform);
	}
}
