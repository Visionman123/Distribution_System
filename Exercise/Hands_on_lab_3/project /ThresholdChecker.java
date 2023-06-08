import java.io.*;

public class ThresholdChecker {
    private double temperatureThreshold;
    private double soilHumidityThreshold;
    private double airHumidityThreshold;
    private double pHThreshold;
    private double luminosityThreshold;

    public ThresholdChecker(double temperatureThreshold, double soilHumidityThreshold,
                            double airHumidityThreshold, double pHThreshold, double luminosityThreshold) {
        this.temperatureThreshold = temperatureThreshold;
        this.soilHumidityThreshold = soilHumidityThreshold;
        this.airHumidityThreshold = airHumidityThreshold;
        this.pHThreshold = pHThreshold;
        this.luminosityThreshold = luminosityThreshold;
    }

    public void checkThresholds(double temperature, double soilHumidity, double airHumidity,
                                double pH, double luminosity) {
        if (temperature > temperatureThreshold) {
            // Take action to open the fan or perform temperature regulation
            // ...
            System.out.println("Temperature threshold exceeded! Open the fan.");
        }

        if (soilHumidity < soilHumidityThreshold) {
            // Take action to water the soil or adjust irrigation
            // ...
            System.out.println("Soil humidity threshold exceeded! Water the soil.");
        }

        if (airHumidity > airHumidityThreshold) {
            // Take action to adjust humidity levels or activate dehumidifier
            // ...
            System.out.println("Air humidity threshold exceeded! Adjust humidity levels.");
        }

        if (pH < pHThreshold) {
            // Take action to balance pH levels or apply necessary treatments
            // ...
            System.out.println("pH threshold exceeded! Balance pH levels.");
        }

        if (luminosity > luminosityThreshold) {
            // Take action to adjust lighting conditions or shading
            // ...
            System.out.println("Luminosity threshold exceeded! Adjust lighting conditions.");
        }
    }
}


