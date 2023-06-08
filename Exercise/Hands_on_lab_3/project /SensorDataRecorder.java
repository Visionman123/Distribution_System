import java.io.*;

import java.util.Random;

public class SensorDataRecorder {
    private Random random;

    public SensorDataRecorder() {
        random = new Random();
    }

    public double getTemperature() {
        // Generate a random temperature between 20 and 40 degrees Celsius
        return 20 + random.nextDouble() * 20;
    }

    public double getSoilHumidity() {
        // Generate a random soil humidity between 0 and 100
        return random.nextDouble() * 100;
    }

    public double getAirHumidity() {
        // Generate a random air humidity between 40 and 60
        return 40 + random.nextDouble() * 20;
    }

    public double getpH() {
        // Generate a random pH value between 5 and 8
        return 5 + random.nextDouble() * 3;
    }

    public double getLuminosity() {
        // Generate a random luminosity between 0 and 1000 lux
        return random.nextDouble() * 1000;
    }
}

