package com.mkyong.service;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.sforce.soap.enterprise.Connector;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.sobject.Contact;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

public class Main {

static final String USERNAME = "ajay.p.dhole-ext@jci.com.jmcdev";
static final String PASSWORD = "Ajay@1234";
static EnterpriseConnection connection;

  public String getList() {
//public static void main(String args[]){

    ConnectorConfig config = new ConnectorConfig();
    config.setUsername(USERNAME);
    config.setPassword(PASSWORD);
    System.out.println("username = "+USERNAME);
    System.out.println("Password = "+PASSWORD);
    //config.setTraceMessage(true);
    List<Contact> contactList = new ArrayList<Contact>();
    Gson gson = new Gson();
    String jsonString = "";
    
    try {
      connection = Connector.newConnection(config);
      // display some current settings
      System.out.println("Auth EndPoint: "+config.getAuthEndpoint());
      System.out.println("Service EndPoint: "+config.getServiceEndpoint());
      System.out.println("Username: "+config.getUsername());
      System.out.println("SessionId: "+config.getSessionId());
      // run the different examples
      contactList = queryContacts();
      System.out.println("contactList: "+contactList);
      jsonString = gson.toJson(contactList);
      System.out.println("jsonString: "+jsonString);
      

    } catch (ConnectionException e1) {
        e1.printStackTrace();
    } 
    return jsonString;
  }
  // queries and displays the 5 newest contacts
  private static List<Contact> queryContacts() {
	  Contact c = new Contact();
	  List<Contact> contactList = new ArrayList<Contact>();
    System.out.println("Querying for the 5 newest Contacts...");
    try {
      // query for the 5 newest contacts     
   QueryResult queryResults = connection.query("SELECT Id, FirstName, LastName, Account.Name " +
            "FROM Contact WHERE AccountId != NULL ORDER BY CreatedDate DESC LIMIT 5");
      if (queryResults.getSize() > 0) {
        for (int i=0;i<queryResults.getRecords().length;i++) {
          // cast the SObject to a strongly-typed Contact
          c = (Contact)queryResults.getRecords()[i];
          System.out.println("Id: " + c.getId() + " - Name: "+c.getFirstName()+" "+
              c.getLastName()+" - Account: "+c.getAccount().getName());
          contactList.add(c);

        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    } 
    return contactList;
  }


}


