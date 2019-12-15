import com.mongodb.Block;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

import java.awt.Desktop.Action;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;



public class MongoDBHandle {

    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> userCollection;
    private static MongoCollection<Document> cityCollection;
    private static MongoCollection<Document> hotelCollection;
    private static MongoCollection<Document> reviewCollection;

    static{
        mongoClient = DBConnection.getInstance().mongoClient;
        database = mongoClient.getDatabase("task2");
        userCollection = database.getCollection("user");
        cityCollection = database.getCollection("city");
        hotelCollection = database.getCollection("hotel");
        reviewCollection = database.getCollection("review");
    }

    public static void finish() {
        if(mongoClient != null) {
            mongoClient.close();
            System.out.println("Closed connection with the DB");
        }
    }
  
    // Login interface

    // It creates an Employee or a Customer object, but it returns a User object. In this way it can be used to read
    // both Customers and Employees.
    public static User readUser(User user, StringBuilder msg) {
    	Customer customer;
    	Employee employee;
    	try {
    		Document document = userCollection.find(Filters.and(Filters.eq("email", user.getEmail()),Filters.eq("password", user.getPassword()))).first();
    		// Check if the user exists
    		if(document == null) {
    			msg.append("Wrong email or password");
    			return null;
    		}
    		System.out.println("Document retrieved in readUser: " + document.toString());
    		// Check if the role field exists
			if(document.getString("role") != null) {
				if(document.getString("role").equals("customer")) {	// customer
					msg.append("Success!");
	    			return customer = new Customer(document.getString("name"), document.getString("surname"), user.getEmail(), user.getPassword(), 
	    					document.getString("username"), (List<String>) document.get("preferences"));
	    		} else // employee
	    			return employee = new Employee(document.getString("name"), document.getString("surname"), user.getEmail(), user.getPassword());
			}
    	}catch(Exception ex) {
        	msg.append("Oops! Something went wrong");
    		System.out.println("Error during readUser: "+ex.getMessage());
    		return null;
    	}
    	// if role is null
    	msg.append("Oops! Something went wrong. Do again the registration");
    	return null;
    }

    /* Create new object. 
	 * Return 0 -> everything is ok
	 * Return 1 -> object already exists or a unique value already exists
	 * Return 2 -> DB error
	 */
    public static int createCustomer(Customer customer) {
    	try {
    	Document doc = new Document("role",customer.getRole())
    			.append("name", customer.getName())
    			.append("surname", customer.getSurname())
    			.append("email", customer.getEmail())
    			.append("password", customer.getPassword())
    			.append("username", customer.getUsername());
    	userCollection.insertOne(doc);
    	}catch(Exception ex) {
    		System.out.println("Error inserting a new customer: "+ex.getMessage());
    		// Duplicate key
    		if(ex.toString().contains("E11000")) {
    			return 1;
    		}
    		// Other general errors
    		return 2;
    	}
        return 0;
    }

    // Customer interface (City)
    // If the fields are null, search all the cities
    public static List<City> selectCities(String name) {
    	return selectCities(name, null, null);
    }
    
    public static List<City> selectCities(String name, String country, List<String> filters) {
    	List<City> cities = new ArrayList<City>();
    	MongoCursor<Document> cursor = cityCollection.find(Filters.eq("_id.city", name)).iterator();
    	try {
    		while(cursor.hasNext()) {
    			 Document dc = cursor.next();
    			 cities.add(buildCity(dc));
    		}
    	}catch(Exception ex) {
    		System.out.println("Error: "+ex);
    		return null;
    	}
        return cities;
    }
    
    // Initialize city with the values taken from the DB
    static City buildCity(Document dc) {
    	HashMap<String,Integer> charact = new HashMap<String,Integer>();
    	// put all the characteristics in the HashMap
    	for(String key: dc.keySet()) {
    		// exclude the ID field
    		if(!key.equals(Utils.ID)) {
    			charact.put(key, dc.getInteger(key));
    		}    			
		}
    	Document id = (Document)dc.get(Utils.ID);
    	String cityName = " ";
    	String countryName = " ";
    	// Check if the id is a Document
    	if(id!=null) {
    		cityName = id.getString(Utils.CITY) != null ?
        			id.getString(Utils.CITY) : " ";
        	countryName = id.getString(Utils.COUNTRY) != null ?
        			id.getString(Utils.COUNTRY) : " ";
    	}
    	return new City(charact,cityName, countryName);
    }

    public static List<Hotel> selectHotels(String city, String country) {
        return null;
    }

    // Customer Interface (Hotel)
    public static List<Review> selectReviews(String hotelName, String city, String country) {
        return null;
    }

    public static int createReview(Review review) {
        return 0;
    }

    // Personal Area
    public static boolean updatePreferences(Customer customer) {
    	UpdateResult result = userCollection.updateOne(Filters.eq("email", customer.getEmail()), new Document("$set",
    			new Document(Utils.cityAttributes.get(Utils.cityNames.PREFERENCES), customer.getPreferences())));
    	if(result.getModifiedCount() == 0) {
    		System.out.println("Update preferences failed: Customer not found");
    		return false;
    	}
        return true;
    }
  
    // Employee Interface
    public static List<Customer> selectCustomers() {
        return null;
    }

    // Delete also hotels and reviews connected
    public static boolean deleteCity(City city) {
        return false;
    }

    public static int createCity(City city) {
        return 0;
    }

    public static boolean deleteHotel(Hotel hotel) {
        return false;
    }

    public static int createHotel(Hotel hotel) {
        return 0;
    }

    public static List<Integer> aggregateCustomersPreferences() {
        return null;
    }

    public static List<Integer> aggregateCitiesAttributes() {
        return null;
    }
    
    
    public static void main(String[] args) {
    	/*
    	// Read city  by name
    	List<City> cities = selectCities("Milan");
    	for(int i = 0; i<cities.size(); i++) {
    		System.out.println("country: "+cities.get(i).getCountryName());
    		System.out.println("city: "+cities.get(i).getCityName());
    		System.out.println("city: "+cities.get(i).getCharacteristics());
    	}
    	*/
    	/*
    	// Insert a new Customer
    	Customer customer = new Customer("Eugenia","Petrangeli","e.petrangeli@gmail.com","Eugenia","Geggi", null);
    	System.out.println("Insert a customer: "+createCustomer(customer));
    	// Delete the Customer
    	userCollection.deleteOne(Filters.eq("username","Geggi"));
		*/
    	/*
    	// read a Customer
    	StringBuilder msg = new StringBuilder();
    	Customer customer = new Customer("Eugenia","Petrangeli","e.petrangeli@gmail.com","Eugenia","Geggi", null);
    	System.out.println("Insert a customer: "+createCustomer(customer));
    	User user = new User("","","e.petrangeli@gmail.com","Eugenia","");
    	Customer readUser = (Customer) readUser(user, msg);
    	Utils.printUser(readUser);
    	System.out.println("msg: "+msg);
    	userCollection.deleteOne(Filters.eq("username","Geggi"));
    	
    	// read an employee
    	// nel DB c'è: Employee employee = new Employee("Carmelo","Aparo","c.aparo@gmail.com","Carmelo");
    	user.setEmail("c.aparo@gmail.com");
    	user.setPassword("Carmelo");
    	Employee readUser2 = (Employee) readUser(user, msg);
    	System.out.println("msg: "+msg);
    	Utils.printUser(readUser2);
    	*/
    }
    
}
