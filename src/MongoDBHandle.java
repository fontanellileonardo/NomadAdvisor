import com.mongodb.Block;
import com.mongodb.MongoWriteException;
import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
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
import java.util.Map;
import java.util.logging.Filter;

import org.bson.Document;
import org.bson.conversions.Bson;

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
    // Retrieve all the cities
    public static List<City> selectCities() {
    	List<City> cities = new ArrayList<City>();
    	MongoCursor<Document> cursor = cityCollection.find().limit(30).iterator();
    	try {
    		while(cursor.hasNext()) {
    			 Document dc = cursor.next();
    			 City city = buildCity(dc);
    			 if(city != null)
    				 cities.add(city);
    		}
    	}catch(Exception ex) {
    		System.out.println("Error: "+ex);
    		return null;
    	}
        return cities;
    }
    
    // Search the first 30 cities that satisfies the preferences inserted
    public static List<City> selectCities(HashMap<String,Integer> pref) {
    	List<City> cities = new ArrayList<City>();
    	List<Bson> filters = new ArrayList<Bson>();
    	// set the filter for the research
    	if(pref.get("temp_lower") != null) {	// if the filter's temperature is set
    		filters.add(Filters.and(
					Filters.gte(Utils.cityAttributes.get(Utils.cityNames.TEMPERATURE), pref.get("temp_lower")),
					Filters.lte(Utils.cityAttributes.get(Utils.cityNames.TEMPERATURE), pref.get("temp_greater"))
					));
    		pref.remove("temp_lower");
    		pref.remove("temp_greater");
    	}
	    	
    	for(Map.Entry<String, Integer> entry: pref.entrySet()) {
    		if(entry.getKey().equals(Utils.cityAttributes.get(Utils.cityNames.COST))) { // filter's cost
    			filters.add(Filters.lte(entry.getKey(), entry.getValue()));
    		} else
    			filters.add(Filters.gte(entry.getKey(),entry.getValue()));	// all the other filters
    	}
    	
    	// Show the first 30 cities
    	MongoCursor<Document> cursor = cityCollection.find(Filters.and(filters)).limit(30).iterator();
    	try {
    		while(cursor.hasNext()) {
    			 Document dc = cursor.next();
    			 City city= buildCity(dc);
    			 if( city != null)
    				 cities.add(city);
    		}
    	}catch(Exception ex) {
    		System.out.println("Error: "+ex);
    		return null;
    	}
    	return cities;
    }
    
    public static List<City> selectCities(String name) {
    	List<City> cities = new ArrayList<City>();
    	MongoCursor<Document> cursor = cityCollection.find(Filters.eq("_id.city", name)).limit(30).iterator();
    	try {
    		while(cursor.hasNext()) {
    			 Document dc = cursor.next();
    			 City city = buildCity(dc);
    			 if(city != null)
    				 cities.add(city);
    		}
    	}catch(Exception ex) {
    		System.out.println("Error: "+ex);
    		return null;
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
    	// Check if the id is not null
    	if(id != null) {
    		cityName = id.getString(Utils.CITY);
        	countryName = id.getString(Utils.COUNTRY);
        	if(cityName == null || countryName == null)
        		return null;
    	}
    	return new City(charact, cityName, countryName);
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
    // Update customer preferences
    public static boolean updatePreferences(Customer customer) {
    	UpdateResult result = userCollection.updateOne(Filters.eq("email", customer.getEmail()), new Document("$set",
    			new Document(Utils.PREFERENCES, customer.getPreferences())));
    	if(result.getModifiedCount() == 0) {
    		System.out.println("Customer preferences update operation failed: There's nothing to change");
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

    /*
     * Create a new hotel
     * Return:
     * 0 if everything goes right
     * 1 if the hotel already exists
     * 2 if an error occurs
     * */
    public static int createHotel(Hotel hotel) {
    	try {
	    	Document hotelDoc = new Document(
	    			"_id", new Document(
	    					"name", hotel.getHotelName())
	    					.append("city", hotel.getCityName())
	    					.append("country", hotel.getCountryName()))
	    			.append("address", hotel.getAddress());
	    	if(hotel.getWebsite() != null)
	    		hotelDoc.append("websites", hotel.getWebsite());
	    	hotelCollection.insertOne(hotelDoc);
    	} catch(Exception ex) {
    		if(ex.toString().contains("E11000")) { // Hotel already exists, so it is updated
    			return updateHotel(hotel);
    		}
    		return 2;
    	}
        return 0;
    }
    
    // Updates the fields address and websites of an hotel
    private static int updateHotel(Hotel hotel) {
    	Document id = new Document(
				"name", hotel.getHotelName())
				.append("city", hotel.getCityName())
				.append("country", hotel.getCountryName());
    	Document updatedFields = new Document("address", hotel.getAddress()); // Fields to update
    	if(hotel.getWebsite() != null)
    		updatedFields.append("websites", hotel.getWebsite());
    	UpdateResult result = hotelCollection.updateOne(Filters.eq("_id", id), new Document("$set", updatedFields));
    	if(result.getModifiedCount() == 0) {
    		System.out.println("Hotel update operation failed: There's nothing to change");
    		return 2;
    	}
        return 0;
    }

    // For each city characteristics, computes the number of customers that have that preference
    public static HashMap<String, Integer> aggregateCustomersPreferences() {
    	HashMap<String, Integer> result = new HashMap<String, Integer>();
    	MongoCursor<Document> cursor = null;
    	try {
    		for(Map.Entry<Utils.cityNames, String> attribute : Utils.cityAttributes.entrySet()) {
    			// Count the customers that has the attribute
    			cursor = userCollection.aggregate(
    					Arrays.asList(
    							Aggregates.match(Filters.eq(Utils.PREFERENCES, Utils.cityAttributes.get(attribute.getKey()))),
    							Aggregates.count()
    							)).iterator();
    			if(cursor.hasNext()) {
    				result.put(Utils.cityAttributes.get(attribute.getKey()), (Integer) cursor.next().get("count"));
    			}
    			else {
    				result.put(Utils.cityAttributes.get(attribute.getKey()), 0);
    			}
    		}
    	}
    	catch(Exception ex) {
    		System.out.println("Customers' preferences aggregation exception: " + ex.getMessage());
    		return null;
    	}
    	finally {
    		if(cursor != null)
    			cursor.close();
    	}
        return result;
    }

    // For each city characteristics, computes the number of cities that have that characteristic
    public static HashMap<String, Integer> aggregateCitiesCharacteristics() {
    	HashMap<String, Integer> result = new HashMap<String, Integer>();
    	MongoCursor<Document> cursor = null;
    	try {
	    	for(Map.Entry<Utils.cityNames, String> attribute : Utils.cityAttributes.entrySet()) { // For each attribute
	    		if(attribute.getKey() == Utils.cityNames.COST) { // Count costs less or equal 2000
	    			cursor = cityCollection.aggregate(
	    	    			Arrays.asList(
	    	    					Aggregates.match(Filters.lte(Utils.cityAttributes.get(Utils.cityNames.COST), 2000)),
	    	    					Aggregates.count()
	    	    					)).iterator();
	    			if(cursor.hasNext()) {
	    				result.put(Utils.cityAttributes.get(Utils.cityNames.COST), (Integer) cursor.next().get("count"));
	    			}
	    			else {
	    				result.put(Utils.cityAttributes.get(Utils.cityNames.COST), 0);
	    			}
	    		}
	    		else if(attribute.getKey() == Utils.cityNames.TEMPERATURE) { // Count temperatures between 15 and 25
	    			cursor = cityCollection.aggregate(
	    	    			Arrays.asList(
	    	    					Aggregates.match(Filters.and(
	    	    							Filters.gte(Utils.cityAttributes.get(Utils.cityNames.TEMPERATURE), 15),
	    	    							Filters.lte(Utils.cityAttributes.get(Utils.cityNames.TEMPERATURE), 25)
	    	    							)),
	    	    					Aggregates.count()
	    	    					)).iterator();
	    			if(cursor.hasNext()) {
	    				result.put(Utils.cityAttributes.get(Utils.cityNames.TEMPERATURE), (Integer) cursor.next().get("count"));
	    			}
	    			else {
	    				result.put(Utils.cityAttributes.get(Utils.cityNames.TEMPERATURE), 0);
	    			}
	    		}
	    		else { // Count attributes greater or equal to 3
	    			cursor = cityCollection.aggregate(
	    					Arrays.asList(
	    	    					Aggregates.match(Filters.gte(Utils.cityAttributes.get(attribute.getKey()), 3)),
	    	    					Aggregates.count()
	    	    					)).iterator();
	    			if(cursor.hasNext()) {
	    				result.put(Utils.cityAttributes.get(attribute.getKey()), (Integer) cursor.next().get("count"));
	    			}
	    			else {
	    				result.put(Utils.cityAttributes.get(attribute.getKey()), 0);
	    			}
	    		}
	    	}
    	}
    	catch(Exception ex) {
    		System.out.println("Cities' characteristics aggregation exception: " + ex.getMessage());
    		return null;
    	}
    	finally {
    		if(cursor != null)
    			cursor.close();
    	}
        return result;
    }
}
