public class weatherdisplay implements weatherobserver {
    public String name;

    public weatherdisplay(String name) {
        this.name = name;
    }

    @Override
    public void update(weatherdata data) {
        System.out.println(name + " display updated:");
        System.out.println("  " + data);
        analyzeWeather(data);
    }

    private void analyzeWeather(weatherdata data) {
        if (data.getTemperature() > 30) {
            System.out.println("  Hot weather alert!");
        }
        if (data.getWindSpeed() > 15) {
            System.out.println("  Strong wind warning!");
        }
        if (data.getHumidity() > 80) {
            System.out.println("  High humidity notice");
        }
        System.out.println();
    }
}