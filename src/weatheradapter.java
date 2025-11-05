public class weatheradapter {
    public weatherdata convertFromFahrenheit(double fahrenheitTemp, double humidity, double windSpeedMph, double feelsLikeF) {
        double celsiusTemp = (fahrenheitTemp - 32) * 5/9;
        double celsiusFeelsLike = (feelsLikeF - 32) * 5/9;
        double windSpeedMs = windSpeedMph * 0.44704;

        return new weatherdata.builder()
                .temperature(Math.round(celsiusTemp * 10.0) / 10.0)
                .humidity(humidity)
                .windSpeed(Math.round(windSpeedMs * 10.0) / 10.0)
                .feelsLike(Math.round(celsiusFeelsLike * 10.0) / 10.0)
                .build();
    }

    public weatherdata convertFromKph(double temp, double humidity, double windSpeedKph, double feelsLike) {
        double windSpeedMs = windSpeedKph * 0.277778;

        return new weatherdata.builder()
                .temperature(temp)
                .humidity(humidity)
                .windSpeed(Math.round(windSpeedMs * 10.0) / 10.0)
                .feelsLike(feelsLike)
                .build();
    }
}