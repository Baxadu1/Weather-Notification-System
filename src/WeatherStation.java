import observers.Observer;
import strategies.UpdateStrategy;

import java.util.ArrayList;
import java.util.List;

public class WeatherStation {
    private List<Observer> observers = new ArrayList<>();
    private UpdateStrategy updateStrategy;

    public void setUpdateStrategy(UpdateStrategy updateStrategy) {
        this.updateStrategy = updateStrategy;
        System.out.println("Switched update strategy to: " + updateStrategy.getClass().getSimpleName());
    }

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers(String weatherData) {
        for (Observer o : observers) {
            o.update(weatherData);
        }
    }

    public void fetchAndNotify() {
        if (updateStrategy == null) {
            System.out.println("No update strategy selected!");
            return;
        }
        String data = updateStrategy.fetchWeatherData();
        notifyObservers(data);
    }
}
