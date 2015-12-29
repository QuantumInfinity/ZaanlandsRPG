package zrpg;

import zrpg.bootstrap.BootstrapState;
import zrpg.statemachine.StateMachine;

public class States {
	
	public static int BOOTSTRAP;
	
	public static void registerStates() {
		StateMachine gameState = ZRPG.instance().gameState;
		
		BOOTSTRAP = gameState.addState(new BootstrapState());
	}
}
