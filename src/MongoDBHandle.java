import com.mongodb.*;

import java.util.List;

public class MongoDBHandle {

    // Login interface

    // It creates an Employee or a Customer object, but it returns a User object. In this way it can be used to read
    // both Customers and Employees.
    User readUser(String email) {
        return null;
    }

    int createCustomer(Customer customer) {
        return 0;
    }

    // Customer interface (City)
    // If the fields are null, search all the cities
    List<City> selectCities(String name, String country, List<String> filters) {
        return null;
    }

    List<Hotel> selectHotels(String city, String country) {
        return null;
    }

    // Customer Interface (Hotel)
    List<Review> selectReviews(String hotelName, String city, String country) {
        return null;
    }

    int createReview(Review review) {
        return 0;
    }

    // Personal Area
    boolean updatePreferences(String email, List<String> preferences) {
        return false;
    }

    // Employee Interface
    List<Customer> selectCustomers() {
        return null;
    }

    // Delete also hotels and reviews connected
    boolean deleteCity(City city) {
        return false;
    }

    int createCity(City city) {
        return 0;
    }

    boolean deleteHotel(Hotel hotel) {
        return false;
    }

    int createHotel(Hotel hotel) {
        return 0;
    }

    List<Integer> aggregateCustomersPreferences() {
        return null;
    }

    List<Integer> aggregateCitiesAttributes() {
        return null;
    }
}
