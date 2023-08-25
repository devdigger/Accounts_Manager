import java.sql.*;
import java.util.*;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Tools extends Accounts_manager {
    static boolean checkVal(int a_no) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM BANK ;");
            while (rs.next()) {
                int a = rs.getInt("a_no");
                if (a == a_no) {
                    return true;
                }
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return false;
    }
    static void printDetails(int a_no, String name, int balance) {
        System.out.println("\nACCOUNT NUMBER = " + a_no + "\nNAME = " + name + "\nCURRENT BALANCE = " + balance + "\nDATE : " + java.time.LocalDate.now() + "\nTIME : " + java.time.LocalTime.now());
    }
    static void headers() {
        System.out.print("------------------------");
    }
}


public class Accounts_manager {
    static Connection c = null;
    static Statement stmt = null;
    static //creating Database
    {

        File file = new File("accounts.db");
        if (file.exists()) //check whether database exists
        {
            System.out.print("This database name already exists\n");
        } else {
            c = DatabaseConnection.getConnection();
            System.out.println("Opened database successfully");
        }
    }

    static void looper() {
        Tools ob = new Tools();

        Scanner sc = new Scanner(System.in);
        try {
            c = DatabaseConnection.getConnection();
            boolean exists = c.getMetaData().getTables(null, null, "BANK", null).next();
            stmt = c.createStatement();
            if (!exists) {

                String sql = "CREATE TABLE BANK " +
                    "(A_NO INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " BALANCE        INT     NOT NULL) ";
                stmt.executeUpdate(sql);
                stmt.close();
                System.out.println("Table created successfully");
            } else {
                System.out.println("Table exists !");
            }

            while (true) {
                String name;
                int a_no;
                int balance;
                int ch = 0;
                System.out.println("--------------------------");
                System.out.println("1. Create Account ");
                System.out.println("2. Deposit Money ");
                System.out.println("3. WithDraw Money ");
                System.out.println("4. Account Inquiry ");
                System.out.println("5. Delete Account ");
                System.out.println("6. EXIT ");
                System.out.println("--------------------------");
                System.out.print("Enter Choice : ");
                ch = sc.nextInt();
                if (ch == 1) {
                    System.out.print("Enter Name : ");
                    name = sc.next();
                    System.out.print("Enter Account Number : ");
                    a_no = sc.nextInt();
                    if (ob.checkVal(a_no)) {
                        System.out.println("ACCOUNT ALREADY EXISTS");
                        continue;
                    }
                    System.out.print("Enter Deposited Amount (0 if none) : ");
                    balance = sc.nextInt();
                    String sql = "INSERT INTO BANK (A_NO,NAME,BALANCE) " +
                        "VALUES (" + Integer.toString(a_no) + ", '" + name + "', " + Integer.toString(balance) + ");";

                    //System.out.println(sql);
                    stmt.executeUpdate(sql);
                    System.out.println("NEW ACCOUNT " + a_no + " CREATED !");
                }
                if (ch == 2) {
                    System.out.print("Enter Account Number : ");
                    a_no = sc.nextInt();
                    if (!ob.checkVal(a_no)) {
                        System.out.println("ACCOUNT NOT FOUND");
                        continue;
                    }
                    System.out.print("Enter Amount Deposited : ");
                    balance = sc.nextInt();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM BANK where A_NO=" + Integer.toString(a_no) + ";");
                    int bal = rs.getInt("balance");
                    balance += bal;
                    String sql = "UPDATE BANK set BALANCE = " + Integer.toString(balance) + " where A_NO=" + Integer.toString(a_no) + ";";
                    stmt.executeUpdate(sql);
                    //System.out.println(sql);
                    System.out.println("BALANCE UPDATED!");
                }
                if (ch == 3) {
                    System.out.print("Enter Account Number : ");
                    a_no = sc.nextInt();
                    if (!ob.checkVal(a_no)) {
                        System.out.println("ACCOUNT NOT FOUND");
                        continue;
                    }
                    System.out.print("Enter Amount Withdrwan : ");
                    balance = sc.nextInt();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM BANK where A_NO=" + Integer.toString(a_no) + ";");
                    int bal = rs.getInt("balance");
                    balance = bal - balance;
                    if (balance < 0) {
                        System.out.println("INSUFFICIENT BALANCE !");
                        continue;
                    }
                    String sql = "UPDATE BANK set BALANCE = " + Integer.toString(balance) + " where A_NO=" + Integer.toString(a_no) + ";";
                    stmt.executeUpdate(sql);
                    //System.out.println(sql);
                    System.out.println("BALANCE UPDATED!");
                }
                if (ch == 4) {
                    System.out.print("Enter Account Number : ");
                    a_no = sc.nextInt();
                    if (!ob.checkVal(a_no)) {
                        System.out.println("ACCOUNT NOT FOUND");
                        continue;
                    }
                    String q = "SELECT * FROM BANK where A_NO=" + Integer.toString(a_no) + ";";
                    ResultSet rs = stmt.executeQuery(q);
                    int b = rs.getInt("balance");
                    String n = rs.getString("name");
                    int a = rs.getInt("a_no");
                    ob.headers();
                    ob.printDetails(a, n, b);
                    ob.headers();
                }
                if (ch == 5) {
                    System.out.print("Enter Account Number : ");
                    a_no = sc.nextInt();

                    if (!ob.checkVal(a_no)) {
                        System.out.println("ACCOUNT NOT FOUND");
                        continue;
                    }
                    String q = "SELECT * FROM BANK where A_NO=" + Integer.toString(a_no) + ";";
                    ResultSet rs = stmt.executeQuery(q);
                    int bal = rs.getInt("balance");
                    System.out.println(bal);
                    if (bal != 0) {
                        System.out.println("Account NOT 0 BALANCE,can't delete !");
                    } else {
                        String sql = "DELETE from BANK where A_NO=" + Integer.toString(a_no) + ";";
                        stmt.executeUpdate(sql);
                        System.out.println("ACCOUNT DELETED !");
                    }
                }
                if (ch == 6) {
                    DatabaseConnection.closeConnection();
                    System.exit(0);
                }
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    public static void main(String args[]) {
        try {
            looper();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            looper();
        }
    }
}
