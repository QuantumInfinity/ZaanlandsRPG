package zrpg;

import zrpg.statemachine.StateMachine;

public class States {
	
	// register all game states.
	public static void registerStates() {
		StateMachine gameState = ZRPG.instance().gameState;
		
		//EMPTY = gameState.addState(new EmptyState());
		
		//gameState.pushState(..);
	}
}
