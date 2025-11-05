package strategies;

import java.util.Random;

public class RealTimeUpdate implements UpdateStrategy {
    @Override
    public String fetchWeatherData() {
        // Simulate live data from sensors
        Random r = new Random();
        return "Real-time Update: Temperature = " + (15 + r.nextInt(10)) + "°C, Humidity = " + (40 + r.nextInt(20)) + "%";
    }
}
