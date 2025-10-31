import java.time.Instant;

public class weatherReading {
    private final double temperatureC;
    private final double humidityPct;
    private final double pressureHpa;
    private final double windMs;
    private final String source;
    private final Instant timestamp;

    weatherReading(double temperatureC, double humidityPct, double pressureHpa, double windMs, String source, Instant timestamp) {
        this.temperatureC = temperatureC;
        this.humidityPct = humidityPct;
        this.pressureHpa = pressureHpa;
        this.windMs = windMs;
        this.source = source;
        this.timestamp = timestamp;
    }

    public double temperatureC() { return temperatureC; }
    public double humidityPct() { return humidityPct; }
    public double pressureHpa() { return pressureHpa; }
    public double windMs() { return windMs; }
    public String source() { return source; }
    public Instant timestamp() { return timestamp; }


}
