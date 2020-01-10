package AngelBBDD;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class XMLToDB extends DefaultHandler {

    private Connection connection = null;
    Statement stmt = null;

    private boolean yearSemaphore, titleSemaphore,  directorSemaphore, countrySemaphore, writeIntoDB;
    static String año, titulo, director, pais;

    private Connection getConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://192.168.22.157:3306/angelMateuEscola", "angel", "Mugiw@ra27");
            System.out.println("BBDD CORRECTO");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return connection;
    }



        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if(qName.equalsIgnoreCase("film")){
                año = attributes.getValue("produced");
                yearSemaphore = true;
            }else if(qName.equalsIgnoreCase("title")){
                titleSemaphore = true;
            }else if(qName.equalsIgnoreCase("director")){
                directorSemaphore = true;
            }else if(qName.equalsIgnoreCase("country")){
                countrySemaphore = true;
                writeIntoDB = false;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            if (yearSemaphore){
                yearSemaphore = false;
            }else if(titleSemaphore){
                titulo =  new String(ch, start, length);
                titleSemaphore = false;
            }else if(directorSemaphore){
                director = new String(ch, start, length);
                directorSemaphore = false;
            }else if(countrySemaphore){
                pais = new String(ch, start, length);
                countrySemaphore = false;
                writeIntoDB = true;
                insertData();
            }
        }

    private void insertData() {
        try {
            stmt = connection.createStatement();
//            stmt.executeUpdate("INSERT INTO Films " + "VALUES ('" + año + "', '" + titulo + "', '" + director + "', '" + pais + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void read (){
        try{
            File f = new File("films.xml");

            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();

            XMLToDB films = new XMLToDB();
            saxParser.parse(f, films);
            if (writeIntoDB) {
                System.out.println(año + "', '" + titulo + "', '" + director + "', '" + pais);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        XMLToDB filmsToDB = new XMLToDB();
        filmsToDB.getConnection();
        filmsToDB.read();
    }
}
