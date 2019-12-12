import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

import java.util.Arrays;
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
    public static User readUser(String email) {
        return null;
    }

    public static int createCustomer(Customer customer) {
        return 0;
    }

    // Customer interface (City)
    // If the fields are null, search all the cities
    public static List<City> selectCities(String name, String country, List<String> filters) {
        return null;
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
    			new Document(Utils.PREFERENCES, customer.getPreferences())));
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
