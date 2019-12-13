import java.util.List;

public class NomadHandler {

    public boolean addReview(Review newReview){
        return true;
    }

    public List<Review> getReviews(Hotel hotel){
        return null;
    }
	
	public String updatePreferences(Customer customer, List<String> preferences) {
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
