import java.util.Queue;

public class Scheduler implements Runnable{
	
	private Queue<Request> upQueue;
	private Queue<Request> downQueue;
	
	public Scheduler() {
		//upQueue = new Queue<Request>();
		//downQueue = new Queue<Request>();
		
	}
	/**
     * Puts requests in the scheduler
     * 
     * @param request
     */
    public synchronized void getRequest(Request request) {
        if(request.getDirection()) {
        	upQueue.add(request);
        }else {
        	downQueue.add(request);
        }
    }
	

	@Override
	public void run() {
		while(!(upQueue.isEmpty() && downQueue.isEmpty())) { //While the queues are not empty
			if(!upQueue.isEmpty()) { //If upQueue has requests
				handleUpRequest(upQueue.remove());
			}else { //downQueue has requests
				handleDownRequest(downQueue.remove());
			}
			
		}
		
	}

	private void handleDownRequest(Request remove) {
		// TODO Auto-generated method stub
		
	}


	private void handleUpRequest(Request remove) {
		// TODO Auto-generated method stub
		
	}
	
	

}
