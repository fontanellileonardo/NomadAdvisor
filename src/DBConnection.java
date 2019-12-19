import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class DBConnection {
    private static DBConnection instance;
    public static MongoClient mongoClient;

    private DBConnection() {
        mongoClient = MongoClients.create("mongodb://127.0.0.1:27017,127.0.0.1:27018,127.0.0.1:27019");
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
