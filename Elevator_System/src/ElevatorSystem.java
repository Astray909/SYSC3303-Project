import java.util.*;
/**
 * 
 * @author Jia Chen Huang 101073186
 * @version January 30
 *
 */
public class ElevatorSystem 
{
	private List selectedFloors = new ArrayList<>();
	private boolean direction;
	private boolean doorOpenClose;
	private Request request = new Request();
	private int id;
	
	private void doorOpenCose()
	{
		doorOpenClose = !doorOpenClose;
	}
	
	public void getTask(Request request)
	{
		request = this.request;
	}
}
