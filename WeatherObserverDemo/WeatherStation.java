import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class WeatherStation implements Observable {

    private static final Logger logger = Logger.getLogger(WeatherStation.class.getName());
    private final List<Observer> observers = new ArrayList<>();
    private int temperature;

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
        logger.info(observer.getClass().getSimpleName() + " subscribed.");
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
        logger.info(observer.getClass().getSimpleName() + " unsubscribed.");
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            try {
                observer.update(temperature);
            } catch (Exception e) {
                logger.severe("Error notifying " + observer.getClass().getSimpleName() + ": " + e.getMessage());
            }
        }
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        logger.info("Temperature updated to " + temperature);
        notifyObservers();
    }

    // Start gathering input from user
    public void startMonitoring() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter temperature (or type 'exit'): ");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) break;
                int temp = Integer.parseInt(input);
                setTemperature(temp);
            } catch (NumberFormatException e) {
                logger.warning("Invalid input. Please enter a number.");
            } catch (Exception e) {
                logger.severe("Unexpected error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
