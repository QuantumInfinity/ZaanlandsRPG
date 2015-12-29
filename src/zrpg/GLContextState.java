package zrpg;

import zrpg.statemachine.IState;

public class GLContextState implements IState {

	// State to setup the gl context & manage the window.
	
	// TODO params & error handling & logging
	@Override
	public void onInit() {
		ZRPG.gameState().popState();
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
