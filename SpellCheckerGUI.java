import java.util.*;

public class SpellChecker {

    static String[] dictionary = {
        "apple", "banana", "orange", "grape", "mango",
        "computer", "science", "java", "python", "project",
        "student", "college", "engineering", "keyboard", "mouse",
        "calendar", "school", "teacher", "book", "pen"
    };

    public static boolean isCorrect(String word) {
        for (String w : dictionary) {
            if (w.equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
    }

    public static String suggestWord(String input) {
        int minDistance = Integer.MAX_VALUE;
        String suggestion = "";

        for (String word : dictionary) {
            int distance = levenshteinDistance(input.toLowerCase(), word);
            if (distance < minDistance) {
                minDistance = distance;
                suggestion = word;
            }
        }
        return suggestion;
    }

    public static int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0)
                    dp[i][j] = j;
                else if (j == 0)
                    dp[i][j] = i;
                else if (a.charAt(i - 1) == b.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                            Math.min(dp[i - 1][j], dp[i][j - 1]));
            }
        }
        return dp[a.length()][b.length()];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Java Spell Checker ===");
        System.out.print("Enter a word: ");
        String input = sc.nextLine();

        if (isCorrect(input)) {
            System.out.println("✅ Correct word!");
        } else {
            String suggestion = suggestWord(input);
            System.out.println("❌ Incorrect word!");
            System.out.println("💡 Did you mean: " + suggestion + "?");
        }

        sc.close();
    }
}



Output : === Java Spell Checker ===
Enter a word: calendrr
? Incorrect word!
? Did you mean: calendar?

=== Code Execution Successful ===