import java.util.ArrayList;
import java.util.HashMap;
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
	
	// Retrieve all the registered users for the Employee Interface
	public static List<Customer> getCustomers(){
		return MongoDBHandle.selectCustomers();
	}
	
	public static String createCity(City addedCity) {
		if(addedCity != null) {
			//Check if all the text fields have been correctly inserted from the Employee
			if(addedCity.getCityName().equals("") == false && addedCity.getCountryName().equals("") == false) {
				int result = MongoDBHandle.createCity(addedCity);
				switch(result) {
				case 0:
					return "Success!";
				case 1:
					return "The city already exists. Data have been updated!";
				case 2:
					return "Oops! Something went wrong. Try again later!";
				}
			}
		}
		System.out.println("The city fields are not correctly inserted");
        return "You didn't complete the city fields";
	}
	
	public static String deleteCity(City selectedCity) {
		if(selectedCity != null) {
			boolean result = MongoDBHandle.deleteCity(selectedCity);
			if(result)
				return "Success!";
		}
		return "Oops! Something went wrong. Try again later!";
	}
	
	public static String deleteHotel(Hotel hotel) {
		if(hotel != null) {
			boolean result = MongoDBHandle.deleteHotel(hotel.getHotelName(),hotel.getCityName(),hotel.getCountryName());
			if(result)
				return "Success!";
		}
		return "Oops! Something went wrong. Try again later!";
	}
	
	// Search the city by a given preferences
	public static List<City> getCity(HashMap<String,Integer> pref) {
		if(pref.isEmpty())
			return MongoDBHandle.selectCities();
		return MongoDBHandle.selectCities(pref);
	}
	
	// Search the city by a given name
	public static List<City> getCity(String cityName) {
		if(cityName.isEmpty())
			return MongoDBHandle.selectCities();
		return MongoDBHandle.selectCities(cityName);
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
	}
	
	public static List<HashMap<String, Integer>> computePieChartsData() {
		List<HashMap<String, Integer>> pieChartsData = new ArrayList();
		pieChartsData.add(MongoDBHandle.aggregateCitiesCharacteristics());
		pieChartsData.add(MongoDBHandle.aggregateCustomersPreferences());
		if((pieChartsData.get(0) == null) || (pieChartsData.get(1) == null))
			return null;
		return pieChartsData;
	}
}
