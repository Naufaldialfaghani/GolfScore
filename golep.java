import java.util.Scanner;

public class golep {

    public static void main(String[] args) {
        Scanner egel = new Scanner(System.in);

        // Data lapangan golf
        String[] courses = { "Abdulrachman Saleh Golf Course", "The Araya Golf Course" };
        int[][] pars = {
                { 4, 4, 3, 5, 4, 4, 3, 4, 5, 5, 3, 4, 4, 4, 4, 3, 4, 5 }, // Abdulrachman Saleh
                { 4, 4, 4, 3, 5, 3, 4, 5, 4, 5, 5, 4, 4, 3, 4, 3, 4, 4 } // The Araya
        };
        int totalPar = 72;

        // Pilih lapangan golf
        System.out.println("Choose your golf course:");
        for (int i = 0; i < courses.length; i++) {
            System.out.println((i + 1) + ". " + courses[i]);
        }
        System.out.print("Enter your choice (1 or 2): ");
        int choice = egel.nextInt();

        if (choice < 1 || choice > 2) {
            System.out.println("Invalid choice. Program will exit.");
            return;
        }

        int[] selectedPars = pars[choice - 1];
        System.out.println("You selected: " + courses[choice - 1]);

        // Input skor untuk setiap hole
        int[] scores = new int[selectedPars.length];
        for (int i = 0; i < selectedPars.length; i++) {
            System.out.print("Enter your score for Hole #" + (i + 1) + " (Par " + selectedPars[i] + "): ");
            scores[i] = egel.nextInt();
        }

        // Hitung HDC, NET, dan ADJ
        int totalScore = 0;
        int totalAdjusted = 0;

        for (int i = 0; i < scores.length; i++) {
            totalScore += scores[i];

            // Adjusted score: jika skor lebih dari double par, sesuaikan
            if (scores[i] > 2 * selectedPars[i]) {
                totalAdjusted += 2 * selectedPars[i];
            } else {
                totalAdjusted += scores[i];
            }
        }

        int hdc = totalScore - totalPar;
        int net = totalAdjusted - totalPar;

        // Output hasil
        System.out.println("\n=== Golf Score Summary ===");
        System.out.println("Total Score: " + totalScore);
        System.out.println("Total Adjusted Score (ADJ): " + totalAdjusted);
        System.out.println("Handicap (HDC): " + hdc);
        System.out.println("Net Score (NET): " + net);
    }
}
