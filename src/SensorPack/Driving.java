package SensorPack;

import lejos.robotics.navigation.MovePilot;

public class Driving {

	MovePilot pilot;
	
	public Driving(MovePilot p) {
		pilot = p;
	}
	
	public void travelAndRodate() {
		pilot.travel(12);
		pilot.rotate(90);
	}
}
