package strategies;

import java.time.LocalTime;

public class ScheduledUpdate implements UpdateStrategy {
    @Override
    public String fetchWeatherData() {
        return "Scheduled Update (" + LocalTime.now() + "): Temperature = 20°C, Humidity = 55%";
    }
}
