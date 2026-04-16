import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * =========================================================
 * MAIN CLASS - UseCase13TrainConsistMgmnt
 * =========================================================
 *
 * Use Case 13: Performance Comparison (Loops vs Streams)
 *
 * Description:
 * This class compares execution time of loop-based filtering
 * versus stream-based filtering using System.nanoTime().
 *
 * At this stage, the application:
 * - Creates bogie test dataset
 * - Measures loop execution time
 * - Measures stream execution time
 * - Calculates elapsed duration
 * - Displays performance results
 *
 * This maps performance benchmarking using high-resolution timing.
 *
 * @author Developer
 * @version 13.0
 */
public class UseCase13TrainConsistMgmnt {

    // Bogie model
    static class Bogie {
        String type;
        int capacity;

        Bogie(String type, int capacity) {
            this.type = type;
            this.capacity = capacity;
        }
    }

    // Loop-based filtering: returns bogies with capacity > threshold
    static List<Bogie> filterByLoop(List<Bogie> bogies, int threshold) {
        List<Bogie> result = new ArrayList<>();
        for (Bogie b : bogies) {
            if (b.capacity > threshold) {
                result.add(b);
            }
        }
        return result;
    }

    // Stream-based filtering: returns bogies with capacity > threshold
    static List<Bogie> filterByStream(List<Bogie> bogies, int threshold) {
        return bogies.stream()
                .filter(b -> b.capacity > threshold)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println(" UC13 - Performance Comparison (Loops vs Streams) ");
        System.out.println("==============================================\n");

        // Create large test dataset
        List<Bogie> bogies = new ArrayList<>();
        String[] types = {"Sleeper", "AC Chair", "First Class", "General", "Luxury"};
        for (int i = 0; i < 100000; i++) {
            bogies.add(new Bogie(types[i % types.length], 20 + (i % 80)));
        }

        int threshold = 60;

        // ---- LOOP BENCHMARK ----
        long loopStart = System.nanoTime();
        List<Bogie> loopResult = filterByLoop(bogies, threshold);
        long loopEnd = System.nanoTime();
        long loopElapsed = loopEnd - loopStart;

        // ---- STREAM BENCHMARK ----
        long streamStart = System.nanoTime();
        List<Bogie> streamResult = filterByStream(bogies, threshold);
        long streamEnd = System.nanoTime();
        long streamElapsed = streamEnd - streamStart;

        System.out.println("Loop Execution Time (ns): " + loopElapsed);
        System.out.println("Stream Execution Time (ns): " + streamElapsed);

        System.out.println("\nUC13 performance benchmarking completed...");
    }
}
