import java.rmi.Remote; 
import java.rmi.RemoteException;  

// Creating Remote interface for our application 
public interface Calculator extends Remote {  
   void pushValue(int clientID, int value) throws RemoteException;
    void pushOperation(String operator, int clientID) throws RemoteException;
    int pop(int clientID) throws RemoteException;
    boolean isEmpty(int clientID) throws RemoteException;
    int delayPop(int millis, int clientID) throws RemoteException;
   //  void createNewClientStack(int clientID) throws RemoteException;
   //  boolean createNewClientID(int clientID) throws RemoteException;
   //  boolean hasTwoValues(int clientID) throws RemoteException;
   //  boolean hasZero(int clientID) throws RemoteException;
} 



