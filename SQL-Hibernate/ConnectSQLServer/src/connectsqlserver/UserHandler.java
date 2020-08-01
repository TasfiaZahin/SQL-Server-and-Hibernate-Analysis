/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectsqlserver;

import static connectsqlserver.ConnectSQLServer.addVote;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author acer
 */
class UserHandler extends DefaultHandler{
    private boolean inxmlsplitroot = false;
   boolean bId = false;
   boolean bPostId = false;
   boolean bVoteTypeId = false;
   boolean bCreationDate = false;
   DBConnect dbc;
   
   UserHandler(DBConnect dbc)
   {
       this.dbc = dbc;
   }

   @Override
   public void startElement(String uri, 
      String localName, String qName, Attributes attrs)throws SAXException {
        if (qName.equalsIgnoreCase("xml_split:root")) {
            inxmlsplitroot = true;
        } else if (qName.equals("row")) {
            if (!inxmlsplitroot) {
                throw new SAXException("row tag not expected here.");
            }
            int length = attrs.getLength();
            int Id = -1;
            int PostId = -1;
            int VoteTypeId = -1;
            int UserId = -1;
            String CreationDate = "";
            for (int i=0; i<length; i++) {

                String name = attrs.getQName(i);
               // System.out.print(name + ": ");
                String value = attrs.getValue(i);
                if(name.equalsIgnoreCase("Id"))
                {
                    Id = Integer.parseInt(value);
                }
                else if(name.equalsIgnoreCase("PostId"))
                {
                    PostId = Integer.parseInt(value);
                }
                else if(name.equalsIgnoreCase("VoteTypeId"))
                {
                    VoteTypeId = Integer.parseInt(value);
                }
                else if(name.equalsIgnoreCase("UserId"))
                {
                    UserId = Integer.parseInt(value);
                }
                else if(name.equalsIgnoreCase("CreationDate"))
                {
                    CreationDate = value;
                    if(VoteTypeId ==5)
                    {
                        addVote(Id,PostId,VoteTypeId,UserId,CreationDate, dbc);
                    }
                    else
                    {
                        addVote(Id,PostId,VoteTypeId,CreationDate, dbc);
                    }
                }
                //System.out.println(value); 
                
            }
        }          
     
   }

   @Override
   public void endElement(String uri, 
      String localName, String qName) throws SAXException {
      if (qName.equalsIgnoreCase("xml_split:root")) {
         //System.out.println("End Element :" + qName);
      }
   }

   @Override
   public void characters(char ch[], 
      int start, int length) throws SAXException {
      if (bId) {
         //System.out.println("Id: " + new String(ch, start, length));
         bId = false;
      } else if (bPostId) {
         //System.out.println("PostId: " + new String(ch, start, length));
         bPostId = false;
      } else if (bVoteTypeId) {
         //System.out.println("VoteTypeId: "+ new String(ch, start, length));
         bVoteTypeId = false;
      } else if (bCreationDate) {
         //System.out.println("CreationDate: " + new String(ch, start, length));
         bCreationDate = false;
      }
   }
}
