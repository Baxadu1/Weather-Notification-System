import java.time.Instant;

public class weatherReadingBuilder {
    private Double temperatureC;
    private Double humidityPct;

    private Double windMs;

    public weatherReadingBuilder temperatureC(double v) { this.temperatureC = v; return this; }
    public weatherReadingBuilder humidityPct(double v) { this.humidityPct = v; return this; }
    public weatherReadingBuilder windMs(double v) { this.windMs = v; return this; }


    public weatherReading build() {
        if (temperatureC == null || humidityPct == null || windMs == null) {
            throw new IllegalStateException("missing fields");
        }
        return new weatherReading(temperatureC, humidityPct, windMs);
    }
}
