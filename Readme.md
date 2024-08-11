# <b>Overview

This project outlines the development of a distributed Calculator application using Java RMI (Remote Method Invocation). The application is designed to allow multiple clients to perform mathematical operations on their own data stack, managed by a centralized server. The program supports operations such as pushing values, pushing mathematical operations (minimum, maximum, least common multiple, and greatest common divisor), popping values, checking if the stack is empty, and a delayed pop feature. Each client has a unique identifier, ensuring isolated stacks and operations to prevent data overlap between sessions.

# <b>Design and Architecture

The project is divided into four main components:

Calculator Interface (Calculator.java):
This interface defines the methods that are remotely accessible to clients. These methods include operations for pushing values and operations onto the stack, popping values, checking stack status, and performing time-delayed pops. It serves as a contract between the client and server, specifying the operations that the server must implement.

Calculator Implementation (CalculatorImplementation.java):
This class implements the remote interface and defines the business logic of the operations. A critical feature of this class is the use of a HashMap to maintain separate stacks for each client identified by unique client IDs. This design allows the server to handle multiple clients concurrently, each with its own isolated data stack. Operations such as min, max, lcm, and gcd are implemented by manipulating the stack contents according to the operation specified.

Calculator Server (CalculatorServer.java):
The server setup involves creating an instance of the CalculatorImplementation, which is then bound to the RMI registry under the name "Calculator". This setup allows clients to locate the calculator service by this name and invoke methods on it. The server acts as the central node that clients connect to and request service from.

Calculator Client (CalculatorClient.java):
The client program provides a user interface for interacting with the calculator service. It allows users to perform operations such as pushing values or operations to their stack, popping results, and checking if their stack is empty. It maintains a session for each client by managing a unique client ID. The client program handles user inputs, displays menus, and communicates with the remote calculator server to perform operations.

# <b>Functionality</b>

Each component plays a vital role in the application’s functionality:

Remote Interface: Establishes a contract for services that the server offers, ensuring that the client and server agree on the methods that can be invoked remotely.

Implementation Class: Manages the core logic and state of each client’s data. This class is crucial for ensuring data isolation between clients and accurate computation of results based on stack operations.

Server Module: Acts as the gateway for all RMI communications. It registers the calculator service in the RMI registry, making it accessible to clients.

Client Module: Provides an interactive interface for the end-users. It handles input, communicates with the server, and displays results. The client module is designed to be simple yet functional, offering a menu-driven approach to accessing various calculator operations.

# Test Cases


Min result: 0

Max result: 20

Lcm result: 420

GCD result: 1

test case- 5: Maximum Calculation with Mixed Integers result: 30

test case- 6: Single Element Stack for Min result: 100

test case- 7: No Values before Operation result: -1

test case- 8: GCD All Same Numbers result: 7

test case- 9: Sequential Operations result: -1

test case- 11: Non-Existent Client ID result: -1

# References 

https://github.com/clariechek/JavaRMI/tree/main

https://gist.github.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc

https://www.tutorialspoint.com/java_rmi/java_rmi_application.html

https://www.cs.uic.edu/~troy/fall04/cs441/rmi/calc/index.html

https://www.geeksforgeeks.org/calculator-using-rmiremote-method-invocation-in-java/

ChatGpt# RMI-Java-Calculator-Distributed-Systems
