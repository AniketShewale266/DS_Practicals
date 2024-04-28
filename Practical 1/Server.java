import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try {
            Calculator calculator = new CalculatorImpl();
            Registry registry = LocateRegistry.createRegistry(1098);
            registry.bind("CalculatorService", calculator);
            System.out.println("Server is running...");
            
            // Wait for input to stop the server
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press any key to stop the server...");
            scanner.nextLine();
            scanner.close();
            
            // Unbind and exit
            registry.unbind("CalculatorService");
            System.out.println("Server stopped.");
            System.exit(0);
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
