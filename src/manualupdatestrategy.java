public class manualupdatestrategy implements updatestrategy {
    private weatherdata lastManualData;

    @Override
    public weatherdata fetchWeatherData() {
        if (lastManualData == null) {
            lastManualData = new weatherdata.builder()
                    .temperature(22.0)
                    .humidity(65.0)
                    .windSpeed(5.5)
                    .feelsLike(23.0)
                    .build();
        }
        return lastManualData;
    }

    public void setManualData(double temp, double humidity, double windSpeed, double feelsLike) {
        this.lastManualData = new weatherdata.builder()
                .temperature(temp)
                .humidity(humidity)
                .windSpeed(windSpeed)
                .feelsLike(feelsLike)
                .build();
    }

    @Override
    public String getStrategyName() {
        return "Manual Input";
    }
}