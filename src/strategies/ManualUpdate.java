package strategies;

import java.util.Scanner;

public class ManualUpdate implements UpdateStrategy {
    @Override
    public String fetchWeatherData() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter temperature: ");
        String temp = sc.nextLine();
        System.out.print("Enter humidity: ");
        String hum = sc.nextLine();
        return "Manual Update: Temperature = " + temp + "°C, Humidity = " + hum + "%";
    }
}
