import java.util.ArrayList;
import java.util.Scanner;

public class golepyuk {
    public static void main(String[] args) {
        Scanner egel = new Scanner(System.in);

        class GolfCourse {
            String name;
            int[] pars;

            GolfCourse(String name, int[] pars) {
                this.name = name;
                this.pars = pars;
            }
        }

        class Player {
            String name;
            int[] scores;
            int totalScore;
            int totalAdjustedScore;
            int handicap;

            Player(String name, int holes) {
                this.name = name;
                this.scores = new int[holes];
                this.totalScore = 0;
                this.totalAdjustedScore = 0;
                this.handicap = 0;
            }

            void calculateScores(int[] pars) {
                totalScore = 0;
                totalAdjustedScore = 0;
                for (int i = 0; i < pars.length; i++) {
                    totalScore += scores[i];
                    if (scores[i] > 2 * pars[i]) {
                        totalAdjustedScore += 2 * pars[i];
                    } else {
                        totalAdjustedScore += scores[i];
                    }
                }
                // handicap calculation
                handicap = totalScore - 72;
            }
        }

        // courses
        GolfCourse[] courses = {
                new GolfCourse("Abdulrachman Saleh Golf Course",
                        new int[] { 4, 4, 3, 5, 4, 4, 3, 4, 5, 5, 3, 4, 4, 4, 4, 3, 4, 5 }),
                new GolfCourse("The Araya Golf Course",
                        new int[] { 4, 4, 4, 3, 5, 3, 4, 5, 4, 5, 5, 4, 4, 3, 4, 3, 4, 4 })
        };

        // choose course
        System.out.println("Choose your golf course:");
        for (int i = 0; i < courses.length; i++) {
            System.out.println((i + 1) + ". " + courses[i].name);
        }
        System.out.print("Enter your choice (1 or 2): ");
        int choice = egel.nextInt();

        if (choice < 1 || choice > courses.length) {
            System.out.println("Invalid choice. Program will exit.");
            return;
        }

        GolfCourse selectedCourse = courses[choice - 1];
        System.out.println("You selected: " + selectedCourse.name);

        // Multiplayer mode
        System.out.print("Enter the number of players: ");
        int numPlayers = egel.nextInt();
        egel.nextLine(); // Consume newline

        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name of Player " + (i + 1) + ": ");
            String playerName = egel.nextLine();
            players.add(new Player(playerName, selectedCourse.pars.length));
        }

        // Input scores
        for (Player player : players) {
            System.out.println("Entering scores for " + player.name);
            for (int i = 0; i < selectedCourse.pars.length; i++) {
                while (true) {
                    System.out.print("Score for Hole #" + (i + 1) + " (Par " + selectedCourse.pars[i] + "): ");
                    int score = egel.nextInt();
                    if (score > 0) {
                        player.scores[i] = score;
                        break;
                    } else {
                        System.out.println("Invalid score. Please enter a positive number.");
                    }
                }
            }
            player.calculateScores(selectedCourse.pars);
        }

        // results
        System.out.println("\nFinal Results:");
        for (Player player : players) {
            System.out.println("Player: " + player.name);
            System.out.println("Total Score: " + player.totalScore);
            System.out.println("Adjusted Score: " + player.totalAdjustedScore);
            System.out.println("Handicap: " + player.handicap);
            System.out.println("-------------------------");
        }

        // the winner
        Player winner = players.get(0);
        for (Player player : players) {
            if (player.totalAdjustedScore < winner.totalAdjustedScore) {
                winner = player;
            }
        }
        System.out.println("Winner: " + winner.name + " with Adjusted Score: " + winner.totalAdjustedScore);

        egel.close();
    }
}
