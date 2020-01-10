package AngelBBDD;

import java.sql.*;
import java.util.Scanner;

public class BDConnection {
    public BDConnection() {
    }

    private Connection connection = null;
    Scanner sc = new Scanner(System.in);
    Statement stmt = null;

    private Connection getConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://192.168.22.157:3306/angelMateuEscola", "angel", "Mugiw@ra27");
            System.out.println("BBDD CORRECTO");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return connection;
    }

    private void getQuerySelect() {
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Alumnes");
        while (rs.next()) {
            String nom = rs.getString("Nom");
            String edat = rs.getString("Edat");
            System.out.println(nom + " " + edat);
        }
        connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertData(){
        String nom = sc.nextLine();
        String cognom = sc.nextLine();
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO Alumnes " + "VALUES ('" + nom + "', '" + cognom + "', '38', 'Puig')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getQuerySelectBetween() {
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Alumnes WHERE Edat BETWEEN 25 AND 48");
            while (rs.next()) {
                String nom = rs.getString("Nom");
                String edat = rs.getString("Edat");
                System.out.println(nom + " " + edat);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BDConnection bdConnection = new BDConnection();
        Connection connection = bdConnection.getConnection();
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Query Select" +
                "\n2. Insert Data" +
                "\n3. Query Select 25-48");
        int option = sc.nextInt();
        switch (option){
            case 1:
                bdConnection.getQuerySelect();
                break;
            case 2:
                bdConnection.insertData();
                break;
            case 3:
                bdConnection.getQuerySelectBetween();
                break;
        }
    }
}

