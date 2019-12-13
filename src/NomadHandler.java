import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NomadHandler {

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
	
	public static List<HashMap<String, Integer>> computePieChartsData() {
		List<HashMap<String, Integer>> pieChartsData = new ArrayList();
		pieChartsData.add(MongoDBHandle.aggregateCitiesCharacteristics());
		pieChartsData.add(MongoDBHandle.aggregateCustomersPreferences());
		if((pieChartsData.get(0) == null) || (pieChartsData.get(1) == null))
			return null;
		return pieChartsData;
	}
}
