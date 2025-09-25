public class LaptopDisplay implements Observer {
    @Override
    public void update(int temperature) {
        System.out.println("Laptop display: Temperature updated to " + temperature + "°C");
    }
}
