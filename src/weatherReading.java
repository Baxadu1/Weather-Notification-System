public class weatherReading {
    private final double temperatureC;
    private final double humidityPct;

    private final double windMs;

    weatherReading(double temperatureC, double humidityPct, double pressureHpa) {
        this.temperatureC = temperatureC;
        this.humidityPct = humidityPct;
        this.windMs = windMs();
    }

    public double temperatureC() { return temperatureC; }
    public double humidityPct() { return humidityPct; }
    public double windMs() { return windMs; }



}
