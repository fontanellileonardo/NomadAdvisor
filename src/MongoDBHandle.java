import com.mongodb.Block;
import com.mongodb.MongoWriteException;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

import java.awt.Desktop.Action;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Filter;

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
    public static User readUser(String email) {
        return null;
    }

    public static int createCustomer(Customer customer) {
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
    	}
        return cities;
    }
    
    // initialize city with the values taken from the DB
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
        List<Hotel> hotels = new ArrayList<>();
        MongoCursor<Document> cursor = hotelCollection.find(Filters.and(Filters.eq("_id.city", city), Filters.eq("_id.country", country))).iterator();
        try{
            while(cursor.hasNext()){
                Document d = cursor.next();
                Document d_hotel = (Document) d.get("_id");
                int avg = d.getInteger("avgScore")==null?0:d.getInteger("avgScore");
                Hotel h = new Hotel(d_hotel.getString("name"), d_hotel.getString("city"), d_hotel.getString("country"), avg, d.getString("address"), d.getString("website"));
                hotels.add(h);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return hotels;
    }

    // Customer Interface (Hotel)
    public static List<Review> selectReviews(String hotelName, String city, String country) {
        List<Review> reviews = new ArrayList<>();
        MongoCursor<Document> cursor = reviewCollection.find(Filters.and(Filters.eq("hotelId.name", hotelName), Filters.eq("hotelId.city", city), Filters.eq("hotelId.country", country))).iterator();
        try{
            while(cursor.hasNext()){
                Document d = cursor.next();
                Document d_hotel = (Document) d.get("hotelId");

                DateFormat osLocalizedDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(osLocalizedDateFormat.format(d.getDate("date")));

                String username = d.getString("username")==null?"Anonymous":d.getString("username");
                Review r = new Review(username, d.getString("nationality"), d.getInteger("rating"), d.getString("text"), date, d_hotel.getString("name"), d_hotel.getString("city"), d_hotel.getString("country"));
                reviews.add(r);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return reviews;
    }

    public static boolean createReview(Review review) {
        Document rv = new Document("username", review.getUsername())
                        .append("nationality", review.getNationality())
                        .append("rating", review.getRating())
                        .append("text", review.getText())
                        .append("date", review.getDate())
                        .append("hotelId", new Document("name", review.getHotelName()).append("city", review.getCityName()).append("country", review.getCountryName()));
        try {
            reviewCollection.insertOne(rv);
        } catch (MongoWriteException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
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

}
