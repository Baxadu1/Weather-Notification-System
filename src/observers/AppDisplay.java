package observers;

public class AppDisplay implements Observer {
    private String appName;

    public AppDisplay(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(String weatherData) {
        System.out.println("[App: " + appName + "] Received → " + weatherData);
    }
}
