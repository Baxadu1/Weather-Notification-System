import observers.*;
import strategies.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();


        Observer phone1 = new PhoneDisplay("Bakyt");
        Observer app = new AppDisplay("WeatherNow");
        Observer billboard = new BillboardDisplay();

        // Register them
        station.addObserver(phone1);
        station.addObserver(app);
        station.addObserver(billboard);

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nChoose Update Strategy:");
            System.out.println("1. Real-Time");
            System.out.println("2. Scheduled");
            System.out.println("3. Manual Input");
            System.out.println("4. Exit");
            System.out.print("→ ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> station.setUpdateStrategy(new RealTimeUpdate());
                case 2 -> station.setUpdateStrategy(new ScheduledUpdate());
                case 3 -> station.setUpdateStrategy(new ManualUpdate());
                case 4 -> {
                    System.out.println("Exiting system...");
                    return;
                }
                default -> System.out.println("Invalid option!");
            }

            station.fetchAndNotify();
        }
    }
}
