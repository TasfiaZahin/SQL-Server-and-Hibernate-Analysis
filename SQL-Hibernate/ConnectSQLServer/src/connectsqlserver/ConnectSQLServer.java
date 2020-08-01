/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectsqlserver;

import java.util.Date;

import java.sql.*;
import java.util.Scanner;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ConnectSQLServer {

  public static void addVote(int id, int postId, int voteTypeId, String creationDate, DBConnect dbc)
  {
    //DBConnect dbc=new DBConnect("localhost","voteDB","sa","tasfia", "sqlserver");
    try
    {		
      /*String query = String.format("select * from votes where id = %d", id);
      ResultSet rs = dbc.searchDB(query);
      if(rs.next())
      {
        System.out.println("Vote with this Id already exisits");
      }
      else
      {*/
        String insertQuery = String.format("insert into votes(Id, PostID, VoteTypeId, CreationDate) values (%d, %d, %d, '%s')", 
            id, postId, voteTypeId, creationDate);
        dbc.updateDB(insertQuery);
      //}
    }
    catch(Exception e)
    {
      System.out.println("Exception in addVote: " + e);
    }
    /*finally
    {
      dbc.close();
    }*/
  }

  public static void addVote(int id, int postId, int voteTypeId, int userId, String creationDate, DBConnect dbc)
  {
    //DBConnect dbc=new DBConnect("localhost","voteDB","sa","tasfia", "sqlserver");
    try
    {		
      /*String query = String.format("select * from votes where id = %d", id);
      ResultSet rs = dbc.searchDB(query);
      if(rs.next())
      {
        System.out.println("Vote with this Id already exists");
      }
      else
      {*/
        String insertQuery = String.format("insert into votes(Id, PostID, VoteTypeId, UserId, CreationDate) values (%d, %d, %d, %d, '%s')", 
            id, postId, voteTypeId, userId, creationDate);
        dbc.updateDB(insertQuery);
      //}
    }
    catch(Exception e)
    {
      System.out.println("Exception in addVote: " + e);
    }
    /*finally
    {
      dbc.close();
    }*/
  }
  
  public static void deleteVote(int id)
  {
    DBConnect dbc=new DBConnect("localhost","voteDB","sa","tasfia", "sqlserver");
    try
    {		
      String query = String.format("select * from votes where id = %d", id);
      ResultSet rs = dbc.searchDB(query);
      if(rs.next())
      {
        String deleteQuery = String.format("delete from votes where Id = %d",id); 
        dbc.updateDB(deleteQuery);
      }
      else
      {
        System.out.println("Vote with this Id does not exist");
      }
    }
    catch(Exception e)
    {
      System.out.println("Exception in deleteVote: " + e);
    }
    finally
    {
      dbc.close();
    }
  }
  
  public static void updateVote(int id)
  {
    DBConnect dbc=new DBConnect("localhost","voteDB","sa","tasfia", "sqlserver");
    try
    {		
      String query = String.format("select * from votes where id = %d", id);
      ResultSet rs = dbc.searchDB(query);
      if(rs.next())
      {
        String updateQuery = String.format("update votes set VoteTypeId = 100 where Id = %d",id); 
        dbc.updateDB(updateQuery);
      }
      else
      {
        System.out.println("Vote with this Id does not exist");
      }
    }
    catch(Exception e)
    {
      System.out.println("Exception in deleteVote: " + e);
    }
    finally
    {
      dbc.close();
    }
  }
  
  public static void listVotes() 
  {
    DBConnect dbc=new DBConnect("localhost","voteDB","sa","tasfia", "sqlserver");
    try
    {
      String query = "select * from votes order by Id";
      ResultSet rs = dbc.searchDB(query);
      System.out.println("Vote List");
      System.out.println("Id	PostId	VoteTypeId      UserId  	CreationDate");
      while(rs.next()) 
      {
        System.out.print(rs.getInt("Id"));
        System.out.print("	      ");
        System.out.print(rs.getInt("PostId"));
        System.out.print("	      ");
        System.out.print(rs.getInt("VoteTypeId"));
        System.out.print("	      ");
        System.out.print(rs.getInt("UserId"));
        System.out.print("	       ");
        System.out.println(rs.getString("CreationDate"));
      }
    } 
    catch(Exception e) 
    {
      System.out.println("Exception in listVotes: " + e);
    } 
    finally
    {
      dbc.close();
    }
  }
  
  public static void Query1()
  {
    DBConnect dbc=new DBConnect("localhost","voteDB","sa","tasfia", "sqlserver");
    try
    {
      String query = "select * from votes where VoteTypeId = 5";
      ResultSet rs = dbc.searchDB(query);
      
      while(rs.next()) 
      {
        System.out.print(rs.getInt("Id"));
        System.out.print("	      ");
        System.out.print(rs.getInt("PostId"));
        System.out.print("	      ");
        System.out.print(rs.getInt("VoteTypeId"));
        System.out.print("	      ");
        System.out.print(rs.getInt("UserId"));
        System.out.print("	       ");
        System.out.println(rs.getString("CreationDate"));
      }
    } 
    catch(Exception e) 
    {
      System.out.println("Exception in Query1: " + e);
    } 
    finally
    {
      dbc.close();
    }
  }
  
  public static void Query2()
  {
    DBConnect dbc=new DBConnect("localhost","voteDB","sa","tasfia", "sqlserver");
    try
    {
      String query = "select VoteTypeId,count(*) from votes group by VoteTypeId";
      ResultSet rs = dbc.searchDB(query);
      
      while(rs.next()) 
      {
        System.out.print(rs.getInt("Id"));
        System.out.print("	      ");
        System.out.print(rs.getInt("PostId"));
        System.out.print("	      ");
        System.out.print(rs.getInt("VoteTypeId"));
        System.out.print("	      ");
        System.out.print(rs.getInt("UserId"));
        System.out.print("	       ");
        System.out.println(rs.getString("CreationDate"));
      }
    } 
    catch(Exception e) 
    {
      System.out.println("Exception in Query1: " + e);
    } 
    finally
    {
      dbc.close();
    }
  }
 
  public static void main(String args[])
  {
    System.out.println("connected");
    Parse parse =new Parse();

    Date startDate = new Date();
    Query1();
    Date endDate  = new Date();
    
    long time = endDate.getTime() - startDate.getTime();
    System.out.println(time);
    
    //listVotes();
    //DBConnect dbc=new DBConnect("localhost","voteDB","sa","tasfia", "sqlserver");
    //addVote(0,0,0,0,"0-0-0",dbc);
    
  }  
    
 }


    

