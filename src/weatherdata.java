public class weatherdata {
    private double temperature;
    private double humidity;
    private double windSpeed;
    private double feelsLike;

    private weatherdata(builder builder) {
        this.temperature = builder.temperature;
        this.humidity = builder.humidity;
        this.windSpeed = builder.windSpeed;
        this.feelsLike = builder.feelsLike;
    }

    public static class builder {
        private double temperature;
        private double humidity;
        private double windSpeed;
        private double feelsLike;

        public builder temperature(double temperature) {
            this.temperature = temperature;
            return this;
        }

        public builder humidity(double humidity) {
            this.humidity = humidity;
            return this;
        }

        public builder windSpeed(double windSpeed) {
            this.windSpeed = windSpeed;
            return this;
        }

        public builder feelsLike(double feelsLike) {
            this.feelsLike = feelsLike;
            return this;
        }

        public weatherdata build() {
            return new weatherdata(this);
        }
    }

    public double getTemperature() { return temperature; }
    public double getHumidity() { return humidity; }
    public double getWindSpeed() { return windSpeed; }
    public double getFeelsLike() { return feelsLike; }

    @Override
    public String toString() {
        return String.format("Temperature: %.1f°C, Humidity: %.1f%%, Wind: %.1f m/s, Feels like: %.1f°C",
                temperature, humidity, windSpeed, feelsLike);
    }
}