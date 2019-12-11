import java.math.BigInteger;
import java.security.MessageDigest;

public class Utils {
	
	public final static String TEMPERATURE = "temperature",
			COST = "cost",
			AIR_QUALITY = "airQuality",
			SAFETY = "safety",
			QUALITY_LIFE = "qualityLife",
			WALKABILITY = "walkability",
			HEALTHCARE = "healthcare",
			NIGHTLIFE = "nightlife",
			WIFI = "wifi",
			FOREIGNERS = "foreignersFriendly",
			ENGLISH = "english";
	
	// encrypt the password
    public static String cryptPwd(String pwd) {
    	String ret = null;
    	// if the field is empty -> error
    	if(pwd.equals("") == true)
    		return ret;
    	try {
    		MessageDigest digest = MessageDigest.getInstance("SHA-1");
    		digest.reset();
    		digest.update(pwd.getBytes("utf-8"));
    		ret = String.format("%040x",new BigInteger(1,digest.digest()));
    	} catch(Exception e) {
    		System.out.println("Exception in method cryptoPwd"+e.getMessage());
    	}
    	return ret;
    }
}
