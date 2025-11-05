import java.util.Random;

public class realtimeupdatestrategy implements updatestrategy {
    private Random random = new Random();

    @Override
    public weatherdata fetchWeatherData() {
        double baseTemp = 15 + random.nextDouble() * 20;
        double humidity = 30 + random.nextDouble() * 60;
        double windSpeed = 1 + random.nextDouble() * 25;
        double feelsLike = calculateFeelsLike(baseTemp, humidity, windSpeed);

        return new weatherdata.builder()
                .temperature(Math.round(baseTemp * 10.0) / 10.0)
                .humidity(Math.round(humidity * 10.0) / 10.0)
                .windSpeed(Math.round(windSpeed * 10.0) / 10.0)
                .feelsLike(Math.round(feelsLike * 10.0) / 10.0)
                .build();
    }

    private double calculateFeelsLike(double temp, double humidity, double windSpeed) {
        double heatIndex = temp + 0.5 * (humidity / 100);
        double windChill = windSpeed > 5 ? temp - (windSpeed * 0.2) : temp;
        return (heatIndex + windChill) / 2;
    }

    @Override
    public String getStrategyName() {
        return "Real-time Sensor Polling";
    }
}