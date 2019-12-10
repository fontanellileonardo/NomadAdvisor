import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBHandle {

    private static MongoClient mongoClient;

    static{
        mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
        System.out.println("Created Connection with the DB");
    }

    public static void finish() {
        if(mongoClient != null)
            mongoClient.close();
            System.out.println("Closed connection with the DB");
    }

}
