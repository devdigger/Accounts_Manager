# Simple Banking System with JDBC

This project implements a simple banking system using Java and JDBC (Java Database Connectivity). It allows users to create accounts, deposit and withdraw money, check balances, and delete accounts. The project uses an SQLite database to store account information.

## Features

- Create new bank accounts with unique account numbers.
- Deposit money into existing accounts.
- Withdraw money from existing accounts, with checks for sufficient balance.
- Check account balance and details.
- Delete accounts with a zero balance.
- Command-line interface for easy interaction.

## Prerequisites

- Java Development Kit (JDK)
- sqlite-jdbc.jar (SQLite JDBC Driver)

## Getting Started

1. Clone the repository:

    ```
    git clone https://github.com/devdigger/Accounts_Manager.git
    cd Accounts_Manager
    ```

2. Download the [sqlite-jdbc.jar](https://github.com/xerial/sqlite-jdbc/releases) driver and place it in the project directory.

3. Compile the Java code using the following command:

    ```
    javac -classpath ".;sqlite-jdbc.jar" Accounts_manager.java
    ```

4. Run the program:

    ```
    java -classpath ".;sqlite-jdbc.jar" Accounts_manager
    ```

## Usage

- The program provides a simple command-line interface for interacting with the banking system.
- Follow the on-screen instructions to create accounts, perform transactions, inquire about account details, and more.

## Limitations

- Basic text-based interface without graphical user interface (GUI).
- No user authentication, making it unsuitable for real-world use.
- Limited error handling and exception management.
- No encryption or security features for sensitive data.

## Future Enhancements

- Implement a GUI for a more user-friendly interface.
- Add user authentication and security measures.
- Enhance error handling with meaningful error messages.
- Implement transaction management for data consistency.
- Support multiple account types (e.g., savings, checking).
- Improve input validation and data sanitization.
- Integrate logging for better tracking and debugging.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request if you find any issues or want to improve the project.
