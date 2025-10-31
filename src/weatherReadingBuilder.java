import java.time.Instant;

public class weatherReadingBuilder {
    private Double temperatureC;
    private Double humidityPct;
    private Double pressureHpa;
    private Double windMs;
    private String source = "unknown";
    private Instant timestamp = Instant.now();

    public weatherReadingBuilder temperatureC(double v) { this.temperatureC = v; return this; }
    public weatherReadingBuilder humidityPct(double v) { this.humidityPct = v; return this; }
    public weatherReadingBuilder pressureHpa(double v) { this.pressureHpa = v; return this; }
    public weatherReadingBuilder windMs(double v) { this.windMs = v; return this; }
    public weatherReadingBuilder source(String s) { this.source = s; return this; }
    public weatherReadingBuilder timestamp(Instant t) { this.timestamp = t; return this; }

    public weatherReading build() {
        if (temperatureC == null || humidityPct == null || pressureHpa == null || windMs == null) {
            throw new IllegalStateException("missing fields");
        }
        return new weatherReading(temperatureC, humidityPct, pressureHpa, windMs, source, timestamp);
    }
}
