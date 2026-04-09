import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ========================================================
 * MAIN CLASS - UseCase11TrainConsistMgmnt
 * ========================================================
 *
 * Use Case 11: Validate Train ID and Cargo Code (Regex)
 *
 * Description:
 * This class validates input formats using Regular Expressions.
 *
 * At this stage, the application:
 * - Accepts Train ID input
 * - Accepts Cargo Code input
 * - Applies regex validation
 * - Displays validation result
 *
 * Train ID format  : TRN-XXXX  (e.g. TRN-1234)
 * Cargo Code format: PET-XX    (e.g. PET-AB)
 *
 * @author Developer
 * @version 11.0
 */
public class UseCase11TrainConsistMgmnt {

    // ---- DEFINE REGEX RULES ----
    // Train ID: "TRN-" followed by exactly 4 digits
    private static final Pattern TRAIN_ID_PATTERN = Pattern.compile("TRN-\\d{4}");

    // Cargo Code: "PET-" followed by exactly 2 uppercase letters
    private static final Pattern CARGO_CODE_PATTERN = Pattern.compile("PET-[A-Z]{2}");

    /**
     * Validates a Train ID string against the regex pattern.
     */
    public static boolean validateTrainId(String trainId) {
        Matcher matcher = TRAIN_ID_PATTERN.matcher(trainId);
        return matcher.matches();
    }

    /**
     * Validates a Cargo Code string against the regex pattern.
     */
    public static boolean validateCargoCode(String cargoCode) {
        Matcher matcher = CARGO_CODE_PATTERN.matcher(cargoCode);
        return matcher.matches();
    }

    public static void main(String[] args) {

        System.out.println("===============================================");
        System.out.println(" UC11 - Validate Train ID and Cargo Code ");
        System.out.println("===============================================\n");

        Scanner scanner = new Scanner(System.in);

        // Accept input
        System.out.print("Enter Train ID (Format: TRN-1234): ");
        String trainId = scanner.nextLine();

        System.out.print("Enter Cargo Code (Format: PET-AB): ");
        String cargoCode = scanner.nextLine();

        // ---- VALIDATE ----
        boolean trainIdValid   = validateTrainId(trainId);
        boolean cargoCodeValid = validateCargoCode(cargoCode);

        System.out.println("\nValidation Results:");
        System.out.println("Train ID Valid: "   + trainIdValid);
        System.out.println("Cargo Code Valid: " + cargoCodeValid);

        // Extra feedback
        if (!trainIdValid) {
            System.out.println("  [ERROR] Train ID must follow the format: TRN-XXXX (4 digits)");
        }
        if (!cargoCodeValid) {
            System.out.println("  [ERROR] Cargo Code must follow the format: PET-XX (2 uppercase letters)");
        }

        // Demo: test a few fixed cases
        System.out.println("\n--- Batch Test Cases ---");
        String[] testTrainIds   = {"TRN-1234", "TRN-6524", "TRAIN12", "TRN12A", "1234-TRN", "TRN-123", "TRN-12345", ""};
        String[] testCargoCodes = {"PET-AB", "PET-FH", "PET-ab", "PET123", "AB-PET", ""};

        System.out.println("Train ID Tests:");
        for (String t : testTrainIds) {
            System.out.println("  \"" + t + "\" -> " + validateTrainId(t));
        }

        System.out.println("Cargo Code Tests:");
        for (String c : testCargoCodes) {
            System.out.println("  \"" + c + "\" -> " + validateCargoCode(c));
        }

        scanner.close();
        System.out.println("\nUC11 validation completed...");
    }
}