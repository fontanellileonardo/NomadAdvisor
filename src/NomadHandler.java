import java.util.ArrayList;
import java.util.List;

public class NomadHandler {
	
	public static List<City> getCity(String cityName) {
		List<City> cities = new ArrayList<City>();
		return cities = MongoDBHandle.selectCities(cityName);
	}

    public static boolean addReview(Review newReview){
        return true;
    }

    public static List<Review> getReviews(Hotel hotel){
        return null;
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
}
