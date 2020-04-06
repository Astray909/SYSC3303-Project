import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class WORK_IN_PROGRESS extends JFrame implements ActionListener{

	private static final int GRID_L = 2;
	private static final int GRID_H = 3;
	ElevatorSystem elevator = new ElevatorSystem(0);
	List<ElevatorSystem> elevators = new ArrayList<ElevatorSystem>();
	
	Scheduler scheduler = new Scheduler(elevators);

	/**
	 * Constructor for TTTView Displays a TicTacToe window made up of a JFrame with
	 * a grid layout containing TTTButtons
	 * 
	 * @param model
	 *            The model the TTTView is representing
	 */
	public WORK_IN_PROGRESS() {
		super("Elevator System");
		this.setLayout(new GridLayout(GRID_H, GRID_L));

		JButton floorSetupButton = new JButton("Floors Set Up");
		this.add(floorSetupButton);
		floorSetupButton.addActionListener(this);
		
		JButton seButton = new JButton("Scheduler and Elevators Set Up");
		this.add(seButton);
		seButton.addActionListener(this);
		
		JButton simFloorsButton = new JButton("Simulate Floor Requests");
		this.add(simFloorsButton);
		simFloorsButton.addActionListener(this);
		
		JButton simSEbutton = new JButton("Simulate Scheduler and Elevator response");
		this.add(simSEbutton);
		simSEbutton.addActionListener(this);
		
		JButton simDoorOpen = new JButton("Hold door open");
		this.add(simDoorOpen);
		simDoorOpen.addActionListener(this);
		
		JButton simDoorClose = new JButton("Hold door close");
		this.add(simDoorClose);
		simDoorClose.addActionListener(this);
		
		this.setSize(800, 800);
		this.setVisible(true);
	}

	/**
	 * Changes state of model
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String action = e.getActionCommand();
		
		if(action.equals("Simulate Floor Requests")) {
			Thread buildingThread = new Thread(new Building(10));
			buildingThread.run();
			
		}else if(action.equals("Floors Set Up")) {
			Setup.serializeInetAddress("FloorsAddress.txt");
			System.out.print("FloorsAddress serilaized");
			
		}else if(action.equals("Scheduler and Elevators Set Up")) {
			Setup.serializeInetAddress("SchedulerElevatorAddress.txt");
			System.out.print("SchedulerElevatorAddress serilaized");
		
		}else if(action.contentEquals("Simulate Scheduler and Elevator response")){
			elevators.add(elevator);
			scheduler.run();
		}else if(action.contentEquals("Hold door open")) {
			elevator.holdDoor(true);
		}
		
		
	}
	
	public static void main(String[] args) {
		new WORK_IN_PROGRESS();
	}

}
