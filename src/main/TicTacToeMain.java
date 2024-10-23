package main;

import java.util.Scanner;

public class TicTacToeMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int xWins = 0;
        int oWins = 0;
        int ties = 0;

        boolean playAgain = true;

        while (playAgain) {
            TicTacToe game = new TicTacToe();

            System.out.println("Welcome to Tic-Tac-Toe!");
            System.out.println(game);

            // Play the game
            while (game.notDone()) {
                Player currentPlayer = game.getCurrentPlayer();
                System.out.println("Player " + currentPlayer + ", enter your move as a two-digit number (e.g., 11 for top-left):");

                // Read the move as an integer, like 11, 23, etc.
                int move = scanner.nextInt();

                // Extract row and column from the two-digit number
                int row = move / 10 - 1; // first digit as row (1-based, convert to 0-based)
                int column = move % 10 - 1; // second digit as column (1-based, convert to 0-based)

                // Validate that the input is within the correct range
                while (row < 0 || row > 2 || column < 0 || column > 2 || !game.choose(row, column)) {
                    System.out.println("Invalid move. Please enter a valid two-digit number between 11 and 33 that is not already taken.");
                    move = scanner.nextInt();
                    row = move / 10 - 1;
                    column = move % 10 - 1;
                }

                // Display the board after the move
                System.out.println(game);

                // Check if the game has ended
                if (game.didWin(Player.X)) {
                    System.out.println("Player X wins!");
                    xWins++; // Increment M's score
                    break;
                } else if (game.didWin(Player.O)) {
                    System.out.println("Player O wins!");
                    oWins++; // Increment O's score
                    break;
                } else if (game.didTie()) {
                    System.out.println("It's a tie!");
                    ties++; // Increment tie counter
                    break;
                }
            }

            // Display current scores
            System.out.println("\nCurrent Scores:");
            System.out.println("Player X: " + xWins + " wins");
            System.out.println("Player O: " + oWins + " wins");
            System.out.println("Ties: " + ties);

            // Ask if they want to play again
            System.out.println("\nDo you want to play again? (y/n)");
            String response = scanner.next().toLowerCase();

            // Replay if the answer is "yes"
            playAgain = response.equals("y");
        }

        System.out.println("Thanks for playing Tic-Tac-Toe!");
        scanner.close();
    }
}
