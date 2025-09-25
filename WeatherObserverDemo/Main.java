public class Main {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();
        PhoneDisplay phone = new PhoneDisplay();
        LaptopDisplay laptop = new LaptopDisplay();

        station.addObserver(phone);
        station.addObserver(laptop);

        station.startMonitoring(); // Starts user input loop
    }
}
