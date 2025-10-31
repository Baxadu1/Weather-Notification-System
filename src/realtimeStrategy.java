import java.time.Instant;
import java.util.Random;

public class realtimeStrategy implements updateStrategy {
    private final Random rnd;

    public realtimeStrategy(long seed) {
        this.rnd = new Random(seed);
    }

    @Override
    public weatherReading fetch() {
        double t = -10 + rnd.nextDouble() * 40;
        double h = 15 + rnd.nextDouble() * 80;
        double p = 980 + rnd.nextDouble() * 50;
        double w = rnd.nextDouble() * 15;
        return new weatherReadingBuilder()
                .temperatureC(t)
                .humidityPct(h)
                .windMs(w)
                .build();
    }

    @Override
    public String name() {
        return "realtime";
    }
}
