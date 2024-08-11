import java.rmi.registry.Registry; 
import java.rmi.registry.LocateRegistry; 
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject; 

public class CalculatorServer extends CalculatorImplementation { 
   public CalculatorServer() throws RemoteException {
      super();
   }

   // public CalculatorServer() {} 
   public static void main(String args[]) { 
      try { 
         // Instantiating the implementation class 
         CalculatorImplementation obj = new CalculatorImplementation(); 
    
         // Exporting the object of implementation class  
         // (here we are exporting the remote object to the stub) 
         Calculator stub = (Calculator) UnicastRemoteObject.exportObject(obj, 0);  
         
         // Binding the remote object (stub) in the registry 
         Registry registry = LocateRegistry.getRegistry(); 
         
         registry.bind("Calculator", stub);  
         System.err.println("Server ready"); 
      } catch (Exception e) { 
         System.err.println("Server exception: " + e.toString()); 
         e.printStackTrace(); 
      } 
   } 
} 