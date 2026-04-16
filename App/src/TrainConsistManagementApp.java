/**
 * =========================================================
 * MAIN CLASS - UseCase20TrainConsistMgmnt
 * =========================================================
 *
 * Use Case 20: Exception Handling During Search Operations
 *
 * Description:
 * This class prevents searching when no bogies exist
 * by applying fail-fast validation using exceptions.
 *
 * At this stage, the application:
 * - Creates bogie collection
 * - Validates system state
 * - Throws exception if empty
 * - Stops invalid search operation
 * - Displays meaningful message
 *
 * This maps defensive programming using runtime exceptions.
 *
 * @author Developer
 * @version 20.0
 */
public class UseCase20TrainConsistMgmnt {

    /**
     * Searches for a bogie ID in the given array.
     * Throws IllegalStateException if the array is empty (fail-fast).
     *
     * @param bogieIds array of bogie IDs
     * @param searchId the ID to search for
     * @return true if found, false otherwise
     */
    static boolean searchBogie(String[] bogieIds, String searchId) {
        // ---- FAIL-FAST VALIDATION ----
        // Check if train has bogies before performing search
        if (bogieIds.length == 0) {
            throw new IllegalStateException("No bogies available in train. Cannot perform search.");
        }

        // ---- SEARCH LOGIC (executes only if data exists) ----
        boolean found = false;
        for (String id : bogieIds) {
            if (id.equals(searchId)) {
                found = true;
                break;
            }
        }
        return found;
    }

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println(" UC20 - Exception Handling During Search ");
        System.out.println("==============================================\n");

        // ---- Scenario 1: Empty train (should throw exception) ----
        String[] emptyBogies = {};
        String searchId = "BG101";

        try {
            boolean result = searchBogie(emptyBogies, searchId);
            if (result) {
                System.out.println("Bogie " + searchId + " found.");
            } else {
                System.out.println("Bogie " + searchId + " not found.");
            }
        } catch (IllegalStateException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        System.out.println("\nUC20 execution completed...");
    }
}
