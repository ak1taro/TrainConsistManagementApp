import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * ========================================================
 * MAIN CLASS - UseCase12TrainConsistMgmnt
 * ========================================================
 *
 * Use Case 12: Safety Compliance Check for Goods Bogies
 *
 * Description:
 * This class enforces domain safety rules on goods bogies.
 *
 * At this stage, the application:
 * - Creates goods bogie list
 * - Converts List into stream
 * - Applies safety validation rule
 * - Checks compliance using allMatch()
 * - Displays safety status
 *
 * Safety Rule:
 *   Cylindrical bogies must carry ONLY Petroleum cargo.
 *   Non-cylindrical bogies (Open, Box) may carry any cargo.
 *
 * @author Developer
 * @version 12.0
 */
public class UseCase12TrainConsistMgmnt {

    // Goods Bogie model
    static class GoodsBogie {
        String type;   // e.g. "Cylindrical", "Open", "Box"
        String cargo;  // e.g. "Petroleum", "Coal", "Grain"

        GoodsBogie(String type, String cargo) {
            this.type  = type;
            this.cargo = cargo;
        }

        @Override
        public String toString() {
            return type + " -> " + cargo;
        }
    }

    /**
     * Safety validation rule encapsulated as a Predicate (functional interface).
     * Rule: If type is Cylindrical, cargo MUST be Petroleum.
     *       All other bogie types are unconstrained.
     */
    public static boolean isSafetyCompliant(List<GoodsBogie> bogies) {
        Predicate<GoodsBogie> safetyRule = bogie -> {
            if (bogie.type.equalsIgnoreCase("Cylindrical")) {
                return bogie.cargo.equalsIgnoreCase("Petroleum");
            }
            return true; // Non-cylindrical bogies are always safe
        };

        // allMatch() returns true only if EVERY bogie passes the rule
        // Short-circuit: stops as soon as one failure is found
        return bogies.stream().allMatch(safetyRule);
    }

    public static void main(String[] args) {

        System.out.println("===============================================");
        System.out.println(" UC12 - Safety Compliance Check for Goods Bogies ");
        System.out.println("===============================================\n");

        // Create goods bogie list
        List<GoodsBogie> goodsBogies = new ArrayList<>();
        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum")); // Valid
        goodsBogies.add(new GoodsBogie("Open",        "Coal"));      // Valid (non-cylindrical)
        goodsBogies.add(new GoodsBogie("Box",         "Grain"));     // Valid (non-cylindrical)
        goodsBogies.add(new GoodsBogie("Cylindrical", "Coal"));      // INVALID -> causes unsafe

        // Display goods bogies
        System.out.println("Goods Bogies in Train:");
        for (GoodsBogie b : goodsBogies) {
            System.out.println(b);
        }

        // ---- SAFETY VALIDATION ----
        boolean isSafe = isSafetyCompliant(goodsBogies);

        System.out.println("\nSafety Compliance Status: " + isSafe);
        if (isSafe) {
            System.out.println("Train formation is SAFE.");
        } else {
            System.out.println("Train formation is NOT SAFE.");
        }

        // ---- ADDITIONAL TEST CASES ----
        System.out.println("\n--- Test Cases ---");

        // Test 1: All bogies valid
        List<GoodsBogie> allValid = new ArrayList<>();
        allValid.add(new GoodsBogie("Cylindrical", "Petroleum"));
        allValid.add(new GoodsBogie("Open",        "Coal"));
        System.out.println("All valid: " + isSafetyCompliant(allValid));         // true

        // Test 2: Cylindrical with invalid cargo
        List<GoodsBogie> cylindricalInvalid = new ArrayList<>();
        cylindricalInvalid.add(new GoodsBogie("Cylindrical", "Coal"));
        System.out.println("Cylindrical with Coal: " + isSafetyCompliant(cylindricalInvalid)); // false

        // Test 3: Non-cylindrical only
        List<GoodsBogie> nonCylindrical = new ArrayList<>();
        nonCylindrical.add(new GoodsBogie("Open", "Coal"));
        nonCylindrical.add(new GoodsBogie("Box",  "Grain"));
        System.out.println("Non-cylindrical bogies: " + isSafetyCompliant(nonCylindrical));    // true

        // Test 4: Empty list
        List<GoodsBogie> empty = new ArrayList<>();
        System.out.println("Empty list: " + isSafetyCompliant(empty));                          // true

        System.out.println("\nUC12 safety validation completed...");
    }
}