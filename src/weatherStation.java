import java.util.ArrayList;
import java.util.List;

public class weatherStation {
    private final List<observer> observers = new ArrayList<>();
    private updateStrategy strategy;
    private weatherReading last;

    public weatherStation(updateStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(updateStrategy strategy) {
        this.strategy = strategy;
    }

    public String currentStrategyName() {
        return strategy == null ? "none" : strategy.name();
    }

    public void addObserver(observer o) {
        observers.add(o);
    }

    public void removeObserver(String id) {
        observers.removeIf(o -> o.id().equals(id));
    }

    public weatherReading last() {
        return last;
    }

    public weatherReading tick() {
        if (strategy == null) throw new IllegalStateException("no strategy");
        weatherReading r = strategy.fetch();
        last = r;
        for (observer o : observers) o.onUpdate(r);
        return r;
    }
}
