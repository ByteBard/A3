import edu.gatech.cs6310.DeliveryService;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to the Grocery Express Delivery Service, Ming!");
        DeliveryService simulator = new DeliveryService();
        simulator.commandLoop();
    }
}