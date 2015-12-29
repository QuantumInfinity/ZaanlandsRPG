package zrpg.bootstrap;

import zrpg.bootstrap.OSUtils.OSType;
import zrpg.bootstrap.OSUtils.UnsupportedArchitectureException;
import zrpg.bootstrap.OSUtils.UnsupportedOSException;
import zrpg.statemachine.IState;

public class BootstrapState implements IState {

	// State to load the natives.

	@Override
	public void onInit() {

	}

	public static void loadLWJGL() {
		String arch = System.getProperty("sun.arch.data.model");
		if (arch.equals("64"))
			loadLWJGL64();
		else if (arch.equals("86"))
			loadLWJGL86();
		else
			throw new UnsupportedArchitectureException(arch);
	}

	public static void loadLWJGL64() {
		OSType os = OSUtils.getOperatingSystemType();

		switch (os) {
		case Linux:
			break;
		case MacOSX:
			break;
		case Windows:
			break;
		default:
			throw new UnsupportedOSException(os);
		}
	}

	public static void loadLWJGL86() {
		OSType os = OSUtils.getOperatingSystemType();
		switch (os) {
		case Linux:
			break;
		case Windows:
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
	}
}
