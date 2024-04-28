import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1098);
            Calculator calculator = (Calculator) registry.lookup("CalculatorService");
            
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter first number: ");
            int num1 = scanner.nextInt();
            System.out.print("Enter second number: ");
            int num2 = scanner.nextInt();
            
            // Perform addition
            int result = calculator.add(num1, num2);
            System.out.println("Result of addition: " + result);
            
            scanner.close();
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
