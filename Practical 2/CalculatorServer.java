import org.omg.CosNaming.*;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import CalculatorApp.Calculator;
import CalculatorApp.CalculatorHelper;

import org.omg.CORBA.*;

public class CalculatorServer {
    public static void main(String args[]) {
        try {
            ORB orb = ORB.init(args, null);
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            CalculatorImpl calculatorImpl = new CalculatorImpl();

            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(calculatorImpl);
            Calculator href = CalculatorHelper.narrow(ref);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            NameComponent path[] = ncRef.to_name("Calculator");
            ncRef.rebind(path, href);

            System.out.println("CalculatorServer ready and waiting...");

            orb.run();
        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace(System.out);
        }
    }
}
