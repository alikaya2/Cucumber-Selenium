//import com.mongodb.client.*;
//import org.bson.Document;
//import org.bson.types.ObjectId;
//
//public class MongoDbUtil {
//
//    public void ChangePasswordAndLockoutScenario()
//    {
//        String mongoDbUrl = "";
//        //Mongo DB connection
//        MongoClient mongoClient = MongoClients.create(mongoDbUrl);
//        MongoDatabase database = mongoClient.getDatabase("users");
//        MongoCollection<Document> collection = database.getCollection("UserInfos");
//
//        FindIterable<Document> documents = collection.find();
//
//        // Her belge için username ve password değerlerini oku
//    for(Document document :documents)
//        {
//            ObjectId objectId = document.getObjectId("id");
//            String username = document.getString("username");
//            String password = document.getString("password");
//        }
//    }
//}
