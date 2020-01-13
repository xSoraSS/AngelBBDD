//package AngelBBDD;
//
//import org.w3c.dom.Document;
//import org.xml.sax.Attributes;
//import org.xml.sax.helpers.DefaultHandler;
//
//import javax.xml.parsers.*;
//import java.io.File;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class XMLToDB extends DefaultHandler {
//
//    private Connection connection = null;
//    Statement stmt = null;
//
//    private boolean yearSemaphore, titleSemaphore,  directorSemaphore, countrySemaphore, writeIntoDB;
//    static String año, titulo, director, pais;
//
//    private Connection getConnection(){
//        try {
//            connection = DriverManager.getConnection("jdbc:mysql://192.168.22.157:3306/angelMateuEscola", "angel", "Mugiw@ra27");
//            System.out.println("BBDD CORRECTO");
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return connection;
//    }
//
//
//
////    private void insertData() {
////        try {
////            stmt = connection.createStatement();
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }                    stmt = connection.createStatement();
////                    stmt.executeUpdate("INSERT INTO Films " + "VALUES ('" + año + "', '" + titulo + "', '" + director + "', '" + pais + "')");
////    }
//
//    public void read () throws ParserConfigurationException {
//            File f = new File("films.xml");
//
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = dBuilder.newDocument();
//
//
//    }
//
//    public static void main(String[] args) {
//        XMLToDB filmsToDB = new XMLToDB();
//        filmsToDB.getConnection();
//        filmsToDB.read();
//    }
//}
