package observers;

public class PhoneDisplay implements Observer {
    private String name;

    public PhoneDisplay(String name) {
        this.name = name;
    }

    @Override
    public void update(String weatherData) {
        System.out.println("[Phone: " + name + "] New weather data → " + weatherData);
    }
}
