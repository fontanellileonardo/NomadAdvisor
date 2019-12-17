import java.util.ArrayList;

import java.util.List;

public class NomadHandler {
  
	// Search a specific user in the login phase
	public static User readUser(User user, StringBuilder msg) {
		if(user!=null) {
			if(user.getEmail().equals("") == false && user.getPassword() != null) {
				user = MongoDBHandle.readUser(user, msg);
				return user;
			}
		}
		msg.append("You didn't complete all the login fields");
		return null;
	}
	
	// Insert a new Customer in the reg phase
	public static String createCustomer(Customer customer) {
		if(customer!=null) {
			// Check if all the fields are correctly inserted from the Customer
			if(customer.getUsername().equals("") == false && customer.getName().equals("") == false && 
					customer.getSurname().equals("") == false && customer.getEmail().equals("") == false &&
					customer.getPassword().equals("") == false) {
				int result = MongoDBHandle.createCustomer(customer);
				switch(result) {
				case 0:
					return "Success!";
				case 1:
					return "email or username already exists";
				default:
					return "Ooops, something went wrong. Please, try again later";
				}
			}
		}
		System.out.println("The registration fields are not correctly inserted");
        return "You didn't complete the registration fields";
	}
	
	// Search the city by a given name
	public static List<City> getCity(String cityName) {
		List<City> cities = new ArrayList<City>();
		return cities = MongoDBHandle.selectCities(cityName);
	}

    public static boolean addReview(Review newReview){
        return MongoDBHandle.createReview(newReview);
    }

    public static List<Review> getReviews(Hotel hotel){
        return MongoDBHandle.selectReviews(hotel.getHotelName(), hotel.getCityName(), hotel.getCountryName());
    }

    public static List<Hotel> getHotels(City city){
    	return MongoDBHandle.selectHotels(city.getCityName(), city.getCountryName());
	}
	/*
	public static String updatePreferences(Customer customer, List<String> preferences) {
		customer.setPreferences(preferences);
		String result;
		if(MongoDBHandle.updatePreferences(customer)) {
			result = "Success!!!";
		}
		else {
			result = "Update operation failed";
		}
		return result;
	}*/
}
