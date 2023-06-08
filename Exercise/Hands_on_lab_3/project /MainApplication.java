package Exercise.Hands_on_lab_3.project;

public class MainApplication {
    public static void main(String[] args) {
        SensorDataRecorder sensorDataRecorder = new SensorDataRecorder();
        ThresholdChecker thresholdChecker = new ThresholdChecker();
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

