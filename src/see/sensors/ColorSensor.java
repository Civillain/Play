package see.sensors;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import see.comm.Channel;
import see.comm.ChannelColorDetected;
import see.events.ColorDetected;
import see.robot.Robot;


public class ColorSensor extends Sensor<ColorDetected> {

	private ChannelColorDetected channelColorDetected;
	private EV3ColorSensor colorSensor;
	
	public ColorSensor() {
		this.channelColorDetected = new ChannelColorDetected();
	}
	
	@Override
	public void connect(Robot robot) {
		logger.info("Connecting color sensor");
		SensorMap sensorMap = robot.getSensorMap();
		Port port = sensorMap.getPort(SensorType.COLOR);
		EV3ColorSensor colorSensor = new EV3ColorSensor(port);
		SensorMode sensorMode = colorSensor.getColorIDMode();
		colorSensor.setCurrentMode(sensorMode.getName());
		this.colorSensor = colorSensor;
	}
	
	@Override
	public String toString() {
		return "ColorSensor";
	}

	@Override
	public Channel<ColorDetected> channel() {
		return channelColorDetected;
	}

	@Override
	public ColorDetected read() throws InterruptedException {
		int colorId = colorSensor.getColorID();
		ColorDetected detected = new ColorDetected(colorId);
		return detected;
	}
}