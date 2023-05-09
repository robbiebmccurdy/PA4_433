import java.util.Scanner;

public class GuessingGame {
    public static int guess(int n, int k) {
        int[][] dp = new int[n+1][k+1];

        // base case: no chips
        for(int i=0; i<=n; i++) {
            dp[i][0] = 0;
        }

        // base case: only 1 chip
        for(int i=1; i<=n; i++) {
            dp[i][1] = i;
        }

        // bottom-up dynamic programming
        for(int j=2; j<=k; j++) {
            for(int i=1; i<=n; i++) {
                dp[i][j] = Integer.MAX_VALUE;
                for(int m=1; m<=i; m++) {
                    int cost = 1 + Math.max(dp[m-1][j-1], dp[i-m][j]);
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        return dp[n][k];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the number guessing game!");
        System.out.print("Enter the maximum target number (0 to n): ");
        int n = scanner.nextInt();

        System.out.print("Enter the number of chips (k): ");
        int k = scanner.nextInt();

        int guessCount = guess(n, k);

        System.out.println("I bet I can guess your number in at most " + guessCount + " questions!");
        System.out.println("Think of a number between 0 and " + n + ".");

        int left = 0;
        int right = n;

        while (left <= right) {
            int mid = (left + right) / 2;
            System.out.print("Is your number less than " + (mid + 1) + "? (y/n) ");
            String answer = scanner.next();

            if (answer.equalsIgnoreCase("y")) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println("Your number is " + left + ".");
        scanner.close();
    }
}