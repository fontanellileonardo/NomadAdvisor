import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class DBConnection {
    private static DBConnection instance;
    public static MongoClient mongoClient;

    private DBConnection() {
    	String user = "NomadUser";
    	String database = "task2";
    	String password = "NomadAdvisor";
    	String replica1 = "127.0.0.1:27017";
    	String replica2 = "127.0.0.1:27018";
    	String replica3 = "127.0.0.1:27019";
        mongoClient = MongoClients.create("mongodb://" + user + ":" + password + "@" + replica1
        		+ "," + replica2 + "," + replica3 + "/?authSource=" + database);
    }

    public static DBConnection getInstance() {
        if(instance == null) {
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection();
                }
            }
        }
        return instance;
    }
}
