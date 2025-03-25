import java.util.*;

public class SimpleRecommendationSystem {
    public static void main(String[] args) {
        // Sample data: user preferences (userID -> map of itemID -> rating)
        Map<Integer, Map<Integer, Double>> userPreferences = new HashMap<>();
        
        // User 1 preferences
        userPreferences.put(1, new HashMap<>());
        userPreferences.get(1).put(101, 5.0);
        userPreferences.get(1).put(102, 3.0);

        // User 2 preferences
        userPreferences.put(2, new HashMap<>());
        userPreferences.get(2).put(101, 4.0);
        userPreferences.get(2).put(103, 2.0);

        // User 3 preferences
        userPreferences.put(3, new HashMap<>());
        userPreferences.get(3).put(102, 4.0);
        userPreferences.get(3).put(103, 5.0);

        // Recommend items for User 1
        int targetUser = 1;
        System.out.println("Recommendations for User " + targetUser + ":");
        recommendItems(targetUser, userPreferences);
    }

    public static void recommendItems(int userID, Map<Integer, Map<Integer, Double>> userPreferences) {
        // Find items liked by other users but not rated by the target user
        Map<Integer, Double> targetUserPreferences = userPreferences.get(userID);
        Map<Integer, Double> recommendedItems = new HashMap<>();

        for (Map.Entry<Integer, Map<Integer, Double>> entry : userPreferences.entrySet()) {
            int otherUserID = entry.getKey();
            if (otherUserID == userID) continue;

            Map<Integer, Double> otherUserPreferences = entry.getValue();
            for (Map.Entry<Integer, Double> itemEntry : otherUserPreferences.entrySet()) {
                int itemID = itemEntry.getKey();
                double rating = itemEntry.getValue();

                // Recommend if the target user has not rated this item
                if (!targetUserPreferences.containsKey(itemID)) {
                    recommendedItems.put(itemID, rating);
                }
            }
        }

        // Display recommended items
        for (Map.Entry<Integer, Double> entry : recommendedItems.entrySet()) {
            System.out.println("Item ID: " + entry.getKey() + ", Predicted Rating: " + entry.getValue());
        }
    }
}
