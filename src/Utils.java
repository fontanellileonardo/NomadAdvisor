import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;

public class Utils {
	// City's Characteristics
	public static enum cityNames {
		TEMPERATURE,COST,AIR_QUALITY,SAFETY,QUALITY_LIFE,WALKABILITY,HEALTHCARE,NIGHTLIFE,
		WIFI,FOREIGNERS,ENGLISH
	}
	// HashMap where the key is the cityNames and the value is the key in the DB related to the characteristics
	public static HashMap<cityNames,String> cityAttributes = new HashMap();

	static {
		cityAttributes.put(cityNames.TEMPERATURE,"temperature");
		cityAttributes.put(cityNames.COST,"cost");
		cityAttributes.put(cityNames.AIR_QUALITY,"airQuality");
		cityAttributes.put(cityNames.SAFETY,"safety");
		cityAttributes.put(cityNames.QUALITY_LIFE, "qualityLife");
		cityAttributes.put(cityNames.WALKABILITY, "walkability");
		cityAttributes.put(cityNames.HEALTHCARE, "healthcare");		
		cityAttributes.put(cityNames.NIGHTLIFE, "nightlife");
		cityAttributes.put(cityNames.WIFI, "wifi");
		cityAttributes.put(cityNames.FOREIGNERS, "foreignersFriendly");
		cityAttributes.put(cityNames.ENGLISH, "english");
	}
	
	// DB's keys
	public final static String ID = "_id",
			// city's keys
			CITY = "city",
			COUNTRY = "country",
			PREFERENCES = "preferences";
	
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
