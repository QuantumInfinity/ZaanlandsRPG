package zrpg.bootstrap;

import java.io.IOException;

import zrpg.ZRPG;
import zrpg.bootstrap.OSUtils.OSType;
import zrpg.bootstrap.OSUtils.UnsupportedArchitectureException;
import zrpg.bootstrap.OSUtils.UnsupportedOSException;
import zrpg.statemachine.IState;

public class BootstrapState implements IState {

	// State to load the LWJGL natives.

	@Override
	public void onInit() {
		try {
			loadLWJGL();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		ZRPG.gameState().popState();
	}

	public static void loadLWJGL() throws IOException {
		String arch = System.getProperty("sun.arch.data.model");
		if (arch.equals("64"))
			loadLWJGL64();
		else if (arch.equals("86"))
			loadLWJGL86();
		else
			throw new UnsupportedArchitectureException(arch);
	}

	public static void loadLWJGL64() throws IOException {
		OSType os = OSUtils.getOperatingSystemType();

		switch (os) {
		case Linux:
			NativeUtils.loadLibraryFromJar("/natives/libjemalloc.so");
			NativeUtils.loadLibraryFromJar("/natives/libglfw.so");
			NativeUtils.loadLibraryFromJar("/natives/liblwjgl.so");
			break;
		case MacOSX:
			NativeUtils.loadLibraryFromJar("/natives/libjemalloc.dylib");
			NativeUtils.loadLibraryFromJar("/natives/libglfw.dylib");
			NativeUtils.loadLibraryFromJar("/natives/liblwjgl.dylib");
			break;
		case Windows:
			NativeUtils.loadLibraryFromJar("/natives/jemalloc.dll");
			NativeUtils.loadLibraryFromJar("/natives/glfw.dll");
			NativeUtils.loadLibraryFromJar("/natives/lwjgl.dll");
			break;
		default:
			throw new UnsupportedOSException(os);
		}
	}

	public static void loadLWJGL86() throws IOException {
		OSType os = OSUtils.getOperatingSystemType();
		switch (os) {
		case Linux:
			NativeUtils.loadLibraryFromJar("/natives/libjemalloc32.so");
			NativeUtils.loadLibraryFromJar("/natives/libglfw32.so");
			NativeUtils.loadLibraryFromJar("/natives/liblwjgl32.so");
			break;
		case Windows:
			NativeUtils.loadLibraryFromJar("/natives/jemalloc32.dll");
			NativeUtils.loadLibraryFromJar("/natives/glfw32.dll");
			NativeUtils.loadLibraryFromJar("/natives/lwjgl32.dll");
			break;
		default:
			throw new UnsupportedOSException(os);
		}
	}

	@Override
	public void onUpdate() {
	}

	@Override
	public void onRender() {
	}

	@Override
	public void onDestroy() {
		System.gc();
	}
}
