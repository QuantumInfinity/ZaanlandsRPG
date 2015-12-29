package zrpg;

public class FPSMeter {
	// Simple FPS meter class.
	
	private int fps, lastfps = 0;
	private double lastTime;

	public FPSMeter() {
		lastTime = ZRPG.instance().getMillis();
	}

	// Update the fps meter.
	public int update() {
		if (ZRPG.instance().getMillis() - lastTime > 1000) {
			lastfps = fps;
			fps = 0;
			lastTime += 1000;
		}
		fps++;
		return lastfps;
	}

	// Get the amount of FPS last recorded.
	public int getFPS() {
		return lastfps;
	}
}
