package observers;

public class BillboardDisplay implements Observer {
    @Override
    public void update(String weatherData) {
        System.out.println("[Billboard] Updated display → " + weatherData);
    }
}
