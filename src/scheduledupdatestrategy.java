import java.util.Random;

public class scheduledupdatestrategy implements updatestrategy {
    private Random random = new Random();
    private double lastTemp = 20.0;

    @Override
    public weatherdata fetchWeatherData() {
        double tempChange = (random.nextDouble() - 0.5) * 4;
        lastTemp += tempChange;

        double humidity = 40 + random.nextDouble() * 40;
        double windSpeed = 3 + random.nextDouble() * 15;
        double feelsLike = calculateFeelsLike(lastTemp, humidity, windSpeed);

        return new weatherdata.builder()
                .temperature(Math.round(lastTemp * 10.0) / 10.0)
                .humidity(Math.round(humidity * 10.0) / 10.0)
                .windSpeed(Math.round(windSpeed * 10.0) / 10.0)
                .feelsLike(Math.round(feelsLike * 10.0) / 10.0)
                .build();
    }

    private double calculateFeelsLike(double temp, double humidity, double windSpeed) {
        return temp - (windSpeed * 0.1) + (humidity * 0.05);
    }

    @Override
    public String getStrategyName() {
        return "Scheduled Batch Updates";
    }
}