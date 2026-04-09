import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ========================================================
 * MAIN CLASS - UseCase8TrainConsistMgmnt
 * ========================================================
 *
 * Use Case 8: Filter Passenger Bogies Using Streams
 *
 * Description:
 * This class filters passenger bogies based on seating
 * capacity using Java Stream API.
 *
 * At this stage, the application:
 * - Creates a list of bogies
 * - Converts list into stream
 * - Applies filter condition
 * - Collects filtered result
 * - Displays qualifying bogies
 *
 * @author Developer
 * @version 8.0
 */
public class UseCase8TrainConsistMgmnt {

    // Reusing Bogie model from UC7
    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }
    }

    public static void main(String[] args) {

        System.out.println("===============================================");
        System.out.println(" UC8 - Filter Passenger Bogies Using Streams ");
        System.out.println("===============================================\n");

        // Create list of passenger bogies (same style as UC7)
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("General", 90));

        // Display all bogies
        System.out.println("All Bogies:");
        for (Bogie b : bogies) {
            System.out.println(b.name + " -> " + b.capacity);
        }

        // ---- FILTER USING STREAM ----
        // stream() converts list to stream
        // filter() applies the capacity condition
        // collect() gathers results back into a list
        int threshold = 60;
        List<Bogie> filteredBogies = bogies.stream()
                .filter(b -> b.capacity > threshold)
                .collect(Collectors.toList());

        System.out.println("\nFiltered Bogies (Capacity > " + threshold + "):");
        for (Bogie b : filteredBogies) {
            System.out.println(b.name + " -> " + b.capacity);
        }

        // Original list is unchanged
        System.out.println("\nOriginal list size (unchanged): " + bogies.size());

        System.out.println("\nUC8 filtering completed...");
    }
}