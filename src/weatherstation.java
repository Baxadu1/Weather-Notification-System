import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

public class weatherstation {
    private List<weatherobserver> observers;
    private updatestrategy currentStrategy;
    private weatherdata currentWeather;
    private weatheradapter adapter;
    private StringBuilder updateLog;
    private SimpleDateFormat timeFormat;

    public weatherstation() {
        this.observers = new ArrayList<>();
        this.adapter = new weatheradapter();
        this.updateLog = new StringBuilder();
        this.timeFormat = new SimpleDateFormat("HH:mm:ss");
        setUpdateStrategy(new realtimeupdatestrategy());
    }

    public void setUpdateStrategy(updatestrategy strategy) {
        this.currentStrategy = strategy;
        logUpdate("Strategy changed to: " + strategy.getStrategyName());
    }

    public void addObserver(weatherobserver observer) {
        observers.add(observer);
        logUpdate("Observer added: " + observer.getClass().getSimpleName());
    }

    public void removeObserver(weatherobserver observer) {
        observers.remove(observer);
        logUpdate("Observer removed: " + observer.getClass().getSimpleName());
    }

    public void removeObserver(int index) {
        if (index >= 0 && index < observers.size()) {
            weatherobserver observer = observers.remove(index);
            logUpdate("Observer removed: " + observer.getClass().getSimpleName());
        }
    }

    public List<weatherobserver> getObservers() {
        return new ArrayList<>(observers);
    }

    public void updateWeatherData() {
        this.currentWeather = currentStrategy.fetchWeatherData();
        logUpdate("Weather data updated using: " + currentStrategy.getStrategyName());
        notifyObservers();
    }

    public void updateWeatherData(weatherdata data) {
        this.currentWeather = data;
        logUpdate("Weather data manually updated");
        notifyObservers();
    }

    private void notifyObservers() {
        for (weatherobserver observer : observers) {
            observer.update(currentWeather);
        }
    }

    public void addFahrenheitData(double fahrenheitTemp, double humidity, double windSpeedMph, double feelsLikeF) {
        weatherdata convertedData = adapter.convertFromFahrenheit(fahrenheitTemp, humidity, windSpeedMph, feelsLikeF);
        updateWeatherData(convertedData);
    }

    public void addKphData(double temp, double humidity, double windSpeedKph, double feelsLike) {
        weatherdata convertedData = adapter.convertFromKph(temp, humidity, windSpeedKph, feelsLike);
        updateWeatherData(convertedData);
    }

    public weatherdata getCurrentWeather() {
        return currentWeather;
    }

    public String getLastUpdateLog() {
        return updateLog.toString();
    }

    private void logUpdate(String message) {
        String timestamp = timeFormat.format(new Date());
        updateLog.insert(0, "[" + timestamp + "] " + message + "\n");
        if (updateLog.length() > 1000) {
            updateLog.setLength(1000);
        }
    }
}