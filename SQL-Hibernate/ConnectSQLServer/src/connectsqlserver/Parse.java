/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectsqlserver;

import static connectsqlserver.ConnectSQLServer.listVotes;
import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author acer
 */
public class Parse {
    public Parse(){
    try {	
        DBConnect dbc=new DBConnect("localhost","voteDB","sa","tasfia", "sqlserver");
        dbc.connection.setAutoCommit(false);
        
        File directory = new File("D:\\Level 3 Term 1\\CSE 304 Database Sessional\\Project\\DB1");
        File[] files = directory.listFiles();
        for(int i=0;i<files.length;i++) {
            File inputFile = files[i];
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            UserHandler userhandler = new UserHandler(dbc);
            saxParser.parse(inputFile, userhandler);
            dbc.connection.commit();
        }
                    dbc.close();


        System.out.println("added");      
        //listVotes();
        
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
