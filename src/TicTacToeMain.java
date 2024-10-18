import java.util.Scanner;

public class TicTacToeMain {

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println(game);

        while (game.notDone()) {
            char currentPlayer = game.getNextPlayerChar();
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
                row = move / 10 - 1;  // Recalculate row
                column = move % 10 - 1;  // Recalculate column
            }

            // Display the board after the move
            System.out.println(game);

            // Check if the game has ended
            if (game.didWin('X')) {
                System.out.println("Player X wins!");
                break;
            } else if (game.didWin('O')) {
                System.out.println("Player O wins!");
                break;
            } else if (game.didTie()) {
                System.out.println("It's a tie!");
                break;
            }
        }

        System.out.println("Game over. Thanks for playing!");
        scanner.close();
    }
}
