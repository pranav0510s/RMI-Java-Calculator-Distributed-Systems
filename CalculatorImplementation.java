import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class CalculatorImplementation implements Calculator {
    // Constructor to handle RemoteException
    public CalculatorImplementation() throws RemoteException {
        super();
    }

    // Client-specific stacks to manage isolated environments
    private Map<Integer, Stack<Integer>> sessionStacks = new HashMap<>();

    // Method to register new client and create a dedicated stack
    public boolean registerClient(int clientID) throws RemoteException {
        if (sessionStacks.containsKey(clientID)) {
            return false;  // Client already exists
        }
        sessionStacks.put(clientID, new Stack<>());  // New stack for new client
        return true;
    }

    // Pushes a value onto the specified client's stack
    public void pushValue(int clientID, int value) throws RemoteException {
      Stack<Integer> stack = sessionStacks.get(clientID);
    if (stack == null) {
        System.out.println("No stack found for client ID: " + clientID + ", initializing new stack.");
        stack = new Stack<>();
        sessionStacks.put(clientID, stack);
    }
        System.out.println("Client " + clientID + " submits value: " + value);
        sessionStacks.get(clientID).push(value);
    }

    // Processes an operation on the specified client's stack
    public void pushOperation(String operation, int clientID) throws RemoteException {
        System.out.println("Operation requested by Client " + clientID + ": " + operation);

        if (!sessionStacks.containsKey(clientID) || sessionStacks.get(clientID).isEmpty()) {
            System.out.println("No values to operate on for Client " + clientID);
            return;
        }

        int result = sessionStacks.get(clientID).pop();
        while (!sessionStacks.get(clientID).isEmpty()) {
            int nextValue = sessionStacks.get(clientID).pop();
            switch (operation) {
                case "min":
                    result = Math.min(result, nextValue);
                    break;
                case "max":
                    result = Math.max(result, nextValue);
                    break;
                case "lcm":
                    result = lcm(result, nextValue);
                    break;
                case "gcd":
                    result = gcd(result, nextValue);
                    break;
                default:
                    System.out.println("Unsupported operation: " + operation);
                    return;
            }
        }
        sessionStacks.get(clientID).push(result);
    }

    // Least common multiple calculation
    private int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }

    // Greatest common divisor calculation
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // Pops the top value from the client's stack
    public int pop(int clientID) throws RemoteException {
      Stack<Integer> stack = sessionStacks.get(clientID);
    if (stack == null) {
        System.out.println("No stack found for client ID: " + clientID);
        return -1; // or throw an exception
    }
        if (sessionStacks.get(clientID).isEmpty()){
            System.out.println("Stack empty for Client " + clientID);
            return -1;
        }
        int value = sessionStacks.get(clientID).pop();
        System.out.println("Value retrieved by Client " + clientID + ": " + value);
        return value;
    }

    // Checks if a client's stack is empty
    public boolean isEmpty(int clientID) throws RemoteException {
        return sessionStacks.get(clientID).isEmpty();
    }

    // Implements a delay before popping the value
    public int delayPop(int millis, int clientID) throws RemoteException {
        try {
            System.out.println("Delaying retrieval by " + millis + " ms for Client " + clientID);
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted: " + e.getMessage());
        }
        return pop(clientID);
    }
}
