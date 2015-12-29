package zrpg;

import zrpg.statemachine.StateMachine;
import zrpg.util.FPSMeter;

public class ZRPG {

	// The current game instance.
	public static ZRPG instance = null;

	// The amount of frames the engine can maximally skip.
	final public static int MAXFRAMESKIP = 5;

	// The amount of time the game can maximally run behind, in milliseconds.
	final public static int MAXTIMEDIFF = 500;

	// The amount of ticks per second.
	final public static int TPS = 60;

	// The maximum number of fps, fpslimit <= 0: inlimited.
	public int fpslimit = 60;

	// When the game started.
	private long startTime;
	
	// meters to calculate the actual fps and actual tps.
	private FPSMeter fpsmeter, tickmeter;
	
	// The statemachine for this game.
	public StateMachine gameState;

	private ZRPG() {
		if (instance != null)
			throw new RuntimeException("Only once instance of ZRPG can be running.");
		
		instance = this;
		startTime = System.nanoTime();
		fpsmeter = new FPSMeter();
		tickmeter = new FPSMeter();
		
		gameState = new StateMachine();
		States.registerStates();
	}
	
	// Run the main loop.
	private void run() {
		double gameTime = getMillis(), renderTime = gameTime;

		while (!gameState.updateMachine()) {
			double currTime = getMillis();
			if (currTime - gameTime > MAXTIMEDIFF || currTime - renderTime > MAXTIMEDIFF) {
				gameTime = currTime; // resync
				renderTime = currTime;
			}

			while (currTime >= gameTime) {
				gameTime += 1000. / TPS;
				gameState.update();
				tickmeter.update();
			}
			
			if (fpslimit > 0)
			{
				while (currTime >= renderTime) {
					renderTime += 1000. / fpslimit;
					render();
				}
				
				// Sleep until the next update.
				int sleepTime = (int) (Math.min(gameTime - currTime, renderTime - currTime)); 
				if (sleepTime > 0) {
					try {
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
					}
				}
			}
			else
				render();
		}
	}

	// Render the game.
	private void render() {
		fpsmeter.update();
		gameState.render();
	}
	
	// Get the game instance.
	public static ZRPG instance() {
		return instance;
	}
	
	public static StateMachine gameState() {
		return instance().gameState;
	}

	// Get the measured FPS.
	public int getMeasuredFPS() {
		return fpsmeter.getFPS();
	}
	
	// Get the measured TPS.
	public int getMeasuredTPS() {
		return tickmeter.getFPS();
	}
	
	// Get the amount of milliseconds since the game started.
	public double getMillis() {
		return (System.nanoTime() - startTime) / 1000000.;
	}

	public static void main(String[] args) {
		new ZRPG().run();
	}
}