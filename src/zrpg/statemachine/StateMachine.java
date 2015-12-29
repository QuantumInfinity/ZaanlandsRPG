package zrpg.statemachine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StateMachine {
	// Arraylist of states in this statemachine.
	ArrayList<IState> states;
	// The current state.
	Stack<Integer> stack;
	// Actions to update the state with.
	Queue<Action> actionQueue;

	public StateMachine() {
		states = new ArrayList<IState>();
		stack = new Stack<Integer>();
		actionQueue = new LinkedList<Action>();
		int emptyState = addState(new EmptyState()); // This state should always have id '0'
		pushState(emptyState);
	}

	// Add a state to the statemachine, and return an 'id' of the state
	public int addState(IState state) {
		states.add(state);
		return states.size() - 1;
	}

	// Updates the machine with action. Returns if there are no states left (and thus the engine should exit).
	public boolean updateMachine() {
		Action a;
		while ((a = actionQueue.poll()) != null){
			switch(a.action) {
			case Action.ACTION_SWITCH:
				destroy();
				stack.pop();
			case Action.ACTION_PUSH:
				stack.push(a.state);
				init();
				break;
			case Action.ACTION_POP:
				destroy();
				stack.pop();
				break;
			}
		}
		return stack.empty();
	}
	
	// Switch to another state, removing the one thats currently on top.
	public void switchToState(int state) {
		actionQueue.offer(new Action(Action.ACTION_SWITCH, state));
	}

	// Push a state to the stack.
	public void pushState(int state) {
		actionQueue.offer(new Action(Action.ACTION_PUSH, state));
	}
	
	// Pop a state from the stack.
	public void popState() {
		actionQueue.offer(new Action(Action.ACTION_POP, null));
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
	public void render() {
		states.get(stack.peek()).onRender();
	}
	
	// Call the current state's destroy function.
	public void destroy() {
		states.get(stack.peek()).onDestroy();
	}
	
	private class Action {
		final public static int ACTION_PUSH	  = 0; // Push the state.
		final public static int ACTION_POP	  = 1; // Pop the state.
		final public static int ACTION_SWITCH = 2; // Switch to the state (replace topmost state on the stack).
		
		final public int action; // The action to do.
		final public Integer state; // An 'operand' is not always needed (pop).
		
		public Action(int action, Integer state) {
			this.action = action;
			this.state = state;
		}
	}
}
