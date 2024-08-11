import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry;
import java.rmi.server.Operation;  

public class CalculatorClient {  
   private CalculatorClient() {}  
   public static void main(String[] args) {  
      try {  
         // Getting the registry 
         Registry registry = LocateRegistry.getRegistry(null); 
    
         // Looking up the registry for the remote object 

         //test case-1: Minimum Calculation with Positive Integers
         Calculator stub = (Calculator) registry.lookup("Calculator"); 
         stub.pushValue(2,0);
         stub.pushValue(2,1);
         stub.pushValue(2,2);
         stub.pushValue(2,4);
         stub.pushValue(2,3);
         stub.pushOperation("min",2);
         System.out.println("Min result: " + stub.pop(2)); 
         //test case-2: Maximum Calculation with Positive Integers
         stub.pushValue(2,4);
         stub.pushValue(2,5);
         stub.pushValue(2,4);
         stub.pushValue(2,20);
         stub.pushValue(2,10);
         stub.pushOperation("max",2);
         System.out.println("Max result: " + stub.pop(2)); 
         //test case-3: LCM Calculation with Positive Integers
         stub.pushValue(2,1);
         stub.pushValue(2,4);
         stub.pushValue(2,5);
         stub.pushValue(2,6);
         stub.pushValue(2,7);
         stub.pushOperation("lcm",2);
         System.out.println("Lcm result: " + stub.pop(2)); 


         //test case-4: GCD Calculation with Positive Integers
         stub.pushValue(2,3);
         stub.pushValue(2,4);
         stub.pushValue(2,5);
         stub.pushValue(2,6);
         stub.pushValue(2,4);

         stub.pushOperation("gcd", 2);
         System.out.println("GCD result: " + stub.pop(2)); 


         //test case- 5: Maximum Calculation with Mixed Integers
         stub.pushValue(2,-10);
         stub.pushValue(2,-20);
         stub.pushValue(2,0);
         stub.pushValue(2,30);
         stub.pushOperation("max", 2);
         System.out.println("test case- 5: Maximum Calculation with Mixed Integers result: " + stub.pop(2)); 


         //test case- 6: Single Element Stack for Min

         stub.pushValue(2,100);
         stub.pushOperation("min", 2);
         System.out.println("test case- 6: Single Element Stack for Min result: " + stub.pop(2)); 


         //test case- 7: No Values before Operation
         stub.pushOperation("max", 2);
         System.out.println("test case- 7: No Values before Operation result: " + stub.pop(2)); 


         //test case- 8: GCD All Same Numbers

         stub.pushValue(2,7);
         stub.pushValue(2,7);
         stub.pushValue(2,7);
         stub.pushOperation("gcd", 2);
         System.out.println("test case- 8: GCD All Same Numbers result: " + stub.pop(2)); 


         //test case- 9: Sequential Operations
         stub.pushValue(2,12);
         stub.pushValue(2,15);
         stub.pushOperation("gcd", 2);
         stub.pushValue(2,9);
         stub.pushOperation("gcd", 2);
         System.out.println("result: " + stub.pop(2)); 


         //test case- 10: Operations on an Empty Stack
         stub.pushOperation("min", 2);
         System.out.println("test case- 9: Sequential Operations result: " + stub.pop(2)); 


         //test case- 11: Non-Existent Client ID
         stub.pushValue(999, 50); 
         stub.pushOperation("min", 999);
         System.out.println("test case- 11: Non-Existent Client ID result: " + stub.pop(2)); 










         //stub.pop();
         // Calling the remote method using the obtained object 
         // stub.printMsg(); 
      } catch (Exception e) {
         System.err.println("Client exception: " + e.toString()); 
         e.printStackTrace(); 
      } 
   } 
}
