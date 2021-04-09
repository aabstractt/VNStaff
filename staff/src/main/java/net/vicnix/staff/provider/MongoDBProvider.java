package net.vicnix.staff.provider;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import net.vicnix.staff.ConsoleUtils;
import org.bson.Document;
import net.vicnix.staff.session.SessionStorage;

import java.util.UUID;

public class MongoDBProvider {

    private final static MongoDBProvider instance = new MongoDBProvider();

    private MongoCollection<Document> collection;

    public static MongoDBProvider getInstance() {
        return instance;
    }

    public void init(String mongouri) {
        MongoClient mongoClient;

        if (mongouri == null) {
            mongoClient = new MongoClient();
        } else {
            mongoClient = new MongoClient(new MongoClientURI(mongouri));
        }

        MongoDatabase database = mongoClient.getDatabase("VNStaff");

        this.collection = database.getCollection("users");
    }

    public SessionStorage loadSessionStorage(String name, UUID uuid) {
        if (this.collection == null) {
            ConsoleUtils.getInstance().sendMessage("MongoDB not initialized...");

            return null;
        }

        Document document = this.collection.find(Filters.eq("uuid", uuid.toString())).first();

        if (document == null || document.isEmpty()) {
            return null;
        }

        if (name == null) {
            name = document.getString("name");
        }

        return new SessionStorage(
                name,
                uuid,
                document.getBoolean("vanished", false),
                document.getBoolean("canSeeStaff", true)
        );
    }

    public void saveSessionStorage(SessionStorage sessionStorage) {
        if (this.collection == null) {
            ConsoleUtils.getInstance().sendMessage("MongoDB not initialized...");

            return;
        }

        Document document = this.collection.find(Filters.eq("uuid", sessionStorage.getUniqueId().toString())).first();

        Document newDocument = new Document("uuid", sessionStorage.getUniqueId().toString())
                .append("name", sessionStorage.getName())
                .append("vanished", sessionStorage.isVanished());

        if (document == null || document.isEmpty()) {
            this.collection.insertOne(newDocument);
        } else {
            this.collection.findOneAndReplace(Filters.eq("uuid", sessionStorage.getUniqueId().toString()), newDocument);
        }
    }
}