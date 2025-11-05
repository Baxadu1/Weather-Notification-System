public class mobileapp implements weatherobserver {
    public String appName;

    public mobileapp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(weatherdata data) {
        System.out.println(appName + " Mobile Notification:");
        System.out.println("   Temperature: " + data.getTemperature() + "C");
        System.out.println("   Humidity: " + data.getHumidity() + "% humidity");
        System.out.println("   Wind: " + data.getWindSpeed() + " m/s wind");
        System.out.println("   Feels like " + data.getFeelsLike() + "C");

        if (Math.abs(data.getTemperature() - data.getFeelsLike()) > 3) {
            System.out.println("   Actual temperature feels different!");
        }
        System.out.println();
    }
}