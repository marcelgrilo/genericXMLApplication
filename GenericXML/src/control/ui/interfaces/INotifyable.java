package control.ui.interfaces;

public interface INotifyable {
	public INotifyable getParent();
	public void notifyController(Object source, String message);
}
