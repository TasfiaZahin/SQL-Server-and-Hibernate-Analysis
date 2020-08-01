/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibtest;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author acer
 */
public class Hibtest {

    public static void addVote(int id, int postId, int voteTypeId, int userId, String creationDate)
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            Query qry = session.createQuery("from votes order by id");
            List l =qry.list();
            
            votes vote = new votes();
            vote.setId(id);
            vote.setPostId(postId);
            vote.setVoteTypeId(voteTypeId);
            vote.setUserId(userId);
            vote.setCreationDate(creationDate);
            
            if(l.contains(vote.getId()))
            {
                System.out.println("Vote with this Id already exisits");
            }
            else
            {
                session.beginTransaction();
                session.save(vote);
                session.getTransaction().commit();
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception in addVote: " + e);
        }
        finally
        {
            session.close();
        }
        
    }
  
    public static void deleteVote(int id)
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction trans = session.beginTransaction();
        
        try{
            
            String deleteQuery = ("DELETE votes WHERE Id = :ids");
            Query qry = session.createQuery(deleteQuery);
            qry.setInteger("ids", id);
            int result = qry.executeUpdate();
            System.out.println("Rows affected: " + result);
            trans.commit();
            
        }
        catch(Exception e)
        {
            trans.rollback();
            System.out.println("Exception in deleteVote: " + e);
        }
        finally
        {
            session.close();
        }
    }
    
    public static void updateVote(int id)
    {
        
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction trans = session.beginTransaction();
        
        try{
   
            String updateQuery = "update votes set VoteTypeId = 300 where Id = :ids";
            Query qry = session.createQuery(updateQuery);
            
            qry.setInteger("ids", id);
            int result = qry.executeUpdate();
            System.out.println("Rows affected: " + result);
            trans.commit();
        }
        catch(Exception e)
        {
            trans.rollback();
            System.out.println("Exception in updateVote: " + e);
        }
        finally
        {
            session.close();
        }
    }
    
    public static void listVotes()
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        
        try{
            session.beginTransaction();
            Query qry = session.createQuery("from votes order by id");

            List l =qry.list();
            System.out.println("Total Number Of Records : "+l.size());
            Iterator it = l.iterator();

            while(it.hasNext())
            {
                    votes o = (votes)it.next();
                    votes p = (votes)o;
                    System.out.println("Id : "+p.getId());
                    System.out.println("PostId : "+p.getPostId());
                    System.out.println("VoteTypeId : "+p.getVoteTypeId());
                    if(p.getVoteTypeId()==5)
                    {
                        System.out.println("UserId : "+p.getUserId());
                    }
                    System.out.println("CreationDate : "+p.getCreationDate());
                    System.out.println("----------------------");
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception in listeVotes: " + e);
        }
        finally
        {
            session.close();
        }
        
    }
    
    public static void Query1()
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        
        try{
            session.beginTransaction();
            Query qry = session.createQuery("from votes where VoteTypeId = 5");

            List l =qry.list();
            System.out.println("Total Number Of Records : "+l.size());
            Iterator it = l.iterator();

            while(it.hasNext())
            {
                    votes o = (votes)it.next();
                    votes p = (votes)o;
                    System.out.println("Id : "+p.getId());
                    System.out.println("PostId : "+p.getPostId());
                    System.out.println("VoteTypeId : "+p.getVoteTypeId());
                    if(p.getVoteTypeId()==5)
                    {
                        System.out.println("UserId : "+p.getUserId());
                    }
                    System.out.println("CreationDate : "+p.getCreationDate());
                    System.out.println("----------------------");
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception in listeVotes: " + e);
        }
        finally
        {
            session.close();
        }
    }
    
    public static void main(String[] args) {
        
        //deleteVote(0);
        //updateVote(1);
        //listVotes();
        //addVote(0,0,0,0,"0-0-0")
        Date startDate = new Date();
        Query1();
        Date endDate  = new Date();
        long time = endDate.getTime() - startDate.getTime();
        System.out.println(time);
        
    }
}
