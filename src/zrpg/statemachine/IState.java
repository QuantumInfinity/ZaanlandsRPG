package zrpg.statemachine;

public interface IState {
    
    // Called when the statemachine switched to this state.
    public void onInit();
    
    // Called at a constant interval.
    public void onUpdate();
    
    // Called at as fast as possible, or at a constant interval.
    public void onRender();
    
    // Called when the statemachine switched from this state to another.
    public void onDestroy();
}
