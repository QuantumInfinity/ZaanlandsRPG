package zrpg.statemachine;

import java.util.ArrayList;
import java.util.Stack;

public class StateMachine {

	// Arraylist of states in this statemachine.
	ArrayList<IState> states;
	// The current state.
	Stack<Integer> stack;

	public StateMachine() {
		states = new ArrayList<IState>();
		stack = new Stack<Integer>();
		int emptyState = addState(new EmptyState()); // This state should always have id '0'
		pushState(emptyState);
	}

	// Add a state to the statemachine, and return an 'id' of the state
	public int addState(IState state) {
		states.add(state);
		return states.size() - 1;
	}

	// Switch to another state, removing the one thats currently on top.
	public void switchToState(int state) {
		popState();
		pushState(state);
	}

	// Push a state to the stack.
	public void pushState(int state) {
		if (!states.contains(state))
			throw new IllegalArgumentException("Unknown state");
		stack.push(state);
		init();
	}
	
	// Pop a state from the stack.
	public void popState() {
		destroy();
		stack.pop();
	}
	
	// Call the current state's destroy function.
	public void init() {
		states.get(stack.peek()).onInit();
	}
	
	// Call the current state's destroy function.
	public void update() {
		states.get(stack.peek()).onUpdate();
	}

	// Call the current state's destroy function.
	public void render(float dt) {
		states.get(stack.peek()).onRender(dt);
	}
	
	// Call the current state's destroy function.
	public void destroy() {
		states.get(stack.peek()).onDestroy();
	}
}
