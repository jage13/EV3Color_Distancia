package SensorPack;

import java.sql.Driver;
import java.util.Random;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;

public class SensorTest {
	
	EV3ColorSensor colorSensor;
	SampleProvider colorProvider;
	float[] colorSample;
	EV3IRSensor sensor;
	SampleProvider distanceMode;
	MovePilot pilot;
	Random ran = new Random();
	Driving  drive;
	int period = 7;
	int fasttravelspeed= 4;
	int slowtravelspeed= 2;

	
	public static void main(String[] args) {
	new SensorTest();
	
	}
	
	public SensorTest() {
		//Wheel LeftWheel = WheeledChassis.modelWheel(Motor.B,1.5).offset(-3);
		///Wheel RigthWheel = WheeledChassis.modelWheel(Motor.C,1.5).offset(3);
		//Chassis chassis = new WheeledChassis(new Wheel[] {LeftWheel, RigthWheel},WheeledChassis.TYPE_DIFFERENTIAL);
		
		//pilot = new MovePilot(chassis);
		
		//drive = new Driving(pilot);
		//drive.travelAndRodate();
		
		//pilot.setLinearSpeed(fasttravelspeed);
		//pilot.setAngularSpeed(45);
		
		Port s3 = LocalEV3.get().getPort("S3");
		colorSensor = new EV3ColorSensor(s3);
		colorProvider = colorSensor.getRGBMode();
		colorSample = new float[colorProvider.sampleSize()];
		
		sensor = new EV3IRSensor(SensorPort.S2);
		distanceMode = sensor.getDistanceMode();
		
		
		  
	
		
		while(Button.ESCAPE.isUp()) {	
		int concurrentDetectedColor = colorSensor.getColorID();	
		float[] sample = new float[distanceMode.sampleSize()];
		//pilot.travel(12);
		//Delay.msDelay(period*1000);
		

		
		switch (concurrentDetectedColor) {
		case Color.BLUE:
			
		//pilot.setLinearSpeed(slowtravelspeed);	
		
		colorSensor.setFloodlight(Color.BLUE);
		LCD.clear();
		LCD.drawString("Azul", 0, 4);
		
		distanceMode.fetchSample(sample, 0);
		System.out.printf("%.5f m\n", sample[0]);
	
		//pilot.arc(12, -90);
		//pilot.setLinearSpeed(fasttravelspeed);
		break;
		
		case Color.RED:
	
		//pilot.setLinearSpeed(slowtravelspeed);	
		
		colorSensor.setFloodlight(Color.RED);
		LCD.clear();
		LCD.drawString("Rojo", 0, 4);
		distanceMode.fetchSample(sample, 0);
		System.out.printf("%.5f m\n", sample[0]);
		
		//pilot.arc(12, -90);
		//pilot.setLinearSpeed(fasttravelspeed);
		break;
		
		case Color.YELLOW:
		//pilot.setLinearSpeed(slowtravelspeed);		
			
		colorSensor.setFloodlight(Color.BLUE);	
		LCD.clear();
		LCD.drawString("Amarillo", 0, 4);
		distanceMode.fetchSample(sample, 0);
		System.out.printf("%.5f m\n", sample[0]);
		
		//pilot.arc(12, -90);
		//pilot.setLinearSpeed(fasttravelspeed);
		
		break;
		
		default :
			colorSensor.setFloodlight(Color.NONE);	
			LCD.clear();
		break;
		
		}
			Delay.msDelay(250);
		}
		
		colorSensor.close();
		//sensor.close();
	}

}
