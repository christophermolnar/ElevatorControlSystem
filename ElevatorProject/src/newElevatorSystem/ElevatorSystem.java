package newElevatorSystem;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ElevatorSystem {
	public static final int ELEVATOR_NUM = 1;
	private List<Thread> elevatorThreads;
	private List<Elevator> elevators;
	
	public ElevatorSystem() {
		elevatorThreads = new ArrayList<>();
		elevators = new ArrayList<>();
		for (int i = 0; i < ELEVATOR_NUM; i++) {
			Elevator elv = new Elevator(i);
			elevators.add(elv);
			elevatorThreads.add(new Thread(elv,"Elevator " + i));
		}
	}
	
	public void startElevatorService() {
		for (Thread elevator : elevatorThreads) {
			elevator.start();
		}
	}
	
	public List<Thread> getElevatorThreadList(){
		return elevatorThreads;
	}
	
	public List<Elevator> getElevatorList(){
		return elevators;
	}
	
	public static void main (String args[]) {
		ElevatorSystem elevatorSystem = new ElevatorSystem();
		elevatorSystem.startElevatorService();
		//Elevator e1 = elevatorSystem.getElevatorList().get(0);
//		try {
//			TimeUnit.SECONDS.sleep(5);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		DatagramSocket datagramSocket = null;
		try {
			datagramSocket = new DatagramSocket(69);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (true) {
			byte[] buffer = new byte[4];
			DatagramPacket packetReceive = new DatagramPacket(buffer, buffer.length);
			try {
				datagramSocket.receive(packetReceive);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (buffer[0] == (byte) 4) {
				Elevator e = elevatorSystem.getElevatorList().get(buffer[3]);
				e.addFloortoServiceQueue(buffer[2]);
			}
		}
		
//		e1.addFloortoServiceQueue(4);
//		e1.addFloortoServiceQueue(2);
//		e1.addFloortoServiceQueue(5);
//		e1.addFloortoServiceQueue(7);
	}
	
}
