import java.io.*;

public class MainApplication {
    public static void main(String[] args) {

        SensorDataRecorder sensorDataRecorder = new SensorDataRecorder();
        // Get random sensor data values
        double temperature = sensorDataRecorder.getTemperature();
        double soilHumidity = sensorDataRecorder.getSoilHumidity();
        double airHumidity = sensorDataRecorder.getAirHumidity();
        double pH = sensorDataRecorder.getpH();
        double luminosity = sensorDataRecorder.getLuminosity();

        ThresholdChecker thresholdChecker = new ThresholdChecker(30, 70, 50, 7, 800);
        // Get random sensor data values
        double temperature = sensorDataRecorder.getTemperature();
        double soilHumidity = sensorDataRecorder.getSoilHumidity();
        double airHumidity = sensorDataRecorder.getAirHumidity();
        double pH = sensorDataRecorder.getpH();
        double luminosity = sensorDataRecorder.getLuminosity();
        // Check thresholds and take actions if necessary
        thresholdChecker.checkThresholds(temperature, soilHumidity, airHumidity, pH, luminosity);

        
        ActuatorController actuatorController = new ActuatorController();
        Dashboard dashboard = new Dashboard();

        // Record data from sensors
        sensorDataRecorder.recordDataFromSensors();

        // Check threshold and control actuators
        double sensorData = /* Get sensor data */;
        boolean isOverThreshold = thresholdChecker.isOverThreshold(sensorData);
        actuatorController.controlActuators(isOverThreshold);

        // Build and display the dashboard
        dashboard.buildDashboard();
    }
}

