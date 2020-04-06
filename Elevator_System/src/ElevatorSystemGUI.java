import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ElevatorSystemGUI extends JFrame implements ActionListener{

	private static final int GRID_SZ = 2;

	/**
	 * Constructor for TTTView Displays a TicTacToe window made up of a JFrame with
	 * a grid layout containing TTTButtons
	 * 
	 * @param model
	 *            The model the TTTView is representing
	 */
	public ElevatorSystemGUI() {
		super("Elevator System");
		this.setLayout(new GridLayout(GRID_SZ,GRID_SZ));

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
			
		}else if(action.equals("Scheduler and Elevator Set Up")) {
			Setup.serializeInetAddress("SchedulerElevatorAddress.txt");
			
		}else if(action.contentEquals("Simulate Scheduler and Elevator response")){
			ElevatorSystem elevator = new ElevatorSystem(0);
			List<ElevatorSystem> elevators = new ArrayList<ElevatorSystem>();
			elevators.add(elevator);
			Scheduler scheduler = new Scheduler(elevators);
			scheduler.run();
		}
		
		
	}
	
	public static void main(String[] args) {
		new ElevatorSystemGUI();
	}

}
