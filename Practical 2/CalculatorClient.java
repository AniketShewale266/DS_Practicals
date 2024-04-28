import org.omg.CosNaming.*;

import CalculatorApp.Calculator;
import CalculatorApp.CalculatorHelper;

import org.omg.CORBA.*;

public class CalculatorClient {
    public static void main(String args[]) {
        try {
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            Calculator calculator = (Calculator) CalculatorHelper.narrow(ncRef.resolve_str("Calculator"));

            float num1 = 10;
            float num2 = 5;

            float result = calculator.add(num1, num2);
            System.out.println("Addition Result: " + result);

            result = calculator.subtract(num1, num2);
            System.out.println("Subtraction Result: " + result);

            result = calculator.multiply(num1, num2);
            System.out.println("Multiplication Result: " + result);

            result = calculator.divide(num1, num2);
            System.out.println("Division Result: " + result);
        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace(System.out);
        }
    }
}
