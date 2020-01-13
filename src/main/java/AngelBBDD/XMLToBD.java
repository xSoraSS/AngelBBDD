package AngelBBDD;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import java.sql.*;
import java.util.Scanner;

public class XMLToBD {

    public static void main(String[] args) {
        XMLToBD xmlToBD = new XMLToBD();
        xmlToBD.getConnection();
        xmlToBD.insertDataXMLToDB();
    }

    static Connection connection = null;

    private Connection getConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://192.168.22.157:3306/angelMateuEscola", "angel", "Mugiw@ra27");
            System.out.println("BBDD CORRECTO");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return connection;
    }

    private void insertDataXMLToDB(){
        String query;
        Document doc = createDocXML();

        try {
            Statement statement = connection.createStatement();
            NodeList films = doc.getElementsByTagName("Film");
            for (int i = 0; i <films.getLength() ; i++) {
                Element element = (Element) films.item(i);
                if (element.getNodeType() == Node.ELEMENT_NODE){
                    //Atributo año
                    String año = (element.getAttribute("produced"));
                    //Elementos hijos de film
                    String titulo = element.getElementsByTagName("Title").item(0).getTextContent();
                    String director = element.getElementsByTagName("Director").item(0).getTextContent();
                    String country = element.getElementsByTagName("Country").item(0).getTextContent();
                    //
                    query = "INSERT INTO Films (titulo, año, director, country) VALUES ('" + titulo + "', '" + año + "', '"+director+"', '"+country+"')";
                    statement.executeUpdate(query);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Document createDocXML(){
        Document doc = null;
        try{
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("films.xml");
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }
}
