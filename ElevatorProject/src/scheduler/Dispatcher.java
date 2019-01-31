package scheduler;

import java.util.ArrayList;

import resources.*;
import static resources.Constants.NUMBER_OF_FLOORS;

/**
 * Dispatcher class to take care of requests from floors and choose the correct elevator
 * 
 * @author Darren
 *
 */
public class Dispatcher {
	private ArrayList<TempElevator> elevators;
	public final static int MAX_DIFF = NUMBER_OF_FLOORS + 1; // + 1 so that it's always bigger than the greatest possible difference
	
	
	public Dispatcher() {
		this.elevators = new ArrayList<TempElevator>();
	}

	public void addElevator(TempElevator elevator) {
		this.elevators.add(elevator);
	}
	
	public void updateElevatorInfo(Object something) {
		// Use something to update info
	}
	
	// 
	/**
	 * Determine the nearest, applicable elevator given a request consisting of a direction and originating floor 
	 * Currently iterates through a list of elevators but will probably change to ping each elevator
	 * 
	 * @param dir		The direction of the request
	 * @param callingFloor		The request's originating floor
	 * @return			The elevator to handle the request
	 */
	public TempElevator getNearestElevator(Directions dir, int callingFloor) {
		int currDif = MAX_DIFF;
		int newDif;
		TempElevator currElevator = null;
		
		// For each elevator
		for (TempElevator elevator: this.elevators) {
			// Only check elevators that are on standby or going the right direction
			if (!Directions.isOpposite(dir, elevator.getDir())) {
				
				// If elevator is going down, it can only hit floors below it
				// If elevator is going up, it can only hit floors above it
				// If elevator is on standby, it can go either direction so floor locations don't matter
				if (elevator.getDir() == Directions.STANDBY || (dir == Directions.DOWN && elevator.getFloor() > callingFloor && dir == elevator.getDir()) || (dir == Directions.UP && elevator.getFloor() < callingFloor && dir == elevator.getDir())) {
					newDif = Math.abs(elevator.getFloor() - callingFloor);
					if (newDif < currDif) {
						currDif = newDif;
						currElevator = elevator;
					}
				}
			}
		}
		
		return currElevator;
	}
	
	
	// Temporary elevator object to rough functionality in
	public class TempElevator {
		private Directions dir;
		private int currFloor;
		
		public TempElevator (Directions dir, int floor) {
			this.dir = dir;
			this.currFloor = floor;
		}
		
		public int getFloor() {
			return this.currFloor;
		}
		
		public Directions getDir() {
			return this.dir;
		}
	}
}